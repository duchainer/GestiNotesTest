import java.util.ArrayList;
/**
 *  28/09/2016
 * @author Raphael
 */
public class Groupe {
    //Variables
    private int numero;
    private ArrayList<Eleve> tabEleve = new ArrayList<Eleve>();
    
    //Constructeurs
    public  Groupe(){
        this(-1);
    }
    public Groupe(int numero, Eleve eleve){
        this(numero);
        addEleve(eleve);
    }
    public Groupe(int numero){
        setNumero(numero);
    }
    
//Accesseur-Mutateurs (GET-SET)
    public int getNumero() {
        return numero;
    }
    public void setNumero(int numero) {
        this.numero = numero;
    }
    
    public ArrayList<Eleve> getTabEleve() {
        return tabEleve;
    }
    public void setTabEleve(ArrayList<Eleve> tabEleve) {
        this.tabEleve = tabEleve;
    }
//méthodes autres
    public void addEleve(Eleve eleve){
        if(getTabEleve().size()<Etablissement.ELEVES_PAR_GROUPE){
            getTabEleve().add(eleve);
        }
        else{
            Etablissement.addGroupe();
            Etablissement.addEleve(eleve);
        }
    }
    
    public String listeEleve(){
       String liste = "";
        for(Eleve e:tabEleve){
            liste+=e.toString()+"\n";
        }
        //System.out.println(liste);
        return liste;
        
    }

}
