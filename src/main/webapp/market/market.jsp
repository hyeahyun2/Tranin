<%@ page language="java" contentType="text/html; charset=UTF-8"
	pageEncoding="UTF-8"%>
<!DOCTYPE html>
<html>
<head>
<meta charset="UTF-8">
<title>장터</title>
<%@ include file="../include/frontStyle.jsp"%>
<link rel="stylesheet" href="../assets/css/market.css?v=<%= System.currentTimeMillis() %>">
<script src="../assets/js/market.js?v=<%= System.currentTimeMillis() %>" defer></script>
</head>

<body>
	<%@ include file="../include/header.jsp"%>
	<div id="content">
		<h1>
			<a href="./market.jsp">장터</a>
		</h1>
		<div id="contentWrap">
			<div id="selectWrap">
				<form action="#" name="search" id="inputSearch" method="get">
					<input type="text" name="searchText" class="searchText"
						placeholder="search"> <input type="submit" class="submit">
					<a href="https://www.flaticon.com/kr/free-icons/" title="확대경 아이콘">확대경
						아이콘 제작자: Freepik - Flaticon</a>
				</form>
				<div id=category>
					<p>카테고리</p>
					<ul class="goryList">
						<li>전체</li>
						<li>스위치</li>
						<li>타이틀</li>
						<!-- <li>구형모델</li> -->
						<li>액세서리</li>
					</ul>
				</div>
				<ul id="array">
					<li class="select sell">팝니다</li>
					<li class="buy">삽니다</li>
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