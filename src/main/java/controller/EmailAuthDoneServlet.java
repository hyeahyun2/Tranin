package controller;

import javax.servlet.ServletException;
import javax.servlet.annotation.WebServlet;
import javax.servlet.http.HttpServlet;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;
import java.io.IOException;
import java.io.PrintWriter;

@WebServlet("/EmailDone")
public class EmailAuthDoneServlet extends HttpServlet {

    @Override
    protected void doPost(HttpServletRequest request, HttpServletResponse response) throws ServletException, IOException {
        String AuthenticationKey = (String)request.getSession().getAttribute("AuthenticationKey");
        String AuthCode = request.getParameter("Authcode");

//@ResponseBody = PrintWriter(JSP)
        if(AuthenticationKey.equals(AuthCode)){
            PrintWriter out = response.getWriter();
            out.println("1");
            out.flush();
            out.close();
            System.out.println("성공");
        } else{
            PrintWriter out = response.getWriter();
            out.println("0");
            out.flush();
            out.close();
            System.out.println("실패");
        }

    }
}
