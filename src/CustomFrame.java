import javax.swing.*;
import java.awt.*;
import java.io.IOException;

public class CustomFrame extends JFrame {
    CardLayout cards;
    StartingPanel mainPage;
    FormPanel formPage;

    CustomFrame() throws IOException {
        setSize(800,700);
        setDefaultCloseOperation(JFrame.EXIT_ON_CLOSE);
        setTitle("FORMULARZ");
        setResizable(false);
        setVisible(true);
        cards = new CardLayout();
        setLayout(cards);

        mainPage = new StartingPanel();
        formPage = new FormPanel();

        add(mainPage, "mainPage");
        add(formPage, "formSheet");
    }


}
