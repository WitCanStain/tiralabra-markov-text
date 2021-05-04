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
        System.out.println("Entered generateSentence.");
        String sentence = "";
//        List<String> prevSequence = new ArrayList<>();
        String [] prevSequence = new String[10];
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
            prevSequence = Utility.arrayAdd(prevSequence, i, token);
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
            prevSequence = Utility.arrayAdd(prevSequence, sequenceLastIndex, token);
            sequenceLastIndex++;
            int l = 0;
            for (int j = sequenceLastIndex-k+1; j <= sequenceLastIndex; j++) {
                prevSequence[l] = prevSequence[j];
            }
            sequenceLastIndex = k-1;
            
            current = trie.getNodeFromSequence(prevSequence, sequenceLastIndex);
            if (current == null) {
                System.out.println("Error! Error! Token not found.");
                break;
            }
        }
        
        
        System.out.println("Sentence:");
        System.out.println(sentence);
        return sentence;
    }
    
    
    
}
