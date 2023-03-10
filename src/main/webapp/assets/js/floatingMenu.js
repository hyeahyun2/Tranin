//header left/right Side
 const body = document.querySelector("body");
 const headerLeftNav = document.querySelector(".leftNav");
 const headerLeftNavUl = document.querySelector(".count");
 const headerLeftNavLi = document.querySelectorAll(".count > li");
 const headerLeftNavA = document.querySelectorAll(".count > li > a");
 const headerLeftNavBar = document.querySelector("#headerBar");
 const rightQuick = document.querySelector("#rightQuick");
 const rightQuickMenu = document.querySelector(".rightQuickMenu");
 const post_sec4 = document.querySelector(".Post_sec4");
 const rightSearch = document.querySelector("#rightSearch");
 const rightSearchPage = document.querySelector("#rightSearchPage");
 const rightSearchClose = document.querySelector("#rightSearchClose");
 const rightSearchSelect = document.querySelector("#rightSearchSelect");
 const rightSearchAdditionalSelect = document.querySelector("#rightSearchAdditionalSelect");
 const upperSmallNav = document.querySelector("#upperSmallNav");
 const chat = document.querySelector("#chatTag");
 const chatLink = chat.querySelector("a");

 /* 검색창 */
 rightSearch.addEventListener("click",function(e){
  e.preventDefault();
  body.style.overflow = "hidden";
   rightSearchPage.classList.add("active");
   rightSearchPage.style.top = `${window.pageYOffset}px`;
   upperSmallNav.style.opacity=0;
   rightQuickMenu.style.opacity=0;
   headerLeftNav.style.opacity=0;
   headerWrap.style.opacity=0;
 });
 rightSearchClose.addEventListener("click",function(){
  body.style.overflow = "scroll";
  rightSearchPage.classList.remove("active");
   upperSmallNav.style.opacity=1;
   rightQuickMenu.style.opacity=1;
   headerLeftNav.style.opacity=1;
   headerWrap.style.opacity=1;
 });
 rightSearchSelect.addEventListener("change",function(){
	if(rightSearchSelect.value=="장터"){
		rightSearchAdditionalSelect.value="삽니다";
		rightSearchAdditionalSelect.style.display="inline-block";
	}else if(rightSearchSelect.value=="공지사항"){
		rightSearchAdditionalSelect.value="";
		rightSearchAdditionalSelect.style.display="none";
	}else {
		alert("검색 오류!");
	}
});
 
 
 /* 채팅 새 창으로 열기 */
chatLink.addEventListener("click", (e)=>{
  e.preventDefault();
  window.open("../chat/chatMain.jsp", "채팅", " width=400, height=500");
})

/* 메세지창 - 새 메세지 여부 */
const xhr_side = new XMLHttpRequest();
const isNewMsgTag = document.querySelector(".isNewMsg");
function isNewChat(){
	xhr_side.open("POST", "/chatNewCheck", true);
	xhr_side.setRequestHeader("Content-type","application/x-www-form-urlencoded");
	
	xhr_side.onreadystatechange = function(){
		if(xhr_side.readyState != XMLHttpRequest.DONE) return;
		if(xhr_side.status == 200){ // 준완
			let data = xhr_side.response;
			if(data == "") return; // 공백일 경우 제외
			else if(data == "false"){ // 새 메세지 있는 경우 (read여부 = false)
				isNewMsgTag.classList.add("newMsg_false");
			}
			else {
				isNewMsgTag.classList.add("newMsg_true");
			}
			console.log(data);
		}
	}
	xhr_side.send();
}

// 문서 로딩시 실행
window.addEventListener("load", function(){
	isNewChat();
})