<%--
  Created by IntelliJ IDEA.
  User: yusuhyeon
  Date: 2023/01/03
  Time: 4:09 PM
  To change this template use File | Settings | File Templates.
--%>
<%@ page contentType="text/html;charset=UTF-8" language="java" %>
<html>
<head>
    <title>Title</title>
</head>
<body>
<form action="../lostAndFoundForm_process" method="post">
    <li><span> 별명</span> <input type="text" name="nickname" placeholder="별명"></li>
    <li><span> 새 비밀번호</span> <input type="password" name="password" placeholder=" 아이디"></li>
    <li><span> 비밀번호 확인</span> <input type="password" name="password_cf" placeholder=" 별명"></li>
    <button type="submit" value="비밀번호 수정">비밀번호 수정</button>
</form>
<div class="footerWrap page onePage6">
    <footer class="page8" id="footer">
        <p class="us">about us</p>
        <div id="membersWrap">
            <p class="member0_sh member_sh"><a href="#" target="_blank">개인정보처리방침</a></p>
            <p class="member1_sh member_sh"><a href="hyehyun.html" target="_blank">김혜현</a></p>
            <p class="member2_sh member_sh"><a href="beomsu.html" target="_blank">오범수</a></p>
            <p class="member3_sh member_sh"><a href="haejun.html" target="_blank">오해준</a></p>
            <p class="member4_sh member_sh"><a
                    href="https://suhyun980716.github.io/LandingPage/Landing%20Page/index.html"
                    target="_blank">유수현</a></p>
            <p class="member5_sh member_sh"><a href="https://github.com/hyeahyun2/TeamProject"
                                               target="_blank">GitHub</a></p>
        </div>
    </footer>
</div>
</body>
</html>
