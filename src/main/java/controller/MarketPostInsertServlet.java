package controller;

import java.io.IOException;
import java.util.ArrayList;
import java.util.Enumeration;

import javax.servlet.ServletException;
import javax.servlet.annotation.WebServlet;
import javax.servlet.http.HttpServlet;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;

import com.oreilly.servlet.MultipartRequest;
import com.oreilly.servlet.multipart.DefaultFileRenamePolicy;

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
	    
	    System.out.println("market insert servlet 접속 성공");
	    
	    int maxImgSize = 5 * 1024 * 1024;
	    String imgPath = "C:\\webStudy";
	    
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
	    
	    // 가격 형변환
	    Integer priceInt;
	    if(price.isEmpty()) priceInt = 0;
	    else priceInt = Integer.valueOf(price);
	    
	    //input태그의 type="file"인 request 전달받아 저장
	    Enumeration files = multi.getFileNames();
	    String[] imageList = new String[5];
	    int i = 0;
	    while(files.hasMoreElements()) {
	    	String fname = (String) files.nextElement(); // 파라미터 name 불러오기
	    	String fileName = multi.getFilesystemName(fname); // 디렉토리에 저장될 파일명
	    	if(fileName != null) {
	    		String image = imgPath + "/" + fileName;
	    		imageList[i] = image;
	    		i++;
	    	}
	    }
	    
	    // insert
	    MarketDao marketDao = new MarketDao();
	    int marketNo = marketDao.insertPost(part, writeID, title, priceInt, content, imageList);
	    
	    if(marketNo != 0) { // 게시글 등록 성공
	    	response.sendRedirect("market/marketPost.jsp?marketNo=" + marketNo);
	    }
	    else { // 게시글 등록 실패
	    	response.sendRedirect("market/market.jsp?error=insertError");
	    }
	}

	
}
