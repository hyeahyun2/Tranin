package controller.admin;

import javax.servlet.annotation.WebServlet;
import javax.servlet.http.HttpServlet;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;
import javax.servlet.http.HttpSession;

import controller.member.PasswdEncry;
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
        
        // 객체 생성
   	    PasswdEncry pwEn = new PasswdEncry();
   	    // 암호화
   	    //String res = pwEn.getEncry(pw, "testSalt");
        
        String NewPassword = pwEn.getEncry(password, "testSalt");
        System.out.println(memberId);
        AdminLoginDao dao = new AdminLoginDao();
        System.out.println(password);
        System.out.println(NewPassword);
        String state = dao.login(memberId,NewPassword);
        //관리자 로그인 중이지 않은경우(필드값이 0인경우) 관리자 페이지 전반에 대한 접근 거부 해야함
        /* 관리자 중복 로그인 방지
         * 1. 1차로 status 필드를 이용한 1과 0의 값으로 접속여부 확인(로그인시1, 로그아웃시0으로)
         * 2. 로그인 후 별다른 행동없이 30분이 지난 계정은 로그아웃 한것으로 처리(필드값을 0으로)
         * 3. 동일 아이피에서 방금 로그아웃없이 종료한 관리자인 경우는 아이피를 필드에 저장해놓았다가 일치하면 로그인 허용
         * 4. 그외의 경우(이전 접속자와 동일아이피가 아니면서 이전 접속자가 별다른 행동없이 30분이 지나지 않은경우)는 별수없음 
         */
        
        if(dao.getCurrentStatus(memberId)==1) {
        	if(dao.alreadyThirtyMinute(memberId)) {
        		dao.setCurrentStatusFalse(memberId);
        		dao.setCurrentIP((String)request.getRemoteAddr(),memberId);
        	}else {
        		if(dao.getCurrentIP(memberId).equals((String)request.getRemoteAddr())) {
        			//go on
        		}else {
        			state=null;
        		}
        	}
        }else {
        	//go on
        }
        	
        if(state != null){ // 로그인 성공
            request.getSession().setAttribute("manager", state);
            dao.setCurrentStatusTrue(memberId);
            dao.setCurrentIP((String)request.getRemoteAddr(),memberId);
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
