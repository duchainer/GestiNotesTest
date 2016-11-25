package modele;

import java.io.Serializable;

/**
 * 28/09/2016
 * @author raphael
 */
public class Evaluation implements Serializable {
//variables
    private String nom;
    private Professeur prof;
    private float valeurEvaluation;
    private float note=-1.f;
//Constructeurs
    Evaluation(){
        this("cours",.2f);
    }
    Evaluation(String nom,float valeurEvaluation){
        setNom(nom);
        setProf(new Professeur());
        setValeurEvaluation(valeurEvaluation);
    }
    Evaluation(String nom,float valeurEvaluation,float note){
        this(nom, valeurEvaluation);
        setNote(note);
    }
    Evaluation(String nom,String nomProf,float valeurEvaluation){
        this (nom,valeurEvaluation);
        setProf(new Professeur(nomProf));
    }
//Accesseur-Mutateurs (GET-SET)
    public String getNom() {
        return nom;
    }
    public void setNom(String nom) {
        this.nom = nom;
    }
    
    public Professeur getProf() {
        return prof;
    }
    public void setProf(Professeur prof) {
        this.prof = prof;
    }

    public float getValeurEvaluation() {
        return valeurEvaluation;
    }
    public void setValeurEvaluation(float valeurEvaluation) {
        this.valeurEvaluation = valeurEvaluation;
    }
    
    public float getNote() {
        return note;
    }
    public void setNote(float note) {
        this.note = note;
    }
    
//méthodes autres
    


    
}
