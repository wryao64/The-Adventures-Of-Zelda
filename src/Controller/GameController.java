package Controller;

import Item.Puzzle;
import View.*;

import javax.swing.*;

public class GameController implements Runnable {
    public final int WIDTH = 1200;
    public final int HEIGHT = 800;

    private int fps = 60;
    private boolean running;

    private WelcomeScreen welcomeScreen;
    private GameScreen gameScreen;
    private PauseScreen pauseScreen;
    private PuzzleScreen puzzleScreen;
    private EndScreen endScreen;

    GameState currentState;

    //Main frame for the game.
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

        while (running){
            //Current time when loop is entered.
            startTime = System.nanoTime();

            if(currentState == GameState.TUTORIAL){
                update();
                render();
            }
            //The time taken to do all the updates (in millis).
            timeTakenMillis = (System.nanoTime()-startTime)/1000000;

            //Extra time left over that loop needs to wait to get desired fps.
            waitTime = targetTime - timeTakenMillis;

            //Sleep the thread for the extra time.
            try {
                Thread.sleep(waitTime);
            } catch (InterruptedException e) {
                e.printStackTrace();
            }
        }
    }

    public void runGame() {
        frame = new JFrame("The Legend of Zelda");
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

    /**
     * Render + update only when in the game screen? Because all the other screens are event driven?
     */
    public void render(){
        gameScreen.render();
    }

    public void update(){
        gameScreen.update();
    }

    public void gameStateUpdate(GameState nextState) {
        // handle switching of screens
        switch(nextState) {
            case WELCOME:
                welcomeScreen = new WelcomeScreen();
                frame.setContentPane(welcomeScreen);
                break;
            case TUTORIAL:
                gameScreen = new GameScreen();
                frame.setContentPane(gameScreen);
                break;
            case LEVEL_1:
                //set inner panel
                break;
            case LEVEL_2:
                //set inner panel
                break;
            case LEVEL_BOSS:
                //set inner panel
                break;
            case PAUSE:
                frame.setContentPane(new PauseScreen());
                break;
            case END:
                frame.setContentPane(new EndScreen());
                break;
            case PUZZLE:
                //pop-up window?
                break;
        }
        frame.setVisible(true);
        currentState = nextState;
    }


}
