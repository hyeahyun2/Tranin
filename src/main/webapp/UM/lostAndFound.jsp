<%--
  Created by IntelliJ IDEA.
  User: yusuhyeon
  Date: 2023/01/03
  Time: 3:28 PM
  To change this template use File | Settings | File Templates.
--%>
<%@ page contentType="text/html;charset=UTF-8" language="java" %>
<%@ taglib prefix="c" uri="http://java.sun.com/jstl/core_rt" %>
<html>
<head>
    <link rel="stylesheet" href="../assets/css/reset.css">
    <link rel="stylesheet" href="../assets/css/footer.css">
    <link rel="stylesheet" href="../assets/css/common.css">
    <link rel="stylesheet" href="../assets/css/lostAndFound.css">
    <title>Title</title>
</head>
<body>
<div id="wrap">
    <div id="logo">
        <div id="formWrap">
            <form action="../lostAndFoundForm" method="post">
                <h1>비밀번호 찾기</h1>
                <ul id="forms_sh">
                    <li id="idWrap"><p>아이디</p><br><input type="text" name="memberId" placeholder=" 아이디" id="Id" ></li>
                    <li><p>별명</p><br><input type="text" name="nickName"  placeholder=" 별명" id="nickname"></li>
                    <button type="submit" value="비밀번호 찾기" id="btn" >비밀번호 찾기</button>
                    <hr>
                </ul>
            </form>
        </div>
    </div>
</div>
<div class="footerWrap page onePage6">
    <footer class="page8" id="footer">
        <p class="us">about us</p>
        <div id="membersWrap">
            <p class="member0_sh member_sh"><a href="#" target="_blank">개인정보처리방침</a></p>
            <p class="member1_sh member_sh"><a href="hyehyun.html" target="_blank">김혜현</a></p>
            <p class="member2_sh member_sh"><a href="beomsu.html" target="_blank">오범수</a></p>
            <p class="member3_sh member_sh"><a href="haejun.html" target="_blank">오해준</a></p>
            <p class="member4_sh member_sh"><a
                    href="/include/suhyun.jsp" target="_blank">유수현</a>
            </p>
            <p class="member5_sh member_sh"><a href="https://github.com/hyeahyun2/TeamProject"
                                               target="_blank">GitHub</a></p>
        </div>
    </footer>
</div>
</body>
</html>
