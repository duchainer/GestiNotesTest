
/**
 * Crée 2016-11-04,17:58
 *
 *  TODO: -actionPerformed
 *
 * @author Patrick Domingues
 */
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

public class GestionnaireStatistiquesPanel extends UtilePanel {

    //variables
    JComboBox<Groupe> comboBox1;
    JFrame uneFrame;
    JTabbedPane unTabbedPane;
    Groupe g;

    //Méthodes
    //Constructeur
    public GestionnaireStatistiquesPanel(JFrame frame, Groupe g, JTabbedPane tabbedPane) {
        super();
        this.uneFrame = frame;
        this.unTabbedPane = tabbedPane;
        this.g = g;
        GridLayout gl = new GridLayout(10, 2, 0, 25);	//Cree GridLayout
        simplePanel.setLayout(gl);

        addLabel("Statistiques");
        addEspace();
        addChamp("moyenne: ");
        getLastChamp().setEditable(false);
        addChamp("variance: ");
        getLastChamp().setEditable(false);
        addChamp("ecart type: ");
        getLastChamp().setEditable(false);
        addChamp("meilleure note: ");
        getLastChamp().setEditable(false);
        addChamp("pire note: ");
        getLastChamp().setEditable(false);
        addChamp("taux de succes: ");
        getLastChamp().setEditable(false);

        addBouton("retour");
        stats();

    }
    //Autres Méthodes

    @Override
    public void actionPerformed(ActionEvent event) {  // Methode recoit evenement
        //uneFrame.getContentPane().add(new GestionnairePanel(uneFrame, unTabbedPane));
        if (((JButton) event.getSource()).getText() == "retour") {

            uneFrame.getContentPane().removeAll();
            uneFrame.setSize(800, 750);
            unTabbedPane.setSize(800, 750);
            uneFrame.add(unTabbedPane);
            uneFrame.revalidate();
            uneFrame.repaint();
        }
    }

    private void stats() {
        setChamp(0, String.valueOf(Statistique.calculerMoyenne(g)));
        setChamp(1, String.valueOf(Statistique.calculerVariance(g)));
        setChamp(2, String.valueOf(Statistique.calculerEcartType(g)));
        setChamp(3, String.valueOf(meilleureNote()));
        setChamp(4, String.valueOf(pireNote()));
        setChamp(5, String.valueOf(tauxDeReussite()) + "%");

    }

    public double meilleureNote() {
        double note = 0;
        for (int i = 0; i < g.getTabEleve().size(); i++) {
            if (g.getTabEleve().get(i).calculerNoteFinale() > note) {
                note = g.getTabEleve().get(i).calculerNoteFinale();
            }
        }
        return note;
    }

    public double pireNote() {
        double note = 101;
        for (int i = 0; i < g.getTabEleve().size(); i++) {
            if (g.getTabEleve().get(i).calculerNoteFinale() < note) {
                note = g.getTabEleve().get(i).calculerNoteFinale();
            }
        }
        return note;
    }

    public double tauxDeReussite() {
        double taux = 0;
        for (int i = 0; i < g.getTabEleve().size(); i++) {
            if (g.getTabEleve().get(i).calculerNoteFinale() > 60) {
                taux++;
            }
        }
        taux = (taux / g.getTabEleve().size()) * 100;
        return taux;
    }
}

//Etablissement.getTabGroupe().get(i)
//combo.getItemAt(i).  //setName("Groupe "+i);
