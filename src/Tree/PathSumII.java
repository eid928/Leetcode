package Tree;

import java.util.ArrayList;
import java.util.List;

import datastructure.TreeNode;

public class PathSumII {

	public static void main(String[] args) {
		
		TreeNode root = new TreeNode(5);
		root.left = new TreeNode(4);
		root.right = new TreeNode(8);
		root.left.left = new TreeNode(11);
		root.right.left = new TreeNode(13);
		root.right.right = new TreeNode(4);
		root.left.left.left = new TreeNode(7);
		root.left.left.right = new TreeNode(2);
		root.right.right.left = new TreeNode(5);
		root.right.right.right = new TreeNode(1);
		
		System.out.println(pathSum(root, 22));
		
		root = new TreeNode(0);
		root.left = new TreeNode(1);
		root.right = new TreeNode(1);
		
		System.out.println(pathSum(root, 1));
	}

    public static List<List<Integer>> pathSum(TreeNode root, int targetSum) {
    	
    	List<List<Integer>> results = new ArrayList<>();
    	List<Integer> path = new ArrayList<>();
    	/* 求所有組合->dfs */
    	dfs(root, targetSum, 0, path, results);
    	
        return results;
    }

	private static void dfs(TreeNode node, int targetSum, int currSum, List<Integer> path, List<List<Integer>> results) {
		
		if (node == null) {
			return;
		}
		
    	if (currSum+node.val == targetSum && node.left == null && node.right == null) {
    		/* 走到leaf的時候檢查 */
    		path.add(node.val);
    		results.add(new ArrayList<>(path));
    		path.remove(path.size()-1); /* 這邊加完也要記得刪掉再return */
			return;
		}
    	
    	path.add(node.val);
    	dfs(node.left, targetSum, currSum+node.val, path, results);
    	dfs(node.right, targetSum, currSum+node.val, path, results);
    	path.remove(path.size()-1);
	}
}
