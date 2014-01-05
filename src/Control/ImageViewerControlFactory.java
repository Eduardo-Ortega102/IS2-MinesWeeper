package Control;

import UserInterface.AbstractInterface.ImageViewer;

public interface ImageViewerControlFactory {

    public ImageViewerControl createImageViewerControl(ImageViewer viewer);
}
