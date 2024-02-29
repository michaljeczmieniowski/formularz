import javax.swing.*;
import java.awt.*;

public class CustomLabel extends JLabel {

    CustomLabel(String text) {
        setSize(300,600);
        setHorizontalAlignment(CENTER);
        setBackground(Color.PINK);
        setOpaque(true);
        setText(text);
    }

}
