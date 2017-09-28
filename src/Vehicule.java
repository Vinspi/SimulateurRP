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
        double rx, ry;
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
                    alpha = ((360/NbVoie)*0)+4;
                    rx = (10 * Math.cos(Math.toRadians(alpha))) - ((185) * Math.sin(Math.toRadians(alpha)));
                    ry = (10 * Math.sin(Math.toRadians(alpha))) + ((185) * Math.cos(Math.toRadians(alpha)));
                    af.translate(-rx, ry);
                    break;
                case -2:
                    /* 2° voiture file 1 */
                    alpha = ((360/NbVoie)*0)+3.5;
                    rx = (10 * Math.cos(Math.toRadians(alpha))) - ((185+1*63) * Math.sin(Math.toRadians(alpha)));
                    ry = (10 * Math.sin(Math.toRadians(alpha))) + ((185+1*63) * Math.cos(Math.toRadians(alpha)));
                    af.translate(-rx, ry);
                    break;
                case -3:
                    /* 3° voiture file 1 */
                    alpha = ((360/NbVoie)*0)+3;
                    rx = (10 * Math.cos(Math.toRadians(alpha))) - ((185+2*63) * Math.sin(Math.toRadians(alpha)));
                    ry = (10 * Math.sin(Math.toRadians(alpha))) + ((185+2*63) * Math.cos(Math.toRadians(alpha)));
                    af.translate(-rx, ry);
                    break;
                case -4:
                    /* 4° voiture file 1 */
                    alpha = ((360/NbVoie)*0)+2.5;
                    rx = (10 * Math.cos(Math.toRadians(alpha))) - ((185+3*63) * Math.sin(Math.toRadians(alpha)));
                    ry = (10 * Math.sin(Math.toRadians(alpha))) + ((185+3*63) * Math.cos(Math.toRadians(alpha)));
                    af.translate(-rx, ry);
                    break;
                case -5:
                    /* 5° voiture file 1 */
                    alpha = ((360/NbVoie)*0)+2;
                    rx = (10 * Math.cos(Math.toRadians(alpha))) - ((185+4*63) * Math.sin(Math.toRadians(alpha)));
                    ry = (10 * Math.sin(Math.toRadians(alpha))) + ((185+4*63) * Math.cos(Math.toRadians(alpha)));
                    af.translate(-rx, ry);
                    break;


                case -6:
                    /* 1° pos file 2*/
                    alpha = ((360/NbVoie)*1)+4;
                    rx = (10 * Math.cos(Math.toRadians(alpha))) - ((185) * Math.sin(Math.toRadians(alpha)));
                    ry = (10 * Math.sin(Math.toRadians(alpha))) + ((185) * Math.cos(Math.toRadians(alpha)));
                    af.translate(-rx,  ry);
                    af.rotate(Math.toRadians(-360/NbVoie));
                    break;
                case -7:
                    /* 2° voiture file 2 */
                    alpha = ((360/NbVoie)*1)+3.5;
                    rx = (10 * Math.cos(Math.toRadians(alpha))) - ((185+1*63) * Math.sin(Math.toRadians(alpha)));
                    ry = (10 * Math.sin(Math.toRadians(alpha))) + ((185+1*63) * Math.cos(Math.toRadians(alpha)));
                    af.translate(-rx,  ry);
                    af.rotate(Math.toRadians(-360/NbVoie));
                    break;
                case -8:
                    /* 3° voiture file 2 */
                    alpha = ((360/NbVoie)*1)+3;
                    rx = (10 * Math.cos(Math.toRadians(alpha))) - ((185+2*63) * Math.sin(Math.toRadians(alpha)));
                    ry = (10 * Math.sin(Math.toRadians(alpha))) + ((185+2*63) * Math.cos(Math.toRadians(alpha)));
                    af.translate(-rx,  ry);
                    af.rotate(Math.toRadians(-360/NbVoie));
                    break;
                case -9:
                    /* 4° voiture file 2 */
                    alpha = ((360/NbVoie)*1)+2.5;
                    rx = (10 * Math.cos(Math.toRadians(alpha))) - ((185+3*63) * Math.sin(Math.toRadians(alpha)));
                    ry = (10 * Math.sin(Math.toRadians(alpha))) + ((185+3*63) * Math.cos(Math.toRadians(alpha)));
                    af.translate(-rx,  ry);
                    af.rotate(Math.toRadians(-360/NbVoie));
                    break;
                case -10:
                    /* 5° voiture file 2 */
                    alpha = ((360/NbVoie)*1)+2;
                    rx = (10 * Math.cos(Math.toRadians(alpha))) - ((185+4*63) * Math.sin(Math.toRadians(alpha)));
                    ry = (10 * Math.sin(Math.toRadians(alpha))) + ((185+4*63) * Math.cos(Math.toRadians(alpha)));
                    af.translate(-rx,  ry);
                    af.rotate(Math.toRadians(-360/NbVoie));
                    break;


                case -11:
                    /* 1° voiture file 3 */
                    alpha = ((360/NbVoie)*2)+4;
                    rx = (10 * Math.cos(Math.toRadians(alpha))) - ((185) * Math.sin(Math.toRadians(alpha)));
                    ry = (10 * Math.sin(Math.toRadians(alpha))) + ((185) * Math.cos(Math.toRadians(alpha)));
                    af.translate(-rx,  ry);
                    af.rotate(Math.toRadians((-360/NbVoie)*2));
                    break;
                case -12:
                    /* 2° voiture file 3 */
                    alpha = ((360/NbVoie)*2)+3.5;
                    rx = (10 * Math.cos(Math.toRadians(alpha))) - ((185+1*63) * Math.sin(Math.toRadians(alpha)));
                    ry = (10 * Math.sin(Math.toRadians(alpha))) + ((185+1*63) * Math.cos(Math.toRadians(alpha)));
                    af.translate(-rx,  ry);
                    af.rotate(Math.toRadians((-360/NbVoie)*2));
                    break;
                case -13:
                    /* 3° voiture file 3 */
                    alpha = ((360/NbVoie)*2)+3;
                    rx = (10 * Math.cos(Math.toRadians(alpha))) - ((185+2*63) * Math.sin(Math.toRadians(alpha)));
                    ry = (10 * Math.sin(Math.toRadians(alpha))) + ((185+2*63) * Math.cos(Math.toRadians(alpha)));
                    af.translate(-rx,  ry);
                    af.rotate(Math.toRadians((-360/NbVoie)*2));
                    break;
                case -14:
                    /* 4° voiture file 3 */
                    alpha = ((360/NbVoie)*2)+2.5;
                    rx = (10 * Math.cos(Math.toRadians(alpha))) - ((185+3*63) * Math.sin(Math.toRadians(alpha)));
                    ry = (10 * Math.sin(Math.toRadians(alpha))) + ((185+3*63) * Math.cos(Math.toRadians(alpha)));
                    af.translate(-rx,  ry);
                    af.rotate(Math.toRadians((-360/NbVoie)*2));
                    break;
                case -15:
                    /* 5° voiture file 3 */
                    alpha = ((360/NbVoie)*2)+2;
                    rx = (10 * Math.cos(Math.toRadians(alpha))) - ((185+4*63) * Math.sin(Math.toRadians(alpha)));
                    ry = (10 * Math.sin(Math.toRadians(alpha))) + ((185+4*63) * Math.cos(Math.toRadians(alpha)));
                    af.translate(-rx,  ry);
                    af.rotate(Math.toRadians((-360/NbVoie)*2));
                    break;


                case -16:
                    /* 1° voiture file 4 */
                    alpha = ((360/NbVoie)*3)+4;
                    rx = (10 * Math.cos(Math.toRadians(alpha))) - ((185) * Math.sin(Math.toRadians(alpha)));
                    ry = (10 * Math.sin(Math.toRadians(alpha))) + ((185) * Math.cos(Math.toRadians(alpha)));
                    af.translate(-rx,  ry);
                    af.rotate(Math.toRadians((-360/NbVoie)*3));
                    break;
                case -17:
                    /* 2° voiture file 4 */
                    alpha = ((360/NbVoie)*3)+3.5;
                    rx = (10 * Math.cos(Math.toRadians(alpha))) - ((185+1*63) * Math.sin(Math.toRadians(alpha)));
                    ry = (10 * Math.sin(Math.toRadians(alpha))) + ((185+1*63) * Math.cos(Math.toRadians(alpha)));
                    af.translate(-rx,  ry);
                    af.rotate(Math.toRadians((-360/NbVoie)*3));
                    break;
                case -18:
                    /* 3° voiture file 4 */
                    alpha = ((360/NbVoie)*3)+3;
                    rx = (10 * Math.cos(Math.toRadians(alpha))) - ((185+2*63) * Math.sin(Math.toRadians(alpha)));
                    ry = (10 * Math.sin(Math.toRadians(alpha))) + ((185+2*63) * Math.cos(Math.toRadians(alpha)));
                    af.translate(-rx,  ry);
                    af.rotate(Math.toRadians((-360/NbVoie)*3));
                    break;
                case -19:
                    /* 4° voiture file 4 */
                    alpha = ((360/NbVoie)*3)+2.5;
                    rx = (10 * Math.cos(Math.toRadians(alpha))) - ((185+3*63) * Math.sin(Math.toRadians(alpha)));
                    ry = (10 * Math.sin(Math.toRadians(alpha))) + ((185+3*63) * Math.cos(Math.toRadians(alpha)));
                    af.translate(-rx,  ry);
                    af.rotate(Math.toRadians((-360/NbVoie)*3));
                    break;
                case -20:
                    /* 5° voiture file 4 */
                    alpha = ((360/NbVoie)*3)+2;
                    rx = (10 * Math.cos(Math.toRadians(alpha))) - ((185+4*63) * Math.sin(Math.toRadians(alpha)));
                    ry = (10 * Math.sin(Math.toRadians(alpha))) + ((185+4*63) * Math.cos(Math.toRadians(alpha)));
                    af.translate(-rx,  ry);
                    af.rotate(Math.toRadians((-360/NbVoie)*3));
                    break;


                case -21:
                    /* 1° voiture file 5 */
                    alpha = ((360/NbVoie)*4)+4;
                    rx = (10 * Math.cos(Math.toRadians(alpha))) - ((185) * Math.sin(Math.toRadians(alpha)));
                    ry = (10 * Math.sin(Math.toRadians(alpha))) + ((185) * Math.cos(Math.toRadians(alpha)));
                    af.translate(-rx,  ry);
                    af.rotate(Math.toRadians((-360/NbVoie)*4));
                    break;
                case -22:
                    /* 2° voiture file 5 */
                    alpha = ((360/NbVoie)*4)+3.5;
                    rx = (10 * Math.cos(Math.toRadians(alpha))) - ((185+1*63) * Math.sin(Math.toRadians(alpha)));
                    ry = (10 * Math.sin(Math.toRadians(alpha))) + ((185+1*63) * Math.cos(Math.toRadians(alpha)));
                    af.translate(-rx,  ry);
                    af.rotate(Math.toRadians((-360/NbVoie)*4));
                    break;
                case -23:
                    /* 3° voiture file 5 */
                    alpha = ((360/NbVoie)*4)+3;
                    rx = (10 * Math.cos(Math.toRadians(alpha))) - ((185+2*63) * Math.sin(Math.toRadians(alpha)));
                    ry = (10 * Math.sin(Math.toRadians(alpha))) + ((185+2*63) * Math.cos(Math.toRadians(alpha)));
                    af.translate(-rx,  ry);
                    af.rotate(Math.toRadians((-360/NbVoie)*4));
                    break;
                case -24:
                    /* 4° voiture file 5 */
                    alpha = ((360/NbVoie)*4)+2.5;
                    rx = (10 * Math.cos(Math.toRadians(alpha))) - ((185+3*63) * Math.sin(Math.toRadians(alpha)));
                    ry = (10 * Math.sin(Math.toRadians(alpha))) + ((185+3*63) * Math.cos(Math.toRadians(alpha)));
                    af.translate(-rx,  ry);
                    af.rotate(Math.toRadians((-360/NbVoie)*4));
                    break;
                case -25:
                    /* 5° voiture file 5 */
                    alpha = ((360/NbVoie)*4)+2;
                    rx = (10 * Math.cos(Math.toRadians(alpha))) - ((185+4*63) * Math.sin(Math.toRadians(alpha)));
                    ry = (10 * Math.sin(Math.toRadians(alpha))) + ((185+4*63) * Math.cos(Math.toRadians(alpha)));
                    af.translate(-rx,  ry);
                    af.rotate(Math.toRadians((-360/NbVoie)*4));
                    break;


                case -26:
                    /* 1° voiture file 6 */
                    alpha = ((360/NbVoie)*5)+4;
                    rx = (10 * Math.cos(Math.toRadians(alpha))) - ((185) * Math.sin(Math.toRadians(alpha)));
                    ry = (10 * Math.sin(Math.toRadians(alpha))) + ((185) * Math.cos(Math.toRadians(alpha)));
                    af.translate(-rx,  ry);
                    af.rotate(Math.toRadians((-360/NbVoie)*5));
                    break;
                case -27:
                    /* 2° voiture file 6 */
                    alpha = ((360/NbVoie)*5)+3.5;
                    rx = (10 * Math.cos(Math.toRadians(alpha))) - ((185+1*63) * Math.sin(Math.toRadians(alpha)));
                    ry = (10 * Math.sin(Math.toRadians(alpha))) + ((185+1*63) * Math.cos(Math.toRadians(alpha)));
                    af.translate(-rx,  ry);
                    af.rotate(Math.toRadians((-360/NbVoie)*5));
                    break;
                case -28:
                    /* 3° voiture file 6 */
                    alpha = ((360/NbVoie)*5)+3;
                    rx = (10 * Math.cos(Math.toRadians(alpha))) - ((185+2*63) * Math.sin(Math.toRadians(alpha)));
                    ry = (10 * Math.sin(Math.toRadians(alpha))) + ((185+2*63) * Math.cos(Math.toRadians(alpha)));
                    af.translate(-rx,  ry);
                    af.rotate(Math.toRadians((-360/NbVoie)*5));
                    break;
                case -29:
                    /* 4° voiture file 6 */
                    alpha = ((360/NbVoie)*5)+2.5;
                    rx = (10 * Math.cos(Math.toRadians(alpha))) - ((185+3*63) * Math.sin(Math.toRadians(alpha)));
                    ry = (10 * Math.sin(Math.toRadians(alpha))) + ((185+3*63) * Math.cos(Math.toRadians(alpha)));
                    af.translate(-rx,  ry);
                    af.rotate(Math.toRadians((-360/NbVoie)*5));
                    break;
                case -30:
                    /* 5° voiture file 6 */
                    alpha = ((360/NbVoie)*5)+2;
                    rx = (10 * Math.cos(Math.toRadians(alpha))) - ((185+4*63) * Math.sin(Math.toRadians(alpha)));
                    ry = (10 * Math.sin(Math.toRadians(alpha))) + ((185+4*63) * Math.cos(Math.toRadians(alpha)));
                    af.translate(-rx,  ry);
                    af.rotate(Math.toRadians((-360/NbVoie)*5));
                    break;



            }




            g2d.drawImage(imgVoiture, af, null);
        }
        g2d.translate(-405, -405);

    }
}