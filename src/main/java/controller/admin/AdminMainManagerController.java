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

@WebServlet("/myPage/managerPage")
public class AdminMainManagerController extends HttpServlet {
	private static final long serialVersionUID = 1L;
	public AdminMainManagerController() {
        super();
    }
	@Override
	protected void doGet(HttpServletRequest request, HttpServletResponse response) throws ServletException, IOException {
		//관리자는 현재 활동중!
		AdminLoginDao setTime = new AdminLoginDao();
		
		if(request.getSession().getAttribute("manager")!=null) {
			response.setCharacterEncoding("utf-8");
			response.setContentType("text/html");
			request.setAttribute("myPageManagerParam","2");//관리자 마이페이지의 3번째 메뉴임을 의미
			RequestDispatcher requestDispatcher = request.getRequestDispatcher("/myPage/myPage.jsp");
			requestDispatcher.forward(request, response);
		}
	}

	@Override
	protected void doPost(HttpServletRequest req, HttpServletResponse resp) throws ServletException, IOException {
		doGet(req,resp);
	}
	
}
