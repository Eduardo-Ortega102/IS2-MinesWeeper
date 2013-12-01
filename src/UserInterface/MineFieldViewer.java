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
        boolean adjacentMines = matrix[posX][posY].showValue();
        if (adjacentMines) {
            reLoadColumnButton(posX,posY);
            reLoadRowButton(posX,posY);
        }
        //reLoadDiagonalButton(posX,posY);
        
    }

    private static void reLoadColumnButton(int posX, int posY) {
        int index = posY - 1;
        boolean execute = true;
        while(index >= 0 && matrix[posX][index].isNotMine() && execute){
            execute = matrix[posX][index].showValue();
            index--;
        }
        
        execute = true;
        index = posY + 1;
        while(index < matrix.length && matrix[posX][index].isNotMine() && execute){
            execute = matrix[posX][index].showValue();
            index++;
        }
    }

    private static void reLoadRowButton(int posX, int posY) {
        int index = posX - 1;
        boolean execute = true;
        while(index >= 0 && matrix[index][posY].isNotMine() && execute){
            execute = matrix[index][posY].showValue();
            index--;
        }
        
        execute = true;
        index = posX + 1;
        while(index < matrix[0].length && matrix[index][posY].isNotMine() && execute){
            execute = matrix[index][posY].showValue();
            index++;
        }
    }

    private static void reLoadDiagonalButton(int posX, int posY) {
        throw new UnsupportedOperationException("Not supported yet."); //To change body of generated methods, choose Tools | Templates.
    }
}
