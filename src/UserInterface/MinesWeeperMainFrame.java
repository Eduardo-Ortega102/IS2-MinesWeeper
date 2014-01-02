package UserInterface;

import UserInterface.AbstractInterface.MinesWeeperInterface;
import java.awt.*;
import java.awt.event.ActionEvent;
import java.awt.event.ActionListener;
import javax.swing.*;

public class MinesWeeperMainFrame extends JFrame implements MinesWeeperInterface{

    private InfoPanel infoPanel;
    private MineFieldPanel minesPanel;
    
    public MinesWeeperMainFrame(InfoPanel infoPanel, MineFieldPanel minesPanel) throws HeadlessException {
        this.infoPanel = infoPanel;
        this.minesPanel = minesPanel;
        this.setTitle("Minesweeper");
        this.setDefaultCloseOperation(EXIT_ON_CLOSE);
        this.add(createToolbar(), BorderLayout.NORTH);
    }
    
    public void execute(){
        this.add(infoPanel, BorderLayout.SOUTH);
        this.add(minesPanel, BorderLayout.CENTER);
        this.pack();
        this.setVisible(true);
    }

    private JPanel createToolbar() {
        JPanel panel = new JPanel();
        panel.setBackground(Color.BLUE);
        panel.setLayout(new FlowLayout(FlowLayout.LEFT));
        panel.add(createMenus());
        return panel;
    }

    private JMenuBar createMenus() {
        JMenuBar menuBar = new JMenuBar();
        menuBar.add(createGameMenu());
        menuBar.add(createHelpMenu());
        return menuBar;
    }

    private JMenu createGameMenu() {
        JMenu game = new JMenu("Game");
        game.add(createNewGameItem());
        game.add(createOptionsItem());
        game.add(createExitItem());
        return game;
    }

    private JMenu createHelpMenu() {
        JMenu help = new JMenu("Help");
        help.add(createHowToPlayItem());
        return help;
    }

    private JMenuItem createNewGameItem() {
        JMenuItem item = new JMenuItem("New Game");
        return item;
    }

    private JMenuItem createOptionsItem() {
        JMenuItem item = new JMenuItem("Options");
        return item;
    }

    private JMenuItem createExitItem() {
        JMenuItem item = new JMenuItem("Exit");
        item.addActionListener(new ActionListener() {
            @Override
            public void actionPerformed(ActionEvent e) {
                kill();
            }
        });
        return item;
    }

    private JMenuItem createHowToPlayItem() {
        JMenuItem item = new JMenuItem("How to play");
        return item;
    }

    public void kill() {
        dispose();
    }
}
