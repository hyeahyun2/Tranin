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

@WebServlet("/member/memberRegisterTOSServlet")
public class MemberRegisterTOSServlet extends HttpServlet {

	@Override
	protected void doGet(HttpServletRequest req, HttpServletResponse resp) throws ServletException, IOException {
		doPost(req, resp);
	}

	@Override
	protected void doPost(HttpServletRequest req, HttpServletResponse resp) throws ServletException, IOException {
		System.out.println(req.getParameter("tosLabelAll"));
		if(req.getParameter("tosLabelAll").equals("on")) {
			RequestDispatcher rd = req.getRequestDispatcher("/WEB-INF/member/register.jsp");
			rd.forward(req, resp);
		}else {
			resp.sendRedirect("../");
		}
		
		
	}

	
}
