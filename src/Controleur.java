import java.util.Observable;
import java.util.Observer;
import java.util.concurrent.ConcurrentLinkedQueue;

/**
 * Created by deutsch on 23/09/17.
 */
public class Controleur extends Observable implements Observer {

    Modele modele;

    public Controleur(Modele modele){
        this.modele = modele;
    }


    public void verifInsertionRP(String voie, int tolerance){


        int posInit = 0;
        ConcurrentLinkedQueue vhVoie = null;

        if (voie.equals("voie1")){
            posInit = 0;
            vhVoie = modele.vhVoie1;
        }
        else if (voie.equals("voie2")){
            posInit = 25;
            vhVoie = modele.vhVoie2;
        }
        else if (voie.equals("voie3")){
            posInit = 50;
            vhVoie = modele.vhVoie3;
        }
        else if (voie.equals("voie4")){
            posInit = 100;
            vhVoie = modele.vhVoie4;
        }

        boolean passagePossible = true;
        //On regarde dans le quart inférieur s'il y a un véhicule qui circule, et que le véhicule a la place pour s'insérer :
        for(int i = 0; i < tolerance + ((Vehicule)vhVoie.element()).taille; i++){
            if(modele.rondpoint[(posInit+((Vehicule)vhVoie.element()).taille-i)%100] != null) {
                passagePossible = false;
                break;
            }
        }

        if (passagePossible) modele.insérerVehiculeDansRP(vhVoie,modele.tolerance);
    }


    @Override
    public void update(Observable observable, Object o) {

        setChanged();
        notifyObservers();
    }
}
