import javax.swing.*;
import java.awt.*;

public class AlertFrame extends JFrame {
    private JLabel contentLabel;

    AlertFrame(String title , String content){
        contentLabel = new JLabel(content);
        contentLabel.setHorizontalAlignment(JLabel.CENTER);

        add(contentLabel);
        setDefaultCloseOperation(JFrame.DISPOSE_ON_CLOSE);
        setVisible(true);
        setTitle(title);
        setSize(200, 100);
        setLocationRelativeTo(null);
    }

    void editText(String content){
        contentLabel.setText(content);
    }
}
