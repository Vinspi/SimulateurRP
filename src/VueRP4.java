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
    private JComboBox comboBox;
    private String[] s = {"SUD","EST","NORD","OUEST"};
    private JPanel choixSortie;
    private JSlider sliderTolerance;
    private JButton play_pause;




    public VueRP4(Observer obs) throws HeadlessException {


        this.addObserver(obs);
        this.play_pause = new JButton("Pause");
        this.sliderTolerance = new JSlider(5,100);
        this.sliderTolerance.setMajorTickSpacing(25);
        this.sliderTolerance.setMinorTickSpacing(5);
        this.sliderTolerance.setPaintTicks(true);
        this.sliderTolerance.setPaintLabels(true);
        this.sliderTolerance.setValue(25);
        this.comboBox = new JComboBox(s);
        this.choixSortie = new JPanel();
        this.ajouteVoieSud = new JButton("voie Sud");
        this.ajouteVoieSud.setName(Const.BUTTON_SUD);
        this.ajouteVoieSud.addActionListener(this);

        this.ajouteVoieNord = new JButton("voie Nord");
        this.ajouteVoieNord.setName(Const.BUTTON_NORD);
        this.ajouteVoieNord.addActionListener(this);

        this.ajouteVoieEst = new JButton("voie Est");
        this.ajouteVoieEst.setName(Const.BUTTON_EST);
        this.ajouteVoieEst.addActionListener(this);

        this.ajouteVoieOuest = new JButton("voie Ouest");
        this.ajouteVoieOuest.setName(Const.BUTTON_OUEST);
        this.ajouteVoieOuest.addActionListener(this);

        this.panelBoutons = new JPanel(new GridLayout(0,1));
        this.panelBoutons.add(ajouteVoieSud);
        this.panelBoutons.add(ajouteVoieNord);
        this.panelBoutons.add(ajouteVoieEst);
        this.panelBoutons.add(ajouteVoieOuest);
        this.choixSortie.setLayout(new GridLayout(0,1));
        this.choixSortie.add(new JLabel("sortie"));
        this.choixSortie.add(comboBox);
        this.choixSortie.add(new JLabel("Tolérance"));
        this.choixSortie.add(sliderTolerance);
        this.play_pause.setName("play_pause");
        this.play_pause.addActionListener(this);
        this.panelBoutons.add(play_pause);

        this.panelBoutons.add(choixSortie);




        this.canvas = new Jcanvas(800,800);

        this.panelGeneral = new JPanel();

        this.panelGeneral.setLayout(new BorderLayout());
        this.panelGeneral.add(canvas, BorderLayout.CENTER);
        this.panelGeneral.add(panelBoutons,BorderLayout.EAST);



        this.panelGeneral.setSize(canvas.getHeight()+panelBoutons.getHeight(),canvas.getWidth()+panelBoutons.getWidth());


        this.fenetre = new JFrame("Simulateur Rond-point 4 voies");
        this.fenetre.setSize(995,800);
        this.fenetre.setVisible(true);
        this.fenetre.setDefaultCloseOperation(JFrame.EXIT_ON_CLOSE);

        this.fenetre.getContentPane().add(panelGeneral);

        this.fenetre.repaint();

    }



    @Override
    public void update(Observable observable, Object o) {

        if(o instanceof EventRP){
            EventRP event = (EventRP) o;
            Vehicule vehicule;
            Controleur.CoupleVV coupleVV;

            switch (event.event){
                case "ajout":
                    coupleVV = (Controleur.CoupleVV) (event.o);
                    this.canvas.addDrawable((Vehicule) coupleVV.o);
                    this.canvas.repaint();
                    break;
                case "deplacement":
                    double debut = System.nanoTime();
                    this.canvas.repaint();
                    double fin = System.nanoTime();
                    break;
                case "sortie":
                    System.out.println("evenement de sortie");
                    vehicule = ((Vehicule) event.o);
                    this.canvas.removeDrawable(vehicule);
                    this.canvas.repaint();
                    break;



            }
        }

    }

    @Override
    public void actionPerformed(ActionEvent e) {
        String BTN_NAME = ((JButton) e.getSource()).getName();
        System.out.println("BTN_NAME : "+BTN_NAME);
        System.out.println("Tolerance : "+this.sliderTolerance.getValue());
        if(BTN_NAME.equals("play_pause")){
            setChanged();
            notifyObservers("play_pause");
            if(this.play_pause.getText().equals("Pause")){
                this.play_pause.setText("Play");
            }
            else {
                this.play_pause.setText("Pause");
            }
        }
        else {
            setChanged();
            notifyObservers(new EventRP(this.sliderTolerance.getValue(), BTN_NAME + "_V" + (this.comboBox.getSelectedIndex() + 1)));
        }
    }
}
