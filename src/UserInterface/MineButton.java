package UserInterface;

import Model.Square;
import java.awt.event.ActionEvent;
import java.awt.event.ActionListener;
import javax.swing.*;

public class MineButton extends JButton {

    private int posX;
    private int posY;
    private final Square square;

    public MineButton(int posX, int posY, Square square) {
        super("   ");
        this.posX = posX;
        this.posY = posY;
        this.square = square;
        this.setEnabled(true);
        this.addActionListener(new ActionListener() {
            @Override
            public void actionPerformed(ActionEvent e) {
                addActions();
            }
        });
    }

    private void addActions() {
        if (this.square.isMine()) {
            MineFieldPanel.partIsOver();
        } else {
            MineFieldPanel.reLoad(posX, posY);
        }
    }

    public boolean showValue() {
        if (this.isEnabled() == false) return true;
        this.setEnabled(false);

        if (this.square.getAdjacentMines() != 0) {
            this.setText(Integer.toString(square.getAdjacentMines()));
            return true;
        }
        return false;
    }
    
}