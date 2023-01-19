package controller.admin;

import javax.servlet.annotation.WebServlet;
import javax.servlet.http.HttpServlet;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;
import javax.servlet.http.HttpSession;

import Cryptoutils.Sha;
import dao.AdminLoginDao;

import java.io.IOException;
import java.io.PrintWriter;

@WebServlet("/manageLogin")
public class AdminLoginFormServlet extends HttpServlet {

	private static final long serialVersionUID = 1L;

	protected void doPost(HttpServletRequest request, HttpServletResponse response) throws IOException{
        request.setCharacterEncoding("utf-8");
        response.setContentType("text/html; charset=UTF-8");
        Sha sha = new Sha();

        String memberId = request.getParameter("memberId");
        String password = request.getParameter("password");
        String NewPassword = sha.encode(password);

        System.out.println(memberId);
        AdminLoginDao dao = new AdminLoginDao();
        System.out.println(password);
        System.out.println(NewPassword);
        String state = dao.login(memberId,NewPassword);
        
        if(dao.getCurrentStatus(memberId)==1) {
if(			dao.alreadyThirtyMinute(memberId)) {
        		
        	}else {
        		state=null;
        	}
        }else {
        	
        }
        	
        
        
        if(state != null){ // 로그인 성공
            request.getSession().setAttribute("manager", state);
            dao.setCurrentStatusTrue(memberId);
            dao.setSession(request.getSession().getId(), memberId);
            PrintWriter out = response.getWriter();
            out.println("<script>alert('"+ request.getSession().getAttribute("manager")+"님 환영합니다!')</script>");
            out.println("<script>location.href='index.jsp'</script>");
            out.flush();
            out.close();
        }
        else { // 로그인 실패
            PrintWriter out = response.getWriter();
            out.println("<script>alert('로그인에 실패 하였습니다. 다시 시도해주세요.')</script>");
            out.println("<script>location.href='/manage/login.jsp'</script>");
            out.flush();
            out.close();
        }
    }

}
