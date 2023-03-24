<%@ page language="java" contentType="text/html; charset=UTF-8"
    pageEncoding="UTF-8"%>
<%@ page import="dao.NoticeDao" %>
<%@ page import="dto.NoticeDto" %>
<!DOCTYPE html>
<html>
<head>
<meta charset="UTF-8">
<meta http-equiv="X-UA-Compatible" content="IE=edge">
<meta name="viewport" content="width=device-width, initial-scale=1.0">
<title>공지사항</title>
<link rel="stylesheet" href="../assets/css/common.css">
<link rel="stylesheet" href="../assets/css/reset.css">
<link rel="stylesheet" href="../assets/css/showNotice.css">
<link rel="stylesheet" href="../assets/css/footer.css">
<script src="../assets/js/header.js" defer></script>
<script src="https://code.jquery.com/jquery-3.6.3.js" integrity="sha256-nQLuAZGRRcILA+6dMBOvcRh5Pe310sBpanc6+QBmyVM=" crossorigin="anonymous"></script>
<script>
		
        document.addEventListener('DOMContentLoaded',function (){
           
        	const button = document.getElementById("button");
            button.addEventListener('click',function (){
            	const noticeNo = $("#noticeNo").val();
            	if(confirm("정말 삭제하시겠습니까?")) { 		
     				window.location.href=`/deleteNoticeServlet?noticeNo=${noticeNo}`;
            		// yesdelete();
            		insertdelete();
            	} else {
            		alert("취소");
            	}
            });
           
       
   			/*
            function checkDelete() {
            	if(confirm("정말 삭제하시겠습니까?")) { 		
            		alert("승인");
            		insertdelete();
            		yesdelete();
            	} else {
            		alert("취소");
            	}
            }
            */
            /*
            function yesdelete() {
            	const noticeNo = $("#noticeNo").val();
                $.ajax({
                    url:"/deleteNoticeServlet",
                    method:"post",
                    data:{"noticeNo":noticeNo},
                    success:function(){
                        alert("성공")
                    },
                    error:function (){
                        alert("실패")
                    }
                });
            };
            */
            
            function insertdelete() {
             	const noticeNo = $("#noticeNo").val();
                const title = $("#title").val();
                const content = $("#content").val();
                $.ajax({
                    url:"/insertTodeleteDB",
                    method:"post",
                    data:{"noticeNo":noticeNo ,"title":title,"content":content},
                    success:function(){
                        alert("성공")
                        window.location.href="/notice.jsp";
                    },
                    error:function (){
                        alert("실패")
                    }
                });
            };
        });
</script>
</head>
<body>
<%@ include file="../include/header.jsp"%>
    <section id="showNoticeWrap_hj">
        <div id="showNoticeHeaderWrap_hj">
            <h2 class="showNoticeHeader_hj">공지 사항</h2>
            <p class="showNoticeP_hj">공지 내용을 확인하세요.</p>
        </div>
        <%
        	int noticeNo= Integer.parseInt(request.getParameter("noticeNo"));
        	NoticeDao dao = new NoticeDao();
        	ArrayList<NoticeDto> list = dao.getNotice(noticeNo);
        	
        	for(NoticeDto a : list) {
        %>
        <form>
        
        </form>
        	<p>제목<br><input type="text" name="title" size="50" value="<%=a.getTitle()%>" readonly></p>
        	<p>내용<br><textarea name="content" id="content" cols="48" rows="30" readonly><%=a.getContent() %></textarea></p>
        <form>
        	<input type="hidden" id="noticeNo" value="<%=noticeNo%>">
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
       	<%-- <a id="button" href="/deleteNoticeServlet?noticeNo=<%=noticeNo%>">삭제하기</a> --%>
       	<a id="button" style="cursor: pointer;">삭제하기</a>
       	<%
         }
       	%>
       	<%
       	String searchText = (String)session.getAttribute("searchText");
       	String pageNum = request.getParameter("pageNum");
       	if(searchText == null) { %>
       	<a href="./notice.jsp?pageNum=<%=pageNum%>">목록</a>
       	<% }
       	if (searchText != null) {
       	%>
       	<a href="./notice.jsp?searchText=<%=searchText%>">목록</a>
       	<% } %>
       	<%--
          }
       	--%>
    </section>
    <%@ include file="../include/footer.jsp"%>
</body>
</html>