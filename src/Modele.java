import javax.naming.ldap.Control;
import java.util.ArrayList;
import java.util.Observable;
import java.util.Observer;
import java.util.concurrent.ConcurrentLinkedQueue;

public class Modele extends Observable implements Observer {

    private ConcurrentLinkedQueue<Vehicule> vhVoie1;
    private ConcurrentLinkedQueue<Vehicule> vhVoie2;
    private ConcurrentLinkedQueue<Vehicule> vhVoie3;
    private ConcurrentLinkedQueue<Vehicule> vhVoie4;
    private ConcurrentLinkedQueue<Vehicule> vhVoie5;
    private ConcurrentLinkedQueue<Vehicule> vhVoie6;
    private boolean runAnim = true;

    private int nbVoie;

    private ArrayList<Vehicule> vhRP;
    private Vehicule rondpoint[] = new Vehicule[100]; //Taille fixé à 100 pour le moment.

    //Peut être mieux de le mettre pour chaque véhicule.
    //Fixé à 25 (1/4 du rond-point) pour le moment.
    private int tolerance = 25;

    public void switchPlayPause(){
        this.runAnim = !this.runAnim;
    }

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

    public ConcurrentLinkedQueue<Vehicule> getVhVoie5() {
        return vhVoie5;
    }
    public void setVhVoie5(ConcurrentLinkedQueue<Vehicule> vhVoie5) {
        this.vhVoie5 = vhVoie5;
    }

    public ConcurrentLinkedQueue<Vehicule> getVhVoie6() {
        return vhVoie6;
    }
    public void setVhVoie6(ConcurrentLinkedQueue<Vehicule> vhVoie6) {
        this.vhVoie6 = vhVoie6;
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

    public int getNbVoie() {
        return nbVoie;
    }

    public int getTolerance() {
        return tolerance;
    }
    public void setTolerance(int tolerance) {
        this.tolerance = tolerance;
    }

    public Modele(int nb){
        vhVoie1 = new ConcurrentLinkedQueue<>();
        vhVoie2 = new ConcurrentLinkedQueue<>();
        vhVoie3 = new ConcurrentLinkedQueue<>();
        vhVoie4 = new ConcurrentLinkedQueue<>();
        vhVoie5 = new ConcurrentLinkedQueue<>();
        vhVoie6 = new ConcurrentLinkedQueue<>();
        vhRP = new ArrayList<>();

        nbVoie = nb;
    }


    //Ajoute un véhicule dans le fifo de la voie "voie".
    private void addVehiculeToVoie(Vehicule vh, ConcurrentLinkedQueue<Vehicule> voie){



        vh.setPos(((vh.getPos()+1)*Const.NB_MAX_VOIE)-1-voie.size());
        voie.add(vh);
        System.out.println("------------- Véhicule ajouté à la voie :" + voie.element() + " à la pos : "+vh.getPos()+"- -----------");
    }


    //Fait s'insérer le premier véhicule de la voie dans le rond-point.
    //Deux solutions afin de localiser le véhicule :
    // -> associer la pos -X au véhicule, avec X la première case sur laquelle le véhicule va passer en s'engageant dans le rond-point. /!\ A la fonction drawVehicule qui devra dessiner les vh des fifo en fonction de leur position dans la fifo et non de pos.
    // -> switch case sur les voies.
    private void insérerVehiculeDansRP(ConcurrentLinkedQueue<Vehicule> voie)
    {
        int posInit = 0;
        ConcurrentLinkedQueue<Vehicule> vhVoie = vhVoie1;

        if (voie.equals(vhVoie2)){
            posInit = 100/getNbVoie();
            vhVoie = vhVoie2;
        }
        else if (voie.equals(vhVoie3)){
            posInit = (100/getNbVoie()) * 2;
            vhVoie = vhVoie3;
        }
        else if (voie.equals(vhVoie4)){
            posInit = (100/getNbVoie()) * 3;
            vhVoie = vhVoie4;
        }
        else if (voie.equals(vhVoie5)){
            posInit = (100/getNbVoie()) * 4;
            vhVoie = vhVoie5;
        }
        else if (voie.equals(vhVoie6)){
            posInit = (100/getNbVoie()) * 5;
            vhVoie = vhVoie6;
        }


        //On place le véhicule dans le rond-point.

        vhVoie.element().setPos(posInit);
        vhRP.add(vhVoie.element());
        for(int k = 0; k < voie.element().getTaille(); k++){
            rondpoint[Math.floorMod((posInit+k),100)] = voie.element();
        }

        //On le retire du fifo de sa voie.

        vhVoie.poll();

        /* uniquement pour faire une jouli vue */
        for(Vehicule v : vhVoie)
            v.pos++;

        System.out.println("------------ Véhicule inséré dans le rond-point.--------------");



    }




    @Override
    @SuppressWarnings("unchecked")
    public void update(Observable observable, Object o) {

        if(o instanceof EventRP){

            switch (((EventRP) o).event) {
                case "ajout":
                    addVehiculeToVoie(((Vehicule) ((Controleur.CoupleVV) ((EventRP) o).o).o), ((Controleur.CoupleVV) ((EventRP) o).o).voie);

                    setChanged();
                    notifyObservers(new EventRP((Controleur.CoupleVV) ((EventRP) o).o, "ajout"));

                    break;

                case "deplacement":
                    if(runAnim) {
                        rondpoint[Math.floorMod(((Vehicule) ((EventRP) o).o).getPos() + ((Vehicule) ((EventRP) o).o).getTaille(), 100)] = ((Vehicule) ((EventRP) o).o);
                        rondpoint[((Vehicule) ((EventRP) o).o).getPos()] = null;
                        ((Vehicule) ((EventRP) o).o).setPos(Math.floorMod(((Vehicule) ((EventRP) o).o).getPos() + 1, 100));
                        setChanged();
                        notifyObservers(o);
                    }
                    break;

                case "sortie":
                    for (int i = 0; i < ((Vehicule) ((EventRP) o).o).getTaille(); i++) {
                        rondpoint[((Vehicule) ((EventRP) o).o).getPos() + i] = null;
                    }
                    setChanged();
                    notifyObservers(o);
                    break;

                default:
                    break;


            }

        }
        else if (o.equals(vhVoie1) || o.equals(vhVoie2) || o.equals(vhVoie3) || o.equals(vhVoie4) || o.equals(vhVoie5) || o.equals(vhVoie6)){
            insérerVehiculeDansRP((ConcurrentLinkedQueue<Vehicule>)o);
        }




    }
}