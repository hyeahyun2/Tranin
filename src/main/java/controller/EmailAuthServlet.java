package controller;


import javax.mail.Message;
import javax.mail.Session;
import javax.mail.Transport;
import javax.mail.internet.InternetAddress;
import javax.mail.internet.MimeMessage;
import javax.servlet.ServletException;
import javax.servlet.annotation.WebServlet;
import javax.servlet.http.HttpServlet;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;
import javax.servlet.http.HttpSession;
import java.io.IOException;
import java.util.Properties;
import java.util.Random;

@WebServlet("/EmailAuth")
public class EmailAuthServlet extends HttpServlet {

    @Override
    protected void doGet(HttpServletRequest request, HttpServletResponse response) throws ServletException, IOException {
        request.setCharacterEncoding("utf-8");
        response.setContentType("text/html; charset=UTF-8");


        String email = request.getParameter("email");

        System.out.println(email);


        String host = "smtp.googlemail.com";
        String user = "yusuhyun0716@gmail.com";
        String password = "atdcdvpltgfoaltw";

        String to_email = email;

        Properties props = new Properties();
        props.setProperty("mail.smtp.ssl.protocols", "TLSv1.2");
        props.put("mail.smtp.host", host);
        props.put("mail.smtp.port", 465);
        props.put("mail.smtp.auth", "true");
        props.put("mail.smtp.ssl.enable", "true");


        StringBuffer temp = new StringBuffer();
        Random rnd = new Random();
        for (int i = 0; i < 10; i++) {
            int rIndex = rnd.nextInt(3);
            switch (rIndex) {
                case 0:
                    temp.append((char) ((int) (rnd.nextInt(26)) + 97));
                    break;
                case 1:
                    temp.append((char) ((int) (rnd.nextInt(26)) + 65));
                    break;
                case 2:
                    temp.append((rnd.nextInt(10)));
                    break;
            }
        }
        String AuthenticationKey = temp.toString();
        System.out.println(AuthenticationKey);

        Session session = Session.getDefaultInstance(props, new javax.mail.Authenticator() {
            protected javax.mail.PasswordAuthentication getPasswordAuthentication() {
                return new javax.mail.PasswordAuthentication(user, password);
            }
        });

        try {
            MimeMessage msg = new MimeMessage(session);
            msg.setFrom(new InternetAddress(user, "Tranin"));
            msg.addRecipient(Message.RecipientType.TO, new InternetAddress(to_email));

            msg.setSubject("안녕하세요 Tranin 인증 메일입니다.");
            msg.setText("인증 번호는 :" + temp + "입니다.");

            Transport.send(msg);
            System.out.println("이메일 전송");

        } catch (Exception e) {
            e.printStackTrace();
        }
        HttpSession saveKey = request.getSession();
        saveKey.setAttribute("AuthenticationKey", AuthenticationKey);
    }

}

