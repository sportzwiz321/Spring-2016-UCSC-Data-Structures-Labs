public class DictionaryTest {

	public static void main(String args[]) {

		// tests Dictionary constructor

		Dictionary dict = new Dictionary();

		// tests isEmpty() and size()

		System.out.println("isEmpty: " + dict.isEmpty());
		System.out.println("size: " + dict.size());

		// tests insert()

		dict.insert("Halo", "Reach");
		dict.insert("Data", "Structures");
		dict.insert("Alpha", "Male");
		dict.insert("Spartan", "117");
		dict.insert("Master", "Chief");
		dict.insert("John", "Luck");

		System.out.println("isEmpty: " + dict.isEmpty());
		System.out.println("size: " + dict.size());

		// tests lookup()

		System.out.println(dict.lookup("Alpha"));
		System.out.println(dict.lookup("Male"));
		System.out.println(dict.lookup("Data"));

		// tests toString()

		System.out.println("\nDictionary in order:\n" + dict.toString());

		// tests delete()

		dict.delete("Spartan");
		dict.delete("Alpha");

		// tests insert() and delete() exceptions

		// dict.insert("Data", "Bases");
		// dict.delete("Alpha");

		System.out.println("isEmpty: " + dict.isEmpty());
		System.out.println("size: " + dict.size());
		System.out.println("\nDictionary in order:\n" + dict.toString());

		// tests makeEmpty()

		dict.makeEmpty();

		System.out.println("isEmpty: " + dict.isEmpty());
		System.out.println("size: " + dict.size());
		System.out.println("\nDictionary in order:\n" + dict.toString());


	}

}