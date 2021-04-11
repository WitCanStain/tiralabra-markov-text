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
    public static String generateSentence(Trie trie, int length) {
        System.out.println("Entered generateSentence.");
        String sentence = "";
        List<String> prevSequence = new ArrayList<>();
        TrieNode current = trie.getRoot();
        
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
            System.out.println("Sentence: " + sentence);
            prevSequence.add(token);
            current = current.getChildNodes().get(token);
        }
        System.out.println("First part end.");
        
        
        
        for (int i = k; i < length; i++) {
            childTokens = current.getChildTokens();
            weights = current.getWeights();

            String token = Utility.weightedChoice(childTokens, weights);

            sentence += token + " ";
            System.out.println("Sentence: " + sentence);
            prevSequence = prevSequence.subList(prevSequence.size()-k+1, prevSequence.size());
            prevSequence.add(token);
            System.out.println("prevsequence: " + prevSequence);
            current = trie.getNodeFromSequence(prevSequence);
            if (current == null) {
                System.out.println("Error! Error! Token not found.");
                break;
            }
        }
        
        System.out.println("Second part end.");
        System.out.println("Sentence:");
        System.out.println(sentence);
        return sentence;
    }
    
    
    
}
