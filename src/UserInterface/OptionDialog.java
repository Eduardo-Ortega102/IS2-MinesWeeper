package UserInterface;

import UserInterface.AbstractInterface.OptionInterface;
import javax.swing.*;
import java.awt.*;
import java.awt.event.ActionEvent;
import java.awt.event.ActionListener;
import java.awt.event.KeyEvent;
import java.awt.event.KeyListener;

public class OptionDialog extends JFrame implements OptionInterface {

    private ActionListenerFactory factory;
    private JRadioButton personalizeButton;
    private JRadioButton[] defaultOptionButtons;
    private JTextField[] optionTextFields;
    private int rowsAmount;
    private int columnAmount;
    private int minesAmount;

    public OptionDialog(ActionListenerFactory factory) throws HeadlessException {
        super("Options");
        this.factory = factory;
        this.setDefaultCloseOperation(EXIT_ON_CLOSE);
        this.createComponents();
        this.pack();
        this.setVisible(true);
        this.defaultOptionButtons[0].doClick();
    }

    public void execute() {
        this.setVisible(true);
//        this.easyButton.doClick();
        this.defaultOptionButtons[0].doClick();
    }

    private void createComponents() {
        this.add(new Label("Select dificulty"), BorderLayout.NORTH);
        this.add(createToolbar(), BorderLayout.SOUTH);
        this.add(createMainPanel(), BorderLayout.CENTER);
    }

    private JPanel createToolbar() {
        JPanel panel = new JPanel();
        panel.setLayout(new FlowLayout(FlowLayout.RIGHT));
        panel.add(createToolBarButton("Start Game", "StartGame"));
        panel.add(createToolBarButton("Cancel", "Exit"));
        return panel;
    }

    private JButton createToolBarButton(String label, String action) {
        JButton button = new JButton(label);
        button.addActionListener(this.factory.createActionListener(action));
        return button;
    }

    private JPanel createMainPanel() {
        JPanel masterPanel = new JPanel();
        masterPanel.setLayout(new GridLayout(1, 2));
        masterPanel.add(createDefaultOptionsPanel(), 0);
        masterPanel.add(createPersonalizePanel(), 1);
        return masterPanel;
    }

    private JPanel createDefaultOptionsPanel() {
        JPanel panel = new JPanel();
        panel.setLayout(new GridLayout(4, 1));
        this.defaultOptionButtons = createDefaultOptionButtons();
        for (JRadioButton jRadioButton : defaultOptionButtons)
            panel.add(jRadioButton);
        return panel;
    }

    private JRadioButton[] createDefaultOptionButtons() {
        return new JRadioButton[]{
            createOptionButton("Easy level (16 mines, 9x9 squares)", 0, 9, 9, 16),
            createOptionButton("Intermediate level (40 mines, 16x16 squares)", 1, 16, 16, 40),
            createOptionButton("Advanced level (99 mines, 16x30 squares)", 2, 16, 30, 99)
        };
    }

    private JRadioButton createOptionButton(String label, final int index, final int rows, final int columns, final int mines) {
        JRadioButton button = new JRadioButton(label);
        button.addActionListener(new ActionListener() {
            @Override
            public void actionPerformed(ActionEvent e) {
                personalizeButton.setSelected(false);
                setParameters(index, rows, columns, mines, false);
            }
        });
        return button;
    }

    private JPanel createPersonalizePanel() {
        JPanel panel = new JPanel();
        panel.setLayout(new GridLayout(4, 1));
        this.optionTextFields = createTextFields();
        createPersonalizeButton();
        panel.add(personalizeButton);
        panel.add(createTextFieldPanel(this.optionTextFields[0], "High (9-24): "));
        panel.add(createTextFieldPanel(this.optionTextFields[1], "Width (9-30): "));
        panel.add(createTextFieldPanel(this.optionTextFields[2], "Mines (10-668): "));
        return panel;
    }

    private JTextField[] createTextFields() {
        return new JTextField[]{createTextField(10, 'r'), createTextField(10, 'c'), createTextField(10, 'm')};
    }

    private JTextField createTextField(int length, final char attribute) {
        final JTextField field = new JTextField(length);
        field.setEnabled(false);
        field.addKeyListener(new KeyListener() {
            @Override
            public void keyTyped(KeyEvent e) {
            }

            @Override
            public void keyPressed(KeyEvent e) {
            }

            @Override
            public void keyReleased(KeyEvent e) {
                switch (attribute) {
                    case 'r':
                        rowsAmount = Integer.parseInt(field.getText());
                        System.out.println("filas: " + rowsAmount);
                        break;
                    case 'c':
                        columnAmount = Integer.parseInt(field.getText());
                        System.out.println("column: " + columnAmount);
                        break;
                    default:
                        minesAmount = Integer.parseInt(field.getText());
                        System.out.println("minas: " + minesAmount);
                }
            }
        });
        return field;
    }

    private void createPersonalizeButton() {
        personalizeButton = new JRadioButton("Personalize");
        personalizeButton.addActionListener(new ActionListener() {
            @Override
            public void actionPerformed(ActionEvent e) {
                setParameters(-1, 0, 0, 0, true);
            }
        });
    }

    private JPanel createTextFieldPanel(JTextField textField, String label) {
        JPanel panel = new JPanel();
        panel.setLayout(new FlowLayout(FlowLayout.RIGHT));
        panel.add(new Label(label));
        panel.add(textField);
        return panel;
    }

    private void setParameters(final int index, final int rows, final int columns, final int mines, boolean optionFieldsMode) {
        enableOptionFields(optionFieldsMode);
        for (int i = 0; i < this.defaultOptionButtons.length; i++)
            if (i != index) this.defaultOptionButtons[i].setSelected(false);
        this.rowsAmount = rows;
        this.columnAmount = columns;
        this.minesAmount = mines;
        System.out.println("rows: " + rowsAmount + "    " + "colum: " + columnAmount + "    " + "mines: " + minesAmount);
    }

    private void enableOptionFields(boolean mode) {
        for (JTextField jTextField : this.optionTextFields)
            jTextField.setEnabled(mode);
        if (mode == false) clearOptionFields();
    }

    private void clearOptionFields() {
        for (JTextField jTextField : this.optionTextFields)
            jTextField.setText("");
    }

    @Override
    public int getRowsAmount() {
        return this.rowsAmount;
    }

    @Override
    public int getColumnAmount() {
        return this.columnAmount;
    }

    @Override
    public int getMinesAmount() {
        return this.minesAmount;
    }
}
