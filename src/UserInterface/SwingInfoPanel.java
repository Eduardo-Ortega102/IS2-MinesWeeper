package UserInterface;

import Model.MineField;
import UserInterface.AbstractInterface.InfoPanel;
import java.awt.*;
import javax.swing.*;

public final class SwingInfoPanel extends JPanel implements InfoPanel{

    private JPanel minesNumber;
    private static SwingTimer playedTime;

    public SwingInfoPanel() {
        playedTime = new SwingTimer(1000);
        minesNumber = null;
        this.add(playedTime);
        inicializeMinesNumber();
        this.add(minesNumber);
    }
    
    @Override
    public void startClock() {
        playedTime.start();
    }

    @Override
    public void stopClock() {
        playedTime.stop();
    }

    @Override
    public void resetClock() {
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
