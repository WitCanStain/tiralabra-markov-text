package tiralabra.tiralabra.markov.text;
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
    public static String weightedChoice(ArrayList<String> childTokens, ArrayList<Integer> weights) {
        int sumWeight = 0;
        ArrayList<Integer> cumulativeWeights = new ArrayList<>();
        for (int i : weights) {
            sumWeight += i;
            cumulativeWeights.add(sumWeight);
        }
        
       Random random = new Random();
       int randomNumber = random.nextInt(sumWeight);
       int randomIndex = 0;
       for (int i = 0; i < cumulativeWeights.size(); i++) {
           if (cumulativeWeights.get(i) >= randomNumber) {
               randomIndex = i;
           }
       }
       return childTokens.get(randomIndex);
        
        
        
        
    }
    
    
}
