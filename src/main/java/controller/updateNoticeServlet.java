package controller;

import java.io.IOException;
import java.io.UnsupportedEncodingException;

import javax.servlet.ServletException;
import javax.servlet.annotation.WebServlet;
import javax.servlet.http.HttpServlet;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;

import dao.NoticeUpdateDao;

@WebServlet("/updateNoticeServlet")
public class updateNoticeServlet extends HttpServlet {
	
	protected void doPost(HttpServletRequest request, HttpServletResponse response) throws ServletException, IOException {
		request.setCharacterEncoding("utf-8");
		response.setContentType("text/html; charset=UTF=8");
		
		String title = request.getParameter("title");
		String content = request.getParameter("content");
		int noticeNo = Integer.parseInt(request.getParameter("noticeNo"));
		
		NoticeUpdateDao dao = new NoticeUpdateDao();
		
		boolean state = dao.updateContent(title, content, noticeNo);
		
		if(state) {
			response.sendRedirect("/notice/notice.jsp");
		}
		else {
			System.out.println("글 수정을 실패하였습니다.");
		}
    }

}
