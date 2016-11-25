package modele;

import java.io.Serializable;

/**
 * 28/09/2016
 * @author raphael
 */
class Professeur implements Serializable{
//Variables
    private String nom;
//Constructeurs
    Professeur() {
        setNom("McGonagan");
    }
    Professeur(String nom) {
        setNom(nom);
    }
//Accesseur-Mutateur(GET-SET)
    public String getNom() {
        return nom;
    }
    public void setNom(String nom) {
        this.nom = nom;
    }
//Autres méthodes
    
}
