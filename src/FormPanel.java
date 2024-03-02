import com.google.gson.Gson;
import com.google.gson.GsonBuilder;
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
    JPanel textPanel;
    JLabel textLabel;
    JComboBox genderList;
    CustomTextField name;
    CustomTextField surname;
    CustomTextField PESEL;
    CustomTextField birthDate;
    CustomTextField email;
    CustomTextField phoneNumber;
    String[] genders = {"Mężczyzna", "Kobieta", "Wolę nie podawać"};
    CustomButton cancelButton;

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
        name = createText("Imię",centralPanel);
        surname = createText("Nazwisko",centralPanel);
        PESEL = createText("PESEL",centralPanel);

        textPanel = new JPanel();
        textLabel = new JLabel("Płeć");
        textPanel.setAlignmentX(0.5f);
        textPanel.setMaximumSize(new Dimension(400,20));
        centralPanel.add(Box.createVerticalStrut(5));
        textPanel.setBackground(Color.PINK);
        textPanel.setOpaque(true);
        centralPanel.add(textPanel);
        textPanel.add(textLabel);

        genderList = new JComboBox(genders);
        genderList.setMaximumSize(new Dimension(400,20));
        centralPanel.add(genderList);

        centralPanel.add(Box.createVerticalStrut(20));
        birthDate = createText("Data urodzenia",centralPanel);
        email = createText("E-mail",centralPanel);
        phoneNumber = createText("Nr telefonu",centralPanel);

        submitButton = new CustomButton("ZATWIERDŹ");
        submitButton.setAlignmentX(0.5f);
        submitButton.setFocusPainted(false);
        bottomPanel.add(submitButton);
        submitButton.addActionListener(this);

        cancelButton = new CustomButton("ANULUJ");
        cancelButton.setFocusPainted(false);
        bottomPanel.add(cancelButton);

        cancelButton.addActionListener(new ActionListener() {
            @Override
            public void actionPerformed(ActionEvent e) {
                Container container = SwingUtilities.getAncestorOfClass(Container.class, FormPanel.this);
                if (container != null && container.getLayout() instanceof CardLayout) {
                    CardLayout cardLayout = (CardLayout) container.getLayout();
                    cardLayout.show(container, "mainPage");
                }
            }
        });


    }

    private CustomTextField createText(String text, JPanel jPanel){
        CustomTextField textField = new CustomTextField(text);
        JPanel textPanel = new JPanel();
        JLabel textLabel = new JLabel(text);
        if(text.equals("Data urodzenia")){
            textLabel.setText(text+" (rrrr-mm-dd)");
        }
        textPanel.setAlignmentX(0.5f);
        textPanel.setMaximumSize(new Dimension(400,20));
        jPanel.add(Box.createVerticalStrut(5));
        textPanel.setBackground(Color.PINK);
        textPanel.setOpaque(true);

        jPanel.add(textPanel);
        textPanel.add(textLabel);
        jPanel.add(textField);
        jPanel.add(Box.createVerticalStrut(20));
        return textField;
    }

    @Override
    public void actionPerformed(ActionEvent e) {
        Container container = SwingUtilities.getAncestorOfClass(Container.class, this);

        if (isBirthMatchingPESEL(birthDate.getText(), PESEL.getText()) &&
                container != null && container.getLayout() instanceof CardLayout &&
                name.hasCorrectInput() && surname.hasCorrectInput() && PESEL.hasCorrectInput() &&
                birthDate.hasCorrectInput() && email.hasCorrectInput() && phoneNumber.hasCorrectInput()){
            JOptionPane.showMessageDialog(null, "Pomyślnie zarejestrowano!", "Rejestracja zatwierdzona", JOptionPane.INFORMATION_MESSAGE);
            CardLayout cardLayout = (CardLayout) container.getLayout();
            cardLayout.show(container, "mainPage");
            User user = new User(name.getText(), surname.getText(), Long.parseLong(PESEL.getText()), birthDate.getText(), email.getText(), Integer.parseInt(phoneNumber.getText()), genderList.getSelectedItem().toString());
            appendToJson(name.getText(), surname.getText(), Long.parseLong(PESEL.getText()), birthDate.getText(), email.getText(), Integer.parseInt(phoneNumber.getText()), genderList.getSelectedItem().toString());
        }
        else {
            JOptionPane.showMessageDialog(null, "Istnieją pola, które zostały błędnie uzupełnione!", "Rejestracja odrzucona", JOptionPane.ERROR_MESSAGE);
        }

        if (!isBirthMatchingPESEL(birthDate.getText(), PESEL.getText())){
            JOptionPane.showMessageDialog(null, "Data urodzenia nie zgadza się z numerem PESEL!", "Rejestracja odrzucona", JOptionPane.ERROR_MESSAGE);
        }


    }

    public void appendToJson(String imie, String nazwisko, long PESEL, String dataUrodzenia, String email, int nrTelefonu, String plec) {
        JSONObject userData = new JSONObject();
        userData.put("Imię", imie);
        userData.put("Nazwisko", nazwisko);
        userData.put("PESEL", PESEL);
        userData.put("Płeć", plec);
        userData.put("Data urodzenia", dataUrodzenia);
        userData.put("Adres e-mail", email);
        userData.put("Numer telefonu", nrTelefonu);

        JSONArray usersList = new JSONArray();

        try {
            FileReader fileReader = new FileReader("userData\\UsersData.json");
            JSONParser jsonParser = new JSONParser();
            Object obj = jsonParser.parse(fileReader);
            if (obj != null) {
                usersList = (JSONArray) obj;
            }
            fileReader.close();
        } catch (IOException | ParseException e) {
            e.printStackTrace();
        }

        JSONObject userWrapper = new JSONObject();
        int userNumber = usersList.size() + 1;
        String userKey = "User" + userNumber;
        userWrapper.put(userKey, userData);

        usersList.add(userWrapper);

        try (FileWriter file = new FileWriter("userData\\UsersData.json", false)) {
            Gson gson = new GsonBuilder().setPrettyPrinting().create();
            String jsonOutput = gson.toJson(usersList);
            file.write(jsonOutput);
            file.flush();
        } catch (IOException e) {
            e.printStackTrace();
        }
    }

    private boolean isBirthMatchingPESEL(String birthDate, String PESEL){
        String firstDigitOfYear = birthDate.substring(0,1);
        switch (firstDigitOfYear){
            case "1":
                if (birthDate.substring(5,7).equals(PESEL.substring(2,4)) & birthDate.substring(8).equals(PESEL.substring(4,6))) {
                    return true;
                }
                break;
            case "2":
                int monthDigits = Integer.parseInt(birthDate.substring(5,7)) + 20;
                if (String.valueOf(monthDigits).equals(PESEL.substring(2,4)) & birthDate.substring(8).equals(PESEL.substring(4,6))) {
                    return true;
                }
        }
        return false;
    }
}
