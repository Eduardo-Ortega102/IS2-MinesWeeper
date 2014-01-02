package Control;

import java.awt.event.ActionEvent;
import java.awt.event.ActionListener;
import java.util.HashMap;

public class CommandDictionary {
    private final HashMap<String, ActionListener> commandMap;

    public CommandDictionary() {
        commandMap = new HashMap<>();
    }
    
    public void register(String key, final Command action){
        commandMap.put(key, new ActionListener() {
            @Override
            public void actionPerformed(ActionEvent e) {
                //action.executeCommand();
            }
        });
    }
    
    public ActionListener getAction(String key){
//        if(!commandMap.containsKey(key))
        return commandMap.get(key);
    }
    
    

}
