package View;

import javax.swing.*;
import java.awt.*;

public class EndScreen extends JPanel {
    private boolean hasWon;

    JLabel mainLabel;
    JLabel subLabel;

    public EndScreen(boolean success) {
        hasWon = success;

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

        // Buttons panel-------------------------------------------------------------------------
        JPanel buttonPanel = new JPanel();
        buttonPanel.setLayout(new BoxLayout(buttonPanel, BoxLayout.LINE_AXIS));
        buttonPanel.setBackground(new Color(255, 255, 255));

        JButton welcomeButton = new JButton("HOME");
        JButton highScoreButton = new JButton("HIGH SCORES");

        buttonPanel.add(welcomeButton);
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
        // TODO: add horizontal box layout panel here?
        this.add(Box.createRigidArea(new Dimension(0, 100)));
        // TODO: add buttons
        this.add(buttonPanel);

    }

    private void wonGameMessage() {
        mainLabel.setText("Congratulations!");
        subLabel.setText("You have rescued Link and the world is safe from Gandora!");
    }

    private void lostGameMessage() {
        mainLabel.setText("GAME OVER");
        subLabel.setText("Gandora has taken over the world!");
    }
}
