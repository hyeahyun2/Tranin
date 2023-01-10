<%@ page language="java" contentType="text/html; charset=UTF-8"
    pageEncoding="UTF-8"%>
<%@ page import="java.sql.*" %>
<%@ page import="java.util.*" %>
<%@ page import="dao.DBProperty" %>
<%@ page import="dao.NoticeShowDao" %>
<%@ page import="dao.NoticeSearchDao" %>
<%@ page import="dto.NoticeDto" %>
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
      <form action="./searchNotice.jsp" method="get"><input type="text" name="searchText" size="47" placeholder="검색어를 입력하세요."><input type="submit" value="검색"></form>
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
        <%
        	NoticeShowDao dao = new NoticeShowDao();
        	List<NoticeDto> list = dao.showNotice();
		
			for(NoticeDto a : list) {
		%>
		  <tr>
            <td><%=a.getNoticeNo() %></td>
            <td><a href="./showNotice.jsp?id=<%=a.getNoticeNo()%>" role="button"><%=a.getTitle() %></a></td>
            <td><%=a.getRegDate() %></td>
          </tr>
		<% 		
			}
		%>
        <%-- 
          <tr>
            <td>10</td>
            <td><a href="#">트레닌 사이트가 새롭게 단장하였습니다.</a></td>
            <td>관리자</td>
            <td>2022-11-21</td>
            <td>30</td>
          </tr>
          <tr>
            <td>9</td>
            <td><a href="#">트레닌 사이트가 새롭게 단장하였습니다.</a></td>
            <td>관리자</td>
            <td>2022-09-03</td>
            <td>10</td>
          </tr>
          <tr>
            <td>8</td>
            <td><a href="#">트레닌 사이트가 새롭게 단장하였습니다.</a></td>
            <td>관리자</td>
            <td>2022-06-10</td>
            <td>2</td>
          </tr>
          <tr>
            <td>7</td>
            <td><a href="#">트레닌 사이트가 새롭게 단장하였습니다.</a></td>
            <td>관리자</td>
            <td>2022-04-30</td>
            <td>6</td>
          </tr>
          <tr>
            <td>6</td>
            <td><a href="#">트레닌 사이트가 새롭게 단장하였습니다.</a></td>
            <td>관리자</td>
            <td>2022-03-02</td>
            <td>50</td>
          </tr>
          <tr>
            <td>5</td>
            <td><a href="#">트레닌 사이트가 새롭게 단장하였습니다.</a></td>
            <td>관리자</td>
            <td>2022-03-03</td>
            <td>25</td>
          </tr>
          <tr>
            <td>4</td>
            <td><a href="#">트레닌 사이트가 새롭게 단장하였습니다.</a></td>
            <td>관리자</td>
            <td>2022-03-03</td>
            <td>52</td>
          </tr>
          <tr>
            <td>3</td>
            <td><a href="#">트레닌 사이트가 새롭게 단장하였습니다.</a></td>
            <td>관리자</td>
            <td>2022-02-19</td>
            <td>11</td>
          </tr>
          <tr>
            <td>2</td>
            <td><a href="#">트레닌 사이트가 새롭게 단장하였습니다.</a></td>
            <td>관리자</td>
            <td>2022-02-05</td>
            <td>56</td>
          </tr>
          <tr>
            <td>1</td>
            <td><a href="#">트레닌 사이트가 새롭게 단장하였습니다.</a></td>
            <td>관리자</td>
            <td>2022-01-27</td>
            <td>40</td>
          </tr>
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