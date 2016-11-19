import modele.*;
import org.junit.After;
import org.junit.AfterClass;
import org.junit.Before;
import org.junit.BeforeClass;
import org.junit.runner.RunWith;
import org.junit.runners.Suite;

/**
 * Créé fièrement 2016-11-19, 09:36:06
 * @author Raphael Duchaine
 */
@RunWith(Suite.class)
@Suite.SuiteClasses({
    EleveTest.class,
    EvaluationTest.class
})
public class ModeleTest {

    @BeforeClass
    public static void setUpClass() throws Exception {
    }

    @AfterClass
    public static void tearDownClass() throws Exception {
    }

    @Before
    public void setUp() throws Exception {
    }

    @After
    public void tearDown() throws Exception {
    }
    
}
