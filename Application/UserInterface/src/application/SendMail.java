package application;

import java.util.Properties;

import javax.activation.DataHandler;
import javax.activation.DataSource;
import javax.activation.FileDataSource;
import javax.mail.BodyPart;
import javax.mail.Message;
import javax.mail.MessagingException;
import javax.mail.Multipart;
import javax.mail.PasswordAuthentication;
import javax.mail.Session;
import javax.mail.Transport;
import javax.mail.internet.InternetAddress;
import javax.mail.internet.MimeBodyPart;
import javax.mail.internet.MimeMessage;
import javax.mail.internet.MimeMultipart;

public class SendMail {
 public void send(String to){
     String from = "sureshjohn739@gmail.com";//change accordingly
     final String username = "sureshjohn739";//change accordingly
     final String password = "sureshjohn739";//change accordingly
     String host = "smtp.gmail.com";
     Properties props = new Properties();
     props.put("mail.smtp.auth", "true");
     props.put("mail.smtp.starttls.enable", "true");
     props.put("mail.smtp.host", host);
     props.put("mail.smtp.port", "587");
     Session session = Session.getInstance(props, 
     new javax.mail.Authenticator() {
        protected PasswordAuthentication getPasswordAuthentication() {
           return new PasswordAuthentication(username, password);
        }
     });
     try {
         Message message = new MimeMessage(session);
         message.setFrom(new InternetAddress(from));
         message.setRecipients(Message.RecipientType.TO,
            InternetAddress.parse(to));
         message.setSubject("STB Automation Testing - LOG");
         BodyPart messageBodyPart = new MimeBodyPart();
         messageBodyPart.setText("Log data of past STB Automation Testing testcases is attached with this mail.\n\nThis is Auto-generated message negelect if received wrongly\n\n\n\nRegards,\nTesting Team");
         Multipart multipart = new MimeMultipart();
         multipart.addBodyPart(messageBodyPart);
         messageBodyPart = new MimeBodyPart();
         String filename = "E:\\Final year project\\UserInterface\\Data\\logData.txt";
         DataSource source = new FileDataSource(filename);
         messageBodyPart.setDataHandler(new DataHandler(source));
         messageBodyPart.setFileName(filename);
         multipart.addBodyPart(messageBodyPart);
         message.setContent(multipart);
         Transport.send(message);
      } catch (MessagingException e) {
         throw new RuntimeException(e);
      }
  }
 }
