<%@ page language="java" contentType="text/html; charset=UTF-8"
    pageEncoding="UTF-8"%>
<%@ page import="dao.MyPageDao" %>
<%@ page import="dto.MemberDto" %>
<%@ page import="java.util.*" %>
<section id="myPageAdminRepManage">
    <a href="#">멤버관리</a>
    <div id="myPageAdminRepManage">
      <table class="myPageAdminRepManageTable">
		<tr>
			<th><input name="chkAll" type="checkbox" onClick="setChkAll();">선택</th>
			<th>회원번호</th>
			<th>아이디</th>
			<th>닉네임</th>
			<th>주소</th>
			<th>우편번호</th>
			<th>비고</th>
		</tr>
		<%
			int adminNo= Integer.parseInt(request.getParameter("id"));
			int cnt =0;
			MyPageDao memberDao = new MyPageDao();
			ArrayList<MemberDto> memberArrayList = memberDao.getMemberList(adminNo);
			for(MemberDto member : memberArrayList){
				cnt++;
		%>
		<tr>
			<td><input type="checkbox" name="chkID" value="<%=member.getNo()%>" onClick="setChkAlone(this);"></td>
			<td><%=member.getNo()%></td>
			<td><%=member.getId()%></td>
			<td><%=member.getNickName()%></td>
			<td><%=member.getAddress()%></td>
			<td><%=member.getZipCode()%></td>
			<td><span class="badge badge-danger btn" onclick="banMemberByNo('<%=member.getNo()%>')">차단</span></td>
		</tr>
		<%
			}
		%>
		<tr>
			<th></th>
			<th></th>
			<th></th>
			<th></th>
			<th></th>
			<th></th>
			<th></th>
		</tr>
	</table>
  </div>
</section>