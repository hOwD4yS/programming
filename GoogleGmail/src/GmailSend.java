import javax.mail.*;
import javax.mail.internet.InternetAddress;
import javax.mail.internet.MimeMessage;
import java.util.Properties;

public class GmailSend {
    static boolean Send(GmailSendClass gmsc){
        Properties props = System.getProperties();
        props.put("mail.smtp.starttls.enable", "true");
        props.put("mail.smtp.host", "smtp.gmail.com");
        props.put("mail.smtp.auth","true");
        props.put("mail.smtp.port", "587");

        Session session = Session.getInstance(props, new Authenticator() {
            @Override
            protected PasswordAuthentication getPasswordAuthentication() {
                return new PasswordAuthentication(gmsc.gmc.email,gmsc.gmc.password);
            }
        });

        try {
            Message message = new MimeMessage(session);
            message.setFrom(new InternetAddress(gmsc.gmc.email));
            message.setRecipients(Message.RecipientType.TO,InternetAddress.parse(gmsc.targetemail));
            message.setSubject(gmsc.title);
            message.setText(gmsc.content);
            Transport.send(message);
            return true;
        } catch (MessagingException e) {
            System.out.println("Unexpected error in GmailSend");
            e.printStackTrace();
            return false;
        }

    }
}
