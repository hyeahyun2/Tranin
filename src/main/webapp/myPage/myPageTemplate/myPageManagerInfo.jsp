<%@ page language="java" contentType="text/html; charset=UTF-8"
    pageEncoding="UTF-8" import="java.util.*,java.lang.*,dto.ManagerDto,dao.MyPageDao"%>
<%
	MyPageDao dao=new MyPageDao();
	String id = (String)session.getAttribute("manager");
	ManagerDto loginedManager = dao.getManagerById(id);
%>
<script src="https://spi.maps.daum.net/imap/map_js_init/postcode.v2.js"></script>
<section id="myPageMyInfo">
	<form accept-charset="UTF-8" id="myPageMyInfoForm" action="myPageManagerInfoModify" method="post">
		<ul>
			<li><span>아이디</span><div><input type="text" readonly name="myPageMyInfoId" value="<%=loginedManager.getId()%>"></div></li>
			<li><span>패스워드 확인</span><div><input type="password" id="myPageMyInfoPasswordConfirm" autocomplete="off" name="myPageMyInfoPasswordConfirm" value=""><input class="button-68 button-68-small" type="button" id="pwModi" value="패스워드 변경"></div></li>
			<li><span>관리자 이름</span><div><input type="text" readonly name="myPageMyInfoName" value="<%=loginedManager.getName()%>"></div></li>
			<li><input class="button-68" type="submit" name="myPageMyInfoModify" value="수정하기"></li>
		</ul>
	</form>
	<script>
		const myInfo = document.querySelector("#myInfo");
		myInfo.classList.add("active");
		
		const pwModi = document.querySelector("#pwModi");
		pwModi.addEventListener('click',function(){
			const xhr = new XMLHttpRequest();
			let a = prompt('기존 비밀번호를 입력하세요','기존 비밀번호');
			let b = prompt('새로운 비밀번호를 입력하세요','기존 비밀번호');
			let myJSON = "{"+'"pre"'+":"+'"'+a+'"'+ ',"new"'+":"+'"'+b+'"'+"}";
			let myObj = JSON.parse(myJSON);
			console.log(myJSON);
			console.log(myObj);
			xhr.open('POST', "myPageManagerInfoPwModify", true);
			xhr.responseType = "json";
			xhr.setRequestHeader('Content-Type', 'application/json');
			xhr.send(JSON.stringify(myObj));
			xhr.onreadystatechange = () => {
				if(xhr.readyState !== XMLHttpRequest.DONE) return;
				if(xhr.status === 200){
					console.log(xhr.response);
					if(xhr.response=="1"){
						alert('패스워드 변경 성공!');
						location.href='myPage?myPageCategory=0';
					}else{
						alert('패스워드가 다릅니다. 다시 입력해주세요');
						location.href='myPage?myPageCategory=0';
					}
				}
				else {
					console.error('Error',xhr.status,xhr.statusText);
				}
			}
		});
	</script>
</section>