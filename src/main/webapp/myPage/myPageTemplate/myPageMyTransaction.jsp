<%@ page language="java" contentType="text/html; charset=UTF-8"
    pageEncoding="UTF-8"%>
<%@ page import="dao.MyPageDao" %>
<%@ page import="dto.MarketResponseDto" %>
<%@ page import="java.util.*" %>
<%@ page import="java.sql.*" %>
<section id="myPageTransactionList">
	<!-- 
		myPageCategory : 현재 어디 카테고리 소속인지 나타내는 파라미터
		myPageTransaction : 현재 매니저 마이페이지를 보여줘야함을 나타내는 파라미터
		myPageTransactionNo : 멤버관리의 페이징처리용 파라미터
	 -->
    <div id="myPageTransactionFormWrap">
      <form name="frmMarket" method="post">
	  <input type="hidden" name="id">
      <table class="myPageTransactionTable">
		<tr>
			<th>마켓 글 번호</th>
			<th>글쓴이 회원번호</th>
			<th>제목</th>
			<th>내용</th>
			<th>글 종류</th>
			<th>가격/희망가격</th>
			<th>글쓴일</th>
			<th>조회수</th>
			<th>현재상태</th>
		</tr>
		<%
			int pageNum = 1; // 페이지 번호가 전달이 안되면 1페이지
			if(request.getParameter("myPageTransactionNo") != null) { // 페이지 번호가 전달이 된 경우
				pageNum = Integer.parseInt(request.getParameter("myPageTransactionNo"));					
			}
			ArrayList<MarketResponseDto> marketArrayList = (ArrayList<MarketResponseDto>)request.getAttribute("transactionDoneMarketArrayList");
			for(MarketResponseDto market : marketArrayList){
		%>
		<tr>
			<td><%=market.getMarketNo()%></td>
			<td><%=market.getWriterNo()%></td>
			<td><%=market.getTitle()%></td>
			<td><%=market.getContent()%></td>
			<td><%=market.getPart()%></td>
			<td><%=market.getPrice()%></td>
			<td><%=market.getWriteDate()%></td>
			<td><%=market.getHits()%></td>
			<td><%=market.getReport()%></td>
		</tr>
		<%
			}
		%>
		<tr>
			<th></th>
			<th></th>
			<th></th>
			<th></th>
			<th></th>
			<th></th>
			<th></th>
			<th></th>
			<th></th>
		</tr>
	</table>
	</form>
	<form action="myPageTransactionPage/transactionSearch.do" method="post" id="searchForm">
		<select id="select" name="select" form="searchForm">
		    <option value="market_no">장터번호</option>
		    <option value="title">제목</option>
		    <option value="content">내용</option>
		</select>
		<p>회원 검색:<input type="text" id="keyword" name="keyword"></p>
		<input type="text" id="myPageTransactionNo" name="myPageTransactionNo" value="<%=request.getParameter("myPageTransactionNo")%>">
		<input id="searchTransactionBtn" type="button" value="검색하기">
	</form>
	<script>
		const searchTransactionBtn = document.querySelector("#searchTransactionBtn");
		searchTransactionBtn.addEventListener('click',function(){
			let str = "myPage?myPageCategory=2&myPageTransactionNo=1"
			let str2 = "&select="+document.querySelector("#select").value;
			let str3 = "&keyword="+document.querySelector("#keyword").value;
			console.log(str+str2+str3);
			location.href=str+str2+str3;
		});
	</script>
	<div id="myPageTransactionPaging">
    	<%
    	int cntListPerPage = 10;
    	MyPageDao dao = new MyPageDao();
    	
    	ResultSet rs=null;
    	if(request.getParameter("select")==null){
    		rs = dao.getAllTransactionList(dao.getMemberNoById((String)session.getAttribute("memberId")));
    	}else{
    		rs = dao.getAllSearchedTransactionList(dao.getMemberNoById((String)session.getAttribute("memberId")),request.getParameter("select"),request.getParameter("keyword"));
    	}
		rs.next();
		int totalRecord = rs.getInt(1);
		int totalPage = (totalRecord % cntListPerPage == 0) ? totalRecord / cntListPerPage : (totalRecord / cntListPerPage) + 1;
    	
		int block = 5; // 페이지 나올 범위
		int blockTotal = totalPage % block == 0 ? totalPage / block : totalPage / block + 1; // 총 블럭의 수
		int blockThis = ((pageNum - 1) / block) + 1; // 현재 페이지의 블럭
		int blockThisFirstPage = ((blockThis - 1) * block) + 1; // 현재 블럭의 첫 페이지
		int blockThisLastPage = blockThis * block; // 현재 블럭의 마지막 페이지
		blockThisLastPage = (blockThisLastPage > totalPage) ? totalPage : blockThisLastPage; 
    	%>
    	
    	<%
    		if(request.getParameter("select")==null){
    	%>
		        <a href="myPage?myPageCategory=2&myPageTransactionNo=1">첫 페이지</a>
		        <% 
		        if (blockThis > 1) {
		        %>
		        <a href="myPage?myPageCategory=2&myPageTransactionNo=<%=(blockThisFirstPage - 1)%>">앞으로</a>
		        <% 
		        }
		       	for(int i = blockThisFirstPage; i <= blockThisLastPage; i++) {
		        %>
		        <a href="myPage?myPageCategory=2&myPageTransactionNo=<%=i%>" class="num"><%=i%></a>
		        <%
		        }
		        %>
		        <%
		        if(blockThis < blockTotal) {
		        %>
		        <a href="myPage?myPageCategory=2&myPageTransactionNo=<%=(blockThisLastPage + 1)%>">뒤로</a>
		        <%
		        }
		        %>
		        <a href="myPage?myPageCategory=2&myPageTransactionNo=<%=totalPage%>">마지막 페이지</a>
    	<%
    		}else{
    	%>
    			<a href="myPage?myPageCategory=2&myPageTransactionNo=1&select=<%=request.getParameter("select")%>&keyword=<%=request.getParameter("keyword")%>">첫 페이지</a>
		        <% 
		        if (blockThis > 1) {
		        %>
		        <a href="myPageT?myPageCategory=2&myPageTransactionNo=<%=(blockThisFirstPage - 1)%>&select=<%=request.getParameter("select")%>&keyword=<%=request.getParameter("keyword")%>">앞으로</a>
		        <% 
		        }
		       	for(int i = blockThisFirstPage; i <= blockThisLastPage; i++) {
		        %>
		        <a href="myPage?myPageCategory=2&myPageTransactionNo=<%=i%>&select=<%=request.getParameter("select")%>&keyword=<%=request.getParameter("keyword")%>" class="num"><%=i%></a>
		        <%
		        }
		        %>
		        <%
		        if(blockThis < blockTotal) {
		        %>
		        <a href="myPage?myPageCategory=2&myPageTransactionNo=<%=(blockThisLastPage + 1)%>&select=<%=request.getParameter("select")%>&keyword=<%=request.getParameter("keyword")%>">뒤로</a>
		        <%
		        }
		        %>
		        <a href="myPage?myPageCategory=2&myPageTransactionNo=<%=totalPage%>&select=<%=request.getParameter("select")%>&keyword=<%=request.getParameter("keyword")%>">마지막 페이지</a>
    	<%
    		}
    	%>
    </div>
  </div>
</section>
<script>
	document.addEventListener('DOMContentLoaded',function(){
		const myTrans = document.querySelector("#myTrans");
		myTrans.classList.add("active");
		
		let pagingVar = document.querySelectorAll("#myPageTransactionPaging a");
		let pagingServerVar = <%=Integer.parseInt((String)request.getParameter("myPageTransactionNo"))%>;
		
		if(pagingServerVar!=null){
			if(pagingServerVar>5){
				pagingServerVar = (pagingServerVar%5)+2;
			}else {
				pagingServerVar++;
			}
			let a = document.querySelector(".num:nth-child("+pagingServerVar+")");
			console.log(pagingServerVar);
			console.log(a);
			a.classList.add("active");
			a.style.color = "black";
		}
	});
</script>