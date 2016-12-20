package modele;

import java.io.Serializable;

/**
 * 28/09/2016
 *
 * @author raphael
 */
public class Evaluation implements Serializable {
//variables

    private String nom; //Le nom de l'evaluation
    private Professeur prof; //Le professeur qui donne l'evaluation 
    private float valeurEvaluation; //La ponderation de l'evaluation
    private float note = -1.f; //La note de l'evaluation
//Constructeurs 

    /**
     * Constructeur par default, qui cree une evaluation nommee "cours", avec
     * une pnderation de 0.2
     */
    Evaluation() {
        this("cours", .2f);
    }

    /**
     * Constructeur avec 2 parametres, qui cree une evaluation avec un nom et
     * une ponderation definit par les parametres
     *
     * @param nom Le nom de l'evaluation
     * @param valeurEvaluation la ponderation de l'evaluation
     */
    Evaluation(String nom, float valeurEvaluation) {
        setNom(nom);
        setProf(new Professeur());
        setValeurEvaluation(valeurEvaluation);
    }

    /**
     * Constructeur avec 2 parametres, qui cree une evaluation avec un nom, une
     * ponderation et une note definit par les parametres
     *
     * @param nom Le nom de l'evaluation
     * @param valeurEvaluation la ponderation de l'evaluation
     * @param note La note de l'evaluation
     */
    Evaluation(String nom, float valeurEvaluation, float note) {
        this(nom, valeurEvaluation);
        setNote(note);
    }

    /**
     * Constructeur avec 2 parametres, qui cree une evaluation avec un nom, un
     * professeur et une note definit par les parametres
     *
     * @param nom Le nom de l'evaluation
     * @param nomProf Le nom du professeur
     * @param valeurEvaluation la ponderation de l'evaluation
     */
    Evaluation(String nom, String nomProf, float valeurEvaluation) {
        this(nom, valeurEvaluation);
        setProf(new Professeur(nomProf));
    }
//Accesseur-Mutateurs (GET-SET)

    /**
     * @return le nom de l'evaluation
     */
    public String getNom() {
        return nom;
    }

    /**
     * Methode qui definit le nom de l'evaluation
     *
     * @param nom Le nom a mettre
     */
    public void setNom(String nom) {
        this.nom = nom;
    }

    /**
     * @return Le professeur
     */
    public Professeur getProf() {
        return prof;
    }

    /**
     * Methode qui definit un professeur
     *
     * @param prof Le prof a mettre
     */
    public void setProf(Professeur prof) {
        this.prof = prof;
    }

    /**
     * @return La ponderation de l'evaluation
     */
    public float getValeurEvaluation() {
        return valeurEvaluation;
    }

    /**
     * Methode qui definit la ponderation de l'evaluation
     *
     * @param valeurEvaluation la ponderation a mettre
     */
    public void setValeurEvaluation(float valeurEvaluation) {
        this.valeurEvaluation = valeurEvaluation;
    }

    /**
     * @return la note de l'evaluation
     */
    public float getNote() {
        return note;
    }

    /**
     * Methode qui definit la note de l'evaluation
     *
     * @param note la note a mettre
     */
    public void setNote(float note) {
        if ((note > 100) || (note < 0)) {
            throw new ArgumentInvalideException("Une note doit être entre 0 et 100!");
        }
        this.note = note;
    }

//méthodes autres
}
