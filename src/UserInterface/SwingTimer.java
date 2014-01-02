package UserInterface;

import Model.TimerInterface;
import java.awt.Color;
import java.awt.event.ActionEvent;
import java.awt.event.ActionListener;
import javax.swing.*;

public class SwingTimer extends JPanel implements TimerInterface {

    private Timer timer;
    private int minutes;
    private int seconds;
    private JLabel label;

    public SwingTimer(int delay) {
        super();
        this.setBackground(Color.black);
        label = new JLabel();
        refreshLabel("00:00");
        label.setForeground(Color.red);
        minutes = 0;
        seconds = 0;
        timer = new Timer(delay, new ActionListener() {
            @Override
            public void actionPerformed(ActionEvent e) {
                ++seconds;
                refreshLabel(analizeTime());
            }
        });
        this.add(label);
    }
    
    @Override
    public void start() {
        timer.start();
    }

    @Override
    public void stop() {
        timer.stop();
    }

    @Override
    public void reset() {
        minutes = 0;
        seconds = 0;
    }

    private String analizeTime() {
        if (seconds > 59) {
            seconds = 0;
            minutes++;
        }
        return buildTime();
    }

    private void refreshLabel(String string) {
        label.setText("Played Time: " + string);
    }

    private String buildTime() {
        return ((minutes > 9) ? minutes : "0" + minutes) + ":"
                + ((seconds > 9) ? seconds : "0" + seconds);
    }
}
