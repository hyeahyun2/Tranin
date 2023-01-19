package controller.market;

import java.io.File;
import java.io.IOException;
import java.util.Enumeration;
import java.util.Iterator;
import java.util.List;

import javax.servlet.ServletException;
import javax.servlet.annotation.WebServlet;
import javax.servlet.http.HttpServlet;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;

import org.apache.commons.fileupload.DiskFileUpload;
import org.apache.commons.fileupload.FileItem;

import com.oreilly.servlet.MultipartRequest;
import com.oreilly.servlet.multipart.DefaultFileRenamePolicy;

import dao.MarketDao;
import dto.MarketDto;

@WebServlet("/marketPostEdit")
public class MarketPostEditServlet extends HttpServlet {
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
	    //저장될 위치 정해주기
	    String realPath = request.getServletContext().getRealPath("resources/images");
	    File dir = new File(realPath);
	    if(!dir.exists()){ // 만약 해당 디렉토리(경로)가 존재하지 않으면
	    	dir.mkdirs(); // 해당 경로를 만들어주기
	    }
	    
	    int oldMarketNo = 0;
	    
	    // 파라미터 기본값 설정
	    String writeID = null;
	    String ip = request.getRemoteAddr();
	    String allRemoveImg = request.getParameter("removeImg_load");
	    String[] removeImgsInLoad = null;
	    if(allRemoveImg != null) {
	    	removeImgsInLoad = allRemoveImg.split(","); // 삭제한 이미지 모음
	    }
	    
	    MarketDao marketDao = new MarketDao();
	    MarketDto oldPost = new MarketDto();
	    MarketDto newPost = new MarketDto();
	    newPost.setIp(ip);
	    
	    DiskFileUpload upload = new DiskFileUpload();
	    upload.setSizeMax(maxImgSize); // 업로드할 파일 최대 사이즈
	    upload.setSizeThreshold(maxImgSize); // 메모리상 저장할 파일 최대 사이즈 (int)
	    upload.setRepositoryPath(realPath); // 파일 임시로 저장할 디렉토리 설정
	    
	    try {
	    	List items = upload.parseRequest(request); // 객체의 전송된 요청 파라미터 전달
	    	Iterator params = items.iterator(); // 전송된 요청 파라미터를 Iterator 클래스로 변환
	    	
	    	String[] imageList = new String[5];
		    int i = 0; // newPost의 image 인덱스 번호
	    	while(params.hasNext()){ // 요청 파라미터 없을 때까지 반복
	    		// 해당 파라미터 가져와서 FileItem 객체로 저장
	    		FileItem item = (FileItem)params.next(); 
	    		if(item.isFormField()){ // 속성값 file이 아닌 form태그 요소들
	    			String name = item.getFieldName(); // 해당 요소의 요청 파라미터 이름(name값)
	    			switch(name) {// 해당 요소 값 얻기(인코딩:utf-8)
	    			case "part":
	    				newPost.setPart(item.getString("utf-8"));
	    				break;
	    			case "writeID":
	    				writeID = item.getString("utf-8");
	    				break;
	    			case "title":
	    				newPost.setTitle(item.getString("utf-8"));
	    				break;
	    			case "price":
	    				newPost.setPrice(Integer.parseInt(item.getString("utf-8")));
	    				break;
	    			case "content":
	    				newPost.setContent(item.getString("utf-8"));
	    				break;
	    			case "oldMarketNo":
	    				oldMarketNo = Integer.parseInt(item.getString("utf-8"));
	    				oldPost = marketDao.getPostInfoByNo(oldMarketNo);
	    				// 삭제한 이미지 삭제하기 반영
	    				if(removeImgsInLoad != null) {
	    					for(int j=0; j<removeImgsInLoad.length; j++) {
	    						for(int k=0; k<oldPost.getImage().length; k++) {
	    							String remveImgUrl = realPath + "/" + removeImgsInLoad[j];
	    							if(oldPost.getImage()[k] == remveImgUrl) {
	    								oldPost.setImage(k, null);
	    							}
	    						}
	    					}
	    				}
	    				// 삭제하지 않은 이미지는 newPost에 추가
	    				for(String img : oldPost.getImage()) {
	    					if(img != null) {
	    						// i : pewPost의 image의 인덱스번호
	    						newPost.setImage(i, img);
	    						i++;
	    					}
	    				}
	    				break;
	    			}
	    		}
	    		else { // 속성값이 file인 form태그 요소(input 태그)
	    			String fileFieldName = item.getFieldName(); // 요청 파라미터 이름(name값)
	    			String fileName = item.getName(); // 업로드된 파일 경로 + 파일명
    				
    				
    				if(fileName != null) {
    					System.out.println("fileFieldName : " + fileFieldName);
    					System.out.println("fileName : " + fileName);
    					// subString으로 문자열 잘라서 확장자 등 검사에 유용함!!
    					fileName = fileName.substring(fileName.lastIndexOf("\\") + 1); // 파일명만 저장
    					File file = new File(realPath + "/" + fileName); // 파일 저장될 경로 지정
    					item.write(file); // 해당되는 파일 관련 자원 저장하기 (실질적 파일 업로드!!)
    					String image = realPath + "/" + fileName;
    					newPost.setImage(i, image); // MarketDto 객체 image 필드 setter
    					i++;
    				}
	    		}
	    	}
	    	
	    	// 조회수 이전
	    	if(oldMarketNo != 0) {
	    		newPost.setHits(oldPost.getHits());
	    	}
	    	else {
	    		newPost.setHits(0);
	    	}
	    	
	    	/* 게시글 수정하기 */
		    // 수정된 게시글 insert
		    int newmarketNo = marketDao.insertPost(newPost, writeID);
		    
		    if(newmarketNo != 0) { // 게시글 재등록 완
		    	// 수정전 게시글 disabled 처리
		    	boolean state = marketDao.setDisabledByNo(oldMarketNo);
		    	if(state) {
		    		// disabled table에 수정전 글 insert
		    		marketDao.insertToDisabledReportUpdate(oldMarketNo, newmarketNo);
		    		response.sendRedirect("/marketPostInfo?no=" + newmarketNo);
		    	}
		    }
		    else { // 게시글 등록 실패
		    	response.sendRedirect("market/market.jsp?error=insertError");
		    }
	    } catch(Exception e) {
	    	e.printStackTrace();
	    }
	}
}
