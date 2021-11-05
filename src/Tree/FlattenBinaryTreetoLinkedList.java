package Tree;

import datastructure.TreeNode;

public class FlattenBinaryTreetoLinkedList {

	public static void main(String[] args) {
		/**
		 * 給定一顆二元樹，將其所有Node以pre-order順序flatten為linked-list
		 * 雖是linked-list，但一樣用treeNode，全部以right連接
		 * 請以in-place來實作
		 */
		TreeNode root = new TreeNode(1);
		root.left = new TreeNode(2);
		root.right = new TreeNode(5);
		root.left.left = new TreeNode(3);
		root.left.right = new TreeNode(4);
		root.right.right = new TreeNode(6);
		System.out.println(root.getLevelOrderList());
		
		flatten(root);
		System.out.println(root.getLevelOrderList());
	}

    public static void flatten(TreeNode root) {
        
    	flattenTraverse(root);
    }

	private static TreeNode flattenTraverse(TreeNode node) {
		/**
		 * 遞迴
		 * 基本思路為，當我在某node，
		 * 我需要將left部分flatten，也須將right部分flatten
		 * 之後將兩個flatten的部分"合併"
		 * 再將合併的接在right上
		 */
		if (node == null) {
			return null;
		}
		
		TreeNode leftPart = flattenTraverse(node.left);
		TreeNode rightPart = flattenTraverse(node.right);
		node.right = merge(leftPart, rightPart);
		node.left = null;
		
		return node;
	}
	
	public static TreeNode merge(TreeNode left, TreeNode right) {
		/**
		 * 將right接到left最右邊的節點上
		 */
		if (left == null) {
			return right;
		}
		
		TreeNode curr = left;
		while (curr.right != null) {
			curr = curr.right;
		}
		
		curr.right = right;
		
		return left;
	}
}
