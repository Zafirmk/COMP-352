/**
 * Authors - Zafir Khalid (40152164) & Marwa Khalid (40155098)
 * PQEntry object is an entry into the Priority queue
 */
public class PQEntry<K extends Comparable<K>, V> implements Node<K, V> {

    private K key;
    private V value;

    public PQEntry(K key, V value){
        this.key = key;
        this.value = value;
    }

    @Override
    public K getKey() {
        return this.key;
    }

    @Override
    public V getValue() {
        return this.value;
    }
    
    public void setKey(K key){
        this.key = key;
    }

    public void setValue(V value){
        this.value = value;
    }

    public String toString(){
        return("\n" + "Key: " + getKey() + " Value: " + getValue());
    }
}

