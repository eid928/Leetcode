package Tree;

import java.util.ArrayList;
import java.util.List;

import datastructure.TreeNode;

public class BalanceaBinarySearchTree {

	public static void main(String[] args) {
		/**
		 * 將BST平衡後回傳
		 */
		TreeNode root = new TreeNode(1);
		root.right = new TreeNode(2);
		root.right.right = new TreeNode(3);
		root.right.right.right = new TreeNode(4);
		
		System.out.println(root.getLevelOrderList());
		
		System.out.println(balanceBST(root).getLevelOrderList());
	}

    public static TreeNode balanceBST(TreeNode root) {
    	/**
    	 * in-order-traverse後進行construct
    	 */
    	List<TreeNode> sorted = new ArrayList<>();
    	inOrderTraverse(root, sorted);
    	
    	TreeNode balanceTree = constructBST(sorted, 0, sorted.size()-1);
    	
        return balanceTree;
    }

	private static TreeNode constructBST(List<TreeNode> sorted, int start, int end) {
		
		if (start > end) {
			/**
			 * 邊界條件沒有等號
			 * 用3個node來想就好
			 * 1,2,3
			 */
			return null;
		}
		
		int mid = (start+end)/2;
		
		TreeNode curNode = sorted.get(mid);
		curNode.left = constructBST(sorted, start, mid-1);
		curNode.right = constructBST(sorted, mid+1, end);
		
		return curNode;
	}

	private static void inOrderTraverse(TreeNode node, List<TreeNode> sorted) {
		
		if (node == null) {
			return;
		}
		
		inOrderTraverse(node.left, sorted);
		sorted.add(node);
		inOrderTraverse(node.right, sorted);
	}
}
