package controller.mainPage;

import java.io.IOException;
import java.util.ArrayList;
import java.util.List;

import javax.servlet.RequestDispatcher;
import javax.servlet.ServletException;
import javax.servlet.annotation.WebServlet;
import javax.servlet.http.HttpServlet;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;

import dao.PromotionDao;
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
		
		request.setAttribute("promotionList", promotionList);
		
		RequestDispatcher requestDispatcher = request.getRequestDispatcher("/mainPage/welcome.jsp");
		requestDispatcher.forward(request, response);
	}
}