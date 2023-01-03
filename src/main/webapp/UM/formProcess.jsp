<%@ page language="java" contentType="text/html; charset=UTF-8"
    pageEncoding="UTF-8"%>
<%@ page import="dao.DBProperty" %>
<%@ page import="dto.MemberDto" %>
<%@ page import="dao.MemberInsertDao" %>


	<%
	// 기본 세팅(인코딩)
	request.setCharacterEncoding("utf-8");
	response.setContentType("text/html; charset=UTF-8");
	
	String memberId = request.getParameter("memberId");
	String password = request.getParameter("password");
	String nickName = request.getParameter("nickName");
	String zipCode = request.getParameter("zipCode");
	String address1 = request.getParameter("address1");
	String address2 = request.getParameter("address2");
	
	String address = address1 + " " + address2;
	
	MemberInsertDao dao = new MemberInsertDao();
	boolean state = dao.register(memberId, password, nickName, address, zipCode);
	
	if(state){ // 회원가입 성공
		session.setAttribute("memberId", memberId);
		response.sendRedirect("index.jsp");
	}
	else {
		response.sendRedirect("register.jsp");
	}
	%>