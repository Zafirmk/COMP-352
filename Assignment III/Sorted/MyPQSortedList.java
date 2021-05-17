import java.util.Comparator;
@SuppressWarnings("all")

public class MyPQSortedList<K extends Comparable<K>, V>{
	
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
			Node<K, V> pointer = head; // POINTING TO HEAD
			boolean insertAtTail = true; // BOOLEAN FOR INSERTING AT TAIL

			if (isEmpty()) { // IF LIST IS EMPTY
				head = tail = n; // ADDING NODE
				head.prev = null; // POINTING TO NULL
				tail.next = null; // POINTING TO NULL
			} else { // IF LIST IS NOT EMPTY
				while (pointer != null && n.compare(n, pointer) < 0) { // LOOPING THROUGH LIST
					pointer = pointer.next; // MOVING TO NEXT NODE
				}

				if (pointer != null) { // IF NOT NULL
					/* ADDING AT HEAD */
					if (pointer == head) {
						n.next = head; // POINTING TO HEAD
						n.next.prev = n; // POINTING TO NODE TO ADD
						head = n; // HEAD BECOMES NODE

					}

					/* INSERT IN BETWEEN */
					else {
						Node<K, V> temp = pointer.prev; // STORING PREVIOUS NODE
						n.next = pointer; // POINTING TO NEXT NODE
						n.prev = temp; // POINTING TO PREVIOUS NODE
						pointer.prev = n; // POINTING TO PREVIOUS NODE
						temp.next = n; // POINTING TO NEXT NODE

					}
					insertAtTail = false; // BECOMES FALSE
				}

				/* ADDING AT TAIL */
				if (insertAtTail) { // IF TRUE
					tail.next = n; // ADDING NODE AT THE END
					n.prev = tail; // POINTING TO OLD TAIL
					tail = n; // TAIL BECOMES NEW NODE
					tail.next = null; // POINTING TO NULL
				}
			}
			size++; // INCREASING SIZE
		}
		
		// METHOD TO RETURN MINIMAL KEY OF THE LIST
		public Node<K, V> min() {
			if (!(isEmpty())) { // IF LIST IS NOT EMPTY
				return tail; // RETURNING TAIL NODE
			}
			return null; // ELSE RETURN NULL
		}
		
		// METHOD TO REMOVE THE MINIMAL KEY
		public Node<K, V> removeMin() {
			if (!(isEmpty())) { // IF LIST IS NOT EMPTY
				Node<K, V> oldTail = tail; // STORING TAIL
				
				if (size == 1) { // IF SIZE IS 1
					head = null; // HEAD BECOMES NULL
					tail = null; // TAIL BECOMES NULL
					
				} else { // IF SIZE IS GREAT THAN 1
					tail = tail.prev; // REPLACING TAIL WITH 2ND TO LAST NODE
					tail.next = null; // TAIL POINTING TO NULL
				}
				size--; // DECREMENTING SIZE
				return oldTail; // RETURNING REMOVED NODE
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