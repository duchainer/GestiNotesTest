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
    private static Eleve instance = new Eleve();

    public EleveTest() {
    }

    @BeforeClass
    public static void setUpClass() {
    }

    @AfterClass
    public static void tearDownClass() {
        //Pour le garbage collector
        instance =null;
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
    @Test(expected = ArgumentInvalideException.class)
    public void testSetNomVide() {
        System.out.println("setNom");
        System.out.println("L\'argument est invalide");
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
    @Test(expected = ArgumentInvalideException.class)
    public void testSetPrenomVide() {
        System.out.println("setPrenom");
        String prenom = "";
        instance.setPrenom(prenom);
    }
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
    @Test(expected = ArgumentInvalideException.class)
    public void testSetDateNaissanceVide() {
        System.out.println("setDateNaissance");
        String dateNaissance = "";
        instance.setDateNaissance(dateNaissance);
    }
    @Test
    public void testSetDateNaissance() {
        System.out.println("setDateNaissance");
        String dateNaissance = "29-04-1998";
        instance.setDateNaissance(dateNaissance);
        String result = instance.getDateNaissance();
        assertEquals(dateNaissance, result);
    }
    /**
     * Test of get&setNote method, of class Eleve.
     */
    @Test
    public void testGetSetNote() {
        System.out.println("setNote");
        int index = 0;
        float note = 50.42f;
        instance.setNote(index,note);
        float result = Float.parseFloat(instance.getNote(index));
        assertEquals(note, result,0.0);
    }

    /**
     * Test of addEvaluation method, of class Eleve.
     */
    @Test
    public void testAddEvaluation() {
        System.out.println("addEvaluation");
        Evaluation cours = new Evaluation("ScienceFiction", .42f);
        instance.addEvaluation(cours);
        final ArrayList<Evaluation> tabEvaluation = instance.getTabEvaluation();
        Evaluation result = tabEvaluation.get(tabEvaluation.size()-1);
        assertEquals(cours, result);
    }

    /**
     * Test of codePermanent method, of class Eleve.
     */
    @Test
    public void testCodePermanent() {
        System.out.println("codePermanent");
        String expResult = "EP1999";
        String result = instance.codePermanent();
        assertEquals(expResult, result);
    }

    /**
     * Test of calculerNoteFinale method, of class Eleve.
     */
    @Test
    public void testCalculerNoteFinaleZero() {
        System.out.println("calculerNoteFinale");
        double expResult = 0.0;
        double result = instance.calculerNoteFinale();
        assertEquals(expResult, result, 0.0);
    }
    /**
     * Test of calculerNoteFinale method, of class Eleve.
     */
    @Test
    public void testCalculerNoteFinale() {
        System.out.println("calculerNoteFinale");
        double expResult = 100.0;
        instance.setNote(0, 100f);
        double result = instance.calculerNoteFinale();
        assertEquals(expResult, result, 0.0);
    }

    /**
     * Test of toString method, of class Eleve.
     */
    @Test
    public void testToString() {
        System.out.println("toString");
        String expResult= "Etychen, Paul, 01-04-1999, 0.0% EP1999";
        String result = instance.toString();
        assertEquals(result,expResult);
    }

}