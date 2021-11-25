package Tree;

import datastructure.TreeNode;

public class MaximumBinaryTree {

	public static void main(String[] args) {
		/**
		 * 給定一組array
		 * 建立Maximum Binary Tree
		 * 每次找數組中最大的數: x當作root的值
		 * root的左節點是數組中x左邊最大的
		 * root的右節點是數組中x右邊最大的
		 * 以此類推
		 */
		int[] nums = {3,2,1,6,0,5};
		System.out.println(constructMaximumBinaryTree(nums).getLevelOrderList());
	}

    public static TreeNode constructMaximumBinaryTree(int[] nums) {
        
    	return construct(nums, 0, nums.length-1);
    }

	private static TreeNode construct(int[] nums, int left, int right) {
		/**
		 * 單純的遞迴
		 * 由於找出max為線性O(n)
		 * 而樹的高度平均為logn，最大為n
		 * 所以average time: O(nlogn), worst time: O(n^2)
		 */
		if (left > right) {
			return null;
		}
		
		if (left == right) {
			return new TreeNode(nums[left]);
		}
		
		int maxIndex = findMaxIndex(nums, left, right);
		TreeNode node = new TreeNode(nums[maxIndex]);
		node.left = construct(nums, left, maxIndex-1);
		node.right = construct(nums, maxIndex+1, right);
		
		return node;
	}

	private static int findMaxIndex(int[] nums, int left, int right) {
		
		int max = Integer.MIN_VALUE;
		int maxIndex = 0;
		
		for (int i = left; i <= right; i++) {
			if (nums[i] > max) {
				max = nums[i];
				maxIndex = i;
			}
		}
		
		return maxIndex;
	}
}
