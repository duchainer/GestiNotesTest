package modele;

import static modele.Etablissement.*;
import org.junit.After;
import org.junit.AfterClass;
import org.junit.Before;
import org.junit.BeforeClass;
import org.junit.Test;
import static org.junit.Assert.*;
import org.junit.Ignore;

/**
 * Créé fièrement 2016-11-20, 16:12:56
 * @author Raphael Duchaine
 */
public class StatistiqueTest {
    Groupe groupe;
    public StatistiqueTest() {
         groupe= new Groupe(0);
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
     * Test of calculerMoyenne method, of class Statistique.
     */
    @Test
    public void testCalculerMoyenneSansNotes() {
        System.out.println("calculerMoyenneSansNotes");
        for (int i = 0; i < noms.length; i++) {
            groupe.addEleve(new Eleve(noms[i], prenoms[i], dates[i]));
//            groupe.getTabEleve().get(i).setNote(0, i);
        }
        float expResult = 0F;
        float result = Statistique.calculerMoyenne(groupe);
        assertEquals(expResult, result, 0.0);
    }
    @Ignore
    public void testCalculerMoyenne2() {
        System.out.println("calculerMoyenne2");
        for (int i = 0; i < noms.length; i++) {
            groupe.addEleve(new Eleve(noms[i], prenoms[i], dates[i]));
            groupe.getTabEleve().get(i).setNote(0, i);
        }
        float expResult = 0F;
        float result = Statistique.calculerMoyenne(groupe);
        assertEquals(expResult, result, 0.0);
    }

    /**
     * Test of calculerEcartType method, of class Statistique.
     */
    @Test
    public void testCalculerEcartTypeSansNotes() {
        System.out.println("calculerEcartTypeSansNotes");
        for (int i = 0; i < noms.length; i++) {
            groupe.addEleve(new Eleve(noms[i], prenoms[i], dates[i]));
//            groupe.getTabEleve().get(i).setNote(0, i);
        }
        float expResult = 0F;
        float result = Statistique.calculerEcartType(groupe);
        assertEquals(expResult, result, 0.0);
    }

    /**
     * Test of calculerVariance method, of class Statistique.
     */
    @Test
    public void testCalculerVariance() {
        System.out.println("calculerVarianceSansNotes");
        for (int i = 0; i < noms.length; i++) {
            groupe.addEleve(new Eleve(noms[i], prenoms[i], dates[i]));
//            groupe.getTabEleve().get(i).setNote(0, i);
        }
        float expResult = 0F;
        float result = Statistique.calculerVariance(groupe);
        assertEquals(expResult, result, 0.0);
    }

}