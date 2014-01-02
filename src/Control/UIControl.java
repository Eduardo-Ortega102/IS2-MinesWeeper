//package Control;
//
//import UserInterface.GameOverDialog;
//import UserInterface.InfoPanel;
//import UserInterface.MineFieldPanel;
//import UserInterface.MinesWeeperMainFrame;
//import UserInterface.OptionDialog;
//import UserInterface.WinnerDialog;
//
//public class UIControl {
//
////    private static Command<Integer> dialogCommand;
////    private static Command<Integer> gameCommand;
////    private static Command<Integer> killCommand;
//    private static InfoPanel panel;
//    private static MineFieldPanel minesPanel;
//    private static MinesWeeperMainFrame mainFrame;
//    private static GameOverDialog gameOverDialog;
//    private static WinnerDialog winDialog;
//
//    public static void execute() {
////        dialogCommand = createDialogCommand();
////        gameCommand = createGameCommand();
////        killCommand = createKillCommand();
//        panel = new InfoPanel();
////        minesPanel = new MineFieldPanel(gameCommand);
//        mainFrame = new MinesWeeperMainFrame(panel, minesPanel);
//        mainFrame.execute();
//    }
//
//    private static void gameOver() {
//        InfoPanel.stop();
//        gameOverDialog = new GameOverDialog(dialogCommand);
//        gameOverDialog.execute();
//    }
//
//    private static void youWin() {
//        InfoPanel.stop();
//        winDialog = new WinnerDialog();
//        winDialog.execute(dialogCommand);
//    }
//
//    private static Command<Integer> createDialogCommand() {
//        return new Command<Integer>() {
//            @Override
//            public void executeCommand(Integer option) {
//                if (option == 3) {
////                    OptionDialog.execute();
//                }
//                killCommand.executeCommand(option);
//            }
//        };
//    }
//
//    private static Command<Integer> createGameCommand() {
//        return new Command<Integer>() {
//            @Override
//            public void executeCommand(Integer option) {
//                if (option == 0) {
//                    gameOver();
//                } else {
//                    youWin();
//                }
//            }
//        };
//    }
//
//    public static Command<Integer> getKillCommand() {
//        return killCommand;
//    }
//    
//
//    private static Command<Integer> createKillCommand() {
//        return new Command<Integer>() {
//            @Override
//            public void executeCommand(Integer parameters) {
//                kill(parameters);
//            }
//        };
//    }
//    
//    private static void kill(int flag) {
//        if(mainFrame != null) mainFrame.dispose();
//        if(gameOverDialog != null) gameOverDialog.dispose();
//        if(winDialog != null) winDialog.dispose();
////        if(flag == 0) OptionDialog.getInstance().dispose();
//    }
//
//}
