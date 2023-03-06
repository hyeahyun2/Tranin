/* 판매완료 버튼 클릭 */
const soldOutBtn = document.querySelector(".soldOutBtn");
let marketNo = soldOutBtn.getAttribute("data-marketNo");
soldOutBtn.addEventListener("click", (e)=>{
	let result = confirm("정말 판매완료 처리하시겠습니까?(판매완료 처리 후에는 더이상 장터 게시판에 해당 글이 노출되지 않습니다.)");
	if(result){
		// /market/soldOutCheck?marketNo=
		location.href = "/market/soldOutCheck?marketNo=" + marketNo;
	}
});