package tiralabra.tiralabra.markov.text;
import tiralabra.tiralabra.markov.text.datastructures.DynamicList;
import java.util.*;
/**
 *
 * @author ruby
 */
public class Utility {
    
    
    /**
     * Return a child node based on the distribution of probabilities. How can
     * this be made more efficient?
     * @param childTokens 
     * @param weights
     * @return the next token based on frequency of occurrence.
     */
    public static String weightedChoice(DynamicList<String> childTokens, DynamicList<Integer> weights) {
        int sumWeight = 0;
        
        DynamicList<Integer> cumulativeWeights = new DynamicList();
        for (int i = 0; i < weights.size(); i++) {
            sumWeight += (int)weights.get(i);
            cumulativeWeights.add(sumWeight);
        }
        
        
        int randomNumber = randomIntInRange(sumWeight);
        int randomIndex = 0;
        for (int i = 0; i < cumulativeWeights.size(); i++) {
            if ((int)cumulativeWeights.get(i) >= randomNumber) {
                randomIndex = i;
                break;
            }
        }
        
        String token = (String)childTokens.get(randomIndex);
        
        return token;   
    }
    
    public static int randomIntInRange(int range) {
        
        return (int) (System.nanoTime() %  range);
        
    }
    
    /**
     * This method simulated the feature of ArrayList wherein the length of the
     * array is doubled if its size is exceeded and the new value inserted into
     * this new array, which is then returned.
     * @param array the array into which the value is to be added
     * @param lastIndex the last index where the array has meaningful values
     * @param value the element to be inserted into the array
     * @return either the old array with the element inserted, or a new array 
     * double the size of the given one where the element has been inserted.
     * Keeping track of the last index will need to be done in association with
     * the method call, this method will not do it for you.
     */
    public static String[] arrayAdd(String[] array, int lastIndex, String value) {
        
        if (lastIndex < array.length-1) {
            array[lastIndex+1] = value;
            return array;
        } else {
            String[] newArray = new String[array.length * 2];
            for (int i = 0; i< array.length; i++) {
                newArray[i] = array[i];
            }
            newArray[array.length] = value;
            return newArray;
        }
    }
    
    public static int pow(int base, int exponent) {
        int pow = base;
        for (int i = 1; i < exponent; i++) {
            pow *= exponent;
        }
        
        return pow;
    }
    
    
}
