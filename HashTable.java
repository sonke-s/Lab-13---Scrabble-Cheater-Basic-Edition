import java.util.Arrays;
import java.util.LinkedList;

/*
 * This class creates a HashTable which consists of an Array and contains a LinkedList<String> 
 * at each index position of the Array. The collision is managed through the use of LinkedLists.
 * @Author: Sönke Schaarschmidt & Jonas Kallwies
 */

public class HashTable {

	LinkedList<String>[] array;
	int arraySize;
	int wordSum;
	LinkedList<String> list = new LinkedList<String>();
	
	//This just creates the Array as the HashTable for a 
	//given size and fills it with empty LinkedLists
	HashTable(int size) {
		
		arraySize = size;
		array = new LinkedList[size];
		for(int i = 0; i < size; i++) {
			LinkedList<String> list = new LinkedList<String>();
			array[i] = list;
		}
	}
	
	/*
	 * This method takes a word as a parameter and calculates its Index.
	 * It then adds the word to the LinkedList at that index position in the array.
	 * The System.out.prints are just to check for the collision handling and the correct 
	 * placement of the words at their respective index, and can optionally be
	 * turned off or on. In the end the whole array is printed. 
	 */
	public void add(String word, LinkedList<String>[] array, boolean text) {
		
		int index = calculateIndex(word);
			
		if(text == true) {
			System.out.println("|" + word + "|" + " is put in: " + index);
			if(!array[index].isEmpty()) {
					System.out.println("Collision! Added to the LinkedList at index:  " + index);
			}
		}
		System.out.println();
		array[index].add(word);
	}
	
	/*
	 * This method calculates the Index of a word by summing the ASCII codes of each character
	 * in the word multiplied with a large prime number. The prime number and taking the remainder of its modulo with the array size
	 * Its one way of calculating an index. Also needed for getting an element in the array
	 * without knowing its index. 
	 */
	public int calculateIndex(String word) {
		
		word = word.toLowerCase();
		
		int index = 0;
		int code = 0;
		
		char[] c = word.toCharArray();
		for(int i = 0; i < c.length; i++) {
			code = c[i];
			index = index + code * 33751;
		}
		index = index % array.length;
		return index;
	}
	
	/*
	 * Checks whether two words are permutations of each other
	 */
	public boolean isPermutation(String one, String two) {
		
		//normalize Strings
		one = one.toLowerCase();
		two = two.toLowerCase();
		
		if(one.length() != two.length()) {
			return false;
		}
		
		//String to character Array
		char[] cOne = one.toCharArray();
		char[] cTwo = two.toCharArray();
		
		//sort both Arrays
		Arrays.sort(cOne);
		Arrays.sort(cTwo);
		
		//Compare them
		for(int i = 0; i < one.length(); i++) {
			if(cOne[i] != cTwo[i]){
				return false;
			}
		}
		return true;
	}
}
