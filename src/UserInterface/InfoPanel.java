package UserInterface;

import Control.Command;
import Model.Clock;
import Model.MineField;
import java.awt.*;
import javax.swing.*;

public class InfoPanel extends JPanel {

    private JPanel playedTime;
    private JPanel minesNumber;
    private JLabel playedLabel;
    private static Clock clock;

    public InfoPanel() {
        inicializeMinesNumber();
        inicializePlayedTime();
        this.add(playedTime);
        this.add(minesNumber);
        clock = new Clock(new Command<String>() {
            @Override
            public void executeCommand(String text) {
                refreshText(text);
            }
        });
    }

    public static void start() {
        clock.start();
    }

    public static void stop() {
        clock.stop();
    }

    private void inicializePlayedTime() {
        playedTime = new JPanel();
        playedTime.setSize(80, 60);
        playedTime.setBackground(Color.black);
        playedTime.add(createTimeLabel());
    }

    private void inicializeMinesNumber() {
        minesNumber = new JPanel();
        minesNumber.add(createLabel());
        minesNumber.setSize(80, 60);
        minesNumber.setBackground(Color.black);
    }

    private JLabel createLabel() {
        JLabel label = new JLabel();
        label.setText("Mines number: " + Integer.toString(MineField.getInstance().getMinesNumber()));
        label.setForeground(Color.red);
        return label;
    }

    private JLabel createTimeLabel() {
        playedLabel = new JLabel();
        refreshText("0");
        playedLabel.setForeground(Color.red);
        return playedLabel;
    }

    private void refreshText(String text) {
        playedLabel.setText("Played Time: " + text);
    }
}
