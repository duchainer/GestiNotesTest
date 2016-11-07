/**
 * Crée 2016-10-31,09:53:43
 *
 * @author Raphael Duchaine
 */
import javax.swing.*;
//import java.awt.*;
import java.awt.event.*;
import java.util.ArrayList;

public class UtilePanel extends JPanel implements ActionListener {
    //variables
    ArrayList<JButton> boutons;
    ArrayList<JTextField> champs;
    ArrayList<JComboBox> comboBoxes;
    
    JPanel simplePanel;

    //Méthodes
    //Constructeur
    public UtilePanel() {
        simplePanel = new JPanel();
        add(simplePanel);
        boutons = new ArrayList<JButton>();
        champs = new ArrayList<JTextField>();
        //comboBoxes = new ArrayList<JComboBox>();
    }
    //Get-Set
    //toString
    //Autres Méthodes
    
    /*
    public void addComboBox(String label) {
        String comboBox = label;
        JComboBox<String> comboBox = new JComboBox<String>();
        simplePanel.add(comboBox);
        comboBox.addActionListener(this);
        comboBoxes.add(comboBox);
    }*/
    
    public void addBouton(String label) {
        JButton bouton = new JButton(label);
        simplePanel.add(bouton);
        bouton.addActionListener(this);
        boutons.add(bouton);
    }
    public JTextField getChamp(int index) {
        return champs.get(index);
    }
    public JButton getBouton(int index){
        return boutons.get(index);
    }
    public JButton getLastBouton(){
        return getBouton(boutons.size()-1);
    }
    public JTextField getLastChamp() {
        return getChamp(champs.size()-1);
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
        JLabel label =new JLabel(texte);
        simplePanel.add(label);
        return label;
    }
    public void addEspace(){
        simplePanel.add(new JLabel(""));
    }

    @Override
    public void actionPerformed(ActionEvent event) {  // Methode recoit evenemen

        if (((JButton) event.getSource()).getText() == "HelloWorld") {
            System.out.println("HelloWorld");
        }
    }

}
