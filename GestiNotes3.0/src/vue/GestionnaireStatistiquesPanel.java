package vue;

/**
 * Crée 2016-11-04,17:58
 *
 * TODO: -actionPerformed
 *
 * @author Patrick Domingues
 */
import java.awt.BorderLayout;
import modele.Groupe;
import modele.Statistique;
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
import javax.swing.JProgressBar;
import javax.swing.JTabbedPane;
import modele.Etablissement;

public class GestionnaireStatistiquesPanel extends ComplementPanel {

    //variable
    Groupe g;

    //Méthodes
    //Constructeur
    public GestionnaireStatistiquesPanel(UtileFrame frame, int num, Groupe g, JTabbedPane tabbedPane) {
        super(frame, tabbedPane);
        this.g = g;
        GridLayout gl = new GridLayout(10, 2, 0, 25);	//Cree GridLayout
        simplePanel.setLayout(gl);

        addLabel("Statistiques");
        addLabel("Groupe " + num);
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

    GestionnaireStatistiquesPanel(UtileFrame uneFrame, int num, Groupe groupe, JTabbedPane unTabbedPane, JProgressBar pBar) {
        this(uneFrame, num, groupe, unTabbedPane);
        this.pBar = pBar;
    }

    @Override
    public void actionPerformed(ActionEvent event) {  // Methode recoit evenement
        //uneFrame.getContentPane().add(new GestionnairePanel(fenetre, tabbedPane));
        if (((JButton) event.getSource()).getText() == "retour") {
            retour();
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

    public float meilleureNote() {
        float note = 0;
        for (int i = 0; i < g.getTabEleve().size(); i++) {
            if (g.getTabEleve().get(i).calculerNoteFinale() > note) {
                note = g.getTabEleve().get(i).calculerNoteFinale();
            }
        }
        return note;
    }

    public float pireNote() {
        float note = 101;
        for (int i = 0; i < g.getTabEleve().size(); i++) {
            if (g.getTabEleve().get(i).calculerNoteFinale() < note) {
                note = g.getTabEleve().get(i).calculerNoteFinale();
            }
        }
        return note;
    }

    public float tauxDeReussite() {
        float taux = 0;
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
