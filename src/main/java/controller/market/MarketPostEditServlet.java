package controller.market;

import java.io.IOException;
import java.util.Enumeration;

import javax.servlet.ServletException;
import javax.servlet.annotation.WebServlet;
import javax.servlet.http.HttpServlet;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;

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
	    String imgPath = "C:\\webStudy";
	    
	    MultipartRequest multi = new MultipartRequest(request,
	    		imgPath, // 예) "C:\\hyeahyun\\uploadFile"
	    		maxImgSize,
	    		"utf-8", // 예) "utf-8"
	    		new DefaultFileRenamePolicy() // 예) new DefaultFileRenamePolicy()
	    		);
	    
	    String part = multi.getParameter("part");
	    int oldMarketNo = Integer.parseInt(multi.getParameter("oldMarketNo"));
	    String writeID = multi.getParameter("writeID");
	    String title = multi.getParameter("title");
	    String price = multi.getParameter("price");
	    int hits = Integer.parseInt(multi.getParameter("hits"));
	    String content = multi.getParameter("content");
	    String ip = request.getRemoteAddr();
	    
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
	    
	    /* 게시글 수정하기 */
	    MarketDao marketDao = new MarketDao();
	    MarketDto oldPost = marketDao.getPostInfoByNo(oldMarketNo);
	    // 수정된 게시글 insert
	    int newmarketNo = marketDao.insertPost(part, writeID, title, priceInt, oldPost.getHits(), content, ip, imageList);
	    
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
	}
}
