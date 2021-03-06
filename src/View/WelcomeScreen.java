package View;

import javax.swing.*;
import java.awt.*;

import Controller.GameController;
import Controller.GameState;

public class WelcomeScreen extends JPanel {
    private GameController gameController;
    private GameState gameState = GameState.WELCOME;

    public static final String IMAGE_LOCATION = "Resources/Assets/";

    private final int IMAGE_WIDTH = 1200;
    private final int IMAGE_HEIGHT = 800;

    public WelcomeScreen() {
        this.setLayout(null);

        JLabel background = new JLabel("", new ImageIcon(IMAGE_LOCATION + "welcomeBackground.png"), JLabel.CENTER);
        background.setBounds(0, 0, IMAGE_WIDTH, IMAGE_HEIGHT);

        // Play button
        JButton playButton = new JButton("PLAY");
        playButton.setBounds(530, 550, 140, 40);

        playButton.addActionListener(e ->
                gameController.updateGameState(GameState.TUTORIAL)
        );

        // High score button
        JButton highScoreButton = new JButton("HIGH SCORES");
        highScoreButton.setAlignmentX(Component.CENTER_ALIGNMENT);
        highScoreButton.addActionListener(e ->
                gameController.updateGameState(GameState.HIGHSCORE)
        );
        highScoreButton.setBounds(530, 600, 140, 40);

        background.add(playButton);
        background.add(highScoreButton);
        this.add(background);
    }

    public void setGameController(GameController controller) {
        gameController = controller;
    }
}
