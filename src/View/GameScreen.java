package View;

import Controller.Level;

import javax.swing.*;
import java.awt.*;
import java.awt.event.KeyEvent;
import java.awt.event.KeyListener;


public class GameScreen extends JPanel implements KeyListener {
    Level level;

    JLabel levelLabel;

    public GameScreen() {
        this.setLayout(new BorderLayout());
        this.setPreferredSize(new Dimension(WIDTH,HEIGHT));
        this.setFocusable(true);
        this.addKeyListener(this);

        JPanel topBar = new JPanel();
        topBar.setPreferredSize(new Dimension(WIDTH, 50));
        topBar.setBackground(new Color(186, 122, 50));
        topBar.setLayout(new BoxLayout(topBar, BoxLayout.LINE_AXIS));
        topBar.setBorder(BorderFactory.createEmptyBorder(0, 20, 0, 20));

        // Level label
        levelLabel = new JLabel("");
        levelLabel.setFont(new Font(levelLabel.getFont().getName(), levelLabel.getFont().getStyle(), 20));

        this.add(topBar, BorderLayout.NORTH);

        topBar.add(levelLabel);
        topBar.add(Box.createRigidArea(new Dimension(700, 0)));
        // TODO: ADD LIVES HERE

    }

    public void setLevel(Level level, String levelName) {
        this.level = level;
        levelLabel.setText("Level: " + levelName);
    }


    /**
     * Paint method called by repaint to render the level. Called from within the game loop.
     */
    @Override
    protected void paintComponent(Graphics g) {
        super.paintComponent(g);
        Graphics2D g2d = (Graphics2D) g;
        level.paintLevel(g2d);
    }

    /**
     * Updates all the logic of the game.
     */
    public void update(){
        level.updateLevel();
    }

    /**
     * KeyListeners to handle user key input. Sets the speed of the player.
     */
    @Override
    public void keyTyped(KeyEvent e) { }

    @Override
    public void keyPressed(KeyEvent e) {
        int c = e.getKeyCode();
        if(c == KeyEvent.VK_LEFT){
            level.setPlayerSpeedX(-level.getMovementSpeed());
        }if(c == KeyEvent.VK_RIGHT){
            level.setPlayerSpeedX(level.getMovementSpeed());
        }if (c == KeyEvent.VK_UP){
            level.setPlayerJump();
            level.playercanJump(false);
        }
    }

    @Override
    public void keyReleased(KeyEvent e) {
        level.setPlayerSpeedX(0);
        level.playercanJump(true);
    }
}
