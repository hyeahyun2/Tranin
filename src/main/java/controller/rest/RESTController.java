package controller.rest;

import java.io.IOException;
import java.io.PrintWriter;
import java.util.ArrayList;

import javax.servlet.ServletException;
import javax.servlet.annotation.WebServlet;
import javax.servlet.http.HttpServlet;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;
import javax.ws.rs.GET;
import javax.ws.rs.POST;
import javax.ws.rs.Path;
import javax.ws.rs.Produces;
import javax.ws.rs.QueryParam;
import javax.ws.rs.core.Context;
import javax.ws.rs.core.MediaType;
import javax.ws.rs.core.Response;

import com.google.gson.Gson;

import dao.MarketDao;
import dao.MyPageDao;
import dao.NoticeDao;
import dao.RESTDao;
import dto.MarketDto;
import dto.MarketResponseDto;
import dto.MemberDto;
import dto.NoticeDto;

/*
 
 /api/rest : 기본경로
 /memberList.REST : 현재 활성화된 모든 멤버의 목록 출력 
 	- 회원의 id값을 패러미터로 입력하여 검색 가능. 예시) /memberList.REST?id=abc123
 	- 검색 패러미터가 없는경우 전체 목록 출력
 /bannedMemberList.REST : 현재 차단된 모든 멤버의 목록 출력
 	- 회원의 id값을 패러미터로 입력하여 검색 가능. 예시) /bannedMemberList.REST?id=abc123
 	- 검색 패러미터가 없는경우 전체 목록 출력
 /marketAllList.REST : 현재 활성화된 모든 장터글의 목록 출력
 	- 장터글이 판매글인지 구매글인지 패러미터로 입력하여야만 목록 출력. 예시) /marketAllList.REST?part=sell
 	- 장터글의 제목을 패러미터로 입력하여 검색 가능. 예시) /marketAllList.REST?part=sell&keyword=abc123
 	- 어느 경우든 part 패러미터가 없으면 출력 불가능
 /completedMarketList.REST : 회원번호를 패러미터로 그 회원의 완료된 모든 장터글의 목록 출력
 	- 글쓴이 회원번호를 입력해야만 목록 출력. 예시) /completedMarketList.REST?writerNo=33
 	- 장터글의 제목을 패러미터로 입력하여 검색 가능. 예시) /completedMarketList.REST?writerNo=33&keyword=abc123
 	- 어느 경우든 writerNo 패러미터가 없으면 출력 불가능
 /marketOne.REST : 글 번호를 패러미터로 해당 장터글 하나를 출력
  	- 글 번호를 입력해야만 해당 글을 출력. 예시) /marketOne.REST?no=1
  	- 글 번호를 입력하지 않으면 오류 발생
 /noticeList.REST : 현재 활성화된 모든 공지사항의 목록 출력
	- 공지사항의 제목을 패러미터로 입력하여 검색 가능. 예시)/noticeList.REST?keyword=abc123
	- 패러미터가 없는 경우에는 모든 목록 출력
 /noticeOne.REST : 글 번호를 패러미터로 해당 공지사항 하나를 출력
 	- 글 번호를 입력해야만 글을 출력. 예시) /noticeOne.REST?no=39
 	- 글 번호를 입력하지 않으면 오류 발생
*/

@Path("/rest")
public class RESTController {
	
	private Gson gson = new Gson();
	
	@GET
	@Produces(MediaType.TEXT_PLAIN)
	public String sayHello() {
		return "Hello API Service";
	}
	
	@GET
	@Path("/memberList.REST")	
    @Produces(MediaType.APPLICATION_JSON)
    public Response getMemberList(@Context HttpServletRequest request,
    		@Context HttpServletResponse response) {
		MyPageDao myPageDao = new MyPageDao();
		RESTDao restDao = new RESTDao();
		System.out.println(request.getServletPath());
		if(request.getParameter("keyword")!=null) {//검색어를 받아서 검색해서 가져오는 경우
			ArrayList<MemberDto> list = restDao.searchMemberREST("id",(String)request.getParameter("keyword"));
			//json파일로 반환하기
			String memberJsonString = this.gson.toJson(list);
		    return Response.ok().entity(memberJsonString).build();
		}else {//검색어 없이 전체목록을 가져오는 경우
			ArrayList<MemberDto> list = myPageDao.getMemberListNoPaging();
			//json파일로 반환하기
			String memberJsonString = this.gson.toJson(list);
		    return Response.ok().entity(memberJsonString).build();
		}
    }
	
	@GET
	@Path("/bannedMemberList.REST")	
    @Produces(MediaType.APPLICATION_JSON)
    public Response getBannedMemberList(@Context HttpServletRequest request,
            @Context HttpServletResponse response) {
		MyPageDao myPageDao = new MyPageDao();
		RESTDao restDao = new RESTDao();
		System.out.println(request.getServletPath());
		
		if(request.getParameter("keyword")!=null) {//검색어를 받아서 검색해서 가져오는 경우
			ArrayList<MemberDto> list = restDao.searchBannedMemberREST("title",(String)request.getParameter("keyword"));
			//json파일로 반환하기
			String memberJsonString = this.gson.toJson(list);
		    return Response.ok().entity(memberJsonString).build();
		}else {//검색어 없이 전체목록을 가져오는 경우
			ArrayList<MemberDto> list = myPageDao.getBannedMemberListNoPaging();
			//json파일로 반환하기
			String memberJsonString = this.gson.toJson(list);
		    return Response.ok().entity(memberJsonString).build();
		}
	}
	
	@GET
	@Path("/marketAllList.REST")	
    @Produces(MediaType.APPLICATION_JSON)
    public Response getMarketAllList(@Context HttpServletRequest request,
            @Context HttpServletResponse response) {
		MarketDao marketDao = new MarketDao();
		System.out.println(request.getServletPath());
		
		if(request.getParameter("part")!=null&&request.getParameter("keyword")!=null) {//검색어를 받아서 검색해서 가져오는 경우
			ArrayList<MarketDto> list = marketDao.getSearchPostList((String)request.getParameter("part"), (String)request.getParameter("keyword"), 0, Integer.MAX_VALUE);
			//json파일로 반환하기
			String marketJsonString = this.gson.toJson(list);
		    return Response.ok().entity(marketJsonString).build();
		}else if(request.getParameter("part")!=null) {//검색어 없이 전체목록을 가져오는 경우
			ArrayList<MarketDto> list = marketDao.getPostList((String)request.getParameter("part"), 0, Integer.MAX_VALUE);
			//json파일로 반환하기
			String marketJsonString = this.gson.toJson(list);
		    return Response.ok().entity(marketJsonString).build();
		}else {
			System.out.println("getMarketList 에러, 파트를 입력하세요");
			return null;
		}
	}
	
	@GET
	@Path("/completedMarketList.REST")	
    @Produces(MediaType.APPLICATION_JSON)
    public Response getCompletedMarketList(@Context HttpServletRequest request,
            @Context HttpServletResponse response) {
		MyPageDao myPageDao = new MyPageDao();
		RESTDao restDao = new RESTDao();
		System.out.println(request.getServletPath());
		
		if(request.getParameter("keyword")!=null&&request.getParameter("writerNo")!=null) {//검색어를 받아서 검색해서 가져오는 경우
			ArrayList<MarketResponseDto> list = restDao.getSearchTransactionDoneMarketListREST("title",(String)request.getParameter("keyword"),Integer.parseInt((String)request.getParameter("writerNo")));
			//json파일로 반환하기
			String marketJsonString = this.gson.toJson(list);
		    return Response.ok().entity(marketJsonString).build();
		}else if(request.getParameter("writerNo")!=null) {//검색어 없이 전체목록을 가져오는 경우
			ArrayList<MarketResponseDto> list = restDao.getTransactionDoneMarketListREST(Integer.parseInt((String)request.getParameter("writerNo")));
			//json파일로 반환하기
			String marketJsonString = this.gson.toJson(list);
		    return Response.ok().entity(marketJsonString).build();
		}else {
			System.out.println("getCompletedMarketList 에러, 글쓴이ID를 입력하세요");
			return null;
		}
	}
	
	@GET
	@Path("/marketOne.REST")	
    @Produces(MediaType.APPLICATION_JSON)
    public Response getMarketOne(@Context HttpServletRequest request,
            @Context HttpServletResponse response) {
		MarketDao marketDao = new MarketDao();
		System.out.println(request.getServletPath());
		
		if(request.getParameter("no")!=null) {//글번호로 글하나만 가져오는 경우
			MarketDto dto = marketDao.getPostInfoByNo(Integer.parseInt((String)request.getParameter("no")));
			//json파일로 반환하기
			String marketJsonString = this.gson.toJson(dto);
		    return Response.ok().entity(marketJsonString).build();
		}else {
			System.out.println("getMarketOne 에러, 마켓글의 번호를 입력하세요");
			return null;
		}
	}	
	
	@GET
	@Path("/noticeList.REST")	
    @Produces(MediaType.APPLICATION_JSON)
    public Response getNoticeList(@Context HttpServletRequest request,
            @Context HttpServletResponse response) {
		RESTDao restDao = new RESTDao();
		System.out.println(request.getServletPath());
		
		if(request.getParameter("keyword")!=null) {//검색어를 받아서 검색해서 가져오는 경우
			ArrayList<NoticeDto> list = restDao.getSearchREST((String)request.getParameter("keyword"));
			//json파일로 반환하기
			String noticeJsonString = this.gson.toJson(list);
		    return Response.ok().entity(noticeJsonString).build();
		}else {//검색어 없이 전체목록을 가져오는 경우
			ArrayList<NoticeDto> list = restDao.showNoticeREST();
			//json파일로 반환하기
			String noticeJsonString = this.gson.toJson(list);
		    return Response.ok().entity(noticeJsonString).build();
		}
	}
	
	@GET
	@Path("/noticeOne.REST")	
    @Produces(MediaType.APPLICATION_JSON)
    public Response getNoticeOne(@Context HttpServletRequest request,
            @Context HttpServletResponse response) {
		RESTDao restDao = new RESTDao();
		System.out.println(request.getServletPath());
		
		if(request.getParameter("no")!=null) {//글번호로 글하나만 가져오는 경우
			ArrayList<NoticeDto> list = restDao.getNoticeREST(Integer.parseInt((String)request.getParameter("no")));
			//json파일로 반환하기
			String noticeJsonString = this.gson.toJson(list);
		    return Response.ok().entity(noticeJsonString).build();
		}else {
			System.out.println("getNoticeOne 에러, 공지사항글의 번호를 입력하세요");
			return null;
		}
	}	
}
