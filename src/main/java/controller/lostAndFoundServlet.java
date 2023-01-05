package controller;

import dao.MemberInsertDao;
import dao.MemebrPasswordSelectDao;

import javax.servlet.ServletException;
import javax.servlet.annotation.WebServlet;
import javax.servlet.http.HttpServlet;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;
import java.io.IOException;
import java.io.PrintWriter;

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
            request.getSession().setAttribute("passChecked", nickName);
            PrintWriter out = response.getWriter();
            out.println("<script>alert('"+nickName+"님 새로운 비밀번호를 설정해주세요.')</script>");
            out.println("<script>location.href='/UM/lostAndFound_process.jsp'</script>");
            out.flush();
            out.close();
        }
        else { //실패
            PrintWriter out = response.getWriter();
            out.println("<script>alert('이메일/별명을 다시 확인하여 주십시요.')</script>");
            out.println("<script>location.href='/UM/lostAndFound.jsp'</script>");
            out.flush();
            out.close();
        }

    }
}
