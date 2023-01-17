const form = document.getElementById("formId");
const submitBtn = document.getElementById("submitBtn");


function doSubmit() {
	/* 유효성검사 추가하기!! */
	
	form.submit();
	
}

// 등록버튼 클릭 -> 글 등록하기
submitBtn.addEventListener("click", doSubmit);
