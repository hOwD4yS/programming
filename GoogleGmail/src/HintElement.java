import javax.swing.*;
import java.awt.*;
import java.awt.event.FocusEvent;
import java.awt.event.FocusListener;

class JTextHintField extends JTextField implements FocusListener {
    private String hint;
    private boolean isTypedChk;

    JTextHintField(String hint , int column){
        super(null,"",column);

        this.hint = hint;
        isTypedChk = false;
        setColumns(column);
        setText(hint);
        addFocusListener(this);
        setFont(new Font(getFont().getName(), Font.ITALIC, getFont().getSize()));
        setForeground(Color.gray);
    }

    @Override
    public void focusGained(FocusEvent e) {
        if((getText().equals(hint))) {
            isTypedChk = true;
            setFont(new Font(getFont().getName(), Font.PLAIN, getFont().getSize()));
            setForeground(Color.black);
            setText("");
        }
    }

    @Override
    public void focusLost(FocusEvent e) {
        if((getText().length() == 0) || getText().equals(hint)) {
            isTypedChk = false;
            setText(hint);
            setFont(new Font(getFont().getName(), Font.ITALIC, getFont().getSize()));
            setForeground(Color.gray);
        }
    }


    boolean isTyped(){
        return isTypedChk;
    }
}

class JTextHintArea extends JTextArea implements FocusListener {
    private String hint;
    private boolean isTypedChk;

    JTextHintArea(String hint , int row , int column){
        super(null,null,row,column);

        this.hint = hint;
        isTypedChk = false;
        setColumns(column);
        setText(hint);
        addFocusListener(this);
        setFont(new Font(getFont().getName(), Font.ITALIC, getFont().getSize()));
        setForeground(Color.gray);
    }

    @Override
    public void focusGained(FocusEvent e) {
        if((getText().equals(hint))) {
            isTypedChk = true;
            setFont(new Font(getFont().getName(), Font.PLAIN, getFont().getSize()));
            setForeground(Color.black);
            setText("");
        }
    }

    @Override
    public void focusLost(FocusEvent e) {
        if((getText().length() == 0) || getText().equals(hint)) {
            isTypedChk = false;
            setText(hint);
            setFont(new Font(getFont().getName(), Font.ITALIC, getFont().getSize()));
            setForeground(Color.gray);
        }
    }


    boolean isTyped(){
        return isTypedChk;
    }
}

