package tiralabra.tiralabra.markov.text;
import tiralabra.tiralabra.markov.text.datastructures.DynamicList;
import java.io.File;
import java.io.FileNotFoundException;
import java.util.*;


/**
 *
 * @author ruby
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
        
        try {
            File inputFile = new File(filePath);
            Scanner reader = new Scanner(inputFile);
            while (reader.hasNextLine()) {
                String[] readLine = reader.nextLine().split("\\s+|(?=\\W)");
                for (String token: readLine) {
                    tokenList.add(token);
                }
                
                
            }
            reader.close();
            
            
        } catch (FileNotFoundException e) {
            System.out.println("An error occurred:");
            e.printStackTrace();
        }
        
        return tokenList;
    }
    
    
    
}
