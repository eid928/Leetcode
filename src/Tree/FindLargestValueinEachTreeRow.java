package Tree;

import java.util.LinkedList;
import java.util.List;
import java.util.Queue;

import datastructure.TreeNode;

public class FindLargestValueinEachTreeRow {

	public static void main(String[] args) {
		/**
		 * 找出每一層level中最大的數字
		 */
	}

    public static List<Integer> largestValues(TreeNode root) {
        /**
         * BFS
         */
    	Queue<TreeNode> queue = new LinkedList<>();
    	List<Integer> results = new LinkedList<>();
    	
    	if (root == null) {
			return results;
		}
    	
    	queue.add(root);
    	
    	while (!queue.isEmpty()) {
    		
    		int curSizeOfLevel = queue.size();
    		int largestOfLevel = Integer.MIN_VALUE;
    		
    		for (int i = 0; i < curSizeOfLevel; i++) {
    			TreeNode curNode = queue.poll();
    			if (curNode.left != null) queue.add(curNode.left);
    			if (curNode.right != null) queue.add(curNode.right);
    			largestOfLevel = Math.max(largestOfLevel, curNode.val);
    		}
    		
    		results.add(largestOfLevel);
    	}
    	
    	return results;
    }
}
