package tiralabra.tiralabra.markov.text;

/**
 *
 * @author ruby
 */
public class DynamicList {
    private Object array[];
    private int lastIndex = 0;
    
    public DynamicList() {
        array = new Object[10];
        
    }
    
    public void add(Object value) {
        
        if (lastIndex < array.length) {
            array[lastIndex] = value;
            lastIndex++;
        } else {
            System.out.println("Growing array!");
            Object newArray[] = new Object[array.length*2];
            for (int i = 0; i < array.length; i++) {
                newArray[i] = array[i];
            }
            newArray[array.length] = value;
            array = newArray;
            lastIndex++;
        }
    }
    
    public Object get(int index) {
        return array[index];
    }
    
    public int size() {
        return lastIndex;
    }
    
}
