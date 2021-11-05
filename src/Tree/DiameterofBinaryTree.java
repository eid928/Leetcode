package Tree;

import datastructure.TreeNode;

public class DiameterofBinaryTree {

	public static void main(String[] args) {
		/**
		 * 給定一二元樹，求出此二元樹的diameter
		 * diameter為此二元樹中，任意兩節點間的最長距離
		 * 且diameter不需經過root
		 */
		TreeNode root = new TreeNode(1);
		root.left = new TreeNode(2);
		root.right = new TreeNode(3);
		
		root.left.left = new TreeNode(4);
		root.left.right = new TreeNode(5);
		
		System.out.println(diameterOfBinaryTree(root));
	}
	
	private static int longestPathLength;

    public static int diameterOfBinaryTree(TreeNode root) {
        /**
         * 這題和Longest Univalue Path有異曲同工之妙
         * 都是屬於算路徑長度，且此路徑可以跨過subroot的左右子節點
         * 對某節點來說，經過他的最長路徑就會是左右路徑相加
         * 但當它要回傳給自己的父傑點的時候，它只能選左右路徑中大的那條來回傳
         * 因為經過其父節點就無法經過該節點
         * => Use both children, return one of them
         */
    	longestPathLength = 0;
    	traverse(root);
    	
    	return longestPathLength;
    }

	private static int traverse(TreeNode node) {
		
		if (node == null) {
			return 0;
		}
		
		int leftPath = traverse(node.left);
		int rightPath = traverse(node.right);
		
		longestPathLength = Math.max(longestPathLength, leftPath+rightPath);
		
		return 1+Math.max(leftPath, rightPath);
	}
}
