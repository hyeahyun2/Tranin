<%@ page language="java" contentType="text/html; charset=UTF-8"
    pageEncoding="UTF-8"%>
<!DOCTYPE html>
<html>
<head>
<meta charset="UTF-8">
<title>비밀번호 찾기</title>
<link rel="stylesheet" href="../assets/css/reset.css" />
<link rel="stylesheet" href="../assets/css/changePw.css?v=2" />
<script src="../assets/js/changePw.js" defer></script>
<%
String memberId = (String)request.getAttribute("memberId");
%>
</head>
<body>
	<div id="container">
		<h1 class="logo"><a href="/mainPage">비밀번호 찾기</a></h1>
		<form name="changePwForm" id="changePwForm" method="post">
			<input type="hidden" name="id" value="<%= memberId %>">
			<label>
				<input type="password" name="pw" placeholder="변경할 비밀번호를 입력해주세요.">
			</label>
			<label>
				<input type="password" name="pwCheck" placeholder="비밀번호를 한번 더 입력해주세요.">
			</label>
		</form>
		<span class="changePwBtn">비밀번호 변경</span>
	</div>
</body>
</html>