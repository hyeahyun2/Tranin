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

@WebServlet({"/myPage/marketManagerPage",
	"/myPage/marketManagerPage/oneMarketBan.do",
	"/myPage/marketManagerPage/selectedMarketBan.do",
	"/myPage/marketManagerPage/allMarketBan.do",
	"/myPage/marketManagerPage/oneMarketRestore.do",
	"/myPage/marketManagerPage/selectedMarketRestore.do",
	"/myPage/marketManagerPage/allMarketRestore.do",
	"/myPage/marketManagerPage/marketSearch.do",
	"/myPage/marketManagerPage/bannedMarketSearch.do"})
public class AdminMarketManagerController extends HttpServlet {
	//managerPage?myPageManagerCategory=2&memberManagerNo=1
	private static final long serialVersionUID = 1L;
	public AdminMarketManagerController() {
        super();
    }
	protected void doGet(HttpServletRequest request, HttpServletResponse response) throws ServletException, IOException {
		//관리자는 현재 활동중!
		AdminLoginDao setTime = new AdminLoginDao();
		setTime.setSession(request.getSession().getId(), (String)request.getSession().getAttribute("manager"));
		
		//한글삭제, 전체삭제 구현
		if(request.getSession().getAttribute("manager")!=null) {
			AdminMarketDao marketDao = new AdminMarketDao();
			if(request.getServletPath().equals("/myPage/marketManagerPage/oneMarketBan.do")) {//글하나차단
				marketDao.deleteMarketByNo(Integer.parseInt((String)request.getParameter("no")));
				response.sendRedirect("../marketManagerPage?myPageManagerCategory=2&marketManager=0&marketManagerNo=1");
			}else if(request.getServletPath().equals("/myPage/marketManagerPage/selectedMarketBan.do")) {//선택차단
				String chkdID = request.getParameter("chkdID");
				marketDao.deleteSelMarket(chkdID);
				response.sendRedirect("../marketManagerPage?myPageManagerCategory=2&marketManager=0&marketManagerNo=1");
			}else if(request.getServletPath().equals("/myPage/marketManagerPage/allMarketBan.do")) {//전체차단
				marketDao.deleteAllMarket();
				response.sendRedirect("../marketManagerPage?myPageManagerCategory=2&marketManager=0&marketManagerNo=1");
			}else if(request.getServletPath().equals("/myPage/marketManagerPage/oneMarketRestore.do")) {//개인복원
				marketDao.restoreMarketByNo(Integer.parseInt((String)request.getParameter("no")));
				response.sendRedirect("../marketManagerPage?myPageManagerCategory=2&marketManager=0&marketManagerNo=1&marketManagerSub=1");
			}else if(request.getServletPath().equals("/myPage/marketManagerPage/selectedMarketRestore.do")) {//선택복원
				String chkdID = request.getParameter("chkdID");
				marketDao.restoreSelMarket(chkdID);
				response.sendRedirect("../marketManagerPage?myPageManagerCategory=2&marketManager=0&marketManagerNo=1&marketManagerSub=1");
			}else if(request.getServletPath().equals("/myPage/marketManagerPage/allMarketRestore.do")) {//전체복원
				marketDao.restoreAllMarket();
				response.sendRedirect("../marketManagerPage?myPageManagerCategory=2&marketManager=0&marketManagerNo=1&marketManagerSub=1");
			}else {
				
				//기본경로인 경우처리
				response.setCharacterEncoding("utf-8");
				response.setContentType("text/html");
				String param = request.getParameter("myPageManagerCategory");
				if(param==null) {
					param = "2";
				}
				request.setAttribute("myPageManagerCategory",param);
				String param2 = request.getParameter("marketManagerNo");
				if(param2==null) {
					param2 = "1";
				}
				request.setAttribute("myPageManagerNo",param2);
				request.setAttribute("myPageManagerParam","2");

				//마켓글목록 페이징
				if(request.getParameter("marketManagerNo")!=null) {
					if(request.getParameter("select")!=null) {//마켓검색
						int adminNo= Integer.parseInt(request.getParameter("marketManagerNo"));
						ArrayList<MarketDto> marketArrayList = marketDao.searchMarket((String)request.getParameter("select"),(String)request.getParameter("keyword"),adminNo);
						request.setAttribute("marketArrayList", marketArrayList);
					}else {
						int adminNo= Integer.parseInt(request.getParameter("marketManagerNo"));
						ArrayList<MarketDto> marketArrayList = marketDao.getMarketList(adminNo);
						System.out.println(marketArrayList.get(1).toString());
						request.setAttribute("marketArrayList", marketArrayList);
					}
				}
				//밴한멤버목록 페이징
				if((request.getParameter("marketManagerNo")!=null)&&(request.getParameter("marketManagerSub")!=null)) {
					if(request.getParameter("select")!=null) {//벤된 멤버검색
						int adminNo= Integer.parseInt(request.getParameter("marketManagerNo"));
						ArrayList<MarketResponseDto> bannedMarketArrayList = marketDao.searchBannedMarket((String)request.getParameter("select"),(String)request.getParameter("keyword"),adminNo);
						request.setAttribute("bannedMarketArrayList", bannedMarketArrayList);
					}else {
						int adminNo= Integer.parseInt(request.getParameter("marketManagerNo"));
						ArrayList<MarketResponseDto> bannedMarketArrayList = marketDao.getBannedMarketList(adminNo);
						request.setAttribute("bannedMarketArrayList", bannedMarketArrayList);
					}
				}
				System.out.println("AdminMarketManagerController 입장");
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
