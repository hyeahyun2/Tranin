package controller.admin;

import java.io.IOException;

import javax.servlet.ServletException;
import javax.servlet.annotation.WebServlet;
import javax.servlet.http.HttpServlet;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;

import dao.MyPageDao;
import dao.ReportMemberDao;

@WebServlet("/ReportAcceptServlet")
public class ReportAcceptServlet extends HttpServlet {

	@Override
	protected void doPost(HttpServletRequest req, HttpServletResponse resp) throws ServletException, IOException {
		req.setCharacterEncoding("UTF-8");
		resp.setContentType("text.html; charset=utf8");
		
		ReportMemberDao rmd = new ReportMemberDao();
		MyPageDao mpd = new MyPageDao();
		
		int reportNo = Integer.parseInt(req.getParameter("reportNo"));
		System.out.println(reportNo);
		System.out.println("있나 없나");
		int reportMemberNo = rmd.getReportMemberNoById(req.getParameter("reportId"));
		
		rmd.acceptMemberReport(reportNo);
		mpd.deleteMemberByNo(reportMemberNo);

	}
	
}
