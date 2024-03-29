<%@ page language="java" contentType="text/html; charset=UTF-8"
    pageEncoding="UTF-8" import="java.util.*,java.lang.*, dto.MemberDto"%>
<!DOCTYPE html>
<html>
  <head>
    <meta charset="utf-8">
    <title>마이페이지</title>
    <link rel="preconnect" href="https://fonts.googleapis.com" />
    <link rel="preconnect" href="https://fonts.gstatic.com" crossorigin />
    <link
      href="https://fonts.googleapis.com/css2?family=Lato:ital,wght@0,100;0,300;0,400;0,700;0,900;1,400&family=Noto+Sans+KR:wght@100;300;400;500;700;900&display=swap"
      rel="stylesheet" />
    <link rel="stylesheet" href="../assets/css/reset.css" />
    <link rel="stylesheet" href="../assets/css/common.css" />
    <link rel="stylesheet" href="../assets/css/footer.css" />
    <link rel="stylesheet" href="../assets/css/myPage.css" />
    <script type="text/javascript" src="../assets/js/jquery-3.6.1.js"></script>
    <script type="text/javascript" src="../assets/js/jquery-migrate-1.4.1.min.js"></script>
    <script type="text/javascript" src="../assets/js/jquery-ui.js"></script>
    <script src="../assets/js/header.js" defer></script>
    <script src="../assets/js/myPage.js" defer></script>
  </head>
  <body>
  <jsp:include page="/include/header.jsp" />
  </div>
    <%@ include file="../include/floatingMenu.jsp"%>
    <section id="myPageTab">
      <%
      	if(session.getAttribute("memberId")!=null){
      %>
      <h2>마이페이지</h2>
      <%
      	} else {
      %>
      <h2>관리자페이지</h2>
      <%
      	}
      %>
      <ul>
      <%
      	if(session.getAttribute("memberId")!=null){
      %>
        <li id="myInfo"><a href="myPage?myPageCategory=0">내 정보</a></li>
        <li id="myAct"><a href="myPage?myPageCategory=1">내 활동</a></li>
        <li id="myTrans"><a href="myPage?myPageCategory=2&myPageTransactionNo=1">거래내역</a></li>
        <li id="myMemberOut"><a href="myPage?myPageCategory=3">탈퇴하기</a></li>
        <!--jsp로 나중에 추가 <li><a href="">관리자 페이지</a></li> 누르면 아예 다른페이지로 이동하기-->
      <%
      	} else {
      %>
      		<li id="myInfo"><a href="myPage?myPageManagerCategory=0">내 정보</a></li>
            <li id="myMemberOut"><a href="myPage?myPageManagerCategory=1">탈퇴하기</a></li>
            <li id="myAdmin"><a href="managerPage?myPageManagerCategory=2&memberManagerNo=1">관리자페이지</a></li>
      <%
      	}
      %>
      </ul>
    </section>
    <!--탭누르면 카테고리별 파라미터 받아서 알맞은 myPageContent보여주기 -->
    <section id="myPageContent">
    <%
      	if(session.getAttribute("memberId")!=null){
    %>
    
	<%
		if(request.getAttribute("myPageParam").equals("0")){
	%>
		<jsp:include page="myPageTemplate/myPageMyInfo.jsp" />		
	<%	
		} else if(request.getAttribute("myPageParam").equals("1")){
	%>
		<jsp:include page="myPageTemplate/myPageMyActivity.jsp" />	
	<%	
		} else if(request.getAttribute("myPageParam").equals("2")){
	%>
		<jsp:include page="myPageTemplate/myPageMyTransaction.jsp" />	
	<%	
		} else if(request.getAttribute("myPageParam").equals("3")){
	%>
		<jsp:include page="myPageTemplate/myPageMemberOut.jsp" />	
	<%	
		}
	%>
	<%
      	} else {
    %>
	<%
		if(request.getAttribute("myPageManagerParam").equals("0")){
	%>
		<jsp:include page="myPageTemplate/myPageManagerInfo.jsp" />		
	<%	
		} else if(request.getAttribute("myPageManagerParam").equals("1")){
	%>
		<jsp:include page="myPageTemplate/myPageManagerOut.jsp" />	
	<%	
		} else if(request.getAttribute("myPageManagerParam").equals("2")){
	%>
		<jsp:include page="myPageTemplate/myPageAdmin.jsp" />	
	<%	
		}
	%>
	<%
      	}
    %>
    </section>
    <%@ include file="/include/footer.jsp"%>
  </body>
</html>