// List.java
// Nikolai Chen
// nmchen
// lab6
// List.java is a Generic implemenation of a LinkedList
// This will allow the creation of any type of list containing any one uniform data type.

@SuppressWarnings("overrides")
public class List<T> implements ListInterface<T>{

	private class Node {

		private T item;
		private Node next;

		public Node(T item) {

			this.item = item;
			next = null;

		}

	}

	private Node head;
	private Node tail;
	private int numItems;

	public List() {

		head = null;
		tail = null;
		numItems = 0;

	}


	// returns a Node in the List given its index
	private Node find(int index) {
		
		Node n = head;
		if (n == null) {
			return null;
		} else {
			for (int i = 1; i < index; i++) {
				n = n.next;
			}
			return n;
		}

	}

	// returns whether the List is empty or not
	public boolean isEmpty() {

		return (numItems == 0);

	}

	// returns the number of elements in the List
	public int size() {
		return numItems;
	}


	// returns the value of an item in the List, given its index if it is not out of bounds
	public T get(int index) throws ListIndexOutOfBoundsException {

		if (index > numItems || index < 1) {
			throw new ListIndexOutOfBoundsException("List Error: get() called on invalid index: " + index);
		} else {
			Node n = find(index);
			return n.item;
		}

	}

	// adds a new Generic item into the list, given that its index is not out of bounds
	public void add(int index, T newItem) throws ListIndexOutOfBoundsException {

		if (index > (numItems + 1) || index < 1) {
			throw new ListIndexOutOfBoundsException("List Error: add() called on invalid index: " + index);
		} else {
			Node n = new Node(newItem);
			if (numItems == 0) {
				head = n;
				tail = n;
			} else if (index == 1) {
				n.next = head;
				head = n;
			} else if (index == (numItems + 1)) {
				tail.next = n;
				tail = tail.next;
			} else {
				Node c = find(index);
				Node p = head;
				while(p.next != c) {
					p = p.next;
				}
				p.next = n;
				n.next = c;
			}
			numItems++;
		}

	}

	// removes a Generic item from the list, given that it is passed a valid index
	public void remove(int index) throws ListIndexOutOfBoundsException {

		if (index > numItems || index < 1) {
			throw new ListIndexOutOfBoundsException("List Error: remove called on invalid index: " + index);
		} else {
			Node n = head;
			if (numItems == 1) {
				head = null;
				tail = null;
			} else if (index == 1) {
				head = head.next;
				n.next = null;
			} else if (index == numItems) {
				while(n.next != tail) {
					n = n.next;
				}
				tail = n;
				tail.next = null;
			} else {
				Node c = find(index);
				while(n.next != c) {
					n = n.next;
				}
				n.next = c.next;
				c = null;
			}
			numItems--;
		}

	}

	// resets the List to it's original state, empty
	public void removeAll() {

		head = null;
		tail = null;
		numItems = 0;

	}

	// converts all of the Generic values in the List into a string
	public String toString() {

		StringBuffer sb = new StringBuffer();
		Node n = head;
		while(n != null) {
			sb.append(n.item  + " ");
			n = n.next;
		}
		return new String(sb);

	}

	// returns whether or not the List is exactly equal to another passed in Object
	public boolean equals(Object rhs) {
		
		boolean eq = false;
		List<T> x = null;
		Node a = null;
		Node b = null;

		if (this.getClass() == rhs.getClass()) {
			x = (List<T>)rhs;
			eq = (this.numItems == x.numItems);
			a = this.head;
			b = x.head;
			while(eq && a != null) {
				eq = (a.item == b.item);
				a = a.next;
				b = b.next;
			}
		}
		return eq;

	}

}