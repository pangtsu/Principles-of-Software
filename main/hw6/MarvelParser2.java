package hw6;
import java.io.*;
import java.util.*;

public class MarvelParser2 {

	/** @param: filename The path to the "CSV" file that contains the <hero, book> pairs                                                                                                
        @param: charsInBooks The Map that stores parsed <book, Set-of-heros-in-book> pairs;
        	    usually an empty Map
        @param: chars The Set that stores parsed characters; usually an empty Set.
        @effects: adds parsed <book, Set-of-heros-in-book> pairs to Map charsInBooks;
        		  adds parsed characters to Set chars
        @throws: IOException if file cannot be read of file not a CSV file                                                                                     
	 */
    public static void readData(String filename, Map<String, HashMap<String, Integer>> common) 
    		throws IOException {

    	BufferedReader reader = new BufferedReader(new FileReader(filename));
    	
    	HashMap<String, ArrayList<String>> books = new HashMap<String, ArrayList<String>>();
        String line = null;

        while ((line = reader.readLine()) != null) {
             int i = line.indexOf("\",\"");
             if ((i == -1) || (line.charAt(0)!='\"') || (line.charAt(line.length()-1)!='\"')) {
            	 throw new IOException("File "+filename+" not a CSV (\"HERO\",\"BOOK\") file.");
             }             
             String character = line.substring(1,i);
             String book = line.substring(i+3,line.length()-1);
             
             // add characters to common if the character is not in map
             if (!common.containsKey(character)) {
            	 common.put(character, new HashMap<String, Integer>());
             }
             
             // add book with the character on the same line to books
             // if the book is not in the books
             if (!books.containsKey(book)) {
            	 ArrayList<String> ch = new ArrayList<String>();
            	 ch.add(character);
            	 books.put(book,ch);
             }
             else {
            	 ArrayList<String> chars = books.get(book);
            	 HashMap<String, Integer> count1 = common.get(character);
            	 for (String c : chars) {
            		 if (!count1.containsKey(c)) {
            			 count1.put(c, 1);}
            		 else {
            			 count1.put(c,count1.get(c)+1);}
            	 
            		 @SuppressWarnings("keyfor")
		        		/*@KeyFor("common")*/ String c2 = c;
		        		HashMap<String, Integer> count2 = common.get(c2);
		        		if (!count2.containsKey(character)) {
		        			count2.put(character, 1);}
		        		else {
		        			count2.put(character, count2.get(character) + 1);}
		        	}
            	 chars.add(character);
            	 books.put(book, chars);
            	 
            	 }
            	 
             }
    }
}
    
