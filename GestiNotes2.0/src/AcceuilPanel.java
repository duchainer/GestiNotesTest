
import java.awt.Graphics;
import java.awt.Graphics2D;
import java.awt.Image;

/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
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
        Image image = getToolkit().getImage("images/feels.png");    // chercher fichier image
        g2d.drawImage(image, -200, 15, 1000, 1000, this);        // le dessiner avec coordonnees et dimensions
    }
    //Get-Set

    //toString
    //Autres Méthodes
}
