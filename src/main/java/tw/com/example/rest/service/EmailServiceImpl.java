package tw.com.example.rest.service;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.mail.MailException;
import org.springframework.mail.SimpleMailMessage;
import org.springframework.mail.javamail.JavaMailSender;
import org.springframework.mail.javamail.MimeMessageHelper;
import org.springframework.mail.javamail.MimeMessagePreparator;
import org.springframework.stereotype.Service;

@Service
public class EmailServiceImpl {
 
    private JavaMailSender mailSender;
 
    @Autowired
    public EmailServiceImpl(JavaMailSender mailSender) {
        this.mailSender = mailSender;
    }
 
    public void prepareAndSend(String recipient, String message) {
       MimeMessagePreparator messagePreparator = mimeMessage -> {
             MimeMessageHelper messageHelper = new MimeMessageHelper(mimeMessage);
             messageHelper.setFrom("demo.taipei@gmail.com");
             messageHelper.setTo(recipient);
             messageHelper.setSubject(message);
             messageHelper.setText(message);
         };
         try {
             mailSender.send(messagePreparator);
             //System.out.println("sent");
         } catch (MailException e) {
             //System.out.println(e);
             // runtime exception; compiler will not force you to handle it
         }
    }
 
}