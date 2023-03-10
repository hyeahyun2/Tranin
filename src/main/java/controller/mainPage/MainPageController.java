package controller.mainPage;

import java.io.IOException;
import java.util.ArrayList;
import java.util.List;

import javax.servlet.RequestDispatcher;
import javax.servlet.ServletException;
import javax.servlet.annotation.WebServlet;
import javax.servlet.http.Cookie;
import javax.servlet.http.HttpServlet;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;

import dao.MarketDao;
import dao.MemberInfoDao;
import dao.PromotionDao;
import dto.MarketDto;
import dto.PromotionDto;

@WebServlet("/mainPage")
public class MainPageController extends HttpServlet {
	private static final long serialVersionUID = 1L;
	public MainPageController() {
        super();
    }
	protected void doGet(HttpServletRequest request, HttpServletResponse response) throws ServletException, IOException {
		doPost(request,response);
	}
	protected void doPost(HttpServletRequest request, HttpServletResponse response) throws ServletException, IOException {
		response.setCharacterEncoding("utf-8");
		response.setContentType("text/html");
		
		PromotionDao dao = new PromotionDao();
		List<PromotionDto> promotionList = new ArrayList<>();
		promotionList = dao.getPromotionList();
		
		// 장터글(판매글) 리스트
		MarketDao marketDao = new MarketDao();
		ArrayList<MarketDto> postList = marketDao.getPostList("sell", 1, 8);
		
		// 자동 로그인 확인
		Cookie[] c = request.getCookies();
	    if (c != null && request.getSession().getAttribute("memberId") == null) {
	      for (Cookie cf : c) {
	        if (cf.getName().equals("autoLogin")){
	          String ids = cf.getValue();
	          MemberInfoDao mInfoDao = new MemberInfoDao();
	          request.getSession().setAttribute("memberId", ids);
	          request.getSession().setAttribute("memberNick", mInfoDao.getNicknameById(ids));
	        }
	      }
	    }
		
		request.setAttribute("promotionList", promotionList);
		request.setAttribute("postList", postList);
		
		RequestDispatcher requestDispatcher = request.getRequestDispatcher("/mainPage/welcome.jsp");
		requestDispatcher.forward(request, response);
	}
}
