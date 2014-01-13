package UserInterface;

import UserInterface.AbstractInterface.MineFieldViewer;
import UserInterface.AbstractInterface.ActionFactory;
import UserInterface.AbstractInterface.MineViewer;
import UserInterface.AbstractInterface.MineViewerFactory;
import Model.MineField;
import java.awt.*;
import javax.swing.*;

public final class SwingMineFieldViewer extends JPanel implements MineFieldViewer {

    private MineViewer[][] matrix;
    private MineViewerFactory mineViewerFactory;
    private ActionFactory actionFactory;
    private int squareNumber;
    private int count;

    public SwingMineFieldViewer(MineViewerFactory viewerFactory, ActionFactory actionFactory) {
        this.mineViewerFactory = viewerFactory;
        this.actionFactory = actionFactory;
    }

    @Override
    public void load(MineField mineField) {
        if (matrix != null) clearViewer();
        matrix = new MineViewer[mineField.getHigh()][mineField.getWidth()];
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

    @Override
    public void restart() {
        count = 0;
        for (int i = 0; i < matrix.length; i++)
            for (int j = 0; j < matrix[i].length; j++)
                matrix[i][j].reset();
    }

    @Override
    public void showField(int posX, int posY) {
        executeViewer(posX, posY, false);
        countExecutedViewers();
        if ((squareNumber - count) == MineField.getInstance().getMinesNumber()) 
            this.actionFactory.getAction("Winner").execute(0, 0);
    }

    private int executeViewer(int posX, int posY, boolean stop) {
        if (stop) return 1;
        if (posY < 0 || posY == matrix[0].length) return 1;
        if (posX < 0 || posX == matrix.length) return 1;

        stop = matrix[posX][posY].showValue();

        executeViewer(posX, (posY + 1), stop);
        executeViewer(posX, (posY - 1), stop);
        executeViewer((posX + 1), posY, stop);
        executeViewer((posX - 1), posY, stop);
        return 0;
    }

    private void countExecutedViewers() {
        count = 0;
        for (int i = 0; i < matrix.length; i++)
            for (int j = 0; j < matrix[i].length; j++)
                if (matrix[i][j].isShowed()) count++;
    }
}
