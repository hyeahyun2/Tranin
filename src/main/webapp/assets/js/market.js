const fixedBtn = document.getElementById("fixedBtn");
const topBtn = fixedBtn.querySelector(".topBtn");
const searchText = document.search.searchText;
topBtn.addEventListener("click",(e)=>{
  e.preventDefault();
  // 스크롤 스무스하게 올라가게
  window.scrollTo({top: 0, behavior: 'smooth'});
})

// 더보기 클릭시 리스트 추가
// ajax 사용
const xhr = new XMLHttpRequest();
const contentWrap = document.getElementById("contentWrap");
const posts = document.getElementById("posts");
const moreBtn = contentWrap.querySelector(".moreBtn");
const partTag = document.querySelector("#array .select");

let clickNum = 0; // 클릭 수
let nowPart =  // 페이지 로딩 시 part 초기값
			partTag.getAttribute("class").includes("sell")? "sell" : "buy";
let searchKey = searchText.value; // 검색 키워드 초기값

function moreList(part, searchKey){
  xhr.open("POST", "../marketListServlet", true);
  xhr.setRequestHeader("Content-type","application/x-www-form-urlencoded");

  xhr.onreadystatechange = ()=>{
    if(xhr.readyState !== XMLHttpRequest.DONE) return;
    //console.log(xhr.status, xhr.statusText);
    if(xhr.status === 200){ // 서버(url)에 문서가 존재할 때
      //posts.insertAdjacentHTML("beforeend", xhr.response);
      let data = xhr.response;
      if(data == "") return;
      let parsed = JSON.parse(data);
      let result = parsed.result;
      boardList(result, part);
      const postUl = posts.querySelectorAll("ul"); // 현재 ul태그들 변수에 저장
      clickNum++;
      if(clickNum*8 >= Number(parsed.postNum)){ // 게시글 더 없을 경우
				moreBtn.style.display = 'none'; // 더보기 버튼 비활성화
			}
			else {
				moreBtn.style.display = 'block'; // 더보기 버튼 활성화
			}
      posts.style.height = `${80 * clickNum}%`; // ul 부모태그 높이 늘리기
      postUl.forEach(element =>{ // ul태그들 높이 조절
        element.style.height = `${100 / clickNum}%`
      })
      // postUl.style.height = `${100 / clickNum}%`
      //console.log(80 * clickNum);
    }
    else { // 서버(url)에 문서가 존재하지 않을 때
      console.log("Error", xhr.status, xhr.statusText);
    }
  }
  // 보낼 파라미터 설정
  xhr.send("part=" + part +
  		"&clickNum=" + clickNum +
  		"&searchKey=" + searchKey +
  		"&loadNum=8");
}

// 게시글 추가
let today = new Date(); // 현재 날짜 시간
let today_month = today.getMonth() + 1;
let today_date = today.getDate();
if(today_month < 10) today_month = "0" + today_month.toString();
if(today_date < 10) today_date = "0" + today_date.toString();
let date = today.getFullYear() + "-" + today_month + "-" + today_date; // 날짜만 저장
function boardList(result){
	var template = `<ul>`;
	for(let i=0; i<result.length; i++){
		let isToday = "anotherDay"; // 게시글이 오늘 날짜인지 여부 확인 (기본값 : 다른날짜)
		if(result[i].writeDate.includes(date)){ // 오늘 날짜면
			isToday = "toDay";
		}
		// 	http://localhost:8080/resources/images/P1234.png
		let titleImg = 
				result[i].titleImage == "" ? 
						"/assets/image/default_image.png" : `/img/${result[i].titleImage}`;
		if(result[i].titleImage == ""){result[i].tileImage = "defualtImg.png"}
		var post = `<li class="post">
	    		<a href="/marketPostInfo?no=${result[i].no}" class="postImg">
	    			<img src="${titleImg}" alt="${result[i].no}번 글 이미지">
	    		</a>
	    		<dl>
	     		 <dt><a href="/marketPostInfo?no=${result[i].no}" class="postTitle">${result[i].title} <span class="${isToday}">새글</span></a></dt>
	      		<dd class="price">${result[i].price}원</dd>
	     		 <dd class="writer">${result[i].writerNick}</dd>
	      		<dd class="hits_date"><span class="hits">조회수 ${result[i].hits}</span><span class="writeDate">${result[i].writeDate}</span></dd>
	   		 	</dl>
	  		</li>`;
		template += post;
	}
	template += `</ul>`;
	posts.insertAdjacentHTML("beforeend", template);
}

window.addEventListener("load", moreList(nowPart, searchKey)); // 페이지 로드시 디폴트 리스트
moreBtn.addEventListener("click", function(){
	moreList(nowPart, searchKey);
}); // 클릭시 리스트 추가

// 검색
const searchBtn = document.search.searchBtn;
searchBtn.addEventListener("click", function(){
	location.href = "/market/market.jsp?part=" + nowPart
						+ "&searchKey=" + searchText.value;
});


// sell/buy(#array)에 따른 리스트 재나열
const array = document.getElementById("array");
const arrList = array.querySelectorAll("li");

arrList.forEach(element => {
  element.addEventListener("click",(e)=>{
    for(let i=0; i<arrList.length; i++){
      arrList[i].classList.remove("select");
    }
    nowPart = e.currentTarget.getAttribute("class");
    e.currentTarget.classList.add("select"); // 선택된 정렬방식 표시
    
    // 파라미터 변경 후 페이지 새로고침
    location.href = 
    		e.currentTarget.getAttribute("class").includes("sell")?
    			"/market/market.jsp?part=sell" : "/market/market.jsp?part=buy";
    /*
    posts.innerHTML = null;
    clickNum = 0;
    moreList(nowPart, searchKey);
     */
  })
});

