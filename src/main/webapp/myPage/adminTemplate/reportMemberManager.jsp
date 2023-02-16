<%@ page language="java" contentType="text/html; charset=UTF-8"
    pageEncoding="UTF-8"%>
<%@ page import="dao.MyPageDao" %>
<%@ page import="dto.MemberDto" %>
<%@ page import="dao.ReportMemberDao" %>
<%@ page import="dto.ReportMemberDto" %>
<%@ page import="java.util.*" %>
<script src="../../assets/js/memberReportPage.js" defer></script>
<%-- 
<script>
	const accept = document.getElementById("accept");
	const reject = document.getElementById("reject");
	
	accept.addEventListener('click', function(){
		return confirm("정말 차단을 수락하시겠습니까?");	
	});
	
	reject.addEventListener('click', function(){
		return confirm("정말 차단을 거절하시겠습니까?");
	});
</script>
--%>
<section id="reportMemberManage">
	<a>신고관리에오</a>
	 <div id="reportMemberManageWrap">
      <table class="reportMemberManageTable" border="1">
		<tr>
			<th>번호</th>
			<th>신고한 회원 아이디</th>
			<th>[신고 대상 회원 아이디]</th>
			<th>[신고 대상 게시글 URL]</th>
			<th>요청 날짜</th>
			<th>신고 수락</th>
			<th>신고 거절</th>
		</tr>
		<%
			ReportMemberDao dao = new ReportMemberDao();
			ArrayList<ReportMemberDto> list = dao.showReport();
			
			for(ReportMemberDto member : list) {
		%>
		<tr>
			<td align="center" id="reportNo"><%=member.getReportNo()%></td>
			<td align="center"><%=member.getUserId()%></td>
			<td align="center" id="reportId"><%=member.getReportId()%></td>
			<td align="center"><a href="<%=member.getReportUrl()%>"><%=member.getReportUrl()%></a></td>
			<td align="center"><%=member.getRegDate()%></td>
			<td align="center"><button class="accept">수락</button></td>
			<td align="center"><button class="reject">거절</button></td>
		</tr>
		<%
			}
		%>
		
	   </table>
	</div>
</section>