//package email;
//
//import javax.mail.Message;
//import javax.mail.MessagingException;
//import javax.mail.Session;
//import javax.mail.Transport;
//import javax.mail.internet.InternetAddress;
//import javax.mail.internet.MimeMessage;
//import java.util.Properties;
//import javax.activation.*;
//
///**
// * Created by finawei on 9/26/17.
// */
//public class SendEmail {
//    public static void main (String [] args) {
//        String to = "finawei@gmail.com";
//        String from="minionshotelbooking@gmai.com";
//        String host="localhost";
//        Properties properties = System.getProperties();
//        //setup mail server
//        properties.setProperty("mail.smtp.host",host);
//        //Get the defalt sesoon object. what is session?
//        Session session = Session.getDefaultInstance(properties);
//        properties.setProperty("mail.user","minionshotelbooking@gmail.com");
//        properties.setProperty("mail.password","hellominions");
//        try{
//            //create a default mimemessage object
//            MimeMessage message = new MimeMessage(session);
//            message.setFrom(new InternetAddress(from));
//            message.addRecipients(Message.RecipientType.TO, "finawei@gmail.com" );
//            message.setSubject("this is an test");
//            message.setText("test msg");
//            //send msg
//            Transport.send(message);
//
//        } catch(MessagingException mex) {
//            mex.printStackTrace();
//        }
//    }
//}

