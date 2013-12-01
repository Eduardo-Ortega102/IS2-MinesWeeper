package UserInterface;

import Model.MineField;
import java.awt.*;
import javax.swing.*;

public class MineFieldViewer extends JPanel {

    private static MineFieldButton[][] matrix;

    public MineFieldViewer() {
        MineField mineField = MineField.getInstance();
        matrix = new MineFieldButton[mineField.getHigh()][mineField.getWidth()];
        this.setBackground(Color.red);
        this.setLayout(new GridLayout(mineField.getHigh(), mineField.getWidth()));

        for (int i = 0; i < matrix.length; i++) {
            for (int j = 0; j < matrix[i].length; j++) {
                matrix[i][j] = new MineFieldButton(i, j, mineField.getMineField()[i][j]);
            }
        }
        this.loadMineField();

    }

    private void gameOver() {
        System.out.println("FIN DEL JUEGO");
    }

    private void loadMineField() {
        for (int i = 0; i < matrix.length; i++) {
            for (int j = 0; j < matrix[i].length; j++) {
                this.add(matrix[i][j]);
            }
        }
    }

    public static void reLoad(int posX, int posY) {
        reLoadColumnButton(posX, posY, false);
    }

    private static int reLoadColumnButton(int posX, int posY, boolean stop) {
        if (posX < 0 || posX == matrix.length || stop == true) {
            return 0;
        }else{
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
            return 0;
        }else{
            stop = matrix[posX][posY].showValue();
            reLoadRowButton(posX, (posY - 1), stop);
            reLoadRowButton(posX, (posY + 1), stop);
            return 0;
        }
    }
}
