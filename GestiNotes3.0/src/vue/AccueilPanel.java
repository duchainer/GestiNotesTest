package vue;

import Réutilisable.UtileFrame;
import Réutilisable.UtilePanel;
import java.awt.*;
import java.awt.event.*;
import javax.swing.*;
import java.lang.*;
import java.awt.Color;
import java.awt.Font;
import java.awt.Graphics;
import java.awt.Graphics2D;
import java.awt.Image;
import javax.swing.JPanel;

public class AccueilPanel extends UtilePanel implements Runnable {

    /*public static void main(String[] args) {
        UtileFrame f = new UtileFrame("lol",800,750);
    	new AccueilPanel(f);
    }*/
    int x = 0;
    int y = 0;

    boolean v = true;

    // Controleur
    public AccueilPanel(UtileFrame fenetre) {
        super(fenetre);

        Thread t = new Thread(this);
        t.start();
    }

    @Override
    public void paint(Graphics g) {
        // Appeler la methode paint de la superclasse.
        super.paint(g);
        // Creer le contexte graphique 2D
        Graphics2D g2d = (Graphics2D) g;
        Image image = getToolkit().getImage("images/bille.png");    // chercher fichier image
        g2d.drawImage(image, x, y, 285, 285, this);
        g2d.drawImage(image, 500 - x, 375 - y, 285, 285, this);
        g2d.drawImage(image, x, 375 - y, 285, 285, this);
        g2d.drawImage(image, 500 - x, y, 285, 285, this);
        g2d.setFont(new Font("Algerian", Font.BOLD, 17));
        g2d.drawString("Bienvenue dans", 295, 318);
        g2d.drawString("GestiNotes!", 315, 338);

        this.setForeground(new Color(Math.abs((x / 3) - (2 * y / 3)), (x / 3), Math.abs((x / 2) - (2 * y / 3) + 5)));
    }

    // Déplacement boule selon les valeurs des paramètres x et y
    public void deplacer(int x, int y) {
        this.x += x;
        this.y += y;
        try {
            revalidate();
            repaint();
            Thread.sleep(50); //Ici, une petite pause
        } catch (InterruptedException e) {
            e.printStackTrace();
        }
        // L'objet  se redessine (actualiser)
        repaint();
        revalidate();
    }

    @Override
    public void run() {
        while (true) {

            while (v == true) {
                for (int i = 0; i < 25; i++) {
                    deplacer(0, 15);
                }
                for (int i = 0; i < 25; i++) {
                    deplacer(20, 0);
                }
                for (int i = 0; i < 25; i++) {
                    deplacer(0, -15);
                }
                for (int i = 0; i < 25; i++) {
                    deplacer(-20, 0);
                }
                try {
                    Thread.sleep(250);
                } catch (InterruptedException e) {
                    e.printStackTrace();
                }
            }
        }
    }
}
