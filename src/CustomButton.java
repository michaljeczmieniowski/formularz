import javax.swing.*;
import java.awt.*;
import java.awt.event.ActionEvent;
import java.awt.event.ActionListener;

public class CustomButton extends JButton implements ActionListener {

    CustomButton(String text) {
        setBounds(150,500,100,25);
        setFont(new Font("Arial",Font.BOLD,10));
        addActionListener(this);
        setVisible(true);
        setText(text);

    }

    @Override
    public void actionPerformed(ActionEvent e) {

    }
}
