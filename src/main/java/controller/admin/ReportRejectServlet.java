package controller.admin;

import java.io.IOException;

import javax.servlet.ServletException;
import javax.servlet.annotation.WebServlet;
import javax.servlet.http.HttpServlet;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;

import dao.ReportMemberDao;

@WebServlet("/ReportRejectServlet")
public class ReportRejectServlet extends HttpServlet {
	
	@Override
	protected void doPost(HttpServletRequest req, HttpServletResponse resp) throws ServletException, IOException {
		System.out.println("거절 확인");
		req.setCharacterEncoding("UTF-8");
		resp.setContentType("text.html; charset=utf8");
		
		ReportMemberDao dao = new ReportMemberDao();
		
		int reportNo = Integer.parseInt(req.getParameter("reportNo"));
		
		dao.rejectMemberReport(reportNo);
			
	}
	
	
}
