package Tree;
import datastructure.TreeNode;

public class SubtreeofAnotherTree {

	public static void main(String[] args) {
		TreeNode subRoot = new TreeNode(4);
		subRoot.left = new TreeNode(1);
		subRoot.right = new TreeNode(2);
		
		TreeNode root = new TreeNode(3);
		root.left = subRoot;
		root.right = new TreeNode(5);
		
		System.out.println(isSubtree(root, subRoot));
	}
	
	public static boolean isSubtree(TreeNode root, TreeNode subRoot) {
		
		/* 非BST */
		/* 這題跟SameTree類似，可直接用同個方法 */
		if (root == null || subRoot == null) return false;
		if (isSameTree(root, subRoot)) return true;
		
        return isSubtree(root.left, subRoot) || isSubtree(root.right, subRoot);
    }
	
	public static boolean isSameTree(TreeNode p, TreeNode q) {
        
		if (p == null && q == null) {
			return true;
		}
		
		if (p == null || q == null) {
			return false;
		}
		if (p.val == q.val) {
			return isSameTree(p.left, q.left) && isSameTree(p.right, q.right);
		}
		
		return false;
    }
}
