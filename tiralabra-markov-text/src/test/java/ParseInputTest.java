
import org.junit.After;
import org.junit.AfterClass;
import org.junit.Before;
import org.junit.BeforeClass;
import org.junit.Test;
import static org.junit.Assert.*;
import java.util.ArrayList;
import tiralabra.tiralabra.markov.text.ParseInput;
import tiralabra.tiralabra.markov.text.datastructures.DynamicList;

/**
 *
 * @author ruby
 */
public class ParseInputTest {
    
    public ParseInputTest() {
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

    // TODO add test methods here.
    // The methods must be annotated with annotation @Test. For example:
    //
    // @Test
    // public void hello() {}
    @Test
    public void readFileReturnsArrayList() {
        assertTrue(ParseInput.readFile("../testInputFile.txt") instanceof DynamicList<?>);
    }
    
    
}
