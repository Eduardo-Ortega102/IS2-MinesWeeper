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
        matrix[posX][posY].setEnabled(false);
        reLoadColumnButton(posX,posY);
        reLoadRowButton(posX,posY);
        reLoadDiagonalButton(posX,posY);
       
        
        for (int i = posX; i < matrix.length; i++) {
            for (int j = posY; j < matrix[i].length; j++) {
            }
        }
    }

    private static void reLoadColumnButton(int posX, int posY) {
        throw new UnsupportedOperationException("Not supported yet."); //To change body of generated methods, choose Tools | Templates.
    }

    private static void reLoadRowButton(int posX, int posY) {
        throw new UnsupportedOperationException("Not supported yet."); //To change body of generated methods, choose Tools | Templates.
    }

    private static void reLoadDiagonalButton(int posX, int posY) {
        throw new UnsupportedOperationException("Not supported yet."); //To change body of generated methods, choose Tools | Templates.
    }
}
