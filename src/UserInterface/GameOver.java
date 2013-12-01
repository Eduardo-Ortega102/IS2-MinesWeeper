package UserInterface;

import java.awt.HeadlessException;
import javax.swing.*;

public class GameOver extends JFrame {

    public GameOver() throws HeadlessException {
        this.setTitle("Game Over");
    }
    
    public void execute(){
        this.setVisible(true);
    }

}
