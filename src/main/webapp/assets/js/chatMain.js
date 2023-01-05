const xhr = new XMLHttpRequest();
// 채팅 리스트 불러오기
function chatIDListFunction(){
	// 비동기 통신
	xhr.open("POST", "../ChatOhterMemberIDListServlet", true);
	//xhr.setRequestHeader("인코딩?방식", ""); 
	xhr.setRequestHeader("Content-type","application/x-www-form-urlencoded");
	
	xhr.onreadystatechange = function(){
		if(xhr.readyState != XMLHttpRequest.DONE) return;
		if(xhr.status == 200){ // 준완
			let data = xhr.response;
			if(data == "") return; // 공백일 경우 제외
			console.log("ajax 연결 성공");
			let parsed = JSON.parse(data); // json형태로 파싱
			let result = parsed.result;	
			for(let i=0; i<result.length; i++){
				console.log(result[i].otherMemberNick);
				addIDList(result[i].otherMemberNick);
			}
			// chatID값 가져오기
			//lastChatID = Number(parsed.last);
		}
	}
	xhr.send();
}

function addIDList(memberNick){
	var template = `<li class="chatUser">
						<a href="./chatting.jsp?toNick=${memberNick}">
					        <div></div>
					        <span>${memberNick}</span>
				    	</a>
				    </li>`;
	document.querySelector("#chatMemberList").insertAdjacentHTML("beforeend", template);
}

// 문서 로딩시
window.addEventListener("load", function(){
	chatIDListFunction();
})