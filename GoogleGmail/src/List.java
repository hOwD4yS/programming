
import java.awt.*;
import java.awt.event.*;
import javax.mail.*;
import javax.mail.internet.InternetAddress;
import javax.swing.*;
import javax.swing.event.ListSelectionEvent;
import javax.swing.event.ListSelectionListener;

public class List extends JFrame implements ActionListener{
    GmailListViewClass[] mailview;
    Message[] messages;
    GmailClass gmc;
    Timer timer;
    int messagepos;
    int messageCount;

    List(GmailClass gmc) {
        this.gmc = gmc;

        setLayout(new FlowLayout(FlowLayout.LEFT));

        try {

            Folder inbox = gmc.store.getFolder("inbox");
            inbox.open(Folder.READ_ONLY);

            messageCount = inbox.getMessageCount();
            messages = inbox.getMessages();
            mailview = new GmailListViewClass[messages.length];
            JPanel main = new JPanel(new GridBagLayout());

            JButton sendmailBtn = new JButton("쓰기");
            sendmailBtn.addActionListener(this);


            JPanel maillistpanel = new JPanel();
            maillistpanel.setLayout(new FlowLayout());

            JScrollPane scrollPane = new JScrollPane(maillistpanel);
            scrollPane.setHorizontalScrollBarPolicy(JScrollPane.HORIZONTAL_SCROLLBAR_ALWAYS);
            scrollPane.setVerticalScrollBarPolicy(JScrollPane.VERTICAL_SCROLLBAR_ALWAYS);

            scrollPane.setBounds(0, 0, 700, 700);
            JPanel contentPane = new JPanel(null);
            contentPane.setPreferredSize(new Dimension(700, 700));
            contentPane.add(scrollPane);

            System.out.println("message : " + messageCount);

            maillistpanel.setPreferredSize(new Dimension(650, 100 * (messageCount / 6) + 100)); // 약 한줄에 6개

            AlertFrame processframe = new AlertFrame("List","메일을 가져오고 있습니다...");


            GridBagConstraints gbc = new GridBagConstraints();

            gbc.gridx = 0;
            gbc.gridy = 0;

            main.add(sendmailBtn,gbc);
            gbc.gridy++;
            main.add(contentPane,gbc);

            add(main);

            setDefaultCloseOperation(JFrame.EXIT_ON_CLOSE);
            setTitle("Mail List");
            pack();
            setLocationRelativeTo(null);

            messagepos = 0;

            timer = new Timer(100, new ActionListener() {
                @Override
                public void actionPerformed(ActionEvent e) {
                    if(messagepos + 1 == messageCount) {
                        timer.stop();
                        setVisible(true); // SHOW
                    }

                    processframe.editText("메일을 가져오고 있습니다 " + (messagepos+1) + " / " + messageCount);

                    try {
                        mailview[messagepos] = new GmailListViewClass(messages[messagepos]);

                    } catch(Exception exception){
                        System.out.println("ERROR!");
                        System.out.println(messagepos + " occurred error");
                        exception.printStackTrace();
                        return;
                    }

                    mailview[messagepos].AddClickListener(new MouseListener() {
                        @Override
                        public void mouseClicked(MouseEvent e) {
                            Message ClickedMessage = null;
                            for(int i = 0; i < mailview.length; i++){
                                if(mailview[i] == null) continue;

                                if(mailview[i].IsMailWithSource(e.getSource())){
                                    ClickedMessage = messages[i];
                                    break;
                                }
                            }

                            if(ClickedMessage == null) {
                                System.out.println("Unexpected Error occurred"); dispose();
                            }

                            new Clickmail(ClickedMessage);
                        }

                        @Override
                        public void mousePressed(MouseEvent e) {

                        }

                        @Override
                        public void mouseReleased(MouseEvent e) {

                        }

                        @Override
                        public void mouseEntered(MouseEvent e) {

                        }

                        @Override
                        public void mouseExited(MouseEvent e) {

                        }
                    });

                    maillistpanel.add(mailview[messagepos].getPanel());
                    messagepos += 1;
                }
            });
            timer.setRepeats(true);
            timer.setCoalesce(true);
            timer.setInitialDelay(0);
            timer.start();


        }
        catch (MessagingException e) {
            e.printStackTrace();
        }

    }




    @Override
    public void actionPerformed(ActionEvent e) {
        new SendMail(gmc);
    }
}