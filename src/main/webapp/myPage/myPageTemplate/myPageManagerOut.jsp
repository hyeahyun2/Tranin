<%@ page language="java" contentType="text/html; charset=UTF-8"
    pageEncoding="UTF-8"%>
<section id="myPageMemberOut">
  <div id="myPageMemberOutInnerWrap">
  	<form accept-charset="UTF-8" id="myPageMemberOutForm" action="myPageManagerOut" method="post">
  		<a href="#">비밀번호 확인<input type="text" name="pw" value=""></a>
   		<a class="button-68" id="myPageMemberOutBtn">탈퇴하기</a>
  	</form>
  </div>
</section>
<script>
	const myMemberOut = document.querySelector("#myMemberOut");
	const myPageMemberOutBtn = document.querySelector("#myPageMemberOutBtn")
	const myPageMemberOutForm = document.querySelector("#myPageMemberOutForm");
	myMemberOut.classList.add("active");
	myPageMemberOutBtn.addEventListener('click',function(){
		if(confirm("정말 탈퇴하시겠습니까?")){
			myPageMemberOutForm.submit();	
		}else{
			
		}
	});
</script>