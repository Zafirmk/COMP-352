/**
 * Authors - Zafir Khalid (40152164) & Marwa Khalid (40155098)
 * The following class is a unsorted array implementation of a priority queue
 */
@SuppressWarnings("all")
public class MyPQUnsortedArray<K extends Comparable<K>, V> implements MyPQ<K, V>{

    // Initializing variables used throughout
    private int size = 1;
    private PQEntry<K, V>[] arr = new PQEntry[size];
    private int entries = 0;
    private int emptyPosition = 0;

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
        return(entries==0);
    }

    /**
     * Method to insert a key value pair into a priority queue
     */
    @Override
    public void insert(K key, V value) {

        if (emptyPosition >= arr.length){
            arr = doubleSize();
            arr[emptyPosition] = new PQEntry<K,V>(key, value);
            emptyPosition++;
            entries++;
            
        }
        else{
            arr[emptyPosition] = new PQEntry<K,V>(key, value);
            emptyPosition++;
            entries++;
        }
    }


    /**
     * Return but don't remove the node with the minimum key
     */
    @Override
    public Node<K, V> min() {
        int minKey = Integer.MAX_VALUE;
        int index = -1;
        if (isEmpty()){
            return(null);
        }
        else{
            for (int i = 0; i<arr.length; i++){
                if(arr[i] != null){
                    
                    if((int)arr[i].getKey() <= minKey){
                        index = i;
                        minKey = (int)arr[i].getKey();
                    }
                }
            }
        }
        return arr[index];
    }

    /**
     * Return and remove the node with the minimum key
     */
    @Override
    public Node<K, V> removeMin() {

        int minKey = Integer.MAX_VALUE;
        int index = -1;
        if (isEmpty()){
            return(null);
        }
        else{
            for (int i = 0; i<arr.length; i++){
                if(arr[i] != null){
                    if((int)arr[i].getKey() <= minKey){
                        index = i;
                        minKey = (int)arr[i].getKey();
                    }
                }
            }
        }

        Node<K, V> temp = arr[index];
        arr[index] = null;
        entries--;

        if(entries <= 0.25*arr.length){
            arr = halveSize();
        }

        return temp;
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
    
}
