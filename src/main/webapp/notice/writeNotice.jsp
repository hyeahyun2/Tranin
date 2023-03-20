<%@ page language="java" contentType="text/html; charset=UTF-8"
    pageEncoding="UTF-8"%>
<!DOCTYPE html>
<html lang="ko">
<head>
    <meta charset="UTF-8">
    <meta http-equiv="X-UA-Compatible" content="IE=edge">
    <meta name="viewport" content="width=device-width, initial-scale=1.0">
    <title>공지사항 글쓰기</title>
    <link rel="stylesheet" href="../assets/css/common.css">
	<link rel="stylesheet" href="../assets/css/reset.css">
	<link rel="stylesheet" href="../assets/css/writeNotice.css">
	<link rel="stylesheet" href="../assets/css/footer.css">
	<script src="assets/js/header.js" defer></script>
</head>
<body>
<%@ include file="../include/header.jsp"%>
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

    <section id="writeNoticeWrap_hj">
        <div id="writeNoticeHeaderWrap_hj">
            <h2 class="writeNoticeHeader_hj">공지 사항 글쓰기</h2>
            <p>새로운 공지사항을 작성하세요.</p>
        </div>
        <form action="/writeNoticeServlet" id="form" method="post">
        	<p><input type="hidden" name="noticeNo"> 
            <p>제목<br><input type="text" name="title" id="title" size="50" placeholder="제목을 입력하세요."></p>
            <p>내용<br><textarea name="content" id="content" cols="48" rows="30" placeholder="내용을 입력하세요."></textarea></p>
            <input type="button" value="등록" class="registerNotice_hj" onclick="checkForm()">  
        </form> 
        <a href="notice.jsp" id="goBack_hj">돌아가기</a>
    </section>
    <%@ include file="../include/footer.jsp"%>
</body>
</html>