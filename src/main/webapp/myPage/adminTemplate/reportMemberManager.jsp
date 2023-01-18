<%@ page language="java" contentType="text/html; charset=UTF-8"
    pageEncoding="UTF-8"%>
<%@ page import="dao.MyPageDao" %>
<%@ page import="dto.MemberDto" %>
<%@ page import="dao.ReportShowDao" %>
<%@ page import="dto.ReportMemberDto" %>
<%@ page import="java.util.*" %>
<script>
	const accept = document.getElementById("accept");
	const reject = document.getElementById("reject");
	
	accept.addEventListener('click', function(){
		return conform("정말 차단을 수락하시겠습니까?");	
	});
	
	reject.addEventListener('click', function(){
		return conform("정말 차단을 거절하시겠습니까?");
	});
</script>
<section id="reportMemberManage">
	<a>신고관리에오</a>
	 <div id="reportMemberManageWrap">
      <table class="reportMemberManageTable">
		<tr>
			<th>번호</th>
			<th>신고한 회원 아이디</th>
			<th>[신고 대상 회원 아이디]</th>
			<th>[신고 대상 게시글 URL]</th>
			<th>요청 날짜</th>
			<th><a>신고 수락</a></th>
			<th><a>신고 거절</a></th>
		
		<%
			ReportShowDao dao = new ReportShowDao();
			ArrayList<ReportMemberDto> list = dao.showReport();
			
			for(ReportMemberDto member : list) {
		%>
			<th><%=member.getReportNo()%></th>
			<th><%=member.getUserId()%></th>
			<th><%=member.getReportId()%></th>
			<th><%=member.getReportUrl()%></th>
			<th><%=member.getRegDate()%></th>
			<th><a id="accept" href="/ReportAcceptServlet"><%=member.isAccept()%></a></th>
			<th><a id="reject" href="#"><%=member.isReject()%></a></th>
		<%
			}
		%>
		</tr>
	   </table>
	</div>
</section>