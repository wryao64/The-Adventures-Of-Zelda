package View;

import Controller.Level;

import javax.swing.*;
import java.awt.*;


public class GameScreen extends JPanel{

    Level level;

    public GameScreen() {
        setPreferredSize(new Dimension(WIDTH,HEIGHT));
        setFocusable(true);
        requestFocus();
    }

    public void setLevel() {

    }

    @Override
    protected void paintComponent(Graphics g) {
        super.paintComponent(g);

    }


}
