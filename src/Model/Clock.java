package Model;

import Control.Command;
import java.awt.event.ActionEvent;
import java.awt.event.ActionListener;
import javax.swing.Timer;

public class Clock {

    private static Timer timer;
    private static int time = 0;

    public Clock(final Command<String> command) {
        timer = new Timer(1000, new ActionListener() {
            @Override
            public void actionPerformed(ActionEvent e) {
                command.executeCommand(Integer.toString(++time));
            }
        });
    }

    public void start() {
        timer.start();
    }

    public void stop() {
        timer.stop();
    }
}
