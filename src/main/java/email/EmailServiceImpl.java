package email;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.mail.*;
import org.springframework.stereotype.Component;

/**
 * Created by finawei on 9/26/17.
 */
@Component
public class EmailServiceImpl  {
    //this annnotation create instatnce for us.
    @Autowired
    public JavaMailSender emailSender;

    //public JavaMailSender emailSender= new JavaMailSender();
    public void sendSimpleMessage (
            String to, String subject, String text
    ) {
        SimpleMailMessage message = new SimpleMailMessage();
        message.setTo(to);
        message.setSubject(subject);
        message.setText(text);
        emailSender.send(message);
    }

}
