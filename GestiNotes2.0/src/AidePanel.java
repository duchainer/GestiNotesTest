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

    String aPropos= "GestiNotes par Patrick Dominguès et Raphaël Duchaîne de InnovTech"
            + "\nMontréal,Québec"
            + "\nVersion 2 (04/11/2016)"
            + "\nVersion 1 (12/10/2016)"
            + "\n"
            + "\nQuand on demanda aux ingénieurs de InnovTech de concevoir le futur du gestionnaire scolaire,"
            + "\n(un produit qui serait à la fois convivial, efficace et graphique) la majorité de ceux présents a lâché l'éponge."
            + "\nMais les deux qui restaient ont vraiment fait de gros efforts pour accoucher de ce bijou de technologie.";
    String instructions="Pour utiliser ce bijou d'innovation, il faut savoir:"
            + "\n-Que chaque groupe ne peut comprendre que 10 eleves"
            + "\n-Qu'un eleve doit être initialisé avec nom, prenom et date "
            + "\n-Que l'on ne peut pas enlever d'eleve dans cette version"
            + "\n-Que l'aide se trouve ici"
            + "\n-Que 2 et 2 font 4";
           

    //variables
    final int NBR_NOTES = 4;
    JLabel notification;

    //Méthodes
    //Constructeur
    public AidePanel(UtileFrame fenetre) {
        super(fenetre);
        GridLayout gl = new GridLayout(0, 1, 5, 5);	//Cree GridLayout
        simplePanel.setLayout(gl);
        addBouton("?");
        addBouton("À Propos");
        addBouton("Annuler");
        addBouton("Quitter");
    }
    public AidePanel(){
        this(null);
    }
    //Get-Set
    //toString
    //Autres Méthodes

    @Override
    public void actionPerformed(ActionEvent event) {  // Methode recoit evenement
        if (((JButton) event.getSource()).getText() == "Quitter") {
            fenetre.quitter();
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
        fenetre.tabbedPane.setSelectedIndex(0);
    }
    //Fenêtre incluant les créateurs, dates, lieu, etc)
    private void aPropos() {
        JOptionPane.showMessageDialog(simplePanel, aPropos,"À Propos",JOptionPane.INFORMATION_MESSAGE);
    }
    //Instruction sur l'utilisation du programme
    private void instructions() {
        JOptionPane.showMessageDialog(simplePanel, instructions,"Instructions",JOptionPane.INFORMATION_MESSAGE);
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
