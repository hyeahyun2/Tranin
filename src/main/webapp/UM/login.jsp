<%@ page language="java" contentType="text/html; charset=UTF-8"
         pageEncoding="UTF-8" %>
<%@ page import="dao.MemberSelectLoginDao" %>
<%@ page import="controller.loginFormServlet" %>
<!DOCTYPE html>
<html>
<head>
    <link rel="stylesheet" href="../assets/css/reset.css">
    <link rel="stylesheet" href="../assets/css/footer.css">
    <link rel="stylesheet" href="../assets/css/common.css">
    <link rel="stylesheet" href="../assets/css/login.css">
    <script src="../assets/js/login.js" defer></script>
    <meta charset="UTF-8">
    <title>Insert title here</title>
    <script>
        document.addEventListener("DOMContentLoaded", function(){
            const check = document.getElementById("loginChk");

            check.addEventListener("input", function(){
                if(check.checked){
                    alert("공공컴퓨터일 경우 이 옵션을 꺼주십시오.")
                }
            })
        })
    </script>
</head>
<body>
<%
    Cookie[] c = request.getCookies();
    if (c != null) {
        for (Cookie cf : c) {
            if (cf.getName().equals("id")) {
                response.sendRedirect("/mainPage/welcome.jsp");
            }
        }
    }
%>

<div id="logo_sh"><a href="../index.jsp" id="main"></a></div>
<div id="loginWrap_sh">
    <h1>로그인</h1>
    <div id="login_sh"></div>
    <form action="../loginFormServlet" name="member_insert" id="form" method="post">
        <div id="listWrap_sh">
            <ul id="list_sh">
                <li><span>아이디</span><input type="text" placeholder=" 아이디" name="memberId" id="id_sh"></li>
                <li>
                    <div class="check_sh check1"></div>
                </li>
                <li><span>비밀번호</span><input type="password" placeholder=" 비밀번호" name="password" id="password_sh"></li>
                <li>
                    <div class="check_sh check2"></div>
                </li>
                <div id="checkBox_sh">
                    <li>로그인 유지 <input type="checkbox" id="loginChk" name="loginChk" vlaue="true"></li>
                </div>
                <li><span>로그인</span><input type="submit" value="로그인"></li>
            </ul>
        </div>
    </form>
    <div id="linkList_sh">
        <a href="lostAndFound.jsp">비밀번호 찾기</a>
        <a href="registerFormServlet">회원가입</a>
    </div>
    <div id="alterLogin_sh">
        <div id="naver_sh">네이버 로그인</div>
        <div id="kakao_sh">카카오 로그인</div>
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
</body>
</html>