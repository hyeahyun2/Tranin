<%@ page language="java" contentType="text/html; charset=UTF-8"
    pageEncoding="UTF-8"%>
<%@ page import="dao.MyPageDao" %>
<%@ page import="dto.MemberDto" %>
<%@ page import="java.util.*" %>
<%
	int memberManagerNo = Integer.parseInt((String)request.getParameter("memberManagerNo"));
%>
<section id="myPageAdmin">
  <div id="myPageAdminLeftNavWrap">
    <nav id="myPageAdminNav">
      <ul>
        <li class="enabled">통계</li>
        <li>게시판관리</li>
        <li>댓글관리</li>
        <li>멤버관리</li>
        <li>신고관리</li>
      </ul>
    </nav>
  </div>
  <div id="myPageAdminInnerTab">
    <section class="active" id="myPageAdminStatus">
      <a href="#">통계에오</a>
      <div id="myPageAdminStatus">
        <ul id="myPageAdminStatusHeader">
          <li>컬럼1</li>
          <li>컬럼2</li>
          <li>컬럼3</li>
          <li>컬럼4</li>
          <li>컬럼5</li>
        </ul>
        <ul class="myPageAdminStatusContent">
          <li>필드1</li>
          <li>필드2</li>
          <li>필드3</li>
          <li>필드4</li>
          <li>필드5</li>
        </ul>
        <ul class="myPageAdminStatusContent">
          <li>필드1</li>
          <li>필드2</li>
          <li>필드3</li>
          <li>필드4</li>
          <li>필드5</li>
        </ul>
      </div>
    </section>
    <section id="myPageAdminBanManage">
      <a href="#">게시판관리</a>
      <div id="myPageAdminBanManage">
        <ul id="myPageAdminBanManageHeader">
          <li>컬럼1</li>
          <li>컬럼2</li>
          <li>컬럼3</li>
          <li>컬럼4</li>
          <li>컬럼5</li>
        </ul>
        <ul class="myPageAdminBanManageContent">
          <li>필드1</li>
          <li>필드2</li>
          <li>필드3</li>
          <li>필드4</li>
          <li>필드5</li>
        </ul>
        <ul class="myPageAdminBanManageContent">
          <li>필드1</li>
          <li>필드2</li>
          <li>필드3</li>
          <li>필드4</li>
          <li>필드5</li>
        </ul>
      </div>
    </section>
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
    <jsp:include page="../adminTemplate/memberManager.jsp" >
       <jsp:param name="id" value="<%=memberManagerNo%>" />
    </jsp:include>
    <jsp:include page="../adminTemplate/reportMemberManager.jsp" >
       <jsp:param name="id" value="<%=memberManagerNo%>" />
    </jsp:include>
  </div>
</section>
<script>

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
	
	
	
</script>