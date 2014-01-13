package Model;

public class MineField {

    private static MineField mineFieldInstance;
    private static Square[][] mineField;
    private static int minesNumber;
    private static int high;
    private static int width;

    private MineField() {
    }

    public static void createInstance(int high, int width, int minesNumber) {
        if (mineFieldInstance == null) {
            mineFieldInstance = new MineField();
        }
        MineField.minesNumber = minesNumber;
        MineField.high = high;
        MineField.width = width;
        MineField.mineField = new Square[high][width];
    }

    public static MineField getInstance() {
        return mineFieldInstance;
    }

    public Square[][] getMineField() {
        return MineField.mineField;
    }

    public int getMinesNumber() {
        return minesNumber;
    }

    public int getHigh() {
        return high;
    }

    public int getWidth() {
        return width;
    }

    public void print() {
        int count = 0;
        for (int i = 0; i < mineField.length; i++) {
            System.out.print("(");
            for (int j = 0; j < mineField[i].length; j++) {
                if (mineField[i][j].hasMine()) {
                    System.out.print(" T ");
                    count++;
                } else {
                    System.out.print(" " + mineField[i][j].getAdjacentMines() + " ");
                }
            }
            System.out.println(") ");
        }
        System.out.println("Numero de minas = " + count);
    }
}
