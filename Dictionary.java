import java.io.File;
import java.io.FileNotFoundException;
import java.io.FileReader;
import java.io.IOException;
import java.util.*;

public class Dictionary {
	
	private FileReader input;
	private File original;
	private String checkThis = "eisel";
	private Scanner text;
	private HashTable dictionary = new HashTable(1000);
	
	public static void main(String[] args) {
		// TODO Auto-generated method stub
		Dictionary dic = new Dictionary();
		dic.setup();
		
		//System.out.println(dic.dictionary.array[650]);

		dic.dictionary.printArray(dic.dictionary.array);
		
		System.out.println();
		
		System.out.println(dic.lookup(dic.checkThis));
	}

	public void setup() {
		//https://gist.githubusercontent.com/wchargin/8927565/raw/d9783627c731268fb2935a731a618aa8e95cf465/words
		original = new File("/users/sonkeschaarschmidt/downloads/words.txt");
		try {
			input = new FileReader(original);
		} catch (FileNotFoundException e) {
			// TODO Auto-generated catch block
			e.printStackTrace();
		}
		text = new Scanner(input);
		
		while(text.hasNext()) {
			dictionary.hashFunction2(text.next(), dictionary.array, false);
		}
	}
	
	public LinkedList<String> lookup(String word){
		
		int index = dictionary.calculateIndex(word);
		LinkedList<String> list = dictionary.array[index];
		return list;
	}
}
