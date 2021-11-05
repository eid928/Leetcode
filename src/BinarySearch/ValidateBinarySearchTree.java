package BinarySearch;
import datastructure.TreeNode;

public class ValidateBinarySearchTree {

	public static void main(String[] args) {
		
		TreeNode root = new TreeNode(5);
		root.left = new TreeNode(4);
		root.right = new TreeNode(6);
		root.right.left = new TreeNode(3);
		root.right.right = new TreeNode(7);
		/* 
		 * 這題很困難，不能一個Node一個node看 
		 * 例如這個tree，雖然3-6-7是合法的
		 * 但3卻在5的右邊，一次只看node下方是無法抓到的
		 * 所以要隨時track node的max和min
		 * */
		
		System.out.println(root.getLevelOrderList());
		System.out.println(isValidBST(root));
	}

	public static boolean isValidBST(TreeNode root) {
		
		if (root == null) {
			return true;
		}
		
		return isValidNode(root, Long.MAX_VALUE, Long.MIN_VALUE);
    }
	
	public static boolean isValidNode(TreeNode node, long max, long min) {
		
		if (node == null) {
			return true;
		}
		
		if (node.val >= max || node.val <= min) { /* 有等於因為等於是不合法的 */
			return false;
		}
		
		return isValidNode(node.left, node.val, min) && isValidNode(node.right, max, node.val);
	}
}
