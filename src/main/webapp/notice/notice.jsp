<%@ page language="java" contentType="text/html; charset=UTF-8"
    pageEncoding="UTF-8"%>
<%@ page import="java.sql.*" %>
<%@ page import="java.util.*" %>
<%@ page import="dao.DBProperty" %>
<%@ page import="dao.NoticeShowDao" %>
<%@ page import="dao.NoticeSearchDao" %>
<%@ page import="dto.NoticeDto" %>
<%@ page import="dao.NoticePagingDao" %>

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
  <script src="../assets/js/common.js" defer></script>
  <script src="../assets/js/header.js" defer></script>
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
	        int pageNum = 1; // 페이지 번호가 전달이 안되면 1페이지
			if(request.getParameter("pageNum") != null) { // 페이지 번호가 전달이 된 경우
				pageNum = Integer.parseInt(request.getParameter("pageNum"));					
			}
        	List<NoticeDto> list = dao.showNotice(pageNum);
			
        	
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
    <div id="pagingWrap_hj">
    	<%
    	int cntListPerPage = 10;
   
    	NoticePagingDao npd = new NoticePagingDao();
		ResultSet rs = npd.getAllNotice();
		rs.next();
		int totalRecord = rs.getInt(1);
		int totalPage = (totalRecord % cntListPerPage == 0) ? totalRecord / cntListPerPage : (totalRecord / cntListPerPage) + 1;
    	
		int block = 5; // 페이지 나올 범위
		int blockTotal = totalPage % block == 0 ? totalPage / block : totalPage / block + 1; // 총 블럭의 수
		int blockThis = ((pageNum - 1) / block) + 1; // 현재 페이지의 블럭
		int blockThisFirstPage = ((blockThis - 1) * block) + 1; // 현재 블럭의 첫 페이지
		int blockThisLastPage = blockThis * block; // 현재 블럭의 마지막 페이지
		out.println(blockThisLastPage);
		blockThisLastPage = (blockThisLastPage > totalPage) ? totalPage : blockThisLastPage; 
    	%>
        <a href="notice.jsp?pageNum=1">첫 페이지</a>
        <% 
        if (blockThis > 1) {
        %>
        <a href="notice.jsp?pageNum=<%=(blockThisFirstPage - 1)%>">앞으로</a>
        <% 
        }
        
       	for(int i = blockThisFirstPage; i <= blockThisLastPage; i++) {
        %>
        <a href="notice.jsp?pageNum=<%=i%>" class="num"><%=i%></a>
        <%
        }
        %>
        <%-- 
        <li><a href="#" class="num">2</a></li>
        <li><a href="#" class="num">3</a></li>
        <li><a href="#" class="num">4</a></li>
        <li><a href="#" class="num">5</a></li>
        --%>
        <%
        if(blockThis < blockTotal) {
        %>
        <a href="notice.jsp?pageNum=<%=(blockThisLastPage + 1)%>">뒤로</a>
        <%
        }
        %>
        <a href="notice.jsp?pageNum=<%=totalPage%>">마지막 페이지</a>
    </div>
      <%
          if(manager != null) {
      %>
    <div>
    	<a href="./writeNotice.jsp">글쓰기</a>
    </div>
      <%
          }
      %>
  </section>
 <%@ include file="../include/footer.jsp"%>
  
</body>
</html>