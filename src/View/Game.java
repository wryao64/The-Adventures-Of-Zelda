package View;

import javax.swing.*;
import java.awt.*;

public class Game implements Runnable {
    public final int WIDTH = 1200;
    public final int HEIGHT = 800;

    JFrame frame;

    public Game() {
        frame = new JFrame("The Legend of Zelda");
        frame.setSize(WIDTH, HEIGHT);
        frame.setLocationRelativeTo(null); // centers window
        frame.setDefaultCloseOperation(JFrame.EXIT_ON_CLOSE);
//        frame.pack();
        frame.setResizable(false);
        frame.setVisible(true);

        WelcomeScreen welcomePanel = new WelcomeScreen();
        frame.getContentPane().add(welcomePanel);
    }

    @Override
    public void run() {

    }
}
