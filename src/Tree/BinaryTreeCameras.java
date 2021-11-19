package Tree;

import datastructure.TreeNode;

public class BinaryTreeCameras {

	public static void main(String[] args) {
		
		TreeNode root = new TreeNode(0);
		root.left = new TreeNode(0);
		root.left.left = new TreeNode(0);
		root.left.right = new TreeNode(0);
		
		System.out.println(minCameraCover(root));
		
		root = new TreeNode(0);
		root.left = new TreeNode(0);
		root.left.left = new TreeNode(0);
		root.left.left.left = new TreeNode(0);
		root.left.left.left.right = new TreeNode(0);
		
		System.out.println(minCameraCover(root));
	}
	
	private static int camera;

    public static int minCameraCover(TreeNode root) {
    	
    	camera = 0;
    	int rootStatus = dfs(root);
    	if (rootStatus == -1) {
			camera ++;
		}
    	
        return camera;
    }

	private static int dfs(TreeNode node) {
		/**
		 * 和Distribute Coins in Binary Tree很像
		 * 可以想像成由葉節點慢慢往上呈報的感覺
		 * 
		 * 1: 我有camera
		 * 0: 我沒有camera但我不需要
		 * -1: 我沒有camera而且我需要
		 */
		if (node == null) {
			return 0;
		}
		
		int leftChildStatus = dfs(node.left);
		int rightChildStatus = dfs(node.right);
		
		if (leftChildStatus == -1 || rightChildStatus == -1) {
			camera += 1;
			return 1;
		} else if (leftChildStatus == 1 || rightChildStatus == 1) {
			
			return 0;
		} else {
			return -1;
		}
	}
}
