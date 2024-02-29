import javax.swing.*;
import java.awt.*;
import java.awt.event.ActionEvent;
import java.awt.event.ActionListener;
import java.util.HashMap;

public class FormPanel extends JPanel implements ActionListener {

    BoxLayout boxLay;
    CustomButton submitButton;
    JPanel centralPanel;
    JPanel leftPanel;
    JPanel rightPanel;
    JPanel bottomPanel;
    JComboBox genderList;
    CustomTextField test;
    CustomTextField test1;
    CustomTextField test2;

//    static HashMap<String,String> formOptions = new HashMap<>();
//
//    static {
//        formOptions.put("Imię","");
//        formOptions.put("Nazwisko","");
//        formOptions.put("PESEL","");
//        formOptions.put("Płeć","");
//        formOptions.put("Data urodzenia","");
//        formOptions.put("Nr telefonu","");
//    }

    FormPanel() {

        setLayout(new BorderLayout());

        centralPanel = new JPanel();
        leftPanel = new JPanel();
        rightPanel = new JPanel();
        bottomPanel = new JPanel();

        bottomPanel.setBackground(new Color(0xF7A543));
        centralPanel.setBackground(new Color(0xDC946E));
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

        centralPanel.add(Box.createVerticalStrut(20));
//        centralPanel.add(new JLabel("Imię"));
        test = createText("Imię",centralPanel);
        test1 = createText("Nazwisko",centralPanel);
        test2 = createText("PESEL",centralPanel);

        genderList = new JComboBox();

        submitButton = new CustomButton("SUBMIT");
        submitButton.setAlignmentX(0.5f);
        bottomPanel.add(submitButton);
        submitButton.addActionListener(this);
    }

    private CustomTextField createText(String text, JPanel jPanel){
        CustomTextField textField = new CustomTextField(text);
        jPanel.add(textField);
        jPanel.add(Box.createVerticalStrut(20));
        return textField;
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
