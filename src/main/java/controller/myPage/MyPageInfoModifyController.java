package controller.myPage;

import java.io.IOException;
import java.io.PrintWriter;

import javax.servlet.ServletException;
import javax.servlet.annotation.WebServlet;
import javax.servlet.http.HttpServlet;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;

import controller.member.PasswdEncry;
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
		// 객체 생성
   	    PasswdEncry pwEn = new PasswdEncry();
   	    // 암호화
   	    //String res = pwEn.getEncry(pw, "testSalt");
		MyPageDao dao = new MyPageDao();
		System.out.println(request.getServletPath());
		if(request.getServletPath().equals("/myPage/myPageMyInfoModify")) {
			MemberDto memberPwUs = dao.getMemberById((String)request.getSession().getAttribute("memberId"));
			String myPageMyInfoId = (String)request.getSession().getAttribute("memberId");
			String myPageMyInfoPassword = memberPwUs.getPw();
			String myPageMyInfoNickName = request.getParameter("myPageMyInfoNickName");
			String myPageMyAddress = request.getParameter("myPageMyAddress");
			String myPageMyZipCode = request.getParameter("myPageMyZipCode");
			System.out.println("별명:"+myPageMyInfoNickName);
			System.out.println("비번:"+myPageMyInfoPassword);
			System.out.println("비번확인:"+request.getParameter("myPageMyInfoPasswordConfirm"));
			//패스워드확인 일치안하면 튕구기
			if(myPageMyInfoPassword.equals(pwEn.getEncry((String)request.getParameter("myPageMyInfoPasswordConfirm"),"testSalt"))) {
				//데이터베이스에 정보 수정하기
				
				dao.modifyMyPageInfo(myPageMyInfoId,request.getParameter("myPageMyInfoPasswordConfirm"),myPageMyInfoNickName,myPageMyAddress,myPageMyZipCode);
				
				MemberDto member = dao.getMemberById(myPageMyInfoId);
				
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
			ManagerDto managerPwUs = dao.getManagerById((String)request.getSession().getAttribute("manager"));
			
			String myPageMyInfoId = (String)request.getSession().getAttribute("manager");
			
			String myPageMyInfoPassword = managerPwUs.getPw();
			String myPageMyInfoName = request.getParameter("myPageMyInfoName");
			System.out.println("별명:"+myPageMyInfoName);
			System.out.println("비번:"+myPageMyInfoPassword);
			System.out.println("비번확인:"+request.getParameter("myPageMyInfoPasswordConfirm"));
			//패스워드확인 일치안하면 튕구기
			if(myPageMyInfoPassword.equals(pwEn.getEncry((String)request.getParameter("myPageMyInfoPasswordConfirm"),"testSalt"))) {
				//데이터베이스에 정보 수정하기
				dao.modifyManagerInfo(myPageMyInfoId,request.getParameter("myPageMyInfoPasswordConfirm"),myPageMyInfoName);
				
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
	}
}
