// ListTest.java
// Nikolai Chen
// nmchen
// lab6
// ListTest.java is a test file to test the methods in List.java

public class ListTest {

	public static void main(String[] args) {

		List<Integer> intlist = new List<Integer>();

		List<Integer> anotherList = new List<Integer>();

		// tests isEmpty() and size()

		System.out.println("it is empty: " + intlist.isEmpty());

		System.out.println("size of list: " + intlist.size());

		// should be in the order 25,13,7,12,7
		// tests add()

		intlist.add(1, 7);
		intlist.add(1, 13);
		intlist.add(2, 12);
		intlist.add(1, 25);
		intlist.add(3, 7);

		System.out.println("it is empty: " + intlist.isEmpty());

		System.out.println("size of list: " + intlist.size());

		// tests get()

		System.out.println(intlist.get(1));
		System.out.println(intlist.get(2));
		System.out.println(intlist.get(3));
		System.out.println(intlist.get(4));
		System.out.println(intlist.get(5));

		// should now read 25,13,12
		// tests remove()

		intlist.remove(3);
		intlist.remove(4);

		System.out.println("it is empty: " + intlist.isEmpty());

		System.out.println("size of list: " + intlist.size());

		// tests out toString()

		System.out.println("intList: " + intlist.toString());

		anotherList.add(1,25);
		anotherList.add(2,13);
		anotherList.add(3,12);

		System.out.println("anotherList: " + anotherList.toString());

		// test equals()

		System.out.println("They are equal: " + intlist.equals(anotherList));

		// tests exceptions for add(), get(), and remove()

		// intlist.add(5, 5);

		// System.out.println(intlist.get(10));

		// intlist.remove(0);

		// should now read 25,13,12,5,7,24

		intlist.add(4,5);
		intlist.add(5,7);
		intlist.add(6,24);

		System.out.println("it is empty: " + intlist.isEmpty());

		System.out.println("size of list: " + intlist.size());

		System.out.println("intList: " + intlist.toString());

		System.out.println("anotherList: " + anotherList.toString());

		System.out.println("They are equal: " + intlist.equals(anotherList));

		List<List<Integer>> megalist = new List<List<Integer>>();
		megalist.add(1, intlist);
		megalist.add(2, anotherList);
		System.out.println("The mega list: " + megalist);

		// test removeAll()

		intlist.removeAll();
		System.out.println("it is empty: " + intlist.isEmpty());

		System.out.println("size of list: " + intlist.size());

		System.out.println(intlist);
		System.out.println(megalist);



	}

}