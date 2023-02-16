package controller.chat;

import java.io.IOException;
import java.rmi.ServerException;
import java.util.ArrayList;

import javax.servlet.annotation.WebServlet;
import javax.servlet.http.HttpServlet;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;

import dao.ChatDao;
import dao.MemberInfoDao;

@WebServlet("/ChatOhterMemberIDListServlet")
public class ChatOhterMemberIDListServlet extends HttpServlet {
	private static final long serialVersionUID = 1L;
	
    protected void doPost(HttpServletRequest request, HttpServletResponse response) throws ServerException, IOException{
    	// 기본 설정
		request.setCharacterEncoding("UTF-8");
		response.setContentType("text/html; charset=UTF-8");
        
        // 입력받은 값 변수에 저장
        String memberId = (String)request.getSession().getAttribute("memberId");
        
        System.out.println("memberId : " + memberId);
        // 공백 OR 비어있는 경우
        if(memberId == null || memberId.equals("")) {
        	response.getWriter().write(""); // 공백 반환
        }
        else { // session이 공백이 아닌 경우 -> 제대로 불러온 경우
        	
        	try { // 채팅 내역 있는 아이디 리스트 반환
        		response.getWriter().write(getOtherMemberNickList(memberId));
        	} catch(Exception e) {
        		e.printStackTrace();
        	}
        }
	}
    
    // 채팅 내역 있는 사용자의 [닉네임] 목록 json 형태로 얻기
    public String getOtherMemberNickList(String memberId) {
    	// 채팅 내역 있는 사용자 no 리스트
    	ChatDao dao = new ChatDao();
    	ArrayList<String> otherNickList = dao.getOtherNickList(memberId);
    	System.out.println(otherNickList.toString());
		if(otherNickList.size() == 0) return ""; // 없는 경우 공백 반환
		
		/* json 형태로 nickname 리스트 저장 */
		// StringBuffer 객체 생성
    	StringBuffer result = new StringBuffer("");
    	// json형태로
    	result.append("{\"result\":[");
		for(int i=0; i<otherNickList.size(); i++) {
			String isRead = dao.isReadChat(memberId, otherNickList.get(i)) ? "true" : "false";
			result.append("{\"otherMemberNick\": \"" + otherNickList.get(i) + "\",");
			result.append("\"isRead\": \"" + isRead + "\"}");
			if(i != otherNickList.size() - 1) result.append(",");
		}
		
		// json 문장 끝
		result.append("]}");
		System.out.println(result.toString());
    	
    	return result.toString();
    }

}

