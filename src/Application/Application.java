package Application;

import Control.Command;
import Model.MineField;
import Model.Square;
import Persistence.RandomMineFieldLoader;
import Persistence.MineFieldLoader;
import UserInterface.AbstractInterface.ActionFactory;
import UserInterface.AbstractInterface.GameOverDialog;
import UserInterface.AbstractInterface.MineFieldViewer;
import UserInterface.AbstractInterface.MineViewer;
import UserInterface.AbstractInterface.MineViewerFactory;
import UserInterface.AbstractInterface.OptionDialog;
import UserInterface.AbstractInterface.WinnerDialog;
import UserInterface.ActionListenerFactory;
import UserInterface.MinesWeeperMainFrame;
import UserInterface.SwingGameOverDialog;
import UserInterface.SwingMineFieldViewer;
import UserInterface.SwingMineViewer;
import UserInterface.SwingOptionDialog;
import UserInterface.SwingWinnerDialog;
import java.awt.event.ActionEvent;
import java.awt.event.ActionListener;
import java.util.HashMap;
import java.util.logging.Level;
import java.util.logging.Logger;

public class Application {

    private HashMap<String, Command> commandMap;
    private WinnerDialog winDialog;
    private GameOverDialog overDialog;
    private MinesWeeperMainFrame frame;
    private ActionListenerFactory factory;
    private final int minesPerRowAmount = 1;

    public static void main(String[] args) {
        new Application().execute();
    }

    private void execute() {
        MineFieldLoader loader = createLoader();

        OptionDialog optionDialog = createOptionInterface();
        factory = createActionListenerFactory();
        createCommands(optionDialog, loader);
        optionDialog.execute();
    }

    private MineFieldLoader createLoader() {
        return RandomMineFieldLoader.getInstance();
    }

    private OptionDialog createOptionInterface() {
        return new SwingOptionDialog(createActionListenerFactory());
    }

    private void createCommands(final OptionDialog optionDialog, final MineFieldLoader loader) {
        commandMap = new HashMap<>();
        final MineFieldViewer mineFieldViewer = createMineFieldViewer();
        frame = new MinesWeeperMainFrame(factory);
        commandMap.put("StartGame", new Command() {
            @Override
            public void executeCommand() {
                try {
                    optionDialog.reset();
                    loader.buildMineField(optionDialog.getRowsAmount(),
                            optionDialog.getColumnAmount(),
                            optionDialog.getMinesAmount(), 1);
                    mineFieldViewer.load(MineField.getInstance());
                    frame.setMinesViewer(mineFieldViewer);
                    frame.execute();
                } catch (Exception ex) {
                    Logger.getLogger(Application.class.getName()).log(Level.SEVERE, null, ex);
                }
            }
        });

        commandMap.put("PlayAgain", new Command() {
            @Override
            public void executeCommand() {
                winDialog.hidDialog();
                optionDialog.execute();
            }
        });

        commandMap.put("NewGame", new Command() {
            @Override
            public void executeCommand() {
                if (overDialog != null) overDialog.hidDialog();
                optionDialog.execute();
            }
        });

        commandMap.put("Restart", new Command() {
            @Override
            public void executeCommand() {
                System.out.println("NO USES ESTE BOTON AUUUUUUUUN");
                throw new UnsupportedOperationException("Not supported yet."); //To change body of generated methods, choose Tools | Templates.
            }
        });

        commandMap.put("Winner", new Command() {
            @Override
            public void executeCommand() {
                if (winDialog == null) winDialog = createWinnerDialog();
                winDialog.showDialog();
            }
        });

        commandMap.put("Loser", new Command() {
            @Override
            public void executeCommand() {
                if (overDialog == null) overDialog = createOverDialog();
                overDialog.showDialog();
            }
        });

        commandMap.put("Exit", new Command() {
            @Override
            public void executeCommand() {
                System.exit(0);
            }
        });
    }

    private MineFieldViewer createMineFieldViewer() {
        return new SwingMineFieldViewer(new MineViewerFactory() {
            @Override
            public MineViewer createMineViewer(int posX, int posY, Square square, ActionFactory factory) {
                return new SwingMineViewer(posX, posY, square, factory);
            }
        },
                factory);
    }

    private WinnerDialog createWinnerDialog() {
        return new SwingWinnerDialog(factory);
    }

    private GameOverDialog createOverDialog() {
        return new SwingGameOverDialog(factory);
    }

    private ActionListenerFactory createActionListenerFactory() {
        return new ActionListenerFactory() {
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
        };
    }
}
