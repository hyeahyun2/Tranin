<%@ page language="java" contentType="text/html; charset=UTF-8"
    pageEncoding="UTF-8" import="java.util.ArrayList" %>
<!DOCTYPE html>
<html>
<head>
	<%
    String memberId = null;
    memberId = (String)session.getAttribute("memberId");
    %>
<meta charset="UTF-8">
<title>채팅방 목록</title>
<link rel="stylesheet" href="../assets/css/chatMain.css?v=2">
<script src="../assets/js/chatMain.js?v=<%=System.currentTimeMillis()%>" defer></script>
</head>
<body>
	<%
	if(memberId != null){ // 로그인 상태일 때
	%>
	<h1 class="header"><span>채팅목록</span></h1>
	<ul id="chatMemberList">
		<!-- 채팅 내역 있는 user의 id 기반 채팅방 목록 -->
	</ul>
	<%
	}
	%>
</body>
</html>