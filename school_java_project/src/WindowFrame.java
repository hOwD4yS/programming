import javax.imageio.ImageIO;
import javax.swing.*;
import java.awt.*;
import java.awt.image.BufferedImage;
import java.io.File;
import java.io.IOException;

// Frame 상속해서
public class WindowFrame extends JDialog
{
    public WindowFrame(JFrame frame , String title) {
        super(frame,true);
        setTitle(title);
    }

}
