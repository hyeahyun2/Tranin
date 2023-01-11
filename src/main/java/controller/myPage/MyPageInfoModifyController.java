package controller.myPage;

import java.io.IOException;
import java.io.PrintWriter;

import javax.servlet.ServletException;
import javax.servlet.annotation.WebServlet;
import javax.servlet.http.HttpServlet;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;

import dao.MyPageDao;
import dto.ManagerDto;
import dto.MemberDto;

@WebServlet({"/myPage/myPageMyInfoModify","/myPage/myPageManagerInfoModify"})
public class MyPageInfoModifyController extends HttpServlet {
	private static final long serialVersionUID = 1L;
	public MyPageInfoModifyController() {
        super();
        System.out.println("MyPageInfoModifyController 입장");
    }
	protected void doPost(HttpServletRequest request, HttpServletResponse response) throws ServletException, IOException {
		response.setCharacterEncoding("utf-8");
		response.setContentType("text/html");
		request.setCharacterEncoding("utf-8");
		
		System.out.println(request.getServletPath());
		if(request.getServletPath().equals("/myPage/myPageMyInfoModify")) {
			String myPageMyInfoId = request.getParameter("myPageMyInfoId");
			String myPageMyInfoPassword = request.getParameter("myPageMyInfoPassword");
			String myPageMyInfoNickName = (String)request.getSession().getAttribute("memberId");
			String myPageMyAddress = request.getParameter("myPageMyAddress");
			String myPageMyZipCode = request.getParameter("myPageMyZipCode");
			System.out.println("별명:"+myPageMyInfoNickName);
			System.out.println("비번:"+myPageMyInfoPassword);
			System.out.println("비번확인:"+request.getParameter("myPageMyInfoPasswordConfirm"));
			//패스워드확인 일치안하면 튕구기
			if(myPageMyInfoPassword.equals(request.getParameter("myPageMyInfoPasswordConfirm"))) {
				//데이터베이스에 정보 수정하기
				MyPageDao dao = new MyPageDao();
				dao.modifyMyPageInfo(myPageMyInfoId,myPageMyInfoPassword,myPageMyInfoNickName,myPageMyAddress,myPageMyZipCode);
				
				MemberDto member = dao.getMemberById(myPageMyInfoNickName);
				
				PrintWriter out = response.getWriter();
				out.println("<script>alert('개인정보 수정 성공!'); location.href='myPage?myPageCategory=0';</script>");
				out.flush();
				out.close();
			}else {
				PrintWriter out = response.getWriter();
				out.println("<script>alert('패스워드가 다릅니다. 다시 입력해주세요'); location.href='myPage?myPageCategory=0';</script>");
				out.flush();
				out.close();
			}
		}else {
			String myPageMyInfoId = (String)request.getSession().getAttribute("manager");
			String myPageMyInfoPassword = request.getParameter("myPageMyInfoPassword");
			String myPageMyInfoName = request.getParameter("myPageMyInfoName");
			System.out.println("별명:"+myPageMyInfoName);
			System.out.println("비번:"+myPageMyInfoPassword);
			System.out.println("비번확인:"+request.getParameter("myPageMyInfoPasswordConfirm"));
			//패스워드확인 일치안하면 튕구기
			if(myPageMyInfoPassword.equals(request.getParameter("myPageMyInfoPasswordConfirm"))) {
				//데이터베이스에 정보 수정하기
				MyPageDao dao = new MyPageDao();
				dao.modifyManagerInfo(myPageMyInfoId,myPageMyInfoPassword,myPageMyInfoName);
				
				ManagerDto member = dao.getManagerById(myPageMyInfoId);
				
				PrintWriter out = response.getWriter();
				out.println("<script>alert('개인정보 수정 성공!'); location.href='myPage?myPageCategory=0';</script>");
				out.flush();
				out.close();
			}else {
				PrintWriter out = response.getWriter();
				out.println("<script>alert('패스워드가 다릅니다. 다시 입력해주세요'); location.href='myPage?myPageCategory=0';</script>");
				out.flush();
				out.close();
			}
		}
<<<<<<< HEAD
	}
=======

    }
>>>>>>> branch 'master' of https://github.com/hyeahyun2/Tranin.git
}
