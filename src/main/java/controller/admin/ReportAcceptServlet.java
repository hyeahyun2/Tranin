package controller.admin;

import java.io.IOException;

import javax.servlet.ServletException;
import javax.servlet.annotation.WebServlet;
import javax.servlet.http.HttpServlet;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;

import dao.ReportAcceptDao;

@WebServlet("/ReportAcceptServlet")
public class ReportAcceptServlet extends HttpServlet {

	@Override
	protected void doPost(HttpServletRequest req, HttpServletResponse resp) throws ServletException, IOException {
		req.setCharacterEncoding("UTF-8");
		resp.setContentType("text.html; charset=utf8");
		
		int reportNo = Integer.parseInt(req.getParameter("reportNo"));
		String userId = req.getParameter("userId");
		ReportAcceptDao dao = new ReportAcceptDao();
		
	}
	
}
