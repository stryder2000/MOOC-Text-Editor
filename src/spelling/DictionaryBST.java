package spelling;

import java.util.TreeSet;

/**
 * @author UC San Diego Intermediate MOOC team
 *
 */
public class DictionaryBST implements Dictionary 
{
   private TreeSet<String> dict;
	
    // TODO: Implement the dictionary interface using a TreeSet.  
 	// You'll need a constructor here
	public DictionaryBST() {
		dict = new TreeSet<String>();
	}
    
    /** Add this word to the dictionary.  Convert it to lowercase first
     * for the assignment requirements.
     * @param word The word to add
     * @return true if the word was added to the dictionary 
     * (it wasn't already there). */
    public boolean addWord(String word) {
    	// TODO: Implement this method
    	word = word.toLowerCase();
    	if(word==null || word=="" || dict.contains(word)) {
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
    
    private boolean isStringLowerCase(String str){
        
        //convert String to char array
        char[] charArray = str.toCharArray();
        
        for(int i=0; i < charArray.length; i++){
            
            //if any character is not in lower case, return false
            if( !Character.isLowerCase( charArray[i] ))
                return false;
        }
        
        return true;
 
    }
	private boolean isStringUpperCase(String str){
	    
	    //convert String to char array
	    char[] charArray = str.toCharArray();
	    
	    for(int i=0; i < charArray.length; i++){
	        
	        //if any character is not in lower case, return false
	        if( !Character.isUpperCase( charArray[i] ))
	            return false;
	    }
	    
	    return true;
	
	}

    /** Is this a word according to this dictionary? */
    public boolean isWord(String s) {
    	//TODO: Implement this method
    	if(isStringUpperCase(s.substring(0, 1)) && isStringLowerCase(s.substring(1))) {
			return true;
		}
		
		if(isStringUpperCase(s)) {
			return true;
		}
		
    	s = s.toLowerCase();
    	if(dict.contains(s)) {
    		return true;
    	}
        return false;
    }

}
