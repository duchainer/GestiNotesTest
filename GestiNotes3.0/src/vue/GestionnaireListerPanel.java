package vue;

/**
 * Crée 2016-10-31,20:58:32
 *
 * TODO: -actionPerformed
 *
 * @author Patrick Domingues
 */
import modele.Etablissement;
import modele.Groupe;
import java.awt.GridLayout;
import java.awt.event.*;
import javax.swing.JComboBox;
import javax.swing.JOptionPane;
import javax.swing.JProgressBar;

public class GestionnaireListerPanel extends UtilePanel {

    //variables

    //Méthodes
    //Constructeur
    public GestionnaireListerPanel(UtileFrame frame) {
        super(frame);

        GridLayout gl = new GridLayout(13, 6, 0, 25);	//Cree GridLayout
        simplePanel.setLayout(gl);
        //Liste des groupe existants
        addEspace();
        addEspace();
        addLabel("Lister: ");
        addComboBox();
        remplirCombo1();
        addEspace();
        addEspace();

        //Ajoute les divers label pour les divers informations
        addLabel("");
        addLabel("Prenom");
        addLabel("Nom");
        addLabel("Date de naissance");
        addLabel("     Note finale");
        addLabel("Code permanent");

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
        
    }
    //Autres Méthodes

    GestionnaireListerPanel(UtileFrame frame, JProgressBar pBar) {
        this(frame);
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

    private void addProtectedChamp() {
        addTextField();
        getLastChamp().setEditable(false);
    }

    @Override
    public void actionPerformed(ActionEvent e) {
        revalidate();
    }
}

//Etablissement.getTabGroupe().get(i)
//combo.getItemAt(i).  //setName("Groupe "+i);
/*
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


    private void modifier() {
        String code = (String) tabComboBox.get(1).getSelectedItem();
        int num = Integer.parseInt(code.substring(7));
        Groupe groupe = Etablissement.getTabGroupe().get(num);

        fenetre.getContentPane().removeAll();
        //uneFrame.add(tabbedPane);
        fenetre.getContentPane().add(new GestionnaireModifierPanel(fenetre));
        fenetre.revalidate();
        fenetre.repaint();
    }

    private void statistiques() {
        String code = (String) tabComboBox.get(2).getSelectedItem();
        int num = Integer.parseInt(code.substring(7));
        Groupe groupe = Etablissement.getTabGroupe().get(num);

        fenetre.getContentPane().removeAll();
        //uneFrame.add(tabbedPane);
        fenetre.getContentPane().add(new GestionnaireStatistiquesPanel(fenetre, num, groupe,pBar));
        fenetre.revalidate();
        fenetre.repaint();

    }
*/