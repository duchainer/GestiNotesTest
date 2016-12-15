/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package vue;

import javax.swing.JComboBox;
import modele.Eleve;
import modele.Etablissement;

/**
 * Crée 2016-12-15,16:40:02
 *
 * @author Raphael Duchaine
 */
public class GestiNotesPanel extends UtilePanel {
    //variables
    //Voir celles de UtilePanel

    //Méthodes
    //Constructeurs
    public GestiNotesPanel() {
        super();
    }

    public GestiNotesPanel(UtileFrame fenetre) {
        super(fenetre);
    }

    //Autres Méthodes
    public void remplirCombo1() {
        //Méthodes qui permet d'ajouter les groupes à la liste des JComboBox
        JComboBox<String> combo = tabComboBox.get(0);
        combo.removeAllItems();
        for (int i = 0; i < Etablissement.getTabGroupe().size(); i++) {
            combo.addItem("Groupe " + i);
        }
    }

    public void addChampsNote() {
        for (int i = 0; i < Etablissement.NBR_NOTES; i++) {
            addChamp("Note" + (i + 1), Eleve.nomsEvaluations[i]);
            addEspace();
        }
    }

}
