package tiralabra.tiralabra.markov.text;
import tiralabra.tiralabra.markov.text.datastructures.DynamicList;
import java.util.*;
/**
 *
 * @author ruby
 * This class provides a set of helper methods to aid methods in other classes.
 */
public class Utility {
    
    
    /**
     * Return a child node based on the distribution of probabilities.
     * @param childTokens 
     * @param weights
     * @return the next token based on frequency of occurrence.
     */
    public static String weightedChoice(DynamicList<String> childTokens, DynamicList<Integer> weights) {
        int sumWeight = 0;

        DynamicList<Integer> cumulativeWeights = new DynamicList();
        for (int i = 0; i < weights.size(); i++) {
            sumWeight += (int) weights.get(i);
            cumulativeWeights.add(sumWeight);
        }
        
        
        int randomNumber = randomIntInRange(sumWeight);
        int randomIndex = 0;
        for (int i = 0; i < cumulativeWeights.size(); i++) {
            if ((int) cumulativeWeights.get(i) > randomNumber) {
                randomIndex = i;
                break;
            }
        }
        
        String token = childTokens.get(randomIndex);
        
        return token;   
    }
    
    public static int randomIntInRange(int range) {
        
        return (int) (System.nanoTime() %  range);
        
    }
    
    public static int pow(int base, int exponent) {
        int pow = base;
        for (int i = 1; i < exponent; i++) {
            pow *= base;
        }
        
        return pow;
    }
    
    
}
