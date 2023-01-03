package controller;

import dao.MemberInsertDao;
import dao.MemebrPasswordSelectDao;

import javax.servlet.ServletException;
import javax.servlet.annotation.WebServlet;
import javax.servlet.http.HttpServlet;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;
import java.io.IOException;

@WebServlet("/lostAndFoundForm")
public class lostAndFoundServlet extends HttpServlet {

    protected void doPost(HttpServletRequest request, HttpServletResponse response) throws ServletException, IOException{
        request.setCharacterEncoding("utf-8");
        response.setContentType("text/html; charset=UTF-8");

        String memberId = request.getParameter("memberId");
        String nickName = request.getParameter("nickName");

        MemebrPasswordSelectDao dao = new MemebrPasswordSelectDao();

        String state = dao.finding(memberId,nickName);

        if(state != null){ // 성공
            request.getSession().setAttribute("passChecked", state);
            response.sendRedirect("UM/lostAndFound_process.jsp");
        }
        else { //실패
            response.sendRedirect("index.jsp");
        }

    }
}
