/**
 * Created by deutsch on 23/09/17.
 */
public class Hand {

    public static void main(String[] args) {

        Modele modele = new Modele();
        Controleur controleur = new Controleur(modele);




        VueRP4 vue = new VueRP4(controleur);

        modele.addObserver(vue);
    }
}
