package tiralabra.tiralabra.markov.text;
import tiralabra.tiralabra.markov.text.datastructures.DynamicList;
import java.io.File;
import java.io.FileNotFoundException;
import java.util.*;
import tiralabra.tiralabra.markov.text.performance.PerformanceTest;


/**
 *
 * @author ruby
 * This class is responsible for reading the given text file and parsing it into
 * a list of tokens, as well as parsing command line arguments.
 */
public class ParseInput {
    
    
    /**
     * This method takes a file path and generates a list of tokens from the file.
     * Punctuation marks are considered tokens as well.
     * @param filePath path to file the user wishes to use
     * @return a list of words in the file, separated by whitespace
     */
    public static DynamicList<String> readFile(final String filePath) {
        DynamicList<String> tokenList = new DynamicList<>();
        Set<String> uniqueTokens = new HashSet<>();
        try {
            File inputFile = new File(filePath);
            Scanner reader = new Scanner(inputFile);
            while (reader.hasNextLine()) {
                String[] readLine = reader.nextLine().split("\\s+|(?=\\W)");
                for (String token: readLine) {
                    tokenList.add(token);
                    uniqueTokens.add(token);
                }
            }
            reader.close();
            
        } catch (FileNotFoundException e) {
            System.out.println("An error occurred:");
            e.printStackTrace();
        }
        
        System.out.println("Found " + tokenList.size() + " total tokens and " + uniqueTokens.size() + " unique tokens");
        return tokenList;
    }
    
    /**
     * This method parses the parameters given through the command line.
     * @param args arguments given in the command line
     * @return an array of resolved parameter values.
     */
    public static Object[] parseCmdLine(String[] args) {
        
        String filePath = "./fitzgerald_gatsby.txt"; // this is the text we'll be constructing sentences from
        int k = 6; // this value determines how many previous words to take into accocunt
        int l = 15; // this value determines how long the sentence should be
        int n = 10; // this value determines how many sentences we generate at once.
        
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
        
        Object[] params = {filePath, k, l ,n};
        return params;
    }
    
}
