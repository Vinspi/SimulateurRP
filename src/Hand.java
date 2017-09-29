public class Hand {
    public static void main(String[] args) {

        Modele modele = new Modele(3);
        Controleur controleur = new Controleur(modele);

        VueRP3 vue = new VueRP3(controleur);

        modele.addObserver(vue);
    }
}
