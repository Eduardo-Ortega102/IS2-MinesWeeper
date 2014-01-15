package Persistence;

import Model.ImageSet;
import Persistence.abstractInterface.ImageSetLoader;
import Persistence.abstractInterface.BitmapFactory;

public class FileImageSetLoader implements ImageSetLoader {

    private final String path;
    private final String[] filenames;
    private final BitmapFactory factory;

    public FileImageSetLoader(BitmapFactory factory, String path, String... filenames) {
        this.path = path;
        this.filenames = filenames;
        this.factory = factory;
    }

    @Override
    public void loadImageSet() {
        ImageSet set = ImageSet.getInstance();
        for (String filename : filenames) 
            set.put(filename, new ProxyImage(new FileImageLoader(path + "\\" + filename, factory)));
    }
}
