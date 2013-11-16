package UserInterface;

import java.awt.*;
import javax.swing.*;

public class OptionDialog extends JFrame {

    public OptionDialog() throws HeadlessException {
        this.setTitle("Options");
        this.setDefaultCloseOperation(EXIT_ON_CLOSE);
        this.add(createToolbar(), BorderLayout.SOUTH);
        this.add(createPanel(), BorderLayout.CENTER);
        this.pack();
        this.setVisible(true);
    }

    private JPanel createPanel() {
        JPanel panel = new JPanel();
        panel.setLayout(new GridLayout(4, 1));
        panel.add(createsEasyCheckBox());
        panel.add(createsIntermediateCheckBox());
        panel.add(createsAdvancedCheckBox());
        return panel;
    }

    private JPanel createToolbar() {
        JPanel panel = new JPanel();
        panel.setLayout(new FlowLayout(FlowLayout.RIGHT));
        panel.add(createOkButton());
        panel.add(createCancelButton());
        return panel;
    }

    private JButton createOkButton() {
        JButton button = new JButton("OK");
        return button;
    }

    private JButton createCancelButton() {
        JButton button = new JButton("Cancel");
        return button;
    }

    private JRadioButton createsEasyCheckBox() {
        JRadioButton box = new JRadioButton("Easy level (15 mines, 9x9 squares)");
        return box;
    }

    private JRadioButton createsIntermediateCheckBox() {
        JRadioButton box = new JRadioButton("Intermediate level (45 mines, 16x16 squares)");
        return box;
    }

    private JRadioButton createsAdvancedCheckBox() {
        JRadioButton box = new JRadioButton("Advanced level (105 mines, 16x30 squares)");
        return box;
    }
}
