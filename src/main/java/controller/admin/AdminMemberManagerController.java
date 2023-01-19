package controller.admin;

import java.io.IOException;
import java.util.ArrayList;

import javax.servlet.RequestDispatcher;
import javax.servlet.ServletException;
import javax.servlet.annotation.WebServlet;
import javax.servlet.http.HttpServlet;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;

import dao.AdminLoginDao;
import dao.MyPageDao;
import dto.MemberDto;

@WebServlet({"/myPage/memberManagerPage",
	"/myPage/memberManagerPage/oneMemberBan.do",
	"/myPage/memberManagerPage/selectedMemberBan.do",
	"/myPage/memberManagerPage/allMemberBan.do",
	"/myPage/memberManagerPage/oneMemberUnBan.do",
	"/myPage/memberManagerPage/selectedMemberUnBan.do",
	"/myPage/memberManagerPage/allMemberUnBan.do",
	"/myPage/memberManagerPage/memberSearch.do",
	"/myPage/memberManagerPage/bannedMemberSearch.do"})
public class AdminMemberManagerController extends HttpServlet {
	//managerPage?myPageManagerCategory=2&memberManagerNo=1
	private static final long serialVersionUID = 1L;
	public AdminMemberManagerController() {
        super();
    }
	protected void doGet(HttpServletRequest request, HttpServletResponse response) throws ServletException, IOException {
		
		//관리자는 현재 활동중!
		AdminLoginDao setTime = new AdminLoginDao();
		setTime.setSession(request.getSession().getId(), (String)request.getSession().getAttribute("manager"));
		
		//개인삭제, 전체삭제 구현
		if(request.getSession().getAttribute("manager")!=null) {
			MyPageDao memberDao = new MyPageDao();
			if(request.getServletPath().equals("/myPage/memberManagerPage/oneMemberBan.do")) {//개인차단
				memberDao.deleteMemberByNo(Integer.parseInt((String)request.getParameter("no")));
				response.sendRedirect("../memberManagerPage?myPageManagerCategory=2&memberManager=0&memberManagerNo=1");
			}else if(request.getServletPath().equals("/myPage/memberManagerPage/selectedMemberBan.do")) {//선택차단
				String chkdID = request.getParameter("chkdID");
				memberDao.deleteSelMember(chkdID);
				response.sendRedirect("../memberManagerPage?myPageManagerCategory=2&memberManager=0&memberManagerNo=1");
			}else if(request.getServletPath().equals("/myPage/memberManagerPage/allMemberBan.do")) {//전체차단
				memberDao.deleteAllMember();
				response.sendRedirect("../memberManagerPage?myPageManagerCategory=2&memberManager=0&memberManagerNo=1");
			}else if(request.getServletPath().equals("/myPage/memberManagerPage/oneMemberUnBan.do")) {//개인복원
				memberDao.restoreMemberByNo(Integer.parseInt((String)request.getParameter("no")));
				response.sendRedirect("../memberManagerPage?myPageManagerCategory=2&memberManager=0&memberManagerNo=1&memberManagerSub=1");
			}else if(request.getServletPath().equals("/myPage/memberManagerPage/selectedMemberUnBan.do")) {//선택복원
				String chkdID = request.getParameter("chkdID");
				memberDao.restoreSelMember(chkdID);
				response.sendRedirect("../memberManagerPage?myPageManagerCategory=2&memberManager=0&memberManagerNo=1&memberManagerSub=1");
			}else if(request.getServletPath().equals("/myPage/memberManagerPage/allMemberUnBan.do")) {//전체복원
				memberDao.restoreAllMember();
				response.sendRedirect("../memberManagerPage?myPageManagerCategory=2&memberManager=0&memberManagerNo=1&memberManagerSub=1");
			}else {
				
				//기본경로인 경우처리
				response.setCharacterEncoding("utf-8");
				response.setContentType("text/html");
				String param = request.getParameter("myPageManagerCategory");
				if(param==null) {
					param = "2";
				}
				request.setAttribute("myPageManagerCategory",param);
				String param2 = request.getParameter("memberManagerNo");
				if(param2==null) {
					param2 = "1";
				}
				request.setAttribute("myPageManagerNo",param2);
				request.setAttribute("myPageManagerParam","2");

				//멤버목록 페이징
				if(request.getParameter("memberManagerNo")!=null) {
					System.out.println("dafdfasfdasfasdf");
					if(request.getParameter("select")!=null) {//멤버검색
						int adminNo= Integer.parseInt(request.getParameter("memberManagerNo"));
						ArrayList<MemberDto> memberArrayList = memberDao.searchMember((String)request.getParameter("select"),(String)request.getParameter("keyword"),adminNo);
						request.setAttribute("memberArrayList", memberArrayList);
					}else {
						int adminNo= Integer.parseInt(request.getParameter("memberManagerNo"));
						ArrayList<MemberDto> memberArrayList = memberDao.getMemberList(adminNo);
						request.setAttribute("memberArrayList", memberArrayList);
					}
				}
				//밴한멤버목록 페이징
				if((request.getParameter("memberManagerNo")!=null)&&(request.getParameter("memberManagerSub")!=null)) {
					if(request.getParameter("select")!=null) {//벤된 멤버검색
						int adminNo= Integer.parseInt(request.getParameter("memberManagerNo"));
						ArrayList<MemberDto> bannedMemberArrayList = memberDao.searchBannedMember((String)request.getParameter("select"),(String)request.getParameter("keyword"),adminNo);
						request.setAttribute("bannedMemberArrayList", bannedMemberArrayList);
					}else {
						int adminNo= Integer.parseInt(request.getParameter("memberManagerNo"));
						ArrayList<MemberDto> bannedMemberArrayList = memberDao.getBannedMemberList(adminNo);
						request.setAttribute("bannedMemberArrayList", bannedMemberArrayList);
					}
				}
				System.out.println("AdminMemberManagerController 입장");
				RequestDispatcher requestDispatcher = request.getRequestDispatcher("/myPage/myPage.jsp");
				requestDispatcher.forward(request, response);
			}
		}
	}
	@Override
	protected void doPost(HttpServletRequest request, HttpServletResponse response) throws ServletException, IOException {
		doGet(request,response);
	}
	
	
}
