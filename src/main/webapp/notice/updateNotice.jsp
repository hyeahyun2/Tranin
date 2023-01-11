<%@ page language="java" contentType="text/html; charset=UTF-8"
    pageEncoding="UTF-8"%>
<%@ page import="dao.NoticeSelectDao" %>
<%@ page import="dto.NoticeDto" %>
<!DOCTYPE html>
<html lang="ko">
<head>
    <meta charset="UTF-8">
    <meta http-equiv="X-UA-Compatible" content="IE=edge">
    <meta name="viewport" content="width=device-width, initial-scale=1.0">
    <title>공지사항 글쓰기</title>
    <link rel="stylesheet" href="../assets/css/common.css">
	<link rel="stylesheet" href="../assets/css/reset.css">
	<link rel="stylesheet" href="../assets/css/notice.css">
	<link rel="stylesheet" href="../assets/css/footer.css">
	<link rel="stylesheet" href="../assets/css/updateNotice.css">	
	<script src="assets/js/common.js" defer></script>
	<script src="assets/js/header.js" defer></script>
</head>
<body>
	<%@ include file="../include/header.jsp"%>
    <section id="updateNoticeWrap_hj">
        <div id="writeNoticeHeaderWrap_hj">
            <h2 class="writeNoticeHeader_hj">공지 사항 수정</h2>
            <p>수정될 내용을 작성하세요.</p>
        </div>
        <%
	        int noticeNo= Integer.parseInt(request.getParameter("noticeNo"));
	    	NoticeSelectDao dao = new NoticeSelectDao();
	    	ArrayList<NoticeDto> list = dao.getNotice(noticeNo);
	    	
	    	for(NoticeDto a : list) {
        %>
        <form action="../updateNoticeServlet" method="post">
        	<p><input type="hidden" name="noticeNo" value="<%=a.getNoticeNo()%>"> 
            <p>제목<br><input type="text" name="title" size="50" value="<%=a.getTitle()%>"></p>
            <p>내용<br><textarea name="content" cols="48" rows="30"><%=a.getContent() %></textarea></p>
            <input type="submit" value="수정">  
        </form> 
        <%
	    	}
        %>
        <a href="notice.jsp" id="goBack_hj">돌아가기</a>
    </section>
    <%@ include file="../include/footer.jsp"%>
</body>
</html>