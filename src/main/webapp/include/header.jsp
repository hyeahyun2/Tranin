<%@ page language="java" contentType="text/html; charset=UTF-8"
    pageEncoding="UTF-8" import="java.util.*,java.lang.*"%>

  <div id="upperSmallNav">
    <h1 class="hidden">기타관련</h1>
    <%
    String memberId = null;
    
	
    String manager = null;
    manager = (String)session.getAttribute("manager");

    // 자동 로그인 쿠키 있을 경우
    Cookie[] c = request.getCookies();
    if (c != null) {
      for (Cookie cf : c) {
        if (cf.getName().equals("autoLogin")){
          String ids = cf.getValue();
          session.setAttribute("memberId", ids);
        }
      }
    }
    memberId = (String)session.getAttribute("memberId");
    String id = (String) session.getAttribute("id");

    // 현재 페이지
    String url = request.getRequestURI();
    
    if(memberId != null){
    	%>
    <a><%=memberId.split("@")[0]%>님 환영합니다!</a>
   	<a href='../myPage/myPage'>마이페이지</a>
   	<a href="/memberLogout?url=<%= url %>" class='logOut'>로그아웃</a>
    	<%
    }
    else if(manager != null) {
    	%>

    <a><%=manager%>님 환영합니다!</a>
   	<a href='../myPage/myPage'>관리페이지</a>
   	<a href="/memberLogout?url=<%= url %>" class='logOut'>로그아웃</a>

    	<%
    } else if(id != null) {
        %>

    <a><%=id.split("@")[0]%>님 환영합니다!</a>
    <a href='../myPage/myPage'>관리페이지</a>
    <a href="/memberLogout?url=<%= url %>" class='logOut'>로그아웃</a>

    <%
    } else {
    	
    	%>

    <a href="../member/login.jsp?url=<%= url %>">로그인</a>
    <a href="../manage/manageLogin.jsp">관리자 로그인</a>
    <a href="../member/register.jsp">(구)회원가입</a>
    <a href="../member/TOS.jsp">(신)회원가입</a>

    	<%
    }
    	%>
  </div>
  <div id="headerWrap">
    <header id="header">
      <h1 class="logo"><a href="../mainPage/welcome.jsp">logo</a></h1>
      <nav id="gnb">
        <h2 class="hidden">주요이용메뉴</h2>
        <ul id="gnbList">
          <li class="gnbListChild"><a href="../market/market.jsp">장터</a></li>
          <li class="gnbListChild"><a href="#">삽니다</a></li>
          <li class="gnbListChild"><a href="#">커뮤니티</a>
            <ul class="snb">
              <li><a href="#">후기</a></li>
              <li><a href="#">인기글</a></li>
              <li><a href="#">자유게시판</a></li>
              <li><a href="#">정보게시판</a></li>
            </ul>
          </li>
          <li class="gnbListChild"><a href="#">고객센터</a>
            <ul class="snb">
              <li><a href="../notice/notice.jsp">공지사항</a></li>
              <li><a href="../faq/faq.jsp">FAQ</a></li>
              <li><a href="#">신고하기</a></li>
            </ul>
          </li>
        </ul>
      </nav>
    </header>
  </div>