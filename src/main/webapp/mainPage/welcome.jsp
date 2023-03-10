<%@ page language="java" contentType="text/html; charset=UTF-8"
    pageEncoding="UTF-8" import="java.util.*,java.lang.*,dto.PromotionDto,dto.MemberDto,dao.MyPageDao"%>
<%@ page import="dao.NoticeDao" %>
<%@ page import="dto.NoticeDto" %>
<%@ page import="dao.MarketDao" %>
<%@ page import="dto.MarketDto" %>
<!DOCTYPE html>
<html lang="ko" dir="ltr">

<head>
  <meta charset="utf-8" />
  <title>포트폴리오</title>
  <%@ include file="../include/frontStyle.jsp"%>
  <link rel="stylesheet" href="../assets/css/index.css?v=3" />
  <link rel="stylesheet" href="../assets/css/mainPromotion.css" />
  <script src="../assets/js/section1.js" defer></script>
  <script src="../assets/js/section3.js" defer></script>
  <script src="../assets/js/onePage.js" defer></script>
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
    <section class="Wrap_hj page3 page onePage2">
      <div class="firstDiv_hj">
        <div id="headerWrap_hj">
          <h2 class="Header3_hj">최근 올라온 판매 상품!</h2>
          <a href="#" class="more_hj">더보기</a>
        </div>
        <div id="SliderWrap_hj">
          <ul class="Slider_hj">
	          <%
	          ArrayList<MarketDto> postList = (ArrayList<MarketDto>)request.getAttribute("postList");
	          if(postList != null){
	        	  for(MarketDto post : postList){
	        		  String titleImg = post.getImage()[0] == null ?
	        				  "/assets/image/default_image.png" : "/img/" + post.getImage()[0];
	          %>
            <li>
              <a href="/marketPostInfo?no=<%= post.getMarketNo()%>">
                <div>
                  <img src=<%= titleImg %> alt="<%= post.getMarketNo() %>번 글 이미지">
                </div>
                <p class="title_hj"><%= post.getTitle() %></p>
                <p><%= post.getWriteDate() %></p>
              </a>
            </li>
            <%
	        	  }
	          }
            %>
          </ul>
        </div>
        <div id="btnWrap_hj">
          <button class="prev_hj">이전</button>
          <button class="next_hj">다음</button>
        </div>
      </div>
    </section>
    <section class="page5 page onePage3">
      <div>
        <div id="noticeWrap_hj">
          <h2 class="Header_hj"><span>공지사항</span></h2>
          <ul class="noticeUl_hj">
	          <%
	          // 공지사항 리스트 불러오기
	          ArrayList<NoticeDto> noticeList = new NoticeDao().showNotice(1);
	          if(noticeList == null){
        	  %>
        	  	<li>
              <p>0</p>
              <p><a href="#">아직 공지사항이 없습니다.</a></p>
              <p>-</p>
            </li>
        	  <%
	          } else {
		          for(int i=0; i<4; i++){
		          %>
	            <li>
	              <p><%=noticeList.get(i).getNoticeNo() %></p>
	              <p><a href="/notice/showNotice.jsp?noticeNo=<%=noticeList.get(i).getNoticeNo()%>"><%=noticeList.get(i).getTitle() %></a></p>
	              <p><%=noticeList.get(i).getRegDate() %></p>
	            </li>
	            <%
	          	}
	          }
            %>
          </ul>
          <a href="#" class="gopage_hj">공지사항 바로가기</a>
        </div>
      </div>
    </section>
  </main>
  <%@ include file="../include/floatingMenu.jsp"%>

	<%@ include file="../include/footer.jsp" %>
</body>
</html>

