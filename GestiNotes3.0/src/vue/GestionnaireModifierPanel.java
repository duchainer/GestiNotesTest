package vue;

/**
 * Crée 2016-11-04,16:05
 *
 * TODO: -actionPerformed
 *
 * @author Patrick Domingues
 */
import modele.Evaluation;
import modele.Groupe;
import modele.Eleve;
import java.awt.GridLayout;
import java.awt.HeadlessException;
import java.awt.event.ActionEvent;
import java.awt.event.ActionListener;
import java.util.ArrayList;
import javax.swing.JButton;
import javax.swing.JComboBox;
import javax.swing.JFrame;
import javax.swing.JLabel;
import javax.swing.JOptionPane;
import javax.swing.JTabbedPane;

public class GestionnaireModifierPanel extends UtilePanel {

    //variables
    final int NBR_NOTES = 4;
    JComboBox<Groupe> comboBox1;
    JFrame uneFrame;
    JTabbedPane unTabbedPane;
    Groupe g;

    //Méthodes
    //Constructeur
    public GestionnaireModifierPanel(JFrame frame, Groupe g, JTabbedPane tabbedPane) {
        super();
        this.uneFrame = frame;
        this.unTabbedPane = tabbedPane;
        this.g = g;
        GridLayout gl = new GridLayout(10, 2, 0, 25);	//Cree GridLayout
        simplePanel.setLayout(gl);

        addLabel("Choisissez un élève: ");
        comboBox1 = new JComboBox<Groupe>();
        remplir(comboBox1);
        simplePanel.add(comboBox1);
        comboBox1.addActionListener(new ActionListener() {
            @Override
            public void actionPerformed(ActionEvent e) {
                modifier();
            }
        });

        addChamp("Note 1: ");
        addChamp("Note 2: ");
        addChamp("Note 3: ");
        addChamp("Note 4: ");

        addBouton("Enregistrer");

    }
    //Autres Méthodes

    @Override
    public void actionPerformed(ActionEvent event) {  // Methode recoit evenement
        //uneFrame.getContentPane().add(new GestionnairePanel(uneFrame, unTabbedPane));
        if (((JButton) event.getSource()).getText() == "Enregistrer") {
            try {
                String code = (String) comboBox1.getSelectedItem();
                int num = Integer.parseInt(code.substring(6, 7));
                Eleve eleve = g.getTabEleve().get(num);

                ArrayList<Evaluation> evaluations = eleve.getTabEvaluation();
                for (int i = 0; i < NBR_NOTES; i++) {
                    evaluations.get(i).setNote(Float.parseFloat(getChamp(i).getText()));
                }
                uneFrame.getContentPane().removeAll();
                //uneFrame.setSize(800, 750);
                //unTabbedPane.setSize(800, 750);
                uneFrame.add(unTabbedPane);
                uneFrame.revalidate();
                uneFrame.repaint();
            } catch (NumberFormatException e) {
                messageErreur(new Exception("Format des notes incorrect"));
            }
        }
    }

    private void modifier() {
        String code = (String) comboBox1.getSelectedItem();
        int num = Integer.parseInt(code.substring(6, 7));
        Eleve eleve = g.getTabEleve().get(num);

        try {
            for (int i = 0; i < eleve.getNbrEvaluations(); i++) {
                setChamp(i, String.valueOf(eleve.getTabEvaluation().get(i).getNote()));
            }
        } catch (NullPointerException e) {
            messageErreur(new Exception("Eleve introuvable"));
        }

    }

    //Méthodes qui permet d'ajouter les groupes à la liste des JComboBox
    public void remplir(JComboBox combo) {
        for (int i = 0; i < g.getTabEleve().size(); i++) {
            combo.addItem("eleve " + i + " : " + g.getTabEleve().get(i).codePermanent() + ", "
                    + g.getTabEleve().get(i).getNom() + ", " + g.getTabEleve().get(i).getPrenom());
        }
    }
}

//Etablissement.getTabGroupe().get(i)
//combo.getItemAt(i).  //setName("Groupe "+i);
