<%@ page language="java" contentType="text/html; charset=UTF-8"
    pageEncoding="UTF-8"%>
<%@ page import="dto.MarketDto" %>
<!DOCTYPE html>
<html>
<head>
<meta charset="UTF-8">
<title>marketPost</title>
<%@ include file="../include/frontStyle.jsp"%>
<link rel="stylesheet" href="../assets/css/marketPost.css">
<script src="../assets/js/marketPost.js" defer></script>
</head>
<body>
	<%@ include file="../include/header.jsp"%>
	<%
	MarketDto post = (MarketDto)request.getAttribute("post");
	String writerNick = (String)request.getAttribute("writerNick");
	String writerId = (String)request.getAttribute("writerId");
	
	String part = null;
	if(post.getPart().equals("sell")){
		part = "판매";
	}
	else if(post.getPart().equals("buy")){
		part = "구매";
	}
	%>
	<!-- contents -->
  <div id="content">
    <h1><a href="/market/market.jsp"><%= part %>글</a></h1>
    <div id="contentWrap">
      <div class="postImg">
        <ul class="mainImg">
          <!-- 상품 이미지 개수 유동적으로 변화 -->
          <%
          int imgNum = 0; // 이미지 개수
         	for(String imgUrl : post.getImage()){
         		if(imgUrl != null){ // 이미지가 존재하면
         			imgNum ++;
         			String fileName = imgUrl.substring(imgUrl.lastIndexOf("/") + 1);
          %>
          		<li><img src="/img/<%= fileName %>"  alt="<%= fileName %>"></li>
          <%
         		}
         	}
          if(post.getImage()[0] == null){ // 해당 글 이미지 하나도 존재x
        	  imgNum = 1;
        	  // 사이트 기본 이미지
          %>
          	<li><img src="/assets/image/default_image.png" alt="기본 이미지"></li>
          <%
          }
          %>
        </ul>
        <ul class="imgBtn">
	        <%
	        for(int i=0; i<imgNum; i++){
	        %>
	          <li><%= i+1 %></li>
	        <%
	        }
	        %>
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
        if(memberId != null && writerId.equals(memberId)){ // 로그인 멤버 = 판매자
        	if(post.getDisabled().equals("true")){ // 비공개 글
       		%>
           	<p class="warningMessage">비공개 처리된 글입니다.</p>
         	<%
        	}
       		else { // 공개 글
	       	%>
	       	<script src="/assets/js/marketPost_soldOut.js" defer></script>
	       	<ul class="saleBtn">
	          <li><span data-marketNo="<%= post.getMarketNo() %>" class="soldOutBtn"><%= part %>완료</span></li>
	        </ul>
	       	<%
        	}
        }
        else if(memberId != null && !writerId.equals(memberId)){
        %>
        <ul class="saleBtn">
          <li><a href="../chat/chatting.jsp?toNick=<%= writerNick %>" class="chatBtn">채팅</a></li>
        </ul>
        <%
        } else if(memberId == null) {
        %>
        <p class="warningMessage">로그인 후 <%= part %>자와의 채팅이 가능합니다.</p>
        <%
        }
        %>
      </div>
    </div>
    
    <ul id="memberBtn" class="postBtn">
	    <%
	    if(writerId.equals(memberId)){ // 로그인멤버 = 판매자
	    %>
      <li><a href="/marketGoEditPage?no=<%= post.getMarketNo() %>">수정</a></li>
      <li><a href="/marketPostRemove?no=<%= post.getMarketNo() %>">삭제</a></li>
	    <%
	    } else if(memberId != null){ // 로그인멤버 != 판매자
	    %>
	      <li>
    		<script src="/assets/js/marketPost_report.js" defer></script>
	      <form name="reportForm" action="/ReportMemberServlet" method="post">
	      	<input type="hidden" name="postNo" value=<%= post.getMarketNo() %>>
	      	<input type="hidden" name="writerNo" value=<%= post.getWriterNo() %>>
	      </form>
	      <a href="#" class="reportBtn">신고하기</a>
	      </li>
	    <%
	    }
	    %>
    </ul>
  </div>
  <%@ include file="/include/footer.jsp"%>
</body>
</html>