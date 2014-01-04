package Persistence;

import Model.MineField;
import Model.Square;
import java.util.Random;

public class RandomMineFieldLoader implements MineFieldLoader{

    private static MineField fieldInstance;
    private static RandomMineFieldLoader loaderInstance;
    private static int[] minesPerRow;
    private static int maxMinePerRow;

    private RandomMineFieldLoader() {
    }

    public static RandomMineFieldLoader getInstance() {
        if (loaderInstance == null) {
            loaderInstance = new RandomMineFieldLoader();
        }
        return loaderInstance;
    }

    @Override
    public void buildMineField(int high, int width, int minesNumber, final int X) throws Exception{
            MineField.createInstance(high, width, minesNumber);
            fieldInstance = MineField.getInstance();
            maxMinePerRow = X;
            inicializeMinesPerRow(fieldInstance.getHigh());
            createMineField(fieldInstance.getMineField());
            mineFieldSetter(minesNumber, fieldInstance.getMineField());
            inicializeAdjacentMinesValue(fieldInstance.getMineField());

            System.out.println("CAMPO RESULTANTE: ");
            MineField.print(fieldInstance.getMineField());
    }

    private void inicializeMinesPerRow(int high) {
        minesPerRow = new int[high];
        for (int i = 0; i < minesPerRow.length; i++) {
            minesPerRow[i] = 0;
        }
    }

    private void createMineField(Square[][] mineField) {
        for (int i = 0; i < mineField.length; i++) 
            for (int j = 0; j < mineField[i].length; j++) 
                mineField[i][j] = new Square(false);
    }

    private void mineFieldSetter(int minesNumber, Square[][] mineField) {
        int fieldElements = fieldInstance.getHigh() * fieldInstance.getWidth();
        Random rand = new Random();
        while (minesNumber > 0) {
            for (int iteration = 0; iteration < fieldElements && minesNumber > 0; iteration++) {
                int i = rand.nextInt(fieldInstance.getHigh());
                int j = rand.nextInt(fieldInstance.getWidth());
                if (mineField[i][j].isMine()) continue;
                if (minesPerRow[i] == maxMinePerRow) continue;

                mineField[i][j].setMine(getRandomMine(minesNumber));
                if (mineField[i][j].isMine()) {
                    minesPerRow[i] = minesPerRow[i] + 1;
                    minesNumber--;
                }
            }
            maxMinePerRow += 1;
        }
        
        /**
         * --------------------------------
         * ----------BORRAR LA SIGUIENTE INSTRUCCION
         * --------------------------------
         */
        MineField.print(mineField);
    }

    private boolean getRandomMine(int minesNumer) {
        if (minesNumer == 0) return false;
        double mark = 1.0 / 2;
        return ((Math.random()) > mark) ? false : true;
    }

    private void inicializeAdjacentMinesValue(Square[][] mineField) {
        for (int i = 0; i <= (mineField.length - 1); i++) {
            for (int j = 0; j <= (mineField[i].length - 1); j++) {

                if (mineField[i][j].isMine() == false) {
                    compareWithActualRow(i, j, mineField);
                    if (i == (mineField.length - 1)) {
                        compareWithRow(i, j, mineField, true);
                    } else {
                        switch (i) {
                            case 0:
                                compareWithRow(i, j, mineField, false);
                                break;
                            default:
                                compareWithRow(i, j, mineField, true);
                                compareWithRow(i, j, mineField, false);
                        }
                    }
                }
            }
        }
    }

    private void compareWithRow(int actualI, int actualJ, Square[][] mineField, boolean previousRow) {
        int startJ, stopJ;
        int rowI = (previousRow) ? (actualI - 1) : (actualI + 1);
        if (actualJ == (mineField[actualI].length - 1)) {
            startJ = actualJ - 1;
            stopJ = actualJ;
        } else {
            startJ = (actualJ == 0) ? actualJ : actualJ - 1;
            stopJ = (actualJ + 1);
        }

        for (int columnJ = startJ; columnJ <= stopJ; columnJ++) {
            compareElements(actualI, actualJ, rowI, columnJ, mineField);
        }
    }

    private void compareWithActualRow(int actualI, int actualJ, Square[][] mineField) {
        if (actualJ == mineField[actualI].length - 1) {
            compareElements(actualI, actualJ, actualI, (actualJ - 1), mineField);
        } else {
            switch (actualJ) {
                case 0:
                    compareElements(actualI, actualJ, actualI, (actualJ + 1), mineField);
                    break;
                default:
                    int j = actualJ - 1;
                    for (int i = 0; i < 2; i++) {
                        compareElements(actualI, actualJ, actualI, j, mineField);
                        j = actualJ + 1;
                    }
            }
        }
    }

    private void compareElements(int actualI, int actualJ, int rowI, int columnJ, Square[][] mineField) {
        if (mineField[rowI][columnJ].isMine()) {
            mineField[actualI][actualJ].setAdjacentMines(
                    (mineField[actualI][actualJ].getAdjacentMines() + 1));
        }
    }
}