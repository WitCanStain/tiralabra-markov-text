package tiralabra.tiralabra.markov.text;


import java.util.*;
/**
 *
 * @author ruby
 */
public class TrieNode {
    private CustomHashMap childNodes;
    private ArrayList<String> childTokens;
    private ArrayList<Integer> weights;
    
    
    
    
    
    public TrieNode(int i) {
        childTokens = new ArrayList<>();
        weights = new ArrayList<>();
        childNodes = new CustomHashMap();
        
        
    }
    

    public CustomHashMap getChildNodes() {
        return childNodes;
    }
    
    public void childTokensAdd(String token) {
        childTokens.add(token);
    }
    
    public ArrayList<String> getChildTokens() {
        return childTokens;
    }
    
    public void addWeightToExisting(int index) {
        weights.set(index, weights.get(index)+1);
    }
    
    public void addNewWeight() {
        weights.add(1);
    }
    
    public ArrayList<Integer> getWeights() {
        return weights;
    }
    
    
    
    
}
