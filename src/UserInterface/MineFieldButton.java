package UserInterface;

import Model.Square;
import java.awt.event.ActionEvent;
import java.awt.event.ActionListener;
import javax.swing.*;

public class MineFieldButton extends JButton {

    private int posX;
    private int posY;
    private final Square square;

    public MineFieldButton(int posX, int posY, Square square) {
        super(" ");
        this.posX = posX;
        this.posY = posY;
        this.square = square;
        this.setSize(30, 30);//------------------------¿HACE ALGO ESTA LINEA?
        this.addActionListener(new ActionListener() {
            @Override
            public void actionPerformed(ActionEvent e) {
                checkAction();
            }
        });
    }

    public int getPosX() {
        return posX;
    }

    public int getPosY() {
        return posY;
    }

    private void checkAction() {
        if (this.square.isMine()) {
            gameOver();
        } else {
            MineFieldViewer.reLoad(posX, posY);
        }
    }
    
    public boolean isNotMine(){
        return (this.square.isMine())? false:true;
    }

    public boolean showValue() {
        boolean value = true;
        if (this.square.getAdjacentMines() != 0) {
            this.setText(Integer.toString(square.getAdjacentMines()));
            value = false;
        }
        this.setEnabled(false);
        return value;
    }

    private void gameOver() {
        System.out.println("ARREGLA ESTE METODO");
    }
}
