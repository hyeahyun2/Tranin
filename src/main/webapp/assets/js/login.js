const form = document.loginForm;
const loginBtn = document.querySelector(".loginBtn");

loginBtn.addEventListener("click", ()=>{
	form.action = "/memberLogin";
	form.submit();
})

// 엔터 로그인
form.id.addEventListener("keyup", ()=>{
	if( window.event.keyCode == 13 ){
		form.action = "/memberLogin";
		form.submit();
	}
});
form.pw.addEventListener("keyup", ()=>{
	if( window.event.keyCode == 13 ){
		form.action = "/memberLogin";
		form.submit();
	}
})