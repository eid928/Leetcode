package BinarySearch;
import java.util.ArrayList;
import java.util.List;

public class LowestCommonAncestorofaBinarySearchTree {

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
		
		System.out.println(lowestCommonAncestor(root, root.left, root.right).val);
	}

	public static TreeNode lowestCommonAncestor(TreeNode root, TreeNode p, TreeNode q) {
		
		if (p.equals(root) || q.equals(root)) {
			return root;
		}
		
		/* 先搜尋p，記錄到p經過的Node */
		List<TreeNode> nodeInPath = new ArrayList<>();
		search(root, p, nodeInPath);
		
        return findAncestor(root, q, nodeInPath, 0); /* 根據記錄的node來做比對 */
    }
	
	public static void search(TreeNode node, TreeNode p, List<TreeNode> nodeInPath) {
		
		nodeInPath.add(node);
		if (node.val == p.val) {
			return;
		}
		if (node.val > p.val) {
			search(node.left, p, nodeInPath);
		} else {
			search(node.right, p, nodeInPath);
		}
	}
	
	public static TreeNode findAncestor(TreeNode node, TreeNode q, List<TreeNode> nodeInPath, int index) {
		
		if (index >= nodeInPath.size() || node.val != nodeInPath.get(index).val) { /* 出現叉路or q比p遠的狀況 */
			return nodeInPath.get(index-1);
		}
		if (node.val == q.val) { /* q比p近的狀況 */
			return nodeInPath.get(index);
		}
		index++;
		if (node.val > q.val) {
			return findAncestor(node.left, q, nodeInPath, index);
		} else {
			return findAncestor(node.right, q, nodeInPath, index);
		}
	}
	
	public static class TreeNode {
		int val;
		TreeNode left;
		TreeNode right;
		TreeNode(int x) { val = x; }
	}
}
