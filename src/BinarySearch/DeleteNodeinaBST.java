package BinarySearch;
import datastructure.TreeNode;

public class DeleteNodeinaBST {

	public static void main(String[] args) {
		
		TreeNode root = new TreeNode(5);
		root.left = new TreeNode(3);
		root.right = new TreeNode(6);
		root.left.left = new TreeNode(2);
		root.left.right = new TreeNode(4);
		root.right.right = new TreeNode(7);
		
		System.out.println(root.getLevelOrderList());
		
		TreeNode deletedTree = deleteNode(root, 4);
		System.out.println(deletedTree.getLevelOrderList());
	}
    public static TreeNode deleteNode(TreeNode node, int key) {
    	/**
    	 * deletNode會回傳刪除key之後的node會長怎樣
    	 */
    	if (node == null) {
			return null;
		}
    	
    	if (node.val < key) {
    		/* node的右邊變成刪除後的tree */
			node.right = deleteNode(node.right, key);
		} else if (node.val > key) {
			/* node的左邊變成刪除後的tree */
			node.left = deleteNode(node.left, key);
		} else { 
			/**
			 * val == key：找到被刪除的Node
			 * 這邊要回傳這個node被刪掉後會變成什麼tree
			 */
			
			/* case1: 左右child都是null或是只有一邊是null */
			if (node.left == null) {
				return node.right;
			} else if (node.right == null) {
				return node.left;
			}
			
			/**
			 * case2: 
			 * 1. 左右child都不是null，須將node的值設成右tree的最小值
			 * 2. 然後再刪除右tree中的最小值(最小的那個不會有子node，可利用case1)
			 */
			node.val = getMinValue(node.right);
			node.right = deleteNode(node.right, node.val);
		}
        return node;
    }
    
	private static int getMinValue(TreeNode subRoot) {
		
		if (subRoot.left == null) {
			return subRoot.val;
		}
		
		return getMinValue(subRoot.left);
	}
}
