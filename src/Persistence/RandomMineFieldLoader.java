package Persistence;

import Persistence.abstractInterface.MineFieldLoader;
import Model.MineField;
import Model.Square;
import java.util.Random;

public class RandomMineFieldLoader implements MineFieldLoader {

    private static MineField fieldInstance;
    private static RandomMineFieldLoader loaderInstance;
    private static int[] minesPerRow;
    private static int maxMinePerRow;

    private RandomMineFieldLoader() {
        maxMinePerRow = 1;
    }

    public static RandomMineFieldLoader getInstance() {
        if (loaderInstance == null)
            loaderInstance = new RandomMineFieldLoader();
        return loaderInstance;
    }

    private boolean checkParameters(int high, int width, int minesNumber) {
        if (high < 9 || high > 24) return false;
        if (width < 9 || width > 30) return false;
        if (minesNumber < 10 || minesNumber >= high * width || minesNumber > 668)
            return false;
        return true;
    }

    @Override
    public void buildMineField(int high, int width, int minesNumber) throws MineFieldBuilderException {
        if (!checkParameters(high, width, minesNumber)) throw new MineFieldBuilderException();
        MineField.createInstance(high, width, minesNumber);
        fieldInstance = MineField.getInstance();
        inicializeMinesPerRow(fieldInstance.getHigh());
        createMineField(fieldInstance.getMineField());
        mineFieldSetter(minesNumber, fieldInstance.getMineField());

        System.out.println("CAMPO RESULTANTE: ");
        fieldInstance.print();
    }

    private void inicializeMinesPerRow(int high) {
        minesPerRow = new int[high];
        for (int i = 0; i < minesPerRow.length; i++)
            minesPerRow[i] = 0;
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
                if (mineField[i][j].hasMine()) continue;
                if (minesPerRow[i] == maxMinePerRow) continue;

                mineField[i][j].setMine(getRandomMine(minesNumber));
                if (mineField[i][j].hasMine()) {
                    minesPerRow[i] = minesPerRow[i] + 1;
                    minesNumber--;
                    setAjacentMines(i, j, mineField);
                }
            }
            maxMinePerRow += 1;
        }
    }

    private boolean getRandomMine(int minesNumer) {
        if (minesNumer == 0) return false;
        return ((Math.random()) > (1.0 / 2)) ? false : true;
    }

    private void setAjacentMines(int i, int j, Square[][] mineField) {
        for (int k = i - 1; k <= i + 1; k++) 
            for (int m = j - 1; m <= j + 1; m++) 
                increaseAdjacentSquareValue(k, m, mineField);
    }

    private void increaseAdjacentSquareValue(int i, int j, Square[][] mineField) {
        if (i < 0 || i > mineField.length - 1) return;
        if (j < 0 || j > mineField[i].length - 1) return;
        if (mineField[i][j].hasMine()) return;

        mineField[i][j].setAdjacentMines(mineField[i][j].getAdjacentMines() + 1);
    }
}