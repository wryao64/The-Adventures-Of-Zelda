package View;

import Controller.GameController;

import javax.swing.*;
import java.awt.*;
import java.awt.event.KeyEvent;
import java.awt.event.KeyListener;

public class NarrationDialog extends JDialog implements KeyListener {
    int imageIndex = 1;
    Image narrationImage;

    GameController gameController;
    NarrationPane narrationPane;

    private final int IMAGE_WIDTH = 1200;
    private final int IMAGE_HEIGHT = 200;

    Image[] imageArray = {
            new ImageIcon("Assets/Rauru1.png").getImage(),
            new ImageIcon("Assets/Rauru2.png").getImage(),
            new ImageIcon("Assets/Rauru3.png").getImage(),
            new ImageIcon("Assets/Rauru4.png").getImage(),
    };

    public NarrationDialog(Window window, GameController gameController) {
        super(window, "", Dialog.ModalityType.APPLICATION_MODAL);
        this.gameController = gameController;

        this.setBounds(0, 0, IMAGE_WIDTH, IMAGE_HEIGHT);
        this.addKeyListener(this);
        this.setFocusable(true);
        this.setUndecorated(true);
        this.setLocationRelativeTo(window);

        narrationImage = imageArray[0];
        narrationPane = new NarrationPane();
        this.getContentPane().add(narrationPane);

        this.setVisible(true);
        pack();
    }

    @Override
    public void keyTyped(KeyEvent e) {

    }

    @Override
    public void keyPressed(KeyEvent e) {
        int c = e.getKeyCode();

        if (c == KeyEvent.VK_ENTER) {
            if (imageIndex < imageArray.length) {
                narrationImage = imageArray[imageIndex];
                imageIndex++;
                narrationPane.repaint();
            } else {
                gameController.finishNarration();
                gameController.setPaused(false);
                this.dispose();
            }
        }
    }

    @Override
    public void keyReleased(KeyEvent e) {

    }

    public class NarrationPane extends JPanel {

        public NarrationPane(){
            this.setLayout(null);
            setFocusable(true);
        }

        @Override
        public void paintComponent(Graphics g){
            super.paintComponent(g);
            g.drawImage(narrationImage, 0, 0, IMAGE_WIDTH, IMAGE_HEIGHT, null);

        }
    }
}
