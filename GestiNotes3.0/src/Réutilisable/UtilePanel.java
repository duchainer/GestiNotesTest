package Réutilisable;

import java.awt.HeadlessException;
import javax.swing.*;
//import java.awt.*;
import java.awt.event.*;
import java.util.ArrayList;

/**
 * Crée 2016-10-31,09:53:43
 *
 * @author Raphael Duchaine
 */
public class UtilePanel extends JPanel implements ActionListener {

    //variables
    /**
     * ArrayList contenant tous les JButton
     */
    public ArrayList<JButton> boutons;

    /**
     * ArrayList contenant tous les JTextField
     */
    public ArrayList<JTextField> champs;

    /**
     * ArrayList contenant tous les JComboBox
     */
    public ArrayList<JComboBox> tabComboBox;

    /**
     * Le JPanel utlise
     */
    public JPanel simplePanel = new JPanel();

    /**
     * Le UtileFrame utilise
     */
    public UtileFrame fenetre;

    /**
     * Le JTabbedPane utilise
     */
    public JTabbedPane tabbedPane;

    //Méthodes
    //Constructeur
    /**
     * Constructeur qui cree un UtilePanel avec les informations fournis en
     * parametres
     *
     * @param fenetre Le UtileFrame a utiliser
     * @param tabbedPane Le JTabbedPane a utiliser
     */
    public UtilePanel(UtileFrame fenetre, JTabbedPane tabbedPane) {
        this();
        this.fenetre = fenetre;
        this.tabbedPane = tabbedPane;
    }

    /**
     * Constructeur qui cree un UtilePanel avec les informations fournis en
     * parametres
     *
     * @param fenetre Le UtileFrame a utiliser
     */
    public UtilePanel(UtileFrame fenetre) {
        this(fenetre, null);
    }

    /**
     * Constructeur par default qui cree un UtilePanel, ainsi que les
     * composantes de bases
     */
    public UtilePanel() {
        boutons = new ArrayList<JButton>();
        champs = new ArrayList<JTextField>();
        tabComboBox = new ArrayList<JComboBox>();
        add(simplePanel);
        //simplePanel.setBackground(Color.CYAN);

    }
    //Get-Set
    //toString
    //Autres Méthodes

    //Ajoute un comboBox contenant une liste de String 
    /**
     * Methode qui ajoute un JComboBox au simplePanel et au ArrayList
     * tabComboBox
     */
    public void addComboBox() {
        JComboBox<String> comboBox = new JComboBox<String>();
        simplePanel.add(comboBox);
        comboBox.addActionListener(this);
        tabComboBox.add(comboBox);
    }

    /**
     * Methode qui ajoute un JButton au simplePanel et au ArrayList boutons
     *
     * @param label Le nom du bouton
     */
    public void addBouton(String label) {
        JButton bouton = new JButton(label);
        simplePanel.add(bouton);
        bouton.addActionListener(this);
        boutons.add(bouton);
    }

    /**
     * Methode qui retourne le champ demandé à la position choisi en parametre
     *
     * @param index la position du JTextField dans le tableau champs
     * @return Le dernier champs du tableau boutons
     */
    public JTextField getChamp(int index) {
        return champs.get(index);
    }

    /**
     * Methode qui retourne le bouton demandé à la position choisi en parametre
     *
     * @param index la position du JButton dans le tableau boutons
     * @return Le dernier champs du tableau boutons
     */
    public JButton getBouton(int index) {
        return boutons.get(index);
    }

    /**
     * Methode qui retourne le dernier bouton dans le tableau boutons
     *
     * @return Le dernier champs du tableau boutons
     */
    public JButton getLastBouton() {
        return getBouton(boutons.size() - 1);
    }

    /**
     * Methode qui retourne le dernier champ dans le tableau champs
     *
     * @return Le dernier champs du tableau champs
     */
    public JTextField getLastChamp() {
        return getChamp(champs.size() - 1);
    }

    /**
     * Methode qui ajoute un JTextfield au simplePanel et au ArrayList champs
     */
    public void addTextField() {
        JTextField champ = new JTextField(10);
        simplePanel.add(champ);
        champs.add(champ);
    }

    /**
     * Methode qui ajoute un JTextfield au simplePanel et au ArrayList champs,
     * precede par un JLabel (sans ToolTip)
     *
     * @param label Le nom du JLabel qui apparait avant le JTextfield
     */
    public void addChamp(String label) {
        addChamp(label, "");
    }

    /**
     * Methode qui ajoute un JTextfield au simplePanel et au ArrayList champs,
     * precede par un JLabel
     *
     * @param label Le nom du JLabel qui apparait avant le JTextfield
     * @param tooltipText Le texte qui apparait lorsque l'on survole le JLabel
     * et le JTextField
     */
    public void addChamp(String label, String tooltipText) {
        final JLabel jLabel = new JLabel(label);
        simplePanel.add(jLabel);
        JTextField champ = new JTextField(10);
        simplePanel.add(champ);
        champs.add(champ);
        jLabel.setToolTipText(tooltipText);
        champ.setToolTipText(tooltipText);
    }

    /**
     * Methode qui ajoute un JLabel au simplePanel
     *
     * @param texte Le nom du JLabel
     * @return label Le JLabel
     */
    public JLabel addLabel(String texte) {
        return addLabel(texte, "");
    }

    /**
     * Methode qui ajoute un JLabel au simplePanel
     *
     * @param texte Le nom du JLabel
     * @param tooltipText Le texte qui apparait lorsque l'on survole le JLabel
     * et le JTextField
     * @return label Le JLabel
     */
    public JLabel addLabel(String texte, String tooltipText) {
        JLabel label = new JLabel(texte);
        simplePanel.add(label);
        label.setToolTipText(tooltipText);
        return label;
    }

    /**
     * Methode qui ajoute un JLabel vide au simplePanel, dans le but de bien
     * formatter les composantes graphiques
     */
    public void addEspace() {
        simplePanel.add(new JLabel(""));
    }

    /**
     * Methode qui permet de mettre un texte dans le JtextField choisi
     *
     * @param index la position du JTextField dans le tableau
     * @param texte Le texte a mettre
     */
    public void setChamp(int index, String texte) {
        champs.get(index).setText(texte);
    }

    /**
     * Methode qui permet de choisir si un JTextField est editable ou non
     *
     * @param index la position du JTextField dans le tableau
     * @param editable boolean qui determine si le JTextField est editable (True
     * = editable/False = non-editable)
     */
    public void setChampEditable(int index, boolean editable) {
        champs.get(index).setEditable(editable);
    }

    /**
     * Methode qui permet de vider le contenu des JtextFields du tableau champs
     */
    public void viderChamps() {
        for (JTextField champ : champs) {
            champ.setText("");
        }
    }

    @Override
    public void actionPerformed(ActionEvent event) {  // Methode recoit evenemen
        if (event.getSource().getClass().toString().equals("JButton")) {
            if (((JButton) event.getSource()).getText() == "HelloWorld") {
                System.out.println("HelloWorld");
            }
        }
    }

    /**
     * Methode qui permet d'afficher le message d'erreur
     *
     * @param ex l'erreur a intercepter
     * @throws HeadlessException l'erreur a remonter
     */
    public void messageErreur(Exception ex) throws HeadlessException {
        JOptionPane.showMessageDialog(null, ex.getMessage(), "ERROR", JOptionPane.ERROR_MESSAGE);
    }

    /**
     * Methode qui permet d'afficher le message d'erreur
     *
     * @param fr Le message d'erreur en français compréhensible
     * @param ex l'erreur a intercepter
     * @throws HeadlessException l'erreur a remonter
     */
    public void messageErreur(String fr, Exception ex) throws HeadlessException {
        messageErreur(new Exception(fr + "\nSi cela se produit à nouveau,\n voici le rapport d'erreur que vous pouvez donner au support technique:" + ex.toString()));
    }
}
