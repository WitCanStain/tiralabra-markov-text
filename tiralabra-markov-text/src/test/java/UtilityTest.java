
import java.util.HashMap;
import org.junit.Before;
import org.junit.Test;
import static org.junit.Assert.*;
import tiralabra.tiralabra.markov.text.Utility;
import tiralabra.tiralabra.markov.text.datastructures.DynamicList;
/**
 *
 * @author ruby
 */
public class UtilityTest {
    
    public UtilityTest() {
    }
    
    @Before
    public void setUp() {
    }

    @Test
    public void powReturnsCorrectNumber() {
        int number = Utility.pow(2,3);
        assertEquals(8, number);
    }
    
    @Test 
    public void randomIntInRangeReturnsNumberInRange() {
        int number = Utility.randomIntInRange(100);
        
        assertTrue(number<100 && number>=0);
    }
    
    @Test
    public void weightedChoiceReturnsWeightedChoice() {
        
        DynamicList<String> tokens = new DynamicList<>();
        tokens.add("a");
        tokens.add("b");
        tokens.add("c");
        
        DynamicList<Integer> weights = new DynamicList<>();
        weights.add(1);
        weights.add(3);
        weights.add(6);
        
        int[] distribution = {0, 0, 0};
        
        for (int i = 0; i<1000; i++) {
            String token = Utility.weightedChoice(tokens, weights);
            switch (token) {
                case "a":
                    distribution[0]++;
                    break;
                case "b":
                    distribution[1]++;
                    break;
                case "c":
                    distribution[2]++;
                    break;
                default:
                    break;
            }
        }
        for (int i: distribution) {
            System.out.println(i);
        }
        assertTrue(distribution[0]>70 && distribution[0]<130 && distribution[1]>170 
                && distribution[1]>230 && distribution[2]>570 && distribution[1]<630);
    }
}
