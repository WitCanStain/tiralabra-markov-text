
import org.junit.After;
import org.junit.AfterClass;
import org.junit.Before;
import org.junit.BeforeClass;
import tiralabra.tiralabra.markov.text.DynamicList;
import static org.junit.Assert.*;
import org.junit.Test;
/**
 *
 * @author ruby
 */
public class DynamicListTest {
    DynamicList dynamicList;
    
    
    public DynamicListTest() {
    }
    
    @BeforeClass
    public static void setUpClass() {
    }
    
    @AfterClass
    public static void tearDownClass() {
    }
    
    @Before
    public void setUp() {
        dynamicList = new DynamicList();
    }
    
    @After
    public void tearDown() {
    }

    
    
    @Test
    public void addingOneElementAndCheckingSizeReturnsOne() {
        dynamicList.add(1);
        assertEquals(dynamicList.size(), 1);
                
    }
    
    @Test
    public void gettingNegativeIndexReturnsError() {
        dynamicList.add(1);
        Object result = dynamicList.get(-1);
        assertEquals(null, result);
    }
    
    @Test
    public void addingMoreThan10ItemsGrowsList() {
        
        for (int i = 0; i<11; i++) {
            dynamicList.add(i);
        }
        int finalSize = dynamicList.internalArraySize();
        
        assertTrue(finalSize == 20);
        
        
    }
}
