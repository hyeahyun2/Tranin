<%@ page language="java" contentType="text/html; charset=UTF-8"
    pageEncoding="UTF-8"%>
<!DOCTYPE html>
<html>
<head>
	<%
	String myNick = null;
	String toNick = null;
	if(session.getAttribute("memberId") != null){
		myNick = (String)session.getAttribute("memberId");
	}
	if(request.getParameter("toNick") != null){
		toNick = (String)request.getParameter("toNick");
	}
	%>
<meta charset="UTF-8">
<title>채팅</title>
<link rel="stylesheet" href="../assets/css/chatting.css?v=3">
<link rel="stylesheet" href="../assets/css/reset.css">
<script src="../assets/js/chatting.js?v=3" defer></script>
</head>
<body>
	<div id="head">
		<h1 class="header"><span><%= toNick %></span></h1>
		<a href="./chatMain.jsp" class="backBtn">뒤로가기</a>
	</div>
	<div class="chat-content">
		<!-- 채팅 공간 -->
	</div>
	<input class="chat-box" id="chatInput" type="text">
	<button id="Chatsend">전송</button>
</body>
</html>