/**
 * Authors: Zafir Khalid & Marwa Khalid (40152164 & 40155098)
 * Abstract class implementation of a Hash Map
 * Has a constructor to create a hash map with buckets
 */
import java.util.ArrayList;
@SuppressWarnings("all")
public abstract class AbsHashMap{    

    /**
     * arr - An array of arraylists
     */
    ArrayList<Entry>[] arr;
    /**
     * Constructor to fill arr with buckets
     * @param size - size of the hashmap required
     */
    public AbsHashMap(int size){
        arr = new ArrayList[size];
        for(int i=0; i < size; i++){
            arr[i] = new ArrayList<Entry>();
        }
    }
    //Methods to be implemented
    /**
     * To return the number of entries in the hash map
     * @return - number of entries
     */
    public abstract int size();
    /**
     * Check if hashmap is empty
     * @return - true or false
     */
    public abstract boolean isEmpty();
    /**
     * Return (but not remove) an entry in the hashmap
     * @return - value of the entry
     */
    public abstract String get(int key);
    /**
     * Insert a value into the hashmap
     * @param k - Key of entry
     * @param v - Value of entry
     * @return - Value of replaced entry or null if no replacement
     */
    public abstract String put(int k, String v);
    /**
     * Removes an entry from the hash table
     * @param key - Key of entry to remove
     * @return - The removed entry if any or null
     */
    public abstract String remove(int key);
    /**
     * Method to compress hashcode into an index in the hash map
     * @param hash - the hash generated from .hashCode()
     * @return - the index of insertion
     */
    public abstract int compress(int hash);
}