<%@ page language="java" contentType="text/html; charset=UTF-8"
    pageEncoding="UTF-8"%>
<%@ page import="dto.MarketDto" %>
<%@ page import="java.util.*" %>
<%@ page import="java.sql.*" %>
<section id="myPageMyActivity">
  <div id="myPageMyActivityContent">
  	<div id="myPageMyActivityLeftNavWrap">
	    <nav id="myPageMyActivityLeftNav">
	      <ul>
	        <li id="sellList" class="enabled"><a>작성한<br>판매글</a></li>
	        <li id="buyList"><a>작성한<br>구매글</a></li>
	      </ul>
	    </nav>
    </div>
    <div id="myPageMyActivityContent">
    	<table>
    	<thead>
			<tr>
				<th>마켓 글 번호</th>
				<th>작성자</th>
				<th>제목</th>
				<th>가격</th>
				<th>글쓴일</th>
				<th>조회수</th>
				<th>썸네일</th>
			</tr>
		</thead>
		<tbody id="myPageMyActivityAjaxTemplate">
		</tbody>
		<tfoot>
			<tr>
				<td></td>
				<td></td>
				<td></td>
				<td><input type="button" id="myPageMyActivityMoreBtn" value="더보기">	</td>
				<td></td>
				<td></td>
				<td></td>
			</tr>
		</tfoot>
    </table>
    </div>
  </div>
<script>
	const myPageMyActivityMoreBtn = document.querySelector("#myPageMyActivityMoreBtn");
	const myPageMyActivityAjaxTemplate = document.querySelector("#myPageMyActivityAjaxTemplate")
	const sellList = document.querySelector("#sellList");
	const buyList = document.querySelector("#buyList");
	
	document.addEventListener('DOMContentLoaded',function(){
		sellList.click();
		const myInfo = document.querySelector("#myAct");
		myInfo.classList.add("active");
	})
	
	let clickNum = 0;
	let loadNum = 5;
	let part = "sell";
	sellList.addEventListener('click',function(){
		sellList.classList.add("enabled");
		buyList.classList.remove("enabled");
		part = "sell";
		clickNum = 0;
		loadNum = 5;
		myPageMyActivityAjaxTemplate.innerHTML = '';
		myPageMyActivityMoreBtn.style.display ="block";
		myPageMyActivityMoreBtn.click();
	});
	buyList.addEventListener('click',function(){
		buyList.classList.add("enabled");
		sellList.classList.remove("enabled");
		part = "buy";
		clickNum = 0;
		loadNum = 5;
		myPageMyActivityAjaxTemplate.innerHTML = '';
		myPageMyActivityMoreBtn.style.display ="block";
		myPageMyActivityMoreBtn.click();
	});
	
	myPageMyActivityMoreBtn.addEventListener('click',function(){
		const xhr = new XMLHttpRequest();
		let myJSON = "{"+'"part"'+":"+'"'+part+'"'+
						',"clickNum"'+":"+'"'+clickNum+'"'+
						',"loadNum"'+":"+'"'+loadNum+'"'+
						"}";
		let myObj = JSON.parse(myJSON);
		//console.log(myJSON);
		//console.log(myObj);
		xhr.open('POST', "myPageActivityAjax", true);
		xhr.responseType = "json";
		xhr.setRequestHeader('Content-Type', 'application/json');
		xhr.send(JSON.stringify(myObj));
		xhr.onreadystatechange = () => {
			if(xhr.readyState !== XMLHttpRequest.DONE) return;
			if(xhr.status === 200){
				//console.log(xhr.response);
				let arr = xhr.response;
				clickNum++;
				//tr에 td추가하기
				let keys = Object.keys(arr.result);
				
				for (let i=0; i<keys.length; i++) {
					let tr = document.createElement('tr');
			    	let key = keys[i];
			    	//console.log("key : " + key + ", value : " +arr.result[key])
			    	let keys2 = Object.keys(arr.result[key]);
			    	let marketNo = Object.values(arr.result[key])[0];
					tr.addEventListener('click',function(){	
						location.href = "/marketPostInfo?no="+marketNo;
					});
					console.log(marketNo);
			    	for(let j=0;j<keys2.length;j++){
			    		let td = document.createElement('td');
			    		td.innerHTML = Object.values(arr.result[key])[j];
			    		tr.append(td);
			    		let key2 = keys2[j];
				    	//console.log("key : " + key2 + ", value : " + Object.values(arr.result[key])[j]);
			    	}
			    	
			    	myPageMyActivityAjaxTemplate.append(tr);
				}
				
				if(arr.isEnd=="true"){
					myPageMyActivityMoreBtn.style.display = "none";
				}
			}
			else {
				console.error('Error',xhr.status,xhr.statusText);
			}
		}
	});
</script>