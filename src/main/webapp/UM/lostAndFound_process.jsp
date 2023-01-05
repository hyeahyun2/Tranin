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
    <link rel="stylesheet" href="../assets/css/reset.css">
    <link rel="stylesheet" href="../assets/css/footer.css">
    <link rel="stylesheet" href="../assets/css/common.css">
    <link rel="stylesheet" href="../assets/css/lostAndFound_process.css">
    <title>Title</title>
</head>
<body>
<%
    String nickname = null;
    nickname = (String)session.getAttribute("passChecked");
    if(nickname != null){
%>
<div id="wrap">
    <div id="logo">
        <div id="formWrap">
            <form action="../lostAndFoundForm_process" method="post">
                <h1>새 비밀번호 확인</h1>
                <ul id="forms_sh">
                    <input type="hidden" name="nickname" placeholder=" 아이디" id="Id" value="<%=nickname%>">
                    <li><p>새 비밀번호</p><br><input type="password" name="password" placeholder=" 새로운 비밀번호를 입력하세요"></li>
                    <li><p>비밀번호 확인</p><br><input type="password" name="password_cf" placeholder=" 비밀번호 확인"></li>
                    <button type="submit" value="비밀번호 수정" id="btn">비밀번호 수정</button>
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
                    href="https://suhyun980716.github.io/LandingPage/Landing%20Page/index.html" target="_blank">유수현</a>
            </p>
            <p class="member5_sh member_sh"><a href="https://github.com/hyeahyun2/TeamProject"
                                               target="_blank">GitHub</a></p>
        </div>
    </footer>
</div>
</div>
<%
    }
%>

</body>
</html>
