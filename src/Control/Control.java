package Control;

import Persistence.MineFieldLoader;
import Persistence.MineLoader;
import UserInterface.MinesWeeperMainFrame;

public class Control {

    public static void execute() {
        //OptionDialog dialog = new OptionDialog();
//        MineFieldLoader.built(24, 30, 160, 3);
        MineLoader load = obtainLoader();
        try {
            load.buildMineField(9, 9, 16, 1);
        } catch (Exception ex) {
        }
        MinesWeeperMainFrame frame = new MinesWeeperMainFrame();
    }

    private static MineLoader obtainLoader() {
        return MineFieldLoader.getInstance();
//        return new MineLoader(){
//
//            @Override
//            public void buildMineField(int high, int width, int minesNumber, int X) throws Exception {
//                throw new UnsupportedOperationException("Not supported yet."); //To change body of generated methods, choose Tools | Templates.
//            }
//
//            @Override
//            public void reBuildMineField(int high, int width, int minesNumber, int X) {
//                throw new UnsupportedOperationException("Not supported yet."); //To change body of generated methods, choose Tools | Templates.
//            }
//            
//        };
                
    }
}
