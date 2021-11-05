package Tree;

import java.util.Arrays;
import java.util.LinkedList;
import java.util.Queue;

import datastructure.TreeNode;

public class SerializeandDeserializeBST {

	public static void main(String[] args) {
		/**
		 * 幾乎跟binary tree的一樣
		 */
		TreeNode root = new TreeNode(8);
		root.left = new TreeNode(6);
		root.right = new TreeNode(9);
		root.left.left = new TreeNode(4);
		root.left.right = new TreeNode(7);
		root.right.right = new TreeNode(18);
		root.right.right.left = new TreeNode(11);
		root.right.right.right = new TreeNode(21);
		
		System.out.println(root.getLevelOrderList());
		String serializeString = serialize(root);
		System.out.println(serializeString);
		TreeNode newTree = deserialize(serializeString);
		System.out.println(newTree.getLevelOrderList());
	}

	// Encodes a tree to a single string.
    public static String serialize(TreeNode root) {
        
    	StringBuffer sb = new StringBuffer();
    	traversal(root, sb);
    	
    	return sb.toString();
    }

    private static void traversal(TreeNode node, StringBuffer sb) {
		
    	if (node == null) {
			sb.append("N,");
			return;
		}
    	
    	sb.append(node.val+",");
    	traversal(node.left, sb);
    	traversal(node.right, sb);
	}

	// Decodes your encoded data to tree.
    public static TreeNode deserialize(String data) {
        
    	String[] nodeArray = data.split(",");
    	Queue<String> nodeQueue = new LinkedList<>(Arrays.asList(nodeArray));
    	
    	return produceTree(nodeQueue);
    }

	private static TreeNode produceTree(Queue<String> nodeQueue) {
		
		String nodeValue = nodeQueue.poll();
		
		if (nodeValue.equals("N")) {
			return null;
		}
		
		TreeNode node = new TreeNode(Integer.parseInt(nodeValue));
		node.left = produceTree(nodeQueue);
		node.right = produceTree(nodeQueue);
		
		return node;
	}
}
