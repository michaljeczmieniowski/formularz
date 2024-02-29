import javax.swing.*;
import java.awt.*;
import java.awt.event.ActionEvent;
import java.awt.event.ActionListener;

public class StartPanel extends JPanel implements ActionListener {
    BoxLayout boxLay;

    public StartPanel() {
        boxLay = new BoxLayout(this, BoxLayout.Y_AXIS);
        setLayout(boxLay);

        CustomLabel cl = new CustomLabel("Witaj! \nJeśli chcesz wypełnić formularz swoimi danymi, kliknij przycisk Register");
        CustomButton cb = new CustomButton("REGISTER");
        cb.addActionListener(this);

        add(cl);
        add(cb);

    }


    @Override
    public void actionPerformed(ActionEvent e) {
        Container container = SwingUtilities.getAncestorOfClass(Container.class, this);
        if (container != null && container.getLayout() instanceof CardLayout) {
            CardLayout cardLayout = (CardLayout) container.getLayout();
            cardLayout.show(container, "formSheet");
        }
    }
}
