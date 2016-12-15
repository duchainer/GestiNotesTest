package vue;

import Réutilisable.UtileFrame;
import Réutilisable.UtilePanel;
import java.awt.GridLayout;
import java.awt.HeadlessException;
import java.awt.event.ActionEvent;
import java.util.ArrayList;
import javax.swing.Icon;
import javax.swing.JButton;
import javax.swing.JLabel;
import javax.swing.JOptionPane;
import javax.swing.JTabbedPane;

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
            + "\n(un produit qui serait à la fois convivial, efficace et graphique) la majorité de ceux présents a lancé l'éponge."
            + "\nMais les deux qui restèrent ont vraiment fait de gros efforts pour accoucher de ce bijou de technologie.";
    String instructions="Pour utiliser ce bijou d'innovation, il faut savoir:"
            + "\n-Que chaque groupe ne peut comprendre que 10 eleves"
            + "\n-Qu'un eleve doit être initialisé avec nom, prenom et date "
            + "\n-Que l'on ne peut pas enlever d'eleve dans cette version"
            + "\n-Que l'aide se trouve ici"
            + "\n-Que 2 et 2 font 4";
           

    //variables

    //Méthodes
    //Constructeur
    public AidePanel(UtileFrame fenetre) {
        super(fenetre);
        GridLayout gl = new GridLayout(13, 6, 0, 25);	//Cree GridLayout
        simplePanel.setLayout(gl);
        addBouton("?");
        addBouton("À Propos");
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
        }
        if (((JButton) event.getSource()).getText() == "?") {
            instructions();
        }
        if (((JButton) event.getSource()).getText() == "À Propos") {
            aPropos();

        }
    }
    //Retour au début du programme

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
