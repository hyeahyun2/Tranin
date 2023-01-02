package controller;

import dao.MemberSelectLoginDao;

import javax.servlet.annotation.WebServlet;
import javax.servlet.http.HttpServlet;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;
import java.io.IOException;
import java.rmi.ServerException;
import java.rmi.server.ServerCloneException;

@WebServlet("/loginFormServlet")
public class loginFormServlet extends HttpServlet {

    protected void doPost(HttpServletRequest request, HttpServletResponse response) throws ServerException, IOException{
        request.setCharacterEncoding("utf-8");
        response.setContentType("text/html; charset=UTF-8");

        String memberId = request.getParameter("memberId");
        String password = request.getParameter("password");



        MemberSelectLoginDao dao = new MemberSelectLoginDao();

        String state = dao.login(memberId,password);


        if(state != null){ // 회원가입 성공
            request.getSession().setAttribute("nickname", state);
            response.sendRedirect("index.jsp");
        }
        else { // 회원가입 실패
            response.sendRedirect("register.jsp");
        }
    }

}
