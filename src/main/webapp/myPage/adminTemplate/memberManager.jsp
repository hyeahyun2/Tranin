<%@ page language="java" contentType="text/html; charset=UTF-8"
    pageEncoding="UTF-8"%>
<%@ page import="dao.MyPageDao" %>
<%@ page import="dto.MemberDto" %>
<%@ page import="java.util.*" %>
<%@ page import="java.sql.*" %>
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
			int pageNum = 1; // 페이지 번호가 전달이 안되면 1페이지
			if(request.getParameter("pageNum") != null) { // 페이지 번호가 전달이 된 경우
				pageNum = Integer.parseInt(request.getParameter("memberManagerNo"));					
			}
			ArrayList<MemberDto> memberArrayList = (ArrayList<MemberDto>)request.getAttribute("memberArrayList");
			for(MemberDto member : memberArrayList){
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
	<div id="memberManagerPaging">
    	<%
    	int cntListPerPage = 10;
   
    	MyPageDao dao = new MyPageDao();
		ResultSet rs = dao.getAllMemberList();
		rs.next();
		int totalRecord = rs.getInt(1);
		int totalPage = (totalRecord % cntListPerPage == 0) ? totalRecord / cntListPerPage : (totalRecord / cntListPerPage) + 1;
    	
		int block = 5; // 페이지 나올 범위
		int blockTotal = totalPage % block == 0 ? totalPage / block : totalPage / block + 1; // 총 블럭의 수
		int blockThis = ((pageNum - 1) / block) + 1; // 현재 페이지의 블럭
		int blockThisFirstPage = ((blockThis - 1) * block) + 1; // 현재 블럭의 첫 페이지
		int blockThisLastPage = blockThis * block; // 현재 블럭의 마지막 페이지
		out.println(blockThisLastPage);
		blockThisLastPage = (blockThisLastPage > totalPage) ? totalPage : blockThisLastPage; 
    	%>
        <a href="managerPage?myPageManagerCategory=2&memberManager=0&memberManagerNo=1">첫 페이지</a>
        <% 
        if (blockThis > 1) {
        %>
        <a href="managerPage?myPageManagerCategory=2&memberManager=0&memberManagerNo=<%=(blockThisFirstPage - 1)%>">앞으로</a>
        <% 
        }
        
       	for(int i = blockThisFirstPage; i <= blockThisLastPage; i++) {
        %>
        <a href="managerPage?myPageManagerCategory=2&memberManager=0&memberManagerNo=<%=i%>" class="num"><%=i%></a>
        <%
        }
        %>
        <%-- 
        <li><a href="#" class="num">2</a></li>
        <li><a href="#" class="num">3</a></li>
        <li><a href="#" class="num">4</a></li>
        <li><a href="#" class="num">5</a></li>
        --%>
        <%
        if(blockThis < blockTotal) {
        %>
        <a href="managerPage?myPageManagerCategory=2&memberManager=0&memberManagerNo=<%=(blockThisLastPage + 1)%>">뒤로</a>
        <%
        }
        %>
        <a href="managerPage?myPageManagerCategory=2&memberManager=0&memberManagerNo=<%=totalPage%>">마지막 페이지</a>
    </div>
  </div>
</section>
<script>

</script>