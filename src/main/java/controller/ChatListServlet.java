package controller;

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
    	request.setCharacterEncoding("utf-8");
        response.setContentType("text/html; charset=UTF-8");
        
        String fromId = (String)request.getSession().getAttribute("memberId");
        
        String fromNick = new MemberInfoDao().getNicknameById(fromId);
        String toNick = request.getParameter("toNick");
        String listType = request.getParameter("listType");
        
        // 공백 or 비어있는 경우
        if(fromNick == null || fromNick.equals("")
        		|| toNick == null || toNick.equals("")
        		|| listType == null || listType.equals("")) {
        	// 공백 반환
        	response.getWriter().write("");
        }
        else if(listType.equals("ten")) { // 맨 처음 로딩시
        	response.getWriter().write(getTen(URLDecoder.decode(fromNick, "UTF-8"), URLDecoder.decode(toNick, "UTF-8")));
        }
        else { // 새로운 채팅 목록 불러오기 (값 있는 경우)
        	try { // 특정한 채팅 아이디값을 기준으로 대화 정보 가져오기
        		response.getWriter().write(getNew(URLDecoder.decode(fromNick, "UTF-8"), URLDecoder.decode(toNick, "UTF-8"), listType));
        		
        	} catch(Exception e) { // 오류 발생시 공백 문자열 반환
        		e.printStackTrace();
        		response.getWriter().write("");
        	}
        }
    }
    
    // 문서 처음 로딩시 최근 채팅 내역 10개 들고오기
    public String getTen(String fromNick, String toNick) {
    	StringBuffer result = new StringBuffer("");
    	
    	ChatDao chatDao = new ChatDao();
    	MemberInfoDao memberDao = new MemberInfoDao();
    	
    	result.append("{\"result\":["); 
    	ArrayList<ChatDto> chatList = chatDao.getChatListByRecent(fromNick, toNick, 10);
    	if(chatList.size() == 0) return "";
		for(int i=0; i<chatList.size(); i++) {
			String from = memberDao.getNicknameByNo(chatList.get(i).getFromNo());
			String to = memberDao.getNicknameByNo(chatList.get(i).getToNo());
//			System.out.println("getFromID : " + chatList.get(i).getFromID());
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
    public String getNew(String fromNick, String toNick, String chatNo) {
    	StringBuffer result = new StringBuffer("");
    	
    	ChatDao chatDao = new ChatDao();
    	MemberInfoDao memberDao = new MemberInfoDao();
    	
    	result.append("{\"result\":[");
    	ArrayList<ChatDto> chatList = chatDao.getChatListByNo(fromNick, toNick, chatNo);
    	if(chatList.size() == 0) return "";
    	for(int i=0; i<chatList.size(); i++) {
			String from = memberDao.getNicknameByNo(chatList.get(i).getFromNo());
			String to = memberDao.getNicknameByNo(chatList.get(i).getToNo());
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

}
