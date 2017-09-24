/**
 * Created by deutsch on 23/09/17.
 */
public class Voiture extends Vehicule {



    private int pos;
    private int taille = 2;
    private int sortiePrevue;


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
        this.pos = posInit;
        this.sortiePrevue = sortiePrevue;
    }




}
