/**
 * Created by deutsch on 23/09/17.
 */
public class Voiture extends Vehicule {



    private int pos;
    public int taille = 2;


    public int getPos() {
        return pos;
    }
    public void setPos(int pos) {
        this.pos = pos;
    }


    public Voiture(int posInit){
        this.pos = posInit;
    }


    void avancer(){
        this.pos++;
    }

}
