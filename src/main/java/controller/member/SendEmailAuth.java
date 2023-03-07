package controller.member;

import java.util.Properties;
import java.util.Random;

import javax.mail.Message;
import javax.mail.MessagingException;
import javax.mail.PasswordAuthentication;
import javax.mail.Session;
import javax.mail.Transport;
import javax.mail.internet.AddressException;
import javax.mail.internet.InternetAddress;
import javax.mail.internet.MimeMessage;

/**
 * 이메일 인증하기 : 이메일로 인증번호 전송
 * */
public class SendEmailAuth {

	public String sendMail(String mail) {
		/* 인증코드 생성 */
		Random random = new Random();
		String authKey = "";
		for(int i=0; i<3; i++) {
			// 랜덤 알파벳 생성
			int index = random.nextInt(25) + 65;
			authKey += (char)index;
		}
		// 4자리 랜덤 정수 생성
		int numIndex = random.nextInt(9999) + 1000;
		authKey += numIndex;
		
		/* 이메일 보내기 */
		// mail server 설정
		String host = "smtp.googlemail.com";
		String user = "hyhyDevelop@gmail.com"; // 자신의 구글 계정
		String password = "bnvvuuyfocjgehxc";// 구글 메일 2차 인증 번호
		
		// 메일 받을 주소
		String to_email = mail;
		System.out.println("inputedEmail : " + mail);

		// SMTP 서버 정보를 설정한다.
		Properties prop = new Properties();
		prop.setProperty("mail.smtp.ssl.protocols", "TLSv1.2");
		prop.put("mail.smtp.host", host);
		prop.put("mail.smtp.port", 465);
		prop.put("mail.smtp.auth", "true");
		prop.put("mail.smtp.ssl.enable", "true");
        
		Session session = Session.getDefaultInstance(prop, new javax.mail.Authenticator() {
			protected PasswordAuthentication getPasswordAuthentication() {
				return new PasswordAuthentication(user, password);
			}
		});
		
		MimeMessage msg = new MimeMessage(session);
		
		// email 전송
		try {
			msg.setFrom(new InternetAddress(user));
			msg.addRecipient(Message.RecipientType.TO, new InternetAddress(to_email));

			// 메일 제목
			msg.setSubject("Tranin 이메일 인증 번호 전송", "UTF-8");
			// 메일 내용
			msg.setText("인증 번호 :" + authKey);

			Transport.send(msg);
			System.out.println("이메일 전송 : " + authKey);

		} catch (AddressException e) { 
			e.printStackTrace(); 
		} catch (MessagingException e) { 
			e.printStackTrace(); 
		}
		
		return authKey;
	}
}
