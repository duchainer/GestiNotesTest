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

public class EleveModifierPanel extends UtilePanel {

    private UtilePanel gestionPanel;

    //variables
    final int NBR_NOTES = 4;
    UtileFrame fenetre;
    JProgressBar pBar;

    //Méthodes
    //Constructeur
    public EleveModifierPanel(UtileFrame fenetre, JProgressBar pBar) {
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
        addBouton("  Modifier un eleve   ");
        //Un bouton "Clear"
        addBouton("Vider les champs");
        addEspace();
        
        addLabel("Eleve(s) dans le groupe: ");
        simplePanel.add(new JLabel(pBar.getMinimum() + ""));
        simplePanel.add(pBar);
        simplePanel.add(new JLabel(pBar.getMaximum() + ""));
    }
    public EleveModifierPanel(){
        this(null, null);
    }
    //Get-Set
    //toString
    //Autres Méthodes

    @Override
    public void actionPerformed(ActionEvent event) {  // Methode recoit evenement

        if (((JButton) event.getSource()).getText() == "  Modifier un eleve   ") {
            modifierEleve();
            revalidate();
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
            ArrayList<Evaluation> evaluations = eleve.getTabEvaluation();
            for (int i = 0; i < NBR_NOTES; i++) {
                evaluations.get(i).setNote(Double.parseDouble(getChamp(i + 4).getText()));
            }
            notification("Modification effectue");
            
        } catch (NullPointerException e) {
            messageErreur(e);
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
