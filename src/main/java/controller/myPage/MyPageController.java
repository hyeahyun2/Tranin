package controller.myPage;

import java.io.IOException;
import java.util.ArrayList;

import javax.servlet.RequestDispatcher;
import javax.servlet.ServletException;
import javax.servlet.annotation.WebServlet;
import javax.servlet.http.HttpServlet;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;

import dao.MyPageDao;
import dto.MarketResponseDto;
import dto.MemberDto;

@WebServlet("/myPage/myPage")
public class MyPageController extends HttpServlet {
	private static final long serialVersionUID = 1L;
	public MyPageController() {
        super();
    }
	protected void doGet(HttpServletRequest request, HttpServletResponse response) throws ServletException, IOException {
		
		response.setCharacterEncoding("utf-8");
		response.setContentType("text/html");
		
		String param = request.getParameter("myPageCategory");
		if(param==null) {
			param = "0";
		}
		request.setAttribute("myPageParam",param);
		
		String param2 = request.getParameter("myPageManagerCategory");
		if(param2==null) {
			param2 = "0";
		}
		request.setAttribute("myPageManagerParam",param2);
		System.out.println("MyPageController 입장");
		
		/*
		 * 거래내역 선택한 경우(parameter가 2인경우)
		 */
		/*
		 * 거래완료 버튼은 글 작성자만 누를수 있다
		 * 거래완료 시 update 형식으로 장터글을 disabled = on 한 다음 disabled 테이블에 report를
		 * 적는식으로 한다. (report가 "거래완료"인 경우로 거래진행상황을 알 수 있다)
		 * MarketDto의 모든 내용 + report가 "거래완료" 인경우
		 * String sql = 
		 * "SELECT DISTINCT tranin_market.*, tranin_market_disabled.report 
		 *  FROM tranin_market 
		 *  RIGHT JOIN tranin_market_disabled 
		 *  ON tranin_market.market_no = tranin_market_disabled.market_no 
		 *  WHERE tranin_market.disabled='true' 
		 *  AND tranin_market_disabled.report='done' 
		 *  AND tranin_market.writer_no=? 
		 *  ORDER BY market_no 
		 *  DESC LIMIT ?, ?";
		 *  //SELECT DISTINCT tranin_market.*, tranin_market_disabled.report FROM tranin_market RIGHT JOIN tranin_market_disabled ON tranin_market.market_no = tranin_market_disabled.market_no WHERE tranin_market.disabled='true' AND tranin_market_disabled.report='done' AND tranin_market.writer_no=? ORDER BY market_no DESC LIMIT ?, ?
		 */
		if(Integer.parseInt(param)==2) {
			MyPageDao dao = new MyPageDao();
			
			if(request.getParameter("select")!=null) {
				//writer_no 이자 현재 로그인한 member_no 이기도 함
		        int writerNo = dao.getMemberNoById((String)request.getSession().getAttribute("memberId"));
				int pageNum = 1;
		        if(request.getParameter("myPageTransactionNo")!=null) {
		        	pageNum = Integer.parseInt(request.getParameter("myPageTransactionNo"));
		        }
		        request.setAttribute("myPageTransactionNo",pageNum);
		        
				ArrayList<MarketResponseDto> transactionDoneMarketArrayList = dao.getSearchTransactionDoneMarketList((String)request.getParameter("select"),(String)request.getParameter("keyword"),writerNo, pageNum);
				request.setAttribute("transactionDoneMarketArrayList", transactionDoneMarketArrayList);
			}else {
				//writer_no 이자 현재 로그인한 member_no 이기도 함
		        int writerNo = dao.getMemberNoById((String)request.getSession().getAttribute("memberId"));
		        int pageNum = 1;
		        if(request.getParameter("myPageTransactionNo")!=null) {
		        	pageNum = Integer.parseInt(request.getParameter("myPageTransactionNo"));
		        }
		        request.setAttribute("myPageTransactionNo",pageNum);
		        
				ArrayList<MarketResponseDto> transactionDoneMarketArrayList = dao.getTransactionDoneMarketList(writerNo, pageNum);
				request.setAttribute("transactionDoneMarketArrayList", transactionDoneMarketArrayList);
			
			}
		
		}
		RequestDispatcher requestDispatcher = request.getRequestDispatcher("/myPage/myPage.jsp");
		requestDispatcher.forward(request, response);
	}
}