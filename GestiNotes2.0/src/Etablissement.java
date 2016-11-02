
import java.util.*;

/**
 * 28/09/2016
 *
 * @author raphael
 */
public final class Etablissement {

    private static String nomEcole = "Academie de RobberVals";
    static final int ELEVES_PAR_GROUPE = 10;
    private static ArrayList<Groupe> tabGroupe=new ArrayList<Groupe>();
    static String[] noms = {"Attentia", "Bonniveau", "Curviligni", "Donagan", "Erzellman", "Frenchmen", "Karato", "K'amon"};
    static String[] prenoms = {"Genghis", "Conan", "Sarmoulu", "Maurice", "Zarah", "Rolphi", "Ash", "ToutTemps"};
    static String[] dates = {"01-01-0001", "21-05-1982", "30-04-1213", "21-11-1395", "04-02-0999", "31-12-2012", "16-06-0654", "09-07-6000"};

//Constructeur
    public static void Etablissement() {

    }
    
    public static void initialise() {
        addGroupe();
        for (int i = 0; i < noms.length; i++) {
            getLastGroupe().addEleve(new Eleve(noms[i], prenoms[i], dates[i], true));
        }
    }

//Accesseur-Mutateurs(GET-SET)
    public static String getNomEcole() {
        return nomEcole;
    }
    public static void setNomEcole(String aNomEcole) {
        nomEcole = aNomEcole;
    }

    public static ArrayList<Groupe> getTabGroupe() {
        return tabGroupe;
    }
    public static void setTabGroupe(ArrayList<Groupe> aTabGroupe) {
        tabGroupe = aTabGroupe;
    }
    public static Groupe getLastGroupe() {
        return getTabGroupe().get(getTabGroupe().size()-1);
    }

//Autres Méthodes
    public static Groupe addGroupe() {
        int numero;
        if(getTabGroupe()==null){
           numero =0;
        }
        else{
            numero = getTabGroupe().size();
        }
        Groupe groupe = new Groupe(numero);
        getTabGroupe().add(groupe);
        return groupe;
    }
    
    //Ajout un eleve dans le dernier
    public static void addEleve(Eleve eleve){
        Groupe groupe = getLastGroupe();
        groupe.addEleve(eleve);
    }
    //Cherche un eleve dans tous les groupes avec son code permanent
    public static Eleve searchEleve(String code){
        Eleve eleve = null;
        for(Groupe g:getTabGroupe()){
            for(Eleve e:g.getTabEleve()){
                if(e.codePermanent().equals(code))
                    return e;
            }
        }
        return eleve;
    }
    
    public static double randomNote(){
        Random ran = new Random();
        double num= Math.ceil(ran.nextFloat() * 100);
        return num;
    }

}

