package Tree;

import java.util.Arrays;
import java.util.LinkedList;
import java.util.Queue;

public class SerializeandDeserializeBinaryTree {

	public static void main(String[] args) {

		TreeNode root = new TreeNode(6);
		root.left = new TreeNode(2);
		root.right = new TreeNode(8);
		root.left.left = new TreeNode(0);
		root.left.right = new TreeNode(4);
		root.right.left = new TreeNode(7);
		root.right.right = new TreeNode(9);
		root.left.right.left = new TreeNode(3);
		root.left.right.right = new TreeNode(5);
		
		String string = serialize(root);
		System.out.println("serialize: " + string);
		TreeNode deserializedTree = deserialize(string);
		System.out.println("S&D&S----: " + serialize(deserializedTree));
		
		root = new TreeNode(1);
		root.left = new TreeNode(2);
		root.right = new TreeNode(3);
		root.right.left = new TreeNode(4);
		root.right.right = new TreeNode(5);
		root.right.left.left = new TreeNode(6);
		root.right.left.right = new TreeNode(7);
		
		string = serialize(root);
		System.out.println("serialize: " + string);
		deserializedTree = deserialize(string);
		System.out.println("S&D&S----: " + serialize(deserializedTree));
	}

	public static String serialize(TreeNode root) {
		/* pre-order traverse*/
		StringBuffer sb = new StringBuffer();
		preOrderTraverse(root, sb);
		
        return sb.toString();
    }
	
	public static void preOrderTraverse(TreeNode node, StringBuffer sb) {
		
		if (node == null) {
			sb.append("N,");
			return;
		}
		sb.append(node.val+",");
		preOrderTraverse(node.left, sb);
		preOrderTraverse(node.right, sb);
	}

    // Decodes your encoded data to tree.
	public static TreeNode deserialize(String data) {
    	
    	if (data.equals("")) {
			return null;
		}
    	
    	String[] array = data.split(",");
    	Queue<String> q = new LinkedList<>(Arrays.asList(array));
    	
        return produceTree(q);
    }
    
    public static TreeNode produceTree(Queue<String> q) {
    	
    	String currentValue = q.poll();
		if (currentValue.equals("N")) {
			return null;
		}
    	
    	TreeNode node = new TreeNode(Integer.parseInt(currentValue));
    	node.left = produceTree(q);
    	node.right = produceTree(q);
    	
    	return node;
    }
    
    public static class TreeNode {
    	int val;
    	TreeNode left;
    	TreeNode right;
    	TreeNode(int x) { val = x; }
    }
}
