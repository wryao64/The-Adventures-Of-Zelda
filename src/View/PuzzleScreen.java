package View;

import Controller.GameController;
import Controller.GameState;

import javax.swing.*;
import java.awt.*;
import java.awt.event.KeyEvent;
import java.awt.event.KeyListener;

public class PuzzleScreen extends JDialog implements KeyListener{
    Image backgroundImage;

    GameController gameController;
    GameState nextState;
    GameScreen gameScreen;

    private final int IMAGE_WIDTH = 450;
    private final int IMAGE_HEIGHT = 450;

    public PuzzleScreen(Window window, GameController gameController,GameScreen gameScreen) {
        super(window, "", Dialog.ModalityType.APPLICATION_MODAL);
        this.gameController = gameController;
        this.gameScreen = gameScreen;

        GameState state = gameController.getCurrentState();
        switch (state) {
            case TUTORIAL:
                nextState = GameState.LEVEL_1;
                backgroundImage = new ImageIcon("Assets/wisdom.png").getImage();
                break;
            case LEVEL_1:
                nextState = GameState.LEVEL_2;
                backgroundImage = new ImageIcon("Assets/courage.png").getImage();
                break;
            case LEVEL_2:
                nextState = GameState.LEVEL_BOSS;
                backgroundImage = new ImageIcon("Assets/power.png").getImage();
                break;
            case LEVEL_BOSS:
                nextState = GameState.END;
        }

        setDefaultCloseOperation(JFrame.DO_NOTHING_ON_CLOSE);
        setBounds(300, 300, IMAGE_WIDTH, IMAGE_HEIGHT);

        PuzzlePane puzzlePane = new PuzzlePane();
        this.getContentPane().add(puzzlePane);
        this.addKeyListener(this);
        setFocusable(true);
        setUndecorated(true);
        setLocationRelativeTo(window);
        setVisible(true);
        pack();
    }


    @Override
    public void keyTyped(KeyEvent e) {

    }

    @Override
    public void keyPressed(KeyEvent e) {
        int c = e.getKeyCode();
        if (c == KeyEvent.VK_ENTER){
            this.dispose();
            gameController.setPaused(false);
            gameController.updateGameState(nextState);
            gameScreen.setPause(false);
            gameScreen.timer();
        }
    }

    @Override
    public void keyReleased(KeyEvent e) {

    }

    public class PuzzlePane extends JPanel {

        public PuzzlePane(){
            this.setLayout(null);
            setFocusable(true);
        }

        @Override
        public void paintComponent(Graphics g){
            super.paintComponent(g);
            g.drawImage(backgroundImage, 0, 0, IMAGE_WIDTH, IMAGE_HEIGHT, null);

        }
    }
}


