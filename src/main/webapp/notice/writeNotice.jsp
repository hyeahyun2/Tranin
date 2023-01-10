<%@ page language="java" contentType="text/html; charset=UTF-8"
    pageEncoding="UTF-8"%>
<!DOCTYPE html>
<html lang="ko">
<head>
    <meta charset="UTF-8">
    <meta http-equiv="X-UA-Compatible" content="IE=edge">
    <meta name="viewport" content="width=device-width, initial-scale=1.0">
    <title>공지사항 글쓰기</title>
</head>
<body>
	<%@ include file="header.jsp"%>
    <section>
        <div id="writeNoticeHeaderWrap_hj">
            <h2 class="writeNoticeHeader_hj">공지 사항 글쓰기</h2>
            <p>새로운 공지사항을 작성하세요.</p>
        </div>
        <form action="writeNoticeServlet" method="post">
        	<p> 글번호 <input type="hidden" name="index"> 
            <p>제목<br><input type="text" name="title" size="50" placeholder="제목을 입력하세요."></p>
            <p>내용<br><textarea name="content" cols="48" rows="30" placeholder="내용을 입력하세요."></textarea></p>
            <input type="submit" value="등록">  
        </form> 
        <a href="notice.jsp" id="goBack_hj">돌아가기</a>
          
    </section>
    <%@ include file="footer.jsp"%>
</body>
</html>