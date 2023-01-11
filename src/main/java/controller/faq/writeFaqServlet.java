package controller.faq;

import java.io.IOException;

import javax.servlet.ServletException;
import javax.servlet.http.HttpServlet;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;

import dao.FaqInsertDao;
import dao.NoticeInsertDao;

public class writeFaqServlet extends HttpServlet {
	
	protected void doPost(HttpServletRequest request, HttpServletResponse response) throws ServletException, IOException {
		request.setCharacterEncoding("utf-8");
		response.setContentType("text/html; charset=UTF-8");
		
		String title = request.getParameter("title");
		String content = request.getParameter("content");

		
		FaqInsertDao nid = new FaqInsertDao();
		
		boolean state = nid.registFaqContent(title, content);
		
		if(state) {
			response.sendRedirect("/faq/faq.jsp");
		}
		else {
			System.out.println("글 등록에 실패하였습니다.");
			response.sendRedirect("index.jsp");
			
		}
	}
	
}
