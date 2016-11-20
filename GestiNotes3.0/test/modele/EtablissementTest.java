/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */

package modele;

import java.util.ArrayList;
import org.junit.After;
import org.junit.AfterClass;
import org.junit.Before;
import org.junit.BeforeClass;
import org.junit.Test;
import static org.junit.Assert.*;
import org.junit.Ignore;

/**
 * Créé fièrement 2016-11-19, 20:27:09
 * @author Raphael Duchaine
 */
public class EtablissementTest {

    public EtablissementTest() {
       Etablissement.initialise();
    }

    @BeforeClass
    public static void setUpClass() {
        
    }

    @AfterClass
    public static void tearDownClass() {
    }

    @Before
    public void setUp() {
    }

    @After
    public void tearDown() {
    }

    /**
     * Test of initialise method, of class Etablissement.
     */
    @Test
    public void testInitialise() {
        System.out.println("initialise");
        Etablissement.setTabGroupe(new ArrayList<Groupe>());
        Etablissement.initialise();
        //Un seul groupe
        for(Groupe g:Etablissement.getTabGroupe()){
            System.out.println(g.getNumero());
        }
        assertEquals(1,Etablissement.getTabGroupe().size());
        
        // Huit eleves
        assertEquals(8,Etablissement.getLastGroupe().getTabEleve().size());
        
    }

    /**
     * Test of get&setNomEcole method, of class Etablissement.
     */
    @Test
    public void testGetSetNomEcole() {
        System.out.println("setNomEcole");
        String aNomEcole = "College de Rosemontagne";
        Etablissement.setNomEcole(aNomEcole);
        String result = Etablissement.getNomEcole();
        assertEquals(aNomEcole, result);
    }
    
    /**
     * Test of getLastGroupe method, of class Etablissement.
     */
    @Test
    public void testGetLastGroupe() {
        System.out.println("getLastGroupe");
        Groupe expResult = Etablissement.getTabGroupe().get(Etablissement.getTabGroupe().size()-1);
        Groupe result = Etablissement.getLastGroupe();
        assertEquals(expResult, result);
    }

    /**
     * Test of addGroupe method, of class Etablissement.
     */
    @Test
    public void testAddGroupe() {
        System.out.println("addGroupe");
        int expResult = Etablissement.getTabGroupe().size()+1;
        Etablissement.addGroupe();
        int result = Etablissement.getTabGroupe().size();
        assertEquals(expResult, result);
    }

    /**
     * Test of addEleve method, of class Etablissement.
     */
    @Test
    public void testAddEleve() {
        System.out.println("addEleve");
        int expNbr = Etablissement.getLastGroupe().getTabEleve().size()+1;
        Eleve expResult = new Eleve("Le Barbare","Conan","01-02-0900");
        Etablissement.addEleve(expResult);
        final ArrayList<Eleve> tabEleve = Etablissement.getLastGroupe().getTabEleve();
        int nbr = tabEleve.size();
        Eleve result = tabEleve.get(tabEleve.size()-1);
        assertEquals(expNbr, nbr);
        assertEquals(expResult, result);
    }

    /**
     * Test of searchEleve method, of class Etablissement.
     */
    @Test
    public void testSearchEleve() {
        System.out.println("searchEleve");
        Eleve expResult = Etablissement.getLastGroupe().getTabEleve().get(3);
        String code = expResult.codePermanent();
        Eleve result = Etablissement.searchEleve(code);
        assertEquals(expResult, result);
    }

    /**
     * Test of randomNote method, of class Etablissement.
     */
    @Ignore
    public void testRandomNote() {
        System.out.println("randomNote");
        float expResult = 0.0F;
        float result = Etablissement.randomNote();
        assertEquals(expResult, result, 0.0);
        // TODO review the generated test code and remove the default call to fail.
        fail("The test case is a prototype.");
    }

}