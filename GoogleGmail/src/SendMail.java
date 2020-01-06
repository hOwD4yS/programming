import java.awt.*;
import java.awt.event.*;

import javax.mail.internet.InternetAddress;
import javax.mail.internet.MimeMultipart;
import javax.swing.JButton;
import javax.swing.JFrame;
import javax.swing.JLabel;
import javax.swing.JPanel;
import javax.swing.JScrollPane;
import javax.swing.JTextArea;
import javax.swing.JTextField;

public class SendMail extends JFrame implements ActionListener{

    JTextHintField recipient;
    JTextHintField title;
    JTextHintArea content;
    GmailClass gmc;

    SendMail(GmailClass gmc){
        this.gmc = gmc;

        JPanel panel = new JPanel(new GridBagLayout());
        recipient = new JTextHintField("받을 이메일",20);

        title = new JTextHintField("제목",20);
        content = new JTextHintArea("내용",25,40);


        JScrollPane scrollPane = new JScrollPane(content);
        JButton send = new JButton("보내기");
        send.addActionListener(this);


        GridBagConstraints gbc = new GridBagConstraints();

        gbc.insets = new Insets(6, 6, 0, 0);

        gbc.gridx = 0;
        gbc.gridy = 0;

        panel.add(new JLabel("받을 이메일"), gbc); gbc.gridy++; panel.add(recipient, gbc); gbc.gridy++;
        panel.add(new JLabel("제목"), gbc); gbc.gridy++; panel.add(title, gbc); gbc.gridy++;
        gbc.insets = new Insets(6, 15, 0, 0);
        panel.add(scrollPane, gbc); gbc.gridy++; panel.add(send, gbc);


        panel.setPreferredSize(new Dimension(700, 730));

        setContentPane(panel);
        setDefaultCloseOperation(JFrame.DISPOSE_ON_CLOSE);
        setVisible(true);
        setTitle("SendMail");
        setSize(700, 730);
        setPreferredSize(new Dimension(700, 730));
        setLocationRelativeTo(null);
    }

    @Override
    public void actionPerformed(ActionEvent e) {
        if(!recipient.isTyped() || !title.isTyped() || !content.isTyped()){
            new AlertFrame("SendMail","Fill All the blank");
            return;
        }

        System.out.printf("[*] LOG : email : %s , title : %s , content %s \n",recipient.getText(),title.getText(),content.getText());
        boolean result = GmailSend.Send(new GmailSendClass(gmc, recipient.getText(), title.getText() , content.getText()));

        if(result) {
            new AlertFrame("SendMail", "Sending mail success");
            dispose();
        }else{
            new AlertFrame("SendMail","Unexpected Error occurred");
        }

    }

}