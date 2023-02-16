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

@WebServlet("/market/soldOutCheck")
public class MarketPostSoldOutServlet extends HttpServlet {

	@Override
	protected void doGet(HttpServletRequest req, HttpServletResponse resp) throws ServletException, IOException {
		doPost(req, resp);
	}

	@Override
	protected void doPost(HttpServletRequest req, HttpServletResponse resp) throws ServletException, IOException {
		// 기본 설정
		resp.setContentType("text/html; charset=UTF-8");
		
		String marketNo = req.getParameter("marketNo");
		String memberId = (String)req.getSession().getAttribute("memberId");
		
		MarketDao marketDao = new MarketDao();
		MemberInfoDao memberInfoDao = new MemberInfoDao();
		int postWriterNo = marketDao.getPostInfoByNo(Integer.parseInt(marketNo)).getWriterNo();
		int memberNo = memberInfoDao.getMemberById(memberId).getNo();
		
		PrintWriter out = resp.getWriter();

		if(postWriterNo != memberNo) { // 로그인멤버 != 판매자
			out.append("<script>alert('게시글 작성자가 아닙니다. 로그인 정보를 확인해주세요.')</script>");
			out.flush();
            out.close();
			resp.sendRedirect("/marketPostInfo?no=" + marketNo);
			return;
		}
		else {
			// 게시글 비활성화
			// disabled = true 처리
			boolean state = marketDao.setDisabledByNo(Integer.parseInt(marketNo));
			if(state) {
				// 해당 글 market_disabled table에 insert
				marketDao.insertToDisabled("soldOut", Integer.parseInt(marketNo));		
				out.append("<script>alert('판매 완료 처리가 완료되었습니다.')</script>");
				out.flush();
	            out.close();
				resp.sendRedirect("/market/market.jsp");
				return;
			}
		}
	}
	

}
