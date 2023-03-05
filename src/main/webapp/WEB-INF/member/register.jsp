<%@ page language="java" contentType="text/html; charset=UTF-8"
    pageEncoding="UTF-8"%>
<!DOCTYPE html>
<html>
<head>
<meta charset="UTF-8">
<title>회원 가입</title>
<link rel="stylesheet" href="../assets/css/reset.css?v=2" />
<link rel="stylesheet" href="../assets/css/register.css" />
<link rel="stylesheet" href="../assets/css/footer.css?v=2" />
<script src="https://spi.maps.daum.net/imap/map_js_init/postcode.v2.js"></script>
<script src="../assets/js/register.js?v=2" defer></script>
</head>
<body>
	<div id="container">
		<h1 class="logo"><a href="../mainPage/welcome.jsp">트레닌 회원가입</a></h1>
		<form name="registerForm" id="registerForm" method="post">
			<label>
				<span class="explanation">이메일</span>
				<input type="text" name="email" placeholder="abc@abc.com">
			</label>
				<span class="emailAuthBtn spanBtn">인증번호 전송</span>
			<label>
				<input type="text" name="emailAuthNum" placeholder="이메일로 보낸 인증번호를 입력해주세요.">
			</label>
				<span class="emailAuthCheck spanBtn">인증하기</span>
			<label>
				<span class="explanation">비밀번호</span>
				<input type="password" name="pw" placeholder="비밀번호를 입력해주세요.">
			</label>
			<label>
				<span class="explanation">비밀번호 확인</span>
				<input type="password" name="pwCheck" placeholder="비밀번호를 다시 입력해주세요.">
			</label>
			<label>
				<span class="explanation">별명</span>
				<input type="text" name="nickname" placeholder="별명을 입력해주세요.">
			</label>
				<span class="nickDuplCheck spanBtn">중복확인</span>
			<label>
				<span class="explanation">우편번호</span>
				<input type="text" name="zipCode" id="zipcode" placeholder="우편번호 검색 클릭" readonly>
			</label>
				<span class="zipCodeCheck spanBtn">우편번호 검색</span>
			<label>
				<span class="explanation">주소</span>
				<input type="text" name="address01" id="address01" placeholder="우편번호 검색 클릭" readonly>
			</label>
			<label>
				<span class="explanation">상세 주소</span>
				<input type="text" name="address02" id="address02" placeholder="상세 주소를 입력해주세요.">
			</label>
			
			<span class="registerBtn">회원가입</span>
		</form>
	</div>
	
	<%@ include file="../include/footer.jsp" %>
</body>
</html>