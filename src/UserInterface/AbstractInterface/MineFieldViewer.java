package UserInterface.AbstractInterface;

import Model.MineField;

public interface MineFieldViewer {

    public void load(MineField mineField);

    public void restart();

    public void reLoad(int x, int y);

}
