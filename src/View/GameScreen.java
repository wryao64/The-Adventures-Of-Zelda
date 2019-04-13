package View;

import Controller.Level;

import javax.swing.*;
import java.awt.*;
import java.awt.event.KeyListener;


public class GameScreen extends JPanel {

    Level currentLevel;

    public GameScreen() {
        setPreferredSize(new Dimension(WIDTH,HEIGHT));
        setFocusable(true);
        requestFocus();
    }

    public void setLevel(Level level) {
        currentLevel = level;
    }

    public void update(){
        currentLevel.updateLevel();
    }

    public void render(){
    }
}
