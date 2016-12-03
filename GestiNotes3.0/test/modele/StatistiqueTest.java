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
 *
 * @author Raphael Duchaine
 */
public class StatistiqueTest {

    Groupe groupe;

    public StatistiqueTest() {
        groupe = new Groupe(0);
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

    @Test
    public void testCalculerMoyenneAvecNotesIdentiques() {
        System.out.println("calculerMoyenneAvecNotesIdentiques");
        Groupe groupe = new Groupe(0);
        for (int i = 0; i < noms.length; i++) {
            groupe.addEleve(new Eleve(noms[i], prenoms[i], dates[i]));
            final int note = 50;
            groupe.getTabEleve().get(i).setNote(0, note);
            groupe.getTabEleve().get(i).setNote(1, note);
            groupe.getTabEleve().get(i).setNote(2, note);
            groupe.getTabEleve().get(i).setNote(3, note);
        }
        float expResult = 50F;
        float result = Statistique.calculerMoyenne(groupe);
        assertEquals(expResult, result, 0.0);
    }

    @Test
    public void testCalculerMoyenneAvecNotesDiverses() {
        System.out.println("calculerMoyenneAvecNotesDiverses");
        for (int i = 0; i < noms.length; i++) {
            groupe.addEleve(new Eleve(noms[i], prenoms[i], dates[i]));
            final int note = i;
            groupe.getTabEleve().get(i).setNote(0, note);
            groupe.getTabEleve().get(i).setNote(1, note);
            groupe.getTabEleve().get(i).setNote(2, note);
            groupe.getTabEleve().get(i).setNote(3, note);
        }
        float expResult = 3.5F;
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

    @Test
    public void testCalculerEcartTypeAvecNotesIdentiques() {
        System.out.println("calculerEcartTypeAvecNotesIdentiques");
        Groupe groupe = new Groupe(0);
        for (int i = 0; i < noms.length; i++) {
            groupe.addEleve(new Eleve(noms[i], prenoms[i], dates[i]));
            final int note = 1;
            groupe.getTabEleve().get(i).setNote(0, note);
            groupe.getTabEleve().get(i).setNote(1, note);
            groupe.getTabEleve().get(i).setNote(2, note);
            groupe.getTabEleve().get(i).setNote(3, note);
        }
        float expResult = 0F;
        float result = Statistique.calculerEcartType(groupe);
        //TO-DO Make a more precise EcartType methode
        assertEquals(expResult, result, 0.0);
    }

    @Test
    public void testCalculerEcartTypeAvecNotesDiverses() {
        System.out.println("calculerEcartTypeAvecNotesDiverses");
        //Donne des notes de 1 à 8 aux 8 eleves
        for (int i = 0; i < noms.length; i++) {
            groupe.addEleve(new Eleve(noms[i], prenoms[i], dates[i]));
            final int note = i;
            groupe.getTabEleve().get(i).setNote(0, note);
            groupe.getTabEleve().get(i).setNote(1, note);
            groupe.getTabEleve().get(i).setNote(2, note);
            groupe.getTabEleve().get(i).setNote(3, note);
        }
        float expResult = 2.29F;
        float result = Statistique.calculerEcartType(groupe);
        assertEquals(expResult, result, 0.0);
    }

    /**
     * Test of calculerVariance method, of class Statistique.
     */
    @Test
    public void testCalculerVarianceSansNotes() {
        System.out.println("calculerVarianceSansNotes");
        for (int i = 0; i < noms.length; i++) {
            groupe.addEleve(new Eleve(noms[i], prenoms[i], dates[i]));
//            groupe.getTabEleve().get(i).setNote(0, i);
        }
        float expResult = 0F;
        float result = Statistique.calculerVariance(groupe);
        assertEquals(expResult, result, 0.0);
    }

    @Test
    public void testCalculerVarianceAvecNotesIdentiques() {
        System.out.println("calculerVarianceAvecNotesIdentiques");
        for (int i = 0; i < noms.length; i++) {
            groupe.addEleve(new Eleve(noms[i], prenoms[i], dates[i]));
//            groupe.getTabEleve().get(i).setNote(0, i);
        }
        float expResult = 0F;
        float result = Statistique.calculerVariance(groupe);
        assertEquals(expResult, result, 0.0);
    }

    @Test
    public void testCalculerVarianceAvecNotesDiverses() {
        System.out.println("CalculerVarianceAvecNotesDiverses");
        //Donne des notes de 1 à 8 aux 8 eleves
        for (int i = 0; i < noms.length; i++) {
            groupe.addEleve(new Eleve(noms[i], prenoms[i], dates[i]));
            final int note = i;
            groupe.getTabEleve().get(i).setNote(0, note);
            groupe.getTabEleve().get(i).setNote(1, note);
            groupe.getTabEleve().get(i).setNote(2, note);
            groupe.getTabEleve().get(i).setNote(3, note);
        }
        float expResult = 5.25F;
        float result = Statistique.calculerVariance(groupe);
        assertEquals(expResult, result, 0.0);
    }
    
    /*
    *Le 7e eleve ne se faisait pas donner de note puisque en ajoutant un eleve on trie juste apres
    * Donc K'amon se retrouvait 7e et ne se faisait donner aucune note
    * #BugFixing
    */

}
