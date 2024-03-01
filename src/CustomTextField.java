import javax.swing.*;
import java.awt.*;
import java.awt.event.FocusEvent;
import java.awt.event.FocusListener;
import java.text.ParseException;
import java.text.SimpleDateFormat;
import java.util.Date;

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
                }
                switch (text){
                    case "Imię":
                        if (getText().matches("[A-ZŁŚŻ][a-ząćęłńóśźż]+")){
                            setForeground(Color.BLACK);
                        }
                        else if (getText().matches("[a-złśż][a-ząćęłńóśźż]+")){
                            setText(getText().substring(0,1).toUpperCase() + getText().substring(1));
                        }
                        else{
                            setForeground(Color.RED);
                        }
                        break;
                    case "Nazwisko":
                        if (getText().matches("[A-ZŁŚŻŹĆÓ][a-ząćęłńóśźż]+")){
                            setForeground(Color.BLACK);
                        }
                        else if (getText().matches("[a-złśżźćó][a-ząćęłńóśźż]+")){
                            setText(getText().substring(0,1).toUpperCase() + getText().substring(1));
                        }
                        else{
                            setForeground(Color.RED);
                        }
                        break;
                    case "PESEL":
                        if (getText().matches("\\d+") & getText().length()==11 & checkPESEL(getText())){
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
                        if (isValidDate(getText())) {
                            setForeground(Color.BLACK);
                        } else {
                            setForeground(Color.RED);
                        }
                        break;
                }
            }
        });
    }

    private boolean isValidDate(String inputDate) {
        SimpleDateFormat sdf = new SimpleDateFormat("yyyy-MM-dd");
        sdf.setLenient(false);
        try {
            Date parsedDate = sdf.parse(inputDate);
            return parsedDate != null;
        } catch (ParseException e) {
            return false;
        }
    }

    private boolean checkPESEL(String PESEL){
        int[] PESELnumbers = {1,3,7,9,1,3,7,9,1,3};
        int sum = 0;
        for(int i = 0; i < PESEL.length()-1; i++){
            sum += (PESELnumbers[i] * Character.getNumericValue(PESEL.charAt(i)));
        }
        return true;
    }
}
