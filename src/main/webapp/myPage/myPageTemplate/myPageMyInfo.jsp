<%@ page language="java" contentType="text/html; charset=UTF-8"
    pageEncoding="UTF-8" import="java.util.*,java.lang.*,dto.MemberDto,dao.MyPageDao"%>
<%
	MyPageDao dao=new MyPageDao();
	String memberId = (String)session.getAttribute("memberId");
	MemberDto loginedMember = dao.getMemberById(memberId);
%>
<script src="https://spi.maps.daum.net/imap/map_js_init/postcode.v2.js"></script>
<section id="myPageMyInfo">
	<form accept-charset="UTF-8" id="myPageMyInfoForm" action="myPageMyInfoModify" method="post">
		<ul>
			<li><span>아이디</span><div><input type="text" readonly name="myPageMyInfoId" value="<%=loginedMember.getId()%>"></div></li>
			<li><span>패스워드 확인</span><div><input type="password" autocomplete="off" name="myPageMyInfoPasswordConfirm" value=""><input class="button-68 button-68-small" type="button" id="pwModi" value="패스워드 변경"></div></li>
			<li><span>별명</span><div><input type="text" readonly name="myPageMyInfoNickName" value="<%=loginedMember.getNickName()%>"></div></li>
			<li><span>우편번호</span><div><input id="zipcode" type="text" name="myPageMyZipCode" readonly placeholder=" 우편번호" value="<%=loginedMember.getZipCode()%>"> <span class="button-68 button-68-small" id="dblCheck3" style="cursor:pointer"> 우편번호 검색</span></div> </li>
            <li><span>주소</span><div><input id="address01" type="text" name="myPageMyAddress" placeholder=" 주소" value="<%=loginedMember.getAddress()%>"></div></li>
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
	const hey = document.querySelector('#dblCheck3')

	hey.addEventListener('click', function(){
	    new daum.Postcode({
	        oncomplete: function(data) {
	            // 팝업에서 검색결과 항목을 클릭했을때 실행할 코드를 작성하는 부분.

	            // 각 주소의 노출 규칙에 따라 주소를 조합한다.
	            // 내려오는 변수가 값이 없는 경우엔 공백('')값을 가지므로, 이를 참고하여 분기 한다.
	            var fullAddr = ''; // 최종 주소 변수
	            var extraAddr = ''; // 조합형 주소 변수

	            // 사용자가 선택한 주소 타입에 따라 해당 주소 값을 가져온다.
	            if (data.userSelectedType === 'R') { // 사용자가 도로명 주소를 선택했을 경우
	                fullAddr = data.roadAddress;
	            }
	            else { // 사용자가 지번 주소를 선택했을 경우(J)
	                fullAddr = data.jibunAddress;
	            }

	            // 사용자가 선택한 주소가 도로명 타입일때 조합한다.
	            if(data.userSelectedType === 'R'){
	                //법정동명이 있을 경우 추가한다.
	                if(data.bname !== ''){
	                    extraAddr += data.bname;
	                }
	                // 건물명이 있을 경우 추가한다.
	                if(data.buildingName !== ''){
	                    extraAddr += (extraAddr !== '' ? ', ' + data.buildingName : data.buildingName);
	                }
	                // 조합형주소의 유무에 따라 양쪽에 괄호를 추가하여 최종 주소를 만든다.
	                fullAddr += (extraAddr !== '' ? ' ('+ extraAddr +')' : '');
	            }

	            // 우편번호와 주소 정보를 해당 필드에 넣는다.
	            document.getElementById('zipcode').value = data.zonecode; //5자리 새우편번호 사용
	            document.getElementById('address01').value = fullAddr;

	            // 커서를 상세주소 필드로 이동한다.
	            document.getElementById('address01').focus();
	        }
	    }).open();
	});
	
	
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
											xhr.open('POST', "myPageInfoPwModify", true);
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
	</script>
</section>