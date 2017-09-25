import javax.imageio.ImageIO;
import javax.swing.*;
import java.awt.*;
import java.awt.geom.AffineTransform;
import java.io.File;
import java.io.FileInputStream;
import java.io.IOException;
import java.util.LinkedList;

/**
 * Created by vinspi on 23/09/17.
 */
public class Jcanvas extends JPanel {

    private LinkedList<Drawable> drawables;
    private Image backgroundImage;
    public int nbVehiculeVoie1 = 0;
    public int nbVehiculeVoie2 = 0;
    public int nbVehiculeVoie3 = 0;
    public int nbVehiculeVoie4 = 0;


    public Jcanvas(int sizeW, int sizeH) {
        this.drawables = new LinkedList<>();
        this.setSize(sizeW,sizeH);
        try {
            this.backgroundImage = ImageIO.read(new FileInputStream(Const.RP4_Background));

            this.backgroundImage = this.backgroundImage.getScaledInstance(800,800,Image.SCALE_SMOOTH);


        } catch (IOException e) {
            e.printStackTrace();
        }

    }

    public void addDrawable(Drawable d){
        this.drawables.add(d);
    }

    public void removeDrawable(Drawable d){
        this.drawables.remove(d);
    }

    @Override
    public void paint(Graphics graphics) {
        super.paint(graphics);


        graphics.drawImage(this.backgroundImage, 0, 0, null);

     
        for(Drawable d : drawables)
            d.draw(graphics);
    }


}
