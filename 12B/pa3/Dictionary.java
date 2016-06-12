/*
/Dictionary.java is a LinkedList implementation of an Dictionary ADT
/This ADT can store pairs of strings (a key and a value) in a LinkedList
/The methods available to a client are checking if the list is empty,
/checking how big the list is, finding the value of a pairing given its key,
/inserting and deleting new entries, emptying the list,
/and converting all of the pairings into one string
*/ 

public class Dictionary implements DictionaryInterface{

	private class Node {
		String key;
		String value;
		Node next;

		Node(String k, String v) {
			key = k;
			value = v;
			next = null;
		}
	}

	private Node head;
	private int numItems;
	private Node tail;

	// returns the Node, if it exists, given its String key
	private Node findKey(String key) {
		Node n = head;
		while(n != null) {
			if (n.key == key) {
				return n;
			} else {
				n = n.next;
			}
		}
		return null;
	}

	public Dictionary() {
		head = null;
		numItems = 0;
		tail = null;
	}

	// returns true or false whethere the list is empty or not
	public boolean isEmpty() {

		return (numItems == 0);

	}

	// returns the number of items in the list
	public int size() {

		return numItems;

	}

	// returns the value of an entry given its key, if it exists
	public String lookup(String key) {

		Node n = findKey(key);

		if(n != null) {
			return n.value;
		} else {
			return null;
		}

	}

	// inserts a new Node entry at the end of the list if, that key is not already in the list
	public void insert(String key, String value) throws DuplicateKeyException {

		if (findKey(key) != null) {
			throw new DuplicateKeyException("insert() called on already existing key");
		} else if (numItems == 0) {
			head = new Node(key, value);
			tail = head;
			numItems++;
		} else {
			tail.next = new Node(key, value);
			tail = tail.next;
			numItems++;
		}

	}

	// removes all pointers to a given Node, if it exists in the list already
	public void delete(String key) throws KeyNotFoundException {

		Node c = findKey(key);
		if (c == null) {
			throw new KeyNotFoundException("delete() called on a non-existent key");
		} else {
			Node p = head;
			if(p == c) {

				head = p.next;

			} else if (tail == c) {

				while(p.next != tail) {
					p = p.next;
				}
				tail = p;
				p.next = null;

			} else {

				while(p.next != c) {
					p = p.next;
				}
				p.next = c.next;
				c.next = null;

			}
			numItems--;
		}

	}

	// makes the list empty, the new head and tail state does not point to anything and there are now 0 items in the list
	public void makeEmpty() {

		head = null;
		numItems = 0;
		tail = null;

	}

	// appends all the keys and values of each node into a StringBuffer which is then returned as a String when completed
	public String toString() {

		Node p = head;
		StringBuffer dictionary = new StringBuffer();
		while(p != null) {
			dictionary.append(p.key).append(" ").append(p.value).append("\n");
			p = p.next;
		}

		return new String(dictionary);
	}

}