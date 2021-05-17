/**
 * Authors: Zafir Khalid & Marwa Khalid (40152164 & 40155098)
 * Driver file to test the hashmap
 */
import java.util.ArrayList;
import java.util.Random;

public class Driver {
    
    /**
     * Validation method as mentioned in the assignment document
     */
    public static void validate(){
        ArrayList<Entry> Data = new ArrayList<Entry>();
        
        //Populate the data array list
        for(int i = 0; i < 50; i++){
            Entry toAdd = new Entry(getSaltString());
            Data.add(toAdd);
        }

        //Create hash map of 100 capacity
        MyHashMap hashTable = new MyHashMap(100);
        
        //Add to hash map
        for(int i = 0; i < Data.size(); i++){
            hashTable.put(Data.get(i).getKey(), Data.get(i).getVal());
        }
        System.out.println("~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~");

        //Get from hash map
        for(int i = 0; i < Data.size(); i++){
            System.out.println("Tried to GET: " + Data.get(i).getVal());
            hashTable.get(Data.get(i).getKey());
            System.out.println("\n");
        }
        System.out.println("~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~");

        //Delete 25 entries from hash map
        for(int i=0; i<Data.size()/2; i++){
            System.out.println("Tried to REMOVE: " + Data.get(i).getVal());
            hashTable.remove(Data.get(i).getKey());
            System.out.println("\n");
        }
        System.out.println("~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~");

        //Get all 50 values from hash map after deletion
        for(int i = 0; i < Data.size(); i++){
            System.out.println("Tried to GET: " + Data.get(i).getVal());
            hashTable.get(Data.get(i).getKey());
            System.out.println("\n");
        }
    }

    /**
     * Method as mentioned in the assignment document
     * Swap loop values to any value (n) to test speed of insertion
     */
    public static void experiment_interpret(){

        MyHashMap hashTable = new MyHashMap(100);
        ArrayList<Entry> Data = new ArrayList<Entry>();
        for(int i = 0; i < 10; i++){
            Entry toAdd = new Entry(getSaltString());
            Data.add(toAdd);
        }

        for(int i = 0; i < 10; i++){
            hashTable.put(Data.get(i).getKey(), Data.get(i).getVal());
        }

    }


    /**
     * Method to generate a random string as an entry value
     * @return random string of length 5
     */
    public static String getSaltString() {
        String SALTCHARS = "ABCDEFGHIJKLMNOPQRSTUVWXYZ1234567890";
        StringBuilder salt = new StringBuilder();
        Random rnd = new Random();
        while (salt.length() < 5) { // length of the random string.
            int index = (int) (rnd.nextFloat() * SALTCHARS.length());
            salt.append(SALTCHARS.charAt(index));
        }
        String saltStr = salt.toString();
        return saltStr;

    }
    
    public static void main(String[] args) {
        validate();
        // experiment_interpret();
    }
}
