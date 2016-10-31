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
    
    JPanel simplePanel;

    //Méthodes
    //Constructeur
    public UtilePanel() {
        simplePanel = new JPanel();
        add(simplePanel);
        boutons = new ArrayList<JButton>();
        champs = new ArrayList<JTextField>();
    }
    //Get-Set
    //toString
    //Autres Méthodes
    public void addButton(String label) {
        JButton bouton = new JButton(label);
        simplePanel.add(bouton);
        bouton.addActionListener(this);
        boutons.add(bouton);
    }

    public JTextField addChamp(String label) {
        simplePanel.add(new JLabel(label));
        JTextField champ = new JTextField(10);
        simplePanel.add(champ);
        return champ;
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
