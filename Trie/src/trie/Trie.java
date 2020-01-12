package trie;

import java.util.ArrayList;

/**
 * This class implements a Trie. 
 * 
 * @author Sesh Venugopal
 *
 */
public class Trie {
	
	// prevent instantiation
	private Trie() { }
	
	/**
	 * Builds a trie by inserting all words in the input array, one at a time,
	 * in sequence FROM FIRST TO LAST. (The sequence is IMPORTANT!)
	 * The words in the input array are all lower case.
	 * 
	 * @param allWords Input array of words (lowercase) to be inserted.
	 * @return Root of trie with all words inserted from the input array
	 */
	public static TrieNode buildTrie(String[] allWords) {
		/** COMPLETE THIS METHOD **/ 
		
		TrieNode root = new TrieNode(null,null,null);
		
		if (allWords.length == 0) return root;
		
		for (int i = 0; i < allWords.length; i++) {
		   allWords[i] = allWords[i].toLowerCase();
		}
		short endIndex = (short)(allWords[0].length()-1);
		root.firstChild = new TrieNode(new Indexes(0,(short)0,endIndex),null,null);
		
		
		int startInd = -1, endInd = -1, wordInd = -1, match = -1;
		TrieNode ptr = root.firstChild, prev = root.firstChild;
		
		for (int i = 1; i<allWords.length; i++) {
			String word = allWords[i];
			while(ptr!=null) {
				startInd = ptr.substr.startIndex;
				endInd = ptr.substr.endIndex;
				wordInd = ptr.substr.wordIndex;
				
				if(startInd > word.length()) {
					prev = ptr;
					ptr = ptr.sibling;
					
				}
				
				match = match(allWords[wordInd].substring(startInd, endInd +1),word.substring(startInd));
				
				if(match != -1) {
					match+=startInd;
				}
				if(match == -1) {
					prev = ptr;
					ptr = ptr.sibling;
				}
				else {
					if(match==endInd) {
						prev = ptr;
						ptr = ptr.firstChild;
					}
					else if (match<endInd) {
						prev = ptr;
						break;
					}
				}
			
			}
			
			if (ptr == null) {
				Indexes indexes = new Indexes (i, (short)startInd,(short)(word.length()-1));
				prev.sibling = new TrieNode(indexes,null,null);
			}
			else {
				Indexes curIndexes = prev.substr;
				TrieNode curFirstChild = prev.firstChild;
				
				Indexes currWordNewIndexes = new Indexes(curIndexes.wordIndex,(short)(match+1),curIndexes.endIndex);
				curIndexes.endIndex = (short)match;
				
				prev.firstChild = new TrieNode(currWordNewIndexes,null,null);
				prev.firstChild.firstChild = curFirstChild;
				prev.firstChild.sibling = new TrieNode(new Indexes((short)(i),(short)(match+1),(short)(word.length()-1)),null,null);
				
			}
			ptr = prev = root.firstChild;
			match = -1; startInd = -1;  endInd = -1; wordInd = -1;
		}
	
		
		// FOLLOWING LINE IS A PLACEHOLDER TO ENSURE COMPILATION
		// MODIFY IT AS NEEDED FOR YOUR IMPLEMENTATIONd
		return root;
	}
	
	
	private static int match (String compare, String insert) {
		int max = 0;
		while(max<compare.length() && max<insert.length()&& compare.charAt(max)== insert.charAt(max)) {
			max++;
		}
		int res = (max-1);
		return res;
	}
	/**
	 * Given a trie, returns the "completion list" for a prefix, i.e. all the leaf nodes in the 
	 * trie whose words start with this prefix. 
	 * For instance, if the trie had the words "bear", "bull", "stock", and "bell",
	 * the completion list for prefix "b" would be the leaf nodes that hold "bear", "bull", and "bell"; 
	 * for prefix "be", the completion would be the leaf nodes that hold "bear" and "bell", 
	 * and for prefix "bell", completion would be the leaf node that holds "bell". 
	 * (The last example shows that an input prefix can be an entire word.) 
	 * The order of returned leaf nodes DOES NOT MATTER. So, for prefix "be",
	 * the returned list of leaf nodes can be either hold [bear,bell] or [bell,bear].
	 *
	 * @param root Root of Trie that stores all words to search on for completion lists
	 * @param allWords Array of words that have been inserted into the trie
	 * @param prefix Prefix to be completed with words in trie
	 * @return List of all leaf nodes in trie that hold words that start with the prefix, 
	 * 			order of leaf nodes does not matter.
	 *         If there is no word in the tree that has this prefix, null is returned.
	 */
	public static ArrayList<TrieNode> completionList(TrieNode root,
										String[] allWords, String prefix) {
		
		if (root == null) return null;
		
		ArrayList<TrieNode> results = new ArrayList<>();
		TrieNode ptr = root;
		
		while(ptr!= null) {
			if(ptr.substr == null) {
				ptr = ptr.firstChild;
				String w = allWords[ptr.substr.wordIndex], a = w.substring(0,ptr.substr.endIndex+1);
				if(w.startsWith(prefix)|| prefix.startsWith(a)) {
					if(ptr.firstChild!=null) {
						results.addAll(completionList(ptr.firstChild,allWords,prefix));
						ptr = ptr.sibling;
					} else {
						results.add(ptr);
						ptr = ptr.sibling;
					}
				} else {
					ptr = ptr.sibling;
				}
			
			}
		}
		return results;
		// FOLLOWING LINE IS A PLACEHOLDER TO ENSURE COMPILATION
		// MODIFY IT AS NEEDED FOR YOUR IMPLEMENTATION
		
	}
	
	public static void print(TrieNode root, String[] allWords) {
		System.out.println("\nTRIE\n");
		print(root, 1, allWords);
	}
	
	private static void print(TrieNode root, int indent, String[] words) {
		if (root == null) {
			return;
		}
		for (int i=0; i < indent-1; i++) {
			System.out.print("    ");
		}
		
		if (root.substr != null) {
			String pre = words[root.substr.wordIndex]
							.substring(0, root.substr.endIndex+1);
			System.out.println("      " + pre);
		}
		
		for (int i=0; i < indent-1; i++) {
			System.out.print("    ");
		}
		System.out.print(" ---");
		if (root.substr == null) {
			System.out.println("root");
		} else {
			System.out.println(root.substr);
		}
		
		for (TrieNode ptr=root.firstChild; ptr != null; ptr=ptr.sibling) {
			for (int i=0; i < indent-1; i++) {
				System.out.print("    ");
			}
			System.out.println("     |");
			print(ptr, indent+1, words);
		}
	}
 }
