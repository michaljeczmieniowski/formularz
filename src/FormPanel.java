import javax.swing.*;
import java.awt.*;
import java.awt.event.ActionEvent;
import java.awt.event.ActionListener;

public class FormPanel extends JPanel implements ActionListener {

    BoxLayout boxLay;
    CustomTextField textField1;
    CustomTextField textField2;
    CustomButton submitButton;
    JPanel centralPanel;
    JPanel leftPanel;
    JPanel rightPanel;
    JPanel bottomPanel;

    FormPanel() {

        setLayout(new BorderLayout());

        centralPanel = new JPanel();
        leftPanel = new JPanel();
        rightPanel = new JPanel();
        bottomPanel = new JPanel();

        bottomPanel.setBackground(Color.BLACK);
        centralPanel.setBackground(new Color(0,56,212));
        leftPanel.setBackground(Color.CYAN);

        centralPanel.setPreferredSize(new Dimension(600,600));
        leftPanel.setPreferredSize(new Dimension(100,600));
        rightPanel.setPreferredSize(new Dimension(100,600));
        bottomPanel.setPreferredSize(new Dimension(800,35));

        add(centralPanel,BorderLayout.CENTER);
        add(leftPanel,BorderLayout.WEST);
        add(rightPanel,BorderLayout.EAST);
        add(bottomPanel,BorderLayout.SOUTH);

        boxLay = new BoxLayout(centralPanel, BoxLayout.Y_AXIS);
        centralPanel.setLayout(boxLay);

        String test = createText("Imię",centralPanel);
        String test1 = createText("Nazwisko",centralPanel);

        centralPanel.setAlignmentX(Component.CENTER_ALIGNMENT);
        submitButton = new CustomButton("SUBMIT");
        submitButton.setAlignmentX(0.5f);
        bottomPanel.add(submitButton);
        submitButton.addActionListener(this);
    }

    private String createText(String text, JPanel jPanel){
        CustomTextField textField = new CustomTextField(text);
        jPanel.add(textField);
        jPanel.add(Box.createVerticalStrut(20));
        return text;
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
