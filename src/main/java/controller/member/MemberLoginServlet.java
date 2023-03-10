package controller.member;

import java.io.IOException;

import javax.servlet.RequestDispatcher;
import javax.servlet.ServletException;
import javax.servlet.annotation.WebServlet;
import javax.servlet.http.Cookie;
import javax.servlet.http.HttpServlet;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;

import dao.MemberDao;
import dao.MemberInfoDao;

@WebServlet("/memberLogin")
public class MemberLoginServlet extends HttpServlet {

	@Override
	protected void doGet(HttpServletRequest req, HttpServletResponse resp) throws ServletException, IOException {
		doPost(req, resp);
	}

	@Override
	protected void doPost(HttpServletRequest req, HttpServletResponse resp) throws ServletException, IOException {
		// 기본 설정
		req.setCharacterEncoding("UTF-8");
		resp.setContentType("text/html; charset=UTF-8");
		
		String id = req.getParameter("id");
		String pw = req.getParameter("pw");
		String autoLogin = req.getParameter("autoLogin");
		
		// 비밀번호 암호화
		PasswdEncry pwEn = new PasswdEncry();
//		String newPw = pwEn.getEncry(pw, pwEn.getSalt());
		String newPw = pwEn.getEncry(pw, "testSalt");
		
		// 공백일 경우 => 로그인 페이지 유지
		if(id == null || id.equals("") || pw == null || pw.equals("")) {
			resp.sendRedirect("/member/login.jsp");
		}
		else { // 공백 아닐 경우 => 로그인 정보 확인
			MemberDao dao = new MemberDao();
			
			if(dao.loginMember(id, newPw)) { // 로그인 정보 일치
				// 차단 멤버인지 확인
				if(dao.isBanMember(id)) { // 차단 멤버라면
					req.setAttribute("errorMsg", "isBanMember"); // 에러메세지 전송
					RequestDispatcher rd = req.getRequestDispatcher("/member/login.jsp"); // 로그인 페이지로 재이동
					rd.forward(req, resp);
				}
				else {
					// 닉넴 얻기
					String nickname = new MemberInfoDao().getNicknameById(id);
					// 세션 굽기
					req.getSession().setAttribute("memberId", id); 
					req.getSession().setAttribute("memberNick", nickname); 
					
					/* 자동로그인 관련 코드 작성 */
					if(autoLogin != null && autoLogin.equals("true")) {
						Cookie autoLoginCk = new Cookie("autoLogin", id);
						autoLoginCk.setMaxAge(60 * 60 * 24 * 30); // 자동로그인 기간 = 30일
						resp.addCookie(autoLoginCk);
					}
					
					resp.sendRedirect("/index.jsp"); // index 페이지로 이동
				}
				
			}
			else { // 로그인 정보 불일치
				req.setAttribute("errorMsg", "loginFail"); // 에러메세지 전송
				RequestDispatcher rd = req.getRequestDispatcher("/member/login.jsp"); // 로그인 페이지로 재이동
				rd.forward(req, resp);
			}
		}
	}

	
}
