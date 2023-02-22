package kr.co.kmarket.util;


import org.springframework.stereotype.Component;

import java.util.Properties;


public class MailUtil {
    /*기본정보*/
    String sender = "aowlrxm14@gmail.com";
    String password = "비밀번호16자리";
    String receiver = "aowlrxm15@gmail.com";
    String title = "test mail";
    String content = "test text";

    // Gmail SMTP 서버 설정
    /*
    Properties props = new Properties();
		props.put("mail.smtp.host", "smtp.gmail.com");
		props.put("mail.smtp.port", "465");
		props.put("mail.smtp.auth", "true");
		props.put("mail.smtp.ssl.enable", "true");
		props.put("mail.smtp.ssl.trust", "smtp.gmail.com");

    Session session = Session.getInstance(props, new Authenticator() {
        @Override
        protected PasswordAuthentication getPasswordAuthentication() {
            return new PasswordAuthentication(sender, password);
        }
    });

    // 메일발송
    Message message = new MimeMessage(session);

		try {
        System.out.println("메일 전송 시작...");
        message.setFrom(new InternetAddress(sender, "관리자", "UTF-8"));
        message.addRecipient(Message.RecipientType.TO, new InternetAddress(receiver));
        message.setSubject(title);
        message.setContent(content, "text/html;charset=utf-8");
        Transport.send(message);


    } catch (Exception e) {
        e.printStackTrace();
        System.out.println("메일 전송 실패...");
    }

		System.out.println("메일 전송 성공...");

    }
    
*/


}
