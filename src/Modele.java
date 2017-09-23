import java.awt.*;
import java.util.ArrayList;
import java.util.Observable;
import java.util.Observer;
import java.util.concurrent.ConcurrentLinkedQueue;

/**
 * Created by deutsch on 23/09/17.
 */
public class Modele extends Observable implements Observer {

    private ConcurrentLinkedQueue<Vehicule> vhVoie1;
    private ConcurrentLinkedQueue<Vehicule> vhVoie2;
    private ConcurrentLinkedQueue<Vehicule> vhVoie3;
    private ConcurrentLinkedQueue<Vehicule> vhVoie4;

    private ArrayList<Vehicule> vhRP;
    private Vehicule rondpoint[] = new Vehicule[100]; //Taille fixé à 100 pour le moment.

    //Peut être mieux de le mettre pour chaque véhicule.
    //Fixé à 25 (1/4 du rond-point) pour le moment.
    private int tolerance = 25;



    public ConcurrentLinkedQueue<Vehicule> getVhVoie1() {
        return vhVoie1;
    }
    public void setVhVoie1(ConcurrentLinkedQueue<Vehicule> vhVoie1) {
        this.vhVoie1 = vhVoie1;
    }

    public ConcurrentLinkedQueue<Vehicule> getVhVoie2() {
        return vhVoie2;
    }
    public void setVhVoie2(ConcurrentLinkedQueue<Vehicule> vhVoie2) {
        this.vhVoie2 = vhVoie2;
    }

    public ConcurrentLinkedQueue<Vehicule> getVhVoie3() {
        return vhVoie3;
    }
    public void setVhVoie3(ConcurrentLinkedQueue<Vehicule> vhVoie3) {
        this.vhVoie3 = vhVoie3;
    }

    public ConcurrentLinkedQueue<Vehicule> getVhVoie4() {
        return vhVoie4;
    }
    public void setVhVoie4(ConcurrentLinkedQueue<Vehicule> vhVoie4) {
        this.vhVoie4 = vhVoie4;
    }


    public Vehicule[] getRondpoint() {
        return rondpoint;
    }
    public void setRondpoint(Vehicule[] rondpoint) {
        this.rondpoint = rondpoint;
    }

    public ArrayList<Vehicule> getVhRP() {
        return vhRP;
    }
    public void setVhRP(ArrayList<Vehicule> vhRP) {
        this.vhRP = vhRP;
    }

    public int getTolerance() {
        return tolerance;
    }
    public void setTolerance(int tolerance) {
        this.tolerance = tolerance;
    }

    public Modele(){
        vhVoie1 = null;
        vhVoie2 = null;
        vhVoie3 = null;
        vhVoie4 = null;
        vhRP = null;
    }


    //Ajoute un véhicule dans le fifo de la voie "voie".
    void addVehiculeToVoie(Vehicule vh, ConcurrentLinkedQueue<Vehicule> voie){

        voie.add(vh);
    }


    //Fait s'insérer le premier véhicule de la voie dans le rond-point.
    //Deux solutions afin de localiser le véhicule :
    // -> associer la pos -X au véhicule, avec X la première case sur laquelle le véhicule va passer en s'engageant dans le rond-point. /!\ A la fonction drawVehicule qui devra dessiner les vh des fifo en fonction de leur position dans la fifo et non de pos.
    // -> switch case sur les voies.
    void insérerVehiculeDansRP(ConcurrentLinkedQueue<Vehicule> voie){

        int posInit = 0;
        ConcurrentLinkedQueue<Vehicule> vhVoie = vhVoie1;
        if (voie.equals(vhVoie2)){
            posInit = 25;
            vhVoie = vhVoie2;

        }
        else if (voie.equals(vhVoie3)){
            posInit = 50;
            vhVoie = vhVoie3;
        }
        else if (voie.equals(vhVoie4)){
            posInit = 75;
            vhVoie = vhVoie4;
        }

        //On place le véhicule dans le rond-point.

        vhVoie.element().setPos(posInit);
        vhRP.add(vhVoie.element());
        for(int k = 0; k < voie.element().taille; k++){
            rondpoint[(posInit+k)%100] = voie.element();
        }

        //On le retire du fifo de sa voie.
        vhVoie.poll();

    }




    @Override
    public void update(Observable observable, Object o) {

        setChanged();
        notifyObservers();
    }
}
