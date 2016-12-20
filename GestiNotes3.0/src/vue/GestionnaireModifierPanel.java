package vue;


/**
 * Crée 2016-11-04,16:05
 *
 *  TODO: -actionPerformed
 *
 * @author Patrick Domingues
 */
import Réutilisable.UtileFrame;
import java.awt.GridLayout;
import java.awt.event.ActionEvent;
import java.awt.event.ActionListener;
import javax.swing.JFrame;
import javax.swing.JProgressBar;
import modele.Etablissement;
import modele.Groupe;

public class GestionnaireModifierPanel extends GestiNotesPanel {

    //variables
    final int NBR_NOTES = 4;
    JFrame uneFrame;
    JProgressBar pBar;

    //Méthodes
    //Constructeur
    public GestionnaireModifierPanel(UtileFrame frame,JProgressBar p_pBar) {
        super();
        this.uneFrame = frame;
        this.pBar = p_pBar;
        GridLayout gl = new GridLayout(10, 2, 0, 25);	//Cree GridLayout
        simplePanel.setLayout(gl);
        
        addLabel("Choisissez un groupe: ","Choisir le groupe contenant l'élève");
        addComboBox();
        remplirCombo1();
        tabComboBox.get(0).setToolTipText("Choisir le groupe contenant l'élève");
        
        tabComboBox.get(0).addActionListener(new ActionListener() {
            @Override
            public void actionPerformed(ActionEvent e) {
                
                String code = (String) tabComboBox.get(0).getSelectedItem();
                int num = Integer.parseInt(code.substring(7));
                Groupe groupe = Etablissement.getTabGroupe().get(num);
                
                frame.getContentPane().removeAll();
                frame.add(new GestionnaireModifierPanel2(frame, groupe, pBar));
                frame.revalidate();
                frame.repaint(); 
            }
        });

    }
}

//Etablissement.getTabGroupe().get(i)
//combo.getItemAt(i).  //setName("Groupe "+i);
