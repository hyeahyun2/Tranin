package controller.myPage;

import java.io.IOException;
import java.io.PrintWriter;
import java.util.ArrayList;

import javax.servlet.ServletException;
import javax.servlet.annotation.WebServlet;
import javax.servlet.http.HttpServlet;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;

import org.json.JSONObject;

import dao.MemberInfoDao;
import dao.MyPageDao;
import dto.MarketDto;
import util.MyUtils;

@WebServlet("/myPage/myPageActivityAjax")
public class MyPageActivityController extends HttpServlet {
	private static final long serialVersionUID = 1L;
	public MyPageActivityController() {
        super();
        System.out.println("MyPageActivityController 입장");
    }
	protected void doPost(HttpServletRequest request, HttpServletResponse response) throws ServletException, IOException {
		response.setCharacterEncoding("utf-8");
		response.setContentType("text/html");
		request.setCharacterEncoding("utf-8");
		
		MyUtils myUtils = new MyUtils();
        JSONObject jsonObject = new JSONObject(myUtils.getJSONObjectFromHttpServletRequest(request));
		
        MyPageDao dao = new MyPageDao();
        
        String part = (String)jsonObject.get("part");
        int clickNum =  Integer.parseInt((String)jsonObject.get("clickNum"));
        int oneClickLoad = Integer.parseInt((String)jsonObject.get("loadNum"));
        String writerId = (String)request.getSession().getAttribute("memberId");
        int writerNo = dao.getMemberNoById(writerId);
        String str = getPostList(part, clickNum, oneClickLoad, writerNo);
        
        PrintWriter out = response.getWriter();
        out.println(str);

	}
	
	// 게시글 정보 담기
	public String getPostList(String part, int clickNum, int oneClickLoad, int no) {
		StringBuffer result = new StringBuffer("");
		System.out.println("번번번호호호"+no);
		MyPageDao myPageDao = new MyPageDao();
		MemberInfoDao memberDao = new MemberInfoDao();
		
		int writerNo = no;
		
		int postCount = myPageDao.getPostCount(part,no);
		System.out.println(postCount);
		
		if(postCount <= (clickNum * oneClickLoad)) { // 모두 로드된 상황
			return "";
		}
		
		result.append("{\"result\":[");
		
		ArrayList<MarketDto> postList = myPageDao.getPostList(part, clickNum, oneClickLoad, writerNo);
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
		
		if(postCount <=((clickNum+1)*oneClickLoad)) {
			result.append("], \"postNum\":\"" + postCount + "\",");
			result.append("\"isEnd\": \"" + "true" + "\"}");
			System.out.println("끝이에오");
		}else {
			result.append("], \"postNum\":\"" + postCount + "\"}");
			System.out.println("끝아니에오");
		}
		System.out.println(result.toString());
		
		return result.toString();
	}
}
