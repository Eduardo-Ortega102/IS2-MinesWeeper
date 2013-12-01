package Persistence;

public interface MineLoader {
    public void buildMineField(int high, int width, int minesNumber, final int X) throws Exception;
    public void reBuildMineField(int high, int width, int minesNumber, final int X);
}
