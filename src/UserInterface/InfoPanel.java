package UserInterface;

import Model.MineField;
import java.awt.Color;
import java.awt.PopupMenu;
import javax.swing.JLabel;
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
        minesNumber.add(createLabel());
        minesNumber.setSize(80, 60);
        minesNumber.setBackground(Color.black);
    }

    private JLabel createLabel() {
        JLabel label = new JLabel();
        label.setText("Mines number: "+Integer.toString(MineField.getInstance().getMinesNumber()));
        label.setForeground(Color.red);
        return label;
    }

}
