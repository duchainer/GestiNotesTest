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
    }

}
