package View;

import javax.swing.*;
import java.awt.*;
import java.awt.event.ActionEvent;

public class PauseScreen extends JPanel {
    private static final Color backgroundColor = new Color(123, 63, 0);

    public PauseScreen() {
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
        this.add(new JButton(new FooAction("RESUME")));
        this.add(new JButton(new FooAction("RESTART")));
        this.add(new JButton(new FooAction("EXIT TO MAP")));
    }

    // removes dialog
    private class FooAction extends AbstractAction {
        public FooAction(String name) {
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
