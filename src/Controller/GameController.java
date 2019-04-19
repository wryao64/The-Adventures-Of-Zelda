package Controller;

import View.*;

import javax.swing.*;
import java.awt.image.BufferStrategy;

public class GameController implements Runnable {
    public final int WIDTH = 1200;
    public final int HEIGHT = 800;

    private int fps = 60;
    private boolean running;

    private WelcomeScreen welcomeScreen;
    private GameScreen gameScreen;
    private EndScreen endScreen;
    private GameState currentState;
    private boolean paused = false;

    JFrame frame;

    @Override
    public void run() {
        long startTime;
        long timeTakenMillis;
        long waitTime;

        //Time for one loop to run in order to maintain 60 fps
        long targetTime = 1000/fps;
        running = true;

        runGame();

        while (running) {
                if(!paused) {
                    //Current time when loop is entered.
                    startTime = System.nanoTime();

                    //Bad temporary fix to the synch/level null pointer problem.
                    if(currentState == GameState.TUTORIAL) {
                        update();
                        render();
                    }
                  
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
    public void render(){

        if(gameScreen != null && gameScreen.getLevel() != null) {
            gameScreen.repaint();
        }
    }

    /**
     * Updates all the logic of the game.
     */
    public void update(){

        if(gameScreen != null && gameScreen.getLevel() != null) {
            gameScreen.update();
        }
    }

    public void pauseGame(boolean paused){
        this.paused = paused;
    }

    public void updateGameState(GameState nextState) {
        // handle switching of screens
        switch(nextState) {
            case WELCOME:
                welcomeScreen =  new WelcomeScreen();
                welcomeScreen.setGameController(this);
                frame.setContentPane(welcomeScreen);
                break;
            case TUTORIAL:
                gameScreen = new GameScreen();

                gameScreen.setLevel(new Level_Tutorial(), "Tutorial");
                frame.setContentPane(gameScreen);
                gameScreen.requestFocusInWindow();

                gameScreen.setGameController(this);
                break;
            case LEVEL_1:
                gameScreen.setLevel(new Level_1(), "Level 1");
                break;
            case LEVEL_2:
                gameScreen.setLevel(new Level_2(), "Level 2");
                break;
            case LEVEL_BOSS:
                gameScreen.setLevel(new Level_Boss(), "Boss");
                break;
//            case PAUSE:
//                frame.setContentPane(new PauseScreen());
//                break;
            case END:
                endScreen = new EndScreen(true);
                frame.setContentPane(endScreen);
                endScreen.setGameController(this);
                break;
            case PUZZLE:
                //pop-up window?
                break;
        }
        frame.setVisible(true);
        currentState = nextState;
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
}
