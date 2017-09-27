import java.awt.*;
import java.awt.event.ActionEvent;
import java.awt.event.ActionListener;
import javax.swing.*;

public class Ztest extends JFrame {
    private JPanel container = new JPanel();
    private JButton go = new JButton("GO");
    private JRadioButton vh3 = new JRadioButton("  > 3 Voies <");
    private JRadioButton vh4 = new JRadioButton("  > 4 Voies <");
    private JRadioButton vh5 = new JRadioButton("  > 5 Voies <");
    private JRadioButton vh6 = new JRadioButton("  > 6 Voies <");
    private ButtonGroup bg = new ButtonGroup();

    String Voie = "  > 4 Voies <";

    public Ztest(){
        JPanel top = new JPanel(new GridLayout(0,2));

        this.setTitle("Menu");
        this.setSize(250, 300);
        this.setDefaultCloseOperation(JFrame.DISPOSE_ON_CLOSE);
        this.setLocationRelativeTo(null);
        container.setBackground(Color.white);
        container.setLayout(new BorderLayout());
        vh3.addActionListener(new StateListener());
        vh4.addActionListener(new StateListener());
        vh5.addActionListener(new StateListener());
        vh6.addActionListener(new StateListener());

        // 4 Voies par défaut
        vh4.setSelected(true);

        go.addActionListener(new GoListener());

        bg.add(vh3);
        bg.add(vh4);
        bg.add(vh5);
        bg.add(vh6);

        top.add(vh3);
        top.add(vh4);
        top.add(vh5);
        top.add(vh6);

        container.add(top, BorderLayout.CENTER);
        container.add(go, BorderLayout.SOUTH);
        this.setContentPane(container);
        this.setVisible(true);
    }

    class StateListener implements ActionListener{
        public void actionPerformed(ActionEvent e) {
            Voie = ((JRadioButton)e.getSource()).getText();
        }
    }

    class GoListener implements ActionListener{
        public void actionPerformed(ActionEvent e) {
            if(Voie == "  > 3 Voies <")
            {
                Modele modele = new Modele(3);
                Controleur controleur = new Controleur(modele);

                VueRP3 vue = new VueRP3(controleur);

                modele.addObserver(vue);
            }
            else if(Voie == "  > 4 Voies <")
            {
                Modele modele = new Modele(4);
                Controleur controleur = new Controleur(modele);

                VueRP4 vue = new VueRP4(controleur);

                modele.addObserver(vue);
            }
            else if(Voie == "  > 5 Voies <")
            {
                Modele modele = new Modele(5);
                Controleur controleur = new Controleur(modele);

                VueRP5 vue = new VueRP5(controleur);

                modele.addObserver(vue);
            }
            else if(Voie == "  > 6 Voies <")
            {
                Modele modele = new Modele(6);
                Controleur controleur = new Controleur(modele);

                VueRP6 vue = new VueRP6(controleur);

                modele.addObserver(vue);
            }
        }
    }

    public static void main(String[] args) {
        Ztest Z = new Ztest();
    }
}