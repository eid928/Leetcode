package Tree;

import datastructure.TreeNode;

public class BalancedBinaryTree {

	public static void main(String[] args) {
		
		TreeNode root = new TreeNode(3);
		root.left = new TreeNode(9);
		root.right = new TreeNode(20);
		
		root.right.left = new TreeNode(15);
		root.right.right = new TreeNode(7);

		System.out.println(isBalanced(root));
		
		root = new TreeNode(1);
		root.left = new TreeNode(2);
		root.right = new TreeNode(2);
		
		root.left.left = new TreeNode(3);
		root.right.right = new TreeNode(3);
		
		root.left.left.left = new TreeNode(4);
		root.right.right.right = new TreeNode(4);
		System.out.println(root.getLevelOrderList());
		System.out.println(isBalanced(root));
	}

    public static boolean isBalanced(TreeNode root) {
    	
    	if (root == null) {
			return true;
		}
    	
    	int leftHeight = getHeight(root.left);
    	int rightHeight = getHeight(root.right);
    	
    	if (Math.abs(leftHeight - rightHeight) > 1) {
			return false;
		}
    	
    	return isBalanced(root.left) && isBalanced(root.right);
    }

	private static int getHeight(TreeNode node) {
		
		if (node == null) {
			return 0;
		}
		
		return 1 + Math.max(getHeight(node.left), getHeight(node.right));
	}
}
