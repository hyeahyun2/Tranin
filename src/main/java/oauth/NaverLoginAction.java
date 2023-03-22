package oauth;

import java.io.IOException;
import java.math.BigInteger;
import java.net.URLEncoder;
import java.security.SecureRandom;

import javax.servlet.ServletException;
import javax.servlet.annotation.WebServlet;
import javax.servlet.http.HttpServlet;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;
import javax.servlet.http.HttpSession;

import oauth.naverConst.NaverConst;

@WebServlet("/oauth/naver")
public class NaverLoginAction extends HttpServlet {

	@Override
	protected void service(HttpServletRequest req, HttpServletResponse resp) throws ServletException, IOException {
		// TODO Auto-generated method stub
	
		HttpSession session = req.getSession();
		
		String clientId = NaverConst.clientId;//애플리케이션 클라이언트 아이디값";
	    String redirectURI = URLEncoder.encode(NaverConst.redirectURL, "UTF-8");
	    SecureRandom random = new SecureRandom();
	    String state = new BigInteger(130, random).toString();
	    String apiURL = "https://nid.naver.com/oauth2.0/authorize?response_type=code";
	    apiURL += "&client_id=" + NaverConst.clientId;
	    apiURL += "&redirect_uri=" + NaverConst.redirectURL;
	    apiURL += "&state=" + state;
	    session.setAttribute("state", state);
	    
	    resp.sendRedirect(apiURL);
		
	}

	
	

}
