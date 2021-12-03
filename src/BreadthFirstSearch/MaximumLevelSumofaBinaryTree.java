package BreadthFirstSearch;

import java.util.LinkedList;
import java.util.Queue;

import datastructure.TreeNode;

public class MaximumLevelSumofaBinaryTree {

	public static void main(String[] args) {
		/**
		 * 給定一棵樹
		 * 請求出哪一層，其值和為最大
		 */
		TreeNode root = new TreeNode(1);
		root.left = new TreeNode(7);
		root.right = new TreeNode(0);
		
		root.left.left = new TreeNode(7);
		root.left.right = new TreeNode(-8);
		
		System.out.println(maxLevelSum(root));
	}

    public static int maxLevelSum(TreeNode root) {
        /**
         * bfs
         * 同時要track每一層的和、最大和、目前的層數、最大和的層數
         */
    	int maxSum = Integer.MIN_VALUE;
    	int maxSumLevel = 1;
    	int curLevel = 1;
    	
    	Queue<TreeNode> queue = new LinkedList<TreeNode>();
    	queue.add(root);
    	
    	while(!queue.isEmpty()) {
    		
    		int sizeOfLevel = queue.size();
    		int sumOfLevel = 0;
    		
    		for (int i = 0; i < sizeOfLevel; i++) {
    			TreeNode curNode = queue.poll();
    			sumOfLevel += curNode.val;
    			if (curNode.left != null) {
					queue.add(curNode.left);
				}
    			if (curNode.right != null) {
					queue.add(curNode.right);
				}
    		}
    		
    		if (sumOfLevel > maxSum) {
				maxSum = sumOfLevel;
				maxSumLevel = curLevel;
			}
    		
    		curLevel ++;
    	}
    	
    	return maxSumLevel;
    }
}
