package spelling;

import java.util.List;
import java.util.Set;
import java.util.ArrayList;
import java.util.Iterator;
import java.util.LinkedList;

/** 
 * An trie data structure that implements the Dictionary and the AutoComplete ADT
 * @author You
 *
 */
public class AutoCompleteDictionaryTrie implements  Dictionary, AutoComplete {

    private TrieNode root;
    private int size;
    

    public AutoCompleteDictionaryTrie()
	{
		root = new TrieNode();
		size = 0;
	}
	
	
	/** Insert a word into the trie.
	 * For the basic part of the assignment (part 2), you should convert the 
	 * string to all lower case before you insert it. 
	 * 
	 * This method adds a word by creating and linking the necessary trie nodes 
	 * into the trie, as described outlined in the videos for this week. It 
	 * should appropriately use existing nodes in the trie, only creating new 
	 * nodes when necessary. E.g. If the word "no" is already in the trie, 
	 * then adding the word "now" would add only one additional node 
	 * (for the 'w').
	 * 
	 * @return true if the word was successfully added or false if it already exists
	 * in the dictionary.
	 */
	public boolean addWord(String word)
	{
	    //TODO: Implement this method.
		if(word==null || word=="") {
			return false;
		}
		word = word.toLowerCase();
		TrieNode curr = root;
		int i;
		for(i=0; i<word.length(); i++) {
			curr.insert(word.charAt(i));
			curr = curr.getChild(word.charAt(i));
		}
		
		if(curr.endsWord()) {
			return false;
		}else {
			curr.setEndsWord(true);
			++size;
			return true;
		}
	}
	
	/** 
	 * Return the number of words in the dictionary.  This is NOT necessarily the same
	 * as the number of TrieNodes in the trie.
	 */
	public int size()
	{
	    //TODO: Implement this method
	    return size;
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
	/** Returns whether the string is a word in the trie, using the algorithm
	 * described in the videos for this week. */
	@Override
	public boolean isWord(String s) 
	{
	    // TODO: Implement this method
		if(s==null || s=="") {
			return false;
		}
		
		if(isStringUpperCase(s.substring(0, 1)) && isStringLowerCase(s.substring(1))) {
			return true;
		}
		
		if(isStringUpperCase(s)) {
			return true;
		}
		
		s = s.toLowerCase();
		
		TrieNode curr = root;
		int i;
		for(i=0; i<s.length(); i++) {
			curr = curr.getChild(s.charAt(i));
			if(curr==null) {
				return false;
			}
		}
		if(curr.endsWord()) {
			return true;
		}else {
			return false;
		}
	}

	/** 
     * Return a list, in order of increasing (non-decreasing) word length,
     * containing the numCompletions shortest legal completions 
     * of the prefix string. All legal completions must be valid words in the 
     * dictionary. If the prefix itself is a valid word, it is included 
     * in the list of returned words. 
     * 
     * The list of completions must contain 
     * all of the shortest completions, but when there are ties, it may break 
     * them in any order. For example, if there the prefix string is "ste" and 
     * only the words "step", "stem", "stew", "steer" and "steep" are in the 
     * dictionary, when the user asks for 4 completions, the list must include 
     * "step", "stem" and "stew", but may include either the word 
     * "steer" or "steep".
     * 
     * If this string prefix is not in the trie, it returns an empty list.
     * 
     * @param prefix The text to use at the word stem
     * @param numCompletions The maximum number of predictions desired.
     * @return A list containing the up to numCompletions best predictions
     */@Override
     public List<String> predictCompletions(String prefix, int numCompletions) 
     {
    	 // TODO: Implement this method
    	 // This method should implement the following algorithm:
    	 // 1. Find the stem in the trie.  If the stem does not appear in the trie, return an
    	 //    empty list
    	 // 2. Once the stem is found, perform a breadth first search to generate completions
    	 //    using the following algorithm:
    	 //    Create a queue (LinkedList) and add the node that completes the stem to the back
    	 //       of the list.
    	 //    Create a list of completions to return (initially empty)
    	 //    While the queue is not empty and you don't have enough completions:
    	 //       remove the first Node from the queue
    	 //       If it is a word, add it to the completions list
    	 //       Add all of its child nodes to the back of the queue
    	 // Return the list of completions
    	if(prefix==null || prefix=="") {
    		return null;
 		}
    	boolean upper = false;
    	if(isStringUpperCase(prefix)) {
    		upper = true;
    	}
 		prefix = prefix.toLowerCase();
 		TrieNode curr = root;
 		LinkedList<TrieNode> nodes = new LinkedList<TrieNode>();
 		List<String> str = new ArrayList<String>();
 		
 		int i;
 		for(i=0; i<prefix.length(); i++) {
 			curr = curr.getChild(prefix.charAt(i));
 			if(curr==null) {
 				return str;
 			}
 		}
    	nodes.add(curr);
    	
    	while(nodes.isEmpty()==false && numCompletions>0) {
    		curr = nodes.pollFirst();
    		
    		Set<Character> s = curr.getValidNextCharacters();
    		Iterator<Character> it = s.iterator();
    		
    		while(it.hasNext()) {
    			nodes.add(curr.getChild(it.next()));
    		}
    		
    		if(curr.endsWord()) {
    			if(upper) {
    				str.add(curr.getText().toUpperCase());
    			}else {
    				str.add(curr.getText());
    			}
    			numCompletions--;
    		}
    	}
        return str;
     }

 	// For debugging
 	public void printTree()
 	{
 		printNode(root);
 	}
 	
 	/** Do a pre-order traversal from this node down */
 	public void printNode(TrieNode curr)
 	{
 		if (curr == null) 
 			return;
 		
 		System.out.println(curr.getText());
 		
 		TrieNode next = null;
 		for (Character c : curr.getValidNextCharacters()) {
 			next = curr.getChild(c);
 			printNode(next);
 		}
 	}
	
}