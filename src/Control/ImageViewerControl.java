package Control;

import UserInterface.AbstractInterface.ImageViewer;

public class ImageViewerControl {

    private final ImageViewer imageViever;

    public ImageViewerControl(ImageViewer imageViever) {
        this.imageViever = imageViever;
    }

    public void viewImage(String name) {
        this.imageViever.setImage(name);
    }
}
