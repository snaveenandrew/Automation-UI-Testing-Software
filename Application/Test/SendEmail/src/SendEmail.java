/*import java.util.Properties;

import javax.mail.Message;
import javax.mail.MessagingException;
import javax.mail.Session;
import javax.mail.Transport;
import javax.mail.internet.InternetAddress;
import javax.mail.internet.MimeMessage;

public class SendEmail  
{  
 public static void main(String [] args){  
      String to = "rajamegam7@gmail.com";//change accordingly  
      String from = "snaveenandrew@gmail.com";//change accordingly  
      String host = "smtp.gmail.com:465:1";//"localhost";//or IP address  
  
     //Get the session object  
      Properties properties = System.getProperties();  
      properties.setProperty("mail.smtp.host", host);  
      Session session = Session.getDefaultInstance(properties);  
  
     //compose the message  
      try{  
         MimeMessage message = new MimeMessage(session);  
         message.setFrom(new InternetAddress(from));  
         message.addRecipient(Message.RecipientType.TO,new InternetAddress(to));  
         message.setSubject("Ping");  
         message.setText("Hello, this is example of sending email  ");  
  
         // Send message  
         Transport.send(message);  
         System.out.println("message sent successfully....");  
  
      }catch (MessagingException mex) {mex.printStackTrace();}  
   }  
}  */





    import java.util.Properties;

import javax.mail.Message;
import javax.mail.MessagingException;
import javax.mail.PasswordAuthentication;
import javax.mail.Session;
import javax.mail.Transport;
import javax.mail.internet.InternetAddress;
import javax.mail.internet.MimeMessage;  
      
    public class SendEmail {  
     public static void main(String[] args) {  
      
      String host="smtp.gmail.com:465:1";  
      final String user="snaveenandrew@gmail.com";//change accordingly  
      final String password="xxxxx";//change accordingly  
        
      String to="snaveenandrew@gmail.com";//change accordingly  
      
       //Get the session object  
       Properties props = new Properties();  
       props.put("mail.smtp.host",host);  
       props.put("mail.smtp.auth", "true");  
         
       Session session = Session.getDefaultInstance(props,  
        new javax.mail.Authenticator() {  
          protected PasswordAuthentication getPasswordAuthentication() {  
        return new PasswordAuthentication(user,password);  
          }  
        });  
      
       //Compose the message  
        try {  
         MimeMessage message = new MimeMessage(session);  
         message.setFrom(new InternetAddress(user));  
         message.addRecipient(Message.RecipientType.TO,new InternetAddress(to));  
         message.setSubject("javatpoint");  
         message.setText("This is simple program of sending email using JavaMail API");  
           
        //send the message  
         Transport.send(message);  
      
         System.out.println("message sent successfully...");  
       
         } catch (MessagingException e) {e.printStackTrace();}  
     }  
    }  