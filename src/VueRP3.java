import javax.swing.*;
import java.awt.*;
import java.awt.event.ActionEvent;
import java.awt.event.ActionListener;
import java.util.Observable;
import java.util.Observer;

public class VueRP3 extends Observable implements Observer, ActionListener{

    private JButton ajouteVoie1;
    private JButton ajouteVoie2;
    private JButton ajouteVoie3;
    private JFrame fenetre;
    private Jcanvas canvas;
    private JPanel panelGeneral;
    private JPanel panelBoutons;
    private JComboBox comboBox;
    private String[] s = {"SUD","EST","OUEST"};
    private JPanel choixSortie;
    private JSlider sliderTolerance;
    private JButton play_pause;




    public VueRP3(Observer obs) throws HeadlessException {


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
        this.ajouteVoie1 = new JButton("voie Sud");
        this.ajouteVoie1.setName(Const.BUTTON_1);
        this.ajouteVoie1.addActionListener(this);

        this.ajouteVoie2 = new JButton("voie Est");
        this.ajouteVoie2.setName(Const.BUTTON_2);
        this.ajouteVoie2.addActionListener(this);

        this.ajouteVoie3 = new JButton("voie Ouest");
        this.ajouteVoie3.setName(Const.BUTTON_3);
        this.ajouteVoie3.addActionListener(this);

        this.panelBoutons = new JPanel(new GridLayout(0,1));
        this.panelBoutons.add(ajouteVoie1);
        this.panelBoutons.add(ajouteVoie2);
        this.panelBoutons.add(ajouteVoie3);
        this.choixSortie.setLayout(new GridLayout(0,1));
        this.choixSortie.add(new JLabel("sortie"));
        this.choixSortie.add(comboBox);
        this.choixSortie.add(new JLabel("Tolérance"));
        this.choixSortie.add(sliderTolerance);
        this.play_pause.setName("play_pause");
        this.play_pause.addActionListener(this);
        this.panelBoutons.add(play_pause);

        this.panelBoutons.add(choixSortie);




        this.canvas = new Jcanvas(800,800, 3);

        this.panelGeneral = new JPanel();

        this.panelGeneral.setLayout(new BorderLayout());
        this.panelGeneral.add(canvas, BorderLayout.CENTER);
        this.panelGeneral.add(panelBoutons,BorderLayout.EAST);



        this.panelGeneral.setSize(canvas.getHeight()+panelBoutons.getHeight(),canvas.getWidth()+panelBoutons.getWidth());


        this.fenetre = new JFrame("Simulateur Rond-point 3 voies");
        this.fenetre.setSize(995,800);
        this.fenetre.setVisible(true);
        this.fenetre.setDefaultCloseOperation(JFrame.DISPOSE_ON_CLOSE);

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
