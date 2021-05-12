package tiralabra.tiralabra.markov.text.performance;
import tiralabra.tiralabra.markov.text.datastructures.Trie;
import tiralabra.tiralabra.markov.text.ParseInput;
import tiralabra.tiralabra.markov.text.datastructures.DynamicList;
import java.util.*;
/**
 *
 * @author ruby
 */
public class PerformanceTest {
    private static DynamicList<String> tokenList;
    
    public static void setUp() {
        tokenList = ParseInput.readFile("../dostoyevsky_karamazov.txt");
    }
    
    public static void TrieCreationTest() {
        System.out.println("Running TrieCreationTest.");
        setUp();
        int size = tokenList.size();
        ArrayList<Long> testTimes = new ArrayList<>();
        int k = 2; // this value determines how many previous words to take into accocunt
        int l = 20; // this value determines how long the sentence should be
        for (int i =0; i < 20; i++) {
            long startTime = System.nanoTime();
            Trie trie = new Trie(tokenList, k);
            long endTime = System.nanoTime();
            testTimes.add(endTime-startTime);
        }
        long sum = 0;
        for (Long testTime: testTimes) {
            sum += testTime;
        }
        System.out.println("The average time to create a trie of " + size + " words based on running 20 tests: " + (((double)sum) / 20)/1000000 + " ms");
        
        
        
    }
    
    
}
