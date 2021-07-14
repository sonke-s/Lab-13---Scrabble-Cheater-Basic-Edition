import java.util.Arrays;
import java.util.LinkedList;

/*
 * The Array can store any number of Words, as they are each stored in linkedList
 * which can grow in size and therefore take more words.  
 * @Author: Sönke Schaarschmidt & Jonas Kallwies
 */


public class HashTable {

	LinkedList<String>[] array;
	int arraySize;
	int wordSum;
	LinkedList<String> list = new LinkedList<String>();
	
	public static void main(String[] args) {
		HashTable hash = new HashTable(100);
		
		String[] test = {"Benz", "Baaz", "Baut", "Boden"};
		//Index should be: 99  ,   82  ,   96  ,   88
		
		for(int i = 0; i < test.length; i++) {
			hash.hashFunction2(test[i], hash.array, false);
		}
		hash.printArray(hash.array);
	}
	
	//This just creates the Array for a given size and fills it with empty LinkedLists
	HashTable(int size) {
		
		arraySize = size;
		array = new LinkedList[size];
		for(int i = 0; i < size; i++) {
			LinkedList<String> list = new LinkedList<String>();
			array[i] = list;
		}
	}
	/*
	 * This method iterates over the words passed in as an Array and calculates each words Index.
	 * It then adds the word to the LinkedList at that index position in the array.
	 * The System.out.prints were just to check for the collision handling and the correct 
	 * placement of the words at their respective index. In the end the whole array is printed. 
	 */
	public void hashFunction(String[] words, LinkedList<String>[] array, boolean text) {
		
		for(int i = 0; i < words.length; i++) {
			
			int index = 0;
			String word = words[i];
			index = calculateIndex(word);
			
			if(text == true) {
				System.out.println("|" + word + "|" + " is put in: " + index);
				if(!array[index].isEmpty()) {
					System.out.println("Collision! Added to the LinkedList at index:  " + index);
				}
			}else{
				continue;
			}
			
			array[index].add(word);
		}
		System.out.println();
		printArray(array);
	}
	
	/*
	 * HashFunction but per String not the whole Array
	 */
	public void hashFunction2(String word, LinkedList<String>[] array, boolean text) {
		
		int index = calculateIndex(word);
			
		if(text == true) {
			System.out.println("|" + word + "|" + " is put in: " + index);
			if(!array[index].isEmpty()) {
					System.out.println("Collision! Added to the LinkedList at index:  " + index);
			}
		}
		if(array[index].size() < 16) {
			if(array[index].isEmpty()) {
				array[index].add(word);
			}else if(isPermutation(array[index].getLast(), word)){
				array[index].add(word);
			}
		}
	}
	
	/*
	 * This Method calculates the Index of a word by summing the ASCII codes of each character
	 * in the word and taking the remainder of its modulo with the array size
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
			index = index + code;
			//index = index + code * 33751;
		}
		index = index % array.length;
		return index;
	}
	
	public void printArray(LinkedList<String>[] array) {
		for(int i = 0; i < array.length; i++) {
			String indexTable = "";
			LinkedList<String> list = array[i];
				for(int j = 0; j < list.size(); j++) {
					indexTable = indexTable + " | " + list.get(j);
			}
		System.out.println(i + " ->" + indexTable);
		}
	}
	
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
