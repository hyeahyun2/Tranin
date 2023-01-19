package controller.notice;

import dao.MemebrPasswordSelectDao;
import dao.NoticeDeleteDao;

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

		int notice_no = Integer.parseInt(request.getParameter("notice_no"));

		NoticeDeleteDao dao = new NoticeDeleteDao();

		boolean state = dao.deleteNotice(notice_no);

		if(state){ // 성공
			PrintWriter out = response.getWriter();
			out.println("<script>alert('삭제되었습니다')</script>");
			out.println("<script>location.href='/UM/lostAndFound_process.jsp'</script>");
			out.flush();
			out.close();
		}
		else { //실패
			PrintWriter out = response.getWriter();
			out.println("<script>alert('삭제에실패되었습니다.')</script>");
			out.println("<script>location.href='/UM/lostAndFound.jsp'</script>");
			out.flush();
			out.close();
		}
		
	}
	
	
}
