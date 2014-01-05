package Control;

import Model.ImageSet;
import UserInterface.AbstractInterface.ImageViewer;
import java.util.HashMap;

public class ImageViewerControl<Parameter> {

    private final ImageViewer imageViever;
    private ImagePriority lastImagePriority;
    private HashMap<Parameter, ImagePriority> imagePriorityMap;

    public ImageViewerControl(ImageViewer imageViever, HashMap<Parameter, ImagePriority> imagePriorityMap) {
        this.imageViever = imageViever;
        this.imagePriorityMap = imagePriorityMap;
        this.lastImagePriority = ImagePriority.LOW;
    }

    public void reset() {
        lastImagePriority = ImagePriority.LOW;
    }

    public void viewImage(Parameter input) {
        if (imagePriorityMap.get(input).compareTo(lastImagePriority) >= 0) {
            this.imageViever.setImage(ImageSet.getInstance().get((String)input));
            lastImagePriority = imagePriorityMap.get(input);
        }
    }
}
