package controleur;


import java.awt.Image;
import modele.Etablissement;
import java.io.File;
import javax.swing.JFileChooser;
import javax.swing.UIManager;
import vue.PrincipaleFrame;
import vue.LogoRosemont;

/*
* @author Patrick Domingues
*/
//Classe de démarrage
public class GestiNotesGraphique {
    

    public static void main(String[] args) throws InterruptedException {
        LogoRosemont logoRosemont = new LogoRosemont();
        Thread.sleep(1 * 1000);
        PrincipaleFrame frame = new PrincipaleFrame((Image)LogoRosemont.logo); 			 // Creation objet Frame
        frame.setVisible(true);                					 // Visibilite

    }

    

}
