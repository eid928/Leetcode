package Tree;

import java.util.ArrayList;
import java.util.HashSet;
import java.util.List;
import java.util.Set;

import datastructure.TreeNode;

public class AllNodesDistanceKinBinaryTree {

	public static void main(String[] args) {
		/**
		 * 考慮一二元樹，以及樹中一目標節點
		 * 求出距離目標節點為k的所有節點
		 * 距離為k，包含子節點以及父節點
		 */
		TreeNode root = new TreeNode(3);
		
		root.left = new TreeNode(5);
		root.right = new TreeNode(1);
		
		root.left.left = new TreeNode(6);
		root.left.right = new TreeNode(2);
		root.right.left = new TreeNode(0);
		root.right.right = new TreeNode(8);
		
		root.left.right.left = new TreeNode(7);
		root.left.right.right = new TreeNode(4);
		
		TreeNode target = root.left;

		System.out.println(distanceK(root, target, 2));
	}
	
	private static List<TreeNode> pathToTarget;
	private static Set<TreeNode> pathSet;

    public static List<Integer> distanceK(TreeNode root, TreeNode target, int k) {
        /**
         * 先遍歷求出root到target的path，分別存成list以及set
         * 接著從target開始，往下看距離為k的子節點
         * 距離target為1的父節點，則往下看距離為k-1的子節點，以此類推
         * 但往下看子節點，需避開原本的路徑，也就是說若是父節點的左邊為路徑，則此父節點只能往右邊看 => 用set來看
         * 而原本就在路徑上距離target為k的節點，則在主動增加
         * 此方法效能不佳，應有優化解
         */
    	List<Integer> result = new ArrayList<>();
    	List<TreeNode> path = new ArrayList<>();
    	pathToTarget = null;
    	traverse(root, target, path);
    	
    	int i = pathToTarget.size() - 1;
    	
    	while (k >= 0 && i >= 0) {
    		
    		TreeNode currNode = pathToTarget.get(i);
    		
    		if (k == 0) {
    			result.add(currNode.val);
			}
			if (!pathSet.contains(currNode.left)) {
    			findDistanceKChildren(currNode.left, k-1, result, currNode == target ?  null : target);
			}
    		if (!pathSet.contains(currNode.right)) {
    			findDistanceKChildren(currNode.right, k-1, result, currNode == target ?  null : target);
			}
    		
    		k --;
    		i --;
    	}
    	return result;
    }

	private static void findDistanceKChildren(TreeNode node, int k, List<Integer> result, TreeNode target) {
		
		if (node == null || k < 0) {
			return;
		}
		
		if (k == 0 && !pathSet.contains(node)) {
			result.add(node.val);
			return;
		}
		
		findDistanceKChildren(node.left, k-1, result, target);
		findDistanceKChildren(node.right, k-1, result, target);
	}

	private static void traverse(TreeNode node, TreeNode target, List<TreeNode> path) {
		
		if (node == null) {
			
			return;
		}
		
		if (node == target) {
			path.add(target);
			pathToTarget = new ArrayList<>(path);
			pathSet = new HashSet<>(path);
			return;
		}
		
		path.add(node);
		traverse(node.left, target, path);
		traverse(node.right, target, path);
		path.remove(path.size()-1);
	}
}
