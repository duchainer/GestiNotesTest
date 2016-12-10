package vue;


/**
 * Crée 2016-10-31,09:38:32
 *
 * @author Patrick Domingues
 */
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

public class EleveModifierPanel extends ElevePanel {

    //Méthodes
    //Constructeur
    public EleveModifierPanel(UtileFrame fenetre, JProgressBar pBar) {
        super(fenetre,pBar);
        updatePbar();
        texteBouton="Modifier un eleve";
        getBouton(0).setText(texteBouton);
    }
    public EleveModifierPanel(){
        this(null, null);
    }
    //Autres Méthodes

    private void modifierEleve() {
        Eleve eleve;
        String codePermanent;
        if (getChamp(3).getText().equals("")) {
            afficherEleve();
            return;
        } else {
            codePermanent = getChamp(3).getText();
        }
        try {
            eleve = Etablissement.searchEleve(codePermanent);
            if (eleve.equals(null)) {
                throw new NullPointerException("Code incorrect");
            }
            eleve.setNom(getChamp(0).getText());
            eleve.setPrenom(getChamp(1).getText());
            eleve.setDateNaissance(getChamp(2).getText());
            for (int i = 0; i < Etablissement.NBR_NOTES; i++) {
                eleve.setNote(i,Float.parseFloat(getChamp(i + 4).getText()));
            }
            notification("Modification effectue");
            
        } catch (NullPointerException e) {
            messageErreur(e);
        }

    }

    @Override
    void bouton0Presse() {
        modifierEleve();
    }

}
