import javax.swing.*;
import java.awt.*;
import java.awt.event.ActionEvent;
import java.awt.event.ActionListener;
import java.util.Observable;
import java.util.Observer;

/**
 * Created by vinspi on 23/09/17.
 */
public class VueRP4 extends Observable implements Observer, ActionListener{

    private JButton ajouteVoieSud;
    private JButton ajouteVoieNord;
    private JButton ajouteVoieEst;
    private JButton ajouteVoieOuest;
    private JFrame fenetre;
    private Jcanvas canvas;
    private JPanel panelGeneral;
    private JPanel panelBoutons;


    public VueRP4() throws HeadlessException {




        this.ajouteVoieSud = new JButton("voie Sud");
        this.ajouteVoieSud.setName(Const.ADD_Vehicule_V1_To_V1);
        this.ajouteVoieSud.addActionListener(this);

        this.ajouteVoieNord = new JButton("voie Nord");
        this.ajouteVoieNord.setName(Const.ADD_Vehicule_V2_To_V2);
        this.ajouteVoieNord.addActionListener(this);

        this.ajouteVoieEst = new JButton("voie Est");
        this.ajouteVoieEst.setName(Const.ADD_Vehicule_V3_To_V3);
        this.ajouteVoieEst.addActionListener(this);

        this.ajouteVoieOuest = new JButton("voie Ouest");
        this.ajouteVoieOuest.setName(Const.ADD_Vehicule_V4_To_V4);
        this.ajouteVoieOuest.addActionListener(this);

        this.panelBoutons = new JPanel(new GridLayout(0,1));
        this.panelBoutons.add(ajouteVoieSud);
        this.panelBoutons.add(ajouteVoieNord);
        this.panelBoutons.add(ajouteVoieEst);
        this.panelBoutons.add(ajouteVoieOuest);




        this.canvas = new Jcanvas(800,800);

        this.panelGeneral = new JPanel();

        this.panelGeneral.setLayout(new BorderLayout());
        this.panelGeneral.add(canvas, BorderLayout.CENTER);
        this.panelGeneral.add(panelBoutons,BorderLayout.EAST);



        this.panelGeneral.setSize(canvas.getHeight()+panelBoutons.getHeight(),canvas.getWidth()+panelBoutons.getWidth());


        this.fenetre = new JFrame("Simulateur Rond-point 4 voies");
        this.fenetre.setSize(915,800);
        this.fenetre.setVisible(true);
        this.fenetre.setDefaultCloseOperation(JFrame.EXIT_ON_CLOSE);

        this.fenetre.getContentPane().add(panelGeneral);

    }

    @Override
    public void update(Observable observable, Object o) {

    }

    @Override
    public void actionPerformed(ActionEvent e) {
        String BTN_NAME = ((JButton) e.getSource()).getName();
        setChanged();
        notifyObservers(BTN_NAME);
    }
}
