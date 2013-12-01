package UserInterface;

import Control.Command;
import Model.MineField;
import java.awt.*;
import javax.swing.*;

public class MineFieldViewer extends JPanel {

    private static MineFieldButton[][] matrix;
    private static Command command;
    private static int squareNumber;
    private static int count;

    public MineFieldViewer(Command command) {
        MineFieldViewer.command = command;
        MineField mineField = MineField.getInstance();
        matrix = new MineFieldButton[mineField.getHigh()][mineField.getWidth()];
        this.setBackground(Color.red);
        this.setLayout(new GridLayout(mineField.getHigh(), mineField.getWidth()));
        squareNumber = mineField.getHigh()*mineField.getWidth();
        
        for (int i = 0; i < matrix.length; i++) {
            for (int j = 0; j < matrix[i].length; j++) {
                matrix[i][j] = new MineFieldButton(i, j, mineField.getMineField()[i][j]);
            }
        }

        for (int i = 0; i < matrix.length; i++) {
            for (int j = 0; j < matrix[i].length; j++) {
                this.add(matrix[i][j]);
            }
        }

    }

    public static void gameOver() {
        GameOver gameOver = new GameOver();
        gameOver.execute();
    }
    
    public static void winner() {
        Winner win = new Winner();
        win.execute(command);
    }

    public static void reLoad(int posX, int posY) {
        reLoadColumnButton(posX, posY, false);
        countDisableButtons();
        if ((squareNumber - count) == MineField.getInstance().getMinesNumber()) winner();
    }

    private static int reLoadColumnButton(int posX, int posY, boolean stop) {
        if (posX < 0 || posX == matrix.length || stop == true) {
            return 1;
        } else {
            stop = matrix[posX][posY].showValue();
            reLoadRowButton(posX, (posY - 1), stop);
            reLoadRowButton(posX, (posY + 1), stop);
            reLoadColumnButton((posX - 1), posY, stop);
            reLoadColumnButton((posX + 1), posY, stop);
            return 0;
        }
    }

    private static int reLoadRowButton(int posX, int posY, boolean stop) {
        if (posY < 0 || posY == matrix[0].length || stop == true) {
            return 1;
        } else {
            stop = matrix[posX][posY].showValue();
            reLoadColumnButton((posX - 1), posY, stop);
            reLoadColumnButton((posX + 1), posY, stop);
            reLoadRowButton(posX, (posY - 1), stop);
            reLoadRowButton(posX, (posY + 1), stop);
            return 0;
        }
    }
    
    private static void countDisableButtons() {
        count = 0;
        for (int i = 0; i < matrix.length; i++) {
            for (int j = 0; j < matrix[i].length; j++) {
                if(!matrix[i][j].isEnabled()) count++;
            }
        }
    }
}
