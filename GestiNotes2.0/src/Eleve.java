
import java.util.*;

/**
 * 28/09/2016
 *
 * @author Raphael
 */
class Eleve {
//variables

    private String nom, prenom, dateNaissance;
    private ArrayList<Evaluation> Evaluations = new ArrayList<Evaluation>();

//Constructeur
    Eleve() {
        this("Etychen", "Paul", "01-04-1999");
    }

    Eleve(String nom, String prenom, String dateNaissance) {
        setNom(nom);
        setPrenom(prenom);
        setDateNaissance(dateNaissance);
    }

    Eleve(String nom, String prenom, String date, Evaluation[] listeEvaluation) {
        this(nom, prenom, date);
        for (int i = 0; i < listeEvaluation.length; i++) {
            Evaluations.add(listeEvaluation[i]);
        }
    }

    Eleve(String nom, String prenom, String date, Evaluation[] listeEvaluation, boolean random) {
        this(nom,prenom,date,listeEvaluation);
        //Si l'on veut générer une note "au hasard"
        if (random) {
            for (int i = 0; i < listeEvaluation.length; i++) {
                Evaluations.get(i).setNote((double)100-getNom().length()*2);
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

    public int getNbrCours() {
        return Etablissement.listeEvaluations.length;
    }

    public ArrayList<Evaluation> getTabCours() {
        return Evaluations;
    }

    public void addCours(Evaluation cours) {
        this.Evaluations.add(cours);
    }

//Autres méthodes
    public String codePermanent() {
        //ex : Duchaine Raphael 29-04-1998  donne  DR1998
        return getNom().substring(0, 1) + getPrenom().substring(0, 1) + getDateNaissance().substring(6, 10);
    }

    public double calculerNoteFinale() {
        double somme = 0;
        for (int i = 0; i < Evaluations.size(); i++) {
            Evaluation my_cours = Evaluations.get(i);
            somme += (my_cours.getNote() * my_cours.getValeurCours());
        }
        return somme;
    }

    @Override
    public String toString() {
        return (nom + ", " + prenom + ", " + dateNaissance + ", " + calculerNoteFinale() + "% " + codePermanent());
    }

}
