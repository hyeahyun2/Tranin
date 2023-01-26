const form = document.loginForm;
const loginBtn = document.querySelector(".loginBtn");

loginBtn.addEventListener("click", ()=>{
	form.action = "/memberLogin";
	form.submit();
})