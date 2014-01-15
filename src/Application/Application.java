package Application;

import Control.Command;
import Control.ImagePriority;
import Control.ImageViewerControl;
import Control.ImageViewerControlFactory;
import Control.ShowCommand;
import Model.abstractInterface.Bitmap;
import Model.MineField;
import Model.Square;
import Persistence.FileImageSetLoader;
import Persistence.MineFieldBuilderException;
import Persistence.RandomMineFieldBuilder;
import Persistence.abstractInterface.BitmapFactory;
import Persistence.abstractInterface.ImageSetLoader;
import Persistence.abstractInterface.MineFieldBuilder;
import UserInterface.AbstractInterface.Action;
import UserInterface.AbstractInterface.ActionFactory;
//import UserInterface.AbstractInterface.GameOverDialog;
//import UserInterface.AbstractInterface.HelpDialog;
import UserInterface.AbstractInterface.ImageViewer;
import UserInterface.AbstractInterface.InfoPanel;
import UserInterface.AbstractInterface.MineFieldViewer;
import UserInterface.AbstractInterface.SquareViewer;
import UserInterface.AbstractInterface.SquareViewerFactory;
import UserInterface.AbstractInterface.OptionDialog;
import UserInterface.AbstractInterface.Dialog;
import UserInterface.ActionListenerFactory;
import UserInterface.ErrorDialog;
import UserInterface.SwingInfoPanel;
import UserInterface.MinesWeeperMainFrame;
import UserInterface.PanelImageViewer;
import UserInterface.SwingBitmap;
import UserInterface.SwingGameOverDialog;
import UserInterface.SwingHelpDialog;
import UserInterface.SwingMineFieldViewer;
import UserInterface.SwingSquareViewer;
import UserInterface.SwingOptionDialog;
import UserInterface.SwingWinnerDialog;
import java.awt.event.ActionEvent;
import java.awt.event.ActionListener;
import java.util.HashMap;

public class Application {

    private HashMap<String, Command> commandMap;
    private ActionListenerFactory actionListenerFactory;
    private MineFieldBuilder mineFieldBuilder;
    private MineFieldViewer mineFieldViewer;
    private OptionDialog optionDialog;
    private Dialog winDialog;
    private Dialog gameOverDialog;
    private MinesWeeperMainFrame applicationFrame;
    private ImageViewerControlFactory imageViewerControlFactory;
    private ImageViewerControl imageViewerControl;
    private boolean firstTime;

    public static void main(String[] args) {
        new Application().execute();
    }

    private void execute() {
        ImageSetLoader imageSetLoader = createImageSetLoader();
        imageSetLoader.loadImageSet();
        mineFieldBuilder = createMineFieldBuilder();
        actionListenerFactory = createActionListenerFactory();
        optionDialog = createOptionDialog();
        mineFieldViewer = createMineFieldViewer();
        ImageViewer imageViewer = createImageViewer();
        createApplicationFrame(imageViewer);
        imageViewerControlFactory = createImageViewerControlFactory();
        imageViewerControl = createImageViewerControl(imageViewer);
        createCommands();
        optionDialog.execute();
    }

    private ImageViewerControlFactory createImageViewerControlFactory() {
        return new ImageViewerControlFactory() {
            @Override
            public ImageViewerControl createImageViewerControl(ImageViewer viewer) {
                return new ImageViewerControl(viewer, createHashMap());
            }

            private HashMap<String, ImagePriority> createHashMap() {
                final HashMap<String, ImagePriority> priority = new HashMap<>();
                priority.put("winnerIcon.jpg", ImagePriority.HIGH);
                priority.put("loserIcon.jpg", ImagePriority.HIGH);
                priority.put("waitIcon.jpg", ImagePriority.LOW);
                priority.put("moveIcon.jpg", ImagePriority.LOW);
                return priority;
            }
        };
    }

    private ImageViewerControl createImageViewerControl(ImageViewer viewer) {
        return imageViewerControlFactory.createImageViewerControl(viewer);
    }

    private ImageSetLoader createImageSetLoader() {
        return new FileImageSetLoader(
                "src\\Icons",
                new String[]{"winnerIcon.jpg", "waitIcon.jpg", "moveIcon.jpg", "loserIcon.jpg"},
                createBitmapFactory());
    }

    private BitmapFactory createBitmapFactory() {
        return new BitmapFactory<String>() {
            @Override
            public Bitmap createBitmap(String input) {
                return new SwingBitmap(input);
            }
        };
    }

    private MineFieldBuilder createMineFieldBuilder() {
        return RandomMineFieldBuilder.getInstance();
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
        return new SwingMineFieldViewer(createSquareViewerFactory(), createActionFactory());
    }

    private SquareViewerFactory createSquareViewerFactory() {
        return new SquareViewerFactory() {
            @Override
            public SquareViewer createSquareViewer(int posX, int posY, Square square, ActionFactory factory) {
                return new SwingSquareViewer(posX, posY, square, factory);
            }
        };
    }

    private ActionFactory createActionFactory() {
        return new ActionFactory() {
            @Override
            public Action getAction(final String action) {
                return new Action() {
                    @Override
                    public void execute(int x, int y) {
                        Command command= commandMap.get(action);
                        if (command != null)
                            if (checkCommand(command)) 
                                ((ShowCommand) command).execute(x, y);
                            else
                                command.executeCommand();
                    }

                    private boolean checkCommand(Command command) {
                        return (command instanceof ShowCommand);
                    }
                };
            }
        };
    }

    private ImageViewer createImageViewer() {
        return new PanelImageViewer();
    }

    private void createApplicationFrame(ImageViewer viewer) {
        applicationFrame = new MinesWeeperMainFrame(actionListenerFactory, createInfoPanel(viewer));
    }

    private InfoPanel createInfoPanel(ImageViewer viewer) {
        return new SwingInfoPanel(viewer);
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
                if (gameOverDialog != null) gameOverDialog.hidDialog();
                runApplication();
            }
        });

        commandMap.put("Help", new Command() {
            @Override
            public void executeCommand() {
                Dialog helpD = createHelpDialog();
                helpD.showDialog();
            }
        });

        commandMap.put("Restart", new Command() {
            @Override
            public void executeCommand() {
                gameOverDialog.hidDialog();
                mineFieldViewer.restart();
                imageViewerControl.reset();
                imageViewerControl.viewImage("waitIcon.jpg");
                applicationFrame.getInfoPanel().resetClock();
                firstTime = true;
            }
        });

        commandMap.put("Winner", new Command() {
            @Override
            public void executeCommand() {
                if (winDialog == null) winDialog = createWinnerDialog();
                winDialog.showDialog();
                imageViewerControl.viewImage("winnerIcon.jpg");
                applicationFrame.getInfoPanel().stopClock();
            }
        });

        commandMap.put("GameOver", new Command() {
            @Override
            public void executeCommand() {
                if (gameOverDialog == null) gameOverDialog = createOverDialog();
                gameOverDialog.showDialog();
                applicationFrame.getInfoPanel().stopClock();
                imageViewerControl.viewImage("loserIcon.jpg");
            }
        });

        commandMap.put("SquareViewerSelected", new Command() {

            @Override
            public void executeCommand() {
                imageViewerControl.viewImage("moveIcon.jpg");
            }
        });

        commandMap.put("SquareViewerUnselected", new Command() {

            @Override
            public void executeCommand() {
                imageViewerControl.viewImage("waitIcon.jpg");
            }
        });
        
        commandMap.put("showField", new ShowCommand() {
            @Override
            public void execute(int x, int y) {
                mineFieldViewer.showField(x, y);
                if (firstTime) {
                    applicationFrame.getInfoPanel().startClock();
                    firstTime = false;
                }
            }

            @Override
            public void executeCommand() {
            }
        });
        
        commandMap.put("Exit", new Command() {
            @Override
            public void executeCommand() {
                System.exit(0);
            }
        });
    }

    private void runApplication() {
        imageViewerControl.reset();
        imageViewerControl.viewImage("waitIcon.jpg");
        firstTime = true;
        try {
            mineFieldBuilder.buildMineField(optionDialog.getRowsAmount(),
                    optionDialog.getColumnAmount(),
                    optionDialog.getMinesAmount());
            mineFieldViewer.load(MineField.getInstance());
            applicationFrame.setMinesViewer(mineFieldViewer);
            applicationFrame.execute();
        } catch (MineFieldBuilderException ex) {
            Dialog errorDialog = createErrorDialog(ex.getMessage());
            errorDialog.showDialog();
            optionDialog.execute();
        }
    }

    private Dialog createWinnerDialog() {
        return new SwingWinnerDialog(actionListenerFactory);
    }

    private Dialog createOverDialog() {
        return new SwingGameOverDialog(actionListenerFactory);
    }

    private Dialog createHelpDialog() {
        return new SwingHelpDialog();
    }

    private Dialog createErrorDialog(String message) {
        return new ErrorDialog(message);
    }
}
