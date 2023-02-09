package controller.allSearch;

import java.io.IOException;

import javax.servlet.ServletException;
import javax.servlet.annotation.WebServlet;
import javax.servlet.http.HttpServlet;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;

import dao.AllSearchDao;

@WebServlet("/mainSearch")
public class AllSearchController extends HttpServlet {
	//http://localhost:8080/mainSearch?keyword=sad&searchCategory=%EC%9E%A5%ED%84%B0
	private static final long serialVersionUID = 1L;
	public AllSearchController() {
        super();
        System.out.println("AllSearchController...");
    }
	@Override
	protected void doGet(HttpServletRequest req, HttpServletResponse resp) throws ServletException, IOException {
		doPost(req,resp);
	}
	@Override
	protected void doPost(HttpServletRequest req, HttpServletResponse resp) throws ServletException, IOException {
		AllSearchDao dao = new AllSearchDao();
		
		String keyword = req.getParameter("keyword");
		String searchCategory = req.getParameter("searchCategory");
		
		System.out.println("keyword:"+keyword+"//"+"searchCategory:"+searchCategory);
		
		if(searchCategory.equals("공지사항")) {
			//공지사항 검색
			//http://localhost:8080/notice/notice.jsp?searchText=asdasdadsda
			String searchNoticeURL = "/notice/notice.jsp?searchText="+keyword;
			resp.sendRedirect(searchNoticeURL);
		}else if(searchCategory.equals("장터")) {
			//장터 검색
			//http://localhost:8080/market/market.jsp?searchKey=dd
			String searchMarketURL = "/market/market.jsp?searchKey="+keyword;
			resp.sendRedirect(searchMarketURL);
		}
	}
}
