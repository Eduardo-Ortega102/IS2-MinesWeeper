package Application;

import Control.Command;
import Model.MineField;
import Model.Square;
import Persistence.RandomMineFieldLoader;
import Persistence.MineFieldLoader;
import UserInterface.AbstractInterface.Action;
import UserInterface.AbstractInterface.ActionFactory;
import UserInterface.AbstractInterface.GameOverDialog;
import UserInterface.AbstractInterface.HelpDialog;
import UserInterface.AbstractInterface.InfoPanel;
import UserInterface.AbstractInterface.InfoPanelFactory;
import UserInterface.AbstractInterface.MineFieldViewer;
import UserInterface.AbstractInterface.MineViewer;
import UserInterface.AbstractInterface.MineViewerFactory;
import UserInterface.AbstractInterface.OptionDialog;
import UserInterface.AbstractInterface.WinnerDialog;
import UserInterface.ActionListenerFactory;
import UserInterface.ErrorDialog;
import UserInterface.SwingInfoPanel;
import UserInterface.MinesWeeperMainFrame;
import UserInterface.SwingGameOverDialog;
import UserInterface.SwingHelpDialog;
import UserInterface.SwingMineFieldViewer;
import UserInterface.SwingMineViewer;
import UserInterface.SwingOptionDialog;
import UserInterface.SwingWinnerDialog;
import java.awt.event.ActionEvent;
import java.awt.event.ActionListener;
import java.util.HashMap;

public class Application {

    private HashMap<String, Command> commandMap;
    private HashMap<String, UserInterface.AbstractInterface.Action> actionMap;
    private ActionListenerFactory actionListenerFactory;
    private MineFieldLoader loader;
    private MineFieldViewer mineFieldViewer;
    private OptionDialog optionDialog;
    private WinnerDialog winDialog;
    private GameOverDialog overDialog;
    private HelpDialog helpDialog;
    private MinesWeeperMainFrame applicationFrame;
    private int firstTime;

    public static void main(String[] args) {
        new Application().execute();
    }

    private void execute() {
        loader = createLoader();
        actionListenerFactory = createActionListenerFactory();
        optionDialog = createOptionDialog();
        mineFieldViewer = createMineFieldViewer();
        createApplicationFrame();
        createCommands();
        createActions();
        optionDialog.execute();
    }

    private MineFieldLoader createLoader() {
        return RandomMineFieldLoader.getInstance();
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

    private OptionDialog createOptionDialog() {
        return new SwingOptionDialog(actionListenerFactory);
    }

    private MineFieldViewer createMineFieldViewer() {
        return new SwingMineFieldViewer(createMineViewerFactory(), createActionFactory());
    }

    private MineViewerFactory createMineViewerFactory() {
        return new MineViewerFactory() {
            @Override
            public MineViewer createMineViewer(int posX, int posY, Square square, ActionFactory factory) {
                return new SwingMineViewer(posX, posY, square, factory);
            }
        };
    }

    private ActionFactory createActionFactory() {
        return new ActionFactory() {
            @Override
            public UserInterface.AbstractInterface.Action getAction(final String action) {
                return new Action() {
                    @Override
                    public void execute(int x, int y) {
                        Action actionM = actionMap.get(action);
                        if (actionM != null) actionM.execute(x, y);
                    }
                };
            }
        };
    }

    private void createApplicationFrame() {
        applicationFrame = new MinesWeeperMainFrame(actionListenerFactory, createInfoPanelFactory());
    }

    private InfoPanelFactory createInfoPanelFactory() {
        return new InfoPanelFactory() {
            @Override
            public InfoPanel createInfoPanel() {
                return new SwingInfoPanel();
            }
        };
    }

    private void createCommands() {
        commandMap = new HashMap<>();
        commandMap.put("StartGame", new Command() {
            @Override
            public void executeCommand() {
                optionDialog.reset();
                runApplication();
            }
        });

        commandMap.put("PlayAgain", new Command() {
            @Override
            public void executeCommand() {
                winDialog.hidDialog();
                runApplication();
            }
        });

        commandMap.put("Options", new Command() {
            @Override
            public void executeCommand() {
                optionDialog.execute();
            }
        });

        commandMap.put("NewGame", new Command() {
            @Override
            public void executeCommand() {
                if (overDialog != null) overDialog.hidDialog();
                runApplication();
            }
        });

        commandMap.put("Help", new Command() {
            @Override
            public void executeCommand() {
                if (helpDialog == null) helpDialog = createHelpDialog();
                helpDialog.showDialog();
            }
        });

        commandMap.put("Restart", new Command() {
            @Override
            public void executeCommand() {
                overDialog.hidDialog();
                mineFieldViewer.restart();
                applicationFrame.getInfoPanel().resetClock();
                firstTime = 0;
            }
        });

        commandMap.put("Exit", new Command() {
            @Override
            public void executeCommand() {
                System.exit(0);
            }
        });
    }

    private void createActions() {
        actionMap = new HashMap<>();

        actionMap.put("Winner", new UserInterface.AbstractInterface.Action() {
            @Override
            public void execute(int x, int y) {
                if (winDialog == null) winDialog = createWinnerDialog();
                winDialog.showDialog();
                applicationFrame.getInfoPanel().stopClock();
            }
        });

        actionMap.put("partIsOver", new UserInterface.AbstractInterface.Action() {
            @Override
            public void execute(int x, int y) {
                if (overDialog == null) overDialog = createOverDialog();
                overDialog.showDialog();
                applicationFrame.getInfoPanel().stopClock();
            }
        });

        actionMap.put("reLoad", new UserInterface.AbstractInterface.Action() {
            @Override
            public void execute(int x, int y) {
                mineFieldViewer.reLoad(x, y);
                if (firstTime == 0) applicationFrame.getInfoPanel().startClock();
                firstTime = 1;
            }
        });
    }

    private void runApplication() {
        firstTime = 0;
        try {
            loader.buildMineField(optionDialog.getRowsAmount(),
                    optionDialog.getColumnAmount(),
                    optionDialog.getMinesAmount());
            mineFieldViewer.load(MineField.getInstance());
            applicationFrame.setMinesViewer(mineFieldViewer);
            applicationFrame.execute();
        } catch (Exception ex) {
            HelpDialog errorDialog = createErrorDialog(ex.getMessage());
            errorDialog.showDialog();
            optionDialog.execute();
        }
    }

    private WinnerDialog createWinnerDialog() {
        return new SwingWinnerDialog(actionListenerFactory);
    }

    private GameOverDialog createOverDialog() {
        return new SwingGameOverDialog(actionListenerFactory);
    }

    private HelpDialog createHelpDialog() {
        return new SwingHelpDialog();
    }

    private HelpDialog createErrorDialog(String message) {
        return new ErrorDialog(message);
    }
}
