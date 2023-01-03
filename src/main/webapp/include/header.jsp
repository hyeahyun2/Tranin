<%@ page language="java" contentType="text/html; charset=UTF-8"
    pageEncoding="UTF-8" import="java.util.*,java.lang.*"%>
  <div id="upperSmallNav">
    <h1 class="hidden">기타관련</h1>
    <%
    String nickname = null;
    nickname = (String)session.getAttribute("nickname");
    if(nickname != null){
    	%>
    <a><%=nickname%>님 환영합니다!</a>
   	<a href='myPage'>마이페이지</a>
   	<a href="../UM/logout.jsp" class='logOut'>로그아웃</a>
    	<%
    }
    else {
    	%>
    <a href="../UM/login.jsp">로그인</a>
    <a href="../UM/register.jsp">회원가입</a>
    	<%
    	
    }
    	%>
  </div>
  <div id="headerWrap">
    <header id="header">
      <h1 class="logo"><a href="./index.html">logo</a></h1>
      <nav id="gnb">
        <h2 class="hidden">주요이용메뉴</h2>
        <ul id="gnbList">
          <li class="gnbListChild"><a href="../sale/sale.jsp">팝니다</a></li>
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
              <li><a href="../notice.html">공지사항</a></li>
              <li><a href="../FAQ.html">FAQ</a></li>
              <li><a href="#">신고하기</a></li>
            </ul>
          </li>
        </ul>
      </nav>
    </header>
  </div>