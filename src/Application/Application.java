package Application;

import Control.Command;
import Persistence.MineFieldLoader;
import Persistence.MineLoader;
import UserInterface.AbstractInterface.OptionInterface;
import UserInterface.ActionListenerFactory;
import UserInterface.OptionDialog;
import java.awt.event.ActionEvent;
import java.awt.event.ActionListener;
import java.util.HashMap;
import java.util.logging.Level;
import java.util.logging.Logger;

public class Application {

    private static HashMap<String, Command> commandMap;
    private static final int minesPerRowAmount = 1;

    public static void main(String[] args) {
        MineLoader load = createLoader();
        OptionInterface optionDialog = createOptionInterface();
        createCommands(optionDialog, load);
    }

    private static MineLoader createLoader() {
        return MineFieldLoader.getInstance();
    }

    private static OptionInterface createOptionInterface() {
        return new OptionDialog(new ActionListenerFactory() {
            @Override
            public ActionListener createActionListener(final String action) {
                return new ActionListener() {
                    @Override
                    public void actionPerformed(ActionEvent e) {
                        Command command = commandMap.get(action);
                        if (command != null) command.executeCommand();
                    }
                };
            }
        });
    }

    private static void createCommands(final OptionInterface optionDialog, final MineLoader load) {
        commandMap = new HashMap<>();
        commandMap.put("StartGame", new Command() {
            @Override
            public void executeCommand() {
                try {
                    System.out.println("looooooooooooooooooooooooooool");
//                    load.buildMineField(
//                            optionDialog.getRowsAmount(),
//                            optionDialog.getColumnAmount(),
//                            optionDialog.getMinesAmount(),
//                            minesPerRowAmount
//                            );
                } catch (Exception ex) {
                    Logger.getLogger(Application.class.getName()).log(Level.SEVERE, null, ex);
                }
            }
        });

        commandMap.put("Exit", new Command() {
            @Override
            public void executeCommand() {
                System.exit(0);
            }
        });
    }
}
