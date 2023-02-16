package controller.market;

import java.io.IOException;
import java.util.ArrayList;

import javax.servlet.ServletException;
import javax.servlet.annotation.WebServlet;
import javax.servlet.http.HttpServlet;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;

import dao.MarketDao;
import dao.MemberInfoDao;
import dto.MarketDto;

@WebServlet("/marketListServlet")
public class MarketListServlet extends HttpServlet {
	private static final long serialVersionUID = 1L;

	@Override
	protected void doGet(HttpServletRequest req, HttpServletResponse resp) throws ServletException, IOException {
		doPost(req, resp);
	}

	@Override
	protected void doPost(HttpServletRequest request, HttpServletResponse response) throws ServletException, IOException {
		
		// 기본 설정
		request.setCharacterEncoding("UTF-8");
		response.setContentType("text/html; charset=UTF-8");

        String part = request.getParameter("part"); // sell or buy
        String searchKey = request.getParameter("searchKey"); // 검색 키워드
        int clickNum = Integer.parseInt(request.getParameter("clickNum")); // 더보기 클릭 수
        int oneClickLoad = Integer.parseInt(request.getParameter("loadNum")); // 클릭 한 번 당 로드될 게시글 수
        
        response.getWriter().write(getPostList(part, searchKey, clickNum, oneClickLoad));
	}
	
	// 게시글 정보 담기
	public String getPostList(String part, String searchKey, int clickNum, int oneClickLoad) {
		StringBuffer result = new StringBuffer("");
		
		MarketDao marketDao = new MarketDao();
		MemberInfoDao memberDao = new MemberInfoDao();
		
//		int postCount = marketDao.getPostCount(part);
		int postCount =
				(searchKey == null || searchKey.equals("")) ? // 검색 키워드 있는지 확인
						marketDao.getPostCount(part) : marketDao.getSearchPostCount(part, searchKey);
		System.out.println(postCount);
		if(postCount <= (clickNum * oneClickLoad)) { // 모두 로드된 상황
			return "";
		}

		result.append("{\"result\":[");
		
//		ArrayList<MarketDto> postList = marketDao.getPostList(part, clickNum, oneClickLoad);
		ArrayList<MarketDto> postList = 
				(searchKey == null || searchKey.equals("")) ? // 검색 키워드 있는지 확인
						marketDao.getPostList(part, clickNum, oneClickLoad) : marketDao.getSearchPostList(part, searchKey, clickNum, oneClickLoad);
		if(postList.size() == 0) return "";
		for(int i=0; i<postList.size(); i++) {
			String imgName = "";
			String imgUrl = postList.get(i).getImage()[0];
			if(imgUrl != null) {
				imgName = imgUrl.substring(imgUrl.lastIndexOf("/") + 1);
			}
			result.append("{\"no\": \"" + postList.get(i).getMarketNo() + "\",");
			result.append("\"writerNick\": \"" + memberDao.getNicknameByNo(postList.get(i).getWriterNo()) + "\",");
			result.append("\"title\": \"" + postList.get(i).getTitle() + "\",");
			result.append("\"price\": \"" + postList.get(i).getPrice() + "\",");
			result.append("\"writeDate\": \"" + postList.get(i).getWriteDate() + "\",");
			result.append("\"hits\": \"" + postList.get(i).getHits() + "\",");
			result.append("\"titleImage\": \"" + imgName + "\"}");
			if(i != postList.size() - 1) result.append(",");
		}
		
		result.append("], \"postNum\":\"" + postCount + "\"}");
		
		System.out.println(result.toString());
		
		return result.toString();
	}
}
