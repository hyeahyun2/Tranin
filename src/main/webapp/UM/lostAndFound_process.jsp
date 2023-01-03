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
    <li><span> 별명</span> <input type="text" name="nickname" placeholder="별명" ></li>
    <li><span> 새 비밀번호</span> <input type="password" name="password" placeholder=" 아이디" ></li>
    <li><span> 비밀번호 확인</span> <input type="password" name="password_cf"  placeholder=" 별명"></li>
    <button type="submit" value="비밀번호 수정" >비밀번호 수정</button>
  </form>
  </body>
</html>
