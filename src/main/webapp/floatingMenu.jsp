<%@ page language="java" contentType="text/html; charset=UTF-8"
    pageEncoding="UTF-8"%>
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