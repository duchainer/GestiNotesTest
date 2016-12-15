package vue;

/**
 * Crée
 *
 * @author Patrick Domingues
 */
import Réutilisable.UtileFrame;
import modele.Evaluation;
import modele.Etablissement;
import modele.Eleve;
import java.awt.GridLayout;
import java.awt.HeadlessException;
import java.awt.event.ActionEvent;
import java.util.ArrayList;
import javax.swing.JButton;
import javax.swing.JLabel;
import javax.swing.JOptionPane;
import javax.swing.JProgressBar;
import javax.swing.JTextField;

public class EleveNouveauPanel extends ElevePanel {

    //Méthodes
    //Constructeur
    public EleveNouveauPanel(UtileFrame fenetre, JProgressBar pBar) {
        super(fenetre, pBar);
        texteBouton="Ajouter un eleve";
        getBouton(0).setText(texteBouton);
        for(int i =0;i<Etablissement.NBR_NOTES;i++)
            setChampEditable(i+4, false);
    }

    public EleveNouveauPanel() {
        this(null, null);
    }

    //Autres Méthodes
    @Override
    void bouton0Presse() {
        addEleve();
        updatePbar();
        if (pBar.getValue() == Etablissement.ELEVES_PAR_GROUPE) {
            JOptionPane.showMessageDialog(fenetre, "Le prochain eleve sera enregistre dans un nouveau groupe", "Notification", JOptionPane.INFORMATION_MESSAGE);
            pBar.setValue(0);
        }
        pBar.setString(pBar.getValue() + "/10");
        revalidate();
    }

    void addEleve() {
        try {
            //Traitement de champ vide
            for (int i = 0; i < 2; i++) {
                if (champs.get(i).getText().equals("")) {
                    throw new Exception("Donnée introuvable");
                }
            }
            if (champs.get(2).getText().length() != 10) {
                throw new Exception("Format incorrect \n format requis: \"JJ-MM-AAAA\"");
            }
            Eleve eleve = new Eleve(champs.get(0).getText(), champs.get(1).getText(), champs.get(2).getText());
            Etablissement.addEleve(eleve);
            afficherEleve(eleve.codePermanent());
            notification("Enregistrement effectue");
            //Afficher l'élève créé
            //Enregistrer des notes
        } catch (Exception e) {
            messageErreur(e);
        }
    }

}
