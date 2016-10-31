/**
 * 28/09/2016
 * @author raphael
 */
class Evaluation {
//variables
    private String nom;
    private Professeur prof;
    private Double valeurEvaluation;
    private Double note=-1.;
//Constructeurs
    Evaluation(){
        this("cours",.2);
    }
    Evaluation(String nom,Double valeurEvaluation){
        setNom(nom);
        setProf(new Professeur());
        setValeurEvaluation(valeurEvaluation);
    }
    Evaluation(String nom,Double valeurEvaluation,Double note){
        this(nom, valeurEvaluation);
        setNote(note);
    }
    Evaluation(String nom,String nomProf,Double valeurEvaluation){
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

    public Double getValeurEvaluation() {
        return valeurEvaluation;
    }
    public void setValeurEvaluation(Double valeurEvaluation) {
        this.valeurEvaluation = valeurEvaluation;
    }
    
    public Double getNote() {
        return note;
    }
    public void setNote(Double note) {
        this.note = note;
    }
    
//méthodes autres
    


    
}
