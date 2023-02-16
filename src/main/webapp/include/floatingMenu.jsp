<%@ page language="java" contentType="text/html; charset=UTF-8"
    pageEncoding="UTF-8"%>

<script src="../assets/js/floatingMenu.js?v=3" defer></script>

<section id="rightQuick">
    <div class="rightQuickMenu">
      <h2 class="hidden">rightQuick</h2>
      <nav>
        <ul>
          <li id="chatTag">
            <a href="../chat/chatMain.jsp">채팅상담<span class="isNewMsg">새 메세지</span></a>
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
          <input id="rightSearchText" type="text" name="keyword" placeholder="검색어를 입력하세요">
          <select id="rightSearchSelect" name="searchCategory" id="searchCategory">
            <option value="장터">장터</option>
            <option value="공지사항">공지사항</option>
          </select>
          <input id="rightSearchBtn" class="button-68" type="submit" value="검색">
        </fieldset>
      </form>
      <button class="button-68" id="rightSearchClose">닫기</button>
    </section>
  </section>