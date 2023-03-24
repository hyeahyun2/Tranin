<%@ page language="java" contentType="text/html; charset=UTF-8"
	pageEncoding="UTF-8"%>
<!DOCTYPE html>
<html>
<head>
<meta charset="UTF-8">
<title>장터</title>
<%@ include file="../include/frontStyle.jsp"%>
<link rel="stylesheet" href="../assets/css/market.css?v=2">
<script src="../assets/js/market.js?v=5" defer></script>
</head>
<%
String searchKey = request.getParameter("searchKey");
String part = request.getParameter("part");
%>
<body>
	<%@ include file="../include/header.jsp"%>
	<div id="content">
		<h1>
			<a href="./market.jsp?part=<%= part %>">장터</a>
		</h1>
		<div id="contentWrap">
			<div id="selectWrap">
				<form action="#" name="search" id="inputSearch" method="get">
					<input type="text" name="searchText" class="searchText"
						placeholder="search" <%if(searchKey != null){ out.print("value=\""+ searchKey +"\""); }%>> 
					<input type="button" name="searchBtn" class="submitBtn">
					<a href="https://www.flaticon.com/kr/free-icons/" title="확대경 아이콘">확대경
						아이콘 제작자: Freepik - Flaticon</a>
				</form>
				<ul id="array">
					<li class="<%if(part.equals("sell")){ out.print("select"); }%> sell">팝니다</li>
					<li class="<%if(part.equals("buy")){ out.print("select"); }%> buy">삽니다</li>
				</ul>
			</div>
			<div id="posts">
				<!-- ul태그(./marketList.html) 삽입 부분 -->
			</div>
			<div class="moreBtn">더보기</div>
		</div>
	</div>
	<%@ include file="../include/floatingMenu.jsp"%>
	<!-- write & top button -->
	<div id="fixedBtn">
		<%
		if(memberId != null){
		%>
		<a href="./writeMarket.jsp?part=sell" class="writeNow">판매 글쓰기</a>
		<a href="./writeMarket.jsp?part=buy" class="writeNow">구매 글쓰기</a>
		<%
		}
		%>
		<a href="#" class="topBtn">top</a>
	</div>
	<%@ include file="../include/footer.jsp"%>
</body>
</html>