const myPageProfileNickName = document.querySelector("#myPageProfileNickName");
const myPageProfileIntroduce = document.querySelector("#myPageProfileIntroduce");
const myPageProfilePicture = document.querySelector("#myPageProfilePicture");
const myPageProfileAttach = document.querySelector("#myPageProfileAttach");
const myPageMyActivityLeftNav = document.querySelector("#myPageMyActivityLeftNav");
const myPageMyActivityLeftNavLi = document.querySelectorAll("#myPageMyActivityLeftNav li");
const myPageTab = document.querySelectorAll("#myPageTab li");
const myPageFavoriteUl = document.querySelector("#myPageFavorite ul");
const myPageMemberOutInnerWrap = document.querySelector("#myPageMemberOutInnerWrap input");
const myPageMemberOutBtn = document.querySelector("#myPageMemberOutBtn");
const myPageMemberBannedListBtns = document.querySelectorAll("#myPageMemberBannedList input");
const myPageAdminInnerTab = document.querySelectorAll("#myPageAdminInnerTab section");
const myPageAdminNav = document.querySelectorAll("#myPageAdminNav ul li");

for(let i=0;i<myPageTab.length;i++){
  console.log(i);
  myPageTab[i].addEventListener('click',function(){
    for(let j=0;j<myPageTab.length;j++){
      myPageTab[j].classList.remove("active");
    }
    myPageTab[i].classList.add("active");
  });
}
for(let i=0;i<myPageMyActivityLeftNavLi.length;i++){
  console.log(i);
  myPageMyActivityLeftNavLi[i].addEventListener('click',function(){
    for(let j=0;j<myPageMyActivityLeftNavLi.length;j++){
      myPageMyActivityLeftNavLi[j].classList.remove("enabled");
    }
    myPageMyActivityLeftNavLi[i].classList.add("enabled");
  });
}
if(myPageFavoriteUl!=null){
	myPageFavoriteUl.addEventListener('scroll',function(){
	  if( myPageFavoriteUl.scrollTop === (myPageFavoriteUl.scrollHeight - myPageFavoriteUl.offsetHeight)){
	    console.log(myPageFavoriteUl.scrollTop+"ajax 발동");
	    let li = document.createElement("li")
	    myPageFavoriteUl.append(li);
	  }
	});
}


