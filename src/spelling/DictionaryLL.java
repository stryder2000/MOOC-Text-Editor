package spelling;

import java.util.LinkedList;
import java.util.ListIterator;

/**
 * A class that implements the Dictionary interface using a LinkedList
 *
 */
public class DictionaryLL implements Dictionary 
{

	private LinkedList<String> dict;
	
    // TODO: Add a constructor
	public DictionaryLL() {
		dict = new LinkedList<String>();
	}

    /** Add this word to the dictionary.  Convert it to lowercase first
     * for the assignment requirements.
     * @param word The word to add
     * @return true if the word was added to the dictionary 
     * (it wasn't already there). */
    public boolean addWord(String word) {
    	// TODO: Implement this method
    	if(word==null || word.equals("")) {
    		return false;
    	}
//    	word = word.toLowerCase();
//    	ListIterator<String> it = dict.listIterator();
//    	
//    	while(it.hasNext()) {
//    		if(it.next().contentEquals(word)) {
//    			return false;
//    		}
//    	}
    	if(dict.contains(word)) {
    		return false;
    	}
    	
    	dict.add(word);
        return true;
    }


    /** Return the number of words in the dictionary */
    public int size()
    {
        // TODO: Implement this method
        return dict.size();
    }

    /** Is this a word according to this dictionary? */
    public boolean isWord(String s) {
        //TODO: Implement this method
    	if(s.equals("") || s==null) {
    		return false;
    	}
    	s = s.toLowerCase();
//    	ListIterator<String> it = dict.listIterator();
//    	
//    	while(it.hasNext()) {
//    		if(it.next().contentEquals(s)) {
//    			return true;
//    		}
//    	}
    	if(dict.contains(s)) {
    		return true;
    	}
        return false;
    }

    
}
