import java.awt.*;
import java.util.ArrayList;
import java.util.Observable;
import java.util.Observer;
import java.util.concurrent.ConcurrentLinkedQueue;

/**
 * Created by deutsch on 23/09/17.
 */
public class Modele extends Observable implements Observer {

    public ConcurrentLinkedQueue<Vehicule> vhVoie1;
    public ConcurrentLinkedQueue<Vehicule> vhVoie2;
    public ConcurrentLinkedQueue<Vehicule> vhVoie3;
    public ConcurrentLinkedQueue<Vehicule> vhVoie4;
    public ConcurrentLinkedQueue<EventRP> fifoEvent;

    //Taille fixé à 100 pour le moment.
    public Vehicule rondpoint[] = new Vehicule[100];

    //Peut être mieux de le mettre pour chaque véhicule.
    //Fixé à 25 (1/4 du rond-point) pour le moment.
    public int tolerance = 25;

    //Pas nécessairement un fifo.
    public ConcurrentLinkedQueue<Vehicule> vhRP;



    public Modele(){
        vhVoie1 = null;
        vhVoie2 = null;
        vhVoie3 = null;
        vhVoie4 = null;
        vhRP = null;
        fifoEvent = null;
    }



    private void addVehiculeToVoie(Vehicule vh, ConcurrentLinkedQueue<Vehicule> voie){
        voie.add(vh);
    }

    //Deux solutions afin de localiser le véhicule :
    // -> associer la pos -X au véhicule, avec X la première case sur laquelle le véhicule va passer en s'engageant dans le rond-point. /!\ A la fonction drawVehicule qui devra dessiner les vh des fifo en fonction de leur position dans la fifo et non de pos.
    // -> switch case sur les voies.
    void insérerVehiculeDansRP(ConcurrentLinkedQueue<Vehicule> voie, int tolerance){

        int posInit = 0;
        if (voie.equals(vhVoie2)){
            posInit = 25;

        }
        else if (voie.equals(vhVoie3)){
            posInit = 50;

        }
        else if (voie.equals(vhVoie4)){
            posInit = 100;
        }


        for(int k = 0; k < voie.element().taille; k++){
            rondpoint[(posInit+k)%100] = voie.element();
        }



    }

    //Fonction appelé à chaque tic d'horloge si le controleur le demande.
    private void majModele(){


    }




    @Override
    public void update(Observable observable, Object o) {

        setChanged();
        notifyObservers();
    }
}
