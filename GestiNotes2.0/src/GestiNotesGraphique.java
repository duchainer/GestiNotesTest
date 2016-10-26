
import java.io.File;
import javax.swing.JFileChooser;
import javax.swing.UIManager;

/*
* @author Patrick Domingues
*/
//Classe de démarrage
public class GestiNotesGraphique {

    public static void main(String[] args) {
        Etablissement.initialise();
        PrincipaleFrame frame = new PrincipaleFrame(); 			 // Creation objet Frame
        frame.setVisible(true);                					 // Visibilite
        JFileChooser choixDeFichier;
                    choixDeFichier = new JFileChooser();
                    choixDeFichier.setFileSelectionMode(JFileChooser.FILES_ONLY);
                    int resultat = choixDeFichier.showOpenDialog(null);
                    if(resultat != JFileChooser.CANCEL_OPTION){
                        File nomDeFichier = choixDeFichier.getSelectedFile();
                        System.out.println("Nom de fichier: "+ nomDeFichier.getName());
                    }
    }

    

}
