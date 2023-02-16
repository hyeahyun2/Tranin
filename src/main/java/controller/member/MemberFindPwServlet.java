package controller.member;

import java.io.IOException;
import java.io.PrintWriter;

import javax.servlet.RequestDispatcher;
import javax.servlet.ServletException;
import javax.servlet.annotation.WebServlet;
import javax.servlet.http.HttpServlet;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;

import dao.MemberDao;

@WebServlet("/memberFindPw/*")
public class MemberFindPwServlet extends HttpServlet {
	@Override
	protected void doGet(HttpServletRequest req, HttpServletResponse resp) throws ServletException, IOException {
		doPost(req, resp);
	}

	@Override
	protected void doPost(HttpServletRequest req, HttpServletResponse resp) throws ServletException, IOException {
		// 기본 설정
		req.setCharacterEncoding("UTF-8");
		resp.setContentType("text/html; charset=UTF-8");
		
		String RequestURI = req.getRequestURI(); // 전체 경로
		String contextPath = req.getContextPath(); // 프로젝트 Path
		// 전체 경로에서 프로젝트 Path 길이 만큼의 인덱스 이후 문자열
		String command = RequestURI.substring(contextPath.length());
		
		
		if(command.contains("/emailAuthCheck")) { // 이메일로 메일 전송
			reqEmailCheck(req, resp);
		}
		else if(command.contains("/goChangePw")) { // 비밀번호 변경 페이지로 이동
			String memberId = req.getParameter("id");
			req.setAttribute("memberId", memberId); // 비밀번호를 바꿀 이메일 전송
			RequestDispatcher rd = req.getRequestDispatcher("/member/changePw.jsp");
			rd.forward(req, resp);
		}
		else if(command.contains("/changePwSubmit")) { // 비밀번호 변경
			reqChangePwSubmit(req);
			if(reqChangePwSubmit(req)) { // 비밀번호 변경 성공
				PrintWriter out = resp.getWriter();
				out.append("<script>alert('정상적으로 비밀번호가 변경되었습니다.')</script>");
				RequestDispatcher rd = req.getRequestDispatcher("/member/login.jsp");
				rd.forward(req, resp);
			}
			else { // 비밀번호 변경 실패
				PrintWriter out = resp.getWriter();
				out.append("<script>alert('시스템 에러가 발생했습니다.')</script>");
				RequestDispatcher rd = req.getRequestDispatcher("/member/findPw.jsp");
				rd.forward(req, resp);
			}
		}
		else {
			resp.sendRedirect("/errorPage/errorNotFoundPage.jsp");
		}
	}
	
	// 이메일 인증 메일 전송
	private void reqEmailCheck(HttpServletRequest req, HttpServletResponse resp) throws IOException {
		String id = req.getParameter("id");
		
		StringBuffer result = new StringBuffer("");
		result.append("{\"result\": ");
		if(id == null || id.equals("")) {
			result.append("\"false\"}");
		}
		else {
			MemberDao dao = new MemberDao();
			// 아이디 중복 검사
			if(dao.isMemberId(id)) { // 중복 아이디 존재
				// 인증번호 이메일로 전송하기
				String authKey = new SendEmailAuth().sendMail(id);
				// 이메일 인증 번호 전송 성공
				result.append("\"true\" , \"authKey\": \""+ authKey +"\"}");
			}
		}
		// 보내기
		resp.getWriter().write(result.toString());
	}
	
	// 비밀번호 변경
	private boolean reqChangePwSubmit(HttpServletRequest req) {
		
		String id = req.getParameter("id");
		String pw = req.getParameter("pw");
		PasswdEncry pwEn = new PasswdEncry();
		String newPw = pwEn.getEncry(pw, "testSalt");
		
		MemberDao dao = new MemberDao();
		
		if(dao.changePw(id, newPw)) { // 정상적으로 변경 완료
			return true;
		}
		else {
			return false;
		}
	}
}
