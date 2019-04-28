package Controller;

import Object.Character.Player;
import View.*;

import javax.swing.*;
import java.awt.*;
import java.util.HashMap;

public class GameController implements Runnable {
    public final int WIDTH = 1200;
    public final int HEIGHT = 800;

    private int fps = 60;
    private boolean running;

    private boolean narration = false;
    private volatile boolean paused = false;
    private GameState currentState = GameState.WELCOME;

    private WelcomeScreen welcomeScreen;
    private GameScreen gameScreen;
    private EndScreen endScreen;
    private HighScoreScreen highScoreScreen;

    private Level_Tutorial level_tutorial;
    private Level_1 level_1;
    private Level_2 level_2;
    private Level_Boss level_Boss;

    private Player player;

    JFrame frame;
    private RootPaneContainer window;

    @Override
    public void run() {
        long startTime;
        long timeTakenMillis;
        long waitTime;

        //Time for one loop to run in order to maintain 60 fps
        long targetTime = 1000 / fps;
        running = true;

        runGame();

        while (running) {
            if (!paused) {
                //Current time when loop is entered.
                startTime = System.nanoTime();

                this.update();
                this.render();

                //The time taken to do all the updates (in millis).
                timeTakenMillis = (System.nanoTime() - startTime) / 1000000;

                //Extra time left over that loop needs to wait to get desired fps.
                waitTime = targetTime - timeTakenMillis;

                //Sleep the thread for the extra time if there is extra time
                if (waitTime > 4) {
                    try {
                        Thread.sleep(waitTime);
                    } catch (InterruptedException e) {
                        e.printStackTrace();
                    }
                }
            }
        }
    }

    public void runGame() {
        frame = new JFrame("The Adventures of Zelda");
        frame.setSize(WIDTH, HEIGHT);
        frame.setLocationRelativeTo(null); // centers window
        frame.setDefaultCloseOperation(JFrame.EXIT_ON_CLOSE);
        frame.setResizable(false);

        WelcomeScreen welcomePanel = new WelcomeScreen();
        welcomePanel.setGameController(this);
        frame.setContentPane(welcomePanel);
        frame.setVisible(true);
    }

    public void timer() {
        gameScreen.timer();
    }

    public GameState getCurrentState() {
        return currentState;
    }

    public void finishNarration() {
        narration = true;

    }

    public void setPaused(boolean paused) {
        this.paused = paused;
        gameScreen.setPause(paused);
    }

    public void setPlayer(Player player){ this.player = player; }

    public void setEndScreen(EndScreen endScreen) {
        frame.setContentPane(endScreen);
        frame.setVisible(true);
        currentState = GameState.END;
    }

    public void updateGameState(GameState nextState) {
        // handle switching of screens
        switch (nextState) {
            case WELCOME:
                this.resetGame();
                welcomeScreen = new WelcomeScreen();
                welcomeScreen.setGameController(this);
                frame.setContentPane(welcomeScreen);
                break;
            case TUTORIAL:
                gameScreen = new GameScreen();
                player = new Player(45,55,70,600);
                level_tutorial = new Level_Tutorial(player);
                gameScreen.setLevel(level_tutorial, "Tutorial");
                frame.setContentPane(gameScreen);
                gameScreen.requestFocusInWindow();
                gameScreen.setGameController(this);
                break;
            case LEVEL_1:
                level_1 = new Level_1(player);
                gameScreen.setLevel(level_1, "1");
                break;
            case LEVEL_2:
                level_2 = new Level_2(player);
                gameScreen.setLevel(level_2, "2");
                break;
            case LEVEL_BOSS:
                level_Boss = new Level_Boss(player);
                gameScreen.setLevel(level_Boss, "Boss");
                break;
            case HIGHSCORE:
                highScoreScreen = new HighScoreScreen(null);
                frame.setContentPane(highScoreScreen);
                highScoreScreen.setGameController(this);
                highScoreScreen.setPreviousState(currentState);
                highScoreScreen.setEndScreen(endScreen);
                break;
        }

        frame.setVisible(true);
        currentState = nextState;
    }

    public void updateGameState(GameState nextState, Boolean success, HashMap<String, Integer> stats) {
        if (nextState == GameState.END) {
            if (endScreen == null) {
                endScreen = new EndScreen(success, stats);
            }

            endScreen.setGameController(this);
            paused = true;
            frame.setContentPane(endScreen);
            endScreen.setGameController(this);
        }

        frame.setVisible(true);
        currentState = nextState;
    }

    public void updateGameState(GameState nextState, String total) {
        highScoreScreen = new HighScoreScreen(total);
        highScoreScreen.setGameController(this);
        highScoreScreen.setPreviousState(currentState);
        highScoreScreen.setEndScreen(endScreen);

        frame.setContentPane(highScoreScreen);

        frame.setVisible(true);
        currentState =nextState;
    }

    /**
     * Handles all the drawing of objects onto the screen
     */
    private void render() {
        if (gameScreen != null && gameScreen.getLevel() != null) {
            gameScreen.repaint();

            if (!narration && this.getCurrentState() == GameState.TUTORIAL) {
                gameScreen.setPause(true);

                window = (RootPaneContainer) SwingUtilities.getWindowAncestor(gameScreen);
                new NarrationDialog((Window) window, this);
            }
        }

    }

    /**
     * Updates all the logic of the game.
     */
    private void update() {
        if (gameScreen != null && gameScreen.getLevel() != null) {
            gameScreen.update();
        }
    }

    /**
     * Sets all variables to their initial states.
     */
    private void resetGame() {
        Sound.stopBackgroundMusic();
        paused = false;
        welcomeScreen = null;
        gameScreen = null;
        endScreen = null;
        highScoreScreen = null;
        narration = false;
    }
}