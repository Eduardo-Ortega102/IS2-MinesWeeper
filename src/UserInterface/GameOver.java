package UserInterface;

import Control.Command;
import java.awt.BorderLayout;
import java.awt.Component;
import java.awt.HeadlessException;
import java.awt.PopupMenu;
import java.awt.event.ActionEvent;
import java.awt.event.ActionListener;
import javax.swing.*;
import static javax.swing.JFrame.EXIT_ON_CLOSE;

public class GameOver extends JFrame {
    private JButton exitButton;
    private JButton playAgainButton;
    private JButton restartThisGame;
    private Command command;

    public GameOver(Command command) throws HeadlessException {
        this.command = command;
        this.setTitle("Game Over");
        this.setDefaultCloseOperation(EXIT_ON_CLOSE);
        this.setLocation(100, 100);
        this.add(new JLabel("Game Over, better luck next time."), BorderLayout.CENTER);
        this.add(createButtonbar(), BorderLayout.SOUTH);
        this.pack();
    }
    public void kill(){
        dispose();
    }
    public void execute(){
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
                kill();
                command.executeCommand(new int[]{0});
            }
        });
        return exitButton;
    }

    private JButton createRestartButton() {
        restartThisGame = new JButton("Restar This Game");
        restartThisGame.addActionListener(new ActionListener() {
            @Override
            public void actionPerformed(ActionEvent e) {
                MineFieldViewer.restart();
                kill();
            }
        });
        return restartThisGame;
    }

    private JButton createNewGameButton() {
        playAgainButton = new JButton("Play New Game");
        playAgainButton.addActionListener(new ActionListener() {
            @Override
            public void actionPerformed(ActionEvent e) {
                command.executeCommand(new int[]{1});
                kill();
            }
        });
        return playAgainButton;
    }

}
