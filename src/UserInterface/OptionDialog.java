package UserInterface;

import Control.Command;
import java.awt.*;
import java.awt.event.ActionEvent;
import java.awt.event.ActionListener;
import javax.swing.*;

public class OptionDialog extends JFrame {

    private static OptionDialog instance;
    private Command createCommand;
    private Command killCommand;
    private JRadioButton easyButton;
    private JRadioButton intermediateButton;
    private JRadioButton advancedButton;
    private JRadioButton personalizeButton;
    private JTextField rows;
    private JTextField column;
    private JTextField mines;

    private OptionDialog(Command createCommand, Command killCommand) throws HeadlessException {
        this.createCommand = createCommand;
        this.killCommand = killCommand;
        setTitle("Options");
        setDefaultCloseOperation(EXIT_ON_CLOSE);
        add(new Label("Select dificulty"), BorderLayout.NORTH);
        add(createToolbar(), BorderLayout.SOUTH);
        add(createMasterPanel(), BorderLayout.CENTER);
        pack();
    }

    public static OptionDialog getInstance() {
        return instance;
    }

    public static void createInstance(Command createCommand, Command killCommand) {
        if (instance == null) instance = new OptionDialog(createCommand, killCommand);
    }

    public static void execute() {
        instance.setVisible(true);
        instance.easyButton.doClick();
    }

    private JPanel createMasterPanel() {
        JPanel masterPanel = new JPanel();
        masterPanel.setLayout(new GridLayout(1, 2));
        masterPanel.add(createPanel(), 0);
        masterPanel.add(createPanel2(), 1);
        return masterPanel;
    }

    private JPanel createPanel2() {
        JPanel panel = new JPanel();
        panel.setLayout(new GridLayout(4, 1));
        createsPersonalizeButton();
        panel.add(personalizeButton);
        panel.add(rowPanel());
        panel.add(columnPanel());
        panel.add(minesPanel());
        return panel;
    }

    private JPanel createPanel() {
        JPanel panel = new JPanel();
        panel.setLayout(new GridLayout(4, 1));
        createsEasyCheckBox();
        createsIntermediateCheckBox();
        createsAdvancedCheckBox();
        panel.add(easyButton);
        panel.add(intermediateButton);
        panel.add(advancedButton);
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
        button.addActionListener(new ActionListener() {
            @Override
            public void actionPerformed(ActionEvent e) {
                createCommand.executeCommand(new int[]{
                    Integer.parseInt(rows.getText()),
                    Integer.parseInt(column.getText()),
                    Integer.parseInt(mines.getText())});
                setVisible(false);
            }
        });
        return button;
    }

    private JButton createCancelButton() {
        JButton button = new JButton("Cancel");
        button.addActionListener(new ActionListener() {
            @Override
            public void actionPerformed(ActionEvent e) {
                killCommand.executeCommand(0);
            }
        });
        return button;
    }

    private void createsEasyCheckBox() {
        easyButton = new JRadioButton("Easy level (16 mines, 9x9 squares)");
        easyButton.addActionListener(new ActionListener() {
            @Override
            public void actionPerformed(ActionEvent e) {
                intermediateButton.setSelected(false);
                advancedButton.setSelected(false);
                personalizeButton.setSelected(false);
                setParameters("9", "9", "16");
            }
        });
    }

    private void createsIntermediateCheckBox() {
        intermediateButton = new JRadioButton("Intermediate level (40 mines, 16x16 squares)");
        intermediateButton.addActionListener(new ActionListener() {
            @Override
            public void actionPerformed(ActionEvent e) {
                easyButton.setSelected(false);
                advancedButton.setSelected(false);
                personalizeButton.setSelected(false);
                setParameters("16", "16", "40");
            }
        });
    }

    private void createsAdvancedCheckBox() {
        advancedButton = new JRadioButton("Advanced level (99 mines, 16x30 squares)");
        advancedButton.addActionListener(new ActionListener() {
            @Override
            public void actionPerformed(ActionEvent e) {
                easyButton.setSelected(false);
                intermediateButton.setSelected(false);
                personalizeButton.setSelected(false);
                setParameters("16", "30", "99");
            }
        });
    }

    private void createsPersonalizeButton() {
        personalizeButton = new JRadioButton("Personalize");
        personalizeButton.addActionListener(new ActionListener() {
            @Override
            public void actionPerformed(ActionEvent e) {
                easyButton.setSelected(false);
                intermediateButton.setSelected(false);
                advancedButton.setSelected(false);
                rows.setEnabled(true);
                column.setEnabled(true);
                mines.setEnabled(true);
            }
        });
    }

    private JPanel rowPanel() {
        rows = new JTextField(10);
        JPanel panel = new JPanel();
        panel.setLayout(new FlowLayout(FlowLayout.RIGHT));
        panel.add(new Label("High (9-24): "));
        panel.add(rows);
        return panel;
    }

    private JPanel columnPanel() {
        column = new JTextField(10);
        JPanel panel = new JPanel();
        panel.setLayout(new FlowLayout(FlowLayout.RIGHT));
        panel.add(new Label("Width (9-30): "));
        panel.add(column);
        return panel;
    }

    private JPanel minesPanel() {
        mines = new JTextField(10);
        JPanel panel = new JPanel();
        panel.setLayout(new FlowLayout(FlowLayout.RIGHT));
        panel.add(new Label("Mines (10-668): "));
        panel.add(mines);
        return panel;
    }

    private void setParameters(String string, String string0, String string1) {
        rows.setEnabled(false);
        column.setEnabled(false);
        mines.setEnabled(false);
        rows.setText(string);
        column.setText(string0);
        mines.setText(string1);
    }
}
