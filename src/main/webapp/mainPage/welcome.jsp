<%@ page language="java" contentType="text/html; charset=UTF-8"
    pageEncoding="UTF-8" import="java.util.*,java.lang.*,dto.PromotionDto,dto.MemberDto,dao.MyPageDao"%>
<!DOCTYPE html>
<html lang="ko" dir="ltr">

<head>
  <meta charset="utf-8" />
  <title>포트폴리오</title>
  <%@ include file="../include/frontStyle.jsp"%>
  <link rel="stylesheet" href="../assets/css/index.css" />
  <link rel="stylesheet" href="../assets/css/mainPromotion.css" />
  <script src="../assets/js/section1.js" defer></script>
  <script src="../assets/js/section3.js" defer></script>
  <script src="../assets/js/onePage.js?v=2" defer></script>
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
      	<%	
      		MyPageDao myPageDao = new MyPageDao();
      		List<PromotionDto> promo = (List<PromotionDto>)request.getAttribute("promotionList");
      		for(int i=1;i<5;i++){
      			String imgUrl = "";
      			String fileName = "";
      			if(promo.get(i-1).getImage()!=null){
      				imgUrl = promo.get(i-1).getImage();
          			fileName = imgUrl.substring(imgUrl.lastIndexOf("/") + 1);
      			}
      	%>      	
      		<li class="page1_<%=i%> <%if(i==1)out.print("active");%>">
	        	<article class="promotionContent">
	        		<img src="/img/<%= fileName %>"  alt="<%= fileName %>">
	        		<p><%=promo.get(i-1).getTitle()%></p>
	        		<p><%if(promo.get(i-1).getPart().equals("sell")) out.print("현재 판매가는");else out.print("현재 매입가는");%><%=promo.get(i-1).getPrice()%> 원 입니다.</p>
	        		<p><%=myPageDao.getMemberByNo(promo.get(i-1).getWriterNo()).getNickName()%> 님이 등록하셨습니다!</p>
	        		<p><%=promo.get(i-1).getWriteDate()%> 에 등록되었습니다!</p>
	        	</article>
       		</li>
       		<script>
       			document.querySelector(".page1_<%=i%> article").addEventListener('click',function(){
       				location.href="/marketPostInfo?no=<%=promo.get(i-1).getMarketNo()%>"
       			});
       		</script>
      	<%
      		}
      	%>
      	<!-- 
        <li class="page1_1 active">
        	<article class="promotionContent">
        		<a class="promotionContentImage" href="#"></a>
        		<p>제목1</p>
        		<p>가격1</p>
        		<p>판매자1</p>
        	</article>
      	</li>
        <li class="page1_2">
        	<article class="promotionContent">
        		<a class="promotionContentImage" href="#"></a>
        		<p>제목2</p>
        		<p>가격2</p>
        		<p>판매자2</p>
        	</article>
        </li>
        <li class="page1_3">
        	<article class="promotionContent">
        		<a class="promotionContentImage" href="#"></a>
        		<p>제목3</p>
        		<p>가격3</p>
        		<p>판매자3</p>
        	</article>
        </li>
        <li class="page1_4">
        	<article class="promotionContent">
        		<a class="promotionContentImage" href="#"></a>
        		<p>제목4</p>
        		<p>가격4</p>
        		<p>판매자4</p>
        	</article>
        </li>
         -->
      </ul>
    </section>
    <section class="page2 page onePage2">
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
    <section class="page4 page onePage4">
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

