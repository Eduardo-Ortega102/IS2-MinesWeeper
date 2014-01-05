package UserInterface;

import Model.MineField;
import UserInterface.AbstractInterface.ImageViewer;
import UserInterface.AbstractInterface.InfoPanel;
import java.awt.*;
import javax.swing.*;

public final class SwingInfoPanel extends JPanel implements InfoPanel {

    private JPanel minesNumber;
    private ImageViewer imageViewer;
    private static SwingTimer playedTime;

    public SwingInfoPanel(ImageViewer viewer) {
        playedTime = new SwingTimer(1000);
        minesNumber = null;
        imageViewer = viewer;
        this.add(playedTime);
        this.add((Component) imageViewer);
    }

    @Override
    public void refresh() {
        if (minesNumber != null) this.remove(minesNumber);
        this.resetClock();
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

    @Override
    public ImageViewer getImageViewer() {
        return this.imageViewer;
    }
}
