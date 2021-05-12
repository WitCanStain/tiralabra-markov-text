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
        
        String filePath = "../fitzgerald_gatsby.txt"; // this is the text we'll be constructing sentences from
        int k = 2; // this value determines how many previous words to take into accocunt
        int l = 20; // this value determines how long the sentence should be
        int n = 10; // this value determines how many sentences we generate at once.
        
        // parsing the command line arguments
        for (int i = 0; i < args.length; i++) {
            if (args[i].equals("-f")) {
                filePath = args[i+1];
            } else if (args[i].equals("-k")) {
                k = Integer.parseInt(args[i+1]);
            } else if (args[i].equals("-l")) {
                l = Integer.parseInt(args[i+1]);
            } else if (args[i].equals("-n")) {
                n = Integer.parseInt(args[i+1]);
            }
        }
        
        System.out.println("Creating trie from file " + filePath + ".");
        DynamicList<String> tokenList = ParseInput.readFile(filePath);
        
        Trie trie = new Trie(tokenList, k);
        
        System.out.println("Generating " + n + " sentences of length " + l + " using the previous " + k + " tokens.\n");
        long startTime = System.nanoTime();
        for (int i = 1; i<=n; i++) {    
            String sentence = MarkovProcess.generateSentence(trie, k, l);
            System.out.println(String.format("%-4s %s" , i + ":",  sentence));    
        }
        long endTime = System.nanoTime();
        long runTime = (endTime-startTime) / 1000000;
        System.out.println();
        
        System.out.println(n + " sentences generated in " + runTime + " ms.");
        
        
    }
    
    
    
}
