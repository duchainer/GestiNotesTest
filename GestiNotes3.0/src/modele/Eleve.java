package modele;

import java.io.Serializable;
import java.util.*;

/**
 * 28/09/2016 Claase qui definit l'objet Eleve
 *
 * @author Raphael
 */
public class Eleve implements Serializable {
//variables

    private String nom, prenom, dateNaissance; //Informations d'un eleve
    private ArrayList<Evaluation> evaluations = new ArrayList<Evaluation>(); //Tableau contenant les cours d'un eleves

    /**
     * Tableau contenant les noms des cours par default
     */
    public static final String[] nomsEvaluations = {"Science", "Math", "Français", "Informatique"};

    /**
     * Constructeur par defaut, qui cree un Eleve avec "Etychen" comme nom,
     * "Paul" come prenom, et "01-04-1999" comme date de naissance
     */
    Eleve() {
        this("Etychen", "Paul", "01-04-1999");
    }

    /**
     * Constructeur qui cree un Eleve avec les informations fournis en
     * parametres
     *
     * @param nom Le nom de l'eleve cree
     * @param prenom Le prenom de l'eleve cree
     * @param dateNaissance La date de naissance de l'eleve cree
     */
    public Eleve(String nom, String prenom, String dateNaissance) {
        setNom(nom);
        setPrenom(prenom);
        setDateNaissance(dateNaissance);
        addEvaluation(new Evaluation(nomsEvaluations[0], .2f));
        addEvaluation(new Evaluation(nomsEvaluations[1], .2f));
        addEvaluation(new Evaluation(nomsEvaluations[2], .3f));
        addEvaluation(new Evaluation(nomsEvaluations[3], .3f));
    }

    /**
     * Constructeur qui cree un Eleve avec les informations fournis en
     * parametres, qui possede des notes aleatoires
     *
     * @param nom Le nom de l'eleve cree
     * @param prenom Le prenom de l'eleve cree
     * @param dateNaissance La date de naissance de l'eleve cree
     * @param initialise Un boolean qui determine si on ajoute a l'eleve des
     * notes aleatoires ou non
     */
    public Eleve(String nom, String prenom, String dateNaissance, boolean initialise) {
        this(nom, prenom, dateNaissance);
        //Si l'on veut générer une note au hasard
        if (initialise) {
            for (int i = 0; i < evaluations.size(); i++) {
                setNote(i, (float) (Math.round(Etablissement.randomNote() * 100.0) / 100.0));
            }
        }

    }

//Accesseur-Mutateurs (GET-SET)
    /**
     *
     * @return Le nom de l'eleve
     */
    public String getNom() {
        return nom;
    }

    /**
     * Definit le nom d'un eleve
     *
     * @param nom Le nom a mettre
     */
    public void setNom(String nom) {
        if (nom.equals("")) {
            throw new ArgumentInvalideException("Un eleve doit avoir un nom!");
        }
        this.nom = nom;
    }

    /**
     * @return Le prenom de l'eleve
     */
    public String getPrenom() {
        return prenom;
    }

    /**
     * Definit le prenom d'un eleve
     *
     * @param prenom Le prenom a mettre
     */
    public void setPrenom(String prenom) {
        if (prenom.equals("")) {
            throw new ArgumentInvalideException("Un eleve doit avoir un prenom!");
        }
        this.prenom = prenom;
    }

    /**
     * @return La date de naissance de l'eleve
     */
    public String getDateNaissance() {
        return dateNaissance;
    }

    /**
     * Definit la date de naissance d'un eleve
     *
     * @param dateNaissance La date de naissance a mettre
     */
    public void setDateNaissance(String dateNaissance) {
        if (dateNaissance.equals("")) {
            throw new ArgumentInvalideException("Un eleve doit avoir une date de naissance!");
        }
        this.dateNaissance = dateNaissance;
    }

    /**
     * @return Le nombre d'evaluations de l'eleve
     */
    public int getNbrEvaluations() {
        return evaluations.size();
    }

    /**
     * @return Le tableau d'evaluations de l'eleve
     */
    public ArrayList<Evaluation> getTabEvaluation() {
        return evaluations;
    }

    /**
     * @param index la position de l'evaluation dans le tableau
     * @return La note de l'une des evaluations de l'eleve
     */
    public String getNote(int index) {
        return Float.toString(getTabEvaluation().get(index).getNote());
    }

    /**
     * Definit la note d'un eleve dans l'une de ses evaluations
     *
     * @param index La position de l'evaluaiton dans le tableau d'evaluations
     * @param note La note a mettre
     */
    public void setNote(int index, float note) {
        getTabEvaluation().get(index).setNote(note);
    }

    /**
     * Ajoute une Evaluation a un eleve
     *
     * @param cours Le cours a ajouter dans le tableau d'evaluations
     */
    public void addEvaluation(Evaluation cours) {
        this.evaluations.add(cours);
    }

//Autres méthodes
    /**
     * Permet de creer le codePermanent d'un eleve a partir de ses informations
     *
     * @return un String contenant le Code permanent de l'eleve
     */
    public String codePermanent() {
        //ex : Duchaine Raphael 29-04-1998  donne  DR1998
        return getNom().substring(0, 1) + getPrenom().substring(0, 1) + getDateNaissance().substring(6, 10);
    }

    /**
     * Permet de calculer le note finale d'un eleve a partir de ses informations
     *
     * @return en float la valeur de la note finale
     */
    public float calculerNoteFinale() {
        float somme = 0, valeurAccumulée = 0, nbrNotesVides = 0;

        for (int i = 0; i < evaluations.size(); i++) {
            Evaluation my_cours = evaluations.get(i);
            if (my_cours.getNote() != -1.0) {
                valeurAccumulée += my_cours.getValeurEvaluation();
                somme += (my_cours.getNote() * my_cours.getValeurEvaluation());
            } else {
                nbrNotesVides++;
            }
        }
        if (nbrNotesVides == evaluations.size()) {
            return -1f;
        }
        somme /= valeurAccumulée;
        return (float) (Math.round(somme * 100.0) / 100.0);
    }

    @Override
    /**
     * @return en String Les informations de l'eleve
     */
    public String toString() {
        return (nom + ", " + prenom + ", " + dateNaissance + ", " + calculerNoteFinale() + "% " + codePermanent());
    }

}
