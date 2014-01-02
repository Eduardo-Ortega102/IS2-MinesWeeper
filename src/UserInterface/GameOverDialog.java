package UserInterface;

import Control.Command;
import UserInterface.AbstractInterface.GameOverInterface;
import java.awt.*;
import javax.swing.*;
import java.awt.event.ActionEvent;
import java.awt.event.ActionListener;
import static javax.swing.JFrame.EXIT_ON_CLOSE;

public class GameOverDialog extends JFrame implements GameOverInterface{

    private JButton exitButton;
    private JButton playNewGameButton;
    private JButton restartThisGame;
    private Command command;

    public GameOverDialog(Command command) throws HeadlessException {
        this.command = command;
        this.setTitle("Game Over");
        this.setDefaultCloseOperation(EXIT_ON_CLOSE);
        this.setLocation(100, 100);
        this.add(new JLabel("Game Over, better luck next time."), BorderLayout.CENTER);
        this.add(createButtonbar(), BorderLayout.SOUTH);
        this.pack();
    }

    public void execute() {
        this.setVisible(true);
    }

    private JPanel createButtonbar() {
        JPanel panel = new JPanel();
        panel.add(createExitButton());
        panel.add(createRestartButton());
        panel.add(createNewGameButton());
        return panel;
    }

    private JButton createExitButton() {
        exitButton = new JButton("Exit");
        exitButton.addActionListener(new ActionListener() {
            @Override
            public void actionPerformed(ActionEvent e) {
//                command.executeCommand(0);
            }
        });
        return exitButton;
    }

    private JButton createRestartButton() {
        restartThisGame = new JButton("Restar This Game");
        restartThisGame.addActionListener(new ActionListener() {
            @Override
            public void actionPerformed(ActionEvent e) {
                MineFieldPanel.restart();
                visible();
            }
        });
        return restartThisGame;
    }

    private JButton createNewGameButton() {
        playNewGameButton = new JButton("Play New Game");
        playNewGameButton.addActionListener(new ActionListener() {
            @Override
            public void actionPerformed(ActionEvent e) {
//                command.executeCommand(3);
            }
        });
        return playNewGameButton;
    }

    private void visible() {
        this.setVisible(false);
    }
}
