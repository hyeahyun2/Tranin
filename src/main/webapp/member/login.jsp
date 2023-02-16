<%@ page language="java" contentType="text/html; charset=UTF-8"
    pageEncoding="UTF-8"%>
<!DOCTYPE html>
<html>
<head>
<meta charset="UTF-8">
<title>로그인</title>
<link rel="stylesheet" href="../assets/css/reset.css" />
<link rel="stylesheet" href="../assets/css/login.css?v=2" />
<script src="../assets/js/login.js" defer></script>
</head>
<body>
	<div id="container">
		<h1 class="logo"><a href="../mainPage/welcome.jsp">트레닌 로그인</a></h1>
		<%
		String errorMsg = (String)request.getAttribute("errorMsg");
		if(errorMsg != null && errorMsg.equals("loginFail")){ // 로그인 실패
		%>
			<div class="errorWrap">
				<span>! 로그인 정보가 일치하지 않습니다.</span>
			</div>
		<%
		}
		else if(errorMsg != null && errorMsg.equals("isBanMember")){ // 차단된 멤버
		%>
			<div class="errorWrap">
				<span>! 관리자에의해 차단된 회원입니다.</span>
			</div>
		<%
		}
		%>
		<form name="loginForm" id="loginForm" method="post">
			<input type="hidden" name="url" value="<%= request.getParameter("url") %>">
			<input type="text" name="id" placeholder="이메일을 입력해주세요.">
			<input type="password" name="pw" placeholder="비밀번호를 입력헤주세요.">
			<input type="checkbox" name="autoLogin" value="true">자동 로그인
			<a href="/member/findPw.jsp">비밀번호 찾기</a>
			<span class="loginBtn">로그인</span>
		</form>
		<ul class="socialLogin">
			<li class="kakaoLogin">카카오 로그인</li>
			<li class="naverLogin">네이버 로그인</li>
		</ul>
	</div>
</body>
</html>