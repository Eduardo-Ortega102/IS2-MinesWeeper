package UserInterface;

import UserInterface.AbstractInterface.Dialog;
import java.awt.HeadlessException;
import javax.swing.JFrame;
import javax.swing.JOptionPane;

public class ErrorDialog extends JFrame implements Dialog {

    private final String text;

    public ErrorDialog(String message) throws HeadlessException {
        super();
        this.text = message;
    }

    @Override
    public void showDialog() {
        JOptionPane.showMessageDialog(this, text, "Mines Weeper Error", JOptionPane.ERROR_MESSAGE);
    }

    @Override
    public void hidDialog() {
    }
}
