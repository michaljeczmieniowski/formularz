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

    public StartingPanel() {
        Color lighter = new Color(248, 237, 228);
        setLayout(new BorderLayout());

        UpperPanel  = new JPanel();
        LowerPanel = new JPanel();
        RightPanel = new JPanel();
        LeftPanel = new JPanel();
        CentralPanel = new JPanel();

        LowerPanel.setBackground(lighter);
        RightPanel.setBackground(lighter);
        LeftPanel.setBackground(lighter);
        CentralPanel.setBorder(BorderFactory.createEmptyBorder(0, 5, 5, 5));
        CentralPanel.setBackground(lighter);

        UpperPanel.setPreferredSize(new Dimension(800,100));
        UpperPanel.setBackground(Color.WHITE);
        UpperPanel.add(Box.createVerticalStrut(100));
        LowerPanel.setPreferredSize(new Dimension(800,200));
        LowerPanel.add(Box.createVerticalStrut(100));
        RightPanel.setPreferredSize(new Dimension(65,700));
        LeftPanel.setPreferredSize(new Dimension(65,700));
        CentralPanel.setPreferredSize(new Dimension(670,500));

        boxLay = new BoxLayout(CentralPanel,BoxLayout.Y_AXIS);
        CentralPanel.setLayout(boxLay);
        CentralPanel.add(Box.createVerticalStrut(30));

        add(LeftPanel, BorderLayout.EAST);
        add(UpperPanel, BorderLayout.NORTH);
        add(CentralPanel, BorderLayout.CENTER);
        add(LowerPanel, BorderLayout.SOUTH);
        add(RightPanel, BorderLayout.WEST);

        WelcomeText = new JTextArea();
        WelcomeText.setText("Witaj!");
        WelcomeText.setFont(new Font("Bauhaus 93",Font.BOLD,72));

        WelcomeText.setEditable(false);
        WelcomeText.setFocusable(false);
        WelcomeText.setWrapStyleWord(true);
        WelcomeText.setOpaque(false);

        InformationText = new JTextArea();
        InformationText.setAlignmentX(Component.CENTER_ALIGNMENT);

        InformationText.setBackground(lighter);
        InformationText.setText("Jeśli chcesz wypełnić formularz, wciśnij przycisk ZAREJESTRUJ SIĘ");
        InformationText.setLineWrap(true);
        InformationText.setWrapStyleWord(true);
        InformationText.setEditable(false);
        InformationText.setFocusable(false);
        InformationText.setFont(new Font("Calibri",Font.BOLD,27));
        InformationText.setOpaque(true);

        RodoText = new JTextArea();
        RodoText.setBackground(lighter);
        RodoText.setText("Wcisnięcie przycisku ZAREJESTRUJ SIĘ jest równoznaczne z wyrażeniem zgody na przetwarzanie moich danych osobowych dla potrzeb niezbędnych do realizacji procesu rekrutacji (zgodnie z ustawą z dnia 10 maja 2018 roku o ochronie danych osobowych (Dz. Ustaw z 2018, poz. 1000) oraz zgodnie z Rozporządzeniem Parlamentu Europejskiego i Rady (UE) 2016/679 z dnia 27 kwietnia 2016 r.");
        RodoText.setLineWrap(true);
        RodoText.setWrapStyleWord(true);
        RodoText.setEditable(false);
        RodoText.setFocusable(false);
        RodoText.setFont(new Font("Calibri",Font.BOLD,14));
        RodoText.setOpaque(true);

        UpperPanel.add(WelcomeText);
        CentralPanel.add(InformationText);
        CentralPanel.add(RodoText);

        cb = new CustomButton("ZAREJESTRUJ SIĘ");
        cb.setFocusPainted(false);
        cb.addActionListener(this);
        cb.setMargin(new Insets(50,50,50,50));
        cb.setFont(new Font("SansSerif",Font.BOLD,30));
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