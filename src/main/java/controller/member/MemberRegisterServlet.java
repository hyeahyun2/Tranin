package controller.member;

import java.io.IOException;
import java.io.PrintWriter;

import javax.mail.AuthenticationFailedException;
import javax.servlet.RequestDispatcher;
import javax.servlet.ServletException;
import javax.servlet.annotation.WebServlet;
import javax.servlet.http.HttpServlet;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;

import dao.MemberDao;
import dto.MemberDto;

@WebServlet("/memberRegister/*")
public class MemberRegisterServlet extends HttpServlet {

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
		
		if(command.contains("/registerSubmit")) { // 회원 가입 버튼 클릭
			PrintWriter out = resp.getWriter();
			if(reqRegister(req)) { // 회원가입 성공
//				out.append("<script>alert('회원가입에 성공했습니다!')</script>");
//				out.flush();
//	            out.close();
				req.setAttribute("successMsg", "registerSuccess");
				RequestDispatcher rd = req.getRequestDispatcher("/member/login.jsp");
				rd.forward(req, resp);
			}
			else { // 회원가입 실패
//				out.append("<script>alert('회원가입에 실패했습니다! 다시 진행해주세요.')</script>");
				req.setAttribute("errorMsg", "registerfail");
				RequestDispatcher rd = req.getRequestDispatcher("/member/register.jsp");
				rd.forward(req, resp);
			}
			
		}
		else if(command.contains("/emailAuthCheck")) { // 이메일 인증 번호 전송
			reqEmailCheck(req, resp);
		}
		else if(command.contains("/emailAuthNumCheck")) { // 이메일 인증 번호 확인
			reqEmailNumCheck(req, resp);
		}
		else if(command.contains("/nickDuplCheck")) { // 별명 중복 확인
			reqNickDuplCheck(req, resp);
		}
	}
	
	// 회원가입
	private boolean reqRegister(HttpServletRequest req) {
		String id = req.getParameter("email");
		String pw = req.getParameter("pw");
		String nickname = req.getParameter("nickname");
		String zipcode = req.getParameter("zipCode");
		String address1 = req.getParameter("address01");
		String address2 = req.getParameter("address02");
		String address = address1 + " " + address2;
		
		// 비밀번호 암호화
		PasswdEncry pwEn = new PasswdEncry();
		String newPw = pwEn.getEncry(pw, "testSalt");
		
		// member 객체 생성
		MemberDto member = new MemberDto();
		member.setId(id);
		member.setPw(newPw);
		member.setNickName(nickname);
		member.setZipCode(zipcode);
		member.setAddress(address);
		
		MemberDao dao = new MemberDao();
		if(dao.insertMember(member)) { // 회원가입 성공
			return true;
		}
		else { // 회원가입 실패
			return false;
		}
	}
	
	// 이메일 인증 번호 전송
	private void reqEmailCheck(HttpServletRequest req, HttpServletResponse resp) throws IOException {
		String id = req.getParameter("id");
		
		StringBuffer result = new StringBuffer("");
		result.append("{\"result\": ");
		if(id == null || id.equals("")) {
			result.append("\"fail\"}");
		}
		else {
			MemberDao dao = new MemberDao();
			// 아이디 중복 검사
			if(dao.isMemberId(id)) { // 중복 아이디 존재
				result.append("\"duplicated\"}");
			}
			else { // 중복 아이디 존재하지 않음
				// 인증번호 이메일로 전송하기
				String authKey = new SendEmailAuth().sendMail(id);
				result.append("\"true\" , \"authKey\": \""+ authKey +"\"}");
			}
		}
		// 보내기
		resp.getWriter().write(result.toString());
	}
	
	// 이메일 인증 번호 확인
	private void reqEmailNumCheck(HttpServletRequest req, HttpServletResponse resp) throws IOException {
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
				result.append("\"duplicated\"}");
			}
			else { // 중복 아이디 존재하지 않음
				
				// 이메일 인증 번호 전송 성공
				result.append("\"true\"}");
			}
		}
		// 보내기
		resp.getWriter().write(result.toString());
	}
	
	
	// 별명 중복 확인
	private void reqNickDuplCheck(HttpServletRequest req, HttpServletResponse resp) throws IOException {
		String nickname = req.getParameter("nickname");

		StringBuffer result = new StringBuffer("");
		result.append("{\"result\": ");
		if(nickname == null || nickname.equals("")) {
			result.append("\"false\"}");
		}
		else {
			MemberDao dao = new MemberDao();
			if(dao.isMemberNick(nickname)) { // 중복 별명 있을 경우
				result.append("\"false\"}");
			}
			else { // 중복 별명 있는 경우
				result.append("\"true\"}");
			}
		}
		// 보내기
		resp.getWriter().write(result.toString());
	}
}
