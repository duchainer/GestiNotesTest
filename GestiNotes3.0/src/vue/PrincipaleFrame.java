package vue;

import java.awt.BorderLayout;
import modele.Groupe;
import modele.Statistique;
import modele.Etablissement;
import javax.swing.*;
import java.awt.GridLayout;
import java.awt.event.*;
import java.awt.*;
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
    Image logo = new LogoRosemont().logo;
    JProgressBar pBar;

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
        //Création MenuBar
        
        Container contentPane = getContentPane();
        setJMenuBar(menuBar);
        
        JMenu menuEleve= new JMenu("Eleve") ; // création menu client
	menuBar.add(menuEleve); // ajout du menu à la barre des menus
        
        addMenuItem("Nouveau", menuEleve);
        addMenuItem("Afficher", menuEleve);
        addMenuItem("Modifier ", menuEleve);
        
        JMenu menuGestionnaire= new JMenu("Gestionnaire") ; // création menu client
	menuBar.add(menuGestionnaire); // ajout du menu à la barre des menus
        
        addMenuItem("Lister", menuGestionnaire);
        addMenuItem("Modifier", menuGestionnaire);
        addMenuItem("Statistiques", menuGestionnaire);
        
        JMenu menuAide= new JMenu("Aide") ; // création menu client
	menuBar.add(menuAide); // ajout du menu à la barre des menus
        
        addMenuItem("A propos", menuAide);
        addMenuItem("?", menuAide);
        addMenuItem("Annuler", menuAide);        
        addMenuItem("Quitter", menuAide);       
        
        add(new AccueilPanel(this));
        
        

        pBar = new JProgressBar();
        pBar.setMinimum(0);
        pBar.setMaximum(Etablissement.ELEVES_PAR_GROUPE);
        pBar.setValue(Etablissement.getLastGroupe().getTabEleve().size());       
        
    }
    
    @Override
    public void actionPerformed(ActionEvent e) // Afficher DOS le texte de l’élément de menu actionné.
	{
		if (((JMenuItem) e.getSource()).getText() == "Nouveau"){
                    this.getContentPane().removeAll();
                    this.add(new EleveNouveauPanel(this, pBar));
                    repaint2();
                }
                if (((JMenuItem) e.getSource()).getText() == "Afficher"){
                    this.getContentPane().removeAll();
                    this.add(new EleveAfficherPanel(this, pBar));
                    repaint2();
                }
                if (((JMenuItem) e.getSource()).getText() == "Modifier "){
                    this.getContentPane().removeAll();
                    this.add(new EleveModifierPanel(this, pBar));
                    repaint2();
                }
                if (((JMenuItem) e.getSource()).getText() == "Lister"){
                    this.getContentPane().removeAll();
                    this.add(new GestionnaireListerPanel(this));
                    repaint2();
                }
                if (((JMenuItem) e.getSource()).getText() == "Modifier"){
                    this.getContentPane().removeAll();
                    this.add(new GestionnaireModifierPanel(this));
                    repaint2();
                }
                if (((JMenuItem) e.getSource()).getText() == "Statistiques"){
                    this.getContentPane().removeAll();
                    this.add(new GestionnaireStatistiquesPanel(this));
                    repaint2();
                }
                if (((JMenuItem) e.getSource()).getText() == "A propos"){
                    JOptionPane.showMessageDialog(this, "GestiNotes par Patrick Dominguès et Raphaël Duchaîne de InnovTech"
                    + "\nMontréal,Québec"
                    + "\nVersion 2 (04/11/2016)"
                    + "\nVersion 1 (12/10/2016)"
                    + "\n"
                    + "\nQuand on demanda aux ingénieurs de InnovTech de concevoir le futur du gestionnaire scolaire,"
                    + "\n(un produit qui serait à la fois convivial, efficace et graphique) la majorité de ceux présents a lancé l'éponge."
                    + "\nMais les deux qui restèrent ont vraiment fait de gros efforts pour accoucher de ce bijou de technologie.","À Propos",JOptionPane.INFORMATION_MESSAGE);
                }
                
                if (((JMenuItem) e.getSource()).getText() == "?"){
                    JOptionPane.showMessageDialog(this, "Pour utiliser ce bijou d'innovation, il faut savoir:"
                    + "\n-Que chaque groupe ne peut comprendre que 10 eleves"
                    + "\n-Qu'un eleve doit être initialisé avec nom, prenom et date "
                    + "\n-Que l'on ne peut pas enlever d'eleve dans cette version"
                    + "\n-Que les statistiques du groupe s'affichent lorsque un élève est ajouté"
                    + "\n-Que l'aide se trouve ici"
                    + "\n-Que 2 et 2 font 4","Instructions",JOptionPane.INFORMATION_MESSAGE);
                }
                if (((JMenuItem) e.getSource()).getText() == "Annuler"){
                    this.getContentPane().removeAll();
                    this.add(new AccueilPanel(this));
                    repaint2();
                }
                if (((JMenuItem) e.getSource()).getText() == "Quitter"){
                    this.quitter();
                }
                
	}
    public void repaint2(){
        this.revalidate();
        this.repaint();
    }
    
    //methodes supplementaires
    WindowListener exitListener = new WindowAdapter() {
        @Override
        public void windowClosing(WindowEvent e) {
            quitter();
        }
    };
    
    
}

