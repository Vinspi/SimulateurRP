import java.awt.*;
import java.awt.geom.AffineTransform;

/**
 * Created by deutsch on 23/09/17.
 */
public abstract class Vehicule implements Drawable{

    protected int pos;
    protected int taille;
    protected int sortiePrevue;
    protected Image imgVoiture;
    private AffineTransform af;

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
        Graphics2D g2d = (Graphics2D) g;

        g2d.translate(405, 405);
        af = new AffineTransform();

        if(this.pos > -1) {
            af.translate(imgVoiture.getHeight(null) / 2, -imgVoiture.getWidth(null) / 2);

            af.rotate(Math.toRadians(90));






    /* a ce moment la voiture est au centre du RP */

    /* on defini un vecteur de translation de base (qui correspond à la pos 0 du cadran) */

            int vectorX = 170;
            int vectorY = 0;



    /* on calcule l'angle associé à la pos */

            double angle = -(this.pos * 360 / 100); // a modifier avec une cte ou une variable

    /* on applique la rotation qui correspond à la pos de la voiture sur ces coordonnées */

            int newVectorX = (int) (vectorX * Math.cos(Math.toRadians(angle)) - vectorY * Math.sin(Math.toRadians(angle)));
            int newVectorY = (int) (vectorX * Math.sin(Math.toRadians(angle)) + vectorY * Math.cos(Math.toRadians(angle)));


            af.translate(newVectorX, newVectorY);

            af.rotate(Math.toRadians(angle), this.imgVoiture.getWidth(null) / 2, this.imgVoiture.getHeight(null) / 2);


            g2d.drawImage(imgVoiture, af, null);

        }
        if(pos > -11 && pos < 0){


            af.rotate(Math.toRadians(0));
            for (int i=-11;i<0;i++) {
                af.translate(12, 180+i*140);

                g2d.drawImage(imgVoiture, af, null);
            }
        }
        g2d.translate(-405, -405);

    }
}
