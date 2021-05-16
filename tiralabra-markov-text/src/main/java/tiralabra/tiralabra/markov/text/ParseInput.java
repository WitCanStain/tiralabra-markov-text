package tiralabra.tiralabra.markov.text;
import tiralabra.tiralabra.markov.text.datastructures.DynamicList;
import java.io.File;
import java.io.FileNotFoundException;
import java.util.*;


/**
 *
 * @author ruby
 * This class is responsible for reading the given text file and parsing it into
 * a list of tokens.
 */
public class ParseInput {
    private static DynamicList<String> tokenList = new DynamicList<>();
    
    /**
     * This method takes a file path and generates a list of tokens from the file.
     * Punctuation marks are considered tokens as well.
     * @param filePath path to file the user wishes to use
     * @return a list of words in the file, separated by whitespace
     */
    public static DynamicList<String> readFile(final String filePath) {
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
    
    
    
}
