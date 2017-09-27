import java.awt.*;
import java.awt.geom.AffineTransform;

/**
 * Created by deutsch on 23/09/17.
 */
public abstract class Vehicule implements Drawable{

    protected int pos;
    protected int taille;
    protected int sortiePrevue;
    protected int NbVoie;
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
        int r;
        double alpha;

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
                case -1:
                    /* 1° pos file 1*/
                    r = (int) Math.sqrt(10*10 + 185*185);
                    alpha = ((360/NbVoie)*0)+3;
                    af.translate(r*Math.sin(Math.toRadians(alpha)),  r*Math.cos(Math.toRadians(alpha)));
                    break;
                case -2:
                    /* 2° voiture file 1 */
                    r = (int) Math.sqrt(10*10 + 185*185);
                    alpha = ((360/NbVoie)*0)+3;
                    af.translate(r*Math.sin(Math.toRadians(alpha)),  r*Math.cos(Math.toRadians(alpha))+63);
                    break;
                case -3:
                    /* 3° voiture file 1 */
                    r = (int) Math.sqrt(10*10 + 185*185);
                    alpha = ((360/NbVoie)*0)+3;
                    af.translate(r*Math.sin(Math.toRadians(alpha)),  r*Math.cos(Math.toRadians(alpha))+2*63);
                    break;
                case -4:
                    /* 4° voiture file 1 */
                    r = (int) Math.sqrt(10*10 + 185*185);
                    alpha = ((360/NbVoie)*0)+3;
                    af.translate(r*Math.sin(Math.toRadians(alpha)),  r*Math.cos(Math.toRadians(alpha))+3*63);
                    break;
                case -5:
                    /* 5° voiture file 1 */
                    r = (int) Math.sqrt(10*10 + (185*185);
                    alpha = ((360/NbVoie)*0)+3;
                    af.translate(r*Math.sin(Math.toRadians(alpha)),  r*Math.cos(Math.toRadians(alpha))+4*63);
                    break;


                case -6:
                    /* 1° pos file 2*/
                    r = (int) Math.sqrt(185*185 + 10*10);
                    alpha = ((360/NbVoie)*1)+3;
                    af.translate(r*Math.sin(Math.toRadians(alpha)),  r*Math.cos(Math.toRadians(alpha)));
                    af.rotate(Math.toRadians(-360/NbVoie));
                    break;
                case -7:
                    /* 2° voiture file 2 */
                    r = (int) Math.sqrt( + 6*6);
                    alpha = ((360/NbVoie)*1)+3;
                    af.translate(r*Math.sin(Math.toRadians(alpha))+63,  r*Math.cos(Math.toRadians(alpha)));
                    af.rotate(Math.toRadians(-360/NbVoie));
                    break;
                case -8:
                    /* 3° voiture file 2 */
                    r = (int) Math.sqrt(185*185 + 6*6);
                    alpha = ((360/NbVoie)*1)+3;
                    af.translate(r*Math.sin(Math.toRadians(alpha))+2*63, r*Math.cos(Math.toRadians(alpha)));
                    af.rotate(Math.toRadians(-360/NbVoie));
                    break;
                case -9:
                    /* 4° voiture file 2 */
                    r = (int) Math.sqrt(185*185 + 6*6);
                    alpha = ((360/NbVoie)*1)+3;
                    af.translate(r*Math.sin(Math.toRadians(alpha))+3*63,r*Math.cos(Math.toRadians(alpha)));
                    af.rotate(Math.toRadians(-360/NbVoie));
                    break;
                case -10:
                    /* 5° voiture file 2 */
                    r = (int) Math.sqrt(185*185 + 6*6);
                    alpha = ((360/NbVoie)*1)+3;
                    af.translate(r*Math.sin(Math.toRadians(alpha))+4*63,r*Math.cos(Math.toRadians(alpha)));
                    af.rotate(Math.toRadians(-360/NbVoie));
                    break;


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