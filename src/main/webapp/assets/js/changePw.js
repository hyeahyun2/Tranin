const form = document.changePwForm;
const labelWrap = document.querySelectorAll("#changePwForm label");

let regExpPw = /^(?=.*[a-z])(?=.*\d)(?=.*[$@$!%*?&])[A-Za-z\d$@$!%*?&]{8,}/;

// 비밀번호
const pw = form.pw;
let spanPw = document.createElement("span");
labelWrap[0].append(spanPw);
spanPw.style.color = "red";
let pwVali = ()=>{
	if(pw.value.length == 0){
		spanPw.innerText = "";
		return false;
	}
	else if(!regExpPw.test(pw.value)){
		spanPw.innerText = "영문자, 숫자, 특수 문자 각각 하나 이상을 포함하여 8자 이상으로 작성하세요.";
		return false;
	}
	else {
		spanPw.innerText = "";
		return true;
	}
}
pw.addEventListener("keyup", pwVali);

// 비밀번호 확인
const pwCheck = form.pwCheck;
let spanPwCheck = document.createElement("span");
labelWrap[1].append(spanPwCheck);
spanPw.style.color = "red";
let pwCheckVali = ()=>{
	if(pwCheck.value.length == 0){
		spanPwCheck.innerText = "";
		return false;
	}
	else if(pwCheck.value != pw.value){
		spanPwCheck.innerText = "비밀번호가 일치하지 않습니다. 다시 확인해주세요.";
		return false;
	}
	else {
		spanPwCheck.innerText = "";
		return true;
	}
}
pwCheck.addEventListener("keyup", pwCheckVali);

// 비밀번호 변경 버튼 클릭
const changePwBtn = document.querySelector(".changePwBtn");
changePwBtn.addEventListener("click", ()=>{
	form.action = "/memberFindPw/changePwSubmit";
	form.submit();
})