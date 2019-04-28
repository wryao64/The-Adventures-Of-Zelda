package View;

import Controller.GameController;
import Controller.GameState;
import Controller.ScoreManager;

import javax.swing.*;
import java.awt.*;
import java.util.ArrayList;

public class HighScoreScreen extends JPanel {

    private EndScreen endScreen;
    private ScoreManager scoreManager;
    private GameController gameController;
    private ArrayList<String> topScores;
    private GameState previousGameState;
    private String scoreToHighlight;

    private JPanel centrePanel;
    private JLabel title;

    private static final Color backgroundColor = new Color(123, 63, 0);
    private static final Font mainFont = new Font("Courier", Font.PLAIN, 50);
    private static final Font regularFont = new Font("Courier", Font.PLAIN, 30);

    public HighScoreScreen(String scoreToHighlight) {
        scoreManager = new ScoreManager();
        topScores = scoreManager.getTopScores();
        this.scoreToHighlight = scoreToHighlight;

        this.setLayout(new BorderLayout());
        this.setPreferredSize(new Dimension(WIDTH, HEIGHT));
        this.setBackground(backgroundColor);
        this.setBorder(BorderFactory.createEmptyBorder(50, 20, 20, 50));

        JPanel panel = new JPanel();
        panel.setLayout(new FlowLayout());

        title = new JLabel("HighScores");
        title.setForeground(Color.ORANGE);
        title.setBorder(BorderFactory.createEmptyBorder(0, 0, 20, 0));
        title.setHorizontalAlignment(SwingConstants.CENTER);
        title.setFont(mainFont);

        JButton button = new JButton("BACK");
        button.addActionListener(e ->{
            if(previousGameState == GameState.END){
                gameController.setEndScreen(endScreen);
            }else {
                gameController.updateGameState(previousGameState);
            }

                }
        );

        panel.add(button);
        panel.add(title);
        panel.setBackground(backgroundColor);

        centrePanel = setUpCentrePanel();
        centrePanel.setBackground(backgroundColor);
        centrePanel.setAlignmentX(CENTER_ALIGNMENT);

        this.add(panel, BorderLayout.NORTH);
        this.add(centrePanel,BorderLayout.CENTER);
    }


    public JPanel setUpCentrePanel() {
        JPanel panel = new JPanel();
        panel.setBackground(backgroundColor);
        panel.setLayout(new GridLayout(0,2));
        panel.setAlignmentX(CENTER_ALIGNMENT);

        if (topScores.size() == 0) {
            JLabel noScoresLabel = new JLabel("Looks like you don't have any saved scores!");
            noScoresLabel.setForeground(Color.ORANGE);
            noScoresLabel.setFont(regularFont);
            panel.add(noScoresLabel);
        }else{
            JLabel rankLabel = new JLabel("Rank");
            rankLabel.setForeground(Color.BLACK);
            rankLabel.setFont(regularFont);
            panel.add(rankLabel);

            JLabel scoreLabel = new JLabel("Score");
            scoreLabel.setForeground(Color.BLACK);
            scoreLabel.setFont(regularFont);
            panel.add(scoreLabel);

            for(int i = 0; i < 10; i++ ){
                if(i <topScores.size()) {
                    String s = topScores.get(i);

                    String scoreString0 = i+1 + ".";
                    JLabel scoreLabel0 = new JLabel(scoreString0);
                    scoreLabel0.setFont(regularFont);

                    String scoreString1 = s;
                    JLabel scoreLabel1 = new JLabel(scoreString1);
                    scoreLabel1.setFont(regularFont);

                    if(s.equals(scoreToHighlight)) {
                        scoreLabel0.setForeground(Color.white);
                        scoreLabel1.setForeground(Color.white);
                        scoreToHighlight = null;
                    }else{
                        scoreLabel0.setForeground(Color.orange);
                        scoreLabel1.setForeground(Color.orange);
                    }

                    panel.add(scoreLabel0);
                    panel.add(scoreLabel1);
                }else{
                    panel.add(new Label(""));
                    panel.add(new Label(""));

                }
            }
        }
        JPanel wrapperPanel = new JPanel(new GridBagLayout());
        wrapperPanel.add(panel);
        return panel;

    }

    public void setGameController(GameController gm) {
        gameController = gm;
    }

    public void setPreviousState( GameState gameState) {
        previousGameState = gameState;
    }

    public void setEndScreen(EndScreen endscreen) { this.endScreen = endscreen; }


}
