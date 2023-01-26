package controller.member;

import java.io.IOException;

import javax.servlet.ServletException;
import javax.servlet.annotation.WebServlet;
import javax.servlet.http.Cookie;
import javax.servlet.http.HttpServlet;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;

@WebServlet("/memberLogout")
public class MemberLogout extends HttpServlet {

	@Override
	protected void doGet(HttpServletRequest req, HttpServletResponse resp) throws ServletException, IOException {
		doPost(req, resp);
	}

	@Override
	protected void doPost(HttpServletRequest req, HttpServletResponse resp) throws ServletException, IOException {
		String url = req.getParameter("url");
		req.getSession().invalidate();
		// 자동 로그인 쿠키 있을 경우
	    Cookie[] c = req.getCookies();
	    if (c != null) {
	      for (Cookie cf : c) {
	        if (cf.getName().equals("autoLogin")){
	        	System.out.println("autoLogin OFF");
	        	cf.setMaxAge(0);
	        	resp.addCookie(cf);
	        }
	      }
	    }
		resp.sendRedirect(url); // 원래 페이지로 이동
	}

}
