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


    public Jcanvas(int sizeW, int sizeH) {
        this.drawables = new LinkedList<>();
        this.setSize(sizeW,sizeH);

    }

    @Override
    public void paint(Graphics graphics) {
        System.out.println("paint !");
        super.paint(graphics);

        try {
            this.backgroundImage = ImageIO.read(new FileInputStream(Const.RP4_Background));

            this.backgroundImage = this.backgroundImage.getScaledInstance(800,800,Image.SCALE_SMOOTH);
            graphics.drawImage(this.backgroundImage, 0, 0, null);


        } catch (IOException e) {
            e.printStackTrace();
        }

        for(Drawable d : drawables)
            d.draw(graphics);
    }


}
