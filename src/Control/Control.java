package Control;

import Persistence.MineFieldLoader;
import UserInterface.MinesWeeperMainFrame;

public class Control {

    public static void execute() {
        //OptionDialog dialog = new OptionDialog();
//        MineFieldLoader.built(24, 30, 160, 3);
        MineFieldLoader.builtMineField(9, 9, 16, 1);
        MinesWeeperMainFrame frame = new MinesWeeperMainFrame();
    }
}
