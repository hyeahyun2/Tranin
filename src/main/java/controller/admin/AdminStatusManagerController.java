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
		setAttributeSupport(request,"dayRegMemberCount", dayRegMemberCount);
		String weekRegMemberCount = dao.getWeekRegMemberCount();
		setAttributeSupport(request,"weekRegMemberCount", weekRegMemberCount);
		String monthRegMemberCount = dao.getMonthRegMemberCount();
		setAttributeSupport(request,"monthRegMemberCount",monthRegMemberCount);
		String allRegMemberCount = dao.getAllRegMemberCount();
		setAttributeSupport(request,"allRegMemberCount",allRegMemberCount);
		
		String[] dayPopPost = dao.getDayPopPost();
		setAttributeSupport(request,"dayPopPostNo",dayPopPost[0]);
		setAttributeSupport(request,"dayPopPost",dayPopPost[1]);
		String[] weekPopPost = dao.getWeekPopPost();
		setAttributeSupport(request,"weekPopPostNo",weekPopPost[0]);
		setAttributeSupport(request,"weekPopPost",weekPopPost[1]);
		String[] monthPopPost = dao.getMonthPopPost();
		setAttributeSupport(request,"monthPopPostNo",monthPopPost[0]);
		setAttributeSupport(request,"monthPopPost",monthPopPost[1]);
		String[] allPopPost = dao.getAllPopPost();
		setAttributeSupport(request,"allPopPostNo",allPopPost[0]);
		setAttributeSupport(request,"allPopPost",allPopPost[1]);
		
		String dayMostUser = dao.getDayMostUser();
		setAttributeSupport(request,"dayMostUser",dayMostUser);
		String weekMostUser = dao.getWeekMostUser();
		setAttributeSupport(request,"weekMostUser",weekMostUser);
		String monthMostUser = dao.getMonthMostUser();
		setAttributeSupport(request,"monthMostUser",monthMostUser);
		String allMostUser = dao.getAllMostUser();
		setAttributeSupport(request,"allMostUser",allMostUser);
		String dayMostUserCount = dao.getDayMostUserCount();
		setAttributeSupport(request,"dayMostUserCount",dayMostUserCount);
		String weekMostUserCount = dao.getWeekMostUserCount();
		setAttributeSupport(request,"weekMostUserCount",weekMostUserCount);
		String monthMostUserCount = dao.getMonthMostUserCount();
		setAttributeSupport(request,"monthMostUserCount",monthMostUserCount);
		String allMostUserCount = dao.getAllMostUserCount();
		setAttributeSupport(request,"allMostUserCount",allMostUserCount);
		
		String dayPostCount = dao.getDayPostCount();
		setAttributeSupport(request,"dayPostCount",dayPostCount);
		String weekPostCount = dao.getWeekPostCount();
		setAttributeSupport(request,"weekPostCount",weekPostCount);
		String monthPostCount = dao.getMonthPostCount();
		setAttributeSupport(request,"monthPostCount",monthPostCount);
		String allPostCount = dao.getAllPostCount();
		setAttributeSupport(request,"allPostCount",allPostCount);
		
		String dayBanPostCount = dao.getDayBanPostCount();
		setAttributeSupport(request,"dayBanPostCount",dayBanPostCount);
		String weekBanPostCount = dao.getWeekBanPostCount();
		setAttributeSupport(request,"weekBanPostCount",weekBanPostCount);
		String monthBanPostCount = dao.getMonthBanPostCount();
		setAttributeSupport(request,"monthBanPostCount",monthBanPostCount);
		String allBanPostCount = dao.getAllBanPostCount();
		setAttributeSupport(request,"allBanPostCount",allBanPostCount);
		
		String dayBanMemberCount = dao.getDayBanMemberCount();
		setAttributeSupport(request,"dayBanMemberCount",dayBanMemberCount);
		String weekBanMemberCount = dao.getWeekBanMemberCount();
		setAttributeSupport(request,"weekBanMemberCount",weekBanMemberCount);
		String monthBanMemberCount = dao.getMonthBanMemberCount();
		setAttributeSupport(request,"monthBanMemberCount",monthBanMemberCount);
		String allBanMemberCount = dao.getAllBanMemberCount();
		setAttributeSupport(request,"allBanMemberCount",allBanMemberCount);
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
	
	private void setAttributeSupport(HttpServletRequest request,String key, String value) {
		if(value==""||value==null) {
			request.setAttribute(key,"없음");
		}else {
			request.setAttribute(key,value);
		}
	}
	
}
