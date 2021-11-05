package Tree;
import java.util.ArrayList;
import java.util.List;

public class KthSmallestElementinaBST {

	public static void main(String[] args) {
		
		TreeNode root = new TreeNode(5);
		root.right = new TreeNode(6);
		root.left = new TreeNode(3);
		root.left.right = new TreeNode(4);
		root.left.left = new TreeNode(2);
		root.left.left.left = new TreeNode(1);
		
		System.out.println(kthSmallest(root, 3));
	}

	public static int kthSmallest(TreeNode root, int k) {
        
		List<Integer> orderList = new ArrayList<>();
		inOrderTraversal(root, orderList, k);
		System.out.println(orderList);
		return orderList.get(k-1);
    }
	
	public static void inOrderTraversal(TreeNode node, List<Integer> orderList, int k) {
		
		if (node == null) {
			return;
		}
		
		if (orderList.size() >= k) {
			return;
		}
		
		inOrderTraversal(node.left, orderList, k);
		orderList.add(node.val);
		inOrderTraversal(node.right, orderList, k);
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
