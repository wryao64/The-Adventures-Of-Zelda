package View;

import javax.swing.*;
import java.awt.*;

public class WelcomeScreen extends JPanel {
    private final int BORDER_SIZE = 30;

    public WelcomeScreen() {
//        JPanel displayPane = new JPanel();
        this.setLayout(new BoxLayout(this, BoxLayout.PAGE_AXIS));
        this.setPreferredSize(new Dimension(WIDTH, HEIGHT));
        this.setBackground(Color.red); // TESTING

        Label welcomeLabel = new Label("The Legend of Zelda");
        welcomeLabel.setBackground(Color.blue);
        welcomeLabel.setAlignment(JLabel.CENTER); //TODO: DOES THIS WORK??

        Button playButton = new Button("PLAY");

        this.add(welcomeLabel);
        this.add(playButton);
        this.setBorder(BorderFactory.createEmptyBorder(BORDER_SIZE, BORDER_SIZE, BORDER_SIZE, BORDER_SIZE));
    }
}
