// Queue.java
// Nikolai Chen
// nmchen
// pa4
// Queue.java is a LinkedList implemenation of a queue
// It stores objects and can only add to the list at the end
// while removing objects from the front
// you may check if the queue is empty, add an object, remove an object
// remove all objects, check how big the queue is, as well as
// converting the entire queue into a string


public class Queue implements QueueInterface {

	private class Node {

		Object job;
		Node next;

		Node(Object job) {
			this.job = job;
			next = null;
		}

	}

	private Node head;
	private Node tail;
	private int numItems;

	public Queue() {

		head = null;
		tail = null;
		numItems = 0;

	}

	// returns true or false whether the queue is empty or not
	public boolean isEmpty() {

		if(numItems == 0) {
			return true;
		} else {
			return false;
		}

	}

	// returns the length of the queue
	public int length() {

		return numItems;

	}

	// adds a new object to the end of the queue
	public void enqueue(Object newItem) {

		if (numItems == 0) {
			head = new Node(newItem);
			tail = head;
		} else {
			tail.next = new Node(newItem);
			tail = tail.next;
		}
		numItems++;

	}

	// removes and returns the object at the front of the queue if it is not empty
	public Object dequeue() throws QueueEmptyException {
		
		if (numItems == 0) {
			throw new QueueEmptyException("dequeue() called on empty queue");
		} else if (numItems == 1) {
			Node ref = head;
			head = head.next;
			tail = tail.next;
			numItems--;
			return ref.job;
		} else {
			Node ref = head;
			head = head.next;
			numItems--;
			return ref.job;
		}

	}

	// returns the object at the front of the queue if it is not empty
	public Object peek() throws QueueEmptyException {

		if (numItems == 0) {
			throw new QueueEmptyException("peek() called on empty queue");
		} else {
			return head.job;
		}

	}

	// removes all objects in the queue if it is not empty
	public void dequeueAll() throws QueueEmptyException {

		if (numItems == 0) {
			throw new QueueEmptyException("dequeueAll() called on empty queue");
		} else {
			numItems = 0;
			head = null;
			tail = null;
		}

	}

	// appends all objects into a String Buffer which is then used to create a String that is returned
	public String toString() {
		
		if (numItems == 0) {
			return "";
		} else {
			StringBuffer list = new StringBuffer();
			Node ref = head;
			while(ref != null) {
				list.append(ref.job.toString() + " ");
				ref = ref.next;
			}
			return new String(list);
		}

	}

}