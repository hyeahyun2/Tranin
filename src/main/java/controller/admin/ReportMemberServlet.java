package controller.admin;

import java.io.IOException;

import javax.servlet.ServletException;
import javax.servlet.annotation.WebServlet;
import javax.servlet.http.HttpServlet;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;

import dao.ReportMemberDao;
import dto.ReportMemberDto;

@WebServlet("/ReportMemberServlet")
public class ReportMemberServlet extends HttpServlet{

	@Override
	protected void doPost(HttpServletRequest req, HttpServletResponse resp) throws ServletException, IOException {
		// TODO Auto-generated method stub
		if (req.getSession().getAttribute("memberId") != null) {
			req.setCharacterEncoding("UTF-8");
			resp.setContentType("text/html; charset=utf8");
			
			ReportMemberDto dto = new ReportMemberDto();
			ReportMemberDao dao = new ReportMemberDao();
			
			String memberId = (String)req.getSession().getAttribute("memberId");
			String reportId = dao.getReportMemberIdByNo(Integer.parseInt(req.getParameter("writerNo")));
			String reportUrl = "/marketPostInfo?no=" + req.getParameter("postNo");
			
			boolean state = dao.insertReportPost(memberId, reportId, reportUrl);
			
			if(state) {
				resp.sendRedirect("/market/market.jsp");
			}
			else {
				System.out.println("신고하는 중 오류가 발생하였습니다.");
			}
		}
	}
	
}
