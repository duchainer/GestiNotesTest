package vue;

import Réutilisable.UtileFrame;
import java.awt.BorderLayout;
import modele.Groupe;
import modele.Statistique;
import modele.Etablissement;
import javax.swing.*;
import java.awt.event.*;
import java.awt.*;
import java.util.ArrayList;
import java.awt.HeadlessException;
import java.awt.Image;
import java.io.BufferedWriter;
import java.io.EOFException;
import java.io.File;
import java.io.FileInputStream;
import java.io.FileNotFoundException;
import java.io.FileOutputStream;
import java.io.FileWriter;
import java.io.IOException;
import java.io.InputStream;
import java.io.InputStreamReader;
import java.io.ObjectInputStream;
import java.io.ObjectOutputStream;
import modele.Eleve;
//import modele.Serialise;

/**
 * 28/09/2016
 *
 * @author Patrick Domingues 28/9/2016 et Raphael Duchaine 26/10/2016
 */
public class PrincipaleFrame extends UtileFrame {

    

    //Attributs
    //Attributs graphiques
    
    private final String quitterFileSelection = "Souhaitez-vous abandonner la selection de fichier?",
            titreQuitterFileSelection = "Abandonner la Selection",
            responseNoQuitterFileSelection = "Vous allez être redirigé vers la fenêtre de sélection de fichiers",
            responseYesQuitterFileSelection = "Vous allez être redirigé vers la fenêtre principale";
    
    InputStream is;
    InputStreamReader reader;
    public JTextArea aideEnLigne = new JTextArea();
    
    //VENANT DE UTILEFRAME:
    //JPanel simplePanel;  
    //ArrayList<JButton> boutons = new ArrayList<JButton>();
    //ArrayList<JTextField> champs = new ArrayList<JTextField>();
    JProgressBar pBar;
    boolean retour = true; //Variable qui permet de savoir quand l'utilisateur a l'intention de revenir sur la fenêtre d'explorateur de fichiers

    String[] tooltipsMenus = {
        "Options liées aux élèves individuellement",
        "Options liées aux groupes contenant les élèves",
        "Options d'aide et autres"};
    String[] tooltipsOptions = {
        //Menu Eleve
        "Permet d'enregistrer un élève",//"Nouveau"
        "Permet d'afficher les informations d'un élève",//"Afficher"
        "Permet de modifier les informations d'un élève",//"Modifier"
        "Permet d'imprimer les informations d'un élève",//"Imprimer"

        //Menu Gestionnaire
        "Permet d'initialiser les groupes d'élèves",//"Initialiser"
        "Permet d'importer les groupes d'élèves",//"Importer"
        "Permet d'exporter les groupes d'élèves",//"Exporter"
        "Permet de lister les informations des élèves d'un groupe",//"Lister"
        "Permet de modifier les informations des élèves d'un groupe",//"Modifier"
        "Permet d'afficher les statistiques d'un groupe",//"Statistiques"

        //Menu Aide
        "Informations générale sur le programme",//À Propos
        "Aide générale sur le programme",//?
        "Retour à l'écran titre",//Annuler
        "Quitter l'application"};//Quitter

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
        menuEleve.setToolTipText(tooltipsMenus[0]);
        menuBar.add(menuEleve); // ajout du menu à la barre des menus

        addMenuItem("Nouveau", menuEleve, tooltipsOptions[0]);
        addMenuItem("Afficher", menuEleve, tooltipsOptions[1]);
        addMenuItem("Modifier ", menuEleve, tooltipsOptions[2]);
        addMenuItem("Imprimer", menuEleve, tooltipsOptions[3]);

        JMenu menuGestionnaire = new JMenu("Gestionnaire"); // création menu client
        menuGestionnaire.setToolTipText(tooltipsMenus[1]);
        menuBar.add(menuGestionnaire); // ajout du menu à la barre des menus
        int i = 4;
        for (String s : tabGestionnaireOptions) {
            addMenuItem(s, menuGestionnaire, tooltipsOptions[i]);
            i++;
        }

        JMenu menuAide = new JMenu("Aide"); // création menu client
        menuAide.setToolTipText(tooltipsMenus[2]);
        menuBar.add(menuAide); // ajout du menu à la barre des menus

        addMenuItem("A propos", menuAide, tooltipsOptions[i]);
        addMenuItem("?", menuAide, tooltipsOptions[i + 1]);
        addMenuItem("Annuler", menuAide, tooltipsOptions[i + 2]);
        addMenuItem("Quitter", menuAide, tooltipsOptions[i + 3]);

        add(new AccueilPanel(this));

        pBar = new JProgressBar();
        pBar.setMinimum(0);
        pBar.setMaximum(Etablissement.ELEVES_PAR_GROUPE);

        //ForTesting : uncomment
        //pBar.setValue(Etablissement.getLastGroupe().getTabEleve().size());
        
        //Aide en ligne 
        try {
        is = getClass().getResourceAsStream("/aide.txt");
        reader = new InputStreamReader(is);
        aideEnLigne.read(reader, "");
        JOptionPane.showMessageDialog(this, aideEnLigne.getText(),"Aide en ligne", JOptionPane.INFORMATION_MESSAGE);
        } catch (Exception ex) {
            messageErreur("Erreur lors de la lecture de l'aide en ligne", ex);
        }
    }

    @Override
    public void actionPerformed(ActionEvent e) //Fait l'action lié à l’élément de menu actionné (en utilisant son texte).
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
        if (((JMenuItem) e.getSource()).getText() == tabGestionnaireOptions[0]) 
            initialiser();

        
        if (((JMenuItem) e.getSource()).getText() == tabGestionnaireOptions[1])
            importer();
        
        if (((JMenuItem) e.getSource()).getText() == tabGestionnaireOptions[2]) {
            exporter();
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
                    + "\nVersion 4 (19/12/2016)"
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
                    + "\n-Qu'en cas de doute, il suffit de survoler", "Instructions", JOptionPane.INFORMATION_MESSAGE);
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
            messageErreur("Échec lors de la création du fichier", ex);

        } catch (Exception ex) {
            messageErreur(new Exception("Erreur inconnue lors de la création du fichier\n Veuillez contacter les développeur!"));

        }

    }

    private void initialiser() throws HeadlessException {
        ObjectInputStream entree = null;
        ArrayList<Groupe> init = new ArrayList<Groupe>();
        int reponse = JOptionPane.showConfirmDialog(null, "Voulez-vous vraiment initialiser?(Cela écrasera les groupes existants)",
                tabGestionnaireOptions[0], JOptionPane.YES_NO_OPTION);
        if (reponse == JOptionPane.NO_OPTION) {
            return;
        }
        try {
            //Pour lire le fichier objet :
            // Ouverture du flux objet en entree
            entree = new ObjectInputStream(
                    new FileInputStream("initialisation.txt"));
            // Lecture de l'objet contenu dans le fichier
            init = (ArrayList<Groupe>) entree.readObject();
            Etablissement.setTabGroupe(init);
            JOptionPane.showMessageDialog(this, "Initialisation effectue avec succes", "Succès", JOptionPane.INFORMATION_MESSAGE);
        } catch (FileNotFoundException ex) {
            // Exception declenchee si le fichier n'existe pas
            messageErreur("Fichier introuvable!", ex);
        } catch (EOFException ex) {
            // Exception declenchee si la fin du fichier est atteinte
            messageErreur("Fin du fichier\nAucun Objet", ex);
        } catch (IOException ex) {
            // Exception declenchee si un autre probleme acces fichier
            messageErreur("Erreur lors de la lecture du fichier", ex);
        } catch (ClassNotFoundException ex) {
            messageErreur("Erreur lors de la lecture du fichier", ex);
        } finally {
            try {
                entree.close();
            } catch (IOException ex) {
                messageErreur("Erreur lors de la fermeture du fichier", ex);
            } catch (Exception ex) {
                messageErreur("Erreur lors de la fermeture du fichier", ex);
            }
        }
        return;
    }

    private void importer() throws HeadlessException {
        ObjectInputStream input = null;
        while (retour = true) {
            String chemin = "";
            JFileChooser importe = new JFileChooser();
            importe.setFileSelectionMode(JFileChooser.FILES_ONLY);
            int reponse = importe.showOpenDialog(this);
            if (reponse == JFileChooser.APPROVE_OPTION) {
                File importeur = importe.getSelectedFile();
                chemin = importeur.getPath();
                try {
                    input = new ObjectInputStream(new FileInputStream(chemin));
                    Etablissement.setTabGroupe((ArrayList<Groupe>) input.readObject());
                    JOptionPane.showMessageDialog(null, "Les élèves ont bien été chargées depuis la base de donnée selectionnée.");
                    try {
                        input.close();
                    } catch (IOException ex) {
                        messageErreur("Erreur lors de la fermeture du fichier", ex);
                    } finally {
                        break;
                    }
                } catch (FileNotFoundException ex) {
                    messageErreur("Fichier introuvable", ex);
                } catch (EOFException ex) {
                    messageErreur("Erreur EOF", ex);
                } catch (IOException ex) {
                    messageErreur("Erreur lors du traitement du fichier", ex);
                } catch (Exception ex) {
                    ex.printStackTrace();
                }
            } else if (reponse == JFileChooser.CANCEL_OPTION) {
                int redirection = JOptionPane.showConfirmDialog(null, quitterFileSelection, titreQuitterFileSelection, JOptionPane.YES_NO_OPTION);
                if (redirection == JOptionPane.NO_OPTION) {
                    JOptionPane.showMessageDialog(null, responseNoQuitterFileSelection);
                    retour = true;
                } else if (redirection == JOptionPane.YES_OPTION) {
                    JOptionPane.showMessageDialog(null, responseYesQuitterFileSelection);
                    break;
                }
            }
            
        }
        this.revalidate();
        this.repaint();
    }

    private void exporter() {
        JFrame f = this; //permet de mettre le frame dans une variable, et le me ttre dans export.showSaveDialog(f) (car mettre "this" ne marche pas)
        Thread exportToFile = new Thread() {
            @Override
            public void run() {
                while (retour = true) {
                    String chemin = "";
                    JFileChooser export = new JFileChooser();
                    export.setFileSelectionMode(JFileChooser.DIRECTORIES_ONLY);
                    int reponse;
                    reponse = export.showSaveDialog(f);
                    if (reponse == JFileChooser.APPROVE_OPTION) {
                        File emplacement = export.getSelectedFile();
                        chemin = emplacement.getPath();
                        ObjectOutputStream output = null;
                        try {
                            output = new ObjectOutputStream(new FileOutputStream(chemin + File.separator + "dataEtudiants.dat"));
                        } catch (IOException ex) {
                            JOptionPane.showMessageDialog(null, "Erreur lors de la creation du OOS");
                        }
                        try {
                            output.writeObject(Etablissement.getTabGroupe());
                            try {
                                output.close();
                                JOptionPane.showMessageDialog(null, "Les eleves ont bien ete enregistres avec succes");
                                break;
                            } catch (IOException ex) {
                                JOptionPane.showMessageDialog(null, "Erreur lors de la fermeture de output");
                            }
                        } catch (IOException ex) {
                            JOptionPane.showMessageDialog(null, "Erreur lors de la creation du fichier objet.\nVous allez être redirige vers le menu principal");
                            break;
                        }
                        
                    } else {
                        int redirection = JOptionPane.showConfirmDialog(null, quitterFileSelection, titreQuitterFileSelection, JOptionPane.YES_NO_OPTION);
                        if (redirection == JOptionPane.NO_OPTION) {
                            JOptionPane.showMessageDialog(null, responseNoQuitterFileSelection);
                            retour = true;
                        } else if (redirection == JOptionPane.YES_OPTION) {
                            JOptionPane.showMessageDialog(null, responseYesQuitterFileSelection);
                            break;
                        }
                    }
                }
            }
        };
        //Lancement du thread
        exportToFile.run();
    }

}
