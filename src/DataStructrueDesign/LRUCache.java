package DataStructrueDesign;

import java.util.HashMap;
import java.util.Map;

public class LRUCache {
	
	private int size;
	
	private Map<Integer, Node> cache = new HashMap<>();;
	
	private CacheList list = new CacheList();

	public static void main(String[] args) {
		/**
		 * 實作LRU快取器
		 * 就像一個map
		 * 但是一旦加入時超過capacity，就刪除map中最久沒使用的key
		 */
		LRUCache lRUCache = new LRUCache(2);
		lRUCache.put(1, 1); // cache is {1=1}
		lRUCache.put(2, 2); // cache is {1=1, 2=2}
		System.out.println(lRUCache.get(1));    // return 1
		lRUCache.put(3, 3); // LRU key was 2, evicts key 2, cache is {1=1, 3=3}
		System.out.println(lRUCache.get(2));    // returns -1 (not found)
		lRUCache.put(4, 4); // LRU key was 1, evicts key 1, cache is {4=4, 3=3}
		System.out.println(lRUCache.get(1));    // return -1 (not found)
		System.out.println(lRUCache.get(3));    // return 3
		System.out.println(lRUCache.get(4));    // return 4
	}

	public LRUCache(int capacity) {
        size = capacity;
    }
    
    public int get(int key) {
    	
    	if (!cache.containsKey(key)) {
			return -1;
		}
    	
    	Node theNode = cache.get(key);
		list.removeToFirst(theNode);
    	return theNode.value;
    }
    
    public void put(int key, int value) {
    	/**
    	 * map中儲存的value是node
    	 * 而list則是使用的順序
    	 * 一旦使用就將該node移動到最前方
    	 * 因為有了map去儲存，能很快地找到list中那個node的位置來進行移動
    	 */
    	if (cache.containsKey(key)) {
			Node theNode = cache.get(key);
			theNode.value = value;
			list.removeToFirst(theNode);
			return;
		}
    	
    	if (cache.size() < size && !cache.containsKey(key)) {
    		
    		Node theNode = new Node(key, value);
			cache.put(key, theNode);
    		list.addFirst(theNode);
    		return;
    	}
    	
    	Node leastUsedNode = list.removeLast();
    	cache.remove(leastUsedNode.key);
    	Node theNode = new Node(key, value);
		cache.put(key, theNode);
		list.addFirst(theNode);
    }
    
    private class Node {
    	int key;
    	int value;
    	Node next;
    	Node prev;
    	public Node(int key, int value) {
			this.key = key;
			this.value = value;
		}
    }
    
    private class CacheList {
    	/**
    	 * 雖然JDK中的linkedlist是double
    	 * 但其remove(Object)是O(N)
    	 * 所以自己來實作
    	 */
    	private Node head;
    	private Node tail;
    	public CacheList() {
			head = new Node(0,0);
			tail = new Node(0,0);
			head.next = tail;
			tail.prev = head;
		}
    	public void addFirst(Node node) {
    		node.next = head.next;
    		head.next.prev = node;
    		
    		head.next = node;
    		node.prev = head;
    	}
    	public Node removeLast() {
    		
    		Node lastNode = tail.prev;
    		lastNode.prev.next = lastNode.next;
    		lastNode.next.prev = lastNode.prev;
    		
    		return lastNode;
    	}
    	public void removeToFirst(Node node) {
    		
    		node.prev.next = node.next;
    		node.next.prev = node.prev;
    		addFirst(node);
    	}
    }
}
