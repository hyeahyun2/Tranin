const accept = document.querySelectorAll(".accept");
const reject = document.querySelectorAll(".reject");

for (let i=0; i < accept.length; i++) {
	accept[i].addEventListener('click', function(){
		if(confirm("정말 차단을 수락하시겠습니까?")){
			acceptReport();
		}	
	});
}

for (let i=0; i < reject.length; i++) {
	reject[i].addEventListener('click', function(){
		if(confirm("정말 차단을 거절하시겠습니까?")){
			rejectReport();
		}
		
	});
}

function acceptReport() {
	const reportId = document.getElementById("reportId").innerText;
	console.log(reportId);
	const reportNo = document.getElementById("reportNo").innerText;
	console.log(reportNo);
    $.ajax({
        url:"/ReportAcceptServlet",
        method:"post",
        data:{"reportId":reportId, "reportNo":reportNo},
        success:function(){
            if(confirm("성공하였습니다. 계속하시겠습니까?")) {
            	window.location.href="/myPage/managerPage?myPageManagerCategory=2&reportManager=0";
            } else {
            	window.location.href="/";
            }
        },
        error:function (){
            alert("실패");
        }
    });
}

function rejectReport() {
	const reportNo = document.getElementById("reportNo").innerText;
    $.ajax({
        url:"/ReportRejectServlet",
        method:"post",
        data:{"reportNo":reportNo},
        success:function(){
            confirm("성공");
//            window.location.href="/myPage/managerPage?myPageManagerCategory=2&reportManager=0";
        },
        error:function (){
            alert("실패");
        }
    });
}

