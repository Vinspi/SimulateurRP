import javax.swing.*;
import java.awt.*;
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
        graphics.setColor(Color.cyan);
        graphics.fillRect(0,0,500,500);
        for(Drawable d : drawables)
            d.draw(graphics);
    }


}
