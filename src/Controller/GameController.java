package Controller;

import Object.Character.Player;
import View.*;

import javax.swing.*;
import java.awt.image.BufferStrategy;
import java.util.HashMap;

public class GameController implements Runnable {
    public final int WIDTH = 1200;
    public final int HEIGHT = 800;

    private int fps = 60;
    private boolean running;

    private WelcomeScreen welcomeScreen;
    private GameScreen gameScreen;
    private EndScreen endScreen;
    private PuzzleScreen puzzleScreen;
    private HighScoreScreen highScoreScreen;

    private Level_Tutorial level_tutorial;
    private Level_1 level_1;
    private Level_2 level_2;
    private Level_Boss level_Boss;


    private GameState currentState = GameState.WELCOME;
    private volatile boolean paused = false;

    JFrame frame;

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

                update();
                render();

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

    /**
     * Handles all the drawing of objects onto the screen
     */
    public void render() {

        if (gameScreen != null && gameScreen.getLevel() != null) {
            gameScreen.repaint();
        }
    }

    /**
     * Updates all the logic of the game.
     */
    public void update() {

        if (gameScreen != null && gameScreen.getLevel() != null) {
            gameScreen.update();
        }
    }

    public void updateGameState(GameState nextState) {
        // handle switching of screens
        switch (nextState) {
            case WELCOME:
                paused = false;
                endScreen =null;
                welcomeScreen = new WelcomeScreen();
                welcomeScreen.setGameController(this);
                frame.setContentPane(welcomeScreen);
                break;
            case TUTORIAL:
                gameScreen = new GameScreen();
                level_tutorial = new Level_Tutorial();
                gameScreen.setLevel(level_tutorial, "Tutorial");
                frame.setContentPane(gameScreen);
                gameScreen.requestFocusInWindow();
                gameScreen.setGameController(this);
                break;
            case LEVEL_1:
                level_1 = new Level_1(level_tutorial.getPlayer());
                gameScreen.setLevel(level_1, "Level 1");
                break;
            case LEVEL_2:
                level_2 = new Level_2(level_1.getPlayer());
                gameScreen.setLevel(level_2, "Level 2");
                break;
            case LEVEL_BOSS:
                level_Boss = new Level_Boss(level_2.getPlayer());
                gameScreen.setLevel(level_Boss, "Boss");
                break;
            case HIGHSCORE:
                highScoreScreen = new HighScoreScreen(null);
                frame.setContentPane(highScoreScreen);
                highScoreScreen.setGameController(this);
                highScoreScreen.setPreviousState(currentState);
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

    public void updateGameState(GameState nextState, String total, Boolean success,HashMap<String, Integer> stats) {
        highScoreScreen = new HighScoreScreen(total);
        highScoreScreen.setGameController(this);
        highScoreScreen.setPreviousState(currentState);
        highScoreScreen.setEndScreen(endScreen);

        frame.setContentPane(highScoreScreen);

        frame.setVisible(true);
        currentState =nextState;
    }

    public void setEndScreen(EndScreen endScreen) {
        frame.setContentPane(endScreen);
        frame.setVisible(true);
        currentState = GameState.END;
    }

    public void runGame() {
        frame = new JFrame("The Adventures of Zelda");
        frame.setSize(WIDTH, HEIGHT);
        frame.setLocationRelativeTo(null); // centers window
        frame.setDefaultCloseOperation(JFrame.EXIT_ON_CLOSE);
//        frame.pack();
        frame.setResizable(false);

        WelcomeScreen welcomePanel = new WelcomeScreen();
        welcomePanel.setGameController(this);
        frame.setContentPane(welcomePanel);
//        frame.getContentPane().add(welcomePanel);
        frame.setVisible(true);
    }

    public void setPaused(boolean paused) {
        this.paused = paused;
        gameScreen.setPause(true);
    }

    public GameState getCurrentState() {
        return currentState;
    }
}
