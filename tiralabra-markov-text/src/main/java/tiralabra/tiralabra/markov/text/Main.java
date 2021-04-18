package tiralabra.tiralabra.markov.text;
import java.util.*;
/**
 *
 * @author ruby
 */
public class Main {
    
    
    public static void main(String[] args) {
        ArrayList<String> tokenList = ParseInput.readFile("../testInputFile");
        int k = 1; // this value determines how many previous words to take into accocunt
        int l = 6; // this value determines how long the sentence should be
        Trie trie = new Trie(tokenList, k);
        MarkovProcess.generateSentence(trie, k, l);
        System.out.println("End.");
        
        
    }
    
    
    
}
