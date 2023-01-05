package controller;

import java.io.IOException;
import java.io.PrintWriter;
import java.net.URLDecoder;
import java.rmi.ServerException;

import javax.servlet.annotation.WebServlet;
import javax.servlet.http.HttpServlet;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;

import dao.ChatDao;
import dao.MemberSelectLoginDao;

@WebServlet("/chatSubmetServlet")
public class ChatSubmetServlet extends HttpServlet {
	private static final long serialVersionUID = 1L;
	
    protected void doPost(HttpServletRequest request, HttpServletResponse response) throws ServerException, IOException{
        // 기본 설정
    	request.setCharacterEncoding("utf-8");
        response.setContentType("text/html; charset=UTF-8");
        
        String fromNick = (String)request.getSession().getAttribute("nickname");
        String toNick = request.getParameter("toNick");
        String chatContent = request.getParameter("chatContent");
        
        // 공백 or 비어있는 경우
        if(fromNick == null || fromNick.equals("")
        		|| toNick == null || toNick.equals("")
        		|| chatContent == null || chatContent.equals("")) {
        	// "0" 문자 반환
        	response.getWriter().write("0");
        }
        else { // 아닌 경우
        	System.out.println("채팅 받아옴");
        	// 디코딩
        	fromNick = URLDecoder.decode(fromNick, "utf-8");
        	toNick = URLDecoder.decode(toNick, "utf-8");
        	chatContent = URLDecoder.decode(chatContent, "utf-8");
        	
        	// 1 : 성공, -1 : 데이터베이스 오류
        	// 문자열 형태로 반환
        	response.getWriter().write(new ChatDao().submit(fromNick, toNick, chatContent) + "");
        }
    }

}