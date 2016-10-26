
import javax.swing.*;
import java.awt.GridLayout;
import java.awt.event.*;
import java.util.ArrayList;
import java.awt.Color;
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
            boolean sortir = false;
            do {

                //Creation d'un eleve dans un groupe (cree un autre groupe si le groupe present a 10 eleves)
                String titre = "Enregistrer un élève";
                String nom = JOptionPane.showInputDialog(null, "Entrer le nom de l'eleve:", titre, JOptionPane.QUESTION_MESSAGE);
                String prenom = JOptionPane.showInputDialog(null, "Entrer le prenom de l'eleve:", titre, JOptionPane.QUESTION_MESSAGE);
                String date = JOptionPane.showInputDialog(null, "Entrer la date de naissance de l'eleve (JJ-MM-AAAA):", titre, JOptionPane.QUESTION_MESSAGE);
                Eleve eleve = new Eleve(nom, prenom, date);

                Etablissement.addEleve(eleve);

                int reponse = JOptionPane.showConfirmDialog(null, "Voulez-vous enregistrer un autre élève?",
                        titre, JOptionPane.YES_NO_OPTION);
                if (reponse == JOptionPane.NO_OPTION) {
                    sortir = true;
                }
            } while (!sortir);
        }

        if (event.getSource() == boutons.get(1)) {
            //Enregistrement (ou modification) d'une note d'un eleve d'un groupe
            boolean sortir = false;
            do {

                //Creation d'un eleve dans un croupe (cree un autre groupe si le groupe present e 10 eleves)
                String titre = "Modifier des notes";
                String codePermanent = JOptionPane.showInputDialog(null, "Entrer le code permanent de l'eleve:", titre, JOptionPane.QUESTION_MESSAGE);
                Eleve eleve;
                try {
                    eleve = Etablissement.searchEleve(codePermanent);
                    if(eleve.equals(null))
                        throw new Exception("Eleve introuvable");
                }catch (NullPointerException e) {
                    JOptionPane.showMessageDialog(null, "Eleve introuvable", "ERROR", JOptionPane.ERROR_MESSAGE);
                    break;
                } 
                catch (Exception e) {
                    JOptionPane.showMessageDialog(null, e.getMessage(), "ERROR", JOptionPane.ERROR_MESSAGE);
                    break;
                }

                for (Evaluation c : eleve.getTabCours()) {
                    try {
                        String note = JOptionPane.showInputDialog(null, "Entrer la note de l'eleve en " + c.getNom().toUpperCase() + " ou -1 s'il n'en n'a pas:" + "\n Note actuelle: " + c.getNote(), titre, JOptionPane.QUESTION_MESSAGE);
                        if (note == "") {
                            break;
                        }
                        double noteInt = Double.parseDouble(note);
                        if (noteInt < 0) {
                            noteInt = -1;
                        } else if (noteInt > 100) {
                            throw new Exception("La note doit être comprise en 0 et 100");
                        }
                        c.setNote(noteInt);
                    } catch (NullPointerException e) {
                        JOptionPane.showMessageDialog(null, "La note de "+c.getNom()+" reste inchangée.", titre, JOptionPane.INFORMATION_MESSAGE);
                    } catch (NumberFormatException e) {
                        JOptionPane.showMessageDialog(null, "La note de "+c.getNom()+" reste inchangée.", titre, JOptionPane.INFORMATION_MESSAGE);
                    } catch (Exception e) {
                        JOptionPane.showMessageDialog(null, e.toString() + " --- " + e.getMessage(), "ERROR", JOptionPane.ERROR_MESSAGE);
                    }
                }

                int reponse = JOptionPane.showConfirmDialog(null, "Voulez-vous modifier d'autres notes?",
                        titre, JOptionPane.YES_NO_OPTION);
                if (reponse == JOptionPane.NO_OPTION) {
                    sortir = true;
                }
            } while (!sortir);

        }

        if (event.getSource() == boutons.get(2)) {
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
        //TODO Gestion d'exceptions
        if (event.getSource() == boutons.get(3)) {
            //Affichage des statistiques d'un groupe (moyenne, variance, ecart-type)
            try {
                int i = (Integer.parseInt(JOptionPane.showInputDialog(null, "Vous voulez consulter les statistiques de quel groupe?",
                        "Statistiques de classe", JOptionPane.QUESTION_MESSAGE)));
                Groupe groupe = Etablissement.getTabGroupe().get(i);
                String stats = ("Statistiques du groupe " + i + ":\n"
                        + "Moyenne : " + Statistique.calculerMoyenne(groupe) + "\n"
                        + "Variance: " + Statistique.calculerVariance(groupe) + "\n"
                        + "Ecart-Type: " + Statistique.calculerEcartType(groupe) + "\n" /*+"Nombre d'élèves: "+groupe.getTabEleve().size()*/);
                System.err.println(" sdfsdfsdf");
                JOptionPane.showMessageDialog(null, stats, "Afficher des Statistiques", JOptionPane.INFORMATION_MESSAGE);
            } catch (Exception e) {
                JOptionPane.showMessageDialog(null, e.getMessage(), "ERROR", JOptionPane.ERROR_MESSAGE);
            }
            //System.out.println("QuelqueCHose"+Etablissement.tabGroupe.get(i).listeEleve());

        }

        if (event.getSource() == boutons.get(4)) {
            //Permet l'arret du programme
            int reponse = JOptionPane.showConfirmDialog(null, "Voulez-vous vraiment quitter?",
                    "Quitter", JOptionPane.YES_NO_OPTION);
            if (reponse == JOptionPane.YES_OPTION) {
                System.exit(0);
            }
        }
    }
}
