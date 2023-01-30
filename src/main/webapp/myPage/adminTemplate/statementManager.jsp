<%@ page language="java" contentType="text/html; charset=UTF-8"
    pageEncoding="UTF-8"%>
<%@ page import="dao.MyPageDao" %>
<%@ page import="dto.MemberDto" %>
<%@ page import="java.util.*" %>
<%@ page import="java.sql.*" %>
<%
	String dayRegMemberCount = (String)request.getAttribute("dayRegMemberCount");
	String weekRegMemberCount = (String)request.getAttribute("weekRegMemberCount");
	String monthRegMemberCount = (String)request.getAttribute("monthRegMemberCount");
	String allRegMemberCount = (String)request.getAttribute("allRegMemberCount");
	
	String dayPopPost = (String)request.getAttribute("dayPopPost");
	String weekPopPost = (String)request.getAttribute("weekPopPost");
	String monthPopPost = (String)request.getAttribute("monthPopPost");
	String allPopPost = (String)request.getAttribute("allPopPost");
	
	String dayMostUser = (String)request.getAttribute("dayMostUser");
	String weekMostUser = (String)request.getAttribute("weekMostUser");
	String monthMostUser = (String)request.getAttribute("monthMostUser");
	String allMostUser = (String)request.getAttribute("allMostUser");
	String dayMostUserCount = (String)request.getAttribute("dayMostUserCount");
	String weekMostUserCount = (String)request.getAttribute("weekMostUserCount");
	String monthMostUserCount = (String)request.getAttribute("monthMostUserCount");
	String allMostUserCount = (String)request.getAttribute("allMostUserCount");
	
	String dayPostCount = (String)request.getAttribute("dayPostCount");
	String weekPostCount = (String)request.getAttribute("weekPostCount");
	String monthPostCount = (String)request.getAttribute("monthPostCount");
	String allPostCount = (String)request.getAttribute("allPostCount");
	
	String dayBanPostCount = (String)request.getAttribute("dayBanPostCount");
	String weekBanPostCount = (String)request.getAttribute("weekBanPostCount");
	String monthBanPostCount = (String)request.getAttribute("monthBanPostCount");
	String allBanPostCount = (String)request.getAttribute("allBanPostCount");
	
	String dayBanMemberCount = (String)request.getAttribute("dayBanMemberCount");
	String weekBanMemberCount = (String)request.getAttribute("weekBanMemberCount");
	String monthBanMemberCount = (String)request.getAttribute("monthBanMemberCount");
	String allBanMemberCount = (String)request.getAttribute("allBanMemberCount");
	/*
	String dayMostSearch = (String)request.getAttribute("dayMostSearch");
	String weekMostSearch = (String)request.getAttribute("weekMostSearch");
	String monthMostSearch = (String)request.getAttribute("monthMostSearch");
	String allMostSearch = (String)request.getAttribute("allMostSearch");
	String dayMostSearchCount = (String)request.getAttribute("dayMostSearchCount");
	String weekMostSearchCount = (String)request.getAttribute("weekMostSearchCount");
	String monthMostSearchCount = (String)request.getAttribute("monthMostSearchCount");
	String allMostSearchCount = (String)request.getAttribute("allMostSearchCount");
	*/
%>
<section class="active" id="myPageAdminStatus">
  <a href="#">통계에오</a>
  <div id="myPageAdminStatusWrap">
  	<div id="statusRegMember" class="myPageAdminStatus">
  		<h2>가입한 가입자</h2>
	    <ul class="myPageAdminStatusUL">
	      <li>하루간 가입한 가입자는 <%=dayRegMemberCount%> 명 명입니다.</li>
	      <li>일주일간 가입한 가입자는 <%=weekRegMemberCount%> 명 명입니다.</li>
	      <li>한달간 가입한 가입자는 <%=monthRegMemberCount%> 명 명입니다.</li>
	      <li>전체기간 가입한 가입자는 <%=allRegMemberCount%> 명 명입니다.</li>
	    </ul>
  	</div>
  	<div id="statusPopPost" class="myPageAdminStatus">
  		<h2>가장인기 많은 장터글</h2>
	    <ul class="myPageAdminStatusUL">
	      <li>하루간 가장 많은 조회수의 글은 <a href=""><%=dayPopPost%></a> 입니다</li>
	      <li>일주일간 가장 많은 조회수의 글은 <a href=""><%=weekPopPost%></a> 입니다</li>
	      <li>한달간 가장 많은 조회수의 글은 <a href=""><%=monthPopPost%></a> 입니다</li>
	      <li>전체기간 가장 많은 조회수의 글은 <a href=""><%=allPopPost%></a> 입니다</li>
	    </ul>
  	</div>
  	
  	<div id="statusMostUser" class="myPageAdminStatus">
  		<h2>가장 많은 활동을 한 유저</h2>
	    <ul class="myPageAdminStatusUL">
	      <li>하루간 가장 많은 활동을 한 유저는 <a href=""><%=dayMostUser%></a> 입니다. 그는 <%=dayMostUserCount%> 개의 장터글을 작성했습니다.</li>
	      <li>일주일간 가장 많은 활동을 한 유저는 <a href=""><%=weekMostUser%></a> 입니다. 그는 <%=weekMostUserCount%> 개의 장터글을 작성했습니다.</li>
	      <li>한달간 가장 많은 활동을 한 유저는 <a href=""><%=monthMostUser%></a> 입니다. 그는 <%=monthMostUserCount%> 개의 장터글을 작성했습니다.</li>
	      <li>전체기간 가장 많은 활동을 한 유저는 <a href=""><%=allMostUser%></a> 입니다. 그는 <%=allMostUserCount%> 개의 장터글을 작성했습니다.</li>
	    </ul>
  	</div>
  	<div id="statusPostCount" class="myPageAdminStatus">
  		<h2>장터글 작성수</h2>
	    <ul class="myPageAdminStatusUL">
	      <li>하루간 쓰여진 장터글의 숫자는 <%=dayPostCount%> 입니다.</li>
	      <li>일주일간 쓰여진 장터글의 숫자는 <%=weekPostCount%> 입니다.</li>
	      <li>한달간 쓰여진 장터글의 숫자는 <%=monthPostCount%> 입니다.</li>
	      <li>전체기간 쓰여진 장터글의 숫자는 <%=allPostCount%> 입니다.</li>
	    </ul>
  	</div>
  	<div id="statusPostCount" class="myPageAdminStatus">
  		<h2>글 차단 횟수</h2>
	    <ul class="myPageAdminStatusUL">
	      <li>하루간 차단된 글의 숫자는 <%=dayBanPostCount%> 입니다.</li>
	      <li>일주일간 차단된 글의 숫자는 <%=weekBanPostCount%> 입니다.</li>
	      <li>한달간 차단된 글의 숫자는 <%=monthBanPostCount%> 입니다.</li>
	      <li>전체기간 차단된 글의 숫자는 <%=allBanPostCount%> 입니다.</li>
	    </ul>
  	</div>
  	<div id="statusPostCount" class="myPageAdminStatus">
  		<h2>이용자 차단 횟수</h2>
	    <ul class="myPageAdminStatusUL">
	      <li>하루간 차단된 이용자의 숫자는 <%=dayBanMemberCount%> 입니다.</li>
	      <li>일주일간 차단된 이용자의 숫자는 <%=weekBanMemberCount%> 입니다.</li>
	      <li>한달간 차단된 이용자의 숫자는 <%=monthBanMemberCount%> 입니다.</li>
	      <li>전체기간 차단된 이용자의 숫자는 <%=allBanMemberCount%> 입니다.</li>
	    </ul>
  	</div>
  	<%-- 
  	<div id="statusMostSearch" class="myPageAdminStatus">
  		<h2>가장 검색횟수가 많은 검색어</h2>
	    <ul class="myPageAdminStatusUL">
	      <li>하루간 가장 검색횟수가 많은 검색어는 <a href=""><%=dayMostSearch%></a> 입니다. 그 검색어는 <%=dayMostSearchCount%> 번 검색되었습니다.</li>
	      <li>일주일간 가장 검색횟수가 많은 검색어는 <a href=""><%=weekMostSearch%></a> 입니다. 그 검색어는 <%=weekMostSearchCount%> 번 검색되었습니다.</li>
	      <li>한달간 가장 검색횟수가 많은 검색어는 <a href=""><%=monthMostSearch%></a> 입니다. 그 검색어는 <%=monthMostSearchCount%> 번 검색되었습니다.</li>
	      <li>전체기간 가장 검색횟수가 많은 검색어는 <a href=""><%=allMostSearch%></a> 입니다. 그 검색어는 <%=allMostSearchCount%> 번 검색되었습니다.</li>
	    </ul>
  	</div>
  	--%>
  </div>
</section>
<script>
	document.addEventListener('DOMContentLoaded',function(){
		let status = document.querySelector("#myPageAdminNav ul li:nth-child(1)");
		status.classList.add("active");
		
		let statusA = document.querySelector("#myPageAdminNav ul li:nth-child(1) a");
		statusA.classList.add("active");
	});
</script>