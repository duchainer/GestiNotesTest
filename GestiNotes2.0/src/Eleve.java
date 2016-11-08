
import java.util.*;

/**
 * 28/09/2016
 *
 * @author Raphael
 */
class Eleve {
//variables

    private String nom, prenom, dateNaissance;
    private ArrayList<Evaluation> evaluations = new ArrayList<Evaluation>();

//Constructeur
    Eleve() {
        this("Etychen", "Paul", "01-04-1999");
    }

    Eleve(String nom, String prenom, String dateNaissance) {
        setNom(nom);
        setPrenom(prenom);
        setDateNaissance(dateNaissance);
        addEvaluation(new Evaluation("Science", .2));
        addEvaluation(new Evaluation("Math", .2));
        addEvaluation(new Evaluation("Français", .3));
        addEvaluation(new Evaluation("Informatique", .3));
    }


    Eleve(String nom, String prenom, String date, boolean initialise) {
        this(nom,prenom,date);
        //Si l'on veut générer une note au hasard
        if (initialise) {
            for (int i = 0; i < evaluations.size(); i++) {
                evaluations.get(i).setNote(Etablissement.randomNote());
            }
        }

    }

//Accesseur-Mutateurs (GET-SET)
    public String getNom() {
        return nom;
    }

    public void setNom(String nom) {
        this.nom = nom;
    }

    public String getPrenom() {
        return prenom;
    }

    public void setPrenom(String prenom) {
        this.prenom = prenom;
    }

    public String getDateNaissance() {
        return dateNaissance;
    }

    public void setDateNaissance(String dateNaissance) {
        this.dateNaissance = dateNaissance;
    }

    public int getNbrEvaluations() {
        return evaluations.size();
    }

    public ArrayList<Evaluation> getTabEvaluation() {
        return evaluations;
    }

    public void addEvaluation(Evaluation cours) {
        this.evaluations.add(cours);
    }

//Autres méthodes
    public String codePermanent() {
        //ex : Duchaine Raphael 29-04-1998  donne  DR1998
        return getNom().substring(0, 1) + getPrenom().substring(0, 1) + getDateNaissance().substring(6, 10);
    }

    public double calculerNoteFinale() {
        double somme = 0;
        for (int i = 0; i < evaluations.size(); i++) {
            Evaluation my_cours = evaluations.get(i);
            if(my_cours.getNote()!=-1.)
                somme += (my_cours.getNote() * my_cours.getValeurEvaluation());
        }
        return somme;
    }

    @Override
    public String toString() {
        return (nom + ", " + prenom + ", " + dateNaissance + ", " + calculerNoteFinale() + "% " + codePermanent());
    }

}
