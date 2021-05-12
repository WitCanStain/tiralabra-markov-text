package tiralabra.tiralabra.markov.text;
import tiralabra.tiralabra.markov.text.datastructures.DynamicList;
import tiralabra.tiralabra.markov.text.datastructures.Trie;
import tiralabra.tiralabra.markov.text.datastructures.TrieNode;
import java.util.*;
/**
 *
 * @author ruby
 */
public class MarkovProcess {
    
    
    
    /**
     * Create a sentence by traversing the trie.
     * @param trie the trie from which the sentence is to be created.
     * @param length the number of tokens (words) in the sentence.
     * @param k the depth of the trie
     * @return the generated sentence.
     */
    public static String generateSentence(Trie trie, int k, int length) {
        
        String sentence = "";
//        List<String> prevSequence = new ArrayList<>();
        DynamicList<String> prevSequence = new DynamicList<>();
        TrieNode current = trie.getRoot();
        
        
        DynamicList<String> childTokens;
        DynamicList<Integer> weights;
        int sequenceLastIndex = 0;
        /* we have to select the first k words separately since we don't have
        enough tokens to draw on yet.
        */
        for (int i = 0; i < k; i++) {
            
            childTokens = current.getChildTokens();
            weights = current.getWeights();
            
            String token = Utility.weightedChoice(childTokens, weights);
            
            
            sentence += token + " ";
            
//            prevSequence.add(token);
            prevSequence.add(token);
            sequenceLastIndex++;
            current = current.getChildNodes().get(token);
        }
        
        for (int i = k; i < length; i++) {
            childTokens = current.getChildTokens();
            
            weights = current.getWeights();

            String token = Utility.weightedChoice(childTokens, weights);

            sentence += token + " ";
            
//            prevSequence = prevSequence.subList(prevSequence.size()-k+1, prevSequence.size());
//            prevSequence.add(token);
            prevSequence.add(token);
            sequenceLastIndex++;
            
            // prevSequence should contain the previous k tokens only
            DynamicList<String> newSequence = new DynamicList<>();
            for (int j = 0; j < prevSequence.size()-1; j++) {
                newSequence.add(prevSequence.get(j+1));
            }
            prevSequence = newSequence;
            sequenceLastIndex = k-1;
            
            current = trie.getNodeFromSequence(prevSequence, prevSequence.size());
            if (current == null) {
                System.out.println("Error! Error! Token not found.");
                break;
            }
        }
        
        return sentence;
    }
    
    
    
}
