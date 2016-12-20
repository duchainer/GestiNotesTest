package modele;

import java.util.*;

/**
 * 28/09/2016
 *
 * @author raphael
 */
public final class Etablissement {

    /**
     * Variable qui contient le nombre d'evaluations
     */
    public static int NBR_NOTES = 4;

    private static String nomEcole = "Academie de RobberVals";

    /**
     * Variable finale, qui definit combien d'eleve peuvent etre dans un groupe
     */
    public static final int ELEVES_PAR_GROUPE = 10;
    private static ArrayList<Groupe> tabGroupe = new ArrayList<Groupe>();
    static String[] noms = {"Attentia", "Bonniveau", "Curviligni", "Donagan", "Erzellman", "Frenchmen", "K'amon", "Karato"};
    static String[] prenoms = {"Genghis", "Conan", "Sarmoulu", "Maurice", "Zarah", "Rolphi", "ToutTemps", "Ash"};
    static String[] dates = {"01-01-0001", "21-05-1982", "30-04-1213", "21-11-1395", "04-02-0999", "31-12-2012", "09-07-6000", "16-06-0654"};

    /**
     * Methode qui, utilise au debut du programme, permet de creer un groupe avec 8 eleves, dont les informations sont pre-enregistrés
     */
    public static void initialise() {
        addGroupe();
        for (int i = 0; i < noms.length; i++) {
            getLastGroupe().addEleve(new Eleve(noms[i], prenoms[i], dates[i], true));
        }
    }

//Accesseur-Mutateurs(GET-SET)

    /**
     * @return Le nom de l'etablissement
     */
    public static String getNomEcole() {
        return nomEcole;
    }

    /**
     * Definit le nom de l'etablissement
     * @param aNomEcole Le nom de l'etablissement a enregistre
     */
    public static void setNomEcole(String aNomEcole) {
        nomEcole = aNomEcole;
    }

    /**
     * @return Les groupes enregistres dans l'etablissement
     */
    public static ArrayList<Groupe> getTabGroupe() {
        return tabGroupe;
    }

    /**
     * Attribue un ensemble de groupe a l'etablissement
     * @param aTabGroupe Le ArrayList de groupes a ajouter
     */
    public static void setTabGroupe(ArrayList<Groupe> aTabGroupe) {
        tabGroupe = aTabGroupe;
    }

    /**
     * @return Le dernier groupe enregistrer dans le tableau de groupes 
     */
    public static Groupe getLastGroupe() {

        return getTabGroupe().get(getTabGroupe().size() - 1);
    }

//Autres Méthodes

    /**
     * Methode qui permet de creer un groupe, et de l'ajouter au tableau de groupe. Les groupes crees sont vides
     * @return le groupe ajoute
     */
    public static Groupe addGroupe() {
        int numero;
        if (getTabGroupe() == null) {
            numero = 0;
        } else {
            numero = getTabGroupe().size();
        }
        Groupe groupe = new Groupe(numero);
        getTabGroupe().add(groupe);
        return groupe;
    }

    //

    /**
     * Methode qui permet d'ajouter un eleve dans le dernier groupe
     * @param eleve l'eleve a ajouter
     */
    public static void addEleve(Eleve eleve) {
        Groupe groupe = null;
        try {
            groupe = getLastGroupe();
        } catch (ArrayIndexOutOfBoundsException e) {
            groupe = addGroupe();
        }

        groupe.addEleve(eleve);
    }


    /**
     * Methode qui cherche un eleve dans tous les groupes avec son code permanent, et que le retourne
     * @param code Le code de l'eleve recherché
     * @return L'eleve trouvé, ou "null" si introuvable
     */
    public static Eleve searchEleve(String code) {
        Eleve eleve = null;
        for (Groupe g : getTabGroupe()) {
            for (Eleve e : g.getTabEleve()) {
                if (e.codePermanent().equalsIgnoreCase(code)) {
                    return e;
                }
            }
        }
        return eleve;
    }

    /**
     * Methode qui cherche un eleve dans tous les groupes avec son code permanent, et qui retourne son groupe
     * @param code Le code de l'eleve recherché
     * @return Le groupe de l'eleve trouvé, ou "null" si introuvable
     */
    public static Groupe searchGroupeWEleve(String code) {
        Groupe groupe = null;
        for (Groupe g : getTabGroupe()) {
            for (Eleve e : g.getTabEleve()) {
                if (e.codePermanent().equalsIgnoreCase(code)) {
                    return g;
                }
            }
        }
        return groupe;
    }

    /**
     * Methode qui permet de creer un nombre aleatoire entre 0 et 100
     * @return En float une note aleatoire
     */
    public static float randomNote() {
        Random ran = new Random();
        float num = (float) Math.ceil(ran.nextFloat() * 100);
        return num;
    }

}
