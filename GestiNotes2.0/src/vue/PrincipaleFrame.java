package vue;

import java.awt.BorderLayout;
import modele.Groupe;
import modele.Statistique;
import modele.Etablissement;
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
    JTabbedPane tabbedPane;

    Image logo = new LogoRosemont().logo;

    //Constructeurs
    public PrincipaleFrame() {
        super("GestiNotes_02", 800, 750); // Titre, Dimensions x, y
        setIconImage(logo);
        setDefaultCloseOperation(JFrame.DO_NOTHING_ON_CLOSE); // Fermeture par x
        setLocationRelativeTo(null);			// Fenetre centree
        BorderLayout bl = new BorderLayout();
        simplePanel.setLayout(bl);
        setResizable(true);

        addWindowListener(exitListener);

        //Look And Feel natif
        try {
            UIManager.setLookAndFeel(UIManager.getSystemLookAndFeelClassName());
        } catch (Exception e) {
            System.err.println("Erreur de Look and feel: " + e.toString());
        }
        //Création ProgressBar
        pBar = new JProgressBar();
        pBar.setMinimum(0);
        pBar.setMaximum(Etablissement.ELEVES_PAR_GROUPE);
        pBar.setValue(Etablissement.getLastGroupe().getTabEleve().size());

        tabbedPane = new JTabbedPane(JTabbedPane.TOP);
        simplePanel.add(tabbedPane, BorderLayout.NORTH);
        UtilePanel gestionPanel = new GestionnairePanel(this, tabbedPane,pBar);
        tabbedPane.addTab("Acceuil", new AcceuilPanel(this));
        tabbedPane.addTab("Menu Eleve", new ElevePanel(this, gestionPanel));
        tabbedPane.addTab("Menu Gestionnaire", gestionPanel);
        tabbedPane.addTab("Menu Aide", new AidePanel(this, tabbedPane));

       
        
        simplePanel.add(new JLabel(pBar.getMinimum() + ""), BorderLayout.WEST);
        simplePanel.add(pBar, BorderLayout.CENTER);
        simplePanel.add(new JLabel(pBar.getMaximum() + ""), BorderLayout.EAST);
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
                    + "Ecart-Type: " + Statistique.calculerEcartType(groupe) + "\n");
            JOptionPane.showMessageDialog(null, stats, "Afficher des Statistiques", JOptionPane.INFORMATION_MESSAGE);
        } catch (Exception e) {
            JOptionPane.showMessageDialog(null, e.getMessage(), "ERROR", JOptionPane.ERROR_MESSAGE);
        }
    }

    //methodes supplementaires
    WindowListener exitListener = new WindowAdapter() {
        @Override
        public void windowClosing(WindowEvent e) {
            quitter();
        }
    };

}
