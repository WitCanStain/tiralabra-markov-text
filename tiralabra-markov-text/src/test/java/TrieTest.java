
import org.junit.After;
import org.junit.AfterClass;
import org.junit.Before;
import org.junit.BeforeClass;
import org.junit.Test;
import static org.junit.Assert.*;
import java.util.ArrayList;
import tiralabra.tiralabra.markov.text.datastructures.Trie;
import tiralabra.tiralabra.markov.text.datastructures.TrieNode;
import tiralabra.tiralabra.markov.text.ParseInput;
import tiralabra.tiralabra.markov.text.datastructures.DynamicList;
/**
 *
 * @author ruby
 */
public class TrieTest {
    DynamicList<String> tokenList;
    Trie trie;
    public TrieTest() {
    }
    
    
    
    @Before
    public void setUp() {
        tokenList = ParseInput.readFile("../testInputFile.txt");
        trie = new Trie(tokenList, 2);
    }
    
    @After
    public void tearDown() {
    }

    
     @Test
     public void trieConstructorCreatesTrie() {
         
         assertTrue(trie instanceof Trie);
         
     }
     @Test
     public void getRootReturnsRoot() {
         assertTrue(trie.getRoot() instanceof TrieNode);
         
     }
     @Test
     public void insertInsertsSequence()  {
         DynamicList<String> seq = new DynamicList<>();
         seq.add("testInputOne");
         seq.add("testInputTwo");
         seq.add("testInputThree");
         String[] seq2 = {"testInputOne", "testInputTwo", "testInputThree"};
             
         
         trie.insert(seq2);
         TrieNode node = trie.getNodeFromSequence(seq, 2);
         assertTrue(node instanceof TrieNode);
     }
     
     @Test
     public void getNodeFromSequenceReturnsCorrectNode() {
         DynamicList<String> seq = new DynamicList<>();
         seq.add("testInputOne");
         seq.add("testInputTwo");
         seq.add("testInputThree");
         String[] seq2 = {"testInputOne", "testInputTwo", "testInputThree"};
             
         
         trie.insert(seq2);
         TrieNode node = trie.getNodeFromSequence(seq, 2);
         System.out.println(node.getChildTokens());
         assertTrue(node.getChildTokens().contains("testInputThree"));
         
         
     }


}
