package controller;

import dao.MemberSelectLoginDao;

import javax.servlet.annotation.WebServlet;
import javax.servlet.http.HttpServlet;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;
import java.io.IOException;
import java.io.PrintWriter;
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
            PrintWriter out = response.getWriter();
            out.println("<script>alert('"+ request.getSession().getAttribute("nickname")+"님 환영합니다!')</script>");
            out.println("<script>location.href='index.jsp'</script>");
            out.flush();
            out.close();
        }
        else { // 회원가입 실패
            PrintWriter out = response.getWriter();
            out.println("<script>alert('로그인에 실패 하였습니다. 다시 시도해주세요.')</script>");
            out.println("<script>location.href='/UM/login.jsp'</script>");
            out.flush();
            out.close();
        }
    }

}
