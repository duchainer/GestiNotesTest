/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package vue;

import java.awt.BorderLayout;
import javax.swing.JLabel;
import javax.swing.JProgressBar;
import javax.swing.JTabbedPane;
import modele.Etablissement;

/**
 * Crée 2016-11-09,21:03:54
 *
 * @author Raphael Duchaine
 */
class ComplementPanel extends UtilePanel {
//variables

    protected JProgressBar pBar;

    //Méthodes
//Constructeur
    public ComplementPanel() {
        super();
    }

    public ComplementPanel(UtileFrame fenetre) {
        super(fenetre);
    }

    public ComplementPanel(UtileFrame fenetre, JTabbedPane tabbedPane ) {
        super(fenetre,tabbedPane);
    }

    public ComplementPanel( UtileFrame fenetre,JTabbedPane tabbedPane,JProgressBar pBar) {
        super(fenetre, tabbedPane);
        this.pBar = pBar;
    }

    //Get-Set
    //toString
    //Autres Méthodes
    public void retour() {
        fenetre.getContentPane().removeAll();
        fenetre.setSize(800, 750);
        tabbedPane.setSize(800, 750);
        fenetre.add(tabbedPane, BorderLayout.NORTH);
        pBar.setValue(Etablissement.getLastGroupe().getTabEleve().size());
        fenetre.add(new JLabel(pBar.getMinimum() + ""), BorderLayout.WEST);
        fenetre.add(pBar, BorderLayout.CENTER);
        fenetre.add(new JLabel(pBar.getMaximum() + ""), BorderLayout.EAST);
        fenetre.revalidate();
        fenetre.repaint();
    }
}
