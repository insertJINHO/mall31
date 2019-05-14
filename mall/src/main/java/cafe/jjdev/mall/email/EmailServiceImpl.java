package cafe.jjdev.mall.email;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.mail.MailException;
import org.springframework.mail.SimpleMailMessage;
import org.springframework.mail.javamail.JavaMailSender;
import org.springframework.stereotype.Component;

@Component
public class EmailServiceImpl {
    @Autowired
    public JavaMailSender emailSender;
    
    public void sendSimpleMessage(String to, String subject, String text) {
    	SimpleMailMessage message = new SimpleMailMessage();
    	message.setFrom("dkwkvkt@gmail.com");
    	message.setTo(to);//보낼 대상
        message.setSubject(subject);//제목
        message.setText(text);//내용
        System.out.println("EmailServiceImpl.sendSimpleMessage message : "+message);
        try{//예외처리
        	emailSender.send(message);
        }catch(MailException es){
        	es.printStackTrace();
       }
    }
}