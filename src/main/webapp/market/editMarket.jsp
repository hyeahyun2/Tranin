<%@ page language="java" contentType="text/html; charset=UTF-8"
    pageEncoding="UTF-8"%>
<%@ page import="dto.MarketDto" %>
<!DOCTYPE html>
<html>
<head>
<meta charset="UTF-8">
<title>장터 글쓰기</title>
<%@ include file="../include/frontStyle.jsp"%>
<link rel="stylesheet" href="../assets/css/writeMarket.css?v=<%= System.currentTimeMillis() %>">
<script src="../assets/js/writeMarket.js?v=<%= System.currentTimeMillis() %>" defer></script>
</head>
<body>
	<%@ include file="../include/header.jsp"%>
	<%
	MarketDto post = (MarketDto)request.getAttribute("post");
	%>
	<div id="content">
		<h1>
			<a href="./market.jsp">
			<%
			if(post.getPart().equals("sell")){
				out.print("판매");
			}
			else if(post.getPart().equals("buy")){
				out.print("구매");
			}
			%>
			글 쓰기
			</a>
		</h1>
		
		<div id="contentWrap">
			<form action="/marketPostEdit" name="editPost" id="formId" method="post" enctype="multipart/form-data">
				<input type="hidden" name="part" value="<%= post.getPart() %>">
				<input type="hidden" name="oldMarketNo" value="<%= post.getMarketNo() %>">
				<input type="hidden" name="writeID" value="<%= memberId %>">
				<div>
					<label for="title">제목</label>
					<input type="text" id="title" name="title" placeholder="제목을 입력하세요." value="<%= post.getTitle() %>">
				</div>
				<div>
					<label for="price"><% if(post.getPart().equals("buy")) out.print("희망"); %>가격</label>
					<input type="text" id="price" name="price" placeholder="<% if(post.getPart().equals("buy")) out.print("희망"); %>가격을 입력하세요.(숫자만 입력)" value="<%= post.getPrice() %>"> 원
				</div>
				<div>
					<label for="postContent">글 내용</label>
					<textarea id="postContent" name="content" cols="100" rows="30" placeholder="글 내용을 입력하시오"><%= post.getContent() %></textarea>
				</div>
				<div>
					<label for="btnAtt">사진</label>
		      <input type="hidden" name="removeImg_load">
					<input type='file' id='btnAtt' name="image" multiple='multiple' />
			    <div id='att_zone'
			      data-placeholder='파일을 첨부 하려면 파일 선택 버튼을 클릭하거나 파일을 드래그앤드롭 하세요'>
			      <%
	         	for(String imgUrl : post.getImage()){
	         		if(imgUrl != null){ // 이미지가 존재하면
	         			String fileName = imgUrl.substring(imgUrl.lastIndexOf("/") + 1);
	          %>
	          <div class="imgDiv imgDiv_load">
          		<img class="imgImg imgImg_load" src="/resources/images/<%= fileName %>"  alt="<%= fileName %>">
	          	<input type="button" value="x" delfile="<%= fileName %>" class="imgCheck imgCheck_load">
	          </div>
	          <%
	         		}
	         	}
			      %>
			    </div>
				</div>
				<div>
					<button type="button" id="submitBtn">등록하기</button>
				</div>
			</form>
		</div>
	</div>
</body>
</html>