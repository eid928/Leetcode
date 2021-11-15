package DataStructrueDesign;

import java.util.HashMap;
import java.util.Map;

public class Trie {
	
	public static void main(String[] args) {
		
		Trie trie = new Trie();
		trie.insert("apple");
		System.out.println(trie.search("apple"));   // return True
		System.out.println(trie.search("app"));     // return False
		System.out.println(trie.startsWith("app")); // return True
		trie.insert("app");
		System.out.println(trie.search("app"));     // return True
	}
	
	private TrieNode root;

	/** Initialize your data structure here. */
    public Trie() {
        root = new TrieNode();
    }
    
    /** Inserts a word into the trie. */
    public void insert(String word) {
    	
        insert(word, 0, root);
    }
    
    private void insert(String word, int index, TrieNode node) {
		
    	if (index >= word.length()) {
			return;
		}
		char curChar = word.charAt(index);
    	Map<Character, TrieNode> children = node.children;
    	if (children.containsKey(curChar)) {
			if (index == word.length()-1) children.get(curChar).isKey = true;
		} else {
			children.put(curChar, new TrieNode(curChar, index == word.length()-1));
		}
    	insert(word, index+1, children.get(curChar));;
	}

	/** Returns if the word is in the trie. */
    public boolean search(String word) {
    	
        return search(word, 0, root);
    }
    
    private boolean search(String word, int index, TrieNode node) {
		
    	if (index == word.length()) {
			return node.isKey;
		}
    	
    	char curChar = word.charAt(index);
    	Map<Character, TrieNode> children = node.children;
    	
		if (children.containsKey(curChar)) {  // O(1)
			return search(word, index+1, children.get(curChar));
		}
    	
		return false;
	}

	/** Returns if there is any word in the trie that starts with the given prefix. */
    public boolean startsWith(String prefix) {
    	
    	return startsWith(prefix, 0, root);
    }
    
    private boolean startsWith(String prefix, int index, TrieNode node) {
		
    	if (index == prefix.length()) {
			return true;
		}
    	
    	char curChar = prefix.charAt(index);
    	Map<Character, TrieNode> children = node.children;
    	
		if (children.containsKey(curChar)) {
			return startsWith(prefix, index+1, children.get(curChar));
		}
    	
		return false;
	}

	public static class TrieNode {
    	
    	char val;
    	boolean isKey;
    	Map<Character, TrieNode> children;
    	// TrieNode[] children = new TrieNode[26]; 
    	
    	@Override
    	public boolean equals(Object obj) {
    		TrieNode node = (TrieNode)obj;
    		return val == node.val && isKey == node.isKey;
    	}
    	
    	public TrieNode() {
			children = new HashMap<>();
		}
    	
    	public TrieNode(char val, boolean isKey) {
    		children = new HashMap<>();
			this.val = val;
			this.isKey = isKey;
		}
    	
    	@Override
    	public String toString() {
    		
    		return "["+val+","+isKey+"]";
    	}
    }
}
