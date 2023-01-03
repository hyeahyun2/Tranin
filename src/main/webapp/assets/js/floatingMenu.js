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
 
 /* 채팅 새 창으로 열기 */

chatLink.addEventListener("click", (e)=>{
  e.preventDefault();
  window.open("../chat/chatMain.jsp", "채팅", " width=400, height=500");
})