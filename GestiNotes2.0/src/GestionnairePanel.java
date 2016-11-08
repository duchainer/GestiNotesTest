
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
import javax.swing.JFrame;
import javax.swing.JLabel;
import javax.swing.JOptionPane;
import javax.swing.JTabbedPane;

public class GestionnairePanel extends UtilePanel {

    //variables
    final int NBR_NOTES = 4;
    JLabel notification;
    JComboBox<Groupe> comboBox1;
    JComboBox<Groupe> comboBox2;
    JComboBox<Groupe> comboBox3;
    JFrame uneFrame;
    JTabbedPane unTabbedPane;
    
    //Méthodes
    //Constructeur
    public GestionnairePanel(JFrame frame, JTabbedPane tabbedPane) {
        super();
        this.uneFrame = frame;
        this.unTabbedPane = tabbedPane;
        
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
        
        addLabel("");
        addLabel("Prenom");
        addLabel("Nom");
        addLabel("Date de naissance");
        addLabel("  Note finale");
        addLabel("Code permanent");
        
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
        addBouton("Aide");
        
        
        
    }
    //Autres Méthodes
    
    
    @Override
    public void actionPerformed(ActionEvent event) {  // Methode recoit evenement

        if (((JButton) event.getSource()).getText() == "Aide") {
            JOptionPane.showMessageDialog(uneFrame, "Pour effectuer une operation, lisez les options, \n"
                    + "puis choisissez le groupe que vous voulez consulter ou modifier", "Aide", JOptionPane.INFORMATION_MESSAGE);  			 // Creation objet Frame    
        }        
    }

    private void lister() {
        String code = (String) comboBox1.getSelectedItem();
        int num = Integer.parseInt(code.substring(7));
        Groupe groupe = Etablissement.getTabGroupe().get(num);
        //System.out.println(""+num);
        
        try {           
            for (int i = 0; i < groupe.getTabEleve().size(); i++) {
                setChamp((5*i),groupe.getTabEleve().get(i).getNom());
                setChamp((5*i)+1,groupe.getTabEleve().get(i).getPrenom());
                setChamp((5*i)+2,groupe.getTabEleve().get(i).getDateNaissance());
                setChamp((5*i)+3,""+groupe.getTabEleve().get(i).calculerNoteFinale());
                setChamp((5*i)+4,""+groupe.getTabEleve().get(i).codePermanent());
            }
        } catch (NullPointerException e) {
            JOptionPane.showMessageDialog(null, "Groupe introuvable", "ERROR", JOptionPane.ERROR_MESSAGE);
        }
    }

    private void modifier() {
        String code = (String) comboBox1.getSelectedItem();
        int num = Integer.parseInt(code.substring(7));
        Groupe groupe = Etablissement.getTabGroupe().get(num);
        
        uneFrame.getContentPane().removeAll();
        //uneFrame.add(unTabbedPane);
        uneFrame.getContentPane().add(new GestionnaireModifierPanel(uneFrame, groupe, unTabbedPane));
        uneFrame.revalidate();
        uneFrame.repaint();        
    }

    private void statistiques() {
        String code = (String) comboBox1.getSelectedItem();
        int num = Integer.parseInt(code.substring(7));
        Groupe groupe = Etablissement.getTabGroupe().get(num);
        
        uneFrame.getContentPane().removeAll();
        //uneFrame.add(unTabbedPane);
        uneFrame.getContentPane().add(new GestionnaireStatistiquesPanel(uneFrame, groupe, unTabbedPane));
        uneFrame.revalidate();
        uneFrame.repaint();   
        
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