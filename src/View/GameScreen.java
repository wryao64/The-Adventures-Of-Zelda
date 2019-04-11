package View;

import javax.swing.*;
import java.awt.*;

public class GameScreen extends JPanel{

    private final int WIDTH = 640;
    private final int HEIGHT = 480;

    private int fps = 60;

    private boolean running;

    public GameScreen() {
        setPreferredSize(new Dimension(WIDTH,HEIGHT));
        setFocusable(true);
        requestFocus();
    }

}
