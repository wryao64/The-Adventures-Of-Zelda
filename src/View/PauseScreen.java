package View;

import Controller.GameController;
import Controller.GameState;

import javax.swing.*;
import java.awt.*;
import java.awt.event.ActionEvent;
import java.awt.event.KeyEvent;
import java.awt.event.KeyListener;

public class PauseScreen extends JDialog {
    private GameController gameController;
    private PausePane pausePane;
    private GameScreen gameScreen;

    private static final String fontFamily = "Arial";
    private static final Color backgroundColor = new Color(123, 63, 0);
    private static final Font regularFont = new Font(fontFamily, Font.BOLD, 16);

    public PauseScreen(Window window, GameController gameController, GameScreen gameScreen) {
        super(window, "", Dialog.ModalityType.APPLICATION_MODAL);
        this.gameController = gameController;
        this.gameScreen = gameScreen;

        pausePane = new PausePane();
        this.getContentPane().add(pausePane);
        this.setUndecorated(true);
        this.pack();
        this.setLocationRelativeTo(window);
        this.setVisible(true);
    }

    /**
     * Pane contains contents of Pause Screen
     */
    public class PausePane extends JPanel implements KeyListener {
        private JButton button1, button2;

        public PausePane() {
            JLabel pausedLabel = new JLabel("PAUSED");
            pausedLabel.setFont(regularFont);
            pausedLabel.setForeground(Color.ORANGE);
            int borderSize = 15;

            JPanel pausedPanel = new JPanel();
            pausedPanel.setOpaque(false);
            pausedPanel.add(pausedLabel);

            this.setFocusable(true);
            this.addKeyListener(this);

            this.setBackground(backgroundColor);
            this.setBorder(BorderFactory.createEmptyBorder(borderSize, borderSize, borderSize, borderSize));
            this.setLayout(new GridLayout(0, 1, 10, 10));

            this.add(pausedPanel);
            button1 = new JButton(new pauseAction("RESUME"));
            button2 = new JButton(new pauseAction("RESTART"));
            this.add(button1);
            this.add(button2);
        }

        @Override
        public void keyTyped(KeyEvent e) {
        }

        @Override
        public void keyPressed(KeyEvent e) {
            int c = e.getKeyCode();
            if (c == KeyEvent.VK_P) {
                gameController.setPaused(false);
                gameScreen.setPause(false);
                gameScreen.timer();
                Window win = SwingUtilities.getWindowAncestor(button1);
                win.dispose();
            }
        }

        @Override
        public void keyReleased(KeyEvent e) {
        }

        // removes dialog
        private class pauseAction extends AbstractAction {
            public pauseAction(String name) {
                super(name);
            }

            @Override
            public void actionPerformed(ActionEvent e) {
                JButton button = (JButton) e.getSource();

                if (button.getText() == "RESTART") {
                    gameController.updateGameState(GameState.WELCOME);
                } else if (button.getText() == "RESUME") {
                    gameController.setPaused(false);
                    gameScreen.setPause(false);
                    gameScreen.timer();
                }

                Window win = SwingUtilities.getWindowAncestor(button);
                win.dispose();
            }
        }
    }
}

