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

    private static final Color backgroundColor = new Color(123, 63, 0);
    private static final Font mainFont = new Font("Courier", Font.PLAIN, 50);
    private static final Font regularFont = new Font("Courier", Font.PLAIN, 30);

    public EndScreen(boolean success, HashMap<String,Integer> stats) {
        hasWon = success;
        playerStats = stats;

        if(sm.newScore(playerStats.get("Total: ").toString())){
            newHighScore = true;
        }

        this.setLayout(new BoxLayout(this, BoxLayout.PAGE_AXIS));
        this.setPreferredSize(new Dimension(WIDTH, HEIGHT));
        this.setBackground(backgroundColor);
        this.setBorder(BorderFactory.createEmptyBorder(20, 20, 20, 20));

        // Main heading
        mainLabel = new JLabel("");
        mainLabel.setFont(mainFont);
        mainLabel.setForeground(Color.ORANGE);
        mainLabel.setAlignmentX(Component.CENTER_ALIGNMENT);

        // Sub heading
        subLabel = new JLabel("");
        subLabel.setFont(regularFont);
        subLabel.setForeground(Color.ORANGE);
        subLabel.setAlignmentX(Component.CENTER_ALIGNMENT);

        // High score label
        hsLabel = new JLabel("");
        hsLabel.setFont(regularFont);
        hsLabel.setForeground(Color.ORANGE);
        hsLabel.setAlignmentX(Component.CENTER_ALIGNMENT);

        // Player stats labels
        JPanel statsPanel = this.processPlayerStats();

        // Buttons panel-------------------------------------------------------------------------
        JPanel buttonPanel = new JPanel();
        buttonPanel.setBackground(backgroundColor);
        buttonPanel.setLayout(new BoxLayout(buttonPanel, BoxLayout.LINE_AXIS));

        // Home button
        JButton homeButton = new JButton("HOME");
        homeButton.addActionListener(e ->
                gameController.updateGameState(GameState.WELCOME)
        );

        // High score button
        JButton highScoreButton = new JButton("HIGH SCORES");
        highScoreButton.addActionListener(e -> {
            if (newHighScore) {
                gameController.updateGameState(GameState.HIGHSCORE,playerStats.get("Total: ").toString());
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
        this.add(Box.createRigidArea(new Dimension(0, 10)));
        this.add(hsLabel);
        this.add(Box.createRigidArea(new Dimension(0, 100)));
        this.add(statsPanel);
        this.add(Box.createRigidArea(new Dimension(0, 100)));
        this.add(buttonPanel);

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

    private JPanel processPlayerStats() {
        JPanel stats = new JPanel();
        stats.setBackground(backgroundColor);
        stats.setLayout(new BoxLayout(stats, BoxLayout.PAGE_AXIS));

        for (String s : playerStats.keySet()) {
            JLabel label = new JLabel(s + " " + playerStats.get(s));
            label.setFont(regularFont);
            label.setForeground(Color.ORANGE);
            label.setAlignmentX(Component.CENTER_ALIGNMENT);

            stats.add(label);
        }

        return stats;
    }
}
