import javax.swing.*;
import javax.swing.border.Border;
import java.awt.*;
import java.util.Observable;
import java.util.Observer;

/**
 * Created by vinspi on 23/09/17.
 */
public class VueRP4 extends Observable implements Observer{
    private Image background;
    private JButton ajouteVoieSud;
    private JButton ajouteVoieNord;
    private JButton ajouteVoieEst;
    private JButton ajouteVoieOuest;
    private JFrame fenetre;
    private Jcanvas canvas;
    private JPanel panelGeneral;
    private JPanel panelBoutons;
    private BorderLayout layoutgeneral;

    public VueRP4() throws HeadlessException {

        this.layoutgeneral = new BorderLayout();
        this.ajouteVoieSud = new JButton("voie Sud");
        this.ajouteVoieNord = new JButton("voie Nord");
        this.ajouteVoieEst = new JButton("voie Est");
        this.ajouteVoieOuest = new JButton("voie Ouest");

        this.panelBoutons = new JPanel();
        this.panelBoutons.add(ajouteVoieSud);
        this.panelBoutons.add(ajouteVoieEst);
        this.panelBoutons.add(ajouteVoieOuest);
        this.panelBoutons.add(ajouteVoieNord);

        this.canvas = new Jcanvas();

        this.panelGeneral = new JPanel();
        this.panelGeneral.setLayout(new BorderLayout());
        this.panelGeneral.add(canvas, BorderLayout.CENTER);
        this.panelGeneral.add(panelBoutons,BorderLayout.SOUTH);



        this.fenetre = new JFrame();
        this.fenetre.setSize(800,600);
        this.fenetre.setVisible(true);
        this.fenetre.setDefaultCloseOperation(JFrame.EXIT_ON_CLOSE);

        this.fenetre.getContentPane().add(panelGeneral);
    }

    @Override
    public void update(Observable observable, Object o) {

    }
}
