package Persistence.abstractInterface;

import Persistence.MineFieldBuilderException;

public interface MineFieldLoader {

    public void buildMineField(int high, int width, int minesNumber) throws MineFieldBuilderException;
}
