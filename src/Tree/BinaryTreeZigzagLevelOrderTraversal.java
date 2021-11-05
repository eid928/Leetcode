package Tree;

import java.util.ArrayList;
import java.util.LinkedList;
import java.util.List;

import datastructure.TreeNode;

public class BinaryTreeZigzagLevelOrderTraversal {

	public static void main(String[] args) {
		/**
		 * 給定一棵樹求出levelorderlist
		 * 但次序依層數不同而不同
		 * 偶數層從左數到右(root為0層)
		 * 奇數層從右數到左
		 */
		TreeNode root = new TreeNode(3);
		root.left = new TreeNode(9);
		root.right = new TreeNode(20);
		root.right.left = new TreeNode(15);
		root.right.right = new TreeNode(7);
		
		System.out.println(zigzagLevelOrder(root));
	}

    public static List<List<Integer>> zigzagLevelOrder(TreeNode root) {
        /**
         * 以linkedlist做遞迴，原因後述
         */
    	List<LinkedList<Integer>> results = new ArrayList<>();
    	
    	zigzagLevelOrderTraversal(root, 0, results);
    	
    	List<List<Integer>> resultList = new ArrayList<>(results);
    	
    	return resultList;
    }

	private static void zigzagLevelOrderTraversal(TreeNode node, int level, List<LinkedList<Integer>> results) {
		
		if (node == null) {
			return;
		}
		
		if (level >= results.size()) {
			results.add(new LinkedList<>());
		}
		
		/**
		 * 用Linkedlist的話，就可依照層數選擇加在後面or加在前面
		 */
		if (level % 2 == 0) {
			results.get(level).add(node.val);
		} else {
			results.get(level).addFirst(node.val);
		}
		
		/**
		 * traversal一樣先左在右，只差在如何插入linkedlist
		 * 若依照層數改變traversal順序的話，也無法處理處於不同subtree的節點順序(例如最下層的左右兩邊)
		 */
		zigzagLevelOrderTraversal(node.left, level+1, results);
		zigzagLevelOrderTraversal(node.right, level+1, results);
	}
}
