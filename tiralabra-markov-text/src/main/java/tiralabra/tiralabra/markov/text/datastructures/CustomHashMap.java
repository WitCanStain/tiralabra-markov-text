package tiralabra.tiralabra.markov.text.datastructures;
import tiralabra.tiralabra.markov.text.Utility;

/**
 *
 * @author ruby
 * This class implements a custom version of a HashMap. Due to the nature of this
 * project, we only need it to store TrieNodes.
 */
public class CustomHashMap {
    private DynamicList<HashNode>[] hashArray;
    private int elementCounter = 0;
    private final int constant = 31;
    private final float loadfactor = 0.75f;
    
    public CustomHashMap() {
        hashArray = new DynamicList[16];
        for (int i = 0; i < 16; i++) {
            hashArray[i] = new DynamicList<>();
        }
    }
    
    /**
     * Put an item in the CustomHashMap based on its key's hashcode.
     * @param key the key that acts as the index of the given value.
     * @param value the value to be inserted into the CustomHashMap.
     */
    public void put(String key, TrieNode value) {
        
        if (!containsKey(key)) {    
            elementCounter++;
            // grow the array when number of elements exceeds the load threshold
            if (elementCounter >= hashArray.length * loadfactor) {
                resize();
            }
            // this ensures that our hashcode falls within the range of the hash array
            int index = hashFunc(key) & (hashArray.length - 1);
            hashArray[index].add(new HashNode(key, value));    
        } else { // if the key is already in the CustomHashMap, overwrite its value
            int index = hashFunc(key) & (hashArray.length - 1);
            hashArray[index].add(new HashNode(key, value));
            hashArray[index].set(hashArray[index].indexOf(get(key, value)), new HashNode(key, value));
        }
        
    }
    
    /**
     * Internal helper method for growing the HashMap when the load factor is
     * exceeded. It redistributes each existing item into a new location based 
     * on the recalculated hash function value.
     * @param newHashArray the new, resized hash array.
     * @param key the key that acts as the index of the given value.
     * @param value the value to be inserted into the CustomHashMap.
     */
    private void put(DynamicList[] newHashArray, String key, TrieNode value) {
        
        int index = hashFunc(key) & (newHashArray.length - 1);
        newHashArray[index].add(new HashNode(key, value));
        
    }
    
    
    /** 
     * This method resizes the CustomHashMap when a certain amount of items has
     * been inserted into it.
     */
    private void resize() {
        
        DynamicList<HashNode>[] newHashArray = new DynamicList[2 * hashArray.length];
        for (int i = 0; i < newHashArray.length; i++) {
            newHashArray[i] = new DynamicList();
        }
                
        // redistribute all existing entries to maintain indexing coherence
        for (int i = 0; i < hashArray.length; i++) {
            for (int j = 0; j < hashArray[i].size(); j++) {
                put(newHashArray, ((HashNode) hashArray[i].get(j)).key, ((HashNode) hashArray[i].get(j)).value);
            }
        }
        // set old hash array to point to the new, bigger one
        hashArray = newHashArray;
    }
    
    /**
     * Get the value based on the key.
     * @param key the key that acts as the index for the value.
     * @return The corresponding value for the key. In this program, always a 
     * TrieNode.
     */
    public TrieNode get(String key) {
        
        int index = hashFunc(key) & (hashArray.length - 1);
        
        DynamicList<HashNode> bucket = hashArray[index];
        
        for (int i = 0; i < bucket.size(); i++) {
            
            HashNode hashNode = ((HashNode) bucket.get(i));
            if (hashNode.key.equals(key)) {
                
                return hashNode.value;
            }
        }
        
        return null;
    }
    
    /**
     * Helper method for overwriting existing values if a key-value with an
     * already-existing key has been provided in the put() function. It returns
     * a HashNode instead of a TrieNode to allow the put() function to overwrite
     * the value at the correct index of the bucket.
     * @param key the key that acts as the index of the value.
     * @param value This doesn't really serve a purpose, it is used for 
     * differentiating overloading constructors.
     * @return The HashNode that contains the key.
     */
    private HashNode get(String key, TrieNode value) {
        
        int index = hashFunc(key) & (hashArray.length - 1);
        
        DynamicList<HashNode> bucket = hashArray[index];
        
        for (int i = 0; i < bucket.size(); i++) {
            
            HashNode hashNode = ((HashNode) bucket.get(i));
            if (hashNode.key.equals(key)) {
                
                return hashNode;
            }
        }
        
        return null;
    }
    
    public int getInternalArrayLength() {
        return hashArray.length;
    }
    
    /**
     * Check whether the given key exists in the CustomHashMap.
     * @param key
     * @return 
     */
    public boolean containsKey(String key) {
        
        return get(key) != null;
    }
    
    /**
     * Calculate the hash value for the given key. Since in this program only
     * strings are used as keys, it calculates the hash value based on the 
     * character codes and the constant A.
     * @param key
     * @return the calculated hash code.
     */
    private int hashFunc(String key) {
        
        int hashSum = 0;
        int l = key.length();
        for (int i = 0; i < l; i++) {
            hashSum += ((int) key.charAt(i)) * Utility.pow(constant, (l - 1) - i);
        }
        
        return hashSum;
    }
}
