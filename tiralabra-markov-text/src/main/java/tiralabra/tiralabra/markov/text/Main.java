package tiralabra.tiralabra.markov.text;
import tiralabra.tiralabra.markov.text.datastructures.DynamicList;
import tiralabra.tiralabra.markov.text.datastructures.Trie;
import tiralabra.tiralabra.markov.text.performance.PerformanceTest;
import java.util.*;
/**
 *
 * @author ruby
 */

public class Main {
    
    
    public static void main(String[] args) {
        
        
//        PerformanceTest.runTests();
        Object[] params = ParseInput.parseCmdLine(args);
        
        String filePath = (String) params[0];
        int k = (int) params[1];
        int l = (int) params[2];
        int n = (int) params[3];
        
        
        System.out.println("Creating trie from file " + filePath + ".");
        DynamicList<String> tokenList = ParseInput.readFile(filePath);
        
        long trieStartTime = System.nanoTime();
        Trie trie = new Trie(tokenList, k);
        long trieEndTime = System.nanoTime();
        long trieRunTime = (trieEndTime - trieStartTime) / 1000000;
        
        System.out.println("Trie created from " + tokenList.size() + " tokens in " + trieRunTime + " ms.\n");
        
        System.out.println("Generating " + n + " sentences of length " + l + " using the previous " + k + " tokens.\n");
        long sentenceStartTime = System.nanoTime();
        for (int i = 1; i <= n; i++) {    
            String sentence = MarkovProcess.generateSentence(trie, k, l);
            System.out.println(String.format("%-4s %s" , i + ":",  sentence));    
        }
        long sentenceEndTime = System.nanoTime();
        long sentenceRunTime = (sentenceEndTime - sentenceStartTime) / 1000000;
        System.out.println();
        
        System.out.println(n + " sentences generated in " + sentenceRunTime + " ms.");
        
        
    }
    
    
    
}
