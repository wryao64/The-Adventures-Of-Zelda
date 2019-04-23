package View;

import Controller.GameController;
import Controller.GameState;
import Controller.ScoreManager;

import javax.swing.*;
import java.awt.*;
import java.util.ArrayList;

public class HighScoreScreen extends JPanel {

    private ScoreManager scoreManager;
    private GameController gameController;
    private ArrayList<String[]> topScores;
    private GameState previousGameState;
    private GameState gameState = GameState.HIGHSCORE;

    private JPanel centrePanel;
    private JLabel title;

    public HighScoreScreen() {
        scoreManager = new ScoreManager();
        topScores = scoreManager.getTopScores();

        this.setLayout(new BorderLayout());
        this.setPreferredSize(new Dimension(WIDTH, HEIGHT));
        this.setBackground(new Color(255, 234, 206));
        this.setBorder(BorderFactory.createEmptyBorder(20, 20, 20, 20));

        JPanel panel = new JPanel();
        panel.setLayout(new FlowLayout());

        title = new JLabel("HighScores");
        title.setBorder(BorderFactory.createEmptyBorder(0, 0, 20, 0));
        title.setHorizontalAlignment(SwingConstants.CENTER);
        title.setFont(new Font(title.getFont().getName(), title.getFont().getStyle(), 50));

        JButton button = new JButton("BACK");
        button.addActionListener(e ->
                gameController.updateGameState(previousGameState)
        );

        panel.add(button);
        panel.add(title);
        this.add(panel, BorderLayout.NORTH);

        centrePanel = setUpCentrePanel();
        centrePanel.setBorder(BorderFactory.createEmptyBorder(20, 100, 20, 100));
        this.add(centrePanel,BorderLayout.CENTER);
        centrePanel.setAlignmentX(CENTER_ALIGNMENT);

    }

    public JPanel setUpCentrePanel() {
        JPanel panel = new JPanel();
        panel.setLayout(new GridLayout(10,3));
        panel.setAlignmentX(CENTER_ALIGNMENT);

        if (topScores.size() == 0) {
            JLabel noScoresLabel = new JLabel("Looks like you don't have any saved scores!");
            noScoresLabel.setFont(new Font(title.getFont().getName(), title.getFont().getStyle(), 32));
            panel.add(noScoresLabel);
        }else{
            for(int i = 0; i < 10; i++ ){
                if(i <topScores.size()) {
                    String[] s = topScores.get(i);
                    //String scoreString = String.format(stringFormat,(i+1)+"." ,s[1],s[0]);
                    String scoreString0 = i+1 + ".";
                    String scoreString1 = s[0];
                    String scoreString2 = s[1];

                    JLabel scoreLabel0 = new JLabel(scoreString0);
                    JLabel scoreLabel1 = new JLabel(scoreString1);
                    JLabel scoreLabel2 = new JLabel(scoreString2);

                    scoreLabel2.setBorder(BorderFactory.createEmptyBorder(0, 80, 0, 0));

                    scoreLabel0.setFont(new Font(title.getFont().getName(), title.getFont().getStyle(), 32));
                    scoreLabel1.setFont(new Font(title.getFont().getName(), title.getFont().getStyle(), 32));
                    scoreLabel2.setFont(new Font(title.getFont().getName(), title.getFont().getStyle(), 32));

                    panel.add(scoreLabel0);
                    panel.add(scoreLabel1);
                    panel.add(scoreLabel2);
                }else{
                    panel.add(new Label(""));
                    panel.add(new Label(""));
                    panel.add(new Label(""));
                }
            }
        }
        JPanel wrapperPanel = new JPanel(new GridBagLayout());
        wrapperPanel.add(panel);
        return wrapperPanel;

    }

    public void setGameController(GameController gm) {
        gameController = gm;
    }

    public void setPreviousState( GameState gameState) {
        previousGameState = gameState;
    }


}
