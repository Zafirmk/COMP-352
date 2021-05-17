/**
 * Authors: Zafir Khalid & Marwa Khalid (40152164 & 40155098)
 * Concrete class implementation of a Hash Map
 */
public class MyHashMap extends AbsHashMap {

    //Instance variables used throughout
    private int entries = 0;
    private int capacity;
    private int collisions;

    //Constructor that makes use of the superclass constructor
    public MyHashMap(int size) {
        super(size);
        capacity = size;
    }

    /**
     * Return the number of entries
     */
    @Override
    public int size() {
        return entries;
    }

    /**
     * Check if hashmap is empty
     */
    @Override
    public boolean isEmpty() {
        if(entries == 0){
            return true;
        }
        return false;
    }

    /**
     * Return but not remove an entry from a hashmap
     */
    @Override
    public String get(int key) {
        long start_time = System.nanoTime();
        Entry entryToFind = new Entry("temp");
        entryToFind.setKey(key);
        int indexToSearch = compress(entryToFind.hashCode());
        if(arr[indexToSearch] == null){
            return(null);
        }
        else{
            for(int i=0; i < arr[indexToSearch].size(); i++){
                if(arr[indexToSearch].get(i).getKey() == key){
                    long end_time = System.nanoTime();
                    double difference = (end_time - start_time) / 1e6;
                    System.out.println("Time taken (Get): " + difference);
                    System.out.println("Got: " + arr[indexToSearch].get(i).getVal());
                    return(arr[indexToSearch].get(i).getVal());
                }
            }
        }
        long end_time = System.nanoTime();
        double difference = (end_time - start_time) / 1e6;
        System.out.println("Time taken (Get): " + difference);
        System.out.println("Got: " + "NULL");

        return null;
    }


    /**
     * Add an entry into the hashmap
     */
    @Override
    public String put(int k, String v){
        long start_time = System.nanoTime();
        Entry E = new Entry(k, v);
        int indexToAddTo = compress(E.hashCode());

        if(arr[indexToAddTo].size() == 0){
            entries++;
            arr[indexToAddTo].add(E);
            long end_time = System.nanoTime();
            double difference = (end_time - start_time) / 1e6;
            System.out.println("\n");
            System.out.println("Adding " + E.getVal() + " with Key: " + E.getKey() + " to Index: " + indexToAddTo);
            System.out.println("Size of Table: " + capacity);
            System.out.println("Entries in Table: " + entries);
            System.out.println("Time taken (Put): " + difference);
            System.out.println("Collisions: " + collisions);
            System.out.println("Items in Bucket: " + arr[indexToAddTo].size());
            return(null);
            //Empty Bucket Entry
        }
        else{
            if(arr[indexToAddTo].contains(E)){
                String temp = arr[indexToAddTo].get(arr[indexToAddTo].indexOf(E)).getVal();
                arr[indexToAddTo].set(arr[indexToAddTo].indexOf(E), E);
                long end_time = System.nanoTime();
                double difference = (end_time - start_time) / 1e6;
                System.out.println("\n");
                System.out.println("Adding " + E.getVal() + " with Key: " + E.getKey() + " to Index: " + indexToAddTo);
                System.out.println("Size of Table: " + capacity);
                System.out.println("Entries in Table: " + entries);
                System.out.println("Time taken (Put): " + difference);
                System.out.println("Collisions: " + collisions);
                System.out.println("Items in Bucket: " + arr[indexToAddTo].size());
                return(temp);
                //Value replacement
            }
            else{
                collisions++;
                entries++;
                arr[indexToAddTo].add(E);
                long end_time = System.nanoTime();
                double difference = (end_time - start_time) / 1e6;
                System.out.println("\n");
                System.out.println("Adding " + E.getVal() + " with Key: " + E.getKey() + " to Index: " + indexToAddTo);
                System.out.println("Size of Table: " + capacity);
                System.out.println("Entries in Table: " + entries);
                System.out.println("Time taken (Put): " + difference);
                System.out.println("Collisions: " + collisions);
                System.out.println("Items in Bucket: " + arr[indexToAddTo].size());
                return(null);
                //Same bucket different key
            }
        }

    }

    /**
     * Remove an entry from the hash map
     */
    @Override
    public String remove(int key) {
        long start_time = System.nanoTime();
        Entry entryToRemove = new Entry("temp");
        entryToRemove.setKey(key);
        int indexToRemoveFrom = compress(entryToRemove.hashCode());
        if(arr[indexToRemoveFrom].size() > 0){
            for (int i = 0; i < arr[indexToRemoveFrom].size(); i++){
                if(arr[indexToRemoveFrom].get(i).getKey() == key){
                    String temp = arr[indexToRemoveFrom].get(i).getVal();
                    arr[indexToRemoveFrom].remove(i);
                    entries--;
                    long end_time = System.nanoTime();
                    double difference = (end_time - start_time) / 1e6;
                    System.out.println("Time taken (Remove): " + difference);
                    System.out.println("Removed: " + temp);
                    return temp;
                }
            }
        }
        long end_time = System.nanoTime();
        double difference = (end_time - start_time) / 1e6;
        System.out.println("Time taken (Remove): " + difference);
        System.out.println("REMOVED: " + "NULL_REMOVE");
        return null;
    }

    /**
     * Generate an index to add to
     */
    @Override
    public int compress(int hash) {
        return Math.abs(hash % capacity);
    }    
}