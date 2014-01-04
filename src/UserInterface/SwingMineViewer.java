package UserInterface;

import UserInterface.AbstractInterface.MineViewer;
import Model.Square;
import UserInterface.AbstractInterface.ActionFactory;
import java.awt.event.ActionEvent;
import java.awt.event.ActionListener;
import javax.swing.*;

public class SwingMineViewer extends JButton implements MineViewer {

    private int posX;
    private int posY;
    private final Square square;
    private final ActionFactory factory;

    public SwingMineViewer(int posX, int posY, Square square, ActionFactory factory) {
        super("   ");
        this.factory = factory;
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
            factory.getAction("partIsOver").execute(0, 0);
        } else {
            factory.getAction("reLoad").execute(posX, posY);
        }
    }

    @Override
    public boolean showValue() {
        if (this.isEnabled() == false) return true;
        this.setEnabled(false);

        if (this.square.getAdjacentMines() != 0) {
            this.setText(Integer.toString(square.getAdjacentMines()));
            return true;
        }
        return false;
    }

    @Override
    public boolean isShowed() {
        return this.isEnabled();
    }

    @Override
    public void reset() {
        this.setText("");
        this.setEnabled(true);
    }
}
