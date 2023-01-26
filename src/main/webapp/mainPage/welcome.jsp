<%@ page language="java" contentType="text/html; charset=UTF-8"
    pageEncoding="UTF-8" import="java.util.*,java.lang.*"%>
<!DOCTYPE html>
<html lang="ko" dir="ltr">

<head>
  <meta charset="utf-8" />
  <title>포트폴리오</title>
  <%@ include file="../include/frontStyle.jsp"%>
  <link rel="stylesheet" href="../assets/css/index.css" />
  <script src="../assets/js/onePage.js" defer></script>
  <script src="../assets/js/section1.js" defer></script>
  <script src="../assets/js/section4.js" defer></script>
  <script src="../assets/js/section3.js" defer></script>
</head>

<body>
<%@ include file="../include/header.jsp" %>
  <main>
    <div class="leftNav">
      <h2 class="hidden">페이지넘버</h2>
      <ul class="count">
        <li><a href="#">1</a></li>
        <li><a href="#">2</a></li>
        <li><a href="#">3</a></li>
        <li><a href="#">4</a></li>
        <li><a href="#">5</a></li>
        <li><a href="#">6</a></li>
        <li><a href="#">6</a></li>
        <li><a href="#">6</a></li>
      </ul>
      <span id="headerBar"></span>
    </div>
    <section class="mainslider onePage1">
      <h2 class="hidden">프로모션</h2>
      <div id="mainsliderBtn">
        <button id="mainslide_btnPrev">이전 슬라이더</button>
        <button id="mainslide_btnNext">다음 슬라이더</button>
      </div>
      <ul class="page1_slides">
        <li class="page1_1 active">
        </li>
        <li class="page1_2">
        </li>
        <li class="page1_3">
        </li>
        <li class="page1_4">
        </li>
      </ul>
    </section>
    <section class="Wrap_hj page3 page onePage3">
      <div class="firstDiv_hj">
        <div id="headerWrap_hj">
          <h2 class="Header3_hj">최근 올라온 판매 상품!</h2>
          <a href="#" class="more_hj">더보기</a>
        </div>
        <div id="SliderWrap_hj">
          <ul class="Slider_hj">
            <li>
              <a href="#">
                <div>
                  이미지
                </div>
                <p class="title_hj">닌텐도 팝니다!1</p>
                <p>2021년도 상품이고, 거의 새거입니다.</p>
              </a>
            </li>
            <li>
              <a href="#">
                <div>
                  이미지
                </div>
                <p class="title_hj">닌텐도 팝니다!2</p>
                <p>2021년도 상품이고, 거의 새거입니다.</p>
              </a>
            </li>
            <li>
              <a href="#">
                <div>
                  이미지
                </div>
                <p class="title_hj">닌텐도 팝니다!3</p>
                <p>2021년도 상품이고, 거의 새거입니다.</p>
              </a>
            </li>
            <li>
              <a href="#">
                <div>
                  이미지
                </div>
                <p class="title_hj">닌텐도 팝니다!4</p>
                <p>2021년도 상품이고, 거의 새거입니다.</p>
              </a>
            </li>
            <li>
              <a href="#">
                <div>
                  이미지
                </div>
                <p class="title_hj">닌텐도 팝니다!5</p>
                <p>2021년도 상품이고, 거의 새거입니다.</p>
              </a>
            </li>
            <li>
              <a href="#">
                <div>
                  이미지
                </div>
                <p class="title_hj">닌텐도 팝니다!6</p>
                <p>2021년도 상품이고, 거의 새거입니다.</p>
              </a>
            </li>
            <li>
              <a href="#">
                <div>
                  이미지
                </div>
                <p class="title_hj">닌텐도 팝니다!7</p>
                <p>2021년도 상품이고, 거의 새거입니다.</p>
              </a>
            </li>
            <li>
              <a href="#">
                <div>
                  이미지
                </div>
                <p class="title_hj">닌텐도 팝니다!8</p>
                <p>2021년도 상품이고, 거의 새거입니다.</p>
              </a>
            </li>
          </ul>
        </div>
        <div id="btnWrap_hj">
          <button class="prev_hj">이전</button>
          <button class="next_hj">다음</button>
        </div>
      </div>
    </section>
    </section>
    <section class="page4 page onePage4">
      <ul class="Category_sec4">
        <li class="check_gory">후기글</li>
        <li>자유게시판</li>
        <li>공략게시판</li>
      </ul>
      <div class="Post_sec4">
        <div>
          <h5>후기글</h5>
          <ul class="review_sec4 check_sec4">
            <li class="List_sec4">
              <a href="#" class="Img_sec4">후기글사진1</a>
              <dl>
                <dt><a href="#">후기글제목1</a></dt>
                <dd class="nick_sec4">별명1</dd>
                <dd class="date_Num_sec4"></dd>
              </dl>
            </li>
            <li class="List_sec4">
              <a href="#" class="Img_sec4">후기글사진2</a>
              <dl>
                <dt><a href="#">후기글제목2</a></dt>
                <dd class="nick_sec4">별명2</dd>
                <dd class="date_Num_sec4"></dd>
              </dl>
            </li>
            <li class="List_sec4">
              <a href="#" class="Img_sec4">후기글사진3</a>
              <dl>
                <dt><a href="#">후기글제목3</a></dt>
                <dd class="nick_sec4">별명3</dd>
                <dd class="date_Num_sec4"></dd>
              </dl>
            </li>
            <li class="List_sec4">
              <a href="#" class="Img_sec4">후기글사진4</a>
              <dl>
                <dt><a href="#">후기글제목4</a></dt>
                <dd class="nick_sec4">별명4</dd>
                <dd class="date_Num_sec4"></dd>
              </dl>
            </li>
            <li class="List_sec4">
              <a href="#" class="Img_sec4">후기글사진5</a>
              <dl>
                <dt><a href="#">후기글제목5</a></dt>
                <dd class="nick_sec4">별명5</dd>
                <dd class="date_Num_sec4"></dd>
              </dl>
            </li>
            <li class="List_sec4">
              <a href="#" class="Img_sec4">후기글사진6</a>
              <dl>
                <dt><a href="#">후기글제목6</a></dt>
                <dd class="nick_sec4">별명6</dd>
                <dd class="date_Num_sec4"></dd>
              </dl>
            </li>
          </ul>
          <ul class="free_sec4">
            <li class="List_sec4">
              <a href="#" class="Img_sec4">자유글사진1</a>
              <dl>
                <dt><a href="#">자유글제목1</a></dt>
                <dd class="nick_sec4">별명1</dd>
                <dd class="date_Num_sec4"></dd>
              </dl>
            </li>
            <li class="List_sec4">
              <a href="#" class="Img_sec4">자유글사진2</a>
              <dl>
                <dt><a href="#">자유글제목2</a></dt>
                <dd class="nick_sec4">별명2</dd>
                <dd class="date_Num_sec4"></dd>
              </dl>
            </li>
            <li class="List_sec4">
              <a href="#" class="Img_sec4">자유글사진3</a>
              <dl>
                <dt><a href="#">자유글제목3</a></dt>
                <dd class="nick_sec4">별명3</dd>
                <dd class="date_Num_sec4"></dd>
              </dl>
            </li>
            <li class="List_sec4">
              <a href="#" class="Img_sec4">자유글사진4</a>
              <dl>
                <dt><a href="#">자유글제목4</a></dt>
                <dd class="nick_sec4">별명4</dd>
                <dd class="date_Num_sec4"></dd>
              </dl>
            </li>
            <li class="List_sec4">
              <a href="#" class="Img_sec4">자유글사진5</a>
              <dl>
                <dt><a href="#">자유글제목5</a></dt>
                <dd class="nick_sec4">별명5</dd>
                <dd class="date_Num_sec4"></dd>
              </dl>
            </li>
            <li class="List_sec4">
              <a href="#" class="Img_sec4">자유글사진6</a>
              <dl>
                <dt><a href="#">자유글제목6</a></dt>
                <dd class="nick_sec4">별명6</dd>
                <dd class="date_Num_sec4"></dd>
              </dl>
            </li>
          </ul>
          <ul class="info_sec4">
            <li class="List_sec4">
              <a href="#" class="Img_sec4">정보글사진1</a>
              <dl>
                <dt><a href="#">정보글제목1</a></dt>
                <dd class="nick_sec4">별명1</dd>
                <dd class="date_Num_sec4"></dd>
              </dl>
            </li>
            <li class="List_sec4">
              <a href="#" class="Img_sec4">정보글사진2</a>
              <dl>
                <dt><a href="#">정보글제목2</a></dt>
                <dd class="nick_sec4">별명2</dd>
                <dd class="date_Num_sec4"></dd>
              </dl>
            </li>
            <li class="List_sec4">
              <a href="#" class="Img_sec4">정보글사진3</a>
              <dl>
                <dt><a href="#">정보글제목3</a></dt>
                <dd class="nick_sec4">별명3</dd>
                <dd class="date_Num_sec4"></dd>
              </dl>
            </li>
            <li class="List_sec4">
              <a href="#" class="Img_sec4">정보글사진4</a>
              <dl>
                <dt><a href="#">정보글제목4</a></dt>
                <dd class="nick_sec4">별명4</dd>
                <dd class="date_Num_sec4"></dd>
              </dl>
            </li>
            <li class="List_sec4">
              <a href="#" class="Img_sec4">정보글사진5</a>
              <dl>
                <dt><a href="#">정보글제목5</a></dt>
                <dd class="nick_sec4">별명5</dd>
                <dd class="date_Num_sec4"></dd>
              </dl>
            </li>
            <li class="List_sec4">
              <a href="#" class="Img_sec4">정보글사진6</a>
              <dl>
                <dt><a href="#">정보글제목6</a></dt>
                <dd class="nick_sec4">별명6</dd>
                <dd class="date_Num_sec4"></dd>
              </dl>
            </li>
          </ul>
        </div>
      </div>
    </section>
    <section class="page5 page onePage5">
      <div>
        <div id="noticeWrap_hj">
          <h2 class="Header_hj"><span>공지사항</span></h2>
          <ul class="noticeUl_hj">
            <li>
              <p>4</p>
              <p><a href="#">공지사항입니다.</a></p>
              <p>조회수</p>
            </li>
            <li>
              <p>3</p>
              <p><a href="#">공지사항입니다.</a></p>
              <p>조회수</p>
            </li>
            <li>
              <p>2</p>
              <p><a href="#">공지사항입니다.</a></p>
              <p>조회수</p>
            </a></li>
            <li>
              <p>1</p>
              <p><a href="#">공지사항입니다.</a></p>
              <p>조회수</p>
            </li>
          </ul>
          <a href="#" class="gopage_hj">공지사항 바로가기</a>
        </div>
        <div id="reportWrap_hj">
          <h2 class="Header_hj"><span>부정거래 신고</span></h2>
          <div class="reportExplan">
            <p>공정한 거래를 위해 부정거래 발생 시 신고해주세요.</p>
            <div>이미지</div>
          </div>
          <a href="#" class="gopage_hj">신고하기 바로가기</a>
        </div>
      </div>
    </section>
  </main>
  <%@ include file="../include/floatingMenu.jsp"%>

	<%@ include file="../include/footer.jsp" %>
</body>
</html>

