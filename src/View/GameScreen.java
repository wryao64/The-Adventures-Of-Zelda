package View;

import Controller.GameController;
import Controller.GameState;
import Controller.Level;
import Controller.Sound;

import javax.swing.*;
import java.awt.*;
import java.awt.event.KeyEvent;
import java.awt.event.KeyListener;
import java.util.concurrent.CompletableFuture;


public class GameScreen extends JPanel implements KeyListener {
    private GameController gameController;
    private JPanel glassPane;
    private RootPaneContainer window;

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

        Sound.playBackgroundMusic();
    }

    public void setGameController(GameController controller){
        gameController = controller;
    }

    public void setLevel(Level level, String levelName) {
        CompletableFuture<Void> future = CompletableFuture.runAsync(() -> {
            try {
                this.level = level;
                levelLabel.setText("Level: " + levelName);
                this.level.setGameScreen(this);
            } catch (Exception e) {
                e.printStackTrace();
            }
        });
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
            level.setSideKeyPressed(true);
        }
        if (c == KeyEvent.VK_RIGHT) {
            level.setPlayerSpeedX(level.getMovementSpeed());
            level.setPlayerDirection(1);
            level.setSideKeyPressed(true);
        }
        if (c == KeyEvent.VK_UP) {
            level.setPlayerJump();
            level.setJumpKeyPressed(true);
            level.playerCanJump(false);
        }

        if (c == KeyEvent.VK_SPACE) {
            level.setPlayerShoot();
        }

        if (c == KeyEvent.VK_O) {
            if(level.getPuzzleStatus()) {
                if (gameController.getCurrentState() == GameState.LEVEL_BOSS){
                    gameController.setPaused(true);
                    gameController.updateGameState(GameState.END,true,level.getPlayer().getFinalStats());
//                    System.out.println("here");
                }else {
                    level.getPlayer().collectOrb();
                    gameController.setPaused(true);
                    window = (RootPaneContainer) SwingUtilities.getWindowAncestor(this);
                    new PuzzleScreen((Window) window, gameController);
                }
            }
        }

        // SCREEN CHANGE
        // Shortcut: skips to boss level
        if (c == KeyEvent.VK_PAGE_DOWN) {
            // TODO: equip all orbs
            gameController.updateGameState(GameState.LEVEL_BOSS);
        }
        // FOR TESTING PURPOSES: skips to end screen
        if (c == KeyEvent.VK_E) {
            gameController.updateGameState(GameState.END);
        }
        // FOR TESTING PURPOSES: skips to next level
        if (c == KeyEvent.VK_N) {
            GameState state = gameController.getCurrentState();
            switch (state) {
                case TUTORIAL:
                    gameController.updateGameState(GameState.LEVEL_1);
                    break;
                case LEVEL_1:
                    gameController.updateGameState(GameState.LEVEL_2);
                    break;
                case LEVEL_2:
                    gameController.updateGameState(GameState.LEVEL_BOSS);
                    break;
                case LEVEL_BOSS:
                    gameController.updateGameState(GameState.END);
                    break;
            }
        }
        // Shortcut: Exit game
        if (c == KeyEvent.VK_ESCAPE) {
            gameController.pauseGame(true);
            this.exitPressed();
        }

        // FOR TESTING PURPOSES: play sound
        if (c == KeyEvent.VK_S) {
            String sound = Sound.SOUND_LOCATION + "player_hit.wav";
            Sound.playSound(sound);
        }
    }

    @Override
    public void keyReleased(KeyEvent e) {
        int c = e.getKeyCode();

        if (c == KeyEvent.VK_LEFT ||c == KeyEvent.VK_RIGHT) {
            level.setSideKeyPressed(false);
        }if (c == KeyEvent.VK_UP) {
            level.setJumpKeyPressed(false);
        }
        level.setPlayerSpeedX(0);
    }

    /**
     * Pause game
     */
    private void pauseGame() {
        //Set the players movement to zero (prevents residual movement once resumed)
        level.setPlayerSpeedX(0);
        level.setPayerSpeedY(0);

        glassPane = new JPanel() {
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

        window.setGlassPane(glassPane);
        glassPane.setVisible(true);
    }

    /**
     * Creates pause dialog
     */
    private void pausePressed() {
        // create modal JDialog for pause screen
        window = (RootPaneContainer) SwingUtilities.getWindowAncestor(this);
        this.pauseGame();
        new PauseScreen((Window) window, gameController);
        glassPane.setVisible(false);
    }

    /**
     * Creates exit dialog
     */
    private void exitPressed() {
        window = (RootPaneContainer) SwingUtilities.getWindowAncestor(this);
        this.pauseGame();
        new ExitDialog((Window) window, gameController);
        glassPane.setVisible(false);
    }

    public void setSuccess(boolean success) {
        gameController.updateGameState(GameState.END,success,level.getPlayer().getFinalStats());
    }

}
