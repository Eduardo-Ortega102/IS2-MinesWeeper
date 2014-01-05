package Control;

import Model.ImageSet;
import UserInterface.AbstractInterface.ImageViewer;
import java.util.HashMap;

public class ImageViewerControl {

    private final ImageViewer imageViever;
    private ImagePriority lastImagePriority;
    private HashMap<String, ImagePriority> imagePriorityMap;

    public ImageViewerControl(ImageViewer imageViever, HashMap<String, ImagePriority> imagePriorityMap) {
        this.imageViever = imageViever;
        this.imagePriorityMap = imagePriorityMap;
        this.reset();
    }

    public void reset() {
        lastImagePriority = ImagePriority.LOW;
    }

    public void viewImage(String input) {
        if (imagePriorityMap.get(input).compareTo(lastImagePriority) >= 0) {
            this.imageViever.setImage(ImageSet.getInstance().get(input));
            lastImagePriority = imagePriorityMap.get(input);
        }
    }
}
