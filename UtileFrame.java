/**
 * Crée 2016-10-31,08:11:51
 *
 * @author Raphael Duchaine
 */
import java.awt.HeadlessException;
import javax.swing.*;
//import java.awt.*;
import java.awt.event.*;
import java.util.ArrayList;

public class UtileFrame extends JFrame implements ActionListener {
    //variables
    JPanel simplePanel;
    ArrayList<JButton> boutons;
    ArrayList<JTextField> champs;
    public JTabbedPane tabbedPane;

    //Méthodes
    //Constructeur
    public UtileFrame(String title, int x, int y) {
        setTitle(title);
        setSize(x, y);
        simplePanel = new JPanel();
        add(simplePanel);
        boutons = new ArrayList<JButton>();
        champs = new ArrayList<JTextField>();
        setLocationRelativeTo(null);            // Fenetre centree
    }

    //Autres Méthodes
    public void addBouton(String label) {
        JButton bouton = new JButton(label);    //Cree le bouton
        simplePanel.add(bouton);    //Ajoute bouton au panneau
        bouton.addActionListener(this);//Rend le bouton interactif
        boutons.add(bouton); //Ajoute le bouton dans le tableau boutons
    }

    private void addEspace() {
        simplePanel.add(new JLabel(""));
    }

    public JLabel addChamp(String texte) {
        JLabel label = new JLabel(texte); //Cree le label
        simplePanel.add(label); //Ajoute le label au panneau
        JTextField champ = new JTextField(10);//Cree le champ
        simplePanel.add(champ); //Ajoute le champ au panneau
        champs.add(champ);//Ajoute le champ dans le tableau champs
        return label; //Retourne le label
    }
    public JLabel addLabel(String texte) {
        JLabel label =new JLabel(texte);
        simplePanel.add(label);
        return label;
    }

    @Override
    public void actionPerformed(ActionEvent event) { // Methode recoit evenement
        if (((JButton) event.getSource()).getText() == "HelloWorld") {
            System.out.println("HelloWorld");
        }
    }

    public void quitter() throws HeadlessException {
        //Permet l'arret du programme
        int reponse = JOptionPane.showConfirmDialog(null, "Voulez-vous vraiment quitter?",
                "Quitter", JOptionPane.YES_NO_OPTION);
        if (reponse == JOptionPane.YES_OPTION) {
            System.exit(0);
        }
    }

}
