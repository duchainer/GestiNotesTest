package modele;


import java.util.*;

/**
 * 30/09/2016
 * @author raphael
 */
public class Statistique {
    
    //Méthode pour calculer la moyenne
    public static float calculerMoyenne(Groupe groupe){
        float somme=0;
        for(int count=0;count<groupe.getTabEleve().size();count++){
            final float calculNoteFinale = groupe.getTabEleve().get(count).calculerNoteFinale();
            if(calculNoteFinale!=-1)
                somme+=calculNoteFinale;
        }
        float moyenne =somme/groupe.getTabEleve().size();
        return (float) (Math.round(moyenne*100.0)/100.0);
    }
    //Méthode pour calculer l'écart-type 
    public static float calculerEcartType(Groupe groupe){
        return (float) (Math.round(Math.sqrt(Statistique.calculerVariance(groupe))*100.0)/100.0);
    }
    
    //Méthode calculer variance 
    public static float calculerVariance(Groupe groupe){
        float variance = 0;
        ArrayList<Eleve> tab = groupe.getTabEleve();
        for(int count=0;count<groupe.getTabEleve().size();count++){
            final float calculNoteFinale = tab.get(count).calculerNoteFinale();
            if(calculNoteFinale!=-1)
                variance += Math.pow((calculNoteFinale)-(Statistique.calculerMoyenne(groupe)),2.);
        }
        return (float) (Math.round((variance/ tab.size())*100.0)/100.0);
    }
    
}