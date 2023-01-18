package controller.market;

import java.io.File;
import java.io.IOException;
import java.util.ArrayList;
import java.util.Enumeration;
import java.util.Iterator;
import java.util.List;

import javax.servlet.ServletException;
import javax.servlet.annotation.WebServlet;
import javax.servlet.http.HttpServlet;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;

import com.oreilly.servlet.MultipartRequest;
import com.oreilly.servlet.multipart.DefaultFileRenamePolicy;

import org.apache.commons.fileupload.*;

import dao.MarketDao;

@WebServlet("/marketPostInsertServlet")
public class MarketPostInsertServlet extends HttpServlet{
	private static final long serialVersionUID = 1L;

	@Override
	protected void doGet(HttpServletRequest req, HttpServletResponse resp) throws ServletException, IOException {
		doPost(req, resp);
	}

	@Override
	protected void doPost(HttpServletRequest request, HttpServletResponse response) throws ServletException, IOException {
		// 기본 설정
		request.setCharacterEncoding("utf-8");
	    response.setContentType("text/html; charset=UTF-8");
	    
	    int maxImgSize = 5 * 1024 * 1024;
	    String imgPath = "C:/webStudy/uploadFile";
	    
	    // 파라미터 기본값 설정
	    String part = null;
	    String writeID = null;
	    String title = null;
	    int price = 0;
	    String content = null;
	    String ip = request.getRemoteAddr();
	    
	    DiskFileUpload upload = new DiskFileUpload();
	    upload.setSizeMax(maxImgSize); // 업로드할 파일 최대 사이즈
	    upload.setSizeThreshold(maxImgSize); // 메모리상 저장할 파일 최대 사이즈 (int)
	    upload.setRepositoryPath(imgPath); // 파일 임시로 저장할 디렉토리 설정
	    
	    try {
	    	List items = upload.parseRequest(request); // 객체의 전송된 요청 파라미터 전달
	    	Iterator params = items.iterator(); // 전송된 요청 파라미터를 Iterator 클래스로 변환
	    	
	    	String[] imageList = new String[5];
		    int i = 0;
	    	while(params.hasNext()){ // 요청 파라미터 없을 때까지 반복
	    		// 해당 파라미터 가져와서 FileItem 객체로 저장
	    		FileItem item = (FileItem)params.next(); 
	    		if(item.isFormField()){ // 속성값 file이 아닌 form태그 요소들
	    			String name = item.getFieldName(); // 해당 요소의 요청 파라미터 이름(name값)
	    			switch(name) {// 해당 요소 값 얻기(인코딩:utf-8)
	    			case "part":
	    				part = item.getString("utf-8");
	    				break;
	    			case "writeID":
	    				writeID = item.getString("utf-8");
	    				break;
	    			case "title":
	    				title = item.getString("utf-8");
	    				break;
	    			case "price":
	    				price = Integer.parseInt(item.getString("utf-8"));
	    				break;
	    			case "content":
	    				content = item.getString("utf-8");
	    				break;
	    			}
	    		}
	    		else { // 속성값이 file인 form태그 요소(input 태그)
	    			String fileFieldName = item.getFieldName(); // 요청 파라미터 이름(name값)
	    			String fileName = item.getName(); // 업로드된 파일 경로 + 파일명
	    			// subString으로 문자열 잘라서 확장자 등 검사에 유용함!!
	    			fileName = fileName.substring(fileName.lastIndexOf("\\") + 1); // 파일명만 저장
	    			
	    			File file = new File(imgPath + "/" + fileName); // 파일 저장될 경로 지정
	    			item.write(file); // 해당되는 파일 관련 자원 저장하기 (실질적 파일 업로드!!)
	    			
	    			if(fileName != null) {
	    	    		String image = imgPath + "/" + fileName;
	    	    		imageList[i] = image;
	    	    		i++;
	    	    	}
	    		}
	    	}
	    	
	    	// insert
		    MarketDao marketDao = new MarketDao();
		    int marketNo = marketDao.insertPost(part, writeID, title, price, 0, content, ip, imageList);
		    
		    if(marketNo != 0) { // 게시글 등록 성공
		    	response.sendRedirect("/marketPostInfo?no=" + marketNo);
		    }
		    else { // 게시글 등록 실패
		    	response.sendRedirect("market/market.jsp?error=insertError");
		    }
	    } catch(Exception e) {
	    	e.printStackTrace();
	    	response.sendRedirect("market/market.jsp?error=insertError");
	    }
	    /*
	    MultipartRequest multi = new MultipartRequest(request,
	    		imgPath, // 예) "C:\\hyeahyun\\uploadFile"
	    		maxImgSize,
	    		"utf-8", // 예) "utf-8"
	    		new DefaultFileRenamePolicy() // 예) new DefaultFileRenamePolicy()
	    		);
	    
	    String part = multi.getParameter("part");
	    String writeID = multi.getParameter("writeID");
	    String title = multi.getParameter("title");
	    String price = multi.getParameter("price");
	    String content = multi.getParameter("content");
	    String ip = request.getRemoteAddr();
	    
	    // 가격 형변환
	    Integer priceInt;
	    if(price.isEmpty()) priceInt = 0;
	    else priceInt = Integer.valueOf(price);
	    
	    //input태그의 type="file"인 request 전달받아 저장
	    Enumeration<String> files = multi.getFileNames();
	    String[] imageList = new String[5];
	    int i = 0;
	    while(files.hasMoreElements()) {
	    	System.out.println("i = " + i);
	    	String fname = (String) files.nextElement(); // 파라미터 name 불러오기
	    	String fileName = multi.getFilesystemName(fname); // 디렉토리에 저장될 파일명
	    	System.out.println("fileName : " + fileName);
	    	if(fileName != null) {
	    		String image = imgPath + "/" + fileName;
	    		imageList[i] = image;
	    		i++;
	    	}
	    }
	    */
	    
	}

	
}
