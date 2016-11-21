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

public class EleveAfficherPanel extends UtilePanel {

    private UtilePanel gestionPanel;

    //variables
    final int NBR_NOTES = 4;
    UtileFrame fenetre;
    JProgressBar pBar;

    //Méthodes
    //Constructeur
    public EleveAfficherPanel(UtileFrame fenetre, JProgressBar pBar) {
        super(fenetre);
        this.gestionPanel=gestionPanel;
        this.pBar = pBar;
        GridLayout gl = new GridLayout(12, 6, 0, 25);	//Cree GridLayout
        simplePanel.setLayout(gl);
        addEspace();
        addChamp("Nom");
        addEspace();
        addEspace();
        addChamp("Prenom");
        addEspace();
        addEspace();
        addChamp("Date (JJ-MM-AAAA)");
        addEspace();
        addEspace();
        addChamp("Code Permanent");
        addEspace();
        addEspace();
        getLastChamp().setEditable(false);
        for (int i = 0; i < NBR_NOTES; i++) {
            addChamp("Note" + (i + 1));
            addEspace();
            addEspace();
        }
        addBouton("  Afficher un eleve   ");
        //Un bouton "Clear"
        addBouton("Vider les champs");
        addEspace();
        
        addLabel("Eleve(s) dans le groupe: ");
        simplePanel.add(new JLabel(pBar.getMinimum() + ""));
        simplePanel.add(pBar);
        simplePanel.add(new JLabel(pBar.getMaximum() + ""));
    }
    public EleveAfficherPanel(){
        this(null, null);
    }
    //Get-Set
    //toString
    //Autres Méthodes

    @Override
    public void actionPerformed(ActionEvent event) {  // Methode recoit evenement

        if (((JButton) event.getSource()).getText() == "  Afficher un eleve   ") {
            afficherEleve();
        }
        //Un bouton qui vide tous les champs
        if (((JButton) event.getSource()).getText() == "Vider les champs") {
            viderChamps();
        }
       
        
    }

    private void afficherEleve(){
            String codePermanent = JOptionPane.showInputDialog(null,
                    "Entrer le code permanent de l'eleve:",
                    "Afficher un Eleve", JOptionPane.QUESTION_MESSAGE);
            if(afficherEleve(codePermanent))
                notification("Affichage effectue");
    }
    private boolean afficherEleve(String codePermanent) {
        Eleve eleve;
        try {
            eleve = Etablissement.searchEleve(codePermanent);
            if (eleve.equals(null)) {
                throw new NullPointerException("Code incorrect");
            }
            setChamp(0, eleve.getNom());
            setChamp(1, eleve.getPrenom());
            setChamp(2, eleve.getDateNaissance());
            setChamp(3, codePermanent);
            for (int i = 0; i < NBR_NOTES; i++) {
                setChamp(i + 4, eleve.getNote(i));
            }
            return true;
        } catch (NullPointerException e) {
            messageErreur(new Exception("Eleve introuvable"));
            return false;
        }
        catch (Exception e) {
            messageErreur(e);
            return false;
        }
    }
    
    private void notification(String texte) {
        JOptionPane.showMessageDialog(null, texte, "Operation Effectué", JOptionPane.INFORMATION_MESSAGE);
    }

    private void viderChamps() {
        for(JTextField champ :champs)
            champ.setText("");
    }

    public void refreshComboBoxes() {
        gestionPanel.refreshComboBoxes(Etablissement.getTabGroupe());
    }
}
