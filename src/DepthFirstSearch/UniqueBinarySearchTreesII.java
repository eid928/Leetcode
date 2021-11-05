package DepthFirstSearch;

import java.util.ArrayList;
import java.util.List;

import datastructure.TreeNode;

public class UniqueBinarySearchTreesII {

	public static void main(String[] args) {
		/**
		 * 和unique BST一樣，但要直接求出組合
		 * 給定一個正整數n
		 * 求出用1~n可組合出的所有BST
		 */
		List<TreeNode> generateTrees = generateTrees(3);
		for (TreeNode root : generateTrees) {
			System.out.println(root.getLevelOrderList());
		}
	}

    public static List<TreeNode> generateTrees(int n) {
    	/**
    	 * 困難，非常不好想
    	 * 首先加入順序不一樣不一定就是獨特BST
    	 * 例如加入順序2,1,3和2,3,1，組出的BST會是相同的
    	 * 所以不可看作是permutaion的變化題目
    	 * 一樣是遞迴，但直接回傳list
    	 */
    	return dfs(1, n);
    }

	private static List<TreeNode> dfs(int start, int end) {
		/**
		 * dfs會回傳範圍start~end的所有BST組合
		 * 邊界條件也就是單獨一個，start==end的時候，就單獨給該node
		 */
		if (start == end) {
			List<TreeNode> result = new ArrayList<>();
			result.add(new TreeNode(start));
			return result;
		}
		
		if (start > end) {
			return null;
		}
		
		List<TreeNode> result = new ArrayList<>();
		
		for (int i = start; i <= end; i++) {
			/**
			 * 接著就是建樹
			 * 以i為root
			 * 左邊的所有可能BST就是dfs小於i的範圍
			 * 右邊的所有可能BST就是dfs大於i的範圍
			 * 再將左右邊的所有BST用兩個for組合
			 */
			List<TreeNode> leftPart = dfs(start, i-1);
			List<TreeNode> rightPart = dfs(i+1, end);
			
			if (leftPart == null) {
				for (TreeNode rightChildren : rightPart) {
					TreeNode curNode = new TreeNode(i);
					curNode.right = rightChildren;
					result.add(curNode);
				}
				continue;
			}
			
			if (rightPart == null) {
				for (TreeNode leftChildren : leftPart) {
					TreeNode curNode = new TreeNode(i);
					curNode.left = leftChildren;
					result.add(curNode);
				}
				continue;
			}
			
			for (TreeNode leftChildren : leftPart) {
				for (TreeNode rightChildren : rightPart) {
					TreeNode curNode = new TreeNode(i);
					curNode.left = leftChildren;
					curNode.right = rightChildren;
					result.add(curNode);
				}
			}
		}
		return result;
	}
}
