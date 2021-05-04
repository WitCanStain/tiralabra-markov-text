package tiralabra.tiralabra.markov.text;

/**
 *
 * @author ruby
 */
public class CustomHashMap {
    private DynamicList<HashNode>[] hashArray;
    private int elementCounter = 0;
    private final int A = 31;
    private final float loadfactor = 0.75f;
    
    public CustomHashMap() {
        hashArray = new DynamicList[16]; // we start with an array of size 2^4
        for (int i = 0; i<16; i++) {
            hashArray[i] = new DynamicList<>();
        }
    }
    
    public void put(String key, TrieNode value) {
        
        if (!containsKey(key)) {    
            elementCounter++;
            // grow the array when number of elements exceeds the load threshold
            if (elementCounter >= hashArray.length*loadfactor) {
                resize();
            }
            // this ensures that our hashcode falls within the range of the hash array
            int index = hashFunc(key) & (hashArray.length-1);
            hashArray[index].add(new HashNode(key, value));    
        }
        
    }
    
    private void put(DynamicList[] newHashArray, String key, TrieNode value ) {
        
        int index = hashFunc(key) & (newHashArray.length-1);
        newHashArray[index].add(new HashNode(key, value));
        
    }
    
    private void resize() {
        
        DynamicList<HashNode>[] newHashArray = new DynamicList[2*hashArray.length];
        for (int i = 0; i < newHashArray.length; i++) {
            newHashArray[i]= new DynamicList();
        }
                
        // redistribute all existing entries to maintain indexing coherence
        for (int i = 0; i < hashArray.length; i++) {
            for (int j = 0; j <hashArray[i].size(); j++) {
                put(newHashArray, ((HashNode)hashArray[i].get(j)).key, ((HashNode)hashArray[i].get(j)).value);
            }
        }
        // set old hash array to point to the new, bigger one
        hashArray = newHashArray;
    }
    
    public TrieNode get(String key) {
        
        int index = hashFunc(key) & (hashArray.length-1);
        
        DynamicList<HashNode> bucket = hashArray[index];
        
        for (int i = 0; i < bucket.size(); i++) {
            
            HashNode hashNode = ((HashNode) bucket.get(i));
            if (hashNode.key.equals(key)) {
                
                return hashNode.value;
            }
        }
        
        return null;
    }
    
    public boolean containsKey(String key) {
        
        return get(key) != null;
    }
    
    private int hashFunc(String key) {
        
        int hashSum = 0;
        int l = key.length();
        for (int i = 0; i<l; i++) {
            hashSum += ((int)key.charAt(i))*Utility.pow(A, (l-1)-i);
        }
        
        return hashSum;
    }
}
