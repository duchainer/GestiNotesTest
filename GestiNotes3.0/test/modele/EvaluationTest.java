package modele;

import org.junit.After;
import org.junit.AfterClass;
import org.junit.Before;
import org.junit.BeforeClass;
import org.junit.Test;
import static org.junit.Assert.*;

/**
 * Créé fièrement 2016-11-19, 09:47:20
 * @author Raphael Duchaine
 */
public class EvaluationTest {
    Evaluation instance = new Evaluation();

    public EvaluationTest() {
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
        instance = new Evaluation();
    }

    /**
     * Test of getNom & setNom method, of class Evaluation.
     */
    @Test
    public void testGetSetNom() {
        System.out.println("get&SetNom");
        String expResult = "Bonjour";
        instance.setNom(expResult);
        String result = instance.getNom();
        assertEquals(expResult, result);
    }

    /**
     * Test of getProf & setProf method, of class Evaluation.
     */
    @Test
    public void testGetSetProf() {
        System.out.println("get&SetProf");
        Professeur expResult = new Professeur("Dumble Dors Profondement");
        instance.setProf(expResult);
        Professeur result = instance.getProf();
        assertEquals(expResult, result);
    }

    /**
     * Test of get&setValeurEvaluation method, of class Evaluation.
     */
    @Test
    public void testGetSetValeurEvaluation() {
        System.out.println("get&SetValeurEvaluation");
        float expResult = 0.42f;
        instance.setValeurEvaluation(expResult);
        float result = instance.getValeurEvaluation();
        assertEquals(expResult, result, 0.0);
    }

    /**
     * Test of get&SetNote method, of class Evaluation.
     */
    @Test
    public void testGetSetNote() {
        System.out.println("get&SetNote");
        float expResult = 42.42f;
        instance.setNote(expResult);
        float result = instance.getNote();
        assertEquals(expResult, result, 0.0);
    }

}