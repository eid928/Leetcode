package BinarySearch;
import datastructure.TreeNode;

public class ConvertSortedArraytoBinarySearchTree {

	public static void main(String[] args) {
		
		int[] nums = {-10,-3,0,5,9};
		TreeNode root = sortedArrayToBST(nums);
		System.out.println(root.getLevelOrderList());
	}
	
    public static TreeNode sortedArrayToBST(int[] nums) {
        
    	return binarySearch(nums, 0, nums.length-1);
    }

	private static TreeNode binarySearch(int[] nums, int left, int right) {
		
		if (left > right) {
			/**
			 * 沒有等號
			 * 在剩下兩個的時候，一定一個是parent一個是child
			 * 因為剩下兩個時，mid = left，所以left那個會是parent
			 * 則right會是right child，即binarySearch(nums, mid+1=right, right)
			 */
			return null;
		}
		
		int mid = (left + right) / 2;
		
		TreeNode node = new TreeNode(nums[mid]);
		
		node.left = binarySearch(nums, left, mid-1); /* 不包含mid否則root可能被重複抓到 */
		node.right = binarySearch(nums, mid+1, right);
		
		return node;
	}
}
