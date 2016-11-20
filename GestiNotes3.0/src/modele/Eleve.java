package modele;

import java.util.*;

/**
 * 28/09/2016
 *
 * @author Raphael
 */
public class Eleve {
//variables

    private String nom, prenom, dateNaissance;
    private ArrayList<Evaluation> evaluations = new ArrayList<Evaluation>();

//Constructeur
    Eleve() {
        this("Etychen", "Paul", "01-04-1999");
    }

    public Eleve(String nom, String prenom, String dateNaissance) {
        setNom(nom);
        setPrenom(prenom);
        setDateNaissance(dateNaissance);
        addEvaluation(new Evaluation("Science", .2f));
        addEvaluation(new Evaluation("Math", .2f));
        addEvaluation(new Evaluation("Français", .3f));
        addEvaluation(new Evaluation("Informatique", .3f));
    }

    public Eleve(String nom, String prenom, String date, boolean initialise) {
        this(nom, prenom, date);
        //Si l'on veut générer une note au hasard
        if (initialise) {
            for (int i = 0; i < getTabEvaluation().size(); i++) {
                evaluations.get(i).setNote((float) (Math.round(Etablissement.randomNote() * 100.0) / 100.0));
            }
        }

    }

//Accesseur-Mutateurs (GET-SET)
    public String getNom() {
        return nom;
    }

    public void setNom(String nom) {
        if(nom.equals(""))
            throw new ArgumentInvalideException("Un eleve doit avoir un nom!");
        this.nom = nom;
    }

    public String getPrenom() {
        return prenom;
    }

    public void setPrenom(String prenom) {
        if(prenom.equals(""))
            throw new ArgumentInvalideException("Un eleve doit avoir un prenom!");
        this.prenom = prenom;
    }

    public String getDateNaissance() {
        return dateNaissance;
    }

    public void setDateNaissance(String dateNaissance) {
        if(dateNaissance.equals(""))
            throw new ArgumentInvalideException("Un eleve doit avoir une date de naissance!");
        this.dateNaissance = dateNaissance;
    }

    public int getNbrEvaluations() {
        return evaluations.size();
    }

    public ArrayList<Evaluation> getTabEvaluation() {
        return evaluations;
    }

    public String getNote(int index) {
        return Float.toString(getTabEvaluation().get(index).getNote());
    }
    public void setNote(int index,float note) {
        getTabEvaluation().get(index).setNote(note);
    }

    public void addEvaluation(Evaluation cours) {
        this.evaluations.add(cours);
    }

//Autres méthodes
    public String codePermanent() {
        //ex : Duchaine Raphael 29-04-1998  donne  DR1998
        return getNom().substring(0, 1) + getPrenom().substring(0, 1) + getDateNaissance().substring(6, 10);
    }

    public float calculerNoteFinale() {
        float somme = 0, valeurAccumulée = 0;

        for (int i = 0; i < evaluations.size(); i++) {
            Evaluation my_cours = evaluations.get(i);
            if (my_cours.getNote() != -1.0) {
                valeurAccumulée += my_cours.getValeurEvaluation();
                somme += (my_cours.getNote() * my_cours.getValeurEvaluation());
            }
        }
        somme /= valeurAccumulée;
        return (float) (Math.round(somme * 100.0) / 100.0);
    }

    @Override
    public String toString() {
        return (nom + ", " + prenom + ", " + dateNaissance + ", " + calculerNoteFinale() + "% " + codePermanent());
    }

}
