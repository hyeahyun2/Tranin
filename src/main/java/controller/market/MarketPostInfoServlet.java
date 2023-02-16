package controller.market;

import java.io.IOException;

import javax.servlet.RequestDispatcher;
import javax.servlet.ServletException;
import javax.servlet.annotation.WebServlet;
import javax.servlet.http.Cookie;
import javax.servlet.http.HttpServlet;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;

import dao.MarketDao;
import dao.MemberInfoDao;
import dto.MarketDto;
import dto.MemberDto;

@WebServlet("/marketPostInfo")
public class MarketPostInfoServlet extends HttpServlet {
	private static final long serialVersionUID = 1L;

	@Override
	protected void doGet(HttpServletRequest req, HttpServletResponse resp) throws ServletException, IOException {
		doPost(req, resp);
	}

	@Override
	protected void doPost(HttpServletRequest request, HttpServletResponse response) throws ServletException, IOException {
		// marketPost.jsp?no=${result[i].no}
		// 기본 설정
		response.setContentType("text/html; charset=UTF-8");

        String marketNo = request.getParameter("no");
        // no가 빈 경우 -> 에러 페이지로 이동
        if(marketNo == null || marketNo.equals("")) {
        	response.sendRedirect("/errorPage/errorNotFoundPage.jsp");
        }
        
        int no = Integer.parseInt(marketNo);
        
        MarketDao marketDao = new MarketDao();
        
        // 1. 조회수 중복 방지 (쿠키 활용, postView key에 market_no 저장)
        Cookie oldCookie = null;
        Cookie[] cookies = request.getCookies();
        if(cookies != null) { // 쿠키 찾기
        	for(Cookie cookie : cookies){
        		if(cookie.getName().equals("postView")) {
        			oldCookie = cookie;
        		}
        	}
        }
        if (oldCookie != null) { // 쿠키값이 존재하면
        	// market_no가 쿠키에 저장됐는지 확인
			if (!oldCookie.getValue().contains("["+ no +"]")) { // 저장x (조회 x)
				marketDao.increaseHits(no); // 조회수 증가
				oldCookie.setValue(oldCookie.getValue() + "_[" + no + "]"); // 쿠키 값에 추가
				oldCookie.setPath("/"); // 쿠키 범위 설정 (웹 어플리케이션의 모든 url 범위)
				oldCookie.setMaxAge(60 * 60 * 24); // 쿠키 시간
				response.addCookie(oldCookie); // 쿠키 업데이트(덮어쓰기)
			}
		} 
        else { // 쿠키값 존재하지 않으면
        	marketDao.increaseHits(no); // 조회수 증가
			Cookie newCookie = new Cookie("postView", "[" + no + "]");
			newCookie.setPath("/"); // 쿠키 범위 설정 (웹 어플리케이션의 모든 url 범위)
			newCookie.setMaxAge(60 * 60 * 24); // 쿠키 시간
			response.addCookie(newCookie); // 새 쿠키 굽기
		}
        
        // 2. market_no에 해당하는 글 정보 얻기
        MarketDto post = marketDao.getPostInfoByNo(no);
        
        // disabled 처리된 글일 경우 -> 에러 페이지로 이동
        if(post == null) {
        	response.sendRedirect("/errorPage/errorNotFoundPage.jsp");
        }
        else { // 아닌 경우 -> 정상적으로 상품 상세 페이지로 이동
        	// 글쓴이 정보
        	MemberDto member = new MemberInfoDao().getMemberByNo(post.getWriterNo());
        	// 전달할 파라미터
        	request.setAttribute("post", post); // 글 정보
        	request.setAttribute("writerNick", member.getNickName()); // 글쓴이 닉네임
        	request.setAttribute("writerId", member.getId()); // 글쓴이 아이디
        	RequestDispatcher dispatcher = request.getRequestDispatcher("/market/marketPost.jsp");
        	dispatcher.forward(request, response);
        }
	}
	
}
