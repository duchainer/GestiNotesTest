package modele;

import java.util.ArrayList;
import org.junit.After;
import org.junit.AfterClass;
import org.junit.Before;
import org.junit.BeforeClass;
import org.junit.Test;
import org.junit.*;
import static org.junit.Assert.*;

/**
 * Créé fièrement 2016-11-17, 19:53:06
 * @author Raphael Duchaine
 */
public class EleveTest {
    Eleve instance = new Eleve();

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
        instance = new Eleve();
    }

    /**
     * Test of setNom method, of class Eleve.
     */
    @Test(expected = Exception.class)
    public void testSetNomVide() {
        System.out.println("setNom");
        String nom = "";
        instance.setNom(nom);
    }
    @Test
    public void testSetNom() {
        System.out.println("setNom");
        String nom = "Morane";
        instance.setNom(nom);
        String result = instance.getNom();
        assertEquals(nom, result);
    }

    /**
     * Test of setPrenom method, of class Eleve.
     */
    @Test
    public void testSetPrenom() {
        System.out.println("setPrenom");
        String prenom = "Bob";
        instance.setPrenom(prenom);
        String result = instance.getPrenom();
        assertEquals(prenom, result);
    }

    /**
     * Test of setDateNaissance method, of class Eleve.
     */
    @Test
    public void testSetDateNaissance() {
        System.out.println("setDateNaissance");
        String dateNaissance = "29-04-1998";
        instance.setDateNaissance(dateNaissance);
        String result = instance.getDateNaissance();
        assertEquals(dateNaissance, result);
    }

    /**
     * Test of getNote method, of class Eleve.
     */
    @Test
    public void testGetNote() {
        System.out.println("getNote");
        int index = 0;
        String expResult = "";
        String result = instance.getNote(index);
        assertEquals(expResult, result);
        // TODO review the generated test code and remove the default call to fail.
        fail("The test case is a prototype.");
    }
    
    /**
     * Test of setNote method, of class Eleve.
     */
    @Test
    public void testSetNote() {
        System.out.println("setNote");
        int index = 0;
        float note = 50.42f;
        instance.setNote(index,note);
        String result = instance.getNote(index);
        assertEquals(note, result);
    }

    /**
     * Test of addEvaluation method, of class Eleve.
     */
    @Test
    public void testAddEvaluation() {
        System.out.println("addEvaluation");
        Evaluation cours = null;
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
        String result = instance.toString();
        assertNotNull(result);
    }

}