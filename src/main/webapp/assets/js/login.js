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

function kakaoLogin() {
  const url = "https://kauth.kakao.com/oauth/authorize?" + 
  		"client_id=04938764cd5055a91180bd7367981503" + 
//  		"&redirect_uri=http://127.0.0.1:8080/oauth/kakao?cmd=callback" + 
  		"&redirect_uri=http://runeah.cafe24.com/oauth/kakao?cmd=callback" + 
  		"&response_type=code";
  kakao_Login.setAttribute("href", url);
}

const kakao_Login = document.getElementById("kakao_Login");
kakao_Login.addEventListener("click", (e)=>{
	kakaoLogin();
	
	
})