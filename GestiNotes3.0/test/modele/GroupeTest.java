package modele;

import java.util.ArrayList;
import static modele.Etablissement.noms;
import static modele.Etablissement.prenoms;
import static modele.Etablissement.dates;
import org.junit.After;
import org.junit.AfterClass;
import org.junit.Before;
import org.junit.BeforeClass;
import org.junit.Test;
import static org.junit.Assert.*;
import org.junit.*;

/**
 * Créé fièrement 2016-11-20, 07:30:24
 *
 * @author Raphael Duchaine
 */
public class GroupeTest {

    Groupe instance = new Groupe(0);

    public GroupeTest() {
    }

    @BeforeClass
    public static void setUpClass() {
    }

    @AfterClass
    public static void tearDownClass() {
    }

    @Before
    public void setUp() {
        instance = new Groupe(0);
        for (int i = 0; i < noms.length; i++) {
            instance.addEleve(new Eleve(noms[i], prenoms[i], dates[i], true));
        }
    }

    @After
    public void tearDown() {

    }

    /**
     * Test of setNumero method, of class Groupe.
     */
    @Test
    public void testSetNumero0() {
        System.out.println("setNumero");
        int expResult = 0;
        instance.setNumero(expResult);
        int result = instance.getNumero();
        assertEquals(expResult, result);
    }

    /**
     * Test of get&setTabEleve method, of class Groupe.
     */
    @Test
    public void testGetSetTabEleve() {
        System.out.println("GetSetTabEleve");
        ArrayList<Eleve> expResult = new ArrayList<Eleve>();
        expResult.add(new Eleve());
        instance.setTabEleve(expResult);
        ArrayList<Eleve> result = instance.getTabEleve();
        assertEquals(expResult, result);
    }

    /**
     * Test of addEleve method, of class Groupe.
     */
    @Test
    public void testAddEleve() {
        System.out.println("addEleve");
        instance = new Groupe(0);
        Eleve expResult = new Eleve();
        instance.addEleve(expResult);
        Eleve result = instance.getTabEleve().get(0);
        assertEquals(expResult, result);
    }

    /**
     * Test of listeEleve method, of class Groupe.
     */
    @Test
    public void testListeEleve() {
        System.out.println("listeEleve");
        String expResult = "";
        String result = instance.listeEleve();
        assertNotEquals(expResult, result);
    }

    /**
     * Test of trier method, of class Groupe.
     */
    @Test
    public void testTrier() {
        System.out.println("trier");
        String[] expResult = {"Attentia", "Bonniveau", "Curviligni", "Donagan", "Erzellman", "Frenchmen", "K'amon", "Karato"};
//        Groupe groupeAvant= instance;
        instance.trier();
//        assertNotEquals(groupeAvant, instance);
        String[] result = new String[8];
        for (int i = 0; i < instance.getTabEleve().size(); i++) {
            result[i] = instance.getTabEleve().get(i).getNom();
        }
        assertEquals(expResult, result);
    }

    /**
     * Test of class CompareNoms.
     */
    private final CompareNoms comparator = new CompareNoms();

    @Test
    public void greaterTest() {
        Eleve o1 = new Eleve();
        o1.setNom("BBBBBB");
        Eleve o2 = new Eleve();
        o2.setNom("AAAAAA");
        int c = comparator.compare(o1, o2);
        // assert that c is > 0
        assertTrue(c > 0);
    }

    @Test
    public void lowerTest() {
        Eleve o1 = new Eleve();
        o1.setNom("AAAAAA");
        Eleve o2 = new Eleve();
        o2.setNom("BBBBBB");
        int c = comparator.compare(o1, o2);
        // assert that c is < 0
        assertTrue(c < 0);
    }

    @Test
    public void equalTest() {
        Eleve o1 = new Eleve();
        o1.setNom("AAAAAA");
        Eleve o2 = new Eleve();
        o2.setNom("AAAAAA");
        int c = comparator.compare(o1, o2);
        // assert that c is == 0
        assertTrue(c == 0);
    }

}
