import org.json.simple.JSONArray;
import org.json.simple.JSONObject;
import org.json.simple.parser.JSONParser;
import org.json.simple.parser.ParseException;

import javax.swing.*;
import java.awt.*;
import java.awt.event.ActionEvent;
import java.awt.event.ActionListener;
import java.io.FileReader;
import java.io.FileWriter;
import java.io.IOException;

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

        add(centralPanel, BorderLayout.CENTER);
        add(leftPanel, BorderLayout.WEST);
        add(rightPanel, BorderLayout.EAST);
        add(bottomPanel, BorderLayout.SOUTH);

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
//            formOptions.put("Imie",test2.getText());
//            System.out.println(formOptions);
        }
        appendToJson(test.getText(), test1.getText());
    }

    public void appendToJson(String imie, String nazwisko) {
        JSONObject userData = new JSONObject();
        userData.put("Imię", imie);
        userData.put("Nazwisko", nazwisko);

        JSONArray usersList = new JSONArray();

        try {
            // Read existing data from the file if it exists
            FileReader fileReader = new FileReader("UsersData.json");
            JSONParser jsonParser = new JSONParser();
            Object obj = jsonParser.parse(fileReader);
            if (obj != null) {
                usersList = (JSONArray) obj;
            }
            fileReader.close();
        } catch (IOException | ParseException e) {
            e.printStackTrace();
        }

        // Append new user data to the existing list
        usersList.add(userData);

        // Write the updated list to the file
        try (FileWriter file = new FileWriter("UsersData.json", false)) {
            // We can write any JSONArray or JSONObject instance to the file
            file.write(usersList.toJSONString());
            file.flush();
        } catch (IOException e) {
            e.printStackTrace();
        }
    }


}
