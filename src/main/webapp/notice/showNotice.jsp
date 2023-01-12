<%@ page language="java" contentType="text/html; charset=UTF-8"
    pageEncoding="UTF-8"%>
<%@ page import="dao.NoticeSelectDao" %>
<%@ page import="dto.NoticeDto" %>
<!DOCTYPE html>
<html>
<head>
<meta charset="UTF-8">
<title>공지사항</title>
<link rel="stylesheet" href="../assets/css/common.css">
<link rel="stylesheet" href="../assets/css/reset.css">
<link rel="stylesheet" href="../assets/css/notice.css">
<link rel="stylesheet" href="../assets/css/footer.css">
<link rel="stylesheet" href="../assets/css/showNotice.css">
<script src="assets/js/common.js" defer></script>
<script src="assets/js/header.js" defer></script>
</head>
<body>
	<%@ include file="../include/header.jsp"%>
    <section id="showNoticeWrap_hj">
        <div id="showNoticeHeaderWrap_hj">
            <h2 class="showNoticeHeader_hj">공지 사항</h2>
            <p>공지 내용을 확인하세요.</p>
        </div>
        <%
        	int noticeNo= Integer.parseInt(request.getParameter("id"));
        	NoticeSelectDao dao = new NoticeSelectDao();
        	ArrayList<NoticeDto> list = dao.getNotice(noticeNo);
        	
        	for(NoticeDto a : list) {
        %>
        제목 : <p><%=a.getTitle()%></p>
        내용 : <p><%=a.getContent() %></p>
        <%
        	}
        %>
        <%
          if(manager != null) {
        %>
       	<a href="./updateNotice.jsp?noticeNo=<%=noticeNo%>">수정하기</a>
       	<a href="#">삭제하기</a>
       	<a href="./notice.jsp">목록</a>
       	<%
          }
       	%>
    </section>
    <%@ include file="../include/footer.jsp"%>
</body>
</html>