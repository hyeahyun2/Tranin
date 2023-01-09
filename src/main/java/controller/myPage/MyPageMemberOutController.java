package controller.myPage;

import java.io.IOException;
import java.io.PrintWriter;

import javax.servlet.ServletException;
import javax.servlet.annotation.WebServlet;
import javax.servlet.http.HttpServlet;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;

import dao.MyPageDao;
import dto.MemberDto;

@WebServlet("/myPage/myPageMemberOut")
public class MyPageMemberOutController extends HttpServlet {
	private static final long serialVersionUID = 1L;
	public MyPageMemberOutController() {
        super();
        System.out.println("MyPageMemberOutController 입장");
    }
	protected void doPost(HttpServletRequest request, HttpServletResponse response) throws ServletException, IOException {
		response.setCharacterEncoding("utf-8");
		response.setContentType("text/html");
		request.setCharacterEncoding("utf-8");
        MyPageDao dao = new MyPageDao();
        MemberDto member = dao.getMemberByNickname((String)request.getSession().getAttribute("nickname"));
        System.out.println("입력한 기존 비번:"+request.getParameter("pw"));
        System.out.println("현재 데이터베이스의 비번:"+member.getPw());
        if(member.getPw().equals(request.getParameter("pw"))) {
        	//입력이 올바른 경우, 회원탈퇴
        	dao.deleteMember(member.getNickName());
        	request.getSession().removeAttribute("nickname");
			PrintWriter out = response.getWriter();
			out.println("<script>alert('탈퇴 성공!'); location.href='../index.jsp';</script>");
			out.flush();
			out.close();
        }else {
        	//비번을 틀린경우, 튕구기
        	PrintWriter out = response.getWriter();
        	out.println("<script>alert('패스워드가 다릅니다. 다시 입력해주세요'); location.href='myPage?myPageCategory=3';</script>");
			out.flush();
			out.close();
        }
	}
}
