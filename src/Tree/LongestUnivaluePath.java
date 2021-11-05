package Tree;

import datastructure.TreeNode;

public class LongestUnivaluePath {
	
	private static int longestPath;

	public static void main(String[] args) {
		/**
		 * 給定一棵二元樹，求出這棵樹中，所有node都是相同值得最長路徑
		 * 此路徑可以穿過subroot
		 * 例如：
		 * 4
		 * |\
		 * 4 4
		 * 這算是2(兩根edge)
		 */
		TreeNode root = new TreeNode(5);
		root.left = new TreeNode(4);
		root.right = new TreeNode(5);
		root.left.left = new TreeNode(1);
		root.left.right = new TreeNode(1);
		root.right.right = new TreeNode(5);
		
		System.out.println(longestUnivaluePath(root));
		
		root = new TreeNode(1);
		root.left = new TreeNode(4);
		root.right = new TreeNode(5);
		root.left.left = new TreeNode(4);
		root.left.right = new TreeNode(4);
		root.right.right = new TreeNode(5);
		
		System.out.println(longestUnivaluePath(root));
		
		root = new TreeNode(1);
		root.right = new TreeNode(1);
		root.right.left = new TreeNode(1);
		root.right.right = new TreeNode(1);
		root.right.left.left = new TreeNode(1);
		root.right.left.right = new TreeNode(1);
		root.right.right.left = new TreeNode(1);
		System.out.println(root.getLevelOrderList());
		System.out.println(longestUnivaluePath(root));
	}

	public static int longestUnivaluePath(TreeNode root) {
		/**
		 * 這題挺困難
		 * 先以一個static field紀錄最長路徑
		 * 然後調用DFS
		 */
		longestPath = 0;
		
		dfs(root);
		
        return longestPath;
    }

	private static int dfs(TreeNode node) {
		
		if (node == null) {
			return 0;
		}
		/**
		 * 分別對左右邊DFS
		 * 跟自己一樣的一邊就可以+1，否則歸零
		 */
		int left = dfs(node.left);
		int right = dfs(node.right);
		
		left = (node.left != null && node.val == node.left.val) ? left+1 : 0;
		right = (node.right != null && node.val == node.right.val) ? right+1 : 0;
		/**
		 * 例如：
		 * 4
		 * |\
		 * 4 4
		 * 對最上面的4來說，這裡會是1+1=2，這個2會拿去和longestPath做比對並記錄
		 */
		longestPath = Math.max(longestPath, left+right);
		/**
		 * 重點：
		 * 但是，若再往上回傳
		 *   4
		 *  /
		 * 4
		 * |\
		 * 4 4
		 * 中間的這個4不可以回傳2給最上面的4，因為要和最上面的4組合的話，只能選一邊最大的回傳
		 */
		return Math.max(left, right);
	}
}
