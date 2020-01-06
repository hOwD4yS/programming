import javax.mail.*;
import javax.swing.*;
import java.awt.*;
import java.awt.event.ActionEvent;
import java.awt.event.ActionListener;
import java.util.Properties;

public class Login extends JFrame implements ActionListener {

    JTextHintField EmailField;
    JPasswordField PasswordField;
    JLabel StatusLog;

    Login(){

        Container container = getContentPane();
        setLayout(new GridBagLayout());

        JLabel EmailLabel = new JLabel("Gmail : ");
        EmailField = new JTextHintField("abc@gmail.com",15);

        JLabel PasswordLabel = new JLabel("PW : ");
        PasswordField = new JPasswordField(15);

        JButton loginBtn = new JButton("Login");
        loginBtn.addActionListener(this);
        StatusLog = new JLabel("Please Login");

        GridBagConstraints gbc = new GridBagConstraints();

        gbc.gridx = 0;
        gbc.gridy = 0;

        add(EmailLabel, gbc); gbc.gridy++; add(PasswordLabel, gbc);

        gbc.gridy = 0; gbc.gridx++;

        add(EmailField, gbc); gbc.gridy++; add(PasswordField, gbc); gbc.gridy++;

        add(loginBtn, gbc); gbc.gridy++;
        add(StatusLog, gbc);


        setDefaultCloseOperation(JFrame.EXIT_ON_CLOSE);
        setVisible(true);
        setTitle("Gmail");
        setSize(400,300);
        setLocationRelativeTo(null);

    }


    @Override
    public void actionPerformed(ActionEvent ev) {

        //로그인
        String email = EmailField.getText();
        String password = String.valueOf(PasswordField.getPassword());

        Properties props = new Properties();
        props.setProperty("mail.store.protocol", "imaps");

        try {

            Session session = Session.getDefaultInstance(props, null);
            //session.setDebug(true);

            Store store = session.getStore("imaps");
            store.connect("imap.gmail.com", email , password );

            System.out.println("Login Success");

            StatusLog.setText("Login Success");

            dispose(); // 종료

            new List(new GmailClass(store , email , password ));


        } catch (MessagingException e) {
            //로그인 실패
            e.printStackTrace();
            System.out.println("Login Failed");
            StatusLog.setText("Login Failed");
            return;
        }


    }

}
