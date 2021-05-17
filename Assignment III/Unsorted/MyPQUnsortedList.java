import java.util.Comparator;
@SuppressWarnings("all")

public class MyPQUnsortedList<K extends Comparable<K>, V> {
	
	// CREATING NODE CLASS
	class Node<K extends Comparable<K>, V> implements Comparator<Node<K, V>> {
		private K key; // KEY TO STORE
		private V value; // VALUE FOR QUEUE
		private Node<K, V> next; // POINTER TO NEXT NODE
		private Node<K, V> prev; // POINTER TO PREVIOUS NODE

		// PARAMETRIZED CONSTRUCTOR
		public Node(K key, V value) {
			this.key = key; // STORING PASSED KEY
			this.value = value; // STORING VALUE
		}

		@Override
		// METHOD TO COMPARE KEYS
		public int compare(Node<K, V> o1, Node<K, V> o2) {
			return o1.key.compareTo(o2.key); // RETURNS POSITIVE, ZERO OR NEGATIVE
			// ZERO MEANS EQUALS, POSITIVE MEANS GREATER, NEGATIVE MEANS LESSER
		}
	} // END OF NODE CLASS

	private Node<K, V> head = null; // HEAD IS NULL
	private Node<K, V> tail = null; // TAIL IS NULL
	private int size = 0; // SIZE IS ZERO

	// METHOD TO ADD ELEMENTS
	public void insert(K k, V v) {
		Node<K, V> n = new Node<K, V>(k, v); // CREATING NODE

		if (isEmpty()) { // IF LIST IS EMPTY
			head = tail = n; // ADDING NODE
			head.prev = null; // POINTING TO NULL
			tail.next = null; // POINTING TO NULL
		} else { // LIST IS NOT EMPTY
			tail.next = n; // ADDING NODE AT THE END
			n.prev = tail; // POINTING TO OLD TAIL
			tail = n; // TAIL BECOMES NEW NODE
			tail.next = null; // POINTING TO NULL
		}
		size++; // INCREASING SIZE
	}

	// METHOD TO RETURN MINIMAL KEY OF THE LIST
	public Node<K, V> min() {
		Node<K, V> pointer = head; // POINTING TO HEAD
		Node<K, V> toReturn = head.next; // POINTING TO NODE AFTER HEAD
		
		if (!isEmpty()) { // IF LIST IS NOT EMPTY
			while (pointer != null) { // LOOP THROUGH LIST
				if (pointer.compare(toReturn, pointer) > 0) { // IF KEY IS GREATER THAN NEXT KEY
					toReturn = pointer; // NODE TO RETURN BECOMES THE NODE THE POINTER IS AT
				}
				pointer = pointer.next; // MOVING TO NEXT NODE
			}
			return toReturn; // RETURNING NODE
		}
		return null; // ELSE RETURN NULL
	}

	// METHOD TO REMOVE THE MINIMAL KEY
	public Node<K, V> removeMin() {
		Node<K, V> pointer = head; // POINTING TO HEAD
		Node<K, V> toReturn = head.next; // POINTING TO NODE AFTER HEAD

		if (!isEmpty()) { // IF LIST IS NOT EMPTY
			
			/* IF SIZE IS 1 */
			if (size == 1) {
				head = null; // HEAD BECOMES NULL
				tail = null; // TAIL BECOMES NULL
			} else {
				
				/* LOOPING THROUGH LIST */
				while (pointer != null) {
					if (pointer.compare(toReturn, pointer) > 0) { // IF KEY IS GREATER THAN NEXT KEY
						toReturn = pointer; // NODE TO RETURN BECOMES THE NODE THE POINTER IS AT
					}
					pointer = pointer.next; // MOVING TO NEXT NODE
				}
				
				/* REMOVE FROM TAIL */
				if (toReturn == tail) { // IF WE HAVE TO REMOVE TAIL
					tail = tail.prev; // REPLACING TAIL WITH 2ND TO LAST NODE
					tail.next = null; // TAIL POINTING TO NULL
				}
				
				/* REMOVE FROM HEAD */ 
				else if (toReturn == head) {
					head.next.prev = null; // POINTING TO PREVIOUS NULL
					head = head.next; // MOVING TO NEXT NODE
				}
				
				/* REMOVE FROM INSIDE */
				else {
					Node<K, V> removeNode = toReturn; // STORING NODE TO REMOVE
					removeNode.prev.next = removeNode.next; // MOVING POINTER TO NEXT NODE
					removeNode.next.prev = removeNode.prev; // POINTING TO PREVIOUS ONE	
				}
			}
			size--; // DECREMENTING SIZE
			return toReturn; // RETURNING NODE
		}
		return null; // ELSE RETURN NULL
	}

	// METHOD FOR SIZE OF LIST
	public int size() {
		return size; // RETURNING SIZE
	}

	// METHOD TO CHECK IF LIST IS EMPTY
	public boolean isEmpty() {
		return (size == 0); // RETURNS TRUE OR FALSE
	}

	// METHOD TO DISPLAY QUEUE
	public void display() {
		int i = 1; // ELEMENT COUNTER
		Node<K, V> pointer = head; // POINTING TO HEAD
		
		while (pointer != null) { // LOOPING THROUGH LIST
			System.out.println("Element " + i + " has key " + pointer.key + " and a value of " + pointer.value + ".");
			pointer = pointer.next; // MOVING TO NEXT NODE
			i++; // INCREMENTING
		}
	}
}