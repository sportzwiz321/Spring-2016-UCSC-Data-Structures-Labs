// QueueEmptyException.java
// Nikolai Chen
// nmchen
// pa4
// exception class for an empty Queue

public class QueueEmptyException extends RuntimeException {
	public QueueEmptyException(String s) {
		super(s);
	}
}