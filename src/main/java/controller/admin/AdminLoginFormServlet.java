package controller.admin;

import javax.servlet.annotation.WebServlet;
import javax.servlet.http.HttpServlet;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;

import dao.AdminLoginDao;

import java.io.IOException;
import java.io.PrintWriter;

@WebServlet("/manageLogin")
public class AdminLoginFormServlet extends HttpServlet {

	private static final long serialVersionUID = 1L;

	protected void doPost(HttpServletRequest request, HttpServletResponse response) throws IOException{
        request.setCharacterEncoding("utf-8");
        response.setContentType("text/html; charset=UTF-8");

        String memberId = request.getParameter("memberId");
        String password = request.getParameter("password");

        AdminLoginDao dao = new AdminLoginDao();

        String state = dao.login(memberId,password);

        if(state != null){ // 회원가입 성공
            request.getSession().setAttribute("manager", state);
            PrintWriter out = response.getWriter();
            out.println("<script>alert('"+ request.getSession().getAttribute("manager")+"님 환영합니다!')</script>");
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