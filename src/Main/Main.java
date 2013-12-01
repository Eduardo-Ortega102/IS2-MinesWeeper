package Main;

import Control.Control;
import Persistence.MineFieldLoader;
import Persistence.MineLoader;

public class Main {

    public static void main(String[] args) {
        MineLoader load = obtainLoader();
        Control.execute(load);

    }

    private static MineLoader obtainLoader() {
        return MineFieldLoader.getInstance();
    }
}
