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
                        if (getText().matches("[A-ZŁŚŻ][a-ząćęłńóśźż]+")){
                            setForeground(Color.BLACK);
                        }
                        else{
                            setForeground(Color.RED);
                        }
                        break;
                    case "PESEL":
                        if (getText().matches("\\d+") & getText().length()==11){
                            setForeground(Color.BLACK);
                        }
                        else {
                            setForeground(Color.RED);
                        }
                        break;
                    case "E-mail":
                        if(getText().matches(".+@(o2\\.pl|pw\\.edu\\.pl|wp\\.pl|yahoo\\.com|gmail\\.com)")){
                            setForeground(Color.BLACK);
                        }
                        else {
                            setForeground(Color.RED);
                        }
                        break;
                    case "Nr telefonu":
                        if (getText().matches("\\d+") & getText().length()==9){
                            setForeground(Color.BLACK);
                        }
                        else {
                            setForeground(Color.RED);
                        }
                        break;
                    case "Data urodzenia":
                        if (getText().matches("\\d{4}-\\d{2}-\\d{2}")){
                            setForeground(Color.BLACK);
                        }
                        else {
                            setForeground(Color.RED);
                        }
                        break;
                }
            }
        });
    }
}
