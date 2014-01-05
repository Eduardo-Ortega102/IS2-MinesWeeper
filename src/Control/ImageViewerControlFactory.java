package Control;

import UserInterface.AbstractInterface.ImageViewer;

public interface ImageViewerControlFactory<Parameter> {

    public ImageViewerControl<Parameter> createImageViewerControl(ImageViewer viewer);
}
