package Tree;

import java.util.ArrayList;
import java.util.List;

import datastructure.TreeNode;

public class BinaryTreePaths {

	public static void main(String[] args) {
		
		TreeNode root = new TreeNode(1);
		root.left = new TreeNode(2);
		root.right = new TreeNode(3);
		
		root.left.right = new TreeNode(5);
		
		System.out.println(binaryTreePaths(root));
	}

    public static List<String> binaryTreePaths(TreeNode root) {
        
    	List<List<Integer>> results = new ArrayList<>();
    	List<Integer> result = new ArrayList<>();
    	traverse(root, result, results);
    	System.out.println(results);
    	
    	List<String> processResult = new ArrayList<>();
    	for (List<Integer> path : results) {
    		StringBuffer sb = new StringBuffer();
    		sb.append(path.get(0));
    		for (int i = 1; i < path.size(); i++) {
    			sb.append("->");
    			sb.append(path.get(i));
    		}
    		processResult.add(sb.toString());
    	}
    	
    	return processResult;
    }

	private static void traverse(TreeNode node, List<Integer> result, List<List<Integer>> results) {
		
		if (node == null) {
			
			return;
		}
		
		if (node.left == null && node.right == null) {
			
			result.add(node.val);
			results.add(new ArrayList<>(result));
			result.remove(result.size()-1);
			return;
		}
		
		result.add(node.val);
		traverse(node.left, result, results);
		traverse(node.right, result, results);
		result.remove(result.size()-1);
	}
}
