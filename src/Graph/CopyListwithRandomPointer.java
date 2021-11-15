package Graph;

import java.util.HashMap;
import java.util.Map;

public class CopyListwithRandomPointer {
	
	public static class Node {
		int val;
	    Node next;
	    Node random;

	    public Node(int val) {
	        this.val = val;
	        this.next = null;
	        this.random = null;
	    }
	}

	public static void main(String[] args) {
		/**
		 * 給定一個linkedlist
		 * 其node都有一個random指向隨機一個node
		 * 請複製出一樣的list，random指向也都要一樣
		 */
		Node head = new Node(7);
		head.next = new Node(13);
		head.next.next = new Node(11);
		head.next.next.next = new Node(10);
		head.next.next.next.next = new Node(1);
		
		head.next.random = head;
		head.next.next.random = head.next.next.next.next;
		head.next.next.next.random = head.next.next;
		head.next.next.next.next.random = head;
		
		print(head);
		
		Node cloneHead = copyRandomList(head);
		print(cloneHead);
	}

    public static Node copyRandomList(Node head) {
    	/**
    	 * 和clone graph類似，用一個map來追蹤哪些node被複製過了
    	 * key: 原本的node
    	 * value: 複製的node
    	 */
    	Map<Node, Node> cloneMap = new HashMap<>();
    	
        return dfs(head, cloneMap);
    }
    
    private static Node dfs(Node originNode, Map<Node, Node> cloneMap) {
    	/**
    	 * 針對next做dfs
    	 * 一旦跑到該節點就new出複製體儲存在map中
    	 * 在定向random時，所有節點已經儲存在map中了
    	 * 而第一個完成next與random指向的會是最後一個node
    	 * 順序是：
    	 * 7 13 11 10 1加進map
    	 * 1.next指向null，random指向7
    	 * 10.next指向1，random指向11
    	 * 以此類推
    	 */
    	if (originNode == null) {
			return null;
		}
    	if (cloneMap.containsKey(originNode)) {
			return cloneMap.get(originNode);
		}
    	
    	Node cloneNode = new Node(originNode.val);
    	cloneMap.put(originNode, cloneNode);
    	
    	cloneNode.next = dfs(originNode.next, cloneMap);
    	cloneNode.random = cloneMap.get(originNode.random);
    	
		return cloneNode;
	}

	public static void print(Node head) {
    	
    	if (head == null) {
    		System.out.print("null");
			return;
		}
    	System.out.print("["+head.val+","+(head.random==null? "null": head.random.val)+"] -> ");
    	
    	print(head.next);
    }
}
