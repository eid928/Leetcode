package Tree;

import datastructure.TreeNode;

public class DeleteLeavesWithaGivenValue {

	public static void main(String[] args) {
		/**
		 * 給定一棵二元樹和一正整數target
		 * 刪除樹中值符合target的葉節點
		 * 若是一非葉節點的子節點被刪除後也符合條件，則該節點也需刪除
		 * 例如: 目標3
		 * 1
		 * \
		 * 3
		 * \
		 * 3
		 * 刪完後會只剩1
		 */
		TreeNode root = new TreeNode(1);
		root.left = new TreeNode(2);
		root.right = new TreeNode(3);
		
		root.left.left = new TreeNode(2);
		root.right.left = new TreeNode(2);
		root.right.right = new TreeNode(4);

		System.out.println(root.getLevelOrderList());
		
		TreeNode newRoot = removeLeafNodes(root, 2);
		
		System.out.println(newRoot.getLevelOrderList());
	}

    public static TreeNode removeLeafNodes(TreeNode root, int target) {
    	/**
    	 * 遞迴
    	 * 先針對左右子節點刪除
    	 * 之後再來check自己是否為葉節點
    	 */
    	if (root == null) {
			return null;
		}
    	
    	root.left = removeLeafNodes(root.left, target);
    	root.right = removeLeafNodes(root.right, target);
    	
    	if (root.left == null && root.right == null && root.val == target) {
			return null;
		}
    	
        return root;
    }
}
