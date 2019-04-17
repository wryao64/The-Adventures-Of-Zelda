package View;

import Controller.GameController;

import javax.swing.*;
import java.awt.*;
import java.awt.event.ActionEvent;

public class PauseScreen extends JDialog {
    private PausePane pausePane;

    private static final Color backgroundColor = new Color(123, 63, 0);

    public PauseScreen(Window window) {
        super(window, "", Dialog.ModalityType.APPLICATION_MODAL);

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
    public class PausePane extends JPanel {
        public PausePane() {
            JLabel pausedLabel = new JLabel("PAUSED");
            pausedLabel.setForeground(Color.ORANGE);
            int borderSize = 15;

            JPanel pausedPanel = new JPanel();
            pausedPanel.setOpaque(false);
            pausedPanel.add(pausedLabel);

            this.setBackground(backgroundColor);
            this.setBorder(BorderFactory.createEmptyBorder(borderSize, borderSize, borderSize, borderSize));
            this.setLayout(new GridLayout(0, 1, 10, 10));

            this.add(pausedPanel);
            this.add(new JButton(new pauseAction("RESUME")));
            this.add(new JButton(new pauseAction("RESTART")));
        }

        // removes dialog
        private class pauseAction extends AbstractAction {
            public pauseAction(String name) {
                super(name);
            }

            @Override
            public void actionPerformed(ActionEvent e) {
                Component comp = (Component) e.getSource();
                Window win = SwingUtilities.getWindowAncestor(comp);
                win.dispose();
            }
        }
    }
}

