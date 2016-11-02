/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */

import java.util.ArrayList;
import org.junit.After;
import org.junit.AfterClass;
import org.junit.Before;
import org.junit.BeforeClass;
import org.junit.Test;
import static org.junit.Assert.*;

/**
 * Créé fièrement 2016-10-23, 11:51:23
 * @author Raphael Duchaine
 */
public class EleveTest {

    public EleveTest() {
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
     * Test of getNom method, of class Eleve.
     */
    @Test
    public void testGetNom() {
        System.out.println("getNom");
        Eleve instance = new Eleve();
        String expResult = "";
        String result = instance.getNom();
        assertEquals(expResult, result);
        // TODO review the generated test code and remove the default call to fail.
        fail("getNom ne retourne pas le bon nom");
    }

    /**
     * Test of setNom method, of class Eleve.
     */
    @Test
    public void testSetNom() {
        System.out.println("setNom");
        String nom = "";
        Eleve instance = new Eleve();
        instance.setNom(nom);
        // TODO review the generated test code and remove the default call to fail.
        fail("The test case is a prototype.");
    }

    /**
     * Test of getPrenom method, of class Eleve.
     */
    @Test
    public void testGetPrenom() {
        System.out.println("getPrenom");
        Eleve instance = new Eleve();
        String expResult = "";
        String result = instance.getPrenom();
        assertEquals(expResult, result);
        // TODO review the generated test code and remove the default call to fail.
        fail("The test case is a prototype.");
    }

    /**
     * Test of setPrenom method, of class Eleve.
     */
    @Test
    public void testSetPrenom() {
        System.out.println("setPrenom");
        String prenom = "";
        Eleve instance = new Eleve();
        instance.setPrenom(prenom);
        // TODO review the generated test code and remove the default call to fail.
        fail("The test case is a prototype.");
    }

    /**
     * Test of getDateNaissance method, of class Eleve.
     */
    @Test
    public void testGetDateNaissance() {
        System.out.println("getDateNaissance");
        Eleve instance = new Eleve();
        String expResult = "";
        String result = instance.getDateNaissance();
        assertEquals(expResult, result);
        // TODO review the generated test code and remove the default call to fail.
        fail("The test case is a prototype.");
    }

    /**
     * Test of setDateNaissance method, of class Eleve.
     */
    @Test
    public void testSetDateNaissance() {
        System.out.println("setDateNaissance");
        String dateNaissance = "";
        Eleve instance = new Eleve();
        instance.setDateNaissance(dateNaissance);
        // TODO review the generated test code and remove the default call to fail.
        fail("The test case is a prototype.");
    }

    /**
     * Test of getNbrEvaluation method, of class Eleve.
     */
    @Test
    public void testGetNbrEvaluation() {
        System.out.println("getNbrEvaluation");
        Eleve instance = new Eleve();
        int expResult = 0;
        int result = instance.getNbrEvaluations();
        assertEquals(expResult, result);
        // TODO review the generated test code and remove the default call to fail.
        fail("The test case is a prototype.");
    }

    /**
     * Test of getTabEvaluation method, of class Eleve.
     */
    @Test
    public void testGetTabEvaluation() {
        System.out.println("getTabEvaluation");
        Eleve instance = new Eleve();
        ArrayList<Evaluation> expResult = null;
        ArrayList<Evaluation> result = instance.getTabEvaluation();
        assertEquals(expResult, result);
        // TODO review the generated test code and remove the default call to fail.
        fail("The test case is a prototype.");
    }

    /**
     * Test of setTabEvaluation method, of class Eleve.
     
    @Test
    public void testSetTabEvaluation() {
        System.out.println("setTabEvaluation");
        ArrayList<Evaluation> cours = null;
        Eleve instance = new Eleve();
        instance.setTabEvaluation(cours);
        // TODO review the generated test code and remove the default call to fail.
        fail("The test case is a prototype.");
    }

    /**
     * Test of addEvaluation method, of class Eleve.
     */
    @Test
    public void testAddEvaluation() {
        System.out.println("addEvaluation");
        Evaluation cours = null;
        Eleve instance = new Eleve();
        instance.addEvaluation(cours);
        // TODO review the generated test code and remove the default call to fail.
        fail("The test case is a prototype.");
    }

    /**
     * Test of codePermanent method, of class Eleve.
     */
    @Test
    public void testCodePermanent() {
        System.out.println("codePermanent");
        Eleve instance = new Eleve();
        String expResult = "";
        String result = instance.codePermanent();
        assertEquals(expResult, result);
        // TODO review the generated test code and remove the default call to fail.
        fail("The test case is a prototype.");
    }

    /**
     * Test of calculerNoteFinale method, of class Eleve.
     */
    @Test
    public void testCalculerNoteFinale() {
        System.out.println("calculerNoteFinale");
        Eleve instance = new Eleve();
        double expResult = 0.0;
        double result = instance.calculerNoteFinale();
        assertEquals(expResult, result, 0.0);
        // TODO review the generated test code and remove the default call to fail.
        fail("The test case is a prototype.");
    }

    /**
     * Test of toString method, of class Eleve.
     */
    @Test
    public void testToString() {
        System.out.println("toString");
        Eleve instance = new Eleve();
        String expResult = "";
        String result = instance.toString();
        assertEquals(expResult, result);
        // TODO review the generated test code and remove the default call to fail.
        fail("The test case is a prototype.");
    }

}