
/**
 * Crée 2016-10-31,20:58:32
 *
 *  TODO: -actionPerformed
 * @author Patrick Domingues
 */
import java.awt.GridLayout;
import java.awt.HeadlessException;
import java.awt.event.ActionEvent;
import java.awt.event.ActionListener;
import java.util.ArrayList;
import javax.swing.JButton;
import javax.swing.JComboBox;
import javax.swing.JLabel;
import javax.swing.JOptionPane;
import javax.swing.JTextArea;
import javax.swing.text.BadLocationException;

public class GestionnairePanel extends UtilePanel {

    //variables
    final int NBR_NOTES = 4;
    JLabel notification;
    JComboBox<Groupe> comboBox1;
    JComboBox<Groupe> comboBox2;
    JComboBox<Groupe> comboBox3;
    JTextArea textArea = new JTextArea(25, 75);
    
    //Méthodes
    //Constructeur
    public GestionnairePanel() {
        super();
        GridLayout gl = new GridLayout(13, 6, 0, 25);	//Cree GridLayout
        simplePanel.setLayout(gl);
        
        addLabel("Lister: ");
        comboBox1 = new JComboBox<Groupe>();
        remplir(comboBox1);
        simplePanel.add(comboBox1);
        comboBox1.addActionListener(new ActionListener()
		{
			public void actionPerformed(ActionEvent e)
			{
				lister();
			}
		});
        
        addLabel("Modifier: ");
        comboBox2 = new JComboBox<Groupe>();
        remplir(comboBox2);
        simplePanel.add(comboBox2);
        comboBox2.addActionListener(new ActionListener()
		{
			public void actionPerformed(ActionEvent e)
			{
				modifier();
			}
		});
        
        addLabel("Statistiques: ");
        comboBox3 = new JComboBox<Groupe>();
        remplir(comboBox3);
        simplePanel.add(comboBox3);
        comboBox3.addActionListener(new ActionListener()
		{
			public void actionPerformed(ActionEvent e)
			{
				statistiques();
			}
		});
        
        add(textArea);
        /*
        for (int i=0; i<Etablissement.ELEVES_PAR_GROUPE; i++){
            
            for (int j=0; j<1; j++){
            }
            addChamp("Eleve "+(i+1)); 
            getLastChamp().setEditable(false);
            addTextField();
            getLastChamp().setEditable(false);
            addTextField();
            getLastChamp().setEditable(false);
            addTextField();
            getLastChamp().setEditable(false);
            addTextField();      
            getLastChamp().setEditable(false);        
        }
        */
        
    }
    //Autres Méthodes
    
    /*
    @Override
    public void actionPerformed(ActionEvent event) {  // Methode recoit evenement
            //(((JComboBox) event.getSource()).getSelectedItem() == "Enregistrer un eleve")
        if (event.getSource() == comboBox1.getSelectedItem()) {
            
            notification.setText("Enregistrement effectue");
            System.out.println("ElevePanel.actionPerformed()");
            revalidate();
        }
        if (event.getSource() == comboBox2.getSelectedItem()) {
            
            System.out.println("ElevePanel.actionPerformed()");
        }
        if (event.getSource() == comboBox3.getSelectedItem()) {
            
            System.out.println("ElevePanel.actionPerformed()");
            revalidate();

        }
    }
    */

    private void lister() {
        String code = (String) comboBox1.getSelectedItem();
        int num = Integer.parseInt(code.substring(7));
        Groupe groupe = Etablissement.getTabGroupe().get(num);
        //System.out.println(""+num);
        textArea.setText("");
        try {           //insert
            for (int i = 0; i < groupe.getTabEleve().size(); i++) {
                String msg = groupe.getTabEleve().get(i).toString()+"\n";
                textArea.insert(msg, 0);
            }
            
        } catch (NullPointerException e) {
            JOptionPane.showMessageDialog(null, "Groupe introuvable", "ERROR", JOptionPane.ERROR_MESSAGE);
        }/*catch (BadLocationException f){
            JOptionPane.showMessageDialog(null, "Aucune ligne", "ERROR", JOptionPane.ERROR_MESSAGE);
        }*/
    }

    private void modifier() {
        Eleve eleve;
        String codePermanent;
        if (getChamp(3).getText().equals("")) {            
            return;
        } else {
            codePermanent = getChamp(3).getText();
        }
        try {
            eleve = Etablissement.searchEleve(codePermanent);
            if (eleve.equals(null)) {
                throw new NullPointerException("Code incorrect");
            }
            eleve.setNom(getChamp(0).getText());
            eleve.setPrenom(getChamp(1).getText());
            eleve.setDateNaissance(getChamp(2).getText());
            ArrayList<Evaluation> evaluations = eleve.getTabEvaluation();
            for (int i = 0; i < NBR_NOTES; i++) {
                evaluations.get(i).setNote(Double.parseDouble(getChamp(i + 4).getText()));
            }
            notification.setText("Modification effectue");
            
        } catch (NullPointerException e) {
            JOptionPane.showMessageDialog(null, "Eleve introuvable", "ERROR", JOptionPane.ERROR_MESSAGE);
        }

    }

    private void statistiques() {
    }
    public static String getNote(Eleve eleve, int index) {
        return eleve.getTabEvaluation().get(index).getNote().toString();
    }
    
    //Méthodes qui permet d'ajouter les groupes à la liste des JComboBox
    public void remplir(JComboBox combo){
        for(int i=0; i<Etablissement.getTabGroupe().size(); i++){
            combo.addItem("Groupe "+i);                        
        }
    }
    
    public void setChamp(int index, String texte) {
        champs.get(index).setText(texte);
    }
}


//Etablissement.getTabGroupe().get(i)
//combo.getItemAt(i).  //setName("Groupe "+i);