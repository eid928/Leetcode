package Tree;

import datastructure.TreeNode;

public class BinarySearchTreetoGreaterSumTree {

	public static void main(String[] args) {
		/**
		 * 給定一棵BST
		 * 請將每個節點的值加上該節點所有右邊節點的值
		 */
		TreeNode root = new TreeNode(4);
		root.left = new TreeNode(1);
		root.right = new TreeNode(6);
		
		root.left.left = new TreeNode(0);
		root.left.right = new TreeNode(2);
		root.right.left = new TreeNode(5);
		root.right.right = new TreeNode(7);
		
		root.left.right.right = new TreeNode(3);
		root.right.right.right = new TreeNode(8);
		
		System.out.println(root.getLevelOrderList());
		System.out.println(bstToGst(root).getLevelOrderList());
	}
	
	private static int curSum;

    public static TreeNode bstToGst(TreeNode root) {
    	
        curSum = 0;
    	dfs(root);
    	
    	return root;
    }

	private static void dfs(TreeNode node) {
		/**
		 * 用一個curSum來追蹤目前累積的和
		 * 從右邊開始進行in-order traverse
		 * 一邊traverse一邊更新累積和
		 */
		if (node == null) {
			return;
		}
		
		dfs(node.right);
		node.val += curSum;
		curSum = node.val;
		dfs(node.left);
	}
}
