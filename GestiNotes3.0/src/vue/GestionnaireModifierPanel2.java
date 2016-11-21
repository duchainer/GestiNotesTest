package vue;


/**
 * Crée le
 *
 *  TODO: -actionPerformed
 *
 * @author Patrick Domingues
 */
import modele.Evaluation;
import modele.Groupe;
import modele.Eleve;
import java.awt.GridLayout;
import java.awt.event.ActionEvent;
import java.awt.event.ActionListener;
import java.util.ArrayList;
import javax.swing.JComboBox;
import javax.swing.JFrame;
import javax.swing.JOptionPane;

public class GestionnaireModifierPanel2 extends UtilePanel {

    //variables
    final int NBR_NOTES = 4;
    JFrame uneFrame;
    Groupe g;

    //Méthodes
    //Constructeur
    public GestionnaireModifierPanel2(JFrame frame, Groupe g) {
        super();
        this.uneFrame = frame;
        this.g = g;
        GridLayout gl = new GridLayout(10, 2, 0, 25);	//Cree GridLayout
        simplePanel.setLayout(gl);
        
        addLabel("Choisissez un élève: ");
        addComboBox();
        remplir(tabComboBox.get(0), g);
        
        tabComboBox.get(0).addActionListener(new ActionListener() {
            @Override
            public void actionPerformed(ActionEvent e) {
                modifierEleve();
            }
        });

        addChamp("Note 1: ");
        addChamp("Note 2: ");
        addChamp("Note 3: ");
        addChamp("Note 4: ");

        addBouton("Enregistrer");
        addBouton("Retour");
        boutons.get(0).addActionListener(new ActionListener() {
            @Override
            public void actionPerformed(ActionEvent e) {
                try {
                String code = (String) tabComboBox.get(0).getSelectedItem();
                int num = Integer.parseInt(code.substring(6, 7));
                Eleve eleve = g.getTabEleve().get(num);

                ArrayList<Evaluation> evaluations = eleve.getTabEvaluation();
                for (int i = 0; i < NBR_NOTES; i++) {
                    evaluations.get(i).setNote(Double.parseDouble(getChamp(i).getText()));
                }
                uneFrame.getContentPane().removeAll();
                JOptionPane.showMessageDialog(uneFrame, "Les notes ont ete modifies", "Modification des notes", JOptionPane.INFORMATION_MESSAGE);
                uneFrame.add(new AcceuilPanel((UtileFrame) uneFrame));
                uneFrame.revalidate();
                uneFrame.repaint();
                } catch (NumberFormatException f) {
                    messageErreur(new Exception("Format des notes incorrect"));
                }
            }
        });
        boutons.get(1).addActionListener(new ActionListener() {
            @Override
            public void actionPerformed(ActionEvent e) {
                retour(frame);
            }
        });
    }
    //Autres Méthodes

    /*
    @Override
    public void actionPerformed(ActionEvent event) {  // Methode recoit evenement
        //uneFrame.getContentPane().add(new GestionnairePanel(uneFrame, unTabbedPane));
        if (((JButton) event.getSource()).getText() == "Enregistrer") {
            try {
                String code = (String) tabComboBox.get(0).getSelectedItem();
                int num = Integer.parseInt(code.substring(6, 7));
                Eleve eleve = g.getTabEleve().get(num);

                ArrayList<Evaluation> evaluations = eleve.getTabEvaluation();
                for (int i = 0; i < NBR_NOTES; i++) {
                    evaluations.get(i).setNote(Double.parseDouble(getChamp(i).getText()));
                }
            uneFrame.getContentPane().removeAll();
            JOptionPane.showMessageDialog(uneFrame, "Les notes ont ete modifies", "Modification des notes", JOptionPane.INFORMATION_MESSAGE);
            uneFrame.add(new AcceuilPanel((UtileFrame) uneFrame));
            uneFrame.revalidate();
            uneFrame.repaint();
            } catch (NumberFormatException e) {
                messageErreur(new Exception("Format des notes incorrect"));
            }
        }
    }
    */
    private void modifierEleve() {
        String code = (String) tabComboBox.get(0).getSelectedItem();
        int num = Integer.parseInt(code.substring(6, 7));
        Eleve eleve = g.getTabEleve().get(num);

        try {
            for (int i = 0; i < eleve.getNbrEvaluations(); i++) {
                setChamp(i, String.valueOf(eleve.getTabEvaluation().get(i).getNote()));
            }
        } catch (NullPointerException e) {
            messageErreur(new Exception("Eleve introuvable"));
        }

    }
    public void retour(JFrame frame) {
        frame.getContentPane().removeAll();
        frame.add(new GestionnaireModifierPanel(frame));
        frame.revalidate();
        frame.repaint(); 
    }

    //Méthodes qui permet d'ajouter les groupes à la liste des JComboBox
    public void remplir(JComboBox combo, Groupe g) {
        for (int i = 0; i < g.getTabEleve().size(); i++) {
            combo.addItem("eleve " + i + " : " + g.getTabEleve().get(i).codePermanent() + ", "
                    + g.getTabEleve().get(i).getNom() + ", " + g.getTabEleve().get(i).getPrenom());
        }
    }
}

//Etablissement.getTabGroupe().get(i)
//combo.getItemAt(i).  //setName("Groupe "+i);
/*

    private void modifier() {
        String code = (String) tabComboBox.get(0).getSelectedItem();
        int num = Integer.parseInt(code.substring(7));
        Groupe groupe = Etablissement.getTabGroupe().get(num);

        fenetre.getContentPane().removeAll();
        //uneFrame.add(tabbedPane);
        fenetre.getContentPane().add(new GestionnaireModifierPanel(fenetre));
        fenetre.revalidate();
        fenetre.repaint();
    }
*/
