package controller.chat;

import java.io.IOException;
import java.net.URLDecoder;
import java.rmi.ServerException;

import javax.servlet.annotation.WebServlet;
import javax.servlet.http.HttpServlet;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;

import java.util.ArrayList;
import dao.ChatDao;
import dao.MemberInfoDao;
import dto.ChatDto;

@WebServlet("/chatListServlet")
public class ChatListServlet extends HttpServlet {
	private static final long serialVersionUID = 1L;
	
    protected void doPost(HttpServletRequest request, HttpServletResponse response) throws ServerException, IOException{
    	// 기본 설정
		request.setCharacterEncoding("UTF-8");
		response.setContentType("text/html; charset=UTF-8");
        
        String fromId = (String)request.getSession().getAttribute("memberId");
        
        String fromNick = (String)request.getSession().getAttribute("memberNick");
        String toNick = request.getParameter("toNick");
        String listType = request.getParameter("listType");
        String status = request.getParameter("status");
        
        // 공백 or 비어있는 경우
        if(fromNick == null || fromNick.equals("")
        		|| toNick == null || toNick.equals("")
        		|| listType == null || listType.equals("")
        		|| status == null || status.equals("")) {
        	// 공백 반환
        	response.getWriter().write("");
        }
        else if(listType.equals("ten")) { // 맨 처음 로딩시
        	response.getWriter().write(getTen(URLDecoder.decode(fromNick, "UTF-8"), URLDecoder.decode(toNick, "UTF-8")));
        }
        else if(status.equals("after")){ // 새로운 채팅 목록 불러오기 (값 있는 경우)
        	try { // 특정한 채팅 아이디값을 기준으로 대화 정보 가져오기
        		response.getWriter().write(getNew(URLDecoder.decode(fromNick, "UTF-8"), URLDecoder.decode(toNick, "UTF-8"), listType));
        		
        	} catch(Exception e) { // 오류 발생시 공백 문자열 반환
        		e.printStackTrace();
        		response.getWriter().write("");
        	}
        }
        else if(status.equals("before")) {
        	try {
        		System.out.println("before chat loading...");
        		response.getWriter().write(getPrev(URLDecoder.decode(fromNick, "UTF-8"), URLDecoder.decode(toNick, "UTF-8"), listType));
        	} catch(Exception e) {
        		e.printStackTrace();
        		response.getWriter().write("");
        	}
        }
    }
    
    // 문서 처음 로딩시 최근 채팅 내역 10개 들고오기
    public String getTen(String myNick, String otherNick) {
    	StringBuffer result = new StringBuffer("");
    	
    	ChatDao chatDao = new ChatDao();
    	MemberInfoDao memberDao = new MemberInfoDao();
    	
    	result.append("{\"result\":["); 
    	ArrayList<ChatDto> chatList = chatDao.getChatListByRecent(myNick, otherNick, 10);
    	int myNo = memberDao.getNoByNick(myNick);
    	if(chatList.size() == 0) return "";
		for(int i=0; i<chatList.size(); i++) {
			String from = (myNo == chatList.get(i).getFromNo()) ? myNick : otherNick;
			String to = (myNo == chatList.get(i).getToNo()) ? myNick : otherNick;
			result.append("[{\"value\": \"" + from + "\"},");
			result.append("{\"value\": \"" + to + "\"},");
			result.append("{\"value\": \"" + chatList.get(i).getChatContent() + "\"},");
			result.append("{\"value\": \"" + chatList.get(i).getChatTime() + "\"}]");
			if(i != chatList.size() - 1) result.append(",");
		}
		// json문장 끝 , 가장 마지막 chatID 담기
		result.append("], \"last\":\"" + chatList.get(0).getChatNO() + "\"}");
		System.out.println(result.toString());
    	return result.toString();
    }
    
    // 새로운 채팅 내역
    public String getNew(String myNick, String otherNick, String chatNo) {
    	StringBuffer result = new StringBuffer("");
    	
    	ChatDao chatDao = new ChatDao();
    	MemberInfoDao memberDao = new MemberInfoDao();
    	
    	result.append("{\"result\":[");
    	ArrayList<ChatDto> chatList = chatDao.getChatListByNo(myNick, otherNick, chatNo);
    	int myNo = memberDao.getNoByNick(myNick);
    	if(chatList.size() == 0) return "";
    	for(int i=0; i<chatList.size(); i++) {
			String from = (myNo == chatList.get(i).getFromNo()) ? myNick : otherNick;
			String to = (myNo == chatList.get(i).getToNo()) ? myNick : otherNick;
			result.append("[{\"value\": \"" + from + "\"},");
			result.append("{\"value\": \"" + to + "\"},");
			result.append("{\"value\": \"" + chatList.get(i).getChatContent() + "\"},");
			result.append("{\"value\": \"" + chatList.get(i).getChatTime() + "\"}]");
			if(i != chatList.size() - 1) result.append(",");
		}
    	
    	// json문장 끝 , 가장 마지막 chatID 담기
		result.append("], \"last\":\"" + chatList.get(0).getChatNO() + "\"}");
		return result.toString();
    }
    
    // 이전 채팅 내역
    public String getPrev(String myNick, String otherNick, String numOfChat) {
    	StringBuffer result = new StringBuffer("");
    	
    	ChatDao chatDao = new ChatDao();
    	MemberInfoDao memberDao = new MemberInfoDao();
    	
    	result.append("{\"result\":[");
    	ArrayList<ChatDto> chatList = chatDao.getPrevChatListByfirstNo(myNick, otherNick, numOfChat, 10);
    	int myNo = memberDao.getNoByNick(myNick);
    	if(chatList.size() == 0) return "";
    	for(int i=0; i<chatList.size(); i++) {
			String from = (myNo == chatList.get(i).getFromNo()) ? myNick : otherNick;
			String to = (myNo == chatList.get(i).getToNo()) ? myNick : otherNick;
			result.append("[{\"value\": \"" + from + "\"},");
			result.append("{\"value\": \"" + to + "\"},");
			result.append("{\"value\": \"" + chatList.get(i).getChatContent() + "\"},");
			result.append("{\"value\": \"" + chatList.get(i).getChatTime() + "\"}]");
			if(i != chatList.size() - 1) result.append(",");
		}
    	
    	// json문장 끝
		result.append("]}");
		System.out.println(result.toString());
		return result.toString();
    }

}
