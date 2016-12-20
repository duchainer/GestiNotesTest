package Réutilisable;


import java.awt.BorderLayout;
import java.awt.HeadlessException;
import javax.swing.*;
import java.awt.*;
import java.awt.event.*;
import java.util.ArrayList;

/**
 * Crée 2016-10-31,08:11:51
 *
 * @author Raphael Duchaine
 */
public class UtileFrame extends JFrame implements ActionListener {

    //variables

    /**
     * Le JPanel utlise
     */
    public JPanel simplePanel;

    /**
     * ArrayList contenant tous les JButton
     */
    public ArrayList<JButton> boutons;

    /**
     * ArrayList contenant tous les JTextField
     */
    public ArrayList<JTextField> champs;

    /**
     * Le JMenuBar utilise
     */
    public JMenuBar menuBar; //tabbedPane;

    /**
     * Le JProgressBar utilise
     */
    public JProgressBar pBar;

    //Méthodes
    //Constructeur

    /**
     * Constructeur par default qui cree un JFrame avec les informations fournis en parametres
     * @param title Le nom du JFrame (Le titre)
     * @param x La largeur du JFrame
     * @param y La longueur du JFrame
     */
    public UtileFrame(String title, int x, int y) {
        setTitle(title);
        setSize(x, y);
        simplePanel = new JPanel();
        add(simplePanel);
        boutons = new ArrayList<JButton>();
        champs = new ArrayList<JTextField>();
        menuBar = new JMenuBar();
        setLocationRelativeTo(null);            // Fenetre centree
    }
    /**
    * Constructeur sans parametres, non utilise
    */
    UtileFrame() {
        throw new UnsupportedOperationException("Not supported yet."); //To change body of generated methods, choose Tools | Templates.
    }

    //Autres Méthodes

    /**
     * Methode qui ajoute un JButton au simplePanel et au ArrayList boutons
     * @param label Le nom du bouton
     */
    public void addBouton(String label) {
        JButton bouton = new JButton(label);    //Cree le bouton
        simplePanel.add(bouton);    //Ajoute bouton au panneau
        bouton.addActionListener(this);//Rend le bouton interactif
        boutons.add(bouton); //Ajoute le bouton dans le tableau boutons
    }
    /**
     * Methode qui ajoute un JLabel vide au simplePanel, dans le but de bien formatter les composantes graphiques
     */
    private void addEspace() {
        simplePanel.add(new JLabel(""));
    }

     /**
     * Methode qui ajoute un JTextfield au simplePanel et au ArrayList champs, precede par un JLabel
     * @param texte Le nom du JLabel qui apparait avant le JTextfield
     * @return label le JLabel qui apparait devant le JTextField
     */
    public JLabel addChamp(String texte) {
        JLabel label = new JLabel(texte); //Cree le label
        simplePanel.add(label); //Ajoute le label au panneau
        JTextField champ = new JTextField(10);//Cree le champ
        simplePanel.add(champ); //Ajoute le champ au panneau
        champs.add(champ);//Ajoute le champ dans le tableau champs
        return label; //Retourne le label
    }

    /**
     * Methode qui ajoute un JLabel au simplePanel
     * @param texte Le nom du JLabel
     * @return label Le JLabel
     */
    public JLabel addLabel(String texte) {
        JLabel label = new JLabel(texte);
        simplePanel.add(label);
        return label;
    }
    
    /**
     * Methode qui ajoute un JMenuItem a un JMenu, avec un ToolTip vide
     * @param texte Le nom du JMenuItem a ajouter
     * @param menu Le menu dans lequel on ajoute un JMenuItem
     * @return Le Jmenu
     */
    public JMenu addMenuItem (String texte, JMenu menu) {
        return addMenuItem (texte,menu,"");
    }

    /**
     * Methode qui ajoute un JMenuItem a un JMenu, avec un ToolTip personalise
     * @param texte Le nom du JMenuItem a ajouter
     * @param menu Le menu dans lequel on ajoute un JMenuItem
     * @param toolTipText Le texte qui apparait lorsque l'on survole le JMenuItem
     * @return Le Jmenu
     */
    public JMenu addMenuItem (String texte, JMenu menu,String toolTipText) {
        JMenuItem item = new JMenuItem(texte);
        item.addActionListener(this);
        menu.add(item);
        menu.addSeparator();
        item.setToolTipText(toolTipText);
        return menu;
    }

    @Override
    public void actionPerformed(ActionEvent event) { // Methode recoit evenement
        if (((JButton) event.getSource()).getText() == "HelloWorld") {
            System.out.println("HelloWorld");
        }
    }
    
    /**
     * Methode qui permet d'afficher le message d'erreur
     * @param e l'erreur a intercepter
     * @throws HeadlessException l'erreur a remonter
     */
    public void messageErreur(Exception e) throws HeadlessException {
        JOptionPane.showMessageDialog(null, e.getMessage(), "ERROR", JOptionPane.ERROR_MESSAGE);
    }

    /**
     * Methode qui permet d'afficher le message d'erreur
     * @param fr Le texte a ajouter dans l'erreur
     * @param e l'erreur a intercepter
     * @throws HeadlessException l'erreur a remonter
     */
    public void messageErreur(String fr,Exception e) throws HeadlessException {
        messageErreur(new Exception(fr+"\nSi cela se reproduit, voici le rapport d'erreur:\n"+e.toString()));
    }

    /**
     * Methode qui permet de demander a l'utilisateur la confirmation pour quitter
     * @throws HeadlessException l'erreur a remonter
     */
    public void quitter() throws HeadlessException {
        //Permet l'arret du programme
        int reponse = JOptionPane.showConfirmDialog(null, "Voulez-vous vraiment quitter?",
                "Quitter", JOptionPane.YES_NO_OPTION);
        if (reponse == JOptionPane.YES_OPTION) {
            System.exit(0);
        }
    }

}
