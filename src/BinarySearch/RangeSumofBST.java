package BinarySearch;

import datastructure.TreeNode;

public class RangeSumofBST {

	public static void main(String[] args) {
		
		TreeNode root = new TreeNode(10);
		root.left = new TreeNode(5);
		root.right = new TreeNode(15);
		
		root.left.left = new TreeNode(3);
		root.left.right = new TreeNode(7);
		root.right.right = new TreeNode(18);
		
		System.out.println(rangeSumBST(root, 7, 15));
	}
	
	private static int sum;

    public static int rangeSumBST(TreeNode root, int low, int high) {
        
    	sum = 0;
    	dfs(root, low, high);
    	
    	return sum;
    }

	private static void dfs(TreeNode node, int low, int high) {
		
		if (node == null) {
			return;
		}
		
		if (node.val >= low && node.val <= high) {
			sum += node.val;
			dfs(node.left, low, high);
			dfs(node.right, low, high);
		} else if (node.val > high) {
			dfs(node.left, low, high);
		} else {
			dfs(node.right, low,high);
		}
	}
}
