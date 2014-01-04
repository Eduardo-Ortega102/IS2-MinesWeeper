package UserInterface;

import UserInterface.AbstractInterface.MineFieldViewer;
import Model.MineField;
import UserInterface.AbstractInterface.ActionFactory;
import UserInterface.AbstractInterface.MineViewer;
import UserInterface.AbstractInterface.MineViewerFactory;
import java.awt.*;
import java.util.HashMap;
import javax.swing.*;

public final class SwingMineFieldViewer extends JPanel implements MineFieldViewer {

    private static MineViewer[][] matrix;
    private static MineViewerFactory mineViewerFactory;
    private static ActionFactory actionFactory;
    private static HashMap<String, UserInterface.AbstractInterface.Action> actionMap;
    private static ActionListenerFactory factory;
    private static int squareNumber;
    private static int count;
    private static int firstTime;

    public SwingMineFieldViewer(MineViewerFactory viewerFactory, ActionListenerFactory factory) {
        SwingMineFieldViewer.mineViewerFactory = viewerFactory;
        SwingMineFieldViewer.factory = factory;
        this.createActionMap();
        this.createActionFactory();
    }

    private void createActionMap() {
        actionMap = new HashMap<>();
        actionMap.put("partIsOver", new UserInterface.AbstractInterface.Action() {
            @Override
            public void execute(int x, int y) {
                JButton button = new JButton();
                button.addActionListener(factory.createActionListener("Loser"));
                button.doClick();
                InfoPanel.stop();
            }
        });

        actionMap.put("reLoad", new UserInterface.AbstractInterface.Action() {
            @Override
            public void execute(int x, int y) {
                reLoad(x, y);
            }
        });
    }

    private void createActionFactory() {
        actionFactory = new ActionFactory() {
            @Override
            public UserInterface.AbstractInterface.Action getAction(String action) {
                return actionMap.get(action);
            }
        };
    }

    @Override
    public void load(MineField mineField) {
        if(matrix != null) clearViewer();
        matrix = new MineViewer[mineField.getHigh()][mineField.getWidth()];
        firstTime = 0;
        squareNumber = mineField.getHigh() * mineField.getWidth();
        this.setLayout(new GridLayout(mineField.getHigh(), mineField.getWidth()));
        this.createMatrix(mineField);
        this.addMatrix();
    }
    
    private void clearViewer() {
        this.removeAll();
    }

    private void createMatrix(MineField mineField) {
        for (int i = 0; i < matrix.length; i++)
            for (int j = 0; j < matrix[i].length; j++)
                matrix[i][j] = mineViewerFactory.createMineViewer(i, j,
                        mineField.getMineField()[i][j], actionFactory);
    }

    private void addMatrix() {
        for (int i = 0; i < matrix.length; i++)
            for (int j = 0; j < matrix[i].length; j++)
                this.add((Component) matrix[i][j]);
    }
    
    private void reset(){
        count = 0;
        firstTime = 0;
        InfoPanel.reset();
    }

    @Override
    public void restart() {
        reset();
        for (int i = 0; i < matrix.length; i++) 
            for (int j = 0; j < matrix[i].length; j++) 
                matrix[i][j].reset();
    }

    public void reLoad(int posX, int posY) {
        if (firstTime == 0) InfoPanel.start();
        firstTime = 1;
        showSquares(posX, posY, false);
        countDisableButtons();
        if ((squareNumber - count) == MineField.getInstance().getMinesNumber()) {
            JButton button = new JButton();
            button.addActionListener(factory.createActionListener("Winner"));
            button.doClick();
            InfoPanel.stop();
        }
    }

    private static int showSquares(int posX, int posY, boolean stop) {
        if (stop) return 1;
        if (posY < 0 || posY == matrix[0].length) return 1;
        if (posX < 0 || posX == matrix.length) return 1;

        stop = matrix[posX][posY].showValue();

        showSquares(posX, (posY + 1), stop);
        showSquares(posX, (posY - 1), stop);
        showSquares((posX + 1), posY, stop);
        showSquares((posX - 1), posY, stop);
        return 0;
    }

    private static void countDisableButtons() {
        count = 0;
        for (int i = 0; i < matrix.length; i++)
            for (int j = 0; j < matrix[i].length; j++)
                if (!matrix[i][j].isShowed()) count++;
    }

}
