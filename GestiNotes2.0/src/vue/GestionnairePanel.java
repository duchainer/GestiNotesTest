package vue;

/**
 * Crée 2016-10-31,20:58:32
 *
 * TODO: -actionPerformed
 *
 * @author Patrick Domingues
 */
import modele.Eleve;
import modele.Etablissement;
import modele.Groupe;
import java.awt.GridLayout;
import java.awt.HeadlessException;
import java.awt.event.*;
import java.util.ArrayList;
import javax.swing.JButton;
import javax.swing.JComboBox;
import javax.swing.JFrame;
import javax.swing.JLabel;
import javax.swing.JOptionPane;
import javax.swing.JProgressBar;
import javax.swing.JTabbedPane;

public class GestionnairePanel extends UtilePanel {

    private JProgressBar pBar;

    //variables

    //Méthodes
    //Constructeur
    public GestionnairePanel(UtileFrame frame, JTabbedPane tabbedPane) {
        super(frame,tabbedPane);

        GridLayout gl = new GridLayout(13, 6, 0, 25);	//Cree GridLayout
        simplePanel.setLayout(gl);
        //Liste des groupe existants
        addLabel("Lister: ");
        addComboBox();
        addLabel("Modifier: ");
        addComboBox();
        addLabel("Statistiques: ");
        addComboBox();

        //Ajoute les divers label pour les divers informations
        addLabel("");
        addLabel("Prenom");
        addLabel("Nom");
        addLabel("Date de naissance");
        addLabel("     Note finale");
        addLabel("Code permanent");
        refreshComboBoxes();

        //Ajoute les divers champs pour les divers informations tout en les protégeant de l'édition
        for (int i = 0; i < Etablissement.ELEVES_PAR_GROUPE; i++) {
            addChamp("Eleve " + i);
            getLastChamp().setEditable(false);
            addProtectedChamp();
            addProtectedChamp();
            addProtectedChamp();
            addProtectedChamp();
        }
        addBouton("Aide");
        boutons.get(0).addActionListener(new ActionListener() {
            @Override
            public void actionPerformed(ActionEvent e) {
                JOptionPane.showMessageDialog(fenetre, "Pour effectuer une operation, lisez les options,"
                        + "\npuis choisissez le groupe que vous voulez consulter ou modifier", "Aide", JOptionPane.INFORMATION_MESSAGE);
            }
        });
        tabComboBox.get(0).addActionListener(new ActionListener() {
            public void actionPerformed(ActionEvent e) {
                if (tabComboBox.get(0).getSelectedItem() != null) {
                    lister();
                }
            }
        });
        tabComboBox.get(1).addActionListener(new ActionListener() {
            public void actionPerformed(ActionEvent e) {
                if (tabComboBox.get(1).getSelectedItem() != null) {
                    modifier();
                }
            }
        });
        tabComboBox.get(2).addActionListener(new ActionListener() {
            public void actionPerformed(ActionEvent e) {
                if (tabComboBox.get(2).getSelectedItem() != null) {
                    statistiques();
                }
            }
        });
    }
    //Autres Méthodes

    GestionnairePanel(UtileFrame frame, JTabbedPane tabbedPane, JProgressBar pBar) {
        this(frame,tabbedPane);
        this.pBar = pBar;
    }

    public void refreshComboBoxes() {
        super.refreshComboBoxes(Etablissement.getTabGroupe());
    }

    private void lister() {
        if (champs != null) {

            String code = (String) tabComboBox.get(0).getSelectedItem();
            int num = Integer.parseInt(code.substring(7));
            Groupe groupe = Etablissement.getTabGroupe().get(num);
            //System.out.println(""+num);
            try {
                for (int i = 0; i < Etablissement.ELEVES_PAR_GROUPE; i++) {
                    setChamp((5 * i), "");
                    setChamp((5 * i) + 1, "");
                    setChamp((5 * i) + 2, "");
                    setChamp((5 * i) + 3, "");
                    setChamp((5 * i) + 4, "");
                }
                for (int i = 0; i < groupe.getTabEleve().size(); i++) {
                    setChamp((5 * i), groupe.getTabEleve().get(i).getNom());
                    setChamp((5 * i) + 1, groupe.getTabEleve().get(i).getPrenom());
                    setChamp((5 * i) + 2, groupe.getTabEleve().get(i).getDateNaissance());
                    setChamp((5 * i) + 3, "" + groupe.getTabEleve().get(i).calculerNoteFinale());
                    setChamp((5 * i) + 4, "" + groupe.getTabEleve().get(i).codePermanent());
                }
            } catch (NullPointerException e) {
                messageErreur(new Exception("Groupe introuvable"));
            }
        }
    }

    private void modifier() {
        String code = (String) tabComboBox.get(1).getSelectedItem();
        int num = Integer.parseInt(code.substring(7));
        Groupe groupe = Etablissement.getTabGroupe().get(num);

        fenetre.getContentPane().removeAll();
        //uneFrame.add(tabbedPane);
        fenetre.getContentPane().add(new GestionnaireModifierPanel(fenetre, groupe, tabbedPane));
        fenetre.revalidate();
        fenetre.repaint();
    }

    private void statistiques() {
        String code = (String) tabComboBox.get(2).getSelectedItem();
        int num = Integer.parseInt(code.substring(7));
        Groupe groupe = Etablissement.getTabGroupe().get(num);

        fenetre.getContentPane().removeAll();
        //uneFrame.add(tabbedPane);
        fenetre.getContentPane().add(new GestionnaireStatistiquesPanel(fenetre, num, groupe, tabbedPane,pBar));
        fenetre.revalidate();
        fenetre.repaint();

    }

    private void addProtectedChamp() {
        addTextField();
        getLastChamp().setEditable(false);
    }

    /*
    //Méthodes qui permet d'ajouter les groupes à la liste des JComboBox
    public void remplir(JComboBox combo, ArrayList list) {
        combo.removeAllItems();
        for (int i = 0; i < list.size(); i++) {
            combo.addItem("Groupe " + i);
        }
    }*/
    @Override
    public void actionPerformed(ActionEvent e) {
        revalidate();
    }
}

//Etablissement.getTabGroupe().get(i)
//combo.getItemAt(i).  //setName("Groupe "+i);
