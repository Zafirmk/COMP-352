@SuppressWarnings("all")
/**
 * Authors - Zafir Khalid (40152164) & Marwa Khalid (40155098)
 * The following class is a sorted array implementation of a priority queue
 */
public class MyPQSortedArray<K extends Comparable<K>, V> implements MyPQ<K, V>{

    // Initializing variables used throughout
    private int size = 1;
    private PQEntry<K, V>[] arr = new PQEntry[size];
    private int entries = 0;
    private int head = 0;

    /**
     * Method to return entries in priority queue
     */
    @Override
    public int size() {
        return entries;
    }

    /**
     * Method that checks if priority queue is empty
     */
    @Override
    public boolean isEmpty() {
        return (entries==0);
    }

    /**
     * Method to insert a key value pair into a priority queue
     */
    @Override
    public void insert(K key, V value) {

        //If empty enter at start
        if (arr[0] == null){
            arr[0] = new PQEntry<K,V>(key, value);
            entries++;
        }

        //If full double size and find correct location to enter
        else if (entries >= arr.length){
            arr = doubleSize();
            int i;
            for (i = entries - 1; (i >= 0 && (int)arr[i].getKey() > (int)key); i--)
                arr[i + 1] = arr[i];
    
            arr[i + 1] = new PQEntry<K,V>(key, value);
            entries++;
        }

        //Find correct position and enter so as to maintain order
        else{
            int i;
            for (i = entries - 1; (i >= 0 && (int)arr[i].getKey() > (int)key); i--)
                arr[i + 1] = arr[i];
    
            arr[i + 1] = new PQEntry<K,V>(key, value);
            entries++;
        }
    }

    /**
     * Method to double the size of the priority queue
     * @return - priority queue with the doubled size
     */
    private PQEntry<K, V>[] doubleSize(){
        PQEntry<K, V>[] toReturn = new PQEntry[arr.length * 2];
        for(int i = 0; i < arr.length; i++){
            toReturn[i] = arr[i];
        }
        return(toReturn);
    }

    /**
     * Return but don't remove the node with the minimum key
     */
    @Override
    public Node<K, V> min() {
        if (!isEmpty()){
            return arr[head];
        }
        else{
            return null;
        }
    }

    /**
     * Return and remove the node with the minimum key
     */
    @Override
    public Node<K, V> removeMin() {
        if(isEmpty()){
            return null;
        }
        else{
            PQEntry<K, V> temp = arr[head];
            arr[head] = null;
            head++;
            entries--;
            if(entries <= 0.25*arr.length){
                arr = halveSize();
                head = 0;
            }
            return temp;
        }

    }

    /**
     * Halve the size of the priority queue to reduce wasted space
     * @return
     */
    private PQEntry<K, V>[] halveSize() {
        int newSize = (int) (arr.length*0.5);
        int index = 0;
        PQEntry<K, V>[] toReturn = new PQEntry[newSize];
        for(int i = 0; i < arr.length; i++){
            if(arr[i] != null){
                toReturn[index] = arr[i];
                index++;
            }
        }
        return(toReturn);
    }
}
