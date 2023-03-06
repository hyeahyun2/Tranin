package controller.market;

import java.io.IOException;
import java.io.PrintWriter;

import javax.servlet.RequestDispatcher;
import javax.servlet.ServletException;
import javax.servlet.annotation.WebServlet;
import javax.servlet.http.HttpServlet;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;

import dao.MarketDao;
import dao.MemberInfoDao;
import dto.MarketDto;

@WebServlet("/marketGoEditPage")
public class MarketGoEditPageServlet extends HttpServlet {
	private static final long serialVersionUID = 1L;

	@Override
	protected void doGet(HttpServletRequest request, HttpServletResponse response) throws ServletException, IOException {
		doPost(request, response);
	}

	@Override
	protected void doPost(HttpServletRequest request, HttpServletResponse response) throws ServletException, IOException {
		
		// 기본 설정
		response.setContentType("text/html; charset=UTF-8");
        
		String loginId = (String)request.getSession().getAttribute("memberId");
		String marketNo = request.getParameter("no");
		
		// 파라미터가 비어있다면
		if(loginId == null || loginId.equals("") || marketNo == null || marketNo.equals("")) {
			response.sendRedirect("/errorPage/errorNotFoundPage.jsp");
		}
		int no = Integer.parseInt(marketNo);
		
		MarketDao marketDao = new MarketDao();
		
		// 게시글의 글쓴이인지 확인
		int loginNo = new MemberInfoDao().getMemberById(loginId).getNo();
		int postMemberNo = marketDao.getPostInfoByNo(no).getWriterNo();
		
		if(loginNo != postMemberNo) { // 게시글 작성자가 아니라면
			PrintWriter out = response.getWriter();
			out.append("<script>alert('게시글 작성자가 아닙니다. 로그인 정보를 확인해주세요.')</script>");
			out.flush();
            out.close();
		}
		
		// 게시글 수정 페이지로 이동
		// market_no에 해당하는 글 정보 얻기
        MarketDto post = marketDao.getPostInfoByNo(no);
        System.out.println(post.getPart());
        // 전달할 파라미터
    	request.setAttribute("post", post); // 글 정보
        RequestDispatcher dispatcher = request.getRequestDispatcher("/market/editMarket.jsp");
    	dispatcher.forward(request, response);
	}
}
