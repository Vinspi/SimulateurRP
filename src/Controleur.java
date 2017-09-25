import java.util.*;
import java.util.concurrent.ConcurrentLinkedQueue;


public class Controleur extends Observable implements Observer {

    public class CoupleVV{

        Object o;
        ConcurrentLinkedQueue<Vehicule> voie;

        public CoupleVV(Vehicule vehicule, ConcurrentLinkedQueue<Vehicule> voie){
            this.o = vehicule;
            this.voie = voie;
        }

        public CoupleVV(ConcurrentLinkedQueue<Vehicule> voieEntree, ConcurrentLinkedQueue<Vehicule> voieSortie){
            this.o = voieEntree;
            this.voie = voieSortie;
        }
    }

    private Modele modele;

    private ConcurrentLinkedQueue<EventRP> fifoEvent = null;

    Controleur(Modele modele){

        this.addObserver(modele);

        this.modele = modele;
        this.fifoEvent = new ConcurrentLinkedQueue<>();
        Timer timer = new Timer();
        TimerTask timerTask = new TimerTask() {
            @Override
            public void run(){
                //System.out.println("[Clock] Nombre d'elements dans FifoEvent :" + fifoEvent.size());
                //System.out.println("[Clock] Nombre de voitures circulant dans le rond-point :" + modele.getVhRP().size());
                /*for(Vehicule v : modele.getVhRP()){
                    System.out.print("| "+v.getPos() + " ");
                }*/
                //System.out.println();
                verifFifoEvent();
            }
        };



        timer.schedule(timerTask,0, 60);

    }


    @SuppressWarnings("unchecked")
    private void verifFifoEvent() {

        //double debut = System.nanoTime();

        int size = fifoEvent.size();
        ConcurrentLinkedQueue<EventRP> tmp = new ConcurrentLinkedQueue<>();  //Fifo dans laquelle on place les potentielles nouvelles demandes d'insertion reçues après l'insertion des véhicules des différentes voies.
        ArrayList<Vehicule> tmpSortie = new ArrayList<>();                  //Tampon où mettre les véhicules qui sont sortis, puisqu'on ne peut pas sortir les véhicules pendant le parcours du rond-point.

        //On fait avancer tous les véhicules présents dans le rond-point, et on note ceux qui en sortent.
        if (modele.getVhRP() != null) {
            for (Vehicule vh : modele.getVhRP()) {
                if (faireAvancerVehicule(vh) == 0){
                    tmpSortie.add(vh);
                };
            }
        }
        for(Vehicule vh : tmpSortie){
            modele.getVhRP().remove(vh);
        }



        //Puis, on vérifie l'état de la file d'évènement.

        if (fifoEvent != null){
            while (size > 0) {

                if (fifoEvent.element().event.equals("voitureSortie1")) {
                    verifAjoutVoie((ConcurrentLinkedQueue<Vehicule>) fifoEvent.element().o, "voitureSortie1");
                    fifoEvent.poll();
                    size--;
                }
                else if (fifoEvent.element().event.equals("voitureSortie2")) {
                    verifAjoutVoie((ConcurrentLinkedQueue<Vehicule>) fifoEvent.element().o, "voitureSortie2");
                    fifoEvent.poll();
                    size--;
                }
                else if (fifoEvent.element().event.equals("voitureSortie3")) {
                    verifAjoutVoie((ConcurrentLinkedQueue<Vehicule>) fifoEvent.element().o, "voitureSortie3");
                    fifoEvent.poll();
                    size--;
                }
                else if (fifoEvent.element().event.equals("voitureSortie4")) {
                    verifAjoutVoie((ConcurrentLinkedQueue<Vehicule>) fifoEvent.element().o, "voitureSortie4");
                    fifoEvent.poll();
                    size--;
                }



                else if (fifoEvent.element().event.equals("insertion")) {

                    //Si on a pu insérer le premier élément de la voie dans le rond-point, alors on ajoute la demande d'insertion du "nouveau premier" véhicule de la voie à la file d'évènements, s'il existe.
                    if(verifInsertionRP((ConcurrentLinkedQueue<Vehicule>) fifoEvent.element().o)){


                        if (((ConcurrentLinkedQueue<Vehicule>) fifoEvent.element().o).size() != 0)
                            tmp.add(new EventRP((ConcurrentLinkedQueue<Vehicule>) fifoEvent.element().o, "insertion"));

                    }
                    //Sinon, on remet la demande d'insertion dans la fifo pour le prochain tick d'horloge.
                    else {
                        tmp.add(fifoEvent.element());
                    }
                    fifoEvent.poll();
                    size--;
                }
            }

        }
        //On ajoute toutes les nouvelles demandes d'insertion, ainsi que les refusées, à la file à traiter lors du prochain tic d'horloge.
        if(fifoEvent != null) {
            for (EventRP event : tmp) {
                fifoEvent.add(event);
            }
        }
        //double fin = System.nanoTime();

        //System.out.println("time elapsed (sec) : "+(fin-debut)/Math.pow(10,9));
    }


    //Vérifie si le premier véhicule de la voie "voie" peut s'engager dans le rond-point, auquel cas notifie au modèle de le faire entrer.
    //S'il a pu passer, alors renvoie True, sinon False.
    private boolean verifInsertionRP(ConcurrentLinkedQueue<Vehicule> voie){


        int posInit = -1;
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
            posInit = 75;
        }

        boolean passagePossible = true;
        //On regarde dans le quart inférieur s'il y a un véhicule qui circule, et que le véhicule a la place pour s'insérer :
        for(int i = 0; i < modele.getTolerance() + (voie.peek()).getTaille(); i++){

            if(modele.getRondpoint()[Math.floorMod((posInit+(voie.peek()).getTaille()-i),100)] != null) {
                passagePossible = false;
                break;
            }
        }

        //Si le véhicule peut s'insérer, alors on dit au modèle de l'insérer.
        if (passagePossible){
            setChanged();
            notifyObservers(voie);
            return true;
        }
        return false;

    }


    //Vérifie si on peut ajouer un véhicule à une voie.
    private void verifAjoutVoie(ConcurrentLinkedQueue<Vehicule> voie, String typeVehicule){

        int voiePrev = 0;
        if(voie.equals(modele.getVhVoie1())) voiePrev = 1;
        else if(voie.equals(modele.getVhVoie2())) voiePrev = 2;
        else if(voie.equals(modele.getVhVoie3())) voiePrev = 3;
        else if(voie.equals(modele.getVhVoie4())) voiePrev = 4;


        if(fifoEvent == null) return;

        //S'il reste de la place dans la voie, on demande au modèle d'ajouter à la voie un nouveau véhicule du type demandé (par défaut une voiture).
        if (voie.size() < Const.NB_MAX_VOIE) {
            switch (typeVehicule) {
                case "voitureSortie1":
                    setChanged();
                    notifyObservers(new EventRP(new CoupleVV(new Voiture(-voiePrev, 1), voie), "ajout"));
                    if (voie.size() == 1) {    //Si le nouvel arrivant est le seul à circuler sur la voie, alors on vérifie s'il peut s'engager dans le rond-point.
                        EventRP rp = new EventRP(voie, "insertion");
                        fifoEvent.add(rp);
                        System.out.println("------------- Insertion " + rp.o + " ajouté à la fifo -----------");
                    }
                    break;

                case "voitureSortie2":
                    setChanged();
                    notifyObservers(new EventRP(new CoupleVV(new Voiture(-voiePrev, 2), voie), "ajout"));
                    if (voie.size() == 1) {    //Si le nouvel arrivant est le seul à circuler sur la voie, alors on vérifie s'il peut s'engager dans le rond-point.
                        EventRP rp = new EventRP(voie, "insertion");
                        fifoEvent.add(rp);
                        System.out.println("------------- Insertion " + rp.o + " ajouté à la fifo -----------");
                    }
                    break;

                case "voitureSortie3":
                    setChanged();
                    notifyObservers(new EventRP(new CoupleVV(new Voiture(-voiePrev, 3), voie), "ajout"));
                    if (voie.size() == 1) {    //Si le nouvel arrivant est le seul à circuler sur la voie, alors on vérifie s'il peut s'engager dans le rond-point.
                        EventRP rp = new EventRP(voie, "insertion");
                        fifoEvent.add(rp);
                        System.out.println("------------- Insertion " + rp.o + " ajouté à la fifo -----------");
                    }
                    break;

                case "voitureSortie4":
                    setChanged();
                    notifyObservers(new EventRP(new CoupleVV(new Voiture(-voiePrev, 4), voie), "ajout"));
                    if (voie.size() == 1) {    //Si le nouvel arrivant est le seul à circuler sur la voie, alors on vérifie s'il peut s'engager dans le rond-point.
                        EventRP rp = new EventRP(voie, "insertion");
                        fifoEvent.add(rp);
                        System.out.println("------------- Insertion " + rp.o + " ajouté à la fifo -----------");
                    }
                    break;


            }
        }

    }



    private int faireAvancerVehicule(Vehicule vh){

        int posSortie = Math.floorMod(((vh.getSortiePrevue()-1)*25),100);    //Variable de la position à atteindre pour sortir du rond-point.

        if (Math.floorMod(vh.getPos()+vh.getTaille()+1,100) == posSortie){                //Si nouvelle position après avancée == position de la sortie :
            System.out.println("--------------Sortie----------------");
            setChanged();
            notifyObservers(new EventRP(vh,"sortie"));
            return 0;
        }
        else {
            //System.out.println("--------Déplacement----------");
            setChanged();
            notifyObservers(new EventRP(vh,"deplacement"));
            return 1;
        }

    }

    @Override
    public void update(Observable observable, Object o) {


        System.out.println("\n[C-Event] Observable updated -> " +  observable.getClass().getName());

        if(o instanceof EventRP) {
            EventRP event = (EventRP) o;
            int tolerance = (int) event.o;
            modele.setTolerance(tolerance);
            switch (event.event) {

                //Ajout dans voie1 vers les 4 autres voies :
                case Const.ADD_Vehicule_V1_To_V1:
                    System.out.println("[C-Event] Ajout véhicule en voie 1 vers la voie 1\n");
                    if (fifoEvent != null) fifoEvent.add(new EventRP(modele.getVhVoie1(), "voitureSortie1"));
                    break;
                case Const.ADD_Vehicule_V1_To_V2:
                    System.out.println("[C-Event] Ajout véhicule en voie 1 vers la voie 2\n");
                    if (fifoEvent != null) fifoEvent.add(new EventRP(modele.getVhVoie1(), "voitureSortie2"));
                    break;
                case Const.ADD_Vehicule_V1_To_V3:
                    System.out.println("[C-Event] Ajout véhicule en voie 1 vers la voie 3\n");
                    if (fifoEvent != null) fifoEvent.add(new EventRP(modele.getVhVoie1(), "voitureSortie3"));
                    break;
                case Const.ADD_Vehicule_V1_To_V4:
                    System.out.println("[C-Event] Ajout véhicule en voie 1 vers la voie 4\n");
                    if (fifoEvent != null) fifoEvent.add(new EventRP(modele.getVhVoie1(), "voitureSortie4"));
                    break;


                //Ajout dans voie2 vers les 4 autres voies :
                case Const.ADD_Vehicule_V2_To_V1:
                    System.out.println("[C-Event] Ajout véhicule en voie 2 vers la voie 1\n");
                    if (fifoEvent != null) fifoEvent.add(new EventRP(modele.getVhVoie2(), "voitureSortie1"));
                    break;
                case Const.ADD_Vehicule_V2_To_V2:
                    System.out.println("[C-Event] Ajout véhicule en voie 2 vers la voie 2\n");
                    if (fifoEvent != null) fifoEvent.add(new EventRP(modele.getVhVoie2(), "voitureSortie2"));
                    break;
                case Const.ADD_Vehicule_V2_To_V3:
                    System.out.println("[C-Event] Ajout véhicule en voie 2 vers la voie 3\n");
                    if (fifoEvent != null) fifoEvent.add(new EventRP(modele.getVhVoie2(), "voitureSortie3"));
                    break;
                case Const.ADD_Vehicule_V2_To_V4:
                    System.out.println("[C-Event] Ajout véhicule en voie 2 vers la voie 4\n");
                    if (fifoEvent != null) fifoEvent.add(new EventRP(modele.getVhVoie2(), "voitureSortie4"));
                    break;


                //Ajout dans voie3 vers les 4 autres voies :
                case Const.ADD_Vehicule_V3_To_V1:
                    System.out.println("[C-Event] Ajout véhicule en voie 3 vers la voie 1\n");
                    if (fifoEvent != null) fifoEvent.add(new EventRP(modele.getVhVoie3(), "voitureSortie1"));
                    break;
                case Const.ADD_Vehicule_V3_To_V2:
                    System.out.println("[C-Event] Ajout véhicule en voie 3 vers la voie 2\n");
                    if (fifoEvent != null) fifoEvent.add(new EventRP(modele.getVhVoie3(), "voitureSortie2"));
                    break;
                case Const.ADD_Vehicule_V3_To_V3:
                    System.out.println("[C-Event] Ajout véhicule en voie 3 vers la voie 3\n");
                    if (fifoEvent != null) fifoEvent.add(new EventRP(modele.getVhVoie3(), "voitureSortie3"));
                    break;
                case Const.ADD_Vehicule_V3_To_V4:
                    System.out.println("[C-Event] Ajout véhicule en voie 3 vers la voie 4\n");
                    if (fifoEvent != null) fifoEvent.add(new EventRP(modele.getVhVoie3(), "voitureSortie4"));
                    break;


                //Ajout dans voie4 vers les 4 autres voies :
                case Const.ADD_Vehicule_V4_To_V1:
                    System.out.println("[C-Event] Ajout véhicule en voie 4 vers la voie 1\n");
                    if (fifoEvent != null) fifoEvent.add(new EventRP(modele.getVhVoie4(), "voitureSortie1"));
                    break;
                case Const.ADD_Vehicule_V4_To_V2:
                    System.out.println("[C-Event] Ajout véhicule en voie 4 vers la voie 2\n");
                    if (fifoEvent != null) fifoEvent.add(new EventRP(modele.getVhVoie4(), "voitureSortie2"));
                    break;
                case Const.ADD_Vehicule_V4_To_V3:
                    System.out.println("[C-Event] Ajout véhicule en voie 4 vers la voie 3\n");
                    if (fifoEvent != null) fifoEvent.add(new EventRP(modele.getVhVoie4(), "voitureSortie3"));
                    break;
                case Const.ADD_Vehicule_V4_To_V4:
                    System.out.println("[C-Event] Ajout véhicule en voie 4 vers la voie 4\n");
                    if (fifoEvent != null) fifoEvent.add(new EventRP(modele.getVhVoie4(), "voitureSortie4"));
                    break;


                default:
                    System.out.println("Erreur quelque part");
                    break;
            }
        }
        else if (o instanceof String){
            if(((String) o).equals("play_pause")){
                modele.switchPlayPause();
            }
        }
    }

}
