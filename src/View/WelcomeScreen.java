package View;

import javax.swing.*;
import java.awt.*;
import java.awt.event.ActionEvent;
import java.awt.event.ActionListener;

import Controller.GameController;
import Controller.GameState;

public class WelcomeScreen extends JPanel {
//    private final int BORDER_SIZE = 30;

    private GameController gameController;

    public WelcomeScreen() {
        this.setLayout(new BoxLayout(this, BoxLayout.PAGE_AXIS));
        this.setPreferredSize(new Dimension(WIDTH, HEIGHT));
        this.setBackground(new Color(255, 234, 206));
        this.setBorder(BorderFactory.createEmptyBorder(20, 20, 20, 20));

        // Welcome text
        JLabel welcomeLabel = new JLabel("The Adventures of Zelda");
        welcomeLabel.setFont(new Font(welcomeLabel.getFont().getName(), welcomeLabel.getFont().getStyle(), 50));
        welcomeLabel.setAlignmentX(Component.CENTER_ALIGNMENT);
//        welcomeLabel.setOpaque(true); //shows bg color

        // Play button
        JButton playButton = new JButton("PLAY");
        playButton.setAlignmentX(Component.CENTER_ALIGNMENT);

        playButton.addActionListener(new ActionListener() {
            public void actionPerformed(ActionEvent e) {
                gameController.gameStateUpdate(GameState.TUTORIAL);
            }
        } );

        // High score button
        JButton highScoreButton = new JButton("HIGH SCORES");
        highScoreButton.setAlignmentX(Component.CENTER_ALIGNMENT);

        // Add components to panel
        this.add(Box.createRigidArea(new Dimension(0, 200)));
        this.add(welcomeLabel);
        this.add(Box.createRigidArea(new Dimension(0, 10)));
        this.add(playButton);
        this.add(Box.createRigidArea(new Dimension(0, 10)));
        this.add(highScoreButton);
    }

    public void setGameController(GameController controller){
        gameController = controller;
    }

}
