package UserInterface;

import UserInterface.AbstractInterface.InfoPanel;
import UserInterface.AbstractInterface.MineFieldViewer;
import java.awt.*;
import javax.swing.*;

public class MinesWeeperMainFrame extends JFrame {

    private InfoPanel infoPanel;
    private MineFieldViewer minesViewer;
    private ActionListenerFactory actionListenerFactory;

    public MinesWeeperMainFrame(ActionListenerFactory factory, InfoPanel infoPanel) throws HeadlessException {
        this.actionListenerFactory = factory;
        this.infoPanel = infoPanel;
        this.setTitle("Minesweeper");
        this.setDefaultCloseOperation(EXIT_ON_CLOSE);
        this.add(createToolbar(), BorderLayout.NORTH);
        this.add((Component) infoPanel, BorderLayout.SOUTH);
    }

    public void setMinesViewer(MineFieldViewer minesViewer) {
        this.minesViewer = minesViewer;
        this.remove((Component) minesViewer);
    }

    public InfoPanel getInfoPanel() {
        return infoPanel;
    }

    public void execute() {
        this.infoPanel.refresh();
        this.add((Component) minesViewer, BorderLayout.CENTER);
        this.pack();
        this.setScreenLocation();
        this.setVisible(true);
    }

    private void setScreenLocation() {
        Toolkit tk = Toolkit.getDefaultToolkit();
        this.setLocation(
                (int) ((tk.getScreenSize().getWidth() - this.getWidth()) / 2),
                (int) ((tk.getScreenSize().getHeight() - this.getHeight()) / 2));
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
        help.add(createItem("Help", "How to play"));
        return help;
    }

    private JMenuItem createItem(String action, String label) {
        JMenuItem item = new JMenuItem(label);
        item.addActionListener(actionListenerFactory.createActionListener(action));
        return item;
    }
}
