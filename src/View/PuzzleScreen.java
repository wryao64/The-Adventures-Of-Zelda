package View;

import Controller.GameController;
import Controller.GameState;

import javax.swing.*;
import javax.swing.border.EmptyBorder;
import java.awt.*;
import java.util.ArrayList;

import static Controller.GameState.*;

public class PuzzleScreen extends JDialog {
    JPanel panel;

    GameController gameController;
    GameState nextState;

    public PuzzleScreen(Window window, GameController gameController ) {
        super(window, "", Dialog.ModalityType.APPLICATION_MODAL);
        this.gameController = gameController;

        GameState state = gameController.getCurrentState();
        switch (state) {
            case TUTORIAL:
                nextState = LEVEL_1;
                break;
            case LEVEL_1:
                nextState = LEVEL_2;
                break;
            case LEVEL_2:
                nextState = LEVEL_BOSS;
                break;
            case LEVEL_BOSS:
                nextState = LEVEL_BOSS;
                break;
        }

        setDefaultCloseOperation(JFrame.DO_NOTHING_ON_CLOSE);
        setBounds(300,300,550, 400);
        panel = new JPanel();
        panel.setLayout(new GridLayout(0, 1, 10, 10));

        JLabel titleLabel = new JLabel("You Found an Orb!");
        panel.add(titleLabel);

        JLabel orbLabel = new JLabel("picture of orb here");
        panel.add(orbLabel);

        JButton button = new JButton("NEXT LEVEL");
        button.setPreferredSize(new Dimension(100,50));
        panel.add(button);

        button.addActionListener(e ->{
            Window win = SwingUtilities.getWindowAncestor(button);
            win.dispose();
            gameController.updateGameState(nextState);

            gameController.setPaused(false);
                }
        );

        panel.setBorder(new EmptyBorder(5, 5, 5, 5));
        setContentPane(panel);
        setFocusable(true);
        this.setLocationRelativeTo(window);
        setVisible(true);
    }

    public void setUpMessages() {
    }

}
