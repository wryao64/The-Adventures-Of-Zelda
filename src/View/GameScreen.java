package View;

import Controller.GameController;
import Controller.GameState;
import Controller.Level;

import javax.swing.*;
import java.awt.*;
import java.awt.event.KeyEvent;
import java.awt.event.KeyListener;


public class GameScreen extends JPanel implements KeyListener {
    GameController gameController;

    Level level;
    Boolean pause = false;

    JLabel levelLabel;

    private static final int ALPHA = 175;
    private static final Color GP_BG = new Color(0, 0, 0, ALPHA);
    private PauseScreen pauseScreen = new PauseScreen();

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

        if (c == KeyEvent.VK_P) {
            pause = !pause;

            if (pause) {
                // set the enemies to stop moving
                // set gravity to zero
                this.pausePressed();
            } else {
                // tell the enemies to start moving again
                // reset gravity
            }

            return; // prevents player movement from registering
        }

        // PLAYER MOVEMENT
        if (c == KeyEvent.VK_LEFT) {
            level.setPlayerSpeedX(-level.getMovementSpeed());
        }
        if (c == KeyEvent.VK_RIGHT) {
            level.setPlayerSpeedX(level.getMovementSpeed());
        }
        if (c == KeyEvent.VK_UP) {
            level.setPlayerJump();
            level.playercanJump(false);
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

    public void setGameController(GameController controller){
        gameController = controller;
    }

    public void pausePressed() {
        // create our glass pane
        JPanel glassPane = new JPanel() {
            @Override
            protected void paintComponent(Graphics g) {
                // magic to make it dark without side-effects
                g.setColor(getBackground());
                g.fillRect(0, 0, getWidth(), getHeight());
                super.paintComponent(g);
            }
        };
        // more magic below
        glassPane.setOpaque(false);
        glassPane.setBackground(GP_BG);

        // get the rootpane container, here the JFrame, that holds the JButton
        RootPaneContainer win = (RootPaneContainer) SwingUtilities.getWindowAncestor(this);
        win.setGlassPane(glassPane);  // set the glass pane
        glassPane.setVisible(true);  // and show the glass pane

        // create a *modal* JDialog
        JDialog dialog = new JDialog((Window)win, "", Dialog.ModalityType.APPLICATION_MODAL);
        dialog.getContentPane().add(pauseScreen);  // add its JPanel to it
        dialog.setUndecorated(true); // give it no borders (if desired)
        dialog.pack(); // size it
        dialog.setLocationRelativeTo((Window) win); // ** Center it over the JFrame **
        dialog.setVisible(true);  // display it, pausing the GUI below it

        // at this point the dialog is no longer visible, so get rid of glass pane
        glassPane.setVisible(false);

    }
}
