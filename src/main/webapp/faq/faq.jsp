<%@ page language="java" contentType="text/html; charset=UTF-8"
    pageEncoding="UTF-8"%>
<%@ page import="java.sql.*" %>
<%@ page import="java.util.*" %>
<%@ page import="dao.DBProperty" %>
<%@ page import="dao.FaqShowDao" %>
<%@ page import="dao.FaqPagingDao" %>
<%@ page import="dto.FaqDto" %>
<!DOCTYPE html>
<html lang="ko">

<head>
	<meta charset="UTF-8">
	<meta http-equiv="X-UA-Compatible" content="IE=edge">
	<meta name="viewport" content="width=device-width, initial-scale=1.0">
	<title>자주 찾는 질문</title>
	<link rel="stylesheet" href="../assets/css/common.css">
	<link rel="stylesheet" href="../assets/css/reset.css">
	<link rel="stylesheet" href="../assets/css/faq.css">
	<link rel="stylesheet" href="../assets/css/footer.css">
	<script type="text/javascript" src="../assets/js/jquery-3.6.1.js"></script>
  	<script type="text/javascript" src="../assets/js/jquery-migrate-1.4.1.min.js"></script>
  	<script type="text/javascript" src="../assets/js/jquery-ui.js"></script>
	<script src="../assets/js/common.js" defer></script>
  	<script src="../assets/js/header.js" defer></script>
	<script src="../assets/js/faq.js" defer></script>

</head>

<body>
	<%@ include file="../include/header.jsp" %>
	<section id="faqWrap_hj">
		<div id="faqHeaderWrap_hj">
			<h2 class="faqheader_hj">자주 찾는 질문</h2>
		</div>
		<div id="listWrap_hj">
			<ul class="faqUl_hj">
				<%				
					FaqShowDao dao = new FaqShowDao();
					int pageNum = 1;
					if(request.getParameter("pageNum") != null) { // 페이지 번호가 전달이 된 경우
						pageNum = Integer.parseInt(request.getParameter("pageNum"));					
					}
		        	ArrayList<FaqDto> list = dao.showFaq(pageNum);
		        	
		        	for(FaqDto a : list) {
				%>
				<li class="group_hj">
					<div class="question_hj">
						<span></span><span></span><span></span><span></span>
						Q. <%=a.getTitle()%></div>
					<ul class="sub_hj">
						<li>A. <%=a.getContent()%> </li>
					</ul>
				</li>
				<%
		        	}
				%>
				<%-- 
				<li class="group_hj">
					<div class="question_hj">
						<span></span><span></span><span></span><span></span>
						Q. 아이디가 기억나지 않습니다.</div>
					<ul class="sub_hj">
						<li>A. 로그인 화면에서 아이디찾기 기능을 활용하시면 잊어버리신 아이디를 찾을 수 있습니다. </li>
					</ul>
				</li>
				<li class="group_hj">
					<div class="question_hj">
						<span></span><span></span><span></span><span></span>
						Q. 비밀번호는 어떻게 찾나요?</div>
					<ul class="sub_hj">
						<li>A. 로그인 화면에서 비밀번호 찾기를 통해 잊어버리신 비밀번호를 찾기 또는 재설정 하실 수 있습니다.</li>
					</ul>
				</li>
				<li class="group_hj">
					<div class="question_hj">
						<span></span><span></span><span></span><span></span>
						Q. 계좌 등록은 어떻게 하나요?</div>
					<ul class="sub_hj">
						<li>A. 마이페이지의 내 정보 > 계좌 등록을 통해 결제 계좌 등록을 하실 수 있습니다.</li>
					</ul>
				</li>
				<li class="group_hj">
					<div class="question_hj">
						<span></span><span></span><span></span><span></span>
						Q. 판매글은 어떻게 작성하나요?</div>
					<ul class="sub_hj">
						<li>A. 팝니다 메뉴에 들어가신 후 글쓰기 기능을 통해 판매 게시글 작성이 가능합니다. </li>
					</ul>
				</li>
				<li class="group_hj">
					<div class="question_hj">
						<span></span><span></span><span></span><span></span>
						Q. 사고 싶은 물품을 어떻게 찾나요?</div>
					<ul class="sub_hj">
						<li>A. 팝니다 게시판에서 검색기능을 통해 찾으실 수 있습니다. 
							가격 낮은순, 인기글, 조회수 높은 글순 등 다양하게 검색할 수 있으니 
							해당 기능을<br>활용하여 고객님의 맞추어진 구매를 하시기 바랍니다. 
						</li>
					</ul>
				</li>
				<li class="group_hj">
					<div class="question_hj">
						<span></span><span></span><span></span><span></span>
						Q. 저는 바보인가요?</div>
					<ul class="sub_hj">
						<li>A. 네 맞습니다.</li>
					</ul>
				</li>
				--%>
			</ul>
		</div>
		<div>
			<%
	    	int cntListPerPage = 10;
	   		
	    	FaqPagingDao npd = new FaqPagingDao();
	    	ResultSet rs = null;
				rs = npd.getAllFaq();    		
	
			rs.next();
			int totalRecord = rs.getInt(1);
			int totalPage = (totalRecord % cntListPerPage == 0) ? totalRecord / cntListPerPage : (totalRecord / cntListPerPage) + 1;
	    	
			int block = 5; // 페이지 나올 범위
			int blockTotal = totalPage % block == 0 ? totalPage / block : totalPage / block + 1; // 총 블럭의 수
			int blockThis = ((pageNum - 1) / block) + 1; // 현재 페이지의 블럭
			int blockThisFirstPage = ((blockThis - 1) * block) + 1; // 현재 블럭의 첫 페이지
			int blockThisLastPage = blockThis * block; // 현재 블럭의 마지막 페이지
			// out.println(blockThisLastPage);
			blockThisLastPage = (blockThisLastPage > totalPage) ? totalPage : blockThisLastPage; 
	    	%>
	        <a href="notice.jsp?pageNum=1">첫 페이지</a>
	        <% 
	        if (blockThis > 1) {
	        %>
	        <a href="notice.jsp?pageNum=<%=(blockThisFirstPage - 1)%>">앞으로</a>
	        <% 
	        }
	        
	       	for(int i = blockThisFirstPage; i <= blockThisLastPage; i++) {
	        %>
	        <a href="notice.jsp?pageNum=<%=i%>" class="num"><%=i%></a>
	        <%
	        }
	        %>
	        <%-- 
	        <li><a href="#" class="num">2</a></li>
	        <li><a href="#" class="num">3</a></li>
	        <li><a href="#" class="num">4</a></li>
	        <li><a href="#" class="num">5</a></li>
	        --%>
	        <%
	        if(blockThis < blockTotal) {
	        %>
	        <a href="notice.jsp?pageNum=<%=(blockThisLastPage + 1)%>">뒤로</a>
	        <%
	        }
	        %>
	        <a href="notice.jsp?pageNum=<%=totalPage%>">마지막 페이지</a>
		</div>
		<div id="counsel_hj">
			<a href="#">상담사 연결</a>
		</div>
			<%--
         		if(manager != null) {
       		--%>
		<div>
			<a href="./writeFaq.jsp">글쓰기</a>
		</div>
			<%--
    			}
    		--%>
	</section>
	<%@ include file="../include/footer.jsp" %>
</body>

</html>