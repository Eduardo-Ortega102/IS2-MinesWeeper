package UserInterface.AbstractInterface;

import Model.Square;

public interface SquareViewerFactory {
    
    public SquareViewer createSquareViewer(int posX, int posY, Square square, ActionFactory factory);

}
