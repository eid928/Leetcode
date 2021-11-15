package BinarySearch;

import java.util.Stack;

import datastructure.TreeNode;

public class MinimumAbsoluteDifferenceinBST {

	public static void main(String[] args) {
		
		TreeNode root = new TreeNode(4);
		root.left = new TreeNode(2);
		root.right = new TreeNode(6);
		
		root.left.left = new TreeNode(1);
		root.left.right = new TreeNode(3);
		
		System.out.println(getMinimumDifference(root));
	}
	
	private static int minDiff;

    public static int getMinimumDifference(TreeNode root) {
    	
    	minDiff = Integer.MAX_VALUE;
    	Stack<Integer> stack = new Stack<>();
    	inOrderTraverse(root, stack);
        
    	return minDiff;
    }

	private static void inOrderTraverse(TreeNode node, Stack<Integer> stack) {
		
		if (node == null) return;
		
		inOrderTraverse(node.left, stack);
		
		if (!stack.isEmpty()) {
			int lastNodeVal = stack.peek();
			minDiff = Math.min(minDiff, Math.abs(lastNodeVal - node.val));
		}
		stack.add(node.val);
		
		inOrderTraverse(node.right, stack);
	}
}
