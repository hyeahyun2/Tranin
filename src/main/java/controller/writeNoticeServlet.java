package controller;

import java.io.IOException;
import java.io.UnsupportedEncodingException;
import java.util.Date;

import javax.servlet.ServletException;
import javax.servlet.annotation.WebServlet;
import javax.servlet.http.HttpServlet;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;

import dao.NoticeInsertDao;

@WebServlet("/writeNoticeServlet")
public class writeNoticeServlet extends HttpServlet {
	
	protected void doPost(HttpServletRequest request, HttpServletResponse response) throws ServletException, IOException {
		request.setCharacterEncoding("utf-8");
		response.setContentType("text/html; charset=UTF-8");
		
		String title = request.getParameter("title");
		String content = request.getParameter("content");

		
		NoticeInsertDao nid = new NoticeInsertDao();
		
		boolean state = nid.registContent(title, content);
		
		if(state) {
			response.sendRedirect("/notice/notice.jsp");
		}
		else {
			System.out.println("글 등록에 실패하였습니다.");
			response.sendRedirect("index.jsp");
			
		}
	}
}
