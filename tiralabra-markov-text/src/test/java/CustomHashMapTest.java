
import org.junit.After;
import org.junit.AfterClass;
import org.junit.Before;
import org.junit.BeforeClass;
import org.junit.Test;
import static org.junit.Assert.*;
import tiralabra.tiralabra.markov.text.datastructures.CustomHashMap;
import tiralabra.tiralabra.markov.text.datastructures.HashNode;
import tiralabra.tiralabra.markov.text.datastructures.TrieNode;

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
    
    @Test
    public void internalArrayIsResizedWhenLoadFactorExceeded() {
        
        for (int i = 0; i<13; i++) {
            TrieNode value = new TrieNode();
            customHashMap.put("test" + i, value);
        }
        assertTrue(customHashMap.getInternalArrayLength() == 32);
    }
    
    @Test
    public void puttingWithSameKeyOverWritesValue() {
        
        TrieNode first = new TrieNode();
        TrieNode second = new TrieNode();
        first.getChildTokens().add("test1");
        second.getChildTokens().add("test2");
        
        
        customHashMap.put("test", first);
        customHashMap.put("test", second);
        TrieNode secondRetrieved = customHashMap.get("test");
        
        assertTrue(secondRetrieved.getChildTokens().contains("test2"));
    }
    
    @Test
    public void gettingNonExistentItemReturnsNull() {
        TrieNode result = customHashMap.get("nonexistent");
        
        assertEquals(result, null);
    }
}
