/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */

/**
 * 28/09/2016
 * @author raphael
 */
class Professeur {
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
