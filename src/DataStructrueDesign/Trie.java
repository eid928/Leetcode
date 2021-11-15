package DataStructrueDesign;

import java.util.HashMap;
import java.util.Map;

public class Trie {
<<<<<<< HEAD
	
	public static void main(String[] args) {
		
		Trie trie = new Trie();
		trie.insert("apple");
=======

	public static void main(String[] args) {
		/**
		 * 實作trie
		 */
		Trie trie = new Trie();
		trie.insert("apple");
		System.out.println(trie.root.children);
		System.out.println(trie.root.children.get('a').children);
		System.out.println(trie.root.children.get('a').children.get('p').children);
		System.out.println(trie.root.children.get('a').children.get('p').children.get('p').children);
		System.out.println(trie.root.children.get('a').children.get('p').children.get('p').children.get('l').children);
>>>>>>> 63b505a9fdf9a258fc818882e526f29e220e002c
		System.out.println(trie.search("apple"));   // return True
		System.out.println(trie.search("app"));     // return False
		System.out.println(trie.startsWith("app")); // return True
		trie.insert("app");
<<<<<<< HEAD
=======
		System.out.println(trie.root.children);
		System.out.println(trie.root.children.get('a').children);
		System.out.println(trie.root.children.get('a').children.get('p').children);
		System.out.println(trie.root.children.get('a').children.get('p').children.get('p').children);
		System.out.println(trie.root.children.get('a').children.get('p').children.get('p').children.get('l').children);
>>>>>>> 63b505a9fdf9a258fc818882e526f29e220e002c
		System.out.println(trie.search("app"));     // return True
	}
	
	private TrieNode root;

	/** Initialize your data structure here. */
    public Trie() {
<<<<<<< HEAD
=======
    	/**
    	 * space:
    	 * best case: 加入重疊的string，例如apple->app，總共只有五個node
    	 * worst case: 每次加入的string都不重疊
    	 * => O(N*L)
    	 */
>>>>>>> 63b505a9fdf9a258fc818882e526f29e220e002c
        root = new TrieNode();
    }
    
    /** Inserts a word into the trie. */
    public void insert(String word) {
<<<<<<< HEAD
    	
=======
    	/**
    	 * time: O(L), where L = length of word
    	 */
>>>>>>> 63b505a9fdf9a258fc818882e526f29e220e002c
        insert(word, 0, root);
    }
    
    private void insert(String word, int index, TrieNode node) {
		
    	if (index >= word.length()) {
			return;
		}
		char curChar = word.charAt(index);
    	Map<Character, TrieNode> children = node.children;
<<<<<<< HEAD
    	if (children.containsKey(curChar)) {
=======
    	if (children.containsKey(curChar)) { /* 若已經存在節點，且新插入的字已到結尾了，就要更新原有的節點 */
>>>>>>> 63b505a9fdf9a258fc818882e526f29e220e002c
			if (index == word.length()-1) children.get(curChar).isKey = true;
		} else {
			children.put(curChar, new TrieNode(curChar, index == word.length()-1));
		}
<<<<<<< HEAD
    	insert(word, index+1, children.get(curChar));;
=======
    	insert(word, index+1, children.get(curChar));
>>>>>>> 63b505a9fdf9a258fc818882e526f29e220e002c
	}

	/** Returns if the word is in the trie. */
    public boolean search(String word) {
<<<<<<< HEAD
    	
=======
    	/**
    	 * time: O(L), where L = length of word
    	 */
>>>>>>> 63b505a9fdf9a258fc818882e526f29e220e002c
        return search(word, 0, root);
    }
    
    private boolean search(String word, int index, TrieNode node) {
<<<<<<< HEAD
		
=======
		/**
		 * 和startsWith類似
		 * 最後一個node要看是否為key
		 */
>>>>>>> 63b505a9fdf9a258fc818882e526f29e220e002c
    	if (index == word.length()) {
			return node.isKey;
		}
    	
    	char curChar = word.charAt(index);
    	Map<Character, TrieNode> children = node.children;
    	
<<<<<<< HEAD
		if (children.containsKey(curChar)) {  // O(1)
=======
		if (children.containsKey(curChar)) {
>>>>>>> 63b505a9fdf9a258fc818882e526f29e220e002c
			return search(word, index+1, children.get(curChar));
		}
    	
		return false;
	}

	/** Returns if there is any word in the trie that starts with the given prefix. */
    public boolean startsWith(String prefix) {
<<<<<<< HEAD
    	
=======
    	/**
    	 * time: O(L), where L = length of prefix
    	 */
>>>>>>> 63b505a9fdf9a258fc818882e526f29e220e002c
    	return startsWith(prefix, 0, root);
    }
    
    private boolean startsWith(String prefix, int index, TrieNode node) {
<<<<<<< HEAD
		
=======
    	/**
		 * 和search類似
		 * 但只要能找到node不管是不是key都為true
		 */
>>>>>>> 63b505a9fdf9a258fc818882e526f29e220e002c
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
<<<<<<< HEAD
    	
    	char val;
    	boolean isKey;
    	Map<Character, TrieNode> children;
    	// TrieNode[] children = new TrieNode[26]; 
=======
    	/**
    	 * TrieNode
    	 */
    	char val; //儲存字母
    	boolean isKey; //表示為字的結尾
    	Map<Character, TrieNode> children; //根據字母找到子節點
>>>>>>> 63b505a9fdf9a258fc818882e526f29e220e002c
    	
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
