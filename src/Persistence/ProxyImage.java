package Persistence;

import Persistence.abstractInterface.ImageLoader;
import Model.Bitmap;
import Model.Image;

public class ProxyImage implements Image {

    private Image realImage;
    private ImageLoader loader;

    public ProxyImage(ImageLoader loader) {
        this.loader = loader;
        this.realImage = null;
    }

    @Override
    public Bitmap getBitmap() {
        if(this.realImage == null) this.realImage = this.loader.load();
        return this.realImage.getBitmap();
    }
}
