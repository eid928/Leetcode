package Tree;

import java.util.LinkedList;
import java.util.Queue;

public class PopulatingNextRightPointersinEachNode {

	public static class Node {
	    public int val;
	    public Node left;
	    public Node right;
	    public Node next;

	    public Node() {}
	    
	    public Node(int _val) {
	        val = _val;
	    }

	    public Node(int _val, Node _left, Node _right, Node _next) {
	        val = _val;
	        left = _left;
	        right = _right;
	        next = _next;
	    }
	};
	
	public static void main(String[] args) {
		/**
		 * 給定一個full binary tree，但每個node的next目前都指向null
		 * 
		 * 請將每個node的next指向同level右邊的node
		 * 最右邊的node則指向null
		 */
		
		Node root = new Node(1);
		root.left = new Node(2);
		root.right =  new Node(3);
		
		root.left.left =  new Node(4);
		root.left.right =  new Node(5);
		root.right.left =  new Node(6);
		root.right.right =  new Node(7);
	}

    public Node connect(Node root) {
    	/**
    	 * BFS
    	 */
    	if (root == null) {
			return null;
		}
    	
    	Queue<Node> queue = new LinkedList<>();
    	queue.add(root);
    	
    	while (!queue.isEmpty()) {
    		
    		int curLevelSize = queue.size();
    		
    		for (int i = 0; i < curLevelSize; i++) {
    			
    			Node curNode = queue.poll();
    			if (curNode.left != null) queue.add(curNode.left);
    			if (curNode.right != null) queue.add(curNode.right);
    			
    			if (i != curLevelSize-1) { /* 指向同一層的右邊node */
					curNode.next = queue.peek();
				} else { /* 若是最右邊的，指向null */
					curNode.next = null;
				}
    		}
    	}
        
    	return root;
    }
}
