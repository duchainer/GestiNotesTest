package vue;

import Réutilisable.UtileFrame;
import Réutilisable.UtilePanel;
import java.lang.*;
import java.awt.Color;
import java.awt.Font;
import java.awt.Graphics;
import java.awt.Graphics2D;
import java.awt.HeadlessException;
import java.awt.Image;
import java.io.IOException;
import java.net.URL;
import javax.swing.JPanel;
import javax.swing.JProgressBar;
import modele.Etablissement;
import sun.applet.Main;

public class AccueilPanel extends UtilePanel implements Runnable {

    /*public static void main(String[] args) {
        UtileFrame f = new UtileFrame("lol",800,750);
    	new AccueilPanel(f);
    }*/
    int x = 0;
    int y = 0;

    boolean v = true;

    Image image = null;
    JProgressBar pBar;

    // Controleur
    public AccueilPanel(UtileFrame fenetre, JProgressBar p_pBar) {
        super(fenetre);

        try {
            image = getImage(); // chercher fichier image
        } catch (IOException ex) {
            messageErreur("L'image n'existe pas", ex);
        }

        //simplePanel.setLayout(null);
        this.pBar = p_pBar;
        pBar.setStringPainted(true);
        pBar.setString(pBar.getValue() + "/10");
        //pBar.setSize(0, 340);
        //pBar.setBounds(0, 340, pBar.getWidth(), pBar.getHeight());
        addLabel("Nombre d'élèves dans le dernier groupe:");
        simplePanel.add(pBar);
        updatePbar();

        Thread t = new Thread(this);
        t.start();
    }

    @Override
    public void paint(Graphics g) {
        // Appeler la methode paint de la superclasse.
        super.paint(g);
        // Creer le contexte graphique 2D
        Graphics2D g2d = (Graphics2D) g;
        g2d.drawImage(image, x, 30 + y, 275, 275, this);
        g2d.drawImage(image, 500 - x, 375 - y, 275, 275, this);
        g2d.drawImage(image, x, 375 - y, 275, 275, this);
        g2d.drawImage(image, 500 - x, 30 + y, 275, 275, this);
        g2d.setFont(new Font("Algerian", Font.BOLD, 17));
        g2d.drawString("Bienvenue dans", 325, 330);
        g2d.drawString("GestiNotes!", 340, 350);

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
                    deplacer(0, 14);
                }
                for (int i = 0; i < 25; i++) {
                    deplacer(20, 0);
                }
                for (int i = 0; i < 25; i++) {
                    deplacer(0, -14);
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

    private Image getImage() throws IOException {
        // find the file in the file system.. probably not a good idea
        URL url = Main.class.getResource("/bille.png");
//        Image r_image = getToolkit().getImage("images/bille.png");   
        Image r_image = getToolkit().getImage(url);

        return r_image;
    }

    public void updatePbar() throws HeadlessException {
        try {
            pBar.setValue(Etablissement.getLastGroupe().getTabEleve().size());
            pBar.setString(pBar.getValue() + "/10");
        } catch (Exception e) {
            if (!e.getMessage().equals("-1")) {
                messageErreur(e);
            }
        }
    }
}
