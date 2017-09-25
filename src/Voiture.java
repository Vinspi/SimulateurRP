import javax.imageio.ImageIO;
import java.awt.*;
import java.io.FileInputStream;
import java.io.IOException;

/**
 * Created by deutsch on 23/09/17.
 */
public class Voiture extends Vehicule {






    public int getPos() {
        return pos;
    }
    public void setPos(int pos) {
        this.pos = pos;
    }

    public int getSortiePrevue() {
        return sortiePrevue;
    }

    @Override
    public int getTaille() {
        return taille;
    }

    public Voiture(int posInit, int sortiePrevue){
        int randomSkin = (int) (Math.random()*6);
        this.pos = posInit;
        this.sortiePrevue = sortiePrevue;
        this.taille = 7;
        try {
            this.imgVoiture = ImageIO.read(new FileInputStream("voiture"+randomSkin+".png"));

            this.imgVoiture = this.imgVoiture.getScaledInstance(30,60, Image.SCALE_SMOOTH);
        }
        catch (IOException e){
            e.printStackTrace();
        }
    }




}
