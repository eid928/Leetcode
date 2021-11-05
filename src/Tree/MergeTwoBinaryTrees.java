package Tree;

import datastructure.TreeNode;

public class MergeTwoBinaryTrees {

	public static void main(String[] args) {
		/**
		 * 給定兩棵binary tree，
		 * 回傳一個新的binart tree
		 * 新樹每個節點的值是兩棵樹相對應位置節點的和
		 * 若該位置只有一個節點，則為該節點
		 */
		TreeNode root1 = new TreeNode(1);
		root1.left = new TreeNode(3);
		root1.right = new TreeNode(2);
		root1.left.left = new TreeNode(5);
		
		TreeNode root2 = new TreeNode(2);
		root2.left = new TreeNode(1);
		root2.right = new TreeNode(3);
		root2.left.right = new TreeNode(4);
		root2.right.right = new TreeNode(7);
		
		System.out.println(root1.getLevelOrderList());
		System.out.println(root2.getLevelOrderList());
		
		TreeNode mergeTrees = mergeTrees(root1, root2);
		System.out.println(mergeTrees.getLevelOrderList());
	}

	public static TreeNode mergeTrees(TreeNode root1, TreeNode root2) {
		/**
		 * 單純的PreOrder遞迴
		 */
		if (root1 == null) {
			return root2;
		}
		
		if (root2 == null) {
			return root1;
		}
		
		TreeNode mergedTree = new TreeNode(root1.val + root2.val);
		
		mergedTree.left = mergeTrees(root1.left, root2.left);
		mergedTree.right = mergeTrees(root1.right, root2.right);
		
        return mergedTree;
    }
}
