package UserInterface.AbstractInterface;

import Model.Square;

public interface MineViewerFactory {
    
    public SquareViewer createMineViewer(int posX, int posY, Square square, ActionFactory factory);

}
