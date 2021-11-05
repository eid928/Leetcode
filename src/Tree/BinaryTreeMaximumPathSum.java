package Tree;

import datastructure.TreeNode;

public class BinaryTreeMaximumPathSum {

	public static void main(String[] args) {
		/**
		 * 給定一二元樹
		 * 求出最大的PathSum
		 * Path可以經過subroot
		 */
		TreeNode root = new TreeNode(-10);
		root.left = new TreeNode(9);
		root.right = new TreeNode(20);
		
		root.right.left = new TreeNode(15);
		root.right.right = new TreeNode(7);
		
		System.out.println(maxPathSum(root));
	}

	private static int maxSum;
	
    public static int maxPathSum(TreeNode root) {
        /**
         * 和Diameter of Binary Tree、Longest Univalue Path有異曲同工之妙
         * 但因為sum有可能為負數
         * 所以leftPathSum、rightPathSum分別traverse之後還要與0比較大小
         * 若為負數就直接不取用
         */
    	maxSum = Integer.MIN_VALUE;
    	traverse(root);
    	
    	return maxSum;
    }

	private static int traverse(TreeNode node) {
		
		if (node == null) {
			return 0;
		}
		
		int leftPathSum = Math.max(0, traverse(node.left));
		int rightPathSum = Math.max(0, traverse(node.right));
		
		maxSum = Math.max(maxSum, node.val + leftPathSum + rightPathSum);
		
		return node.val + Math.max(leftPathSum, rightPathSum);
	}
}
