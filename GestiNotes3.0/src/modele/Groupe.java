package modele;

import java.io.Serializable;
import java.util.ArrayList;
import java.util.Collections;
import java.util.Comparator;

/**
 * 28/09/2016
 *
 * @author Raphael
 */
public class Groupe implements Serializable{

    //Variables
    private int numero; //le numero du groupe
    private ArrayList<Eleve> tabEleve = new ArrayList<Eleve>(); //les eleves dans le groupe

    //Constructeurs

    /**
     * Constructeur sans parametres, qui cree un Groupe avec le numero "-1"
     */
    public Groupe() {
        this(-1);
    }

    /**
     * Constructeur avec 2 parametres, qui cree un Groupe avec un numero et un eleve fourni en parametres
     * @param numero le numero du groupe
     * @param eleve l'eleve a mettre dans le groupe
     */
    public Groupe(int numero, Eleve eleve) {
        this(numero);
        addEleve(eleve);
    }

    /**
     * Constructeur avec 1 parametre, qui cree un Groupe avec un numero
     * @param numero le numero du groupe
     */
    public Groupe(int numero) {
        setNumero(numero);
    }

//Accesseur-Mutateurs (GET-SET)

    /**
     * @return le numero du groupe
     */
    public int getNumero() {
        return numero;
    }

    /**
     * Definit le numero du groupe
     * @param numero le numero du groupe
     */
    public void setNumero(int numero) {
        this.numero = numero;
    }

    /**
     * @return Un ArrayList contenant tous les eleves dans le groupe
     */
    public ArrayList<Eleve> getTabEleve() {
        return tabEleve;
    }

    /**
     * Definit les eleves dans un groupe
     * @param tabEleve le ArrayList a mettre
     */
    public void setTabEleve(ArrayList<Eleve> tabEleve) {
        this.tabEleve = tabEleve;
    }
//méthodes autres

    /**
     * Methode qui ajoute un eleve au groupe
     * @param eleve l'eleve a ajouter
     */
    public void addEleve(Eleve eleve) {
        if (getTabEleve().size() < Etablissement.ELEVES_PAR_GROUPE) {
            getTabEleve().add(eleve);
        } else {
            Etablissement.addGroupe();
            Etablissement.addEleve(eleve);
        }
        trier();
    }

    /**
     * Methode qui retourne toutes les informations des eleves du groupe
     * @return en String les informations des eleves du groupe
     */
    public String listeEleve() {
        String liste = "";
        for (Eleve e : tabEleve) {
            liste += e.toString() + "\n";
        }
        return liste;

    }

    /**
     * Methode qui trie les eleves par ordre alphabetique de noms
     */
    public void trier() {
        Collections.sort(getTabEleve(), new CompareNoms());
    }
}
/**
 * Classe contenant le comparateur de nom
 * @author client Patrick
 */
class CompareNoms implements Comparator<Eleve> {

    @Override
    public int compare(Eleve e1, Eleve e2) {
        return e1.getNom().compareToIgnoreCase(e2.getNom());
    }
}
