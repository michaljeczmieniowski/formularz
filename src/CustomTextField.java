import javax.swing.*;
import java.awt.*;
import java.awt.event.FocusEvent;
import java.awt.event.FocusListener;

public class CustomTextField extends JTextField {
    CustomTextField(String text){
        super(text);
//        setAlignmentX(0.775f);
        setMaximumSize(new Dimension(400,20));
        addFocusListener(new FocusListener() {
            @Override
            public void focusGained(FocusEvent e) {
                if(getText().equals(text)){
                    setText("");
                }
            }

            @Override
            public void focusLost(FocusEvent e) {
                if(getText().equals("")){
                    setText(text);
                }
            }
        });
    }
}
