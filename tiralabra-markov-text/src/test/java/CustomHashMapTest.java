
import org.junit.After;
import org.junit.AfterClass;
import org.junit.Before;
import org.junit.BeforeClass;
import org.junit.Test;
import static org.junit.Assert.*;
import tiralabra.tiralabra.markov.text.CustomHashMap;
import tiralabra.tiralabra.markov.text.HashNode;
import tiralabra.tiralabra.markov.text.TrieNode;

/**
 *
 * @author ruby
 */
public class CustomHashMapTest {
    CustomHashMap customHashMap;
    public CustomHashMapTest() {
    }
    
    @BeforeClass
    public static void setUpClass() {
    }
    
    @AfterClass
    public static void tearDownClass() {
    }
    
    @Before
    public void setUp() {
        customHashMap = new CustomHashMap();
        
    }
    
    @After
    public void tearDown() {
    }

  
    @Test
    public void insertedItemIsReturned() {
        TrieNode value = new TrieNode();
        customHashMap.put("test", value);
        TrieNode retrievedValue = customHashMap.get("test");
        
        assertEquals(value, retrievedValue);
                
                
    }
}
