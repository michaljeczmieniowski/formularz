import javax.swing.*;
import java.awt.*;
import java.awt.event.ActionEvent;
import java.awt.event.ActionListener;
import java.io.IOException;

public class StartingPanel extends JPanel implements ActionListener {

    BoxLayout boxLay;
    CustomButton cb;
    JPanel UpperPanel;
    JPanel LowerPanel;
    JPanel RightPanel;
    JPanel LeftPanel;
    JPanel CentralPanel;
    JTextArea WelcomeText;
    JTextArea RodoText;
    JTextArea InformationText;

    public StartingPanel() throws IOException {
        setLayout(new BorderLayout());

        UpperPanel  = new JPanel();
        LowerPanel = new JPanel();
        RightPanel = new JPanel();
        LeftPanel = new JPanel();
        CentralPanel = new JPanel();

        LowerPanel.setBackground(new Color(0x9693F5));
        UpperPanel.setBackground(new Color(0x9693F5));
        RightPanel.setBackground(new Color(0x9693F5));
        LeftPanel.setBackground(new Color(0x9693F5));
        CentralPanel.setBackground(new Color(0xBFF19A));

        UpperPanel.setPreferredSize(new Dimension(800,100));
        LowerPanel.setPreferredSize(new Dimension(800,360));
        RightPanel.setPreferredSize(new Dimension(65,700));
        LeftPanel.setPreferredSize(new Dimension(65,700));
        CentralPanel.setPreferredSize(new Dimension(670,300));

        boxLay = new BoxLayout(CentralPanel,BoxLayout.Y_AXIS);
        CentralPanel.setLayout(boxLay);

        add(LeftPanel, BorderLayout.EAST);
        add(UpperPanel, BorderLayout.NORTH);
        add(CentralPanel, BorderLayout.CENTER);
        add(LowerPanel, BorderLayout.SOUTH);
        add(RightPanel, BorderLayout.WEST);

        WelcomeText = new JTextArea();
        WelcomeText.setText("Witamy!");
        WelcomeText.setFont(new Font("Bauhaus 93",Font.BOLD,72));
        WelcomeText.setEditable(false);
        WelcomeText.setWrapStyleWord(true);
        //WelcomeText.setAlignmentX(Component.CENTER_ALIGNMENT);
        WelcomeText.setOpaque(false);

        RodoText = new JTextArea();
        RodoText.setBackground(new Color(0xBFF19A));
        RodoText.setText("Wcisnięcie przycisku REGISTER jest równoznaczne z wyrażeniem zgody na przetwarzanie moich danych osobowych dla potrzeb niezbędnych do realizacji procesu rekrutacji (zgodnie z ustawą z dnia 10 maja 2018 roku o ochronie danych osobowych (Dz. Ustaw z 2018, poz. 1000) oraz zgodnie z Rozporządzeniem Parlamentu Europejskiego i Rady (UE) 2016/679 z dnia 27 kwietnia 2016 r.");
        RodoText.setLineWrap(true);
        RodoText.setWrapStyleWord(true);
        RodoText.setEditable(false);
        RodoText.setFont(new Font("Calibri",Font.BOLD,14));
        RodoText.setOpaque(true);

        InformationText = new JTextArea();
        InformationText.setBackground(new Color(0xBFF19A));
        InformationText.setText("Jeśli chcesz wypełnić formularz, wciśnij przycisk REGISTER");
        InformationText.setLineWrap(true);
        InformationText.setWrapStyleWord(true);
        InformationText.setEditable(false);
        InformationText.setFont(new Font("Calibri",Font.BOLD,27));
        InformationText.setOpaque(true);

        UpperPanel.add(WelcomeText);
        CentralPanel.add(InformationText);
        CentralPanel.add(RodoText);


        cb = new CustomButton("REGISTER");
        cb.addActionListener(this);
        cb.setMargin(new Insets(50,50,50,50));
        cb.setFont(new Font("Bauhaus 93",Font.BOLD,30));
        LowerPanel.add(cb);




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