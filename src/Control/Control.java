package Control;

import Persistence.MineFieldLoader;
import Persistence.MineLoader;
import UserInterface.MinesWeeperMainFrame;

public class Control {

    public static void execute() {
        //OptionDialog dialog = new OptionDialog();
        MineLoader load = obtainLoader();
        try {
//            load.buildMineField(9, 9, 16, 1);
            load.buildMineField(10, 10, 16, 1);
        } catch (Exception ex) {
        }
        MinesWeeperMainFrame frame = new MinesWeeperMainFrame();
    }

    private static MineLoader obtainLoader() {
        return MineFieldLoader.getInstance();
    }
}
