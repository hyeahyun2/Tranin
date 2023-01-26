const form = document.findPwForm;
const id = form.id;

let authKey = "";
let authEmailVal = "";

// 이메일 인증 메일 전송
const emailAuthBtn = document.querySelector(".emailAuthBtn");
let regExpEmail = /^[A-Za-z0-9_\.\-]+@[A-Za-z0-9\-]+\.[A-Za-z0-9\-]+/;
emailAuthBtn.addEventListener("click", ()=>{
	// 유효성 검사
	if(!regExpEmail.test(id.value)){
		alert("이메일을 양식에 맞게 입력해주세요.");
		return;
	}
	// ajax 통신
	const xhr = new XMLHttpRequest();
	xhr.open("POST", "/memberFindPw/emailAuthCheck", true);
	xhr.setRequestHeader("Content-type","application/x-www-form-urlencoded");
	
	xhr.onreadystatechange = function(){
		if(xhr.readyState != XMLHttpRequest.DONE) return;
		if(xhr.status == 200){ // 준완
			let json = JSON.parse(xhr.response);
			if(json.result == 'fail'){
				alert("이메일 인증 메일 전송에 실패했습니다.");
			}
			else if(json.result == 'true'){
				alert("이메일 인증 메일이 전송되었습니다. 이메일을 확인하여 주세요.");
				authKey = json.authKey; // 인증 번호 저장
				authEmailVal = id.value; // 인증 이메일 전송한 이메일 저장
			}
		}
	}
	
	xhr.send("id=" + id.value);
})

// 이메일 인증
const loginBtn = document.querySelector(".emailAuthCheck");
loginBtn.addEventListener("click", ()=>{
	if(form.emailAuthNum.value == authKey){ // 인증 성공
		id.value = authEmailVal; // 인증 번호 보낸 이메일로 파라미터 고정해주기
		form.action = "/memberFindPw/goChangePw";
		form.submit();
	}
	else { // 인증 실패
		alert("인증번호를 다시 확인해주세요.");
	}
})