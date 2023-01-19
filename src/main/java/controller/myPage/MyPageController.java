package controller.myPage;

import java.io.IOException;

import javax.servlet.RequestDispatcher;
import javax.servlet.ServletException;
import javax.servlet.annotation.WebServlet;
import javax.servlet.http.HttpServlet;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;

@WebServlet("/myPage/myPage")
public class MyPageController extends HttpServlet {
	private static final long serialVersionUID = 1L;
	public MyPageController() {
        super();
    }
	protected void doGet(HttpServletRequest request, HttpServletResponse response) throws ServletException, IOException {
		
		response.setCharacterEncoding("utf-8");
		response.setContentType("text/html");
		
		String param = request.getParameter("myPageCategory");
		if(param==null) {
			param = "0";
		}
		request.setAttribute("myPageParam",param);
		
		String param2 = request.getParameter("myPageManagerCategory");
		if(param2==null) {
			param2 = "0";
		}
		request.setAttribute("myPageManagerParam",param2);
		System.out.println("MyPageController 입장");
		RequestDispatcher requestDispatcher = request.getRequestDispatcher("/myPage/myPage.jsp");
		requestDispatcher.forward(request, response);
	}
}
