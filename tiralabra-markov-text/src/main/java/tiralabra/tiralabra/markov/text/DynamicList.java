package tiralabra.tiralabra.markov.text;

/**
 *
 * @author ruby
 */
public class DynamicList<T> {
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
        if (indexOutOfBounds(index)) {
            System.out.println("Index out of bounds.");
            return null;
        }
        final T object = (T)array[index];
        return object;
    }
    
    public void set(int index, int value) {
        if (indexOutOfBounds(index)) {
            return;
        }
        array[index] = value;
    }
    
    /**
     * This function returns the index of the given string token if it exists, or -1
     * otherwise. Only works for DynamicLists of type String.
     * @param token token to be searched
     * @return index of token if found or -1 otherwise
     */
    public int indexOf(T token) {
        for (int i = 0; i <= lastIndex; i++) {
            if (((T)array[i]).equals(token)) {
                return i;
            }
        }
        return -1;
    }
    
    public int size() {
        return lastIndex;
    }
    
    public int internalArraySize() {
        return array.length;
    }
    
    private boolean indexOutOfBounds(int index) {
        return index < 0 || index > lastIndex;
    }
}
