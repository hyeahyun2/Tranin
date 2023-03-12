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
	<div hidden id="dialog1" title="기존 비밀번호를 입력해주세요">
   		<input id="dialog1_input" type="password" size="25" />
	</div>
	<div hidden id="dialog2" title="새로운 비밀번호를 입력해주세요">
   		<input id="dialog2_input" type="password" size="25" />
	</div>
	
	<script>
		const myInfo = document.querySelector("#myInfo");
		myInfo.classList.add("active");
		
		const pwModi = document.querySelector("#pwModi");
		let a;
		let b;
		pwModi.addEventListener('click',function(){
			document.getElementById('dialog1_input').value = null;
			document.getElementById('dialog2_input').value = null;
			
				$( "#dialog1" ).dialog({
					'buttons': {
				        'Confirm': function(event) {
				            a = document.getElementById('dialog1_input').value;
				            $( "#dialog2" ).dialog({
								'buttons': {
							        'Confirm': function(event) {
							            b = document.getElementById('dialog2_input').value;
							            //validation
							            let regExpPw = /^(?=.*[a-z])(?=.*\d)(?=.*[$@$!%*?&])[A-Za-z\d$@$!%*?&]{8,}/;
							            if(!regExpPw.test(b)){
							            	if (window.confirm("영문자, 숫자, 특수 문자 각각 하나 이상을 포함하여 8자 이상으로 작성하세요."))
							            	{
							            	    // They clicked Yes
							            		location.href="myPage?myPageCategory=0";
							            	}
							            	else
							            	{
							            	    // They clicked no
							            		location.href="myPage?myPageCategory=0";
							            	}
							            }else{
							            	//ajax
								            const xhr = new XMLHttpRequest();
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
							            }
							        }
							    }
							})
				        }
				    }
				});
				
			});
			
			
			/*
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
			*/
	</script>
</section>