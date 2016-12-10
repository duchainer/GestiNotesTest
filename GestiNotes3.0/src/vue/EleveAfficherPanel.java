package vue;


/**
 * Crée le 
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

public class EleveAfficherPanel extends ElevePanel {
    //Méthodes
    //Constructeur
    public EleveAfficherPanel(UtileFrame fenetre, JProgressBar pBar) {
        super(fenetre,pBar);
        updatePbar();
        texteBouton="Afficher un eleve";
        getBouton(0).setText(texteBouton);
        for(JTextField c:champs){
            c.setEditable(false);
        }
    }
    public EleveAfficherPanel(){
        this(null, null);
    }
    //Autres Méthodes
    @Override
    void bouton0Presse() {
        afficherEleve();
    }
}
