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
    <section id="rightQuick">
      <div class="rightQuickMenu">
        <h2 class="hidden">rightQuick</h2>
        <nav>
          <ul>
            <li>
              <a href="#">채팅상담</a>
            </li>
            <li>
              <a href="#">찜목록</a>
            </li>
            <li>
              <a href="#">장바구니</a>
            </li>
            <li id="rightSearch">
              <a href="#">검색</a>
            </li>
          </ul>
        </nav>
      </div>
      <section id="rightSearchPage">
        <form action="/mainSearch" method="get" id="searchWrap">
          <fieldset>
            <legend>검색하기</legend>
            <input type="text" name="keyword" placeholder="검색어를 입력하세요">
            <select name="searchCategory" id="cars">
              <option value="제목">제목</option>
              <option value="내용">내용</option>
              <option value="제목내용">제목+내용</option>
              <option value="글쓴이">글쓴이</option>
            </select>
            <input type="submit" value="검색">
          </fieldset>
        </form>
        <button id="rightSearchClose">검색창 닫기</button>
      </section>
    </section>
    <section id="myPageTab">
      <h2>마이페이지</h2>
      <ul>
      <%
      	if(session.getAttribute("memberId")!=null){
      %>
        <li id="myInfo"><a href="myPage?myPageCategory=0">내 정보</a></li>
        <li id="myAct"><a href="myPage?myPageCategory=1">내 활동</a></li>
        <li id="myTrans"><a href="myPage?myPageCategory=2">구매내역</a></li>
        <li id="myMemberOut"><a href="myPage?myPageCategory=3">탈퇴하기</a></li>
        <!--jsp로 나중에 추가 <li><a href="">관리자 페이지</a></li> 누르면 아예 다른페이지로 이동하기-->
      <%
      	} else {
      %>
      		<li id="myInfo"><a href="myPage?myPageManagerCategory=0">내 정보</a></li>
            <li id="myMemberOut"><a href="myPage?myPageManagerCategory=1">탈퇴하기</a></li>
            <li id="myAdmin"><a href="myPage?myPageManagerCategory=2&memberManagerNo=1">관리자페이지</a></li>
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
    <div class="footerWrap page onePage6">
      <footer class="page8" id="footer">
        <p class="us">about us</p>
        <div id="membersWrap">
          <p class="member0_sh member_sh"><a href="#" target="_blank">개인정보처리방침</a></p>
          <p class="member1_sh member_sh"><a href="hyehyun.html" target="_blank">김혜현</a></p>
          <p class="member2_sh member_sh"><a href="beomsu.html" target="_blank">오범수</a></p>
          <p class="member3_sh member_sh"><a href="haejun.html" target="_blank">오해준</a></p>
          <p class="member4_sh member_sh"><a href="https://suhyun980716.github.io/LandingPage/Landing%20Page/index.html" target="_blank">유수현</a></p>
          <p class="member5_sh member_sh"><a href="https://github.com/hyeahyun2/TeamProject" target="_blank">GitHub</a></p>
        </div>
      </footer>
    </div>
  </body>
</html>
