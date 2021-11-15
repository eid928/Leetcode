package Tree;

import datastructure.TreeNode;

public class InvertBinaryTree {
	
	public TreeNode invertTree(TreeNode root) {
		
		if (root == null) {
			return null;
		}
		
		TreeNode right = root.right;
		TreeNode left = root.left;
		
		root.right = left;
		root.left = right;
		
		invertTree(root.left);
		invertTree(root.right);
		
		return root;
    }
}
