package Control;

import Persistence.MineLoader;
import UserInterface.OptionDialog;

public class Control {

    private static MineLoader load;
    private static Command<int[]> controlCommand;

    public static void execute(MineLoader load) {
        Control.load = load;
        controlCommand = new Command<int[]>() {
            @Override
            public void executeCommand(int[] parameter) {
                start(parameter[0], parameter[1], parameter[2]);
            }
        };
        
        OptionDialog.createInstance(controlCommand, UIControl.getKillCommand());
        OptionDialog.execute();
    }

    public static void start(int rows, int columns, int mines) {
        try {
            load.buildMineField(rows, columns, mines, 1);
            UIControl.execute();
        } catch (Exception ex) {
        }
    }
}
