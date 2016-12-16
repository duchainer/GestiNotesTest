package vue;


/**
 * Crée 2016-11-04,17:58
 *
 *  TODO: -actionPerformed
 *
 * @author Patrick Domingues
 */
import Réutilisable.UtileFrame;
import modele.Groupe;
import modele.Statistique;
import java.awt.GridLayout;
import java.awt.event.ActionEvent;
import java.awt.event.ActionListener;
import javax.swing.JProgressBar;
import modele.Etablissement;

public class GestionnaireStatistiquesPanel extends GestiNotesPanel {


    //variable
    String[] tooltips = {
        "Statistiques diverse sur le groupe selectionné",//Stats
        "Selectionner un groupe pour en avoir les statistiques",//Lister
        "Moyenne du groupe",//moyenne
        "Écart type au carré",//variance
        "Moyenne des écart entre notes et moyenne du groupe",//ecart type
        "Meilleure note finale du groupe",//meilleure note
        "Pire note finale du groupe",//pire note
        "Taux des notes au-dessus de 60%"};//taux de succes
    //Méthodes
    //Constructeur
    public GestionnaireStatistiquesPanel(UtileFrame frame) {
        super(frame);
        GridLayout gl = new GridLayout(10, 2, 0, 25);	//Cree GridLayout
        simplePanel.setLayout(gl);

        addLabel("Statistiques",tooltips[0]);
        addEspace();
        addLabel("Lister: ",tooltips[1]);
        addComboBox();
        remplirCombo1();
        tabComboBox.get(0).setToolTipText(tooltips[1]);
        addChamp("moyenne: ",tooltips[2]);
        getLastChamp().setEditable(false);
        addChamp("variance: ",tooltips[3]);
        getLastChamp().setEditable(false);
        addChamp("ecart type: ",tooltips[4]);
        getLastChamp().setEditable(false);
        addChamp("meilleure note: ",tooltips[5]);
        getLastChamp().setEditable(false);
        addChamp("pire note: ",tooltips[6]);
        getLastChamp().setEditable(false);
        addChamp("taux de succes: ",tooltips[7]);
        getLastChamp().setEditable(false);
        
        tabComboBox.get(0).addActionListener(new ActionListener() {
            @Override
            public void actionPerformed(ActionEvent e) {
                if (tabComboBox.get(0).getSelectedItem() != null) {
                    stats();
                }
            }
        });

    }
    //Autres Méthodes
    private void stats() {
        String code = (String) tabComboBox.get(0).getSelectedItem();
        int num = Integer.parseInt(code.substring(7));
        Groupe g = Etablissement.getTabGroupe().get(num);
            
        setChamp(0, String.valueOf(Statistique.calculerMoyenne(g)));
        setChamp(1, String.valueOf(Statistique.calculerVariance(g)));
        setChamp(2, String.valueOf(Statistique.calculerEcartType(g)));
        setChamp(3, String.valueOf(meilleureNote(g)));
        setChamp(4, String.valueOf(pireNote(g)));
        setChamp(5, String.valueOf(tauxDeReussite(g)) + "%");

    }

    public double meilleureNote(Groupe g) {
        double note = 0;
        for (int i = 0; i < g.getTabEleve().size(); i++) {
            if (g.getTabEleve().get(i).calculerNoteFinale() > note) {
                note = g.getTabEleve().get(i).calculerNoteFinale();
            }
        }
        return Math.round(note*100)/100;
    }

    public double pireNote(Groupe g) {
        double note = 101;
        for (int i = 0; i < g.getTabEleve().size(); i++) {
            if (g.getTabEleve().get(i).calculerNoteFinale() < note) {
                note = g.getTabEleve().get(i).calculerNoteFinale();
            }
        }
        return Math.round(note*100)/100;
    }

    public double tauxDeReussite(Groupe g) {
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
