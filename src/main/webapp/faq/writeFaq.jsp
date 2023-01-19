<%@ page language="java" contentType="text/html; charset=UTF-8"
    pageEncoding="UTF-8"%>
<!DOCTYPE html>
<html>
<head>
<meta charset="UTF-8">
<title>자주묻는 질문 등록</title>
<link rel="stylesheet" href="../assets/css/common.css">
<link rel="stylesheet" href="../assets/css/reset.css">
<link rel="stylesheet" href="../assets/css/footer.css">
<link rel="stylesheet" href="../assets/css/writeFaq.css">
<script src="assets/js/common.js" defer></script>
<script src="assets/js/header.js" defer></script>
</head>
<body>
	<script>
		
		function checkForm() {
			var form = document.getElementById("form");
			var title = document.getElementById("title");
			var content = document.getElementById("content");
			
			if(title.value === "") {
				alert("제목을 입력하세요!!");
				title.focus();
				return false;
			};
			if(content.value === "") {
				alert("내용을 입력하세요!");
				content.focus();
				return false;
			};
			
			//if (title.value != "" && content.value != "") {
			//	confirm("게시물을 등록하시겠습니까?");
			//	return false;
			//};
			
			
			if(confirm("게시물을 등록하시겠습니까?")){
				form.submit();
			}
			
		}
	</script>
	<%@ include file="../include/header.jsp" %>
	<section id="writeFaqWrap_hj">
        <div id="writeFaqHeaderWrap_hj">
            <h2 class="writeFaqHeader_hj">자주 묻는 질문 글쓰기</h2>
            <p>새로운 질문과 답변을 작성하세요.</p>
        </div>
        <form action="/writeFaqServlet" id="form" method="post">
        	<p><input type="hidden" name="faqNo"> 
            <p>제목<br><input type="text" name="title" id="title" size="50" placeholder="제목을 입력하세요."></p>
            <p>내용<br><textarea name="content" id="content" cols="48" rows="30" placeholder="내용을 입력하세요."></textarea></p>
            <input type="button" value="등록" onclick="checkForm()">  
        </form> 
        <a href="faq.jsp" id="goBack_hj">돌아가기</a>
    </section>
    <%@ include file="../include/footer.jsp" %>
</body>
</html>