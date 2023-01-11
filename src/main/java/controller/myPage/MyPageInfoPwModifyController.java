package controller.myPage;

import java.io.IOException;
import java.io.PrintWriter;

import javax.servlet.ServletException;
import javax.servlet.annotation.WebServlet;
import javax.servlet.http.HttpServlet;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;

import org.json.JSONObject;

import dao.MyPageDao;
import dto.ManagerDto;
import dto.MemberDto;
import util.MyUtils;

@WebServlet({"/myPage/myPageInfoPwModify","/myPage/myPageManagerInfoPwModify"})
public class MyPageInfoPwModifyController extends HttpServlet {
	private static final long serialVersionUID = 1L;
	public MyPageInfoPwModifyController() {
        super();
        System.out.println("MyPageInfoPwModifyController 입장");
    }

	protected void doPost(HttpServletRequest request, HttpServletResponse response) throws ServletException, IOException {
		response.setCharacterEncoding("utf-8");
		response.setContentType("json");
		MyUtils myUtils = new MyUtils();
        JSONObject jsonObject = new JSONObject(myUtils.getJSONObjectFromHttpServletRequest(request));
        MyPageDao dao = new MyPageDao();
        
        if(request.getServletPath().equals("/myPage/myPageInfoPwModify")) {
        	MemberDto member = dao.getMemberById((String)request.getSession().getAttribute("memberId"));
            
            System.out.println("입력한 기존 비번:"+jsonObject.get("pre"));
            System.out.println("입력한 새 비번:"+jsonObject.get("new"));
            System.out.println("현재 데이터베이스의 비번:"+member.getPw());
            
            if(member.getPw().equals(jsonObject.get("pre"))) {
            	//입력이 올바른 경우, 새비번으로 교체
            	dao.modifyMyPageInfo(member.getId(),(String)jsonObject.get("new"),member.getNickName(),member.getAddress(),member.getZipCode());
            	PrintWriter out = response.getWriter();
    			out.println("1");
    			out.flush();
    			out.close();
            }else {
            	//입력한 기존비번을 틀린경우, 튕구기
            	PrintWriter out = response.getWriter();
    			out.println("0");
    			out.flush();
    			out.close();
            }
        }else {
        	ManagerDto member = dao.getManagerById((String)request.getSession().getAttribute("manager"));
            
            System.out.println("입력한 기존 비번:"+jsonObject.get("pre"));
            System.out.println("입력한 새 비번:"+jsonObject.get("new"));
            System.out.println("현재 데이터베이스의 비번:"+member.getPw());
            
            if(member.getPw().equals(jsonObject.get("pre"))) {
            	//입력이 올바른 경우, 새비번으로 교체
            	dao.modifyManagerInfo(member.getId(),(String)jsonObject.get("new"),member.getName());
            	PrintWriter out = response.getWriter();
    			out.println("1");
    			out.flush();
    			out.close();
            }else {
            	//입력한 기존비번을 틀린경우, 튕구기
            	PrintWriter out = response.getWriter();
    			out.println("0");
    			out.flush();
    			out.close();
            }
        }

	}
}
