package oauth;

import java.io.BufferedReader;
import java.io.IOException;
import java.io.InputStream;
import java.io.InputStreamReader;
import java.io.UnsupportedEncodingException;
import java.net.HttpURLConnection;
import java.net.MalformedURLException;
import java.net.URL;
import java.net.URLEncoder;
import java.util.HashMap;
import java.util.Map;

import javax.servlet.ServletException;
import javax.servlet.annotation.WebServlet;
import javax.servlet.http.HttpServlet;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;
import javax.servlet.http.HttpSession;

import org.apache.tomcat.util.json.JSONParser;
import org.json.JSONObject;

import com.fasterxml.jackson.core.JsonProcessingException;
import com.fasterxml.jackson.core.type.TypeReference;
import com.fasterxml.jackson.databind.ObjectMapper;
import com.fasterxml.jackson.databind.util.JSONWrappedObject;
import com.sun.mail.iap.Response;

import dao.MemberDao;
import dto.MemberDto;
import oauth.naverConst.NaverConst;

@WebServlet("/oauth/naver/token")
public class NaverGetToken extends HttpServlet  {

	@Override
	protected void service(HttpServletRequest req, HttpServletResponse resp) throws ServletException, IOException {
		 String token = getToken(req, resp); // 네이버 로그인 접근 토큰;
//        String header = "Bearer " + token; // Bearer 다음에 공백 추가
//
//
//        String apiURL = "https://openapi.naver.com/v1/nid/me";
//
//
//        Map<String, String> requestHeaders = new HashMap<>();
//        requestHeaders.put("Authorization", header);
//        String responseBody = get(apiURL,requestHeaders);


//        System.out.println(responseBody);
		 MemberDao memberDao = new MemberDao();
		 HttpSession session = req.getSession();
		 if(token != null) {
			HashMap<String, String> getUserInfo = getUserInfo(token);
			System.out.println(getUserInfo.toString());
			MemberDto member = new MemberDto();
			String userEmail = "Naver@" + getUserInfo.get("id");
			member.setId(userEmail);
			member.setNickName(getUserInfo.get("nickname"));
			member.setPw("NaverJoin");
			if(memberDao.insertMember(member)) {
				session.setAttribute("memberId", userEmail);
				session.setAttribute("memberNick", getUserInfo.get("nickname"));
			}
		 } else {
			System.out.println("토큰 에러 발생");
		 }
		 resp.sendRedirect("/mainPage");
	}
	
	private String getToken(HttpServletRequest req, HttpServletResponse resp) throws UnsupportedEncodingException  {
		// TODO Auto-generated method stub
				String clientId = NaverConst.clientId;//애플리케이션 클라이언트 아이디값";
			    String clientSecret = NaverConst.clientSecret;//애플리케이션 클라이언트 시크릿값";
			    String code = req.getParameter("code");
			    String state = req.getParameter("state");
			    String redirectURI = URLEncoder.encode(NaverConst.redirectURL, "UTF-8");
			    String apiURL;
			    apiURL = "https://nid.naver.com/oauth2.0/token?grant_type=authorization_code&";
			    apiURL += "client_id=" + NaverConst.clientId;
			    apiURL += "&client_secret=" + NaverConst.clientSecret;
			    apiURL += "&redirect_uri=" + NaverConst.redirectURL;
			    apiURL += "&code=" + code;
			    apiURL += "&state=" + state;
			    String access_token = "";
			    String refresh_token = "";
			    System.out.println("apiURL="+apiURL);
			    try {
			      URL url = new URL(apiURL);
			      HttpURLConnection con = (HttpURLConnection)url.openConnection();
			      con.setRequestMethod("GET");
			      int responseCode = con.getResponseCode();
			      BufferedReader br;
			      System.out.print("responseCode="+responseCode);
			      if(responseCode==200) { // 정상 호출
			        br = new BufferedReader(new InputStreamReader(con.getInputStream()));
			      } else {  // 에러 발생
			        br = new BufferedReader(new InputStreamReader(con.getErrorStream()));
			      }
			      String inputLine;
			      StringBuffer res = new StringBuffer();
			      while ((inputLine = br.readLine()) != null) {
			        res.append(inputLine);
			      }
			      br.close();
			      if(responseCode==200) {
			    	String access_Token = null;
	                // jackson objectmapper 객체 생성
	                ObjectMapper objectMapper = new ObjectMapper();
	                // JSON String -> Map
	                Map<String, Object> jsonMap = objectMapper.readValue(res.toString(), new TypeReference<Map<String, Object>>() {
	                });

	                access_Token = jsonMap.get("access_token").toString();
	             
			        return access_Token;
			      }
			    } catch (Exception e) {
			      System.out.println(e);
			    }
			    return null;
	}
	
	  private static String get(String apiUrl, Map<String, String> requestHeaders){
	        HttpURLConnection con = connect(apiUrl);
	        try {
	            con.setRequestMethod("GET");
	            for(Map.Entry<String, String> header :requestHeaders.entrySet()) {
	                con.setRequestProperty(header.getKey(), header.getValue());
	            }


	            int responseCode = con.getResponseCode();
	            if (responseCode == HttpURLConnection.HTTP_OK) { // 정상 호출
	                return readBody(con.getInputStream());
	            } else { // 에러 발생
	                return readBody(con.getErrorStream());
	            }
	        } catch (IOException e) {
	            throw new RuntimeException("API 요청과 응답 실패", e);
	        } finally {
	            con.disconnect();
	        }
	    }


	    private static HttpURLConnection connect(String apiUrl){
	        try {
	            URL url = new URL(apiUrl);
	            return (HttpURLConnection)url.openConnection();
	        } catch (MalformedURLException e) {
	            throw new RuntimeException("API URL이 잘못되었습니다. : " + apiUrl, e);
	        } catch (IOException e) {
	            throw new RuntimeException("연결이 실패했습니다. : " + apiUrl, e);
	        }
	    }


	    private static String readBody(InputStream body){
	        InputStreamReader streamReader = new InputStreamReader(body);


	        try (BufferedReader lineReader = new BufferedReader(streamReader)) {
	            StringBuilder responseBody = new StringBuilder();


	            String line;
	            while ((line = lineReader.readLine()) != null) {
	                responseBody.append(line);
	            }


	            return responseBody.toString();
	        } catch (IOException e) {
	            throw new RuntimeException("API 응답을 읽는데 실패했습니다.", e);
	        }
	    }
	    
	    private HashMap<String, String> getUserInfo(String token) {
	        HashMap<String, String> userInfo = null;
	        String header = "Bearer " + token; // Bearer 다음에 공백 추가
	        String apiURL = "https://openapi.naver.com/v1/nid/me";


	        Map<String, String> requestHeaders = new HashMap<>();
	        requestHeaders.put("Authorization", header);
	        String responseBody = get(apiURL,requestHeaders);
//	        log.info("responseBody = {}",responseBody);


	        // jackson objectmapper 객체 생성
	        ObjectMapper objectMapper = new ObjectMapper();
	        // JSON String -> Map
	        try {
	            Map<String, Object> jsonMap = objectMapper.readValue(responseBody, new TypeReference<Map<String, Object>>() {});
//	            log.info("jsonMap = {}",jsonMap);
	            userInfo = (HashMap<String, String>) jsonMap.get("response");

//	            log.info("[네이버 응답] id = {}",userInfo.get("id"));
//	            log.info("[네이버 응답] profile_image = {},",userInfo.get("profile_image"));
	        } catch (JsonProcessingException e) {
	            throw new RuntimeException(e);
	        }
	        return userInfo;
	    }
	
}
