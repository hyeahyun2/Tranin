<%@ page language="java" contentType="text/html; charset=UTF-8"
         pageEncoding="UTF-8" %>
<!DOCTYPE html>
<html>
<head>
    <link rel="stylesheet" href="../assets/css/reset.css">
    <link rel="stylesheet" href="../assets/css/footer.css">
    <link rel="stylesheet" href="../assets/css/common.css">
    <link rel="stylesheet" href="../assets/css/managerLogin.css">
    <meta charset="UTF-8">
    <title>Insert title here</title>
</head>
<body>
<div id="managerLoginWrap">
    <a href="../mainPage/welcome.jsp"><img src="../assets/image/mainLogo.png"></a>
    <h1>관리자 로그인</h1>
    <form action="../manageLogin" name="member_insert" id="form" method="post">
        <div>
            <ul>
                <li><span>아이디</span><input type="text" placeholder=" 아이디" name="memberId" id="id_sh"></li>
                <li><span>비밀번호</span><input type="password" placeholder=" 비밀번호" name="password" id="password_sh"></li>
                <li><input class="button-68" type="submit" value="로그인"></li>
            </ul>
        </div>
    </form>
</div>
</body>
</html>