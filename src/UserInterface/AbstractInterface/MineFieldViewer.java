package UserInterface.AbstractInterface;

import Model.MineField;

public interface MineFieldViewer {

    public void load(MineField mineField);

    public void showField(int x, int y);

    public void restart();
}
