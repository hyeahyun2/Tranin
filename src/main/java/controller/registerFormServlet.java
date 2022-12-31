package controller;

import java.io.IOException;
import javax.servlet.ServletException;
import javax.servlet.annotation.WebServlet;
import javax.servlet.http.HttpServlet;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;

import dao.MemberInsertDao;

@WebServlet("/registerFormServlet")
public class registerFormServlet extends HttpServlet {
	private static final long serialVersionUID = 1L;

	
	protected void doPost(HttpServletRequest request, HttpServletResponse response) throws ServletException, IOException {
		// 기본 세팅(인코딩)
		request.setCharacterEncoding("utf-8");
		response.setContentType("text/html; charset=UTF-8");
		
		String memberId = request.getParameter("memberId");
		String password = request.getParameter("password");
		String nickName = request.getParameter("nickName");
		String zipCode = request.getParameter("zipCode");
		String address1 = request.getParameter("address1");
		String address2 = request.getParameter("address2");
		
		String address = address1 + " " + address2;
		
		MemberInsertDao dao = new MemberInsertDao();
		boolean state = dao.register(memberId, password, nickName, address, zipCode);
		
		if(state){ // 회원가입 성공
			request.getSession().setAttribute("memberId", memberId);
			response.sendRedirect("index.jsp");
		}
		else { // 회원가입 실패
			response.sendRedirect("register.jsp");
		}
	}

}
