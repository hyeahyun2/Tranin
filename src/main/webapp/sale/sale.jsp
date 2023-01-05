<%@ page language="java" contentType="text/html; charset=UTF-8"
	pageEncoding="UTF-8"%>
<!DOCTYPE html>
<html>
<head>
<meta charset="UTF-8">
<title>장터</title>
<link rel="preconnect" href="https://fonts.googleapis.com" />
<link rel="preconnect" href="https://fonts.gstatic.com" crossorigin />
<link
	href="https://fonts.googleapis.com/css2?family=Lato:ital,wght@0,100;0,300;0,400;0,700;0,900;1,400&family=Noto+Sans+KR:wght@100;300;400;500;700;900&display=swap"
	rel="stylesheet" />
<link rel="stylesheet" href="../assets/css/common.css">
<link rel="stylesheet" href="../assets/css/reset.css">
<link rel="stylesheet" href="../assets/css/sale.css">
<link rel="stylesheet" href="../assets/css/footer.css">
<script type="text/javascript" src="../assets/js/jquery-3.6.1.js"></script>
<script type="text/javascript"
	src="assets/js/jquery-migrate-1.4.1.min.js"></script>
<script type="text/javascript" src="../assets/js/jquery-ui.js"></script>
<script src="../assets/js/sale.js" defer></script>
<script src="../assets/js/header.js" defer></script>
</head>
<body>
	<%@ include file="../include/header.jsp"%>
	<div id="content">
		<h1>
			<a href="./sale.jsp">장터</a>
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
					<li class="select">팝니다</li>
					<li>삽니다</li>
				</ul>
			</div>
			<div id="posts">
				<!-- ul태그(./saleList.html) 삽입 부분 -->
			</div>
			<div class="moreBtn">더보기</div>
		</div>
	</div>
	<%@ include file="../include/floatingMenu.jsp"%>
	<!-- write & top button -->
	<div id="fixedBtn">
		<a href="#" class="writeNow">글쓰기</a> <a href="#" class="topBtn">top</a>
	</div>
	<%@ include file="../include/footer.jsp"%>
</body>
</html>