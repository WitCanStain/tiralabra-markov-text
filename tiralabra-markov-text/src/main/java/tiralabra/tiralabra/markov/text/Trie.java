package tiralabra.tiralabra.markov.text;
import java.util.*;
/**
 *
 * @author ruby
 */
public class Trie {
    private TrieNode root;
    
    public Trie (ArrayList<String> tokenList, int k) {
        
    }
    
    /**
     * Create a trie based on a list of word tokens.
     * @param tokenList ordered list of words.
     * @param k the number of tokens constituting the previous state. The depth
     * of the trie will be k+2 (root, k nodes and leaf node)
     */
    public void createTrie(ArrayList<String> tokenList, int k) {
        
        for (int i = k; i < tokenList.size(); i++) {
            ArrayList<String> sequence = new ArrayList<>();
            for (int j = i-k; j <= i; j--) {
                sequence.add(tokenList.get(j));
            }
            
            insert(sequence);
        }
    }
    
    /**
     * Insert a state into the trie if it does not exist, or increase the 
     * counter of its occurrences if it already exists.
     * @param sequence the sequence of tokens to be inserted.
     */
    public void insert(ArrayList<String> sequence) {
        TrieNode current = root;
        
        for (String token : sequence) {
            HashMap<String, TrieNode> nodeChildren = current.getChildNodes();
            if (nodeChildren.containsKey(token)) {
                current.addWeightToExisting(current.getChildTokens().indexOf(token));
                current = nodeChildren.get(token);
                
                
                
            } else {
                TrieNode newNode = new TrieNode();
                nodeChildren.put(token, newNode);
                current.childTokensAdd(token);
                current.addNewWeight();
                current = newNode;
                
            }
        }
        
        
        
        
    }
    
    /**
     * Traverse the trie along the given sequence and return the last node.
     * @param sequence the sequence to traverse, i.e. a list of tokens.
     * @return the last node of the sequence.
     */
    public TrieNode getNodeFromSequence(List<String> sequence) {
        TrieNode current = root;
        for (int i = 0; i < sequence.size(); i++) {
            
            String token = sequence.get(i);
            TrieNode node = current.getChildNodes().get(token);
            if (node == null) {
                return null;
            }
            current = node;
            
        }
        return current;
    }
    
    public TrieNode getRoot() {
        return root;
    }
    
}
