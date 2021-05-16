package tiralabra.tiralabra.markov.text.datastructures;

import tiralabra.tiralabra.markov.text.datastructures.TrieNode;

/**
 *
 * @author ruby
 * This class implements the nodes that populate CustomHashMap buckets and allow
 * for O(1) time putting and getting.
 */
public class HashNode {
    
    public String key;
    public TrieNode value;
    
    public HashNode(String key, TrieNode value) {
        this.key = key;
        this.value = value;
    }
}
