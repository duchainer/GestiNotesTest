package vue;

import java.awt.*;
import java.awt.event.*;
import javax.swing.*;
import java.lang.*;


public class AccueilPanel2 extends JFrame {

    public static void main(String[] args) {
    	new AccueilPanel2();
    }

    // Controleur
    public AccueilPanel2 () {
    	boolean t=true;

        // Initialisation de la fenêtre...
		setTitle("Animation");
	    setSize(800,750);
	    setLocationRelativeTo(null);
	    setDefaultCloseOperation(JFrame.EXIT_ON_CLOSE);

	    //on place le panneau dans la fenêtre
	    AnimPanel  panel=new AnimPanel();
            this.add( panel  );     // ajout de panneau contenant boule
            setVisible(true);

		// Une fois que notre fenetre est visible, l'animation dans panneau peut commencer
        while(t==true){

        for(int i=0 ; i<25 ; i++)
            panel.deplacer(0, 15);
        for(int i=0 ; i<25 ; i++)
            panel.deplacer(20, 0);
        for(int i=0 ; i<25 ; i++)
            panel.deplacer(0, -15);
        for(int i=0 ; i<25 ; i++)
            panel.deplacer(-20, 0);
		}
    }
}

// classe pour un panel contenant une boule animée
class AnimPanel extends JPanel {
    // Attributs : coordonnées de la boule
    int x=0;
    int y=0;

    // Dessine boule
    public void paintComponent(Graphics g) {
        // Appeler la methode paint de la superclasse.
        super.paintComponent(g);
        // Creer le contexte graphique 2D
        Graphics2D g2d = (Graphics2D) g;
        Image image = getToolkit().getImage("images/bille.png");    // chercher fichier image
        g2d.drawImage(image, x, y, 285, 285, this); 
        g2d.drawImage(image, 500-x, 375-y, 285, 285, this); 
        g2d.drawImage(image, x, 375-y, 285, 285, this); 
        g2d.drawImage(image, 500-x, y, 285, 285, this); 
        g2d.setFont( new Font( "Algerian", Font.BOLD, 17 ) );
        g2d.drawString("Bienvenue dans le", 295, 318);
        g2d.drawString("gestionnaire GestiNotes", 295, 338);
        g2d.drawString("version 2.0!", 295, 358);
        
        this.setForeground(new Color(Math.abs((x/2)-(2*y/3)),(x/2),Math.abs((x/2)-(2*y/3)+5)));
    }

    // Déplacement boule selon les valeurs des paramètres x et y
    public void deplacer(int x, int y) {
        this.x += x;
        this.y += y;
        try {
            Thread.sleep(50); //Ici, une pause d'une seconde
        }catch(InterruptedException e) {
            e.printStackTrace();
        }
        // L'objet  se redessine (actualiser)
        repaint();
    }
}

/* A faire  : Modifier le code pour:
- Ralentir/accélérer la balle
- Changer la trajectoire de la balle horizontale/verticale
- Ajouter un bouton qui termine l’application dans cet exemple d’animation, y a-t-il un blocage? Pourquoi?
*/