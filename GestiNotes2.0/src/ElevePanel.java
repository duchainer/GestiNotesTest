
/**
 * Crée 2016-10-31,09:38:32
 *
 * @author Raphael Duchaine
 */
import java.awt.HeadlessException;
import java.awt.event.ActionEvent;
import javax.swing.JButton;
import javax.swing.JOptionPane;

public class ElevePanel extends UtilePanel {

    //variables
    final int NBR_NOTES = 4;

    //Méthodes
    //Constructeur
    public ElevePanel() {
        super();
        addChamp("Nom");
        addChamp("Prenom");
        addChamp("Date");
        for (int i = 0; i < NBR_NOTES; i++) {
            addChamp("Note" + (i + 1));
        }
        addBouton("Enregistrer un eleve");
        getLastBouton().addActionListener(this);
        addBouton("Afficher un eleve");
        getLastBouton().addActionListener(this);
        addBouton("Modifier ");
        getLastBouton().addActionListener(this);
    }

    //Get-Set
    //toString
    //Autres Méthodes
    public void ajouterEleve() throws HeadlessException {
        //Creation d'un eleve dans un groupe (cree un autre groupe si le groupe a 10 eleves)
        //String titre = "Enregistrer un élève";

    }

    @Override
    public void actionPerformed(ActionEvent event) {  // Methode recoit evenement

        if (((JButton) event.getSource()).getText() == "Enregistrer un eleve") {
            Etablissement.addEleve(new Eleve(champs.get(0).getText(), champs.get(0).getText(), champs.get(0).getText()));
        }
        if (((JButton) event.getSource()).getText() == "Afficher un eleve") {
            afficherEleve();
        }
        if (((JButton) event.getSource()).getText() == "Modifier un eleve") {
            modifierEleve();
        }
    }

    public void setChamp(int index, String texte) {
        champs.get(index).setText(texte);
    }

    private void afficherEleve() {
        String codePermanent = JOptionPane.showInputDialog(null, "Entrer le code permanent de l'eleve:", "Afficher un Eleve", JOptionPane.QUESTION_MESSAGE);
        Eleve eleve;
        try {
            eleve = Etablissement.searchEleve(codePermanent);
            setChamp(0, eleve.getNom());
            setChamp(1, eleve.getPrenom());
            setChamp(2, eleve.getDateNaissance());
            for (int i = 0; i < NBR_NOTES; i++) {
                setChamp(i+3, getNote(eleve, i));
            }

        } catch (NullPointerException e) {
            JOptionPane.showMessageDialog(null, "Eleve introuvable", "ERROR", JOptionPane.ERROR_MESSAGE);
        }
    }

    private void modifierEleve() {
        throw new UnsupportedOperationException("Not supported yet."); //To change body of generated methods, choose Tools | Templates.
    }

    public static String getNote(Eleve eleve, int index) {
        return eleve.getTabEvaluation().get(index).getNote().toString();
    }
}
