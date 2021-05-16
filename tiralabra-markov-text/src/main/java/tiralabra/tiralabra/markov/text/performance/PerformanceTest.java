package tiralabra.tiralabra.markov.text.performance;
import tiralabra.tiralabra.markov.text.datastructures.Trie;
import tiralabra.tiralabra.markov.text.ParseInput;
import tiralabra.tiralabra.markov.text.MarkovProcess;
import tiralabra.tiralabra.markov.text.datastructures.DynamicList;
import java.util.*;
/**
 *
 * @author ruby
 */
public class PerformanceTest {
    private static DynamicList<String> tokenList;
    
    
    public static void runTests() {
        setUp();
        trieCreationTest(2);
        trieCreationTest(3);
        sentenceCreationTest();
    }
    
    public static void setUp() {
        tokenList = ParseInput.readFile("../dostoyevsky_karamazov.txt");
    }
    
    public static void trieCreationTest(int k) {
        System.out.println("Running TrieCreationTest.");
        int size = tokenList.size();
        ArrayList<Long> testTimes = new ArrayList<>();
        
        
        for (int i = 0; i < 20; i++) {
            long startTime = System.nanoTime();
            Trie trie = new Trie(tokenList, k);
            long endTime = System.nanoTime();
            testTimes.add(endTime - startTime);
        }
        long sum = 0;
        for (Long testTime: testTimes) {
            sum += testTime;
        }
        System.out.println("The average time to create a trie of " + size + " words with order " + k + " based on running 20 tests: " + (((double) sum) / 20) / 1000000 + " ms");
        System.out.println("Total time taken: " + ((double) sum) / 1000000 + " ms");   
    }
    
    public static void sentenceCreationTest() {
        int k = 2;
        Trie trie = new Trie(tokenList, k);
        long startTime = System.nanoTime();
        for (int i = 0; i < 10000; i++) {
            MarkovProcess.generateSentence(trie, k, 16);
        }
        long endTime = System.nanoTime();
        
        long runTime = endTime - startTime;
        
        System.out.println("The average time to generate 10 000 sentences from a trie of " + tokenList.size() + " words: " + (((double) runTime / 1000000) / 10000) + " ms");
        System.out.println("Total time taken: " + ((double) runTime / 1000000) + " ms");
    }
    
    
}
