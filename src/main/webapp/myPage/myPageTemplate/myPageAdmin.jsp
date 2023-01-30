<%@ page language="java" contentType="text/html; charset=UTF-8"
    pageEncoding="UTF-8"%>
<%@ page import="dao.MyPageDao" %>
<%@ page import="dto.MemberDto" %>
<%@ page import="java.util.*" %>
<section id="myPageAdmin">
  <div id="myPageAdminLeftNavWrap">
    <nav id="myPageAdminNav">
      <ul>
        <li><a href="statementManagerPage?myPageManagerCategory=2&statusManager=0">통계</a></li>
        <li><a href="marketManagerPage?myPageManagerCategory=2&marketManager=0&marketManagerNo=1">게시판관리</a></li>
        <li><a href="managerPage?myPageManagerCategory=2&repManager=0">댓글관리</a></li>
        <li><a href="memberManagerPage?myPageManagerCategory=2&memberManager=0&memberManagerNo=1">멤버관리</a></li>
        <li><a href="managerPage?myPageManagerCategory=2&reportManager=0">신고관리</a></li>
      </ul>
    </nav>
  </div>
  <div id="myPageAdminInnerTab">
  	<%
    	if(request.getParameter("statusManager")!=null){
    %>
    <jsp:include page="../adminTemplate/statementManager.jsp" />
    <%
    	}else if((request.getParameter("marketManager")!=null)&&(request.getAttribute("bannedMarketArrayList")!=null)){
    		int marketManagerNo = Integer.parseInt((String)request.getParameter("marketManagerNo"));
	%>
    <jsp:include page="../adminTemplate/bannedMarketManager.jsp" />
    <%
    	}else if((request.getParameter("marketManager")!=null)&&(request.getAttribute("bannedMarketArrayList")==null)){
    %>
    <jsp:include page="../adminTemplate/marketManager.jsp" />
    <%
    	}else if(request.getParameter("repManager")!=null){
    %>
    <section id="myPageAdminBoardManage">
      <a href="#">댓글관리</a>
      <div id="myPageAdminBoardManage">
        <ul id="myPageAdminBoardManageHeader">
          <li>컬럼1</li>
          <li>컬럼2</li>
          <li>컬럼3</li>
          <li>컬럼4</li>
          <li>컬럼5</li>
        </ul>
        <ul class="myPageAdminBoardManageContent">
          <li>필드1</li>
          <li>필드2</li>
          <li>필드3</li>
          <li>필드4</li>
          <li>필드5</li>
        </ul>
        <ul class="myPageAdminBoardManageContent">
          <li>필드1</li>
          <li>필드2</li>
          <li>필드3</li>
          <li>필드4</li>
          <li>필드5</li>
        </ul>
      </div>
    </section>
	<%
    	}else if((request.getParameter("memberManager")!=null)&&(request.getAttribute("bannedMemberArrayList")!=null)){
    		int memberManagerNo = Integer.parseInt((String)request.getParameter("memberManagerNo"));
	%>
    <jsp:include page="../adminTemplate/bannedMemberManager.jsp" />
    <%
    	}else if((request.getParameter("memberManager")!=null)&&(request.getAttribute("bannedMemberArrayList")==null)){
    %>
    <jsp:include page="../adminTemplate/memberManager.jsp" />
    <%
    	}else if(request.getParameter("reportManager")!=null){
    %>
    <jsp:include page="../adminTemplate/reportMemberManager.jsp" />
    <%
    	}
    %>
  </div>
</section>
<script>
	document.addEventListener('DOMContentLoaded',function(){
		let status = document.querySelector("#myAdmin");
		status.classList.add("active");
	});
</script>
<script>
	/*
	const myAdmin = document.querySelector("#myAdmin");
	myAdmin.classList.add("active");
	
	const myPageAdminNavUlLi = document.querySelectorAll("#myPageAdminNav ul li")
	const myPageAdminInnerTabSection = document.querySelectorAll("#myPageAdminInnerTab section");
	
	for(let i=0;i<myPageAdminNavUlLi.length;i++){
	  console.log(i);
	  myPageAdminNavUlLi[i].addEventListener('click',function(){
	    for(let j=0;j<myPageAdminNavUlLi.length;j++){
	    	myPageAdminNavUlLi[j].classList.remove("enabled");
	    }
	    myPageAdminNavUlLi[i].classList.add("enabled");
	    
	  	  console.log(i);
	  	    for(let j=0;j<myPageAdminInnerTabSection.length;j++){
	  	    	myPageAdminInnerTabSection[j].classList.remove("active");
	  	    }
	  	    myPageAdminInnerTabSection[i].classList.add("active");
	  });
	}
	
	*/
	
</script>