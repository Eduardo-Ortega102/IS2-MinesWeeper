package UserInterface;

import UserInterface.AbstractInterface.WinnerDialog;
import java.awt.*;
import javax.swing.*;
import java.awt.HeadlessException;

public class SwingWinnerDialog extends JFrame implements WinnerDialog {

    private ActionListenerFactory factory;

    public SwingWinnerDialog(ActionListenerFactory factory) throws HeadlessException {
        super("You Win");
        this.factory = factory;
        this.setDefaultCloseOperation(EXIT_ON_CLOSE);
        this.setLocation(100, 100);
        this.add(createPanel(), BorderLayout.CENTER);
        this.add(createButtonbar(), BorderLayout.SOUTH);
        this.pack();
        this.setScreenLocation();
    }

    private void setScreenLocation() {
        Toolkit tk = Toolkit.getDefaultToolkit();
        this.setLocation(
                (int) ((tk.getScreenSize().getWidth() - this.getWidth()) / 2),
                (int) ((tk.getScreenSize().getHeight() - this.getHeight()) / 2));
    }

    @Override
    public void hidDialog() {
        this.setVisible(false);
    }

    @Override
    public void showDialog() {
        this.setVisible(true);
    }

    private JPanel createPanel() {
        JPanel panel = new JPanel();
        panel.add(new JLabel("YOU WIN"));
        return panel;
    }

    private JPanel createButtonbar() {
        JPanel panel = new JPanel();
        panel.setLayout(new FlowLayout(FlowLayout.CENTER));
        panel.add(createButton("Exit", "Exit"));
        panel.add(createButton("PlayAgain", "Play Again"));
        return panel;
    }

    private JButton createButton(String action, String label) {
        JButton button = new JButton(label);
        button.addActionListener(factory.createActionListener(action));
        return button;
    }
}
