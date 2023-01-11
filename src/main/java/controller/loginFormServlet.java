package controller;

import Cryptoutils.Sha;
import dao.MemberSelectLoginDao;

import javax.servlet.annotation.WebServlet;
import javax.servlet.http.Cookie;
import javax.servlet.http.HttpServlet;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;
import java.io.IOException;
import java.io.PrintWriter;
import java.rmi.ServerException;
import java.rmi.server.ServerCloneException;
import java.security.NoSuchAlgorithmException;

@WebServlet("/loginFormServlet")
public class loginFormServlet extends HttpServlet {

    protected void doPost(HttpServletRequest request, HttpServletResponse response) throws ServerException, IOException{
        request.setCharacterEncoding("utf-8");
        response.setContentType("text/html; charset=UTF-8");

        Sha sha = new Sha();

        String memberId = request.getParameter("memberId");
        String password = request.getParameter("password");

        String NewPassword = sha.encode(password);


        String loginChk = request.getParameter("loginChk");

        if (loginChk != null) { // 체크한 경우
            Cookie c = new Cookie("id", memberId);
            c.setMaxAge(60 * 60);
            c.setPath("/");
            response.addCookie(c);
        }


        MemberSelectLoginDao dao = new MemberSelectLoginDao();

        String state = dao.login(memberId,NewPassword);


        if(state != null){ // 회원가입 성공
            request.getSession().setAttribute("memberId", memberId);
            PrintWriter out = response.getWriter();
            out.println("<script>alert('"+ request.getSession().getAttribute("memberId")+"으로 로그인 하셨습니다.')</script>");
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
