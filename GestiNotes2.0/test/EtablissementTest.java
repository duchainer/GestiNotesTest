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
 * Créé fièrement 2016-10-23, 12:28:55
 * @author Raphael Duchaine
 */
public class EtablissementTest {

    public EtablissementTest() {
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
     * Test of Etablissement method, of class Etablissement.
     */
    @Test
    public void testEtablissement() {
        System.out.println("Etablissement");
        Etablissement.Etablissement();
        // TODO review the generated test code and remove the default call to fail.
        fail("The test case is a prototype.");
    }

    /**
     * Test of getNomEcole method, of class Etablissement.
     */
    @Test
    public void testGetNomEcole() {
        System.out.println("getNomEcole");
        String expResult = "";
        String result = Etablissement.getNomEcole();
        assertEquals(expResult, result);
        // TODO review the generated test code and remove the default call to fail.
        fail("The test case is a prototype.");
    }

    /**
     * Test of setNomEcole method, of class Etablissement.
     */
    @Test
    public void testSetNomEcole() {
        System.out.println("setNomEcole");
        String aNomEcole = "";
        Etablissement.setNomEcole(aNomEcole);
        // TODO review the generated test code and remove the default call to fail.
        fail("The test case is a prototype.");
    }

    /**
     * Test of getTabGroupe method, of class Etablissement.
     */
    @Test
    public void testGetTabGroupe() {
        System.out.println("getTabGroupe");
        ArrayList<Groupe> expResult = null;
        ArrayList<Groupe> result = Etablissement.getTabGroupe();
        assertEquals(expResult, result);
        // TODO review the generated test code and remove the default call to fail.
        fail("The test case is a prototype.");
    }

    /**
     * Test of setTabGroupe method, of class Etablissement.
     */
    @Test
    public void testSetTabGroupe() {
        System.out.println("setTabGroupe");
        ArrayList<Groupe> aTabGroupe = null;
        Etablissement.setTabGroupe(aTabGroupe);
        // TODO review the generated test code and remove the default call to fail.
        fail("The test case is a prototype.");
    }

    /**
     * Test of addGroupe method, of class Etablissement.
     */
    @Test
    public void testAddGroupe() {
        System.out.println("addGroupe");
        Groupe expResult = null;
        Groupe result = Etablissement.addGroupe();
        assertEquals(expResult, result);
        // TODO review the generated test code and remove the default call to fail.
        fail("The test case is a prototype.");
    }

    /**
     * Test of searchEleve method, of class Etablissement.
     */
    @Test
    public void testSearchEleve() {
        System.out.println("searchEleve");
        String code = "";
        Eleve expResult = null;
        Eleve result = Etablissement.searchEleve(code);
        assertEquals(expResult, result);
        // TODO review the generated test code and remove the default call to fail.
        fail("The test case is a prototype.");
    }

}