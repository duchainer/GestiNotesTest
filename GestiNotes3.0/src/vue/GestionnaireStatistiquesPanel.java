package vue;


/**
 * Crée 2016-11-04,17:58
 *
 *  TODO: -actionPerformed
 *
 * @author Patrick Domingues
 */
import modele.Groupe;
import modele.Statistique;
import java.awt.GridLayout;
import java.awt.event.ActionEvent;
import java.awt.event.ActionListener;
import javax.swing.JProgressBar;
import modele.Etablissement;

public class GestionnaireStatistiquesPanel extends UtilePanel /*ComplementPanel*/ {


    //variable
    //Méthodes
    //Constructeur
    public GestionnaireStatistiquesPanel(UtileFrame frame) {
        super(frame);
        GridLayout gl = new GridLayout(10, 2, 0, 25);	//Cree GridLayout
        simplePanel.setLayout(gl);

        addLabel("Statistiques");
        addEspace();
        addLabel("Lister: ");
        addComboBox();
        remplirCombo1();
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
