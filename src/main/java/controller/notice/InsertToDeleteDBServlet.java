package controller.notice;


import dao.NoticeDao;

import javax.servlet.ServletException;
import javax.servlet.annotation.WebServlet;
import javax.servlet.http.HttpServlet;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;
import java.io.IOException;
import java.io.PrintWriter;

@WebServlet("/insertTodeleteDB")
public class InsertToDeleteDBServlet extends HttpServlet {

    @Override
    protected void doPost(HttpServletRequest request, HttpServletResponse response) throws ServletException, IOException {
        request.setCharacterEncoding("utf-8");
        response.setContentType("text/html; charset=UTF-8");
        
        int noticeNo = Integer.parseInt(request.getParameter("noticeNo"));
        String title = request.getParameter("title");
        String content = request.getParameter("content");


        NoticeDao dao = new NoticeDao();
        dao.insertToDB(noticeNo, title, content);
    }
}

