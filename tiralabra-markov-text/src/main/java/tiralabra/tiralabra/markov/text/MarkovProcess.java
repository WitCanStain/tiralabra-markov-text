package tiralabra.tiralabra.markov.text;
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
     * @return the generated sentence.
     */
    public String generateSentence(Trie trie, int length) {
        String sentence = "";
        List<String> prevSequence = new ArrayList<>();
        TrieNode current = trie.getRoot();
        ArrayList<String> tokenList = ParseInput.getTokenList();
        int k = Main.getK();
        ArrayList<String> childTokens = new ArrayList<>();
        ArrayList<Integer> weights = new ArrayList<>();
        
        /* we have to select the first k words separately since we don't have
        enough tokens to draw on yet.
        */
        for (int i = 0; i < k; i++) {
            
            childTokens = current.getChildTokens();
            weights = current.getWeights();
            
            String token = Utility.weightedChoice(childTokens, weights);
            
            
            sentence += token + " ";
            prevSequence.add(token);
            current = current.getChildNodes().get(token);
        }
        
        
        
        for (int i = k; i < length; i++) {
            childTokens = current.getChildTokens();
            weights = current.getWeights();

            String token = Utility.weightedChoice(childTokens, weights);

            sentence += token + " ";
            prevSequence = prevSequence.subList(prevSequence.size()-k, prevSequence.size());
            current = trie.getNodeFromSequence(prevSequence);
            if (current == null) {
                System.out.println("Error! Error! Token not found.");
                break;
            }
        }
        
        
        return sentence;
    }
    
    
    
}
