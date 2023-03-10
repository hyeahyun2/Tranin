const xhr = new XMLHttpRequest();
const chatContentWrap = document.querySelector(".chat-content");
let toNick = document.querySelector('.header').innerText; // 상대방 닉네임
let prevDate = ""; // 비교날짜
let eventLoging = false; // 채팅 로딩중 상태

// 채팅 내역 db로 보내기
function submitFunction(){
	let chatContent = document.querySelector("#chatInput").value;
	// 비동기 통신
	xhr.open("POST", "../chatSubmetServlet", true);
	//xhr.setRequestHeader("인코딩?방식", ""); 
	xhr.setRequestHeader("Content-type","application/x-www-form-urlencoded");
	
	xhr.onreadystatechange = function(){
		if(xhr.readyState != XMLHttpRequest.DONE) return;
		if(xhr.status == 200){ // 준완
			let result = xhr.response;
			//console.log("result : " + result);
			/*
			if(result == 1){
				console.log("성공적으로 메세지를 보냈습니다");
			}
			else {
				console.log("메세지 전송에 실패했습니다.");
			}
			 */
		 console.log("numOfChat : " + numOfChat);
		}
	}
	xhr.send("toNick=" + toNick + 
		"&chatContent=" + encodeURIComponent(chatContent)); // header에 포함하고자 하는 key와 값
	document.querySelector("#chatInput").value = ""; // 값 비워주기
}

let numOfChat = 0; // 현재 불러온 챗 개수
let lastChatNO = 0; // 가장 마지막 채팅의 chatID 값
// 채팅 리스트 불러오기
function chatListFunction(type){
	// 비동기 통신
	xhr.open("POST", "../chatListServlet", true);
	//xhr.setRequestHeader("인코딩?방식", ""); 
	xhr.setRequestHeader("Content-type","application/x-www-form-urlencoded");
	
	xhr.onreadystatechange = function(){
		if(xhr.readyState != XMLHttpRequest.DONE) return;
		if(xhr.status == 200){ // 준완'
			console.log("ten success");
			let data = xhr.response;
			if(data == "") return; // 공백일 경우 제외
			let parsed = JSON.parse(data); // json형태로 파싱
			let result = parsed.result;	
			for(let i=result.length-1; i>=0; i--){
				// result[i][0] : fromNick
				// result[i][1] : ToNick
				// result[i][2] : chatContent
				// result[i][3] : chatTime
				addChat(result[i][0].value, result[i][2].value, result[i][3].value, "after");
			}
			// chat_no값 가져오기
			lastChatNO = Number(parsed.last);
			console.log("lastChatNo : " + lastChatNO);
			// 스크롤 가장 아래로 내리기
			document.querySelector(".chat-content").scrollTop = 
					document.querySelector(".chat-content").scrollHeight;
		}
	}
	xhr.send("toNick=" + toNick + 
		"&listType=" + type +  // listType : 첫 로딩시 "ten", 나머지 경우 chat_no(lastChatNO)
		"&status=after");
}

// 이전 채팅 리스트 불러오기
function prevChatListFunction(numOfChat){
	// 비동기 통신
	xhr.open("POST", "../chatListServlet", true);
	//xhr.setRequestHeader("인코딩?방식", ""); 
	xhr.setRequestHeader("Content-type","application/x-www-form-urlencoded");
	
	xhr.onreadystatechange = function(){
		if(xhr.readyState != XMLHttpRequest.DONE) return;
		if(xhr.status == 200){ // 준완'
			let data = xhr.response;
			if(data == "") return; // 공백일 경우 제외
			let parsed = JSON.parse(data); // json형태로 파싱
			let result = parsed.result;	
			for(let i=result.length-1; i>=0; i--){
				// result[i][0] : fromNick
				// result[i][1] : ToNick
				// result[i][2] : chatContent
				// result[i][3] : chatTime
				addChat(result[i][0].value, result[i][2].value, result[i][3].value, "before");
			}
			eventLoging = false;
		}
	}
	xhr.send("toNick=" + toNick + 
		"&listType=" + numOfChat + // firstChatNO
		"&status=before");
}

// 채팅 리스트 화면에 추가
function addChat(chatName, chatContent, chatTime, status){
	let classType = "";
	// 날짜 , 시간 나눠주기
	let timeArray = chatTime.split(' ');
	timeArray = timeArray.map(element => element.trim());
	let regDate = /^\d{4}-\d{1,2}-\d{1,2}$/; // 날짜 유효성 검사 틀
	let date = ""; // 날짜
	let time = ""; // 시간
	timeArray.forEach(element => {
      if(regDate.test(element)) date += element;
      else {
        time += element + " ";
      }
    });
    // 오전, 오후 구별
    let timeType = "오전";
    let hour = Number(time.substr(0, 2));
    let min = time.substr(2, 3);
    if(hour > 12) {
		hour -= 12;
		timeType = "오후";
	}
	time = timeType + " " + hour + min;
	if(chatName != toNick) classType = "chatMine";
	var template = ""; // 추가할 html 구조
	if(date != prevDate){ // 이전 채팅과 날짜가 다르면 -> 날짜 관련 html 구조 추가
		template += `<div class="chatDate">${date}</div>`;
		prevDate = date; // 비교날짜에 현재 채팅 날짜 저장
	}
	template += `<div class="chatLine ${classType}">
		<span class="chat-box">${chatContent}</span>
		<span class="chat-time">${time}</span>
		</div>`;
		if(status == "after"){
			chatContentWrap.insertAdjacentHTML("beforeend", template);
		} else if(status == "before"){
			chatContentWrap.insertAdjacentHTML("afterbegin", template);
		}
		numOfChat ++; // 불러온 챗 개수 증가
}
// 1초 간격으로 새로운 채팅 내용 가져오기
function getInfiniteChat(){
	setInterval(function(){
		chatListFunction(lastChatNO);
	}, 1000);
}

// 문서 로딩시
window.addEventListener("load", function(){
	setTimeout(function(){
		chatListFunction('ten');
		getInfiniteChat();
	}, 10)
})

// 스크롤 가장 위로 올렸을 경우
window.addEventListener("wheel", (e)=>{
	if(!eventLoging){
		if(e.wheelDelta > 0 && chatContentWrap.scrollTop <= 0 && numOfChat != 0){
			eventLoging = true;
			prevChatListFunction(numOfChat);
		}
	}
});

const btn = document.querySelector("#Chatsend");
const chatInput = document.querySelector("#chatInput");

btn.addEventListener("click", submitFunction);
document.addEventListener("keyup", function(e){
	if(e.keyCode === 13 && chatInput.value.length != 0){
		submitFunction();
	}
})