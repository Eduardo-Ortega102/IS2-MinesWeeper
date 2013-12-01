package UserInterface;

import Control.Command;
import java.awt.*;
import java.awt.HeadlessException;
import java.awt.event.ActionEvent;
import java.awt.event.ActionListener;
import javax.swing.*;

public class WinnerDialog extends JFrame{
    private JButton exitButton;
    private JButton playAgainButton;
    private Command command;

    public WinnerDialog() throws HeadlessException {
        this.setTitle("You Win");
        this.setDefaultCloseOperation(EXIT_ON_CLOSE);
        this.setLocation(100, 100);
        this.add(createPanel(), BorderLayout.CENTER);
        this.add(createButtonbar(), BorderLayout.SOUTH);
        this.pack();
    }

    public void execute(Command command) {
        this.command = command;
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
        inicializeExitButton();
        inicializePlayAgainButton();
        panel.add(exitButton);
        panel.add(playAgainButton);
        return panel;
    }

    private void inicializeExitButton() {
        exitButton = new JButton("Exit");
        exitButton.addActionListener(new ActionListener() {
            @Override
            public void actionPerformed(ActionEvent e) {
                command.executeCommand(0);
            }
        });
    }

    private void inicializePlayAgainButton() {
        playAgainButton = new JButton("Play Again");
        playAgainButton.addActionListener(new ActionListener() {
            @Override
            public void actionPerformed(ActionEvent e) {
                command.executeCommand(3);
            }
        });
    }
}
