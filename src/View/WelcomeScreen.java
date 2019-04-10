package View;

import javax.swing.*;
import java.awt.*;

public class WelcomeScreen extends JPanel {
//    private final int BORDER_SIZE = 30;

    public WelcomeScreen() {
        this.setLayout(new BoxLayout(this, BoxLayout.PAGE_AXIS));
        this.setPreferredSize(new Dimension(WIDTH, HEIGHT));
        this.setBackground(Color.red); // TESTING

        JLabel welcomeLabel = new JLabel("The Legend of Zelda");
        welcomeLabel.setFont(new Font(welcomeLabel.getFont().getName(), welcomeLabel.getFont().getStyle(), 50));
        welcomeLabel.setBackground(Color.blue);
        welcomeLabel.setAlignmentX(Component.CENTER_ALIGNMENT);
        welcomeLabel.setOpaque(true); //shows bg color

        JButton playButton = new JButton("PLAY");
        playButton.setAlignmentX(Component.CENTER_ALIGNMENT);

        this.add(welcomeLabel);
        this.add(Box.createRigidArea(new Dimension(0, 10)));
        this.add(playButton);
        this.setBorder(BorderFactory.createEmptyBorder(20,20,20,20));
    }
}
