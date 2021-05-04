package tiralabra.tiralabra.markov.text.datastructures;

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
    /**
     * Add a new item to the list. Grow its size if its current size
     * is exceeded.
     * @param value the item to be added to the list.
     */
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
    
    /**
     * Get the item at the given index
     * @param index index of the item to be retrieved
     * @return the item at the given index
     */
    public T get(int index) {
        if (indexOutOfBounds(index)) {
            System.out.println("get(): index out of bounds.");
            return null;
        }
        final T object = (T)array[index];
        return object;
    }
    
    /**
     * This method sets the value of the list at the given index
     * @param index index where the given item is to be inserted
     * @param value the given item to be inserted
     */
    public void set(int index, T value) {
        if (indexOutOfBounds(index)) {
            System.out.println("set(): index out of bounds.");
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
    
    /**
     * This method checks whether the DynamicList contains the given token
     * @param token token to be checked for existence in the list
     * @return a truth value denoting the existence of the token in the list
     */
    public boolean contains(T token) {
        
        for (Object object: array) {
            if (((T)object).equals(token)) {
                return true;
            }
        }
        return false;
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
