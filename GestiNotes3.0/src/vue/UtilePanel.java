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
    ArrayList<JComboBox> tabComboBox;

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
        tabComboBox = new ArrayList<JComboBox>();
        add(simplePanel);
        //simplePanel.setBackground(Color.CYAN);

    }
    //Get-Set
    //toString
    //Autres Méthodes
    
    //Ajoute un comboBox contenant une liste de String 
    public void addComboBox() {
        JComboBox<String> comboBox = new JComboBox<String>();
        simplePanel.add(comboBox);
        comboBox.addActionListener(this);
        tabComboBox.add(comboBox);
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
        addChamp(label,"");
    }
    
    public void addChamp(String label, String tooltipText) {
        final JLabel jLabel = new JLabel(label);
        simplePanel.add(jLabel);
        JTextField champ = new JTextField(10);
        simplePanel.add(champ);
        champs.add(champ);
        jLabel.setToolTipText(tooltipText);
        champ.setToolTipText(tooltipText);
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
    public void setChampEditable(int index, boolean editable) {
        champs.get(index).setEditable(editable);
    }
    
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
    
    

    public void messageErreur(Exception e) throws HeadlessException {
        JOptionPane.showMessageDialog(null, e.getMessage(), "ERROR", JOptionPane.ERROR_MESSAGE);
    }
}