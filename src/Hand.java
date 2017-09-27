public class Hand {
    public static void main(String[] args) {

        Modele modele = new Modele(4);
        Controleur controleur = new Controleur(modele);

        VueRP3 vue = new VueRP3(controleur);

        modele.addObserver(vue);
    }
}
