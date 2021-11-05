package Tree;

import java.util.HashMap;
import java.util.Map;

import datastructure.TreeNode;

public class ConstructBinaryTreefromPreorderandInorderTraversal {
	
	private static int preorderIndex;
	
	public static void main(String[] args) {
		
		int[] preorder = {1,2,4,5,3,6,7};
		int[] inorder = {4,2,5,1,6,3,7};
		System.out.println(buildTree(preorder, inorder).getLevelOrderList());
	}

	public static TreeNode buildTree(int[] preorder, int[] inorder) {
		
		Map<Integer, Integer> inorderMap = new HashMap<>();
		preorderIndex = 0;
		for (int i = 0; i < inorder.length; i++) {
			inorderMap.put(inorder[i], i);
		}
		
		return arrayToTree(preorder, inorderMap, 0, preorder.length-1);
    }

	private static TreeNode arrayToTree(int[] preorder, Map<Integer, Integer> inorderMap, int left, int right) {
		/* 
		 * preorder中，從0開始，每一個都是某棵樹的root
		 * 例如1234567(levelorder)，2是245這棵樹的root
		 * 在recursion的過程中，用preorderIndex來記錄現在在處理preoder中的哪個element
		 * 以preorder為主來迭代，從inorder得知目前這個root統轄的範圍left和right
		 */
		
		if (preorderIndex > preorder.length) {
			return null;
		}
		if (left > right) {
			return null;
		}
		int currentVal = preorder[preorderIndex];
		TreeNode currentRoot = new TreeNode(currentVal);
		/* preorder = {1,2,4,5,3,6,7};*/
		/* 1看完了看2 */
		preorderIndex++;
		
		Integer rootIndex = inorderMap.get(currentVal); /* 現在root在inorder的位置 */
		/* inorder = {4,2,5,1,6,3,7}; */
		/* 呼叫.left時，preorderIndex=2，他統轄的範圍為left~rootIndex-1 */
		
		currentRoot.left = arrayToTree(preorder, inorderMap, left, rootIndex-1);
		currentRoot.right = arrayToTree(preorder, inorderMap, rootIndex+1, right);
		
		return currentRoot;
	}
}
