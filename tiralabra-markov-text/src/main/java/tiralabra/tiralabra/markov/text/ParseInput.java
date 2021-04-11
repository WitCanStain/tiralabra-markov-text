package tiralabra.tiralabra.markov.text;
import java.io.File;
import java.io.FileNotFoundException;
import java.util.*;


/**
 *
 * @author ruby
 */
public class ParseInput {
    private static ArrayList<String> tokenList = new ArrayList<>();
    
    /**
     * This method takes a file path and generates a list of tokens from the file.
     * Punctuation marks are considered tokens as well.
     * @param filePath path to file the user wishes to use
     * @return a list of words in the file, separated by whitespace
     */
    public static ArrayList<String> readFile(final String filePath) {
        
        try {
            File inputFile = new File(filePath);
            Scanner reader = new Scanner(inputFile);
            while (reader.hasNextLine()) {
                String[] readLine = reader.nextLine().split("\\s+|(?=\\W)");
                Collections.addAll(tokenList, readLine);
                
            }
            reader.close();
            System.out.println(tokenList);
            
        } catch (FileNotFoundException e) {
            System.out.println("An error occurred:");
            e.printStackTrace();
        }
        
        return tokenList;
    }
    
    public static ArrayList<String> getTokenList() {
        return tokenList;
    }
}
