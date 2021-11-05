package Tree;

import java.util.ArrayList;
import java.util.List;

import datastructure.TreeNode;

public class RecoverBinarySearchTree {

	public static void main(String[] args) {
		/**
		 * 有一個BST，但剛好它的其中兩個node的值顛倒了以至於不符合BST的特性
		 * 請將值復原讓樹還原為BST
		 */
		TreeNode root = new TreeNode(1);
		root.left = new TreeNode(3);
		root.left.right = new TreeNode(2);

		System.out.println(root.getLevelOrderList());
		
		recoverTree(root);
		
		System.out.println(root.getLevelOrderList());
		
		root = new TreeNode(3);
		root.right = new TreeNode(2);
		root.right.right = new TreeNode(1);
		
		recoverTree(root);
		
		System.out.println(root.getLevelOrderList());
	}

    public static void recoverTree(TreeNode root) {
        /**
         * in-order-traverse + insertion sort
         * 先in-order-traverse，將node儲存在一個list中
         * 而剛好會有一對node值是顛倒的
         * 從尾端找到其中一個node，再從頭查看該和哪一個node交換
         */
    	List<TreeNode> inOrderList = new ArrayList<>();
    	inOrderTraverse(root, inOrderList);
    	
    	TreeNode node1 = null;
    	
    	for (int i = inOrderList.size()-1; i >= 1 ; i--) {
    		if (inOrderList.get(i).val < inOrderList.get(i-1).val) {
				node1 = inOrderList.get(i);
				break;
			}
    	}
    	
    	for (int i = 0; i < inOrderList.size(); i++) {
    		if (inOrderList.get(i).val > node1.val) {
				int tmp = inOrderList.get(i).val;
				inOrderList.get(i).val = node1.val;
				node1.val = tmp;
				break;
			}
    	}
    }

	private static void inOrderTraverse(TreeNode node, List<TreeNode> inOrderList) {
		
		if (node == null) {
			return;
		}
		
		inOrderTraverse(node.left, inOrderList);
		inOrderList.add(node);
		inOrderTraverse(node.right, inOrderList);
	}
}
