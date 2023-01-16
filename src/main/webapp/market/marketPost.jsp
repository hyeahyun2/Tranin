<%@ page language="java" contentType="text/html; charset=UTF-8"
    pageEncoding="UTF-8"%>
<%@ page import="dto.MarketDto" %>
<!DOCTYPE html>
<html>
<head>
<meta charset="UTF-8">
<title>marketPost</title>
<%@ include file="../include/frontStyle.jsp"%>
<link rel="stylesheet" href="../assets/css/marketPost.css?v=<%= System.currentTimeMillis() %>">
</head>
<body>
	<%@ include file="../include/header.jsp"%>
	<%
	MarketDto post = (MarketDto)request.getAttribute("post");
	String writerNick = (String)request.getAttribute("writerNick");
	String writerId = (String)request.getAttribute("writerId");
	%>
	<!-- contents -->
  <div id="content">
    <h1><a href="./sale.html">팝니다</a></h1>
    <div id="contentWrap">
      <div class="postImg">
        <ul class="mainImg">
          <!-- 상품 이미지 개수 유동적으로 변화 -->
          <li class="checkImg"><img src="#" alt="상품이미지1"></li>
          <li><img src="#" alt="상품이미지2"></li>
          <li><img src="#" alt="상품이미지3"></li>
          <li><img src="#" alt="상품이미지4"></li>
          <li><img src="#" alt="상품이미지5"></li>
        </ul>
        <ul class="imgBtn">
          <li class="checkImg">1</li>
          <li>2</li>
          <li>3</li>
          <li>4</li>
          <li>5</li>
        </ul>
      </div>
      <div class="postInfo">
        <h2><%= post.getTitle() %></h2>
        <p><%= writerNick %></p>
        <p>조회수 <%= post.getHits() %></p>
        <p class="date"><%= post.getWriteDate() %></p>
        <p>가격 : <span class="price"><%= post.getPrice() %>원</span></p>
        <div class="mainText">
					<%= post.getContent() %>
        </div>
        <%
        if(memberId != null && !writerId.equals(memberId)){
        %>
        <ul class="saleBtn">
          <li><a href="../chat/chatting.jsp?toNick=<%= writerNick %>">채팅</a></li>
        </ul>
        <%
        } else if(memberId == null) {
        %>
        <p class="warningMessage">로그인 후 판매자와의 채팅이 가능합니다.</p>
        <%
        }
        %>
      </div>
    </div>
    
    <ul id="memberBtn" class="postBtn">
	    <%
	    if(writerId.equals(memberId)){ // 로그인멤버 = 판매자
	    %>
      <li><a href="#">수정</a></li>
      <li><a href="#">삭제</a></li>
	    <%
	    } else { // 로그인멤버 != 판매자
	    %>
	      <li><a href="#">신고하기</a></li>
	    <%
	    }
	    %>
    </ul>
  </div>
  <div id="PageBtn">
    <a class="prevBtn" href="/marketPostInfo?no=<%= post.getMarketNo() - 1 %>">이전 게시물로 이동</a>
    <a class="nextBtn" href="/marketPostInfo?no=<%= post.getMarketNo() + 1 %>">다음 게시물로 이동</a>
  </div>
  <%@ include file="../include/footer.jsp"%>
</body>
</html>