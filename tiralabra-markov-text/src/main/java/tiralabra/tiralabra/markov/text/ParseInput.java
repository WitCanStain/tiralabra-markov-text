package tiralabra.tiralabra.markov.text;
import java.io.File;
import java.io.FileNotFoundException;
import java.util.*;
import java.util.ArrayList;

/**
 *
 * @author ruby
 */
public class ParseInput {
    
    
    
    public static ArrayList<String> readFile(final String filePath) {
        ArrayList<String> wordList = new ArrayList<>();
        try {
            File inputFile = new File(filePath);
            Scanner reader = new Scanner(inputFile);
            while (reader.hasNextLine()) {
                 Collections.addAll(wordList,reader.nextLine().split("\\s+"));
                
            }
            reader.close();
            System.out.println(wordList);
            
        } catch (FileNotFoundException e) {
            System.out.println("And error occurred:");
            e.printStackTrace();
        }
        
        return wordList;
    }
}
