<%@ page language="java" contentType="text/html; charset=UTF-8"
    pageEncoding="UTF-8"%>
<%@ page import="dao.MyPageDao" %>
<%@ page import="dto.MemberDto" %>
<%@ page import="java.util.*" %>
<section id="myPageAdmin">
  <div id="myPageAdminLeftNavWrap">
    <nav id="myPageAdminNav">
      <ul>
        <li class="enabled">통계</li>
        <li>게시판관리</li>
        <li>댓글관리</li>
        <li>멤버관리</li>
      </ul>
    </nav>
  </div>
  <div id="myPageAdminInnerTab">
    <section class="active" id="myPageAdminStatus">
      <a href="#">통계에오</a>
      <div id="myPageAdminStatus">
        <ul id="myPageAdminStatusHeader">
          <li>컬럼1</li>
          <li>컬럼2</li>
          <li>컬럼3</li>
          <li>컬럼4</li>
          <li>컬럼5</li>
        </ul>
        <ul class="myPageAdminStatusContent">
          <li>필드1</li>
          <li>필드2</li>
          <li>필드3</li>
          <li>필드4</li>
          <li>필드5</li>
        </ul>
        <ul class="myPageAdminStatusContent">
          <li>필드1</li>
          <li>필드2</li>
          <li>필드3</li>
          <li>필드4</li>
          <li>필드5</li>
        </ul>
      </div>
    </section>
    <section id="myPageAdminBanManage">
      <a href="#">게시판관리</a>
      <div id="myPageAdminBanManage">
        <ul id="myPageAdminBanManageHeader">
          <li>컬럼1</li>
          <li>컬럼2</li>
          <li>컬럼3</li>
          <li>컬럼4</li>
          <li>컬럼5</li>
        </ul>
        <ul class="myPageAdminBanManageContent">
          <li>필드1</li>
          <li>필드2</li>
          <li>필드3</li>
          <li>필드4</li>
          <li>필드5</li>
        </ul>
        <ul class="myPageAdminBanManageContent">
          <li>필드1</li>
          <li>필드2</li>
          <li>필드3</li>
          <li>필드4</li>
          <li>필드5</li>
        </ul>
      </div>
    </section>
    <section id="myPageAdminBoardManage">
      <a href="#">댓글관리</a>
      <div id="myPageAdminBoardManage">
        <ul id="myPageAdminBoardManageHeader">
          <li>컬럼1</li>
          <li>컬럼2</li>
          <li>컬럼3</li>
          <li>컬럼4</li>
          <li>컬럼5</li>
        </ul>
        <ul class="myPageAdminBoardManageContent">
          <li>필드1</li>
          <li>필드2</li>
          <li>필드3</li>
          <li>필드4</li>
          <li>필드5</li>
        </ul>
        <ul class="myPageAdminBoardManageContent">
          <li>필드1</li>
          <li>필드2</li>
          <li>필드3</li>
          <li>필드4</li>
          <li>필드5</li>
        </ul>
      </div>
    </section>
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
					int cnt =0;
					MyPageDao memberDao = new MyPageDao();
					ArrayList<MemberDto> memberArrayList = memberDao.getMemberList();
					for(MemberDto member : memberArrayList){
						cnt++;
				%>
				<tr>
					<td><input type="checkbox" name="chkID" value="<%=member.getNo()%>" onClick="setChkAlone(this);"></td>
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
  </div>
</section>
<script>

	const myAdmin = document.querySelector("#myAdmin");
	myAdmin.classList.add("active");
	
	const myPageAdminNavUlLi = document.querySelectorAll("#myPageAdminNav ul li")
	const myPageAdminInnerTabSection = document.querySelectorAll("#myPageAdminInnerTab section");
	
	for(let i=0;i<myPageAdminNavUlLi.length;i++){
	  console.log(i);
	  myPageAdminNavUlLi[i].addEventListener('click',function(){
	    for(let j=0;j<myPageAdminNavUlLi.length;j++){
	    	myPageAdminNavUlLi[j].classList.remove("enabled");
	    }
	    myPageAdminNavUlLi[i].classList.add("enabled");
	    
	  	  console.log(i);
	  	    for(let j=0;j<myPageAdminInnerTabSection.length;j++){
	  	    	myPageAdminInnerTabSection[j].classList.remove("active");
	  	    }
	  	    myPageAdminInnerTabSection[i].classList.add("active");
	    
	  });
	}
	
	
	
</script>