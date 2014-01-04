package UserInterface;

import Model.MineField;
import java.awt.*;
import javax.swing.*;

public class InfoPanel extends JPanel {

    private JPanel minesNumber;
    private static SwingTimer playedTime;

    public InfoPanel() {
        inicializeMinesNumber();
        playedTime = new SwingTimer(1000);
        this.add(playedTime);
        this.add(minesNumber);
    }

    public static void start() {
        playedTime.start();
    }

    public static void stop() {
        playedTime.stop();
    }

    public static void reset() {
        playedTime.reset();
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
}
