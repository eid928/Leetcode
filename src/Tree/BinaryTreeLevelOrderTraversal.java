package Tree;
import java.util.ArrayList;
import java.util.List;

public class BinaryTreeLevelOrderTraversal {

	public static void main(String[] args) {
		
		TreeNode root = new TreeNode(3);
		
		TreeNode node9 = new TreeNode(9);
		TreeNode node11 = new TreeNode(11);
		TreeNode node8 = new TreeNode(8);
		
		TreeNode node20 = new TreeNode(20);
		TreeNode node15 = new TreeNode(15);
		TreeNode node7 = new TreeNode(7);
		
		root.left = node9;
		root.left.left = node11;
		root.left.right = node8;
		
		root.right = node20;
		root.right.left = node15;
		root.right.right = node7;
		
		List<List<Integer>> levelOrder = levelOrder(root);
		System.out.println(levelOrder);
	}

	public static List<List<Integer>> levelOrder(TreeNode root) {
        
		int currentLevel = 0;
		List<List<Integer>> levelList = new ArrayList<List<Integer>>();
		traversal(root, currentLevel, levelList);
		
		return levelList;
    }
	
	public static void traversal(TreeNode node, int currentLevel, List<List<Integer>> levelList) {
		
		if (node == null) {
			return;
		}
		System.out.println("level: " + currentLevel + ", val: " + node.val);
		if (currentLevel == levelList.size()) {
			levelList.add(new ArrayList<>());
		}
		levelList.get(currentLevel).add(node.val);
		currentLevel ++;
		traversal(node.left, currentLevel, levelList);
		traversal(node.right, currentLevel, levelList);
	}
	
	public static class TreeNode {
		int val;
		TreeNode left;
		TreeNode right;
		TreeNode() {}
		TreeNode(int val) { this.val = val; }
		TreeNode(int val, TreeNode left, TreeNode right) {
			this.val = val;
			this.left = left;
			this.right = right;
		}
	}
}
