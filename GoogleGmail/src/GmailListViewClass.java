import javax.mail.Message;
import javax.mail.MessagingException;
import javax.mail.internet.InternetAddress;
import javax.swing.*;
import java.awt.*;
import java.awt.event.ActionEvent;
import java.awt.event.MouseAdapter;
import java.awt.event.MouseListener;

public class GmailListViewClass {
    private JPanel mail;
    private JTextArea title;
    private JLabel recipient;
    private Message message;
    private final int MAX_TITLE_LEN = 25;


    public GmailListViewClass(Message message){
        mail = new JPanel();
        mail.setPreferredSize(new Dimension(100,100));

        try {
            String title_ = message.getSubject();
            String recipient_ = ((InternetAddress)message.getFrom()[0]).getAddress();

            if(title_ == null)
                title_ = "(제목없음)";

            if (title_.length() > MAX_TITLE_LEN)
                title_ = title_.substring(0, MAX_TITLE_LEN) + " ...";

            title = new JTextArea(title_);
            title.setWrapStyleWord(true);
            title.setLineWrap(true);
            title.setOpaque(false);
            title.setEditable(false);
            title.setFocusable(false);
            title.setMargin(new Insets(3, 3, 3, 3));
            title.setPreferredSize(new Dimension(100, 60));
            title.setFont(new Font("monospace", Font.PLAIN, 13));


            mail.setLayout(new FlowLayout());
            recipient = new JLabel(recipient_);
            recipient.setPreferredSize(new Dimension(100, 30));
            recipient.setBorder(BorderFactory.createLineBorder(Color.black));
            recipient.setHorizontalAlignment(JLabel.CENTER);
            recipient.setFont(new Font("monospace", Font.PLAIN, 10));

            mail.add(title);
            mail.add(recipient, FlowLayout.CENTER);

            mail.setBorder(BorderFactory.createLineBorder(Color.black));

        } catch (MessagingException e) {
            e.printStackTrace();
        }
    }

    JPanel getPanel(){
        return this.mail;
    }


    void AddClickListener(MouseListener ml){
        title.addMouseListener(ml);
        mail.addMouseListener(ml);
        recipient.addMouseListener(ml);
    }

    boolean IsMailWithSource(Object source){
        if(source == mail || source == title || source == recipient)
            return true;
        return false;
    }
}
