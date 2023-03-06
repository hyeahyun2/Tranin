/* 신고하기 버튼 클릭 시 submit */
const reportForm = document.reportForm;
const reportBtn = document.querySelector(".reportBtn");
reportBtn.addEventListener("click", function(e){
	e.preventDefault();
	let result = confirm("해당 게시글을 신고하시겠습니까?");
	if(result){
		reportForm.submit();
	}
})
