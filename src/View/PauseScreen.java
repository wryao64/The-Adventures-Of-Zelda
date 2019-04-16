package View;

import javax.swing.*;
import java.awt.*;
import java.awt.event.ActionEvent;

public class PauseScreen extends JPanel {
    private static final Color BG = new Color(123, 63, 0);

    public PauseScreen() {
        JLabel pausedLabel = new JLabel("PAUSED");
        pausedLabel.setForeground(Color.ORANGE);

        JPanel pausedPanel = new JPanel();
        pausedPanel.setOpaque(false);
        pausedPanel.add(pausedLabel);

        setBackground(BG);
        int eb = 15;
        this.setBorder(BorderFactory.createEmptyBorder(eb, eb, eb, eb));
        this.setLayout(new GridLayout(0, 1, 10, 10));
        this.add(pausedPanel);
        this.add(new JButton(new FooAction("RESUME")));
        this.add(new JButton(new FooAction("RESTART")));
        this.add(new JButton(new FooAction("EXIT TO MAP")));

    }

    // simple action -- all it does is to make the dialog no longer visible
    private class FooAction extends AbstractAction {
        public FooAction(String name) {
            super(name);
        }

        @Override
        public void actionPerformed(ActionEvent e) {
            Component comp = (Component) e.getSource();
            Window win = SwingUtilities.getWindowAncestor(comp);
            win.dispose();  // here -- dispose of the JDialog
        }
    }
}
