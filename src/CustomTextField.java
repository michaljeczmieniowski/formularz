import javax.swing.*;
import java.awt.*;
import java.awt.event.FocusEvent;
import java.awt.event.FocusListener;

public class CustomTextField extends JTextField {
    CustomTextField(String text){
        super(text);
        setAlignmentX(0.5f);
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
                    setForeground(Color.BLACK);
                }
                switch (text){
                    case "Imię","Nazwisko":
                        if (getText().matches("[a-zA-Z]+")){
                            setForeground(Color.BLACK);
                        }
                        else{
                            setForeground(Color.RED);
                        }
                    case "PESEL":
                        if (getText().matches("\\d+") & getText().length()==11){
                            setForeground(Color.BLACK);
                        }
                        else {
                            setForeground(Color.RED);
                        }
                }
//                else if (getText().matches("[a-zA-Z]+")){
//                    System.out.println("dobrze");
//                }

            }
        });
    }
}
