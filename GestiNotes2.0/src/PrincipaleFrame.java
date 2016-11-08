
import javax.swing.*;
import java.awt.GridLayout;
import java.awt.event.*;
import java.util.ArrayList;
import java.awt.Color;
import java.awt.HeadlessException;
import java.awt.Image;

/**
 * 28/09/2016
 *
 * @author Patrick Domingues 28/9/2016 et Raphael Duchaine 26/10/2016
 */
public class PrincipaleFrame extends UtileFrame {

    //Attributs
    //Attributs graphiques
    //VENANT DE UTILEFRAME:
    //JPanel simplePanel;  
    //ArrayList<JButton> boutons = new ArrayList<JButton>();
    //ArrayList<JTextField> champs = new ArrayList<JTextField>();
    JRadioButton radio1, radio2;
    ButtonGroup group;

    Image logo = new LogoRosemont().logo;

    //Constructeurs
    public PrincipaleFrame() {
        super("GestiNotes_02", 255, 350); // Titre, Dimensions x, y
        setIconImage(logo);
        setSize(800, 500); 								// Dimensions
        setDefaultCloseOperation(JFrame.EXIT_ON_CLOSE); // Fermeture par x
        setLocationRelativeTo(null);					// Fenetre centree

        setResizable(true);

        //Look And Feel natif
        try {
            UIManager.setLookAndFeel(UIManager.getSystemLookAndFeelClassName());
        } catch (Exception e) {
            System.err.println("Erreur de Look and feel: " + e.toString());
        }

        simplePanel = new JPanel(); 					//Cree le panneau
        JTabbedPane tabbedPane = new JTabbedPane(JTabbedPane.TOP);
        simplePanel.add(tabbedPane);
        tabbedPane.addTab("Menu Eleve", new ElevePanel(this));
        tabbedPane.addTab("Menu Aide", new AidePanel(this));

        addBouton("Lister un groupe");
        boutons.get(boutons.size() - 1).setForeground(Color.BLACK);
        addBouton("Afficher les statistiques d'un groupe");
        boutons.get(boutons.size() - 1).setForeground(Color.BLACK);
        add(simplePanel);
    }

    public void afficherStatistiques() throws HeadlessException {
        //Affichage des statistiques d'un groupe (moyenne, variance, ecart-type)
        try {
            int i = (Integer.parseInt(JOptionPane.showInputDialog(null, "Vous voulez consulter les statistiques de quel groupe?",
                    "Statistiques de classe", JOptionPane.QUESTION_MESSAGE)));
            Groupe groupe = Etablissement.getTabGroupe().get(i);
            String stats = ("Statistiques du groupe " + i + ":\n"
                    + "Moyenne : " + Statistique.calculerMoyenne(groupe) + "\n"
                    + "Variance: " + Statistique.calculerVariance(groupe) + "\n"
                    + "Ecart-Type: " + Statistique.calculerEcartType(groupe) + "\n" /*+"Nombre d'élèves: "+groupe.getTabEleve().size()*/);
            JOptionPane.showMessageDialog(null, stats, "Afficher des Statistiques", JOptionPane.INFORMATION_MESSAGE);
        } catch (Exception e) {
            JOptionPane.showMessageDialog(null, e.getMessage(), "ERROR", JOptionPane.ERROR_MESSAGE);
        }
    }

    public void listerEleve() throws HeadlessException {
        //Affichage de la liste d'eleves d'un groupe, avec leurs notes
        try {
            int i = Integer.parseInt(JOptionPane.showInputDialog(null, "Quel groupe voulez-vous voir?",
                    "Liste de classe", JOptionPane.QUESTION_MESSAGE));
            String liste = Etablissement.getTabGroupe().get(i).listeEleve();
            JOptionPane.showMessageDialog(null, liste, "Liste des élèves du groupe " + i, JOptionPane.INFORMATION_MESSAGE);
        } catch (Exception e) {
            JOptionPane.showMessageDialog(null, e.getMessage(), "ERROR", JOptionPane.ERROR_MESSAGE);
        }
    }
    


    //methodes supplementaires
    @Override
    public void actionPerformed(ActionEvent event) {
        if (event.getSource() == boutons.get(0)) {
            listerEleve();
        }if (event.getSource() == boutons.get(1)) {
            afficherStatistiques();
        }
    }
}
