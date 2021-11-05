package BinarySearch;

import java.util.ArrayList;
import java.util.HashMap;
import java.util.List;
import java.util.Map;

import datastructure.TreeNode;

public class FindModeinBinarySearchTree {

	private static int maxCount = 0;
	
	public static void main(String[] args) {
		
		TreeNode root = new TreeNode(1);
		root.right = new TreeNode(2);
		root.right.left = new TreeNode(2);
		
		System.out.println(root.getLevelOrderList());
		
		int[] findMode = findMode(root);
		for (int i = 0; i < findMode.length; i++) {
			System.out.print(findMode[i] + ",");
		}
	}
    public static int[] findMode(TreeNode root) {
        /**
         * 用map紀錄count需要額外space，O(n)
         */
    	Map<Integer, Integer> countMap = new HashMap<>();
    	maxCount = 0;
    	inOrderTraverse(root, countMap);
    	
    	List<Integer> modes = new ArrayList<>();
    	
    	for (Integer value : countMap.keySet()) {
    		if (countMap.get(value) == maxCount) {
				modes.add(value);
			}
    	}
    	
    	int[] ans = new int[modes.size()];
    	for (int i = 0; i < modes.size(); i++) ans[i] = modes.get(i);
    	
    	return ans;
    }
    
	private static void inOrderTraverse(TreeNode root, Map<Integer, Integer> countMap) {
		
		if (root == null) {
			return;
		}
		
		inOrderTraverse(root.left, countMap);
		
		if (countMap.containsKey(root.val)) {
			countMap.put(root.val, countMap.get(root.val) + 1);
		} else {
			countMap.put(root.val, 1);
		}
		
		maxCount = Math.max(maxCount, countMap.get(root.val));
		
		inOrderTraverse(root.right, countMap);
	}
}
