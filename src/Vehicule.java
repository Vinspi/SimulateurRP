import java.awt.*;
import java.awt.geom.AffineTransform;
import java.util.concurrent.ConcurrentLinkedQueue;

/**
 * Created by deutsch on 23/09/17.
 */
public abstract class Vehicule implements Drawable{

    protected int pos;
    protected int taille;
    protected int sortiePrevue;
    protected Image imgVoiture;

    public int getSortiePrevue() {
        return sortiePrevue;
    }

    public int getPos() {
        return pos;
    }

    public int getTaille() {
        return taille;
    }

    public void setPos(int pos) {
        this.pos = pos;
    }

    @Override
    public void draw(Graphics g) {

        AffineTransform af = new AffineTransform();

        af.translate(imgVoiture.getHeight(null)/2,-imgVoiture.getWidth(null)/2);
        System.out.println("voiture : "+ imgVoiture.getWidth(null));

        af.rotate(Math.toRadians(90));

        Graphics2D g2d = (Graphics2D) g;

        g2d.translate(400,400);


        g2d.setColor(Color.BLUE);
        g2d.fillRect(-5,-5,10,10);

        /* a ce moment la voiture est au centre du RP */

        /* on defini un vecteur de translation de base (qui correspond à la pos 0 du cadran) */

        int vectorX = 170;
        int vectorY = 0;

        /* on calcule l'angle associé à la pos */

        double angle = -(this.pos * 360 / 100); // a modifier avec une cte ou une variable

        /* on applique la rotation qui correspond à la pos de la voiture sur ces coordonnées */

        int newVectorX = (int) (vectorX*Math.cos(Math.toRadians(angle)) - vectorY*Math.sin(Math.toRadians(angle)));
        int newVectory = (int) (vectorX*Math.sin(Math.toRadians(angle)) + vectorY*Math.cos(Math.toRadians(angle)));

        af.translate(newVectorX,newVectory);
        af.rotate(Math.toRadians(angle));


        g2d.drawImage(imgVoiture,af,null);

    }
}
