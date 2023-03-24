<%@ page language="java" contentType="text/html; charset=UTF-8"
    pageEncoding="UTF-8"%>
<!DOCTYPE html>
<html>
<head>
<meta charset="UTF-8">
<title>로그인</title>
<link rel="stylesheet" href="../assets/css/reset.css" />
<link rel="stylesheet" href="../assets/css/login.css?v=2" />
<script src="../assets/js/login.js?v=5" defer></script>
<script src="https://developers.kakao.com/sdk/js/kakao.js"></script>
</head>
<body>
	<div id="container">
		<h1 class="logo"><a href="/mainPage">트레닌 로그인</a></h1>
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
		
		String successMsg = (String)request.getAttribute("successMsg");
		if(successMsg != null && successMsg.equals("registerSuccess")){
		%>
		<script>
			alert("회원가입에 성공했습니다! 로그인을 진행해주세요!");
		</script>
		<%
		}
		%>
		<form name="loginForm" id="loginForm" method="post">
			<input type="hidden" name="url" value="<%= request.getParameter("url") %>">
			<input type="text" name="id" placeholder="이메일을 입력해주세요.">
			<input type="password" name="pw" placeholder="비밀번호를 입력헤주세요.">
			<span><input type="checkbox" name="autoLogin" value="true"> 자동 로그인</span>
			<a class="findPw" href="/member/findPw.jsp">비밀번호 찾기</a>
			<span class="loginBtn">로그인</span>
		</form>
		<ul class="socialLogin">
			<!-- 카카오 로그인 -->
			<li class="kakaoLogin"><a href="#" id="kakao_Login"><img height="55" src="/assets/image/kakao_login_large_narrow.png"/></a></li>
			<!-- 네이버 로그인 -->
			<li class="naverLogin"><a href="/oauth/naver"><img height="55" src="/assets/image/btnG_완성형.png"/></a></li>
		</ul>
	</div>
</body>
<script>
/*
	window.Kakao.init("ca3c33329662fff494d49918872b1ad2");
	
	function kakaoLogin() {
		window.Kakao.Auth.login({
			scope:'profile_nickname, profile_image, account_email',
			success: function(authObj) {
				console.log(authObj);
				window.Kakao.API.request({
					url:'/v2/user/me',
					success: res => {
						const kakao_account = res.kakao_account;
						console.log(kakao_account);
					}
				});
			}
		});
	}
	*/
</script>
</html>