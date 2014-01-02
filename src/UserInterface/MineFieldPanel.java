package UserInterface;

import Control.Command;
import Model.MineField;
import java.awt.*;
import javax.swing.*;

public class MineFieldPanel extends JPanel {

    private static MineButton[][] matrix;
    private static Command command;
    private static int squareNumber;
    private static int count;
    private static int time;

    public MineFieldPanel(Command command) {
        MineField mineField = MineField.getInstance();
        time = 0;
        squareNumber = mineField.getHigh() * mineField.getWidth();
        matrix = new MineButton[mineField.getHigh()][mineField.getWidth()];
        MineFieldPanel.command = command;
        this.setBackground(Color.red);
        this.setLayout(new GridLayout(mineField.getHigh(), mineField.getWidth()));

        for (int i = 0; i < matrix.length; i++) {
            for (int j = 0; j < matrix[i].length; j++) {
                matrix[i][j] = new MineButton(i, j, mineField.getMineField()[i][j]);
            }
        }

        for (int i = 0; i < matrix.length; i++) {
            for (int j = 0; j < matrix[i].length; j++) {
                this.add(matrix[i][j]);
            }
        }
    }

    public static void restart() {
        count = 0;
        time = 0;
        for (int i = 0; i < matrix.length; i++) {
            for (int j = 0; j < matrix[i].length; j++) {
                matrix[i][j].setText("");
                matrix[i][j].setEnabled(true);
            }
        }
    }

    public static void reLoad(int posX, int posY) {
        if (time == 0) InfoPanel.start();
        time = 1;
        showSquares(posX, posY, false);
        countDisableButtons();
        if ((squareNumber - count) == MineField.getInstance().getMinesNumber()) {
//            command.executeCommand(1);
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

    public static void partIsOver() {
//        command.executeCommand(0);
    }

    private static void countDisableButtons() {
        count = 0;
        for (int i = 0; i < matrix.length; i++) {
            for (int j = 0; j < matrix[i].length; j++) {
                if (!matrix[i][j].isEnabled()) count++;
            }
        }
    }
}
