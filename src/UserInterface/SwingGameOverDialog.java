package UserInterface;

import UserInterface.AbstractInterface.GameOverDialog;
import java.awt.*;
import javax.swing.*;
import static javax.swing.JFrame.EXIT_ON_CLOSE;

public class SwingGameOverDialog extends JFrame implements GameOverDialog {

    private ActionListenerFactory factory;

    public SwingGameOverDialog(ActionListenerFactory factory) throws HeadlessException {
        super("Game Over");
        this.factory = factory;
        this.setDefaultCloseOperation(EXIT_ON_CLOSE);
        this.setLocation(100, 100);
        this.add(new JLabel("Game Over, better luck next time."), BorderLayout.CENTER);
        this.add(createButtonbar(), BorderLayout.SOUTH);
        this.pack();
    }

    @Override
    public void showDialog() {
        this.setVisible(true);
    }

    @Override
    public void hidDialog() {
        this.setVisible(false);
    }

    private JPanel createButtonbar() {
        JPanel panel = new JPanel();
        panel.add(createButton("Exit", "Exit"));
        panel.add(createButton("Restart", "Restar This Game"));
        panel.add(createButton("NewGame", "Play New Game"));
        return panel;
    }

    private JButton createButton(String action, String label) {
        JButton button = new JButton(label);
        button.addActionListener(factory.createActionListener(action));
        return button;
    }
}
