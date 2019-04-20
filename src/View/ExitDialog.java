package View;

import Controller.GameController;

import javax.swing.*;
import java.awt.*;
import java.awt.event.ActionEvent;

public class ExitDialog extends JDialog {
    private Window window;
    private GameController gameController;

    private static final Color backgroundColor = new Color(123, 63, 0);

    public ExitDialog(Window window, GameController gameController) {
        super(window, Dialog.ModalityType.APPLICATION_MODAL);
        this.window = window;
        this.gameController = gameController;
        this.createGuiAndShow();
    }

    private void createGuiAndShow() {
        ExitPane exitPane = new ExitPane();
        this.getContentPane().add(exitPane);

        this.setUndecorated(true);
        this.pack();
        this.setLocationRelativeTo(window);
        this.setVisible(true);
    }

    /**
     * Pane contains contents of Exit Dialog
     */
    private class ExitPane extends JPanel {
        public ExitPane() {
            this.createGui();
        }

        private void createGui() {
            int borderSize = 15;
            this.setBorder(BorderFactory.createEmptyBorder(borderSize, borderSize, borderSize, borderSize));
            this.setBackground(backgroundColor);

            JLabel confirmMessage = new JLabel("Are you sure you want to exit the game?");
            confirmMessage.setForeground(Color.ORANGE);
            this.setLayout(new GridLayout(0, 1, 10, 10));

            // Confirmation buttons
            JButton yesButton = new JButton(new exitAction("Yes"));
            JButton noButton = new JButton(new exitAction("No"));

            this.add(confirmMessage);
            this.add(yesButton);
            this.add(noButton);
        }

        private class exitAction extends AbstractAction {
            public exitAction(String name) {
                super(name);
            }

            @Override
            public void actionPerformed(ActionEvent e) {
                JButton button = (JButton) e.getSource();

                if (button.getText() == "Yes") {
                    System.exit(0);
                } else if (button.getText() == "No") {
                    gameController.setPaused(false);
                }

                Window win = SwingUtilities.getWindowAncestor(button);
                win.dispose();
            }
        }
    }
}
