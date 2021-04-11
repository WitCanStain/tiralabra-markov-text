package tiralabra.tiralabra.markov.text;
import java.util.*;
/**
 *
 * @author ruby
 */
public class Main {
    private static int k;
    
    public static void main(String[] args) {
        ArrayList<String> tokenList = ParseInput.readFile("../testInputFile");
        k = 3;
        Trie trie = new Trie(tokenList, k);
        MarkovProcess.generateSentence(trie, 10);
        System.out.println("Should end here.");
    }
    
    
    public static int getK() {
        return k;
    }
}
