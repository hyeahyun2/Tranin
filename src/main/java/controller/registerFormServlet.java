package controller;

import java.io.IOException;
import java.io.PrintWriter;
import java.io.UnsupportedEncodingException;
import java.security.NoSuchAlgorithmException;
import javax.servlet.ServletException;
import javax.servlet.annotation.WebServlet;
import javax.servlet.http.HttpServlet;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;

import Cryptoutils.Sha;
import dao.MemberInsertDao;

@WebServlet("/registerFormServlet")
public class registerFormServlet extends HttpServlet {

	protected void doPost(HttpServletRequest request, HttpServletResponse response) throws  ServletException, IOException {
		// 기본 세팅(인코딩)
		request.setCharacterEncoding("utf-8");
		response.setContentType("text/html; charset=UTF-8");

		Sha sha = new Sha();
		
		String memberId = request.getParameter("memberId");
		String password = request.getParameter("password");

		String NewPassword = sha.encode(password);



		String nickName = request.getParameter("nickName");
		String zipCode = request.getParameter("zipCode");
		String address1 = request.getParameter("address1");
		String address2 = request.getParameter("address2");

		String address = address1 + "," + address2;
		
		MemberInsertDao dao = new MemberInsertDao();
		boolean state = dao.register(memberId, NewPassword, nickName, address, zipCode);
		
		if(state){ // 회원가입 성공
			request.getSession().setAttribute("memberId", memberId);
			request.getSession().setAttribute("nickname", nickName);
			PrintWriter out = response.getWriter();
			out.println("<script>alert('"+ request.getSession().getAttribute("nickname")+"님 환영합니다!')</script>");
			out.println("<script>location.href='index.jsp'</script>");
			out.flush();
			out.close();
		}
		else { // 회원가입 실패
			PrintWriter out = response.getWriter();
			out.println("<script>alert('회원가입에 실패 하였습니다. 다시 시도해주세요.')</script>");
			out.println("<script>location.href='/UM/register.jsp'</script>");
			out.flush();
			out.close();
		}
	}

}
