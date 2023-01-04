package controller;

import dao.MemberPasswordUpdateDao;

import javax.servlet.ServletException;
import javax.servlet.annotation.WebServlet;
import javax.servlet.http.HttpServlet;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;
import java.io.IOException;
import java.io.PrintWriter;

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

            PrintWriter out = response.getWriter();
            out.println("<script>alert('비밀번호가 변경되었습니다.')</script>");
            out.println("<script>location.href='/UM/pwEditDone.jsp'</script>");
            out.flush();
            out.close();
        }
        else { //실패
            PrintWriter out = response.getWriter();
            out.println("<script>alert('비밀번호 변경에 실패하였습니다. 다시 시도해주세요')</script>");
            out.println("<script>location.href='/UM/lostAndFound_process.jsp'</script>");
            out.flush();
            out.close();
        }
    }
}
