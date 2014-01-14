package UserInterface;

import UserInterface.AbstractInterface.SquareViewer;
import UserInterface.AbstractInterface.ActionFactory;
import Model.Square;
import java.awt.event.ActionEvent;
import java.awt.event.ActionListener;
import java.awt.event.MouseEvent;
import java.awt.event.MouseListener;
import javax.swing.*;

public class SwingSquareViewer extends JButton implements SquareViewer {

    private int posX;
    private int posY;
    private final Square square;
    private final ActionFactory factory;

    public SwingSquareViewer(int posX, int posY, Square square, final ActionFactory factory) {
        super("   ");
        this.factory = factory;
        this.posX = posX;
        this.posY = posY;
        this.square = square;
        this.setEnabled(true);
        this.addActions();
    }

    private void addActions() {
        this.addActionListener(new ActionListener() {
            @Override
            public void actionPerformed(ActionEvent e) {
                if (square.hasMine())
                    factory.getAction("GameOver").execute(0, 0);
                else
                    factory.getAction("showField").execute(posX, posY);
            }
        });

        this.addMouseListener(new MouseListener() {
            private boolean pressed = false;

            @Override
            public void mouseClicked(MouseEvent e) {
            }

            @Override
            public void mousePressed(MouseEvent e) {
                pressed = true;
                factory.getAction("MineViewerSelected").execute(0, 0);
            }

            @Override
            public void mouseReleased(MouseEvent e) {
                pressed = false;
                factory.getAction("MineViewerUnselected").execute(0, 0);
            }

            @Override
            public void mouseEntered(MouseEvent e) {
                if (pressed) factory.getAction("MineViewerSelected").execute(0, 0);
            }

            @Override
            public void mouseExited(MouseEvent e) {
                factory.getAction("MineViewerUnselected").execute(0, 0);
            }
        });
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
        return !this.isEnabled();
    }

    @Override
    public void reset() {
        this.setText("");
        this.setEnabled(true);
    }
}
