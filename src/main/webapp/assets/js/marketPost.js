const contentWrap = document.getElementById("contentWrap");
const imgWrap = contentWrap.querySelector(".mainImg");
const imgBtn = contentWrap.querySelector(".imgBtn");

const imgList = imgWrap.querySelectorAll("li");
const imgbtnList = imgBtn.querySelectorAll("li");

const imgAll = imgWrap.querySelectorAll("img");
imgAll.forEach(img => { // img 태그 기본 기능 없애기
  img.addEventListener("mousedown", (e)=>{
    e.preventDefault();
  })
});

window.addEventListener("load", function(){
	imgList[0].classList.add("checkImg");
	imgWrap.style.width = `${imgList.length * 100}%`;
	imgbtnList[0].classList.add("checkImg");
})

let imgNum = 0; // 현재 이미지의 index 번호
let state = 1; // 애니매이션 중일 때 = 0
function imgMove(){
  imgWrap.style.transition = '0.5s';
  imgWrap.style.marginLeft = `-${imgNum * 100}%`;
  imgbtnList.forEach(element => {
    element.classList.remove("checkImg");
  });
  imgbtnList[imgNum].classList.add("checkImg");
  setTimeout(function(){
    imgWrap.style.transition = '0s';
  }, 600);
}
// 버튼 클릭시
imgbtnList.forEach((element, index) => {
  element.addEventListener("click", ()=>{
    imgNum = index;
    imgMove();
  })
})

// 드래그 이벤트
let mouseDown = 0;
let mouseUp = 0;
let mouseDrage = false; // 드래그 중에 true
imgWrap.addEventListener("mousedown", function(e){
  mouseDrage = true;
  mouseDown = e.pageX;
})
imgWrap.addEventListener("mouseup", function(e){
  mouseUp = e.pageX;
  console.log("down: " + mouseDown);
  console.log("up: " + mouseUp);
  // 현재 imgList의 너비의 30%
  let imgListPer30 = 30*(imgList[0].clientWidth/100);
  if(mouseUp < mouseDown - imgListPer30){ // 다음 슬라이드
    console.log("left");
    if(imgNum < imgList.length-1){ 
      imgNum ++;
    }
  }
  else if(mouseUp > mouseDown + imgListPer30){ // 이전 슬라이드
    console.log("right");
    if(imgNum > 0){
      imgNum--;
    }
  }
  else { // 드래그 폭 좁을 경우 현재 슬라이드로 되돌아가기
  }
  imgMove();
  mouseDrage = false;
})
imgWrap.addEventListener("mousemove", function(e){
  if(mouseDrage === true){
    let drageGap = mouseDown - e.pageX;
    let drageGapPer = 100*(drageGap/imgList[0].clientWidth);
    let marginValue = (imgNum*100)+drageGapPer;
    if(marginValue >= 0 && marginValue <= 400){
      e.currentTarget.style.marginLeft = `-${(imgNum*100)+drageGapPer}%`;
    }
  }
})


 /* 채팅 새 창으로 열기 */
const chatBtn = document.querySelector(".chatBtn");
chatBtn.addEventListener("click", (e)=>{
  e.preventDefault();
  window.open(chatBtn.getAttribute("href"), "채팅", " width=400, height=500");
})

