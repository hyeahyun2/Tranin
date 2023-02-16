package controller.notice;

import dao.NoticeDao;

import java.io.IOException;
import java.io.PrintWriter;

import javax.servlet.ServletException;
import javax.servlet.annotation.WebServlet;
import javax.servlet.http.HttpServlet;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;

@WebServlet("/deleteNoticeServlet")
public class deleteNoticeServlet extends HttpServlet {

	@Override
	protected void doGet(HttpServletRequest request, HttpServletResponse response) throws ServletException, IOException {
		request.setCharacterEncoding("utf-8");
		response.setContentType("text/html; charset=UTF-8");
		System.out.println("아아");
		int noticeNo = Integer.parseInt(request.getParameter("noticeNo"));

		NoticeDao dao = new NoticeDao();

		boolean state = dao.deleteNotice(noticeNo);

		if(state){ // 성공
			
			response.sendRedirect("/notice/notice.jsp");
			// PrintWriter out = response.getWriter();
			// out.println("<script>alert('삭제되었습니다')</script>");
			// out.flush();
			// out.close();

		}
		else { //실패
			PrintWriter out = response.getWriter();
			out.println("<script>alert('삭제에 실패되었습니다.')</script>");
			out.flush();
			out.close();
		}
		
	}
	
	
}
