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

    public static void print(Square[][] battleField) {
        int count = 0;
        for (int i = 0; i < battleField.length; i++) {
            System.out.print("(");
            for (int j = 0; j < battleField[i].length; j++) {
                if (battleField[i][j].isMine()) {
                    System.out.print(" T ");
                    count++;
                } else {
                    System.out.print(" " + battleField[i][j].getAdjacentMines() + " ");
                }
            }
            System.out.println(") ");
        }
        System.out.println("Numero de minas = " + count);
    }
}
