package controller.admin;

import java.io.IOException;
import java.util.ArrayList;

import javax.servlet.RequestDispatcher;
import javax.servlet.ServletException;
import javax.servlet.annotation.WebServlet;
import javax.servlet.http.HttpServlet;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;

import dao.MyPageDao;
import dto.MemberDto;

@WebServlet("/myPage/managerPage")
public class AdminMemberManagerController extends HttpServlet {
	//managerPage?myPageManagerCategory=2&memberManagerNo=1
	private static final long serialVersionUID = 1L;
	public AdminMemberManagerController() {
        super();
    }
	protected void doGet(HttpServletRequest request, HttpServletResponse response) throws ServletException, IOException {
		if(request.getSession().getAttribute("manager")!=null) {
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
				int adminNo= Integer.parseInt(request.getParameter("memberManagerNo"));
				MyPageDao memberDao = new MyPageDao();
				ArrayList<MemberDto> memberArrayList = memberDao.getMemberList(adminNo);
				request.setAttribute("memberArrayList", memberArrayList);
			}
			
			
			System.out.println("AdminMemberManagerController 입장");
			RequestDispatcher requestDispatcher = request.getRequestDispatcher("/myPage/myPage.jsp");
			requestDispatcher.forward(request, response);
		}
		
	}
	
	
}
