package controller.chat;

import java.io.IOException;
import java.util.ArrayList;

import javax.servlet.ServletException;
import javax.servlet.annotation.WebServlet;
import javax.servlet.http.HttpServlet;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;

import dao.ChatDao;

@WebServlet("/chatNewCheck")
public class ChatNewCheckServlet extends HttpServlet {

	@Override
	protected void doGet(HttpServletRequest req, HttpServletResponse resp) throws ServletException, IOException {
		doPost(req, resp);
	}

	@Override
	protected void doPost(HttpServletRequest request, HttpServletResponse response) throws ServletException, IOException {
		// 기본 설정
		request.setCharacterEncoding("UTF-8");
		response.setContentType("text/html; charset=UTF-8");
        
        String memberId = (String)request.getSession().getAttribute("memberId");
        
        // 채팅 내역 있는 사용자 no 리스트
    	ChatDao dao = new ChatDao();
    	ArrayList<String> otherNickList = dao.getOtherNickList(memberId);
    	
    	// 새 메세지 여부 (전체 받은 메세지 중)
    	String newMessage = "true";
    	
    	for(int i=0; i<otherNickList.size(); i++) {
    		boolean state = dao.isReadChat(memberId, otherNickList.get(i));
    		if(!state) { // 새 메세지 없는 경우
    			newMessage = "false";
    		}
		}
    	
    	response.getWriter().write(newMessage);
	}
	

}
