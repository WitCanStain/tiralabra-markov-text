package tiralabra.tiralabra.markov.text;
import java.util.*;
/**
 *
 * @author ruby
 */
public class Main {
    
    
    public static void main(String[] args) {
        ArrayList<String> tokenList = ParseInput.readFile("../testInputFile");
        int k = 2; // this value determines how many previous words to take into accocunt
        int l = 6; // this value determines how long the sentence should be
        Trie trie = new Trie(tokenList, k);
        MarkovProcess.generateSentence(trie, k, l);
        System.out.println("End.");
        
//        DynamicList array = new DynamicList();
//        array.add(1);
//        array.add(2);
//        array.add(3);
//        array.add(4);
//        array.add(5);
//        array.add(6);
//        System.out.println("array size: " + array.size());
//        
//        for (int i = 0; i<array.size(); i++) {
//            System.out.println(array.get(i));
//        }
//        
//        CustomHashMap hashMap = new CustomHashMap();
//        
//        TrieNode node = new TrieNode(1);
//        String teksti = "Hello";
//        System.out.println(teksti);
//        hashMap.put(teksti, node);
//        System.out.println(hashMap.get(teksti).getClass().getName());
//        for (int i = 0; i < 16; i++) {
//            teksti = "Hello" + i;
//            TrieNode trienode = new TrieNode(i);
//            hashMap.put(teksti, trienode);
//        }
//        System.out.println("Hello15:");
//        System.out.println(hashMap.get("Hello15").getClass().getName());
//        System.out.println(hashMap.get("Hello15").getI());
        
        
        
    }
    
    
    
}
