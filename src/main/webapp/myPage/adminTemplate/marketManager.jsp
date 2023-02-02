<%@ page language="java" contentType="text/html; charset=UTF-8"
    pageEncoding="UTF-8"%>
<%@ page import="dao.AdminMarketDao" %>
<%@ page import="dto.MarketDto" %>
<%@ page import="dto.MarketDto" %>
<%@ page import="java.util.*" %>
<%@ page import="java.sql.*" %>
<section id="myPageAdminMarketManage">
	<!-- 
		myPageManagerCategory : 현재 어디 카테고리 소속인지 나타내는 파라미터
		marketManager : 현재 매니저 마이페이지를 보여줘야함을 나타내는 파라미터
		marketManagerNo : 마켓글관리의 페이징처리용 파라미터
	 -->
    <a id="marketManagerPageAnchor" href="marketManagerPage?myPageManagerCategory=2&marketManager=0&marketManagerNo=1">마켓글관리</a>
    <a id="marketBanManagerPageAnchor" href="marketManagerPage?myPageManagerCategory=2&marketManager=0&marketManagerNo=1&marketManagerSub=1">내려간 마켓글관리</a>
    <div id="myPageAdminMarketManage">
      <form name="frmMarket" method="post">
	  <input type="hidden" name="id">
	  <input type="hidden" name="chkdID">
      <table class="myPageAdminMarketManageTable">
		<tr>
			<th><input name="chkAll" type="checkbox" onClick="setChkAll();">선택</th>
			<th>마켓 글 번호</th>
			<th>글쓴이 회원번호</th>
			<th>제목</th>
			<th>내용</th>
			<th>글 종류</th>
			<th>가격/희망가격</th>
			<th>글쓴일</th>
			<th>조회수</th>
			<th>비고</th>
		</tr>
		<%
			int pageNum = 1; // 페이지 번호가 전달이 안되면 1페이지
			if(request.getParameter("marketManagerNo") != null) { // 페이지 번호가 전달이 된 경우
				pageNum = Integer.parseInt(request.getParameter("marketManagerNo"));					
			}
			ArrayList<MarketDto> marketArrayList = (ArrayList<MarketDto>)request.getAttribute("marketArrayList");
			for(MarketDto market : marketArrayList){
		%>
		<tr>
			<td><input type="checkbox" name="chkID" value="<%=market.getMarketNo()%>" onClick="setChkAlone(this);"></td>
			<td><%=market.getMarketNo()%></td>
			<td><%=market.getWriterNo()%></td>
			<td><%=market.getTitle()%></td>
			<td><%=market.getContent()%></td>
			<td><%=market.getPart()%></td>
			<td><%=market.getPrice()%></td>
			<td><%=market.getWriteDate()%></td>
			<td><%=market.getHits()%></td>
			<td><span class="badge badge-danger btn" onclick="banMarketByNo('<%=market.getMarketNo()%>')">글내리기</span></td>
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
			<th></th>
		</tr>
	</table>
	<div id="subHandler">
		<span onclick="banMarket()" class="btn btn-danger">전체글내리기</span>
		<span onclick="banMarketSel()" class="btn btn-danger">선택글내리기</span>
	</div>
	</form>
	<form action="marketManagerPage/marketSearch.do" method="post" id="searchForm">
		<select id="select" name="select" form="searchForm">
		    <option value="market_no">장터번호</option>
		    <option value="title">제목</option>
		    <option value="content">내용</option>
		</select>
		<p>회원 검색:<input type="text" id="keyword" name="keyword"></p>
		<input type="text" id="marketManagerNo" name="marketManagerNo" value="<%=request.getParameter("marketManagerNo")%>">
		<input id="searchMarketBtn" type="button" value="검색하기">
	</form>
	<script>
		const searchMarketBtn = document.querySelector("#searchMarketBtn");
		searchMarketBtn.addEventListener('click',function(){
			let str = "marketManagerPage?myPageManagerCategory=2&marketManager=0&marketManagerNo=1"
			let str2 = "&select="+document.querySelector("#select").value;
			let str3 = "&keyword="+document.querySelector("#keyword").value;
			console.log(str+str2+str3);
			location.href=str+str2+str3;
		});
	</script>
	<div id="marketManagerPaging">
    	<%
    	int cntListPerPage = 10;
    	AdminMarketDao dao = new AdminMarketDao();
    	ResultSet rs=null;
    	if(request.getParameter("select")==null){
    		rs = dao.getAllMarketList();
    	}else{
    		rs = dao.getAllSearchedMarketList(request.getParameter("select"),request.getParameter("keyword"));
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
		        <a href="marketManagerPage?myPageManagerCategory=2&marketManager=0&marketManagerNo=1">첫 페이지</a>
		        <% 
		        if (blockThis > 1) {
		        %>
		        <a href="marketManagerPage?myPageManagerCategory=2&marketManager=0&marketManagerNo=<%=(blockThisFirstPage - 1)%>">앞으로</a>
		        <% 
		        }
		       	for(int i = blockThisFirstPage; i <= blockThisLastPage; i++) {
		        %>
		        <a href="marketManagerPage?myPageManagerCategory=2&marketManager=0&marketManagerNo=<%=i%>" class="num"><%=i%></a>
		        <%
		        }
		        %>
		        <%
		        if(blockThis < blockTotal) {
		        %>
		        <a href="marketManagerPage?myPageManagerCategory=2&marketManager=0&marketManagerNo=<%=(blockThisLastPage + 1)%>">뒤로</a>
		        <%
		        }
		        %>
		        <a href="marketManagerPage?myPageManagerCategory=2&marketManager=0&marketManagerNo=<%=totalPage%>">마지막 페이지</a>
    	<%
    		}else{
    	%>
    			<a href="marketManagerPage?myPageManagerCategory=2&marketManager=0&marketManagerNo=1&select=<%=request.getParameter("select")%>&keyword=<%=request.getParameter("keyword")%>">첫 페이지</a>
		        <% 
		        if (blockThis > 1) {
		        %>
		        <a href="marketManagerPage?myPageManagerCategory=2&marketManager=0&marketManagerNo=<%=(blockThisFirstPage - 1)%>&select=<%=request.getParameter("select")%>&keyword=<%=request.getParameter("keyword")%>">앞으로</a>
		        <% 
		        }
		       	for(int i = blockThisFirstPage; i <= blockThisLastPage; i++) {
		        %>
		        <a href="marketManagerPage?myPageManagerCategory=2&marketManager=0&marketManagerNo=<%=i%>&select=<%=request.getParameter("select")%>&keyword=<%=request.getParameter("keyword")%>" class="num"><%=i%></a>
		        <%
		        }
		        %>
		        <%
		        if(blockThis < blockTotal) {
		        %>
		        <a href="marketManagerPage?myPageManagerCategory=2&marketManager=0&marketManagerNo=<%=(blockThisLastPage + 1)%>&select=<%=request.getParameter("select")%>&keyword=<%=request.getParameter("keyword")%>">뒤로</a>
		        <%
		        }
		        %>
		        <a href="marketManagerPage?myPageManagerCategory=2&marketManager=0&marketManagerNo=<%=totalPage%>&select=<%=request.getParameter("select")%>&keyword=<%=request.getParameter("keyword")%>">마지막 페이지</a>
    	<%
    		}
    	%>
    </div>
  </div>
</section>
<script>
	document.addEventListener('DOMContentLoaded',function(){
		let status = document.querySelector("#myPageAdminNav ul li:nth-child(2)");
		status.classList.add("active");
		
		let statusA = document.querySelector("#myPageAdminNav ul li:nth-child(2) a");
		statusA.classList.add("active");
		
		let statusC = document.querySelector("#marketManagerPageAnchor");
		statusC.classList.add("active");
		statusC.style.color = "black";
		
		let pagingVar = document.querySelectorAll("#marketManagerPaging a");
		let pagingServerVar = <%=Integer.parseInt((String)request.getParameter("marketManagerNo"))%>;
		
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
<script>
	const frm = document.frmMarket;
	window.onload=function(){
		document.frmMarket.chkAll.checked=true;
		setChkAll();
	}
	function frmName(){
		return document.frmMarket;
	}

	let arrID = new Array();

	window.onload = function init() {
	    with(frmName()) {
	        if (chkdID.value != "") {
	            arrID =  chkdID.value.split(",");
	            chkAll.checked = true;
	        }
	    }
	    setChkAllYN();
	}

	let setChkAllYN = function() { // 전체가 선택되었는지 검사
	    with(frmName()) {
	        let totalCntForm = totalCntFormChecked = 0;
	        for (var i = 0; i < elements.length; i++) {
	            var e = elements[i];
	            if((e.type === 'checkbox') && (e.name === 'chkID')) {
	                totalCntForm++;
	                if (e.checked) {
	                    totalCntFormChecked++;
	                }
	            }
	        }

	        if (totalCntFormChecked === totalCntForm) {
	            chkAll.checked=true;
	        }
	        else {
	            chkAll.checked=false;
	        }
	    }
	}

	let setArrChange = function (flag, ID) {
	    var idx = null;

	    for ( i = 0; i < arrID.length; i++) {
	        if(arrID[i] === ID) {
	            idx = i;
	        }
	    }

	    if (idx != null) {
	        arrID.splice(idx,1);
	    }

	    if (flag) {
	        arrID.push(ID);
	    }

	    frmName().chkdID.value = arrID;
	}

	let setChkAll = function() { //전체 선택
	    with(frmName()) {
	        for (var i = 0; i < elements.length; i++) {
	            var e = elements[i];

	            if((e.type === 'checkbox') && (e.name === 'chkID')) {
	                e.checked = chkAll.checked;
	                if (chkAll.checked) {
	                    setArrChange(true, e.value);
	                }
	                else {
	                    setArrChange(false, e.value);
	                }
	            }
	        }
	    }
	}
	let setChkAlone = function(T) { //개별 선택
	    with(frmName()) {
	        if (T.checked) {
	            setArrChange(true, T.value);
	        }
	        else {
	            setArrChange(false, T.value);
	        }
	        setChkAllYN();
	    }
	}
	let banMarketByNo = function(no){
		if(confirm('해당 글을 내리시겠습니까?')){
			location.href = 'marketManagerPage/oneMarketBan.do?no='+no;
		}
	}
	let banMarketSel = function(){
		if(confirm('선택한 글을 내리시겠습니까?')){
			frm.action="marketManagerPage/selectedMarketBan.do";
			frm.submit();
		}
	}
	let banMarket = function(){
		if(confirm('전체 글을 내리시겠습니까?')){
			location.href='marketManagerPage/allMarketBan.do';
		}
	}
</script>