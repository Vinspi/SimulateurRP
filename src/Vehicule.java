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
        int posfifo;

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
        if(pos < 0){


            switch (pos){
                /* voie du bas */
                case -1:
                    /* premiere pos file 1*/
                    af.translate(12, 180);
                    break;
                case -2:
                    /* deuxieme voiture file 1 */
                    af.translate(12,180+63);
                    break;
                case -3:
                    /* deuxieme voiture file 1 */
                    af.translate(12,180+2*63);
                    break;
                case -4:
                    /* deuxieme voiture file 1 */
                    af.translate(12,180+3*63);
                    break;
                case -5:
                    /* deuxieme voiture file 1 */
                    af.translate(12,180+4*63);
                    break;
                    /* voie de droite */
                case -6:
                    /* premiere pos file 1*/
                    af.translate(185, -6);
                    af.rotate(Math.toRadians(-90));

                    break;
                case -7:
                    /* deuxieme voiture file 1 */

                    af.translate(185+63, -6);
                    af.rotate(Math.toRadians(-90));

                    break;
                case -8:
                    /* deuxieme voiture file 1 */

                    af.translate(185+2*63, -6);
                    af.rotate(Math.toRadians(-90));

                    break;
                case -9:
                    /* deuxieme voiture file 1 */

                    af.translate(185+3*63,-6);
                    af.rotate(Math.toRadians(-90));

                    break;
                case -10:
                    /* deuxieme voiture file 1 */

                    af.translate(185+4*63,-6);
                    af.rotate(Math.toRadians(-90));

                    break;
                    /* voie du haut */
                case -11:
                    /* deuxieme voiture file 1 */

                    af.translate(-7,-185);
                    af.rotate(Math.toRadians(-180));

                    break;

                case -12:
                    /* deuxieme voiture file 1 */

                    af.translate(-7,-185-63);
                    af.rotate(Math.toRadians(-180));

                    break;
                case -13:
                    /* deuxieme voiture file 1 */

                    af.translate(-7,-185-2*63);
                    af.rotate(Math.toRadians(-180));

                    break;
                case -14:
                    /* deuxieme voiture file 1 */

                    af.translate(-7,-185-3*63);
                    af.rotate(Math.toRadians(-180));

                    break;
                case -15:
                    /* deuxieme voiture file 1 */

                    af.translate(-7,-185-4*63);
                    af.rotate(Math.toRadians(-180));

                    break;
                    /* voie de gauche */
                case -16:
                    af.translate(-185,10);
                    af.rotate(Math.toRadians(90));
                    break;
                case -17:
                    af.translate(-185-63,10);
                    af.rotate(Math.toRadians(90));
                    break;
                case -18:
                    af.translate(-185-2*63,10);
                    af.rotate(Math.toRadians(90));
                    break;
                case -19:
                    af.translate(-185-3*63,10);
                    af.rotate(Math.toRadians(90));
                    break;
                case -20:
                    af.translate(-185-4*63,10);
                    af.rotate(Math.toRadians(90));
                    break;


            }




            g2d.drawImage(imgVoiture, af, null);
        }
        g2d.translate(-405, -405);

    }
}
