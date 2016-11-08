
/**
 * Crée 2016-10-31,20:58:32
 *
 *  TODO: -actionPerformed
 *
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
        //Liste des groupe existants
        addLabel("Lister: ");
        comboBox1=addComboBox();
        comboBox1.addActionListener(new ActionListener() {
            public void actionPerformed(ActionEvent e) {
                lister();
            }
        });
         //Liste des groupe existants
        addLabel("Modifier: ");
        comboBox2=addComboBox();
        comboBox2.addActionListener(new ActionListener() {
            public void actionPerformed(ActionEvent e) {
                modifier();
            }
        });
         //Liste des groupe existants
        addLabel("Statistiques: ");
        comboBox3=addComboBox();
        comboBox3.addActionListener(new ActionListener() {
            public void actionPerformed(ActionEvent e) {
                statistiques();
            }
        });
        //Ajoute les divers label pour les divers informations
        addLabel("");
        addLabel("Prenom");
        addLabel("Nom");
        addLabel("Date de naissance");
        addLabel("     Note finale");
        addLabel("Code permanent");
        
        //Ajoute les divers champs pour les divers informations tout en les protégeant de l'édition
        for (int i = 0; i < Etablissement.ELEVES_PAR_GROUPE; i++) {
            addChamp("Eleve " + (i + 1));
            getLastChamp().setEditable(false);
            addProtectedChamp();
            addProtectedChamp();
            addProtectedChamp();
            addProtectedChamp();
        }
        addBouton("Aide");

    }
    //Autres Méthodes

    @Override
    public void actionPerformed(ActionEvent event) {  // Methode recoit evenement
        if (((JButton) event.getSource()).getText() == "Aide") {
            JOptionPane.showMessageDialog(uneFrame, "Pour effectuer une operation, lisez les options,"
                    + "\npuis choisissez le groupe que vous voulez consulter ou modifier", "Aide", JOptionPane.INFORMATION_MESSAGE);
        }
    }

    private void lister() {
        String code = (String) comboBox1.getSelectedItem();
        int num = Integer.parseInt(code.substring(7));
        Groupe groupe = Etablissement.getTabGroupe().get(num);
        //System.out.println(""+num);
        try {
            for (int i = 0; i < groupe.getTabEleve().size(); i++) {
                setChamp((5 * i), groupe.getTabEleve().get(i).getNom());
                setChamp((5 * i) + 1, groupe.getTabEleve().get(i).getPrenom());
                setChamp((5 * i) + 2, groupe.getTabEleve().get(i).getDateNaissance());
                setChamp((5 * i) + 3, "" + groupe.getTabEleve().get(i).calculerNoteFinale());
                setChamp((5 * i) + 4, "" + groupe.getTabEleve().get(i).codePermanent());
            }
        } catch (NullPointerException e) {
            JOptionPane.showMessageDialog(null, "Groupe introuvable", "ERROR", JOptionPane.ERROR_MESSAGE);
        }
    }

    private void modifier() {
        String code = (String) comboBox2.getSelectedItem();
        int num = Integer.parseInt(code.substring(7));
        Groupe groupe = Etablissement.getTabGroupe().get(num);

        uneFrame.getContentPane().removeAll();
        //uneFrame.add(unTabbedPane);
        uneFrame.getContentPane().add(new GestionnaireModifierPanel(uneFrame, groupe, unTabbedPane));
        uneFrame.revalidate();
        uneFrame.repaint();
    }

    private void statistiques() {
        String code = (String) comboBox3.getSelectedItem();
        int num = Integer.parseInt(code.substring(7));
        Groupe groupe = Etablissement.getTabGroupe().get(num);

        uneFrame.getContentPane().removeAll();
        //uneFrame.add(unTabbedPane);
        uneFrame.getContentPane().add(new GestionnaireStatistiquesPanel(uneFrame, groupe, unTabbedPane));
        uneFrame.revalidate();
        uneFrame.repaint();

    }

    private void addProtectedChamp() {
        addTextField();
        getLastChamp().setEditable(false);
    }

    private JComboBox addComboBox() {
        JComboBox comboBox = new JComboBox<Groupe>();
        remplir(comboBox);
        simplePanel.add(comboBox);
        return comboBox;
    }

    public static String getNote(Eleve eleve, int index) {
        return eleve.getTabEvaluation().get(index).getNote().toString();
    }

    //Méthodes qui permet d'ajouter les groupes à la liste des JComboBox
    public void remplir(JComboBox combo) {
        combo.removeAllItems();
        for (int i = 0; i < Etablissement.getTabGroupe().size(); i++) {
            combo.addItem("Groupe " + i);
        }
    }
}

//Etablissement.getTabGroupe().get(i)
//combo.getItemAt(i).  //setName("Groupe "+i);
