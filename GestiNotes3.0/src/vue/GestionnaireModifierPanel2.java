package vue;


/**
 * Crée le
 *
 *  TODO: -actionPerformed
 *
 * @author Patrick Domingues
 */
import Réutilisable.UtileFrame;
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
import javax.swing.JProgressBar;
import modele.ArgumentInvalideException;

public class GestionnaireModifierPanel2 extends GestiNotesPanel {

    //variables
    final int NBR_NOTES = 4;
    UtileFrame uneFrame;
    Groupe g;
    JProgressBar pBar;

    //Méthodes
    //Constructeur
    public GestionnaireModifierPanel2(UtileFrame frame, Groupe g,JProgressBar p_pBar) {
        super();
        this.uneFrame = frame;
        this.g = g;
        this.pBar = p_pBar;
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
        
        //Appel de la méthode créant les champs pour chaque notes selon NBR_NOTES de Etablissement
        addChampsNote(false);

        addBouton("Enregistrer");
        addBouton("Retour");
        boutons.get(0).addActionListener(new ActionListener() {
            @Override
            public void actionPerformed(ActionEvent e) {
                try {
                String code = (String) tabComboBox.get(0).getSelectedItem();
                int num = Integer.parseInt(code.substring(6, 7));
                    
                Eleve eleve = g.getTabEleve().get(num);
                for (int i = 0; i < NBR_NOTES; i++) {
                    eleve.setNote(i,Float.parseFloat(getChamp(i).getText()));
                }
                JOptionPane.showMessageDialog(uneFrame, "Les notes ont ete modifies", "Modification des notes", JOptionPane.INFORMATION_MESSAGE);
                } catch (NumberFormatException ex) {
                    messageErreur(new Exception("Format des notes incorrect"));
                }
                catch (ArgumentInvalideException ex) {
                    messageErreur(ex);
                }catch (Exception ex) {
                    messageErreur("",ex);
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
    public void retour(UtileFrame frame) {
        frame.getContentPane().removeAll();
        frame.add(new GestionnaireModifierPanel(frame, pBar));
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
