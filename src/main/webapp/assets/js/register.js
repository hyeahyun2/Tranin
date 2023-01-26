const form = document.registerForm;
console.log(form);
const labelWrap = document.querySelectorAll("#registerForm label");

let duplEmail = false;
let duplNick = false;
let authEmailVal = "";
let authKey = "";

/* 유효성검사 */
let regExpEmail = /^[A-Za-z0-9_\.\-]+@[A-Za-z0-9\-]+\.[A-Za-z0-9\-]+/;
let regExpPw = /^(?=.*[a-z])(?=.*\d)(?=.*[$@$!%*?&])[A-Za-z\d$@$!%*?&]{8,}/;
let regExpNick = /^[가-힣|a-z|A-Z]+[가-힣|a-z|A-Z|0-9]*$/;
// 이메일
const email = form.email;
let spanEmail = document.createElement("span");
labelWrap[0].append(spanEmail);
spanEmail.style.color = "red";
let emailVali = ()=>{
	if(email.value.length == 0){
			spanEmail.innerText = "";
			return false;
	}
	else if(!regExpEmail.test(email.value)){
		spanEmail.innerText = "이메일 형식으로 작성하세요.";
		return false;
	}
	else {
		spanEmail.innerText = "";
		return true;
	}
}
email.addEventListener("keyup", ()=>{
	duplEmail = false;
	emailVali();
});

// 비밀번호
const pw = form.pw;
let spanPw = document.createElement("span");
labelWrap[2].append(spanPw);
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
labelWrap[3].append(spanPwCheck);
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

// 별명
const nick = form.nickname;
let spanNick = document.createElement("span");
labelWrap[4].append(spanNick);
spanPw.style.color = "red";
let nickVali = ()=>{
	if(nick.value.length == 0){
		spanNick.innerText = "";
		return false;
	}
	else if(!regExpNick.test(nick.value)){
		spanNick.innerText = "별명은 한글이나 영문으로 시작해야하고, 한글/영문/숫자로 입력 가능합니다.";
		return false;
	}
	else {
		spanNick.innerText = "";
		return true;
	}
}
nick.addEventListener("keyup", ()=>{
	duplNick = false;
	nickVali();
});

// 회원가입 버튼 클릭 시 양식 확인
const registerBtn = document.querySelector(".registerBtn");
registerBtn.addEventListener("click", ()=>{
	if(!emailVali){
		alert("이메일을 양식에 맞게 입력해주세요.");
		email.focus();
		return;
	}
	if(!pwVali){
		alert("비밀번호를 양식에 맞게 입력해주세요.");
		pw.focus();
		return;
	}
	if(!pwCheckVali){
		alert("비밀번호가 일치하지 않습니다.");
		pwCheck.focus();
		return;
	}
	if(!nickVali){
		alert("닉네임을 양식에 맞게 입력해주세요.");
		nick.focus();
		return;
	}
	if(duplEmail == false){
		alert("이메일 인증을 먼저 진행해주세요.");
		return;
	}
	if(duplNick == false){
		alert("닉네임 중복 검사를 진행해주세요.");
		return;
	}
	form.action = "/memberRegister/registerSubmit";
	form.submit();
})

/* 이메일 인증 */
// 인증 번호 전송
const emailAuthBtn = document.querySelector(".emailAuthBtn");
emailAuthBtn.addEventListener("click", function(){
	// 유효성 검사
	if(!emailVali){
		alert("이메일을 양식에 맞게 입력해주세요.");
		return;
	}
	// ajax 통신
	const xhr = new XMLHttpRequest();
	xhr.open("POST", "/memberRegister/emailAuthCheck", true);
	xhr.setRequestHeader("Content-type","application/x-www-form-urlencoded");
	
	xhr.onreadystatechange = function(){
		if(xhr.readyState != XMLHttpRequest.DONE) return;
		if(xhr.status == 200){ // 준완
			let json = JSON.parse(xhr.response);
			if(json.result == 'duplicated'){
				alert("해당 이메일은 이미 가입되어있습니다.");
			}
			else if(json.result == 'fail'){
				alert("이메일 인증 메일 전송에 실패했습니다.");
			}
			else if(json.result == 'true'){
				alert("이메일 인증 메일이 전송되었습니다. 이메일을 확인하여 주세요.");
				authKey = json.authKey; // 인증 번호 저장
				authEmailVal = email.value; // 인증 이메일 전송한 이메일 저장
			}
		}
	}
	
	xhr.send("id=" + email.value);
})
// 인증 번호 확인
const emailAuthNum = form.emailAuthNum;
const emailAuthCheck = document.querySelector(".emailAuthCheck");
emailAuthCheck.addEventListener("click", function(){
			if(authKey == emailAuthNum.value){ // 인증번호 일치하면
				duplEmail = true;
				alert("이메일 인증에 성공했습니다!");
				email.value = authEmailVal; // 인증 진행한 이메일로 고정
				email.setAttribute("readonly", "readonly"); // 읽기만 가능으로 변경
			}
			else {
				alert("이메일 인증에 실패했습니다!");
			}
})

/* 별명 중복 확인 */
const nickDuplCheck = document.querySelector(".nickDuplCheck");
nickDuplCheck.addEventListener("click", function(){
	// 유효성 검사
	if(!nickVali){
		alert("닉네임을 양식에 맞게 입력해주세요.");
		return;
	}
	// ajax 통신
	const xhr = new XMLHttpRequest();
	xhr.open("POST", "/memberRegister/nickDuplCheck", true);
	xhr.setRequestHeader("Content-type","application/x-www-form-urlencoded");
	
	xhr.onreadystatechange = function(){
		if(xhr.readyState != XMLHttpRequest.DONE) return;
		if(xhr.status == 200){ // 준완
			let json = JSON.parse(xhr.response);
			if(json.result == 'true'){
				duplNick = true;
				alert("사용 가능한 별명입니다!");
			}
			else {
				alert("이미 존재하는 별명입니다.");
			}
		}
	}
	
	xhr.send("nickname=" + nick.value);
})

// 우편번호 검색
const zipCodeCheck = document.querySelector(".zipCodeCheck");
zipCodeCheck.addEventListener("click", function(){
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
      document.getElementById('address02').focus();
    }
  }).open();
});
