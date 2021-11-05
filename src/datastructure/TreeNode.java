package datastructure;

import java.util.ArrayList;
import java.util.List;

public class TreeNode {
	public int val;
	public TreeNode left;
	public TreeNode right;
	public TreeNode() {}
	public TreeNode(int val) { this.val = val; }
	public TreeNode(int val, TreeNode left, TreeNode right) {
		this.val = val;
		this.left = left;
		this.right = right;
	}
	
	public List<List<String>> getLevelOrderList() {
		
		List<List<String>> levelOrderList = new ArrayList<>();
		levelOrderTraverse(this, levelOrderList, 0);
		
		return levelOrderList;
	}
	
	private void levelOrderTraverse(TreeNode treeNode, List<List<String>> levelOrderList, int level) {
		
		if (level >= levelOrderList.size()) {
			levelOrderList.add(new ArrayList<>());
		}
		
		if (treeNode == null) {
			levelOrderList.get(level).add("NULL");
			return;
		}
		
		levelOrderList.get(level).add(Integer.toString(treeNode.val));
		levelOrderTraverse(treeNode.left, levelOrderList, level + 1);
		levelOrderTraverse(treeNode.right, levelOrderList, level + 1);
	}
}
