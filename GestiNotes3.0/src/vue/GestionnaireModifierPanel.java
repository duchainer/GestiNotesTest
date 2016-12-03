package vue;


/**
 * Crée 2016-11-04,16:05
 *
 *  TODO: -actionPerformed
 *
 * @author Patrick Domingues
 */
import java.awt.GridLayout;
import java.awt.event.ActionEvent;
import java.awt.event.ActionListener;
import javax.swing.JFrame;
import modele.Etablissement;
import modele.Groupe;

public class GestionnaireModifierPanel extends UtilePanel {

    //variables
    final int NBR_NOTES = 4;
    JFrame uneFrame;

    //Méthodes
    //Constructeur
    public GestionnaireModifierPanel(UtileFrame frame) {
        super();
        this.uneFrame = frame;
        GridLayout gl = new GridLayout(10, 2, 0, 25);	//Cree GridLayout
        simplePanel.setLayout(gl);
        
        addLabel("Choisissez un élève: ");
        addComboBox();
        
        tabComboBox.get(0).addActionListener(new ActionListener() {
            @Override
            public void actionPerformed(ActionEvent e) {
                
                String code = (String) tabComboBox.get(0).getSelectedItem();
                int num = Integer.parseInt(code.substring(7));
                Groupe groupe = Etablissement.getTabGroupe().get(num);
                
                frame.getContentPane().removeAll();
                frame.add(new GestionnaireModifierPanel2(frame, groupe));
                frame.revalidate();
                frame.repaint(); 
            }
        });

    }
}

//Etablissement.getTabGroupe().get(i)
//combo.getItemAt(i).  //setName("Groupe "+i);
