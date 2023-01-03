package controller;

import dao.MemberPasswordUpdateDao;

import javax.servlet.ServletException;
import javax.servlet.annotation.WebServlet;
import javax.servlet.http.HttpServlet;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;
import java.io.IOException;

@WebServlet("/lostAndFoundForm_process")
public class lostAndFoundForm_process extends HttpServlet {

    protected void doPost(HttpServletRequest request, HttpServletResponse response) throws ServletException, IOException{
        request.setCharacterEncoding("utf-8");
        response.setContentType("text/html; charset=UTF-8");

        String pw = request.getParameter("password");
        String nickname = request.getParameter("nickname");

        MemberPasswordUpdateDao dao = new MemberPasswordUpdateDao();


        String state = dao.finding(pw,nickname);

        if(state != null){ // 성공
            response.sendRedirect("UM/pwEditDone.jsp");
        }
        else { //실패
            response.sendRedirect("index.jsp");
        }
    }
}
