package View;

import Controller.GameController;
import Controller.GameState;
import Controller.Level;

import javax.swing.*;
import java.awt.*;
import java.awt.event.KeyEvent;
import java.awt.event.KeyListener;


public class GameScreen extends JPanel implements KeyListener {
    private GameController gameController;
    private PauseScreen pauseScreen;

    Color glassPaneColor = new Color(0, 0, 0, 175);

    Level level;
    Boolean pause = false;

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

            System.out.println("Width: " + WIDTH + " Height: " + HEIGHT);
    }

    public void setGameController(GameController controller){
        gameController = controller;
    }

    public void setLevel(Level level, String levelName) {
        this.level = level;
        levelLabel.setText("Level: " + levelName);
    }

    public Level getLevel(){
        return level;
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

        // pause game
        if (c == KeyEvent.VK_P) {
            gameController.pauseGame(true);
            this.pausePressed();
            return; // prevents player movement from registering
        }

        // PLAYER MOVEMENT
        if (c == KeyEvent.VK_LEFT) {
            level.setPlayerSpeedX(-level.getMovementSpeed());
            level.setPlayerDirection(-1);
        }
        if (c == KeyEvent.VK_RIGHT) {
            level.setPlayerSpeedX(level.getMovementSpeed());
            level.setPlayerDirection(1);
        }
        if (c == KeyEvent.VK_UP) {
            level.setPlayerJump();
            level.playercanJump(false);
        }
        if (c == KeyEvent.VK_SPACE) {
            level.setPlayerShoot();
        }

        // SCREEN CHANGE
        // Shortcut: skips to boss level
        if (c == KeyEvent.VK_B) {
            gameController.updateGameState(GameState.LEVEL_BOSS);
        }
        // FOR TESTING PURPOSES: skips to end screen
        if (c == KeyEvent.VK_E) {
            gameController.updateGameState(GameState.END);
        }
    }

    @Override
    public void keyReleased(KeyEvent e) {
        level.setPlayerSpeedX(0);
    }

    /**
     * Creates pause dialog
     */
    public void pausePressed() {
        JPanel glassPane = new JPanel() {
            @Override
            protected void paintComponent(Graphics g) {
                g.setColor(getBackground());
                g.fillRect(0, 0, getWidth(), getHeight());
                super.paintComponent(g);
            }
        };
        glassPane.setOpaque(false);
        glassPane.setBackground(glassPaneColor);

        // sets glass pane to give dimmed effect
        RootPaneContainer window = (RootPaneContainer) SwingUtilities.getWindowAncestor(this);
        window.setGlassPane(glassPane);
        glassPane.setVisible(true);

        // create modal JDialog for pause screen
        pauseScreen = new PauseScreen((Window) window);

        //
        glassPane.setVisible(false);
        gameController.pauseGame(false);
    }
}
