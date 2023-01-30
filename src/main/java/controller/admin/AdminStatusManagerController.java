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
import dao.AdminMarketDao;
import dao.MarketDao;
import dao.MyPageDao;
import dto.MarketDto;
import dto.MarketResponseDto;
import dto.MemberDto;

@WebServlet("/myPage/statementManagerPage")
public class AdminStatusManagerController extends HttpServlet {
	//statusManagerPage?myPageManagerCategory=2&statusManagerNo=1
	private static final long serialVersionUID = 1L;
	public AdminStatusManagerController() {
        super();
    }
	protected void doGet(HttpServletRequest request, HttpServletResponse response) throws ServletException, IOException {
		//관리자는 현재 활동중!
		AdminLoginDao setTime = new AdminLoginDao();
		setTime.setSession(request.getSession().getId(), (String)request.getSession().getAttribute("manager"));
		
		//기본경로인 경우처리
		response.setCharacterEncoding("utf-8");
		response.setContentType("text/html");
		String param = request.getParameter("myPageManagerCategory");
		if(param==null) {
			param = "2";
		}
		request.setAttribute("myPageManagerCategory",param);
		String param2 = request.getParameter("statusManagerNo");
		if(param2==null) {
			param2 = "1";
		}
		request.setAttribute("myPageManagerNo",param2);
		request.setAttribute("myPageManagerParam","2");
		
		//통계 셋팅
		MyPageDao dao = new MyPageDao();
		
		String dayRegMemberCount = dao.getDayRegMemberCount();
		request.setAttribute("dayRegMemberCount", dayRegMemberCount);
		String weekRegMemberCount = dao.getWeekRegMemberCount();
		request.setAttribute("weekRegMemberCount", weekRegMemberCount);
		String monthRegMemberCount = dao.getMonthRegMemberCount();
		request.setAttribute("monthRegMemberCount",monthRegMemberCount);
		String allRegMemberCount = dao.getAllRegMemberCount();
		request.setAttribute("allRegMemberCount",allRegMemberCount);
		
		String dayPopPost = dao.getDayPopPost();
		request.setAttribute("dayPopPost",dayPopPost);
		String weekPopPost = dao.getWeekPopPost();
		request.setAttribute("weekPopPost",weekPopPost);
		String monthPopPost = dao.getMonthPopPost();
		request.setAttribute("monthPopPost",monthPopPost);
		String allPopPost = dao.getAllPopPost();
		request.setAttribute("allPopPost",allPopPost);
		
		String dayMostUser = dao.getDayMostUser();
		request.setAttribute("dayMostUser",dayMostUser);
		String weekMostUser = dao.getWeekMostUser();
		request.setAttribute("weekMostUser",weekMostUser);
		String monthMostUser = dao.getMonthMostUser();
		request.setAttribute("monthMostUser",monthMostUser);
		String allMostUser = dao.getAllMostUser();
		request.setAttribute("allMostUser",allMostUser);
		String dayMostUserCount = dao.getDayMostUserCount();
		request.setAttribute("dayMostUserCount",dayMostUserCount);
		String weekMostUserCount = dao.getWeekMostUserCount();
		request.setAttribute("weekMostUserCount",weekMostUserCount);
		String monthMostUserCount = dao.getMonthMostUserCount();
		request.setAttribute("monthMostUserCount",monthMostUserCount);
		String allMostUserCount = dao.getAllMostUserCount();
		request.setAttribute("allMostUserCount",allMostUserCount);
		
		String dayPostCount = dao.getDayPostCount();
		request.setAttribute("dayPostCount",dayPostCount);
		String weekPostCount = dao.getWeekPostCount();
		request.setAttribute("weekPostCount",weekPostCount);
		String monthPostCount = dao.getMonthPostCount();
		request.setAttribute("monthPostCount",monthPostCount);
		String allPostCount = dao.getAllPostCount();
		request.setAttribute("allPostCount",allPostCount);
		
		String dayBanPostCount = dao.getDayBanPostCount();
		request.setAttribute("dayBanPostCount",dayBanPostCount);
		String weekBanPostCount = dao.getWeekBanPostCount();
		request.setAttribute("weekBanPostCount",weekBanPostCount);
		String monthBanPostCount = dao.getMonthBanPostCount();
		request.setAttribute("monthBanPostCount",monthBanPostCount);
		String allBanPostCount = dao.getAllBanPostCount();
		request.setAttribute("allBanPostCount",allBanPostCount);
		
		String dayBanMemberCount = dao.getDayBanMemberCount();
		request.setAttribute("dayBanMemberCount",dayBanMemberCount);
		String weekBanMemberCount = dao.getWeekBanMemberCount();
		request.setAttribute("weekBanMemberCount",weekBanMemberCount);
		String monthBanMemberCount = dao.getMonthBanMemberCount();
		request.setAttribute("monthBanMemberCount",monthBanMemberCount);
		String allBanMemberCount = dao.getAllBanMemberCount();
		request.setAttribute("allBanMemberCount",allBanMemberCount);
		/*
		String dayMostSearch = dao.getDayMostSearch();
		request.getAttribute("dayMostSearch");
		String weekMostSearch = dao.getWeekMostSearch();
		request.getAttribute("weekMostSearch");
		String monthMostSearch = dao.getMonthMostSearch();
		request.getAttribute("monthMostSearch");
		String allMostSearch = dao.getAllMostSearch();
		request.getAttribute("allMostSearch");
		String dayMostSearchCount = dao.getDayMostSearchCount();
		request.getAttribute("dayMostSearchCount");
		String weekMostSearchCount = dao.getWeekMostSearchCount();
		request.getAttribute("weekMostSearchCount");
		String monthMostSearchCount = dao.getMonthMostSearchCount();
		request.getAttribute("monthMostSearchCount");
		String allMostSearchCount = dao.getAllMostSearchCount();
		request.getAttribute("allMostSearchCount");
		*/
		RequestDispatcher requestDispatcher = request.getRequestDispatcher("/myPage/myPage.jsp");
		requestDispatcher.forward(request, response);
	}
	@Override
	protected void doPost(HttpServletRequest request, HttpServletResponse response) throws ServletException, IOException {
		doGet(request,response);
	}
	
}
