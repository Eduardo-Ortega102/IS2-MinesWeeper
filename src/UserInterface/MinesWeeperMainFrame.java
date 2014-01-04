package UserInterface;

import UserInterface.AbstractInterface.MineFieldViewer;
import java.awt.*;
import javax.swing.*;

public class MinesWeeperMainFrame extends JFrame {

    private InfoPanel infoPanel;
    private MineFieldViewer minesViewer;
    private ActionListenerFactory factory;

    public MinesWeeperMainFrame(ActionListenerFactory factory) throws HeadlessException {
        this.factory = factory;
        this.setTitle("Minesweeper");
        this.setDefaultCloseOperation(EXIT_ON_CLOSE);
        this.add(createToolbar(), BorderLayout.NORTH);
    }

    public void setMinesViewer(MineFieldViewer minesViewer) {
        this.minesViewer = minesViewer;
    }

    public void execute() {
        if (this.infoPanel == null) {
            this.infoPanel = new InfoPanel();
            this.add(infoPanel, BorderLayout.SOUTH);
        }
        this.remove((Component) minesViewer);
        this.add((Component) minesViewer, BorderLayout.CENTER);
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
        game.add(createItem("NewGame", "New Game"));
        game.add(createItem("Options", "Options"));
        game.add(createItem("Exit", "Exit"));
        return game;
    }

    private JMenu createHelpMenu() {
        JMenu help = new JMenu("Help");
        help.add(createHowToPlayItem());
        return help;
    }

    private JMenuItem createHowToPlayItem() {
        JMenuItem item = new JMenuItem("How to play");
        return item;
    }

    private JMenuItem createItem(String action, String label) {
        JMenuItem item = new JMenuItem(label);
        item.addActionListener(factory.createActionListener(action));
        return item;
    }
}
