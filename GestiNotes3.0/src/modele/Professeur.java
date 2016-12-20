package modele;

import java.io.Serializable;

/**
 * 28/09/2016
 *
 * @author raphael
 */
class Professeur implements Serializable {
//Variables

    private String nom; //Le nom du professeur
//Constructeurs

    /**
     * Constructeur par default, qui cree un professeur nommee "McGonagan"
     */
    Professeur() {
        setNom("McGonagan");
    }

    /**
     * Constructeur avec parametre, qui cree une professeur avec le nom fourni
     * en parametre
     *
     * @param nom Le nom du professeur
     */
    Professeur(String nom) {
        setNom(nom);
    }
//Accesseur-Mutateur(GET-SET)

    /**
     * @return le nom du professeur
     */
    public String getNom() {
        return nom;
    }

    /**
     * Methode qui definit le nom du professeur
     *
     * @param nom Le nom a mettre
     */
    public void setNom(String nom) {
        this.nom = nom;
    }
//Autres méthodes

}
