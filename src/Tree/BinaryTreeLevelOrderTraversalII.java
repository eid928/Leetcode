package Tree;

import java.util.LinkedList;
import java.util.List;
import java.util.Queue;

import datastructure.TreeNode;

public class BinaryTreeLevelOrderTraversalII {

	public static void main(String[] args) {
		/**
		 * 實作bottom-up的level order traverse
		 * 也就是最底下的leaf先，root放最後
		 */
		TreeNode root = new TreeNode(3);
		root.left = new TreeNode(9);
		root.right = new TreeNode(20);
		
		root.right.left = new TreeNode(15);
		root.right.right = new TreeNode(7);
		
		System.out.println(levelOrderBottom(root));
	}

    public static List<List<Integer>> levelOrderBottom(TreeNode root) {
    	/**
    	 * BFS
    	 * 基本上一樣，但加進list的時候用linkedlist addfirst
    	 */
    	LinkedList<List<Integer>> results = new LinkedList<>();
    	Queue<TreeNode> queue = new LinkedList<>();
    	
    	if (root == null) {
			return results;
		}
    	
    	queue.add(root);
    	
    	while (!queue.isEmpty()) {
    		
    		int sizeOfLevel = queue.size();
    		LinkedList<Integer> nodeOfLevel = new LinkedList<>();
    		
    		for (int i = 0; i < sizeOfLevel; i++) {
    			TreeNode curNode = queue.poll();
    			if (curNode.left != null ) queue.add(curNode.left);
    			if (curNode.right != null ) queue.add(curNode.right);
    			nodeOfLevel.add(curNode.val);
    		}
    		
    		results.addFirst(nodeOfLevel);
    	}
    	
    	return results;
    }
}
