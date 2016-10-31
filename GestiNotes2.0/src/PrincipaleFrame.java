
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
public class PrincipaleFrame extends JFrame implements ActionListener {

    //Attributs
    
    //Attributs graphiques
    JPanel simplePanel;
    JRadioButton radio1, radio2;
    ButtonGroup group;
        

    
    ArrayList<JButton> boutons = new ArrayList<JButton>();
    ArrayList<JTextField> champs = new ArrayList<JTextField>();
    
    Image logo =new LogoRosemont().logo;

    
    //Constructeurs
    public PrincipaleFrame() {
        setTitle("GestiNotes_02");    		       		// Titre
        setIconImage(logo);
        setSize(255, 350); 								// Dimensions
        setDefaultCloseOperation(JFrame.EXIT_ON_CLOSE); // Fermeture par x
        setLocationRelativeTo(null);					// Fenetre centree
        setResizable(true);

            //Look And Feel
        try {
            UIManager.setLookAndFeel(UIManager.getSystemLookAndFeelClassName());
//            UIManager.setLookAndFeel("javax.swing.plaf.nimbus.NimbusLookAndFeel");
//            System.out.println(UIManager.getLookAndFeel());
//            System.out.println(UIManager.getSystemLookAndFeelClassName());
        } catch (Exception e) {
            System.err.println("Erreur de Look and feel");
        }

        simplePanel = new JPanel(); 					//Cree le panneau

        GridLayout gl = new GridLayout(5, 0, 0, 25);	//Cree GridLayout
        simplePanel.setLayout(gl);

        addBouton("Inscrire un élève");
        boutons.get(boutons.size() - 1).setForeground(Color.blue);
        addBouton("Gérer les notes");
        boutons.get(boutons.size() - 1).setForeground(Color.green);
        addBouton("Afficher les élèves d'un groupe");
        boutons.get(boutons.size() - 1).setForeground(Color.LIGHT_GRAY);
        addBouton("Afficher les statistiques d'un groupe");
        boutons.get(boutons.size() - 1).setForeground(Color.orange);
        addBouton("Quitter");
        boutons.get(boutons.size() - 1).setForeground(Color.red);

        add(simplePanel);

    }

    public void Quitter() throws HeadlessException {
        //Permet l'arret du programme
        int reponse = JOptionPane.showConfirmDialog(null, "Voulez-vous vraiment quitter?",
                "Quitter", JOptionPane.YES_NO_OPTION);
        if (reponse == JOptionPane.YES_OPTION) {
            System.exit(0);
        }
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
//                System.err.println(" sdfsdfsdf");
JOptionPane.showMessageDialog(null, stats, "Afficher des Statistiques", JOptionPane.INFORMATION_MESSAGE);
        } catch (Exception e) {
            JOptionPane.showMessageDialog(null, e.getMessage(), "ERROR", JOptionPane.ERROR_MESSAGE);
        }
        //System.out.println("QuelqueCHose"+Etablissement.tabGroupe.get(i).listeEleve());
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
    private void addEspace() {
        simplePanel.add(new JLabel(""));
    }

    public void addBouton(String label) {
        boutons.add(new JButton(label));				//Cree le bouton et le met dans boutons
        simplePanel.add(boutons.get(boutons.size() - 1)); 		//Ajoute bouton au panneau
        boutons.get(boutons.size() - 1).addActionListener(this);	//Rend le bouton interactif
    }

    @Override
    public void actionPerformed(ActionEvent event) {
        if (event.getSource() == boutons.get(0)) {
            //ajouterEleve();
        }

        if (event.getSource() == boutons.get(1)) {
            //enregistrerNote();

        }

        if (event.getSource() == boutons.get(2)) {
            listerEleve();
        }
        //TODO Gestion d'exceptions
        if (event.getSource() == boutons.get(3)) {
            afficherStatistiques();

        }

        if (event.getSource() == boutons.get(4)) {
            Quitter();
        }
    }
}
