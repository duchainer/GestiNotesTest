import java.awt.GridLayout;
import java.awt.HeadlessException;
import java.awt.event.ActionEvent;
import java.util.ArrayList;
import javax.swing.Icon;
import javax.swing.JButton;
import javax.swing.JLabel;
import javax.swing.JOptionPane;

/**
 * Crée 2016-11-01,09:29:42 
 * @author Raphael Duchaine
 */
public class AidePanel extends UtilePanel{

    String aPropos= "GestiNotes par Patrick Dominguès et Raphaël Duchaîne"
            + "\nMontréal,Québec"
            + "\nVersion 2 (04/11/2016)"
            + "\nVersion 1 (12/10/2016)";
           

    //variables
    final int NBR_NOTES = 4;
    JLabel notification;

    //Méthodes
    //Constructeur
    public AidePanel() {
        super();
        notification =(addLabel(""));
        GridLayout gl = new GridLayout(0, 1, 5, 5);	//Cree GridLayout
        simplePanel.setLayout(gl);
        addBouton("?");
        addBouton("À Propos");
        addBouton("Annuler");
        addBouton("Quitter");
    }
    //Get-Set
    //toString
    //Autres Méthodes

    @Override
    public void actionPerformed(ActionEvent event) {  // Methode recoit evenement
        if (((JButton) event.getSource()).getText() == "Quitter") {
            quitter();
            System.out.println("ElevePanel.actionPerformed()");
        }
        if (((JButton) event.getSource()).getText() == "?") {
            instructions();
            System.out.println("ElevePanel.actionPerformed()");
        }
        if (((JButton) event.getSource()).getText() == "À Propos") {
            aPropos();
            System.out.println("ElevePanel.actionPerformed()");

        }
        if (((JButton) event.getSource()).getText() == "Annuler") {
            annuler();
            System.out.println("ElevePanel.actionPerformed()");
        }
    }
    //Retour au début du programme
    private void annuler() {
        throw new UnsupportedOperationException("Not supported yet."); //To change body of generated methods, choose Tools | Templates.
    }
    //Fenêtre incluant les créateurs, dates, lieu, etc)
    private void aPropos() {
        JOptionPane.showMessageDialog(simplePanel, aPropos,"À Propos",JOptionPane.INFORMATION_MESSAGE);
    }
    //Instruction sur l'utilisation du programme
    private void instructions() {
        throw new UnsupportedOperationException("Not supported yet."); //To change body of generated methods, choose Tools | Templates.
    }
    //quitter le programme
    public void quitter() throws HeadlessException {
        //Permet l'arret du programme
        int reponse = JOptionPane.showConfirmDialog(null, "Voulez-vous vraiment quitter?",
                "Quitter", JOptionPane.YES_NO_OPTION);
        if (reponse == JOptionPane.YES_OPTION) {
            System.exit(0);
        }
    }
}
