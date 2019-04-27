package View;

import Controller.GameController;
import Controller.GameState;
import Controller.Sound;
import Controller.ScoreManager;

import javax.swing.*;
import java.awt.*;
import java.util.HashMap;

public class EndScreen extends JPanel {
    private GameController gameController;
    private GameState gameState = GameState.END;
    HashMap<String,Integer> playerStats;

    private boolean hasWon;
    private boolean newHighScore = false;
    private ScoreManager sm = new ScoreManager();

    private JLabel mainLabel;
    private JLabel subLabel;
    private JLabel hsLabel;

    public EndScreen(boolean success, HashMap<String,Integer> stats) {
        hasWon = success;
        playerStats = stats;

        if(sm.newScore(playerStats.get("Total: ").toString())){
            newHighScore = true;
        }

        this.setLayout(new BoxLayout(this, BoxLayout.PAGE_AXIS));
        this.setPreferredSize(new Dimension(WIDTH, HEIGHT));
        this.setBackground(new Color(255, 234, 206));
        this.setBorder(BorderFactory.createEmptyBorder(20, 20, 20, 20));

        // Main heading
        mainLabel = new JLabel("");
        mainLabel.setFont(new Font(mainLabel.getFont().getName(), mainLabel.getFont().getStyle(), 50));
        mainLabel.setAlignmentX(Component.CENTER_ALIGNMENT);

        // Sub heading
        subLabel = new JLabel("");
        subLabel.setFont(new Font(subLabel.getFont().getName(), subLabel.getFont().getStyle(), 30));
        subLabel.setAlignmentX(Component.CENTER_ALIGNMENT);

        // High score label
        hsLabel = new JLabel("");
        subLabel.setFont(new Font(subLabel.getFont().getName(), subLabel.getFont().getStyle(), 30));
        subLabel.setAlignmentX(Component.CENTER_ALIGNMENT);

        // Buttons panel-------------------------------------------------------------------------
        JPanel buttonPanel = new JPanel();
        buttonPanel.setLayout(new BoxLayout(buttonPanel, BoxLayout.LINE_AXIS));
        buttonPanel.setBackground(new Color(255, 255, 255));

        // Home button
        JButton homeButton = new JButton("HOME");
        homeButton.addActionListener(e ->
                gameController.updateGameState(GameState.WELCOME)
        );

        // High score button
        JButton highScoreButton = new JButton("HIGH SCORES");
        highScoreButton.addActionListener(e -> {
            if (newHighScore) {
                System.out.println("right method call");
                gameController.updateGameState(GameState.HIGHSCORE,playerStats.get("Total: ").toString(),success,playerStats);
            } else
                gameController.updateGameState(GameState.HIGHSCORE);
        }
        );

        buttonPanel.add(homeButton);
        buttonPanel.add(Box.createRigidArea(new Dimension(20, 0)));
        buttonPanel.add(highScoreButton);

        //----------------------------------------------------------------------------------------

        if (hasWon) {
            // display congratulatory message
            this.wonGameMessage();
        } else {
            // display lost game message
            this.lostGameMessage();
        }

        // Add components to panel
        this.add(Box.createRigidArea(new Dimension(0, 100)));
        this.add(mainLabel);
        this.add(Box.createRigidArea(new Dimension(0, 10)));
        this.add(subLabel);
        this.add(hsLabel);
        // TODO: add horizontal box layout panel here?
        this.add(Box.createRigidArea(new Dimension(0, 100)));
        // TODO: add buttons
        this.add(buttonPanel);
        this.add(new Label(playerStats.toString()));
      
        Sound.stopBackgroundMusic();
    }

    public void setGameController(GameController controller){
        gameController = controller;
    }

    private void wonGameMessage() {
        mainLabel.setText("Congratulations!");
        subLabel.setText("You have rescued Link and the world is safe from Gandora!");
        if(newHighScore) {
            hsLabel.setText("You have a new high score! Click 'High Scores' to check your standings");
        }
    }

    private void lostGameMessage() {
        mainLabel.setText("GAME OVER");
        subLabel.setText("Gandora has taken over the world!");
        if(newHighScore) {
            hsLabel.setText("You have a new high score! Click 'High Scores' to check your standings");
        }
    }
}
