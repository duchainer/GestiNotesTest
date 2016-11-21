package vue;


/**
 * Crée 
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

public class EleveNouveauPanel extends UtilePanel {

    private UtilePanel gestionPanel;

    //variables
    final int NBR_NOTES = 4;
    UtileFrame fenetre;
    JProgressBar pBar;

    //Méthodes
    //Constructeur
    public EleveNouveauPanel(UtileFrame fenetre, JProgressBar pBar) {
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
        addBouton("Enregistrer un eleve");        
        //Un bouton "Clear"
        addBouton("Vider les champs");
        addEspace();
        
        addLabel("Eleve(s) dans le groupe: ");
        simplePanel.add(new JLabel(pBar.getMinimum() + ""));
        simplePanel.add(pBar);
        simplePanel.add(new JLabel(pBar.getMaximum() + ""));
    }
    public EleveNouveauPanel(){
        this(null, null);
    }
    //Get-Set
    //toString
    //Autres Méthodes

    @Override
    public void actionPerformed(ActionEvent event) {  // Methode recoit evenement

        if (((JButton) event.getSource()).getText() == "Enregistrer un eleve") {
            addEleve();
            pBar.setValue(Etablissement.getLastGroupe().getTabEleve().size());
            if(pBar.getValue()==Etablissement.ELEVES_PAR_GROUPE){
                JOptionPane.showMessageDialog(fenetre, "Le prochain eleve sera enregistre dans un nouveau groupe", "Notification", JOptionPane.INFORMATION_MESSAGE);
                pBar.setValue(0);
            }
            revalidate();
        }
        //Un bouton qui vide tous les champs
        if (((JButton) event.getSource()).getText() == "Vider les champs") {
            viderChamps();
        }
       
        
    }


    private void addEleve() {
        try{
            //Traitement de champ vide
            for(int i=0;i<2;i++){
                if(champs.get(i).getText().equals(""))
                    throw new Exception("Donnée introuvable");
            }
            if(champs.get(2).getText().length() != 10){
                throw new Exception("Format incorrect \n format requis: \"JJ-MM-AAAA\"");                
            }
        Eleve eleve =new Eleve(champs.get(0).getText(), champs.get(1).getText(), champs.get(2).getText());
        Etablissement.addEleve(eleve);
        afficherEleve(eleve.codePermanent());
        notification("Enregistrement effectue");
        //Afficher l'élève créé
        //Enregistrer des notes
        } catch (Exception e) {
                messageErreur(e);
        }
                refreshComboBoxes();
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
        super.refreshComboBoxes(Etablissement.getTabGroupe());
    }
}
