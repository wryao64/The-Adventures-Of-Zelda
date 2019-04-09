package View;

import javax.swing.*;
import java.awt.*;

public class GameScreen extends JPanel implements Runnable{

    private final int WIDTH = 640;
    private final int HEIGHT = 480;

    private int fps = 60;

    private boolean running;


    public GameScreen(){

        setPreferredSize(new Dimension(WIDTH,HEIGHT));
        setFocusable(true);
        requestFocus();
    }

    /*
    Game loop so the game is only updated 60 times per second to control rate of game.
     */
    @Override
    public void run() {

        long startTime;
        long timeTakenMillis;
        long waitTime;

        //Time for one loop to run in order to maintain 60 fps
        long targetTime = 1000/fps;
        running = true;

        while (running){
            //Current time when loop is entered.
            startTime = System.nanoTime();

            /*
            ------------------------------------
            Update all the game stuffs here
            -------------------------------------
             */

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
}
