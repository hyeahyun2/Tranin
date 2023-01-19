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
    <script src="https://code.jquery.com/jquery-3.6.3.js" integrity="sha256-nQLuAZGRRcILA+6dMBOvcRh5Pe310sBpanc6+QBmyVM=" crossorigin="anonymous"></script>
    <script>
        document.addEventListener('DOMContentLoaded',function (){
            const button = document.getElementById("button");
            button.addEventListener('click',function (){
                const title = $("#title").val();
                const content = $("#content").val();
                $.ajax({
                    url:"/insertTodeleteDB",
                    method:"post",
                    data:{"title":title,"content":content},
                    success:function(){
                        alert("성공")
                    },
                    error:function (){
                        alert("실패")
                    }
                })
            })
        })
    </script>
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
        <form>
            <input type="hidden" id="title" value="<%=a.getTitle()%>">
            <input type="hidden" id="content" value="<%=a.getContent()%>">
        </form>
        <%
        	}
        %>
        <%
         if(manager != null){
        %>
       	<a href="./updateNotice.jsp?noticeNo=<%=noticeNo%>">수정하기</a>
       	<a id="button" href="/deleteNoticeServlet?notice_no=<%=noticeNo%>">삭제하기</a>
       	<a href="./notice.jsp">목록</a>
       	<%
          }
       	%>
    </section>
    <%@ include file="../include/footer.jsp"%>
</body>
</html>