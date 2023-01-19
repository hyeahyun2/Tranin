const fixedBtn = document.getElementById("fixedBtn");
const topBtn = fixedBtn.querySelector(".topBtn");
console.log(topBtn);
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

let clickNum = 0; // 클릭 수
let nowPart = "sell"; // part 초기값

function moreList(part){
	console.log("clickNum : " + clickNum);
  // page = this.getAttribute();
  //xhr.open('GET', `./marketList.jsp?`); //HTTP 요청 초기화. 통신 방식과 url 결정
  xhr.open("POST", "../marketListServlet", true);
  xhr.setRequestHeader("Content-type","application/x-www-form-urlencoded");

  xhr.onreadystatechange = ()=>{
    if(xhr.readyState !== XMLHttpRequest.DONE) return;
    console.log(xhr.status, xhr.statusText);
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
      console.log(80 * clickNum);
    }
    else { // 서버(url)에 문서가 존재하지 않을 때
      console.log("Error", xhr.status, xhr.statusText);
    }
  }
  // 보낼 파라미터 설정
  xhr.send("part=" + part +
  		"&clickNum=" + clickNum +
  		"&loadNum=8");
}

// 게시글 추가
function boardList(result){
	var template = `<ul>`;
	for(let i=0; i<result.length; i++){
		// 	http://localhost:8080/resources/images/P1234.png
		// ${result[i].titleImage}
		var post = `<li class="post">
	    		<a href="/marketPostInfo?no=${result[i].no}" class="postImg">
	    			<img src="/resources/images/${result[i].titleImage}" alt="${result[i].no}번 글 이미지">
	    		</a>
	    		<dl>
	     		 <dt><a href="/marketPostInfo?no=${result[i].no}" class="postTitle">${result[i].title}</a></dt>
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

window.addEventListener("load", moreList(nowPart)); // 페이지 로드시 디폴트 리스트
moreBtn.addEventListener("click", function(){
	console.log("click");
	moreList(nowPart);
}); // 클릭시 리스트 추가


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
    
    // 리스트 초기화 및 다시 불러오기
    posts.innerHTML = null;
    clickNum = 0;
    moreList(nowPart);
  })
});


// category 선택
const category = document.getElementById("category");
const goryPTag = category.querySelector("p");
const goryList = category.querySelector(".goryList");
const goryItem = goryList.querySelectorAll("li");
console.log(goryItem);
goryPTag.addEventListener("mouseenter",()=>{
  category.style.height = "175px";
  goryList.style.border = "1px solid #000";
})
category.addEventListener("mouseleave", (e)=>{
  e.currentTarget.style.height = "25px";
  goryList.style.border = "unset";
})

goryItem.forEach(element => {
  element.addEventListener("click",(e)=>{
    let pTagText = e.currentTarget.innerText;
    goryPTag.innerText = element.innerText;
    element.innerText = pTagText;
    // 리스트 초기화 및 다시 불러오기
    posts.innerHTML = null;
    clickNum = 0;
    moreList(nowPart);
  })
});