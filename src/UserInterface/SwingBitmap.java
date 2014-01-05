package UserInterface;

import Model.Bitmap;
import java.awt.image.BufferedImage;
import java.io.File;
import java.io.IOException;
import java.util.logging.Level;
import java.util.logging.Logger;
import javax.imageio.ImageIO;

public class SwingBitmap implements Bitmap<BufferedImage> {

    private BufferedImage image;

    public SwingBitmap(String path) {
        try {
            image = ImageIO.read(new File(path));
        } catch (IOException ex) {
            System.out.println("Error en la clase SwingBitMap");
            Logger.getLogger(SwingBitmap.class.getName()).log(Level.SEVERE, null, ex);
        }
    }

    @Override
    public BufferedImage getImage() {
        return this.image;
    }
}
