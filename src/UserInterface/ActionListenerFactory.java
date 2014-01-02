package UserInterface;

import java.awt.event.ActionListener;

public interface ActionListenerFactory {

    public ActionListener createActionListener(final String action);
}
