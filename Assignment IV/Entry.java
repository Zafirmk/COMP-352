/**
 * Authors: Zafir Khalid & Marwa Khalid (40152164 & 40155098)
 * The following class implementation is an entry into a Hash Table.
 * Keys are stored as integers
 * Values are stored as strings
 */
import java.lang.Math;
@SuppressWarnings("all")
public class Entry{

    private String value;
    private int key;

    /**
     * Constructor to create an entry with a random key
     * @param value
     */
    public Entry(String value){
        this.value = value;
        this.key = (int)Math.floor(Math.random()*(Integer.MAX_VALUE));
    }

    /**
     * Constructor to create an entry with a given key
     * @param value
     */
    public Entry(int key, String value){
        this.value = value;
        this.key = key;
    }

    //Getters and setters
    public String getVal(){
        return(this.value);
    }

    public int getKey(){
        return(this.key);
    }

    public void setVal(String val){
        this.value = val;
    }

    public void setKey(int key){
        this.key = key;
    }

    /**
     * Equals method that checks if two entries are equal by
     * looking at their keys
     */
    @Override
    public boolean equals(Object obj) {
        if (obj == null) {
            return false;
        }

        if (obj.getClass() != this.getClass()) {
            return false;
        }

        final Entry other = (Entry) obj;

        if(other.getKey() != this.key){
            return false;
        }

        return true;

    }

    /**
     * To string which prints the value of the entry
     */
    public String toString(){
        return(this.value);
    }

    /**
     * Hashcode method using polynomial hash code implementation
     * Treats each digit of a key individually and creates sum from a polynomial
     */
    public int hashCode(){
        int a = 33;
        int currval = 0;
        int toReturn = 0;
        String keyString = Integer.toString(key);
        int n = keyString.length();
        for(int i = 0; i < n; i++){
            currval = (int) ((int)keyString.charAt(i)*(Math.pow(a, n-i)));
            toReturn += currval;
        }
        return(toReturn);
    }

}