package View;

import Controller.GameController;
import Controller.GameState;
import Controller.Level;
import Controller.Sound;

import javax.swing.*;
import java.awt.*;
import java.awt.event.KeyEvent;
import java.awt.event.KeyListener;
import java.util.TimerTask;
import java.util.Timer;
import java.util.concurrent.CompletableFuture;


public class GameScreen extends JPanel implements KeyListener {
    private GameController gameController;
    private JPanel glassPane;
    private RootPaneContainer window;

    private String timeString = "00:00";
    private int timeCount;

    private static final String fontFamily = "Arial";
    private static final Color glassPaneColor = new Color(0, 0, 0, 175);
    private static final Color backgroundColor = new Color(123, 63, 0);
    private static final Font regularFont = new Font(fontFamily, Font.PLAIN, 20);

    private static final String CHEST_OPEN_SOUND = Sound.SOUND_LOCATION + "chest_open.wav";

    Level level;
    Boolean paused = false;
    JLabel levelLabel;

    public GameScreen() {
        this.setLayout(new BorderLayout());
        this.setPreferredSize(new Dimension(WIDTH, HEIGHT));
        this.setFocusable(true);
        this.addKeyListener(this);

        JPanel topBar = new JPanel();
        topBar.setPreferredSize(new Dimension(WIDTH, 50));
        topBar.setBackground(backgroundColor);
        topBar.setLayout(new BoxLayout(topBar, BoxLayout.LINE_AXIS));
        topBar.setBorder(BorderFactory.createEmptyBorder(0, 20, 0, 20));

        // Level label
        levelLabel = new JLabel("");
        levelLabel.setFont(regularFont);
        levelLabel.setForeground(Color.ORANGE);

        this.add(topBar, BorderLayout.NORTH);

        topBar.add(levelLabel);
        topBar.add(Box.createRigidArea(new Dimension(700, 0)));

        Sound.playBackgroundMusic();
        this.timer();
    }

    public void setGameController(GameController controller) {
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

    public Level getLevel() {
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
        g.setColor(Color.WHITE);
        g.setFont(new Font("Helvetica", Font.BOLD, 24));
        g.drawString("Time: " + timeString, 535, 130);
    }

    /**
     * Updates all the logic of the game.
     */
    public void update() {
        level.updateLevel();
    }

    /**
     * KeyListeners to handle user key input. Sets the speed of the player.
     */
    @Override
    public void keyTyped(KeyEvent e) {
    }

    @Override
    public void keyPressed(KeyEvent e) {
        int c = e.getKeyCode();

        // PAUSE GAME
        if (c == KeyEvent.VK_P) {
            gameController.setPaused(true);
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
            if (level.getPuzzleStatus()) {
                if (gameController.getCurrentState() == GameState.LEVEL_BOSS) {
                    gameController.setPaused(true);
                    gameController.updateGameState(GameState.END, true, level.getPlayer().getFinalStats());
                } else {
                    level.getPlayer().collectOrb();
                    gameController.setPaused(true);
                    Sound.playSound(CHEST_OPEN_SOUND);
                    window = (RootPaneContainer) SwingUtilities.getWindowAncestor(this);
                    new PuzzleScreen((Window) window, gameController, this);
                }
            }
        }

        // SCREEN CHANGE
        // Shortcut: Skips to boss level
        if (c == KeyEvent.VK_PAGE_DOWN) {
            gameController.setPlayer(level.getPlayer().skipToEnd());
            gameController.updateGameState(GameState.LEVEL_BOSS);
        }

        // Shortcut: Exit game
        if (c == KeyEvent.VK_ESCAPE) {
            gameController.setPaused(true);
            this.exitPressed();
        }
    }

    @Override
    public void keyReleased(KeyEvent e) {
        int c = e.getKeyCode();

        if (c == KeyEvent.VK_LEFT || c == KeyEvent.VK_RIGHT) {
            level.setSideKeyPressed(false);
        }
        if (c == KeyEvent.VK_UP) {
            level.setJumpKeyPressed(false);
        }
        level.setPlayerSpeedX(0);
    }

    public void setSuccess(boolean success) {
        gameController.updateGameState(GameState.END, success, level.getPlayer().getFinalStats());
    }

    public void timer() {
        Timer timer = new Timer("Timer");

        TimerTask repeatedTask = new TimerTask() {
            public void run() {
                timeString = String.format("%02d:%02d",
                        (timeCount % 3600) / 60,
                        timeCount % 60);
                if (!paused) {
                    timeCount++;
                } else {
                    timer.cancel();
                }
            }
        };

        long delay = 1000L;
        long period = 1000L;
        timer.scheduleAtFixedRate(repeatedTask, delay, period);
    }

    public void setPause(Boolean paused) {
        this.paused = paused;
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
        new PauseScreen((Window) window, gameController, this);
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
}
