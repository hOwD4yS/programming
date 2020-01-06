import java.awt.*;
import java.awt.event.ActionEvent;
import java.awt.event.ActionListener;
import java.io.IOException;

import javax.mail.BodyPart;
import javax.mail.Message;
import javax.mail.MessagingException;
import javax.mail.internet.InternetAddress;
import javax.mail.internet.MimeMultipart;
import javax.swing.*;

class Clickmail  extends JFrame implements ActionListener {
    Clickmail(Message message) {
        try {
            String content_ = message.getContent().toString();;
            if(message.isMimeType("multipart/*")) {
                MimeMultipart mimeMultipart = (MimeMultipart) message.getContent();
                content_ = getTextFromMimeMultipart(mimeMultipart);
            }

            JPanel panel = new JPanel(new GridBagLayout());
            JTextArea recipient = new JTextArea(((InternetAddress)message.getFrom()[0]).getAddress());
            JTextArea title = new JTextArea(message.getSubject());
            JTextArea content = new JTextArea(content_, 35, 50);

            recipient.setEditable(false);

            title.setEditable(false);
            content.setEditable(false);

            JScrollPane scrollPane = new JScrollPane(content);
            JButton back = new JButton("돌아가기");
            back.addActionListener(this);


            GridBagConstraints gbc = new GridBagConstraints();

            gbc.insets = new Insets(6, 6, 0, 0);

            gbc.gridx = 0;
            gbc.gridy = 0;

            panel.add(new JLabel("받은이메일"), gbc); gbc.gridy+=2; panel.add(recipient, gbc); gbc.gridy++;
            panel.add(new JLabel("제목"), gbc); gbc.gridy++; panel.add(title, gbc); gbc.gridy++;
            gbc.insets = new Insets(6, 15, 0, 0);
            panel.add(scrollPane, gbc); gbc.gridy++; panel.add(back, gbc);

            //panel.add(new JLabel("제목"), gbc); gbc.gridx++; add(title, gbc);



            panel.setPreferredSize(new Dimension(700, 730));

            setContentPane(panel);
            setDefaultCloseOperation(JFrame.DISPOSE_ON_CLOSE);
            setVisible(true);
            setTitle("Clickmail");
            setSize(700, 730);
            setPreferredSize(new Dimension(700, 730));
            setLocationRelativeTo(null);

        } catch (MessagingException e) {
            e.printStackTrace();
        } catch (IOException e) {
            e.printStackTrace();
        }
    }

    @Override
    public void actionPerformed(ActionEvent e) {
        dispose();
    }

     String getTextFromMimeMultipart( MimeMultipart mimeMultipart)  throws MessagingException, IOException{
        //https://stackoverflow.com/questions/11240368/how-to-read-text-inside-body-of-mail-using-javax-mail
        String result = "";
        int count = mimeMultipart.getCount();
        for (int i = 0; i < count; i++) {
            BodyPart bodyPart = mimeMultipart.getBodyPart(i);
            if (bodyPart.isMimeType("text/plain")) {
                result = result + "\n" + bodyPart.getContent();
                break; // without break same text appears twice in my tests
            } else if (bodyPart.isMimeType("text/html")) {
                String html = (String) bodyPart.getContent();
                result = result + "\n" + org.jsoup.Jsoup.parse(html).text();
            } else if (bodyPart.getContent() instanceof MimeMultipart){
                result = result + getTextFromMimeMultipart((MimeMultipart)bodyPart.getContent());
            }
        }
        return result;
    }

}