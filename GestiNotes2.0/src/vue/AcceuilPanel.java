package vue;


import java.awt.Graphics;
import java.awt.Graphics2D;
import java.awt.Image;
import java.awt.*;

/**
 * Crée 2016-11-07,19:22:32
 *
 * @author Raphael Duchaine
 */
public class AcceuilPanel extends UtilePanel {
    //variables

    //Méthodes
    //Constructeur
    public AcceuilPanel(UtileFrame fenetre){
        super(fenetre);
    }
    public void paint(Graphics g) {
        // Appeler la methode paint de la superclasse.
        super.paint(g);
        // Creer le contexte graphique 2D
        Graphics2D g2d = (Graphics2D) g;
        Image image = getToolkit().getImage("images/bille.png");    // chercher fichier image
        g2d.drawImage(image, 2*570/3-125, 750/4, 570/2, 750/2, this);        // le dessiner avec coordonnees et dimensions
        g2d.setFont( new Font( "Algerian", Font.BOLD, 20 ) );
        g2d.drawString("Bienvenue dans le", 15, 250);
        g2d.drawString("gestionnaire GestiNotes", 15, 270);
        g2d.drawString("version 2.0!", 15, 290);
        
    }
    //Get-Set

    //toString
    //Autres Méthodes
}
