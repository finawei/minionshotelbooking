package email;
import org.springframework.context.annotation.Bean;
import org.springframework.mail.SimpleMailMessage;
import org.springframework.mail.javamail.JavaMailSenderImpl;
import org.springframework.stereotype.Component;

import java.util.Properties;

/**
 * Created by finawei on 9/26/17.
 */

@Component
public class JavaMailSender extends JavaMailSenderImpl {

    public JavaMailSender () {
        super();
        configure();
    }

    private void configure () {
        setHost("smtp.gmail.com");
        setPort(587);//what is this port?

        setUsername("minionshotelbooking@gmail.com");
        setPassword("hellominions");

        Properties props = getJavaMailProperties();
        props.put("mail.transport.protocal","smtp");
        props.put("mail.smtp.auth","true");
        props.put("mail.smtp.starttls.enable","true");
        props.put("mail.debug","true");
    }

//
//    public static JavaMailSender getJavaMailSender() {
//        JavaMailSender mailSender = new JavaMailSender();
//        mailSender.setHost("smtp.gmail.com");
//        mailSender.setPort(587);//what is this port?
//
//        mailSender.setUsername("minionshotelbooking@gmail.com");
//        mailSender.setPassword("hellominions");
//
//        Properties props = mailSender.getJavaMailProperties();
//        props.put("mail.transport.protocal","smtp");
//        props.put("mail.smtp.auth","true");
//        props.put("mail.smtp.starttls.enable","true");
//        props.put("mail.debug","true");
//
//
//        return mailSender;
//
//    }

//    public static void main(String[] args) {
//        JavaMailSender jms;
//        jms= getJavaMailSender();
////        EmailServiceImpl esl= new EmailServiceImpl();
////        esl.sendSimpleMessage("finawei@gmail.com","disable annotation"," <3");
//        SimpleMailMessage message = new SimpleMailMessage();
//        message.setTo("finawei@gmail.com");
//        message.setSubject("This is not spam");
//        message.setText("Demo of today's work. Sent from hotelbooking app backend. \n Fina <3");
//        jms.send(message);
//    }
}
