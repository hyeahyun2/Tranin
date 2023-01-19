package controller.notice;

import Cryptoutils.Sha;
import dao.MemberInsertDao;
import dao.NoticeDeleteinsertDB;

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

        String title = request.getParameter("title");
        String content = request.getParameter("content");


        NoticeDeleteinsertDB dao = new NoticeDeleteinsertDB();
        dao.insertToDB(title, content);
//
//        if(state){ // 회원가입 성공
//            PrintWriter out = response.getWriter();
//            out.println("<script>alert('"+ request.getSession().getAttribute("nickname")+"님 환영합니다!')</script>");
//            out.println("<script>location.href='index.jsp'</script>");
//            out.flush();
//            out.close();
//        }
//        else { // 회원가입 실패
//            PrintWriter out = response.getWriter();
//            out.println("<script>alert('회원가입에 실패 하였습니다. 다시 시도해주세요.')</script>");
//            out.println("<script>location.href='/UM/register.jsp'</script>");
//            out.flush();
//            out.close();
//        }
    }
}

