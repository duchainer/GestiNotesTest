package vue;

/**
 * Crée 2016-10-31,09:53:43
 *
 * @author Raphael Duchaine
 */
import java.awt.HeadlessException;
import javax.swing.*;
//import java.awt.*;
import java.awt.event.*;
import java.util.ArrayList;

public class UtilePanel extends JPanel implements ActionListener{

    //variables
    ArrayList<JButton> boutons;
    ArrayList<JTextField> champs;
    ArrayList<JComboBox> comboBoxes;

    JPanel simplePanel = new JPanel();
    UtileFrame fenetre;
    JTabbedPane tabbedPane;
    
    //Méthodes
    //Constructeur
    public UtilePanel(UtileFrame fenetre, JTabbedPane tabbedPane) {
        this();
        this.fenetre = fenetre;
        this.tabbedPane = tabbedPane;
    }
    
    public UtilePanel(UtileFrame fenetre) {
        this(fenetre, null);
    }
    
    public UtilePanel() {
        boutons= new ArrayList<JButton>();
        champs= new ArrayList<JTextField>();
        comboBoxes = new ArrayList<JComboBox>();
        add(simplePanel);

    }
    //Get-Set
    //toString
    //Autres Méthodes
    
    //Ajoute un comboBox contenant une liste de String 
    public void addComboBox() {
        JComboBox<String> comboBox = new JComboBox<String>();
        simplePanel.add(comboBox);
        comboBox.addActionListener(this);
        comboBoxes.add(comboBox);
    }
    
    public void addBouton(String label) {
        JButton bouton = new JButton(label);
        simplePanel.add(bouton);
        bouton.addActionListener(this);
        boutons.add(bouton);
    }
    
    public JTextField getChamp(int index) {
        return champs.get(index);
    }
    
    public JButton getBouton(int index) {
        return boutons.get(index);
    }
    
    public JButton getLastBouton() {
        return getBouton(boutons.size() - 1);
    }
    
    public JTextField getLastChamp() {
        return getChamp(champs.size() - 1);
    }
    
    public void addTextField() {
        JTextField champ = new JTextField(10);
        simplePanel.add(champ);
        champs.add(champ);
    }
    
    public void addChamp(String label) {
        simplePanel.add(new JLabel(label));
        JTextField champ = new JTextField(10);
        simplePanel.add(champ);
        champs.add(champ);
    }
    
    public JLabel addLabel(String texte) {
        JLabel label = new JLabel(texte);
        simplePanel.add(label);
        return label;
    }
    
    public void addEspace() {
        simplePanel.add(new JLabel(""));
    }
    
    public void setChamp(int index, String texte) {
        champs.get(index).setText(texte);
    }
    
    @Override
    public void actionPerformed(ActionEvent event) {  // Methode recoit evenemen
        if (event.getSource().getClass().toString().equals("JButton")) {
            if (((JButton) event.getSource()).getText() == "HelloWorld") {
                System.out.println("HelloWorld");
            }
        }
    }
    
    //Méthodes qui permet d'ajouter les groupes à la liste des JComboBox
    public void remplir(JComboBox combo, ArrayList liste) {
        combo.removeAllItems();
        for (int i = 0; i < liste.size(); i++) {
            combo.addItem("Groupe " + i);
        }
    }
    
    public void refreshComboBoxes(ArrayList liste) {
        if (comboBoxes != null) {
            for (int i = 0; i < comboBoxes.size(); i++) {
                final JComboBox get = comboBoxes.get(i);
                remplir(get,liste);
                get.revalidate();
                get.repaint();
            }
        }
    }

    public void messageErreur(Exception e) throws HeadlessException {
        JOptionPane.showMessageDialog(null, e.toString(), "ERROR", JOptionPane.ERROR_MESSAGE);
    }
}