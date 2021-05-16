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
    
    
    
    public static void runTests() {
        
        trieCreationTest(2, "../fitzgerald_gatsby.txt", 20);
        trieCreationTest(4, "../fitzgerald_gatsby.txt", 20);
        trieCreationTest(2, "../dostoyevsky_karamazov.txt", 20);
        trieCreationTest(3, "../dostoyevsky_karamazov.txt", 20);
        sentenceCreationTest(2, "../dostoyevsky_karamazov.txt");
        sentenceCreationTest(3, "../dostoyevsky_karamazov.txt");
        System.exit(0);
    }
    

    
    public static void trieCreationTest(int k, String filePath, int n) {
        System.out.println("Running TrieCreationTest.");
        DynamicList<String> tokenList = ParseInput.readFile(filePath);
        int size = tokenList.size();
        ArrayList<Long> testTimes = new ArrayList<>();
        
        
        for (int i = 0; i < n; i++) {
            long startTime = System.nanoTime();
            Trie trie = new Trie(tokenList, k);
            long endTime = System.nanoTime();
            testTimes.add(endTime - startTime);
        }
        long sum = 0;
        for (Long testTime: testTimes) {
            sum += testTime;
        }
        System.out.println("The average time to create a trie of " + size + " words with order " + k + " based on running " + n + " tests: " + (((double) sum) / n) / 1000000 + " ms");
        System.out.println("Total time taken: " + ((double) sum) / 1000000 + " ms");   
    }
    
    public static void sentenceCreationTest(int k, String filePath) {
        DynamicList<String> tokenList = ParseInput.readFile(filePath);
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
