package vue;

import Réutilisable.UtileFrame;
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
import java.io.BufferedWriter;
import java.io.File;
import java.io.FileWriter;
import java.io.IOException;
import modele.Eleve;
import modele.Serialise;

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
    JProgressBar pBar;

    String[] tabGestionnaireOptions = {"Initialiser", "Importer", "Exporter", "Lister", "Modifier", "Statistiques"};
    
    //Réaction losque la fenetre se fait fermé par X
    WindowListener exitListener = new WindowAdapter() {
        @Override
        public void windowClosing(WindowEvent e) {
            quitter();
        }
    };
    
    
    //Constructeurs
    public PrincipaleFrame(Image logo) {
        super("GestiNotes_03", 800, 750); // Titre, Dimensions x, y
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

        JMenu menuEleve = new JMenu("Eleve"); // création menu client
        menuBar.add(menuEleve); // ajout du menu à la barre des menus

        addMenuItem("Nouveau", menuEleve);
        addMenuItem("Afficher", menuEleve);
        addMenuItem("Modifier ", menuEleve);
        addMenuItem("Imprimer", menuEleve);

        JMenu menuGestionnaire = new JMenu("Gestionnaire"); // création menu client
        menuBar.add(menuGestionnaire); // ajout du menu à la barre des menus

        for (String s : tabGestionnaireOptions) {
            addMenuItem(s, menuGestionnaire);
        }

        JMenu menuAide = new JMenu("Aide"); // création menu client
        menuBar.add(menuAide); // ajout du menu à la barre des menus

        addMenuItem("A propos", menuAide);
        addMenuItem("?", menuAide);
        addMenuItem("Annuler", menuAide);
        addMenuItem("Quitter", menuAide);

        add(new AccueilPanel(this));

        pBar = new JProgressBar();
        pBar.setMinimum(0);
        pBar.setMaximum(Etablissement.ELEVES_PAR_GROUPE);
        
        //pBar.setValue(Etablissement.getLastGroupe().getTabEleve().size());
        setIconImage(logo);
    }

    @Override
    public void actionPerformed(ActionEvent e) // Afficher DOS le texte de l’élément de menu actionné.
    {
        //Menu Étudiant
        if (((JMenuItem) e.getSource()).getText() == "Nouveau") {
            this.getContentPane().removeAll();
            this.add(new EleveNouveauPanel(this, pBar));
            repaint2();
        }
        if (((JMenuItem) e.getSource()).getText() == "Afficher") {
            this.getContentPane().removeAll();
            this.add(new EleveAfficherPanel(this, pBar));
            repaint2();
        }
        if (((JMenuItem) e.getSource()).getText() == "Modifier ") {
            this.getContentPane().removeAll();
            this.add(new EleveModifierPanel(this, pBar));
            repaint2();
        }
        //permet de mettre le bulletin de l'eleve demande dans un fichier.txt
        if (((JMenuItem) e.getSource()).getText() == "Imprimer") {
            imprimer();
        }

        //Menu Gestionnaire
        if (((JMenuItem) e.getSource()).getText() == tabGestionnaireOptions[0]) {
            try {
                Serialise.initialiseGroupes();
                JOptionPane.showMessageDialog(this, "Initialisation terminé avec succès", "Succès", JOptionPane.INFORMATION_MESSAGE);
            } catch (Exception ex) {
                messageErreur(ex);
            }
        }//Importer
        if (((JMenuItem) e.getSource()).getText() == tabGestionnaireOptions[1]) {
            try {
                Serialise.importeGroupes("groupes.txt");
                JOptionPane.showMessageDialog(this, "Importation terminé avec succès", "Succès", JOptionPane.INFORMATION_MESSAGE);
            } catch (Exception ex) {
                messageErreur(ex);
            }
        }//Exporter
        if (((JMenuItem) e.getSource()).getText() == tabGestionnaireOptions[2]) {
            try {
                Serialise.exporteGroupes(Etablissement.getTabGroupe());
                JOptionPane.showMessageDialog(this, "Exportation terminé avec succès", "Succès", JOptionPane.INFORMATION_MESSAGE);
            } catch (Exception ex) {
                messageErreur(ex);
            }
        }

        if (((JMenuItem) e.getSource()).getText() == "Lister") {
            this.getContentPane().removeAll();
            this.add(new GestionnaireListerPanel(this));
            repaint2();
        }
        if (((JMenuItem) e.getSource()).getText() == "Modifier") {
            this.getContentPane().removeAll();
            this.add(new GestionnaireModifierPanel(this));
            repaint2();
        }
        if (((JMenuItem) e.getSource()).getText() == "Statistiques") {
            this.getContentPane().removeAll();
            this.add(new GestionnaireStatistiquesPanel(this));
            repaint2();
        }
        if (((JMenuItem) e.getSource()).getText() == "A propos") {
            JOptionPane.showMessageDialog(this, "GestiNotes par Patrick Dominguès et Raphaël Duchaîne de InnovTech"
                    + "\nMontréal,Québec"
                    + "\nVersion 3 (02/12/2016)"
                    + "\nVersion 2 (04/11/2016)"
                    + "\nVersion 1 (12/10/2016)"
                    + "\n"
                    + "\nQuand on demanda aux ingénieurs de InnovTech de concevoir le futur du gestionnaire scolaire,"
                    + "\n(un produit qui serait à la fois convivial, efficace et graphique) la majorité de ceux présents a lancé l'éponge."
                    + "\nMais les deux qui restèrent ont vraiment fait de gros efforts pour accoucher de ce bijou de technologie.", "À Propos", JOptionPane.INFORMATION_MESSAGE);
        }

        if (((JMenuItem) e.getSource()).getText() == "?") {
            JOptionPane.showMessageDialog(this, "Pour utiliser ce bijou d'innovation, il faut savoir:"
                    + "\n-Qu'il faut initialiser/importer dans le menu Gestionnaire"
                    + "\n-Que chaque groupe ne peut comprendre que 10 eleves"
                    + "\n-Qu'un eleve doit être initialisé avec nom, prenom et date "
                    + "\n-Que l'on ne peut pas enlever d'eleve dans cette version"
                    + "\n-Que l'aide se trouve ici"
                    + "\n-Que 2 et 2 font 4", "Instructions", JOptionPane.INFORMATION_MESSAGE);
        }
        if (((JMenuItem) e.getSource()).getText() == "Annuler") {
            this.getContentPane().removeAll();
            this.add(new AccueilPanel(this));
            repaint2();
        }
        if (((JMenuItem) e.getSource()).getText() == "Quitter") {
            this.quitter();
        }

    }

    public void repaint2() {
        this.revalidate();
        this.repaint();
    }

    public void creerBulletin(String chemin, String nomBulletin, Groupe groupe, Eleve eleve) throws IOException {
        BufferedWriter bw = new BufferedWriter(new FileWriter(chemin + File.separator + nomBulletin));

        //Variable string permettant de mettre la première lettre du prénom de l'étudiant en majuscule en cas d'oubli lors de l'enregistrement de l'étudiant
        String prenomPresente = Character.toUpperCase(eleve.getPrenom().charAt(0)) + eleve.getPrenom().substring(1).toLowerCase();

        bw.write("Bulletin de l'eleve " + eleve.getNom().toUpperCase() + ", " + prenomPresente + " (" + eleve.codePermanent() + ")");
        bw.newLine();
        bw.write("Date de naissance (JJ-MM-AAAA): " + eleve.getDateNaissance());
        bw.newLine();
        //Science
        bw.write("Note du premier cours: " + eleve.getTabEvaluation().get(0).getNote() + " %");
        bw.newLine();
        bw.write("Note du deuxieme cours: " + eleve.getTabEvaluation().get(1).getNote() + " %");
        bw.newLine();
        bw.write("Note du troisieme cours: " + eleve.getTabEvaluation().get(2).getNote() + " %");
        bw.newLine();
        bw.write("Note du quatrieme cours: " + eleve.getTabEvaluation().get(3).getNote() + " %");
        bw.newLine();
        bw.write("Note finale de l'eleve: " + eleve.calculerNoteFinale() + " %");
        bw.newLine();
        bw.newLine();
        //Trouver les statistiques du groupe appartenant à l'étudiant
        bw.write("Moyenne du groupe: " + Statistique.calculerMoyenne(groupe) + " %");
        bw.newLine();
        bw.write("Ecart-Type du groupe: " + Statistique.calculerEcartType(groupe));
        bw.newLine();
        bw.write("Variance du groupe: " + Statistique.calculerVariance(groupe));
        bw.close();
    }
    
    //methodes supplementaires
    public void imprimer() throws HeadlessException {
        String nomBulletin = "";
        Eleve eleve = null;
        Groupe groupe = null;
        String emplacementSelectionne = "";
        String code = JOptionPane.showInputDialog("Entrez le code de l'étudiant pour générer le bulletin");

        //Objets qui correspondent
        eleve = Etablissement.searchEleve(code);
        groupe = Etablissement.searchGroupeWEleve(code);
        
         if (eleve == null) {
            messageErreur(new Exception("Code invalide. Veuillez reessayer!"));
            return;
        }

        JOptionPane.showMessageDialog(null, "Eleve trouve!\nVeuillez maintenant choisir le dossier dans lequel vous voulez enregistrer le bulletin", "GestiNotes", JOptionPane.INFORMATION_MESSAGE);

        //Création du nom du fichier bulletin
        nomBulletin = eleve.getNom() + eleve.getPrenom() + ".txt";
        //Création et enregistrement du fichier bulletin
        JFileChooser emplacementBulletin = new JFileChooser();
        emplacementBulletin.setFileSelectionMode(JFileChooser.DIRECTORIES_ONLY);
        int reponse = emplacementBulletin.showSaveDialog(this);
        if (reponse == JFileChooser.APPROVE_OPTION) {
            File emplacement = emplacementBulletin.getSelectedFile();
            emplacementSelectionne = emplacement.getPath();
        }
        try {
            creerBulletin(emplacementSelectionne, nomBulletin, groupe, eleve);
            JOptionPane.showMessageDialog(null, "Bulletin créé avec succès!");

        } catch (IOException ex) {
            messageErreur(new IOException("Échec lors de la création du fichier."));
            
        }catch (Exception ex) {
            messageErreur(new Exception("Erreur inconnue lors de la création du fichier\n Veuillez contacter les développeur!"));
            
        }
        

       
    }

}
