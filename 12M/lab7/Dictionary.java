// Dictionary.java
// Nikolai Chen
// nmchen
// lab7
// Dictionary.java is a class definition of a Binary Search Tree (BST)
// implementation of a Dictionary where each node contains two String
// variables, a key and a value

public class Dictionary implements DictionaryInterface {

	private class Node {
		String key;
		String value;
		Node left;
		Node right;

		public Node(String k, String v) {
			key = k;
			value = v;
			left = null;
			right = null;
		}

	}

	Node root;
	int numPairs;

	public Dictionary() {

		root = null;
		numPairs = 0;

	}


	// finds a Node if it exists given a key and the root node
	private Node findKey(Node r, String key) {

		if (r != null) {
			if (r.key.compareTo(key) == 0) {
				return r;
			} else if (r.key.compareTo(key) > 0) {
				return findKey(r.left, key);
			} else {
				return findKey(r.right, key);
			}
		} else {
			return null;
		}

	}

	// finds the parent Node in a BST, assuming it exists
	private Node findParent(Node n, Node r) {

		Node p = null;
		if (n != r) {
			p = r;
			while(p.left != n && p.right != n) {
				if (p.key.compareTo(n.key) > 0) {
					p = p.left;
				} else {
					p = p.right;
				}
			}
		}
		return p;

	}

	// finds the left most or "smallest" key Node given its root Node
	private Node findLeftmost(Node r) {

		Node n = r;
		while(n.left != null) {
			n = n.left;
		}
		return n;

	}

	// recursively creates a String Buffer that contains all of the keys and values within the BST
	private StringBuffer printInOrder(Node r, StringBuffer sb) {

		StringBuffer a = new StringBuffer(sb);

		if (r != null) {
			StringBuffer sbl = printInOrder(r.left, a);
			StringBuffer sbr = printInOrder(r.right, a);
			a.append(sbl);
			a.append(r.key + " " + r.value + "\n");
			a.append(sbr);
		}

		return a;

	}

	// deletes all of the Nodes in a BST
	private void deleteAll(Node n) {

		if (n != null) {
			deleteAll(n.left);
			deleteAll(n.right);
			delete(n.key);
		}

	}

	// returns a boolean whether or not the BST is empty
	public boolean isEmpty() {

		return (numPairs == 0);

	}

	// returns an int for the number of pairs in the BST
	public int size() {

		return numPairs;

	}

	// returns the value of a entry given its key, if it exists in the BST
	public String lookup(String key) {

		Node n = findKey(root, key);

		if (n != null) {
			return n.value;
		} else {
			return null;
		}

	}

	// inserts a new entry into the BST assuming the key is unique/has not been inputted already
	public void insert(String key, String value) throws DuplicateKeyException {

		Node n = findKey(root, key);

		if (n != null) {
			throw new DuplicateKeyException("Dictionary Error: insert() called on existing key");
		} else {

			Node a = new Node(key, value);

			if (root == null) {
				root = a;
			} else {
				Node p = root;
				boolean placed = false;
				while(!placed) {
					if (a.key.compareTo(p.key) < 0) {
						if (p.left == null) {
							p.left = a;
							placed = true;
						} else {
							p = p.left;
						}
					} else {
						if (p.right == null) {
							p.right = a;
							placed = true;
						} else {
							p = p.right;
						}
					}
				}
			}

			numPairs++;
			
		}

	}

	// deletes an entry in the BST given its key, assuming it exists already
	public void delete(String key) throws KeyNotFoundException {

		Node n = findKey(root, key);

		if (n == null) {
			throw new KeyNotFoundException("Dictionary Error: delete() called on non-existent key");
		} else {
			if (n.left == null && n.right == null) {
				if (n == root) {
					root = null;
				} else {
					Node p = findParent(n, root);
					if (p.right == n) {
						p.right = null;
					} else {
						p.left = null;
					}
				}
			} else if (n.left != null && n.right == null) {
				if (n == root) {
					root = root.left;
				} else {
					Node p = findParent(n, root);
					if (p.right == n) {
						p.right = n.left;
						n = null;
					} else {
						p.left = n.left;
						n = null;
					}
				}
			} else if (n.left == null && n.right != null) {
				if (n == root) {
					root = root.right;
				} else {
					Node p = findParent(n, root);
					if (p.right == n) {
						p.right = n.right;
						n = null;
					} else {
						p.left = n.right;
						n = null;
					}
				}
			} else {
				Node m = findLeftmost(n.right);
				n.key = m.key;
				n.value = m.value;
				Node p = findParent(m, n);
				if (p.right == m) {
					p.right = m.right;
				} else {
					p.left = m.right;
				}
			}

			numPairs--;

		}

	}

	// makes the entire BST empty
	public void makeEmpty() {

		deleteAll(root);

	}

	// returns a String composed of all of the entries within the BST
	public String toString() {

		StringBuffer keys = new StringBuffer();
		keys = printInOrder(root, keys);
		return new String(keys);

	}

}