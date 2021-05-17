/**
 * Authors - Zafir Khalid (40152164) & Marwa Khalid (40155098)
 * Node interface used to build PQEntry class for entries into a priority queue
 */
public interface Node<K, V> {
    K getKey();
    V getValue();
}
