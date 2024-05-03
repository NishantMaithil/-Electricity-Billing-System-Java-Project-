package org.example.email;

import java.util.Properties;
import jakarta.mail.*;
import jakarta.mail.internet.InternetAddress;
import jakarta.mail.internet.MimeMessage;


public class GMailSender {
    public boolean sendEmail(String to,String from,String subject,String content){
        boolean flag = false;
        //logic
        Properties properties = new Properties();
        properties.put("mail.smtp.auth",true);
        properties.put("mail.smtp.starttls.enable",true);
        properties.put("mail.smtp.port","587");
        properties.put("mail.smtp.host","smtp.gmail.com");

        //session get
        String username = "nmaithil42";
        String password = "oulm ywbe rlir crtc";


        //session
        Session session = Session.getInstance(properties, new Authenticator() {
            @Override
            protected PasswordAuthentication getPasswordAuthentication() {
                return new PasswordAuthentication(username, password);
            }
        });
        try {

            Message message = new MimeMessage(session);
            message.setRecipient(Message.RecipientType.TO, new InternetAddress(to));
            message.setFrom(new InternetAddress(from));
            message.setSubject(subject);

            message.setText(content);
            Transport.send(message);
            flag = true;
        } catch (Exception e) {
          e.printStackTrace();

        }


        return flag;
    }
}
