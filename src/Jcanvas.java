import javax.imageio.ImageIO;
import javax.swing.*;
import java.awt.*;
import java.io.File;
import java.io.IOException;
import java.util.LinkedList;

/**
 * Created by vinspi on 23/09/17.
 */
public class Jcanvas extends JPanel {

    private LinkedList<Drawable> drawables;

    public Jcanvas() {
        this.drawables = new LinkedList<>();
        this.setSize(800,500);
    }

    @Override
    public void paint(Graphics graphics) {
        super.paint(graphics);
        try {
            Image backgroundImage = ImageIO.read(new File(Const.RP4_Background));
            graphics.drawImage(backgroundImage, 0, 0, null);
        } catch (IOException e) {
            e.printStackTrace();
        }

        for(Drawable d : drawables)
            d.draw(graphics);
    }


}
