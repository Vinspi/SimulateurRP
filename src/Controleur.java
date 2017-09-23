import java.util.Observable;
import java.util.Observer;
import java.util.Timer;
import java.util.TimerTask;
import java.util.concurrent.ConcurrentLinkedQueue;


public class Controleur extends Observable implements Observer {



    private Modele modele;

    private ConcurrentLinkedQueue<EventRP> fifoEvent = null;

    Controleur(Modele modele){
        this.modele = modele;
        Timer timer = new Timer();
        TimerTask timerTask = new TimerTask() {
            @Override
            public void run() {
                verifFifoEvent();
            }
        };

        timer.schedule(timerTask,100);
    }

    @SuppressWarnings("unchecked")
    private void verifFifoEvent(){

        ConcurrentLinkedQueue<EventRP> tmp = null;  //Fifo dans laquelle on place les potentielles nouvelles demandes d'insertion reçues après l'insertion des véhicules des différentes voies.


        //On fait avancer tous les véhicules présents dans le rond-point.
        for(Vehicule vh : modele.getVhRP()){
            vh.avancer();
        }

        //Puis, on vérifie l'état de la file d'évènement.
        while(fifoEvent.size() > 0){
            if(fifoEvent.element().event.equals("voiture")){
                verifAjoutVoie((ConcurrentLinkedQueue<Vehicule>)fifoEvent.element().o,"voiture");
                fifoEvent.poll();
            }
            if(fifoEvent.element().event.equals("insertion")){
                verifInsertionRP((ConcurrentLinkedQueue<Vehicule>)fifoEvent.element().o);
                if(((ConcurrentLinkedQueue<Vehicule>) fifoEvent.element().o).size() != 0)
                    tmp.add(new EventRP((ConcurrentLinkedQueue<Vehicule>)fifoEvent.element().o,"insertion"));   //Ajoute la demande d'insertion du "nouveau premier" véhicule de la voie à la file d'évènements.
                fifoEvent.poll();
            }
        }
        //On ajoute toutes les nouvelles demandes d'insertion à la file à traiter lors du prochain tic d'horloge.
        for(EventRP event : tmp){
            fifoEvent.add(event);
        }

    }


    //Vérifie si le premier véhicule de la voie "voie" peut s'engager dans le rond-point, auquel cas notifie au modèle de le faire entrer.
    private void verifInsertionRP(ConcurrentLinkedQueue<Vehicule> voie){


        int posInit = 0;
        if (voie.equals(modele.getVhVoie1())){
            posInit = 0;

        }
        else if (voie.equals(modele.getVhVoie2())){
            posInit = 25;
        }
        else if (voie.equals(modele.getVhVoie3())){
            posInit = 50;

        }
        else if (voie.equals(modele.getVhVoie4())){
            posInit = 100;
        }

        boolean passagePossible = true;
        //On regarde dans le quart inférieur s'il y a un véhicule qui circule, et que le véhicule a la place pour s'insérer :
        for(int i = 0; i < modele.getTolerance() + (voie.element()).taille; i++){
            if(modele.getRondpoint()[(posInit+(voie.element()).taille-i)%100] != null) {
                passagePossible = false;
                break;
            }
        }

        //Si le véhicule peut s'insérer, alors on dit au modèle de l'insérer.
        if (passagePossible) modele.insérerVehiculeDansRP(voie);

    }


    //Vérifie si on peut ajouer un véhicule à une voie.
    private void verifAjoutVoie(ConcurrentLinkedQueue<Vehicule> voie, String typeVehicule){


        //S'il reste de la place dans la voie, on demande au modèle d'ajouter à la voie un nouveau véhicule du type demandé (par défaut une voiture).
        if (voie.size() < 5) {
            if (typeVehicule.equals("Voiture"))
                modele.addVehiculeToVoie(new Voiture(-1),voie);
                if(voie.size() == 0) fifoEvent.add(new EventRP(voie,"insertion"));  //Si la file est vide, on ajoute l'évènement "Insertion dans le rond-point" à la file d'évènements.
        }


    }

    @Override
    public void update(Observable observable, Object o) {

        switch((String)o){
            case "voie1Voiture":
                fifoEvent.add(new EventRP(modele.getVhVoie1(),"voiture"));
                break;
            case "voie2Voiture":
                fifoEvent.add(new EventRP(modele.getVhVoie2(),"voiture"));
                break;
            case "voie3Voiture":
                fifoEvent.add(new EventRP(modele.getVhVoie3(),"voiture"));
                break;
            case "voie4Voiture":
                fifoEvent.add(new EventRP(modele.getVhVoie4(),"voiture"));
                break;
            default:
                System.out.println("Erreur quelque part");
                break;
        }
    }
}
