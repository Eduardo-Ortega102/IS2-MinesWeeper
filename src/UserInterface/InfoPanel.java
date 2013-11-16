package UserInterface;

import java.awt.Color;
import javax.swing.JPanel;

public class InfoPanel extends JPanel {

    private JPanel playedTime;
    private JPanel minesNumber;

    public InfoPanel() {
        inicializeMinesNumber();
        inicializePlayedTime();
        this.add(playedTime);
        this.add(minesNumber);
    }

    private void inicializePlayedTime() {
        playedTime = new JPanel();
        playedTime.setSize(80, 60);
        playedTime.setBackground(Color.black);
    }

    private void inicializeMinesNumber() {
        minesNumber = new JPanel();
        minesNumber.setSize(80, 60);
        minesNumber.setBackground(Color.black);
    }

}
