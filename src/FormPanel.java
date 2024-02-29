import javax.swing.*;
import java.awt.*;
import java.awt.event.ActionEvent;
import java.awt.event.ActionListener;

public class FormPanel extends JPanel implements ActionListener {
    BoxLayout boxLay;
    CustomLabel tempLabel;
    CustomButton submitButton;

    FormPanel() {
        boxLay = new BoxLayout(this, BoxLayout.Y_AXIS);
        setLayout(boxLay);
        tempLabel = new CustomLabel("oby działało");
        add(tempLabel);
        submitButton = new CustomButton("SUBMIT");
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
