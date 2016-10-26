
import java.io.File;
import javax.swing.JFileChooser;
import javax.swing.UIManager;

/*
* @author Patrick Domingues
*/
//Classe de démarrage
public class GestiNotesGraphique {

    public static void main(String[] args) {
        Etablissement.addGroupe();
        for (int i = 0; i < 8; i++) {
            Etablissement.getLastGroupe().addEleve(
                    new Eleve(Etablissement.noms[i],
                            Etablissement.prenoms[i],
                            Etablissement.dates[i],
                            Etablissement.listeEvaluations, true));
        }
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
