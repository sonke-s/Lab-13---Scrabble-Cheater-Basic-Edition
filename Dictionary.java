import java.io.File;
import java.io.FileNotFoundException;
import java.io.FileReader;
import java.util.*;

/*
 * This class creates a dictionary by reading in a file and storing each word in a HashTable.
 * It can return all the words that are at the same index position as the searched for word,
 * or find all the permutations that the hashTable contains of that specified word.
 * @Author: Sönke Schaarschmidt & Jonas Kallwies
 */

public class Dictionary {
	
	private FileReader input;
	private File original;
	private Scanner text;
	private HashTable hashTable;
	
	/*
	 * Creating and setting up a dictionary. Printing out the lookup results
	 * for the searched String word and all of its permutations.
	 */
	public static void main(String[] args) {
		
		Dictionary d = new Dictionary();
		d.setup();
		
		String word = "ealdngi";
		
		//d.searchForPermutations();

		System.out.println("Entries: " + d.hashTable.entries + " | " + "Collisions: " + d.hashTable.collisions);

		System.out.println();
		LinkedList<String> all = d.lookup(word);
		System.out.println("Permutations of (" + word + ") could be in here:");
		System.out.println(all);
		System.out.println();
		System.out.println("All permutations of (" + word + "):");
		LinkedList<String> permutations = d.lookupPermutations(word);
		System.out.println(permutations);
	}

	/*
	 * Setup method creates a hashTable and reads all the words from a file using a Scanner.
	 * Each word is then stored in the hashTable.
	 */
	public void setup() {
		
		hashTable = new HashTable(1000);
		original = new File("/users/sonkeschaarschmidt/downloads/7letters.txt");
		try {
			input = new FileReader(original);
		} catch (FileNotFoundException e) {
			e.printStackTrace();
		}
		text = new Scanner(input);
		
		while(text.hasNext()) {
			hashTable.add(text.next(), false);
		}
	}
	
	/*
	 * Returns a LinkedList<String> containing all words at the index position where
	 * the word searched for would be situated according to its index calculation.
	 * Its possible permutations are in this LinkedList as well if available.
	 */
	public LinkedList<String> lookup(String word){
		
		int index = hashTable.calculateIndex(word);
		LinkedList<String> list = hashTable.array[index];
		return list;
	}
	
	/*
	 * Returnes a LinkedList<String> containing all permutations of the String word.
	 * If the word searched for is in the dictionary, it is not returned, as this
	 * order of the letters is already known to the user.
	 */
	public LinkedList<String> lookupPermutations(String word){
		
		int index = hashTable.calculateIndex(word);
		LinkedList<String> list = hashTable.array[index];
		LinkedList<String> permutations = new LinkedList<String>();
		for(int i = 0; i < list.size(); i++) {
			if(hashTable.isPermutation(word, list.get(i))) {
				if(!word.equals(list.get(i))) {
				permutations.add(list.get(i));
				}
			}
		}
		return permutations;
	}
	
	/*
	 * Returns all Permutations that are available in the Dictionary.
	 * This was only done to find out which ones there are, without
	 * reading the file ourselves
	 */
	public void searchForPermutations(){
		for(int i = 0; i < hashTable.array.length; i++) {
			LinkedList<String> list = hashTable.array[i];
			for(int j = 0; j < list.size(); j++) {
				String word = list.get(j);
				for(int k = 0; k < list.size(); k++) {
					if(hashTable.isPermutation(word, list.get(k))) {
						if(!word.equals(list.get(k))) {
							System.out.println(word + " - " + list.get(k));
						}
					}
				}
			}
		}
	}
}
