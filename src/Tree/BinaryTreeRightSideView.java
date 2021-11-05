package Tree;

import java.util.ArrayList;
import java.util.List;

import datastructure.TreeNode;

public class BinaryTreeRightSideView {

	public static void main(String[] args) {
		/**
		 * 給一棵二元樹
		 * 求出從右側由上往下看的node list
		 */
		TreeNode root = new TreeNode(1);
		root.left = new TreeNode(2);
		root.right = new TreeNode(3);
		root.left.right = new TreeNode(5);
		root.right.right = new TreeNode(4);
		
		System.out.println(root.getLevelOrderList());
		System.out.println(rightSideView(root));
	}

    public static List<Integer> rightSideView(TreeNode root) {
    	/**
    	 * 類似level-order-list
    	 * 但只要紀錄每一層最右邊的數字
    	 * 由於同一層，最右邊的一定最晚被遍歷到
    	 * 所以一旦該層有數字了，又碰到同層的就可以直接取代掉
    	 * => 用arraylist可以直接set
    	 */
    	ArrayList<Integer> result = new ArrayList<>();
    	int level = 0;
    	traversal(root, level, result);
    	
        return result;
    }

	private static void traversal(TreeNode node, int level, ArrayList<Integer> result) {
		
		if (node == null) {
			return;
		}
		
		if (level >= result.size()) {
			result.add(node.val);
		} else {
			result.set(level, node.val);
		}
		
		traversal(node.left, level+1, result);
		traversal(node.right, level+1, result);
	}
}
