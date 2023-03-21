<%@ page language="java" contentType="text/html; charset=UTF-8"
    pageEncoding="UTF-8"%>
<%@ page import="java.sql.*" %>
<%@ page import="java.util.*" %>
<%@ page import="dao.DBProperty" %>
<%@ page import="dao.NoticeDao" %>
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
  <script src="../assets/js/header.js" defer></script>
</head>
<body>
<%@ include file="../include/header.jsp"%>
  <section id="noticeWrap_hj">
    <div id="headerWrap_hj">
      <h2 class="header_hj">공지사항</h2>
      <form action="./notice.jsp" method="get">
        <input type="search" name="searchText" size="47" placeholder="검색어를 입력하세요.">
      	<input type="submit" value="검색">
      	<input type="button" value="취소" onclick="document.location.href='./notice.jsp'">
      </form>
      <%
      	request.setCharacterEncoding("UTF-8");
        String search = request.getParameter("searchText");
        session.setAttribute("searchText", search);
      %> 
    </div>
    <div id="board_hj">
      <table class="table_hj">
        <thead class="thead_hj">
          <tr>
            <td class="theadNo">글번호</td>
            <td class="theadTitle">제목</td>
           <!-- <td>작성자</td> --> 
            <td class="theadDate">작성일</td>
           <!-- <td>조회수</td> -->
          </tr>
        </thead>
        <tbody class="tbody_hj">
        <%
        	String searchText = request.getParameter("searchText");
        
        	boolean isSearch = searchText != null;
        	
        	ArrayList<NoticeDto> list = null;
        	
        	int pageNum = 1; // 페이지 번호가 전달이 안되면 1페이지
			if(request.getParameter("pageNum") != null) { // 페이지 번호가 전달이 된 경우
				pageNum = Integer.parseInt(request.getParameter("pageNum"));					
			}
			NoticeDao dao = new NoticeDao();
        	if (isSearch) {
    			list = dao.getSearch(searchText, pageNum);
    			if(list.size() == 0) {
    				out.println("<tr>");
    				out.println("<td></td>");
    				out.println("<td>검색된 게시물이 없습니다.</td>");
    				out.println("<td></td>");
    				out.println("</tr>");
    			}
        	}
        	else {
	        	list = dao.showNotice(pageNum);        		
        	}
			
        	
			for(NoticeDto a : list) {
		%>
		  <tr>
            <td align="center" valign="middle" class="noticeNo"><%=a.getNoticeNo() %></td>
            <td align="center" valign="middle" class="noticeTitle"><a href="./showNotice.jsp?noticeNo=<%=a.getNoticeNo()%>" role="button"><%=a.getTitle() %></a></td>
            <td align="center" valign="middle" class="noticeDate"><%=a.getRegDate() %></td>
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
   		
    	NoticeDao npd = new NoticeDao();
    	ResultSet rs = null;
    	if (isSearch) {
  			rs = npd.getSearchNotice(searchText);  		
    	} else {
			rs = npd.getAllNotice();    		
    	}
		rs.next();
		int totalRecord = rs.getInt(1);
		int totalPage = (totalRecord % cntListPerPage == 0) ? totalRecord / cntListPerPage : (totalRecord / cntListPerPage) + 1;
    	
		int block = 5; // 페이지 나올 범위
		int blockTotal = totalPage % block == 0 ? totalPage / block : totalPage / block + 1; // 총 블럭의 수
		int blockThis = ((pageNum - 1) / block) + 1; // 현재 페이지의 블럭
		int blockThisFirstPage = ((blockThis - 1) * block) + 1; // 현재 블럭의 첫 페이지
		int blockThisLastPage = blockThis * block; // 현재 블럭의 마지막 페이지
		// out.println(blockThisLastPage);
		blockThisLastPage = (blockThisLastPage > totalPage) ? totalPage : blockThisLastPage;
		if(isSearch) {
    	%>
	        <a href="notice.jsp?pageNum=1&searchText=<%=searchText%>" class="firstPage_hj">첫 페이지</a>
	        <% 
	        if (blockThis > 1) {
	        %>
	        <a href="notice.jsp?pageNum=<%=(blockThisFirstPage - 1)%>&searchText=<%=searchText%>" class="prevPage_hj">앞으로</a>
	        <% 
	        }
	        
	       	for(int i = blockThisFirstPage; i <= blockThisLastPage; i++) {
	        %>
	        <a href="notice.jsp?pageNum=<%=i%>&searchText=<%=searchText%>" class="num"><%=i%></a>
	        <%
	        }
	        if(blockThis < blockTotal) {
	        %>
	        <a href="notice.jsp?pageNum=<%=(blockThisLastPage + 1)%>&searchText=<%=searchText%>" class="nextPage_hj">뒤로</a>
	        <%
	        }
	        %>
	        <a href="notice.jsp?pageNum=<%=totalPage%>&searchText=<%=searchText%>" class="lastPage_hj">마지막 페이지</a>
        <%
		}
		else {
        %>
           <a href="notice.jsp?pageNum=1" class="firstPage_hj">첫 페이지</a>
	        <% 
	        if (blockThis > 1) {
	        %>
	        <a href="notice.jsp?pageNum=<%=(blockThisFirstPage - 1)%>" class="prevPage_hj">앞으로</a>
	        <% 
	        }
	        
	       	for(int i = blockThisFirstPage; i <= blockThisLastPage; i++) {
	        %>
	        <a href="notice.jsp?pageNum=<%=i%>" class="num"><%=i%></a>
	        <%
	        }
	        %>
	        <%
	        if(blockThis < blockTotal) {
	        %>
	        <a href="notice.jsp?pageNum=<%=(blockThisLastPage + 1)%>" class="nextPage_hj">뒤로</a>
	        <%
	        }
	        %>
	        <a href="notice.jsp?pageNum=<%=totalPage%>" class="lastPage_hj">마지막 페이지</a>
        <%
		}
        %>
        <%-- 
        <li><a href="#" class="num">2</a></li>
        <li><a href="#" class="num">3</a></li>
        <li><a href="#" class="num">4</a></li>
        <li><a href="#" class="num">5</a></li>
        --%>
    </div>
       <%
          if(manager != null) {
       %>
    <div>
    	<a href="./writeNotice.jsp" class="writeNotice_hj">글쓰기</a>
    </div>
    <%
    	}
    %>
  </section>
 <%@ include file="../include/footer.jsp"%>
 <script type="text/javascript">
	 var num = document.getElementsByClassName("num");
	
	 function handleClick(event) {
	   console.log(event.target);
	   // console.log(this);
	   // 콘솔창을 보면 둘다 동일한 값이 나온다
	
	   console.log(event.target.classList);
	
	   if (event.target.classList[1] === "clicked") {
	     event.target.classList.remove("clicked");
	   } else {
	     for (var i = 0; i < num.length; i++) {
	       num[i].classList.remove("clicked");
	     }
	
	     event.target.classList.add("clicked");
	   }
	 }
	
	 function init() {
	   for (var i = 0; i < num.length; i++) {
	     num[i].addEventListener("click", handleClick);
	     console.log(i);
	   }
	 }
 </script>
  
</body>
</html>