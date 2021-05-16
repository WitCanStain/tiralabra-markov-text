
import org.junit.After;
import org.junit.AfterClass;
import org.junit.Before;
import org.junit.BeforeClass;
import tiralabra.tiralabra.markov.text.datastructures.DynamicList;
import static org.junit.Assert.*;
import org.junit.Test;
/**
 *
 * @author ruby
 */
public class DynamicListTest {
    DynamicList<Integer> dynamicList;
    
    
    public DynamicListTest() {
    }
    
    @Before
    public void setUp() {
        dynamicList = new DynamicList<>();
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
    
    @Test
    public void settingIndexOutOfBoundsReturnsFalse() {
        boolean retVal = dynamicList.set(2, 1);
        
        assertFalse(retVal);
    }
    
    @Test
    public void indexOfNonExistentElementIsNegativeOne() {
        dynamicList.add(1);
        int retVal = dynamicList.indexOf(2);
        System.out.println(retVal);
        assertEquals(retVal, -1);
    }
    
    @Test
    public void containsReturnsFalseWhenItemDoesNotExist() {
        dynamicList.add(2);
        boolean retVal = dynamicList.contains(1);
        
        assertFalse(retVal);
    }
    
    @Test
    public void containsReturnsTrueWhenItemExists() {
        dynamicList.add(1);
        boolean retVal = dynamicList.contains(1);
        assertTrue(retVal);
    }
}
