package UserInterface;

import Model.Image;
import Model.ImageSet;
import UserInterface.AbstractInterface.ImageViewer;
import java.awt.Dimension;
import java.awt.Graphics;
import javax.swing.JPanel;

public class PanelImageViewer extends JPanel implements ImageViewer{

  private Image image;

    public PanelImageViewer(){
        super();
    }
    
    @Override
    public void setImage(String name){
        this.image = ImageSet.getInstance().get(name);
        this.setPreferredSize(new Dimension(
                ((java.awt.Image)(image.getBitmap().getImage())).getWidth(null),
                ((java.awt.Image)(image.getBitmap().getImage())).getHeight(null)));
        this.repaint();
    }

    @Override
    public void paint(Graphics g) {
        g.drawImage((java.awt.Image)(image.getBitmap().getImage()), 0, 0, null);
    }

    private void clearPanel(Graphics g) {
        super.paint(g);
    }
       
}
