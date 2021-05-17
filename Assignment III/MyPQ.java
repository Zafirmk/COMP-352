/**
 * Author - Zafir Khalid (40152164) & Marwa Khalid (40155098)
 * Interface off of which priority queue classes are built
 */
public interface MyPQ<K extends Comparable<K>, V> {
    public int size();
    public boolean isEmpty();
    public void insert(K key, V value);
    public Node<K, V> min();
    public Node<K, V> removeMin();
}
