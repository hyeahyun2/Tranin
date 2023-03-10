<%@ page language="java" contentType="text/html; charset=UTF-8"
    pageEncoding="UTF-8"%>
<!DOCTYPE html>
<html>
<head>
<meta charset="UTF-8">
<title>장터 글쓰기</title>
<%@ include file="../include/frontStyle.jsp"%>
<link rel="stylesheet" href="../assets/css/writeMarket.css?v=<%= System.currentTimeMillis() %>">
<script src="../assets/js/writeMarket.js?v=3" defer></script>
</head>
<body>
	<%@ include file="../include/header.jsp"%>
	<%
	String part = request.getParameter("part");
	%>
	<div id="content">
		<h1>
			<a href="/market/market.jsp?part=<%= part %>">
			<%
			if(part.equals("sell")){
				out.print("판매");
			}
			else if(part.equals("buy")){
				out.print("구매");
			}
			%>
			글 쓰기
			</a>
		</h1>
		
		<div id="contentWrap">
			<form action="../marketPostInsertServlet" id="formId" name="insertPost" method="post" enctype="multipart/form-data">
				<input type="hidden" name="part" value="<%= part %>">
				<input type="hidden" name="writeID" value="<%= memberId %>">
				<div>
					<label for="title" class="title">제목</label>
					<input type="text" id="title" name="title" placeholder="제목을 입력하세요.">
				</div>
				<div>
					<label for="price"><% if(part.equals("buy")) out.print("희망"); %>가격</label>
					<input type="text" id="price" name="price" placeholder="<% if(part.equals("buy")) out.print("희망"); %>가격을 입력하세요."> 원
				</div>
				<div>
					<textarea id="postContent" name="content" cols="100" rows="30" placeholder="글 내용을 입력하시오"></textarea>
				</div>
				<div class="imgWrap">
					<label for="btnAtt" class="btnAtt">사진</label>
					<input type='file' id='btnAtt' name="image" multiple='multiple' />
			    <div id='att_zone'
			      data-placeholder='파일을 첨부 하려면 파일 선택 버튼을 클릭하거나 파일을 드래그앤드롭 하세요'>
			    </div>
				</div>
				<div>
					<button type="button" id="submitBtn">등록하기</button>
				</div>
			</form>
		</div>
	</div>
	<%@ include file="../include/footer.jsp"%>
</body>
</html>