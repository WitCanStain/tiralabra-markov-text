package tiralabra.tiralabra.markov.text;

/**
 *
 * @author ruby
 */
public class HashNode {
    
    public String key;
    public TrieNode value;
    
    public HashNode(String key, TrieNode value) {
        this.key=key;
        this.value=value;
    }
}
