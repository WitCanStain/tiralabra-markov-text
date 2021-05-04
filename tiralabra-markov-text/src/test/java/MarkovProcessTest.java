
import java.util.ArrayList;
import org.junit.After;
import org.junit.AfterClass;
import org.junit.Before;
import org.junit.BeforeClass;
import org.junit.Test;
import static org.junit.Assert.*;
import tiralabra.tiralabra.markov.text.MarkovProcess;
import tiralabra.tiralabra.markov.text.ParseInput;
import tiralabra.tiralabra.markov.text.Trie;
/**
 *
 * @author ruby
 */
public class MarkovProcessTest {
    ArrayList<String> tokenList;
    Trie trie;
    
    public MarkovProcessTest() {
    }
    
    
    @Before
    public void setUp() {
        tokenList = ParseInput.readFile("../testInputFile.txt");
        trie = new Trie(tokenList, 1);
    }
    
    @After
    public void tearDown() {
    }

    // TODO add test methods here.
    // The methods must be annotated with annotation @Test. For example:
    //
    // @Test
    // public void hello() {}
    @Test
    public void generateSentenceReturnsCorrectLengthString() {
        String sentence = MarkovProcess.generateSentence(trie, 1, 4);
        
        String[] words = sentence.split(" ");
        
        
        assertEquals(4, words.length);
        
    
    }
}
