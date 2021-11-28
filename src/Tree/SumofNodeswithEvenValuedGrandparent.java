package Tree;
import datastructure.TreeNode;

public class SumofNodeswithEvenValuedGrandparent {

	public static void main(String[] args) {
		/**
		 * 給定一棵樹
		 * 請求出所有grandparent節點值是偶數的節點值的和
		 */
		TreeNode root = new TreeNode(6);
		root.left = new TreeNode(7);
		root.right = new TreeNode(8);
		
		root.left.left = new TreeNode(2);
		root.left.right = new TreeNode(7);
		root.right.left = new TreeNode(1);
		root.right.right = new TreeNode(3);
		
		root.left.left.left = new TreeNode(9);
		root.left.right.left = new TreeNode(1);
		root.left.right.right = new TreeNode(4);
		root.right.right.right = new TreeNode(5);
		
		System.out.println(root.getLevelOrderList());
		System.out.println(sumEvenGrandparent(root));
	}

    public static int sumEvenGrandparent(TreeNode root) {
    	/**
    	 * dfs
    	 * 要隨時track節點的父節點以及grandparent節點的值
    	 */
        return dfs(root, -1, -1);
    }

	private static int dfs(TreeNode node, int parent, int grandParent) {
		
		if (node == null) {
			return 0;
		}
		return dfs(node.left, node.val, parent) + dfs(node.right, node.val, parent) + (grandParent%2 == 0 ? node.val : 0);
	}
}
