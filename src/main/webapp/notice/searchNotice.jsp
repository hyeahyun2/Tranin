<%@ page language="java" contentType="text/html; charset=UTF-8"
    pageEncoding="UTF-8"%>
<%@ page import="dao.NoticeSearchDao" %>
<%@ page import="dto.NoticeDto" %>
<%@ page import="java.util.*" %>

<!DOCTYPE html>
<html lang="en">
<head>
  <meta charset="UTF-8">
  <meta http-equiv="X-UA-Compatible" content="IE=edge">
  <meta name="viewport" content="width=device-width, initial-scale=1.0">
  <title>공지사항</title>
  <link rel="stylesheet" href="../assets/css/common.css">
  <link rel="stylesheet" href="../assets/css/reset.css">
  <link rel="stylesheet" href="../assets/css/notice.css">
  <link rel="stylesheet" href="../assets/css/footer.css">
  <script src="assets/js/common.js" defer></script>
  <script src="assets/js/header.js" defer></script>
</head>
<body>
<%@ include file="../include/header.jsp"%>
  <section id="noticeWrap_hj">
    <div id="headerWrap_hj">
      <h2 class="header_hj">공지사항</h2>
      <form action=""><input type="text" name="searchText" size="47" placeholder="검색어를 입력하세요."><input type="submit" value="검색"></form>
    </div>
    <div id="board_hj">
      <table class="table_hj">
        <thead class="thead_hj">
          <tr>
            <td>글번호</td>
            <td>제목</td>
           <!-- <td>작성자</td> --> 
            <td>작성일</td>
           <!-- <td>조회수</td> -->
          </tr>
        </thead>
        <tbody class="tbody_hj">		
		<%--
		
			String searchText = request.getParameter("searchText");
			NoticeSearchDao dao1 = new NoticeSearchDao();
			// ArrayList<NoticeDto> list1 = dao1.getSearch(searchText);
			if (list1.size() == 0) {
						out.println("<td></td>");				
			   	      	out.println("<td>검색하신 게시물이 없습니다.</td>");
			   	     	out.println("<td></td>");
			} 
			if (list1.size() > 0) {		
		        for(NoticeDto a : list1) {
		
			<tr>
           		 <td><%=a.getNoticeNo() %></td>
            	 <td><a href="./showNotice.jsp?id=<%=a.getNoticeNo()%>"><%=a.getTitle() %></a></td>
            	 <td><%=a.getRegDate() %></td>
            </tr>
        --%> 
        <%--
        		}
        	}
		--%>
        </tbody>
      </table>
    </div>
    <div id="pagingWrap_hj" >
      <ul class="pagingUl_hj">
        <li><a href="#">5페이지앞으로</a></li>
        <li><a href="#">앞으로</a></li>
        <li><a href="#" class="num">1</a></li>
        <li><a href="#" class="num">2</a></li>
        <li><a href="#" class="num">3</a></li>
        <li><a href="#" class="num">4</a></li>
        <li><a href="#" class="num">5</a></li>
        <li><a href="#">뒤로</a></li>
        <li><a href="#">5페이지뒤로</a></li>
      </ul>
    </div>
    <div>
    	<a href="./writeNotice.jsp">글쓰기</a>
    </div>
  </section>
 <%@ include file="../include/footer.jsp"%>
  
</body>
</html>