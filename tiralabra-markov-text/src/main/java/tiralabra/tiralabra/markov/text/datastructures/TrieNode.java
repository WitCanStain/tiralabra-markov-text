package tiralabra.tiralabra.markov.text.datastructures;


import java.util.*;
/**
 *
 * @author ruby
 */
public class TrieNode {
    private CustomHashMap childNodes;
    private DynamicList<String> childTokens;
    private DynamicList<Integer> weights;
    
    
    
    
    
    public TrieNode() {
        childTokens = new DynamicList();
        weights = new DynamicList();
        childNodes = new CustomHashMap();
        
        
    }
    

    public CustomHashMap getChildNodes() {
        return childNodes;
    }
    
    public void childTokensAdd(String token) {
        childTokens.add(token);
    }
    
    public DynamicList getChildTokens() {
        return childTokens;
    }
    
    public void addWeightToExisting(int index) {
        weights.set(index, (int)weights.get(index)+1);
    }
    
    public void addNewWeight() {
        weights.add(1);
    }
    
    public DynamicList getWeights() {
        return weights;
    }
    
    
    
    
}
