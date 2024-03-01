import javax.imageio.ImageIO;
import javax.swing.*;
import java.awt.*;
import java.awt.event.ActionEvent;
import java.awt.event.ActionListener;
import java.awt.image.BufferedImage;
import java.io.File;
import java.io.IOException;

public class StartPanel extends JPanel implements ActionListener {
    BoxLayout boxLay;
    JLabel logoLabel;
    JTextArea welcomeInfo;
    CustomButton cb;

    public StartPanel() throws IOException {
        boxLay = new BoxLayout(this, BoxLayout.Y_AXIS);
        setLayout(boxLay);

        BufferedImage userLOGO = ImageIO.read(new File("resources\\user.png"));
        Image newImage = userLOGO.getScaledInstance(200, 200, Image.SCALE_DEFAULT);

        ImageIcon logoIcon = new ImageIcon(newImage);

        logoLabel = new JLabel(logoIcon);

        // info about joining
        welcomeInfo = new JTextArea("Witaj! \nJeśli chcesz wypełnić formularz swoimi danymi, kliknij przycisk Register");
        welcomeInfo.setBackground(Color.PINK);
        welcomeInfo.setEditable(false);
        welcomeInfo.setLineWrap(true);
        welcomeInfo.setFocusable(false);

        // register button
        cb = new CustomButton("REGISTER");
        cb.addActionListener(this);

        add(logoLabel);
        add(welcomeInfo);
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
