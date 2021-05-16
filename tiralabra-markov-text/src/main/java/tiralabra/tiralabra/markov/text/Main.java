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
        
        String filePath = "./fitzgerald_gatsby.txt"; // this is the text we'll be constructing sentences from
        int k = 2; // this value determines how many previous words to take into accocunt
        int l = 10; // this value determines how long the sentence should be
        int n = 5; // this value determines how many sentences we generate at once.
        
        // parsing the command line arguments
        for (int i = 0; i < args.length; i++) {
            
            switch (args[i]) {
                case "-t":
                    PerformanceTest.runTests();
                    System.exit(0);
                case "-f":
                    filePath = args[i + 1];
                    break;
                case "-k":
                    k = Integer.parseInt(args[i + 1]);
                    break;
                case "-l":
                    l = Integer.parseInt(args[i + 1]);
                    break;
                case "-n":
                    n = Integer.parseInt(args[i + 1]);
                    break;
                default:
                    break;
            }
        }
        
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
