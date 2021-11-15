package Tree;

import java.util.ArrayList;
import java.util.List;

import datastructure.TreeNode;

public class BinaryTreeInorderTraversal {

	public static void main(String[] args) {
		TreeNode node1 = new TreeNode(1);
		TreeNode node2 = new TreeNode(2);
		TreeNode node3 = new TreeNode(3);
		
		node1.right = node2;
		node2.left = node3;
		
		System.out.println(inorderTraversal(node1));
	}
	
	public static List<Integer> inorderTraversal(TreeNode root) {
		
		List<Integer> ans = new ArrayList<>();
		inorder(root, ans);
		return ans;
    }
	
	public static void inorder(TreeNode root, List<Integer> ans) {
		
		if (root == null) {
			
			return;
		}
        inorder(root.left, ans);
        ans.add(root.val);
        inorder(root.right, ans);
	}
}
