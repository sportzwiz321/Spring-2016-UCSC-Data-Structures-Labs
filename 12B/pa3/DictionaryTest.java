public class DictionaryTest {

	public static void main(String[] args) {
		Dictionary d = new Dictionary();

		// tested if the dictionary was empty or not
		// System.out.println(d.isEmpty());

		// prints out the size of the Dictionary
		// System.out.println(d.size());

		// tests the insert, size, and lookup methods
		// d.insert("pikachu", "raichu");
		// d.insert("charmander", "charizard");
		// System.out.println(d.size());
		// System.out.println(d.lookup("charmander"));

		// tests the delete method
		// d.insert("sorry", "whammie");
		// d.insert("league", "of legends");
		// d.delete("league");
		// System.out.println(d.size());
		// System.out.println(d.lookup("league"));

		// tests the makeEmpty method
		// d.insert("trollololol", "ucsc");
		// d.insert("ucsc", "slugs");
		// d.insert("mens", "vball");
		// d.makeEmpty();
		// System.out.println(d.size());
		// System.out.println(d.lookup("ucsc"));

		// tests the toString method
		// d.insert("pikachu", "raichu");
		// d.insert("charmander", "charizard");
		// d.insert("sorry", "whammie");
		// d.insert("league", "of legends");
		// d.insert("trollololol", "ucsc");
		// d.insert("ucsc", "slugs");
		// d.insert("mens", "vball");
		// System.out.println(d.toString());

		// test the KeyNotFoundException
		// d.insert("bulbasaur", "squirtle");
		// d.delete("charmeleon");

		// tests the DuplicateKeyException
		// d.insert("cyndaquil", "typhlosion");
		// d.insert("cyndaquil", "quilava");

		System.out.println();
	}

}