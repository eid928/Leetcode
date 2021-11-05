package Tree;

import datastructure.TreeNode;

public class MinimumDepthofBinaryTree {

	public static void main(String[] args) {
		/**
		 * 給定一棵樹，求出最淺的深度
		 */
		TreeNode root = new TreeNode(3);
		root.left = new TreeNode(9);
		root.right = new TreeNode(20);
		
		root.right.left = new TreeNode(15);
		root.right.right = new TreeNode(7);
		
		System.out.println(minDepth(root));
	}
    public static int minDepth(TreeNode root) {
    	/**
    	 * recursion
    	 */
    	if (root == null) {
			return 0;
		}
		
		if (root.left == null) {
			return 1 + minDepth(root.right);
		}
		
		if (root.right == null) {
			return 1 + minDepth(root.left);
		}
		
		return 1 + Math.min(minDepth(root.right), minDepth(root.left));
    }
}
