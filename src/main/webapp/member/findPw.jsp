<%@ page language="java" contentType="text/html; charset=UTF-8"
    pageEncoding="UTF-8"%>
<!DOCTYPE html>
<html>
<head>
<meta charset="UTF-8">
<title>비밀번호 찾기</title>
<link rel="stylesheet" href="../assets/css/reset.css" />
<link rel="stylesheet" href="../assets/css/findPw.css" />
<script src="../assets/js/findPw.js?v=2" defer></script>
</head>
<body>
	<div id="container">
		<h1 class="logo">비밀번호 찾기</h1>
		<form name="findPwForm" id="findPwForm" method="post">
			<input type="text" name="id" placeholder="이메일을 입력해주세요">
			<span class="emailAuthBtn">인증 번호 전송</span>
			<input type="text" name="emailAuthNum" placeholder="인증번호를 입력해주세요">
			<span class="emailAuthCheck">인증하기</span>
		</form>
	</div>
</body>
</html>