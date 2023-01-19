<%@ page language="java" contentType="text/html; charset=UTF-8"
    pageEncoding="UTF-8"%>
<%@ page import="dao.MyPageDao" %>
<%@ page import="dto.MemberDto" %>
<%@ page import="java.util.*" %>
<%@ page import="java.sql.*" %>
<section id="myPageAdminRepManage">
	<!-- 
		myPageManagerCategory : 현재 어디 카테고리 소속인지 나타내는 파라미터
		memberManager : 현재 매니저 마이페이지를 보여줘야함을 나타내는 파라미터
		memberManagerNo : 멤버관리의 페이징처리용 파라미터
	 -->
    <a href="memberManagerPage?myPageManagerCategory=2&memberManager=0&memberManagerNo=1">멤버관리</a>
    <a href="memberManagerPage?myPageManagerCategory=2&memberManager=0&memberManagerNo=1&memberManagerSub=1">차단 멤버관리</a>
    <div id="myPageAdminRepManage">
      <form name="frmMember" method="post">
	  <input type="hidden" name="id">
	  <input type="hidden" name="chkdID">
      <table class="myPageAdminRepManageTable">
		<tr>
			<th><input name="chkAll" type="checkbox" onClick="setChkAll();">선택</th>
			<th>회원번호</th>
			<th>아이디</th>
			<th>닉네임</th>
			<th>주소</th>
			<th>우편번호</th>
			<th>비고</th>
		</tr>
		<%
			int pageNum = 1; // 페이지 번호가 전달이 안되면 1페이지
			if(request.getParameter("pageNum") != null) { // 페이지 번호가 전달이 된 경우
				pageNum = Integer.parseInt(request.getParameter("memberManagerNo"));					
			}
			ArrayList<MemberDto> memberArrayList = (ArrayList<MemberDto>)request.getAttribute("memberArrayList");
			for(MemberDto member : memberArrayList){
		%>
		<tr>
			<td><input type="checkbox" name="chkID" value="<%=member.getNo()%>" onClick="setChkAlone(this);"></td>
			<td><%=member.getNo()%></td>
			<td><%=member.getId()%></td>
			<td><%=member.getNickName()%></td>
			<td><%=member.getAddress()%></td>
			<td><%=member.getZipCode()%></td>
			<td><span class="badge badge-danger btn" onclick="banMemberByNo('<%=member.getNo()%>')">차단</span></td>
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
		</tr>
	</table>
	<div id="subHandler">
		<span onclick="banMember()" class="btn btn-danger">전체차단하기</span>
		<span onclick="banMemberSel()" class="btn btn-danger">선택차단하기</span>
	</div>
	</form>
	<form action="memberManagerPage/memberSearch.do" method="post" id="searchForm">
		<select id="select" name="select" form="searchForm">
		    <option value="no">회원번호</option>
		    <option value="id">아이디</option>
		    <option value="nickname">별명</option>
		</select>
		<p>회원 검색:<input type="text" id="keyword" name="keyword"></p>
		<input type="text" id="memberManagerNo" name="memberManagerNo" value="<%=request.getParameter("memberManagerNo")%>">
		<input id="searchMemberBtn" type="button" value="검색하기">
	</form>
	<script>
		const searchMemberBtn = document.querySelector("#searchMemberBtn");
		searchMemberBtn.addEventListener('click',function(){
			let str = "memberManagerPage?myPageManagerCategory=2&memberManager=0&memberManagerNo=1"
			let str2 = "&select="+document.querySelector("#select").value;
			let str3 = "&keyword="+document.querySelector("#keyword").value;
			console.log(str+str2+str3);
			location.href=str+str2+str3;
		});
	</script>
	<div id="memberManagerPaging">
    	<%
    	int cntListPerPage = 10;
    	MyPageDao dao = new MyPageDao();
    	ResultSet rs=null;
    	if(request.getParameter("select")==null){
    		rs = dao.getAllMemberList();
    	}else{
    		rs = dao.getAllSearchedMemberList(request.getParameter("select"),request.getParameter("keyword"));
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
		        <a href="memberManagerPage?myPageManagerCategory=2&memberManager=0&memberManagerNo=1">첫 페이지</a>
		        <% 
		        if (blockThis > 1) {
		        %>
		        <a href="memberManagerPage?myPageManagerCategory=2&memberManager=0&memberManagerNo=<%=(blockThisFirstPage - 1)%>">앞으로</a>
		        <% 
		        }
		       	for(int i = blockThisFirstPage; i <= blockThisLastPage; i++) {
		        %>
		        <a href="memberManagerPage?myPageManagerCategory=2&memberManager=0&memberManagerNo=<%=i%>" class="num"><%=i%></a>
		        <%
		        }
		        %>
		        <%
		        if(blockThis < blockTotal) {
		        %>
		        <a href="memberManagerPage?myPageManagerCategory=2&memberManager=0&memberManagerNo=<%=(blockThisLastPage + 1)%>">뒤로</a>
		        <%
		        }
		        %>
		        <a href="memberManagerPage?myPageManagerCategory=2&memberManager=0&memberManagerNo=<%=totalPage%>">마지막 페이지</a>
    	<%
    		}else{
    	%>
    			<a href="memberManagerPage?myPageManagerCategory=2&memberManager=0&memberManagerNo=1&select=<%=request.getParameter("select")%>&keyword=<%=request.getParameter("keyword")%>">첫 페이지</a>
		        <% 
		        if (blockThis > 1) {
		        %>
		        <a href="memberManagerPage?myPageManagerCategory=2&memberManager=0&memberManagerNo=<%=(blockThisFirstPage - 1)%>&select=<%=request.getParameter("select")%>&keyword=<%=request.getParameter("keyword")%>">앞으로</a>
		        <% 
		        }
		       	for(int i = blockThisFirstPage; i <= blockThisLastPage; i++) {
		        %>
		        <a href="memberManagerPage?myPageManagerCategory=2&memberManager=0&memberManagerNo=<%=i%>&select=<%=request.getParameter("select")%>&keyword=<%=request.getParameter("keyword")%>" class="num"><%=i%></a>
		        <%
		        }
		        %>
		        <%
		        if(blockThis < blockTotal) {
		        %>
		        <a href="memberManagerPage?myPageManagerCategory=2&memberManager=0&memberManagerNo=<%=(blockThisLastPage + 1)%>&select=<%=request.getParameter("select")%>&keyword=<%=request.getParameter("keyword")%>">뒤로</a>
		        <%
		        }
		        %>
		        <a href="memberManagerPage?myPageManagerCategory=2&memberManager=0&memberManagerNo=<%=totalPage%>&select=<%=request.getParameter("select")%>&keyword=<%=request.getParameter("keyword")%>">마지막 페이지</a>
    	<%
    		}
    	%>
    </div>
  </div>
</section>
<script>
	const frm = document.frmMember;
	window.onload=function(){
		document.frmMember.chkAll.checked=true;
		setChkAll();
	}
	function frmName(){
		return document.frmMember;
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
	let banMemberByNo = function(no){
		if(confirm('해당 회원을 차단하시겠습니까?')){
			location.href = 'memberManagerPage/oneMemberBan.do?no='+no;
		}
	}
	let banMemberSel = function(){
		if(confirm('선택한 회원을 차단하시겠습니까?')){
			frm.action="memberManagerPage/selectedMemberBan.do";
			frm.submit();
		}
	}
	let banMember = function(){
		if(confirm('전체 차단하시겠습니까?')){
			location.href='memberManagerPage/allMemberBan.do';
		}
	}
</script>