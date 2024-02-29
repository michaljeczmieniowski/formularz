import javax.swing.*;
import java.awt.*;
import java.awt.event.ActionEvent;
import java.awt.event.ActionListener;

public class FormPanel extends JPanel implements ActionListener {
    BoxLayout boxLay;
    CustomTextField textField1;
    CustomTextField textField2;
    CustomButton submitButton;

    FormPanel() {
        boxLay = new BoxLayout(this, BoxLayout.Y_AXIS);
        setLayout(boxLay);
        textField1 = new CustomTextField("Imię",20);
        add(textField1);
        textField2 = new CustomTextField("Nazwisko",20);
        add(textField2);
        submitButton = new CustomButton("SUBMIT");
        submitButton.setAlignmentX(0.5f);
        add(submitButton);
        submitButton.addActionListener(this);



    }

    @Override
    public void actionPerformed(ActionEvent e) {
        Container container = SwingUtilities.getAncestorOfClass(Container.class, this);
        if (container != null && container.getLayout() instanceof CardLayout) {
            CardLayout cardLayout = (CardLayout) container.getLayout();
            cardLayout.show(container, "mainPage");
        }
    }
}
