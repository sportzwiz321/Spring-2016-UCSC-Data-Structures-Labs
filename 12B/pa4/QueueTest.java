// QueueTest.java
// Nikolai Chen
// nmchen
// pa4
// test file for Queue.java
// tests the methods/operations for the Queue class

public class QueueTest {

	public static void main(String[] args) {

		Queue jobs = new Queue();

		// tests isEmpty() prints true
		System.out.println(jobs.isEmpty());

		// tests enqueue() and length() prints 2
		jobs.enqueue(new Job(5,3));
		jobs.enqueue(new Job(2,4));
		System.out.println(jobs.length());

		// tests dequeue() prints 0
		jobs.dequeue();
		jobs.dequeue();
		System.out.println(jobs.length());

		// tests peek() prints 7
		jobs.enqueue(new Job(1,7));
		Job a = (Job)jobs.peek();
		System.out.println(a.getDuration());

		// tests dequeueAll() prints 4 and then 0
		jobs.enqueue(new Job(5,3));
		jobs.enqueue(new Job(2,7));
		jobs.enqueue(new Job(4,8));
		System.out.println(jobs.length());
		jobs.dequeueAll();
		System.out.println(jobs.length());

		// tests dequeue()'s QueueEmptyException
		// jobs.dequeue();

		// tests peek()'s QueueEmptyException
		// jobs.peek();

		// tests dequeueAll()'s QueueEmptyException
		// jobs.dequeueAll();

		// tests toString();
		jobs.enqueue(new Job(5,3));
		jobs.enqueue(new Job(2,7));
		jobs.enqueue(new Job(4,8));
		jobs.enqueue(new Job(5,3));
		jobs.enqueue(new Job(2,4));
		jobs.enqueue(new Job(1,7));
		System.out.println(jobs.toString());

	}

}