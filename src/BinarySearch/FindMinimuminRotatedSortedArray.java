package BinarySearch;

public class FindMinimuminRotatedSortedArray {

	public static void main(String[] args) {
		int[] nums1 = {8,9,1,2,3,4,5,6,7};
		int[] nums2 = {4,5,6,7,8,9,1,2,3};
		System.out.println(findMin(nums1));
		System.out.println(findMin(nums2));
	}

	public static int findMin(int[] nums) {
		
		if (nums.length == 1) {
			return nums[0];
		}
		
		int left = 0;
		int right = nums.length-1;
		if (nums[left] < nums[right]) {
			return nums[left];
		}
		
        return binarySearch(nums, left, right);
    }
	
	private static int binarySearch(int[] nums, int left, int right) {
		
		while (left <= right) {
		/**
		 * 思考：binary search要不要等於？ 
		 * 考慮這個list：123456789
		 * 若我想搜尋1，則找到1的時候會是left=1, right=2，這時候mid剛好指向1可以找到
		 * 但若想搜尋9，當left=8, right=9的時候，mid還是指向8，沒找到9
		 * 下一個loop，left=9, right=9時mid才會指向9，這時若條件沒有等於就進入不了這個loop
		 */
			int mid = (left+right)/2;
			if (isMin(nums, mid)) {
				return nums[mid];
			}
			if (nums[mid] > nums[right]) {
				return binarySearch(nums, mid+1, right);
			} else {
				return binarySearch(nums, left,  mid-1);
			}
		}
		/* 沒有search到才會return這個 */
		return 0;
	}

	public static boolean isMin(int[] nums, int i) {
		int right = i-1 < 0? nums.length-1 : i-1;
		int left = i+1 > nums.length-1? 0 : i+1;
		
		return nums[i] < nums[right] && nums[i] < nums[left];
	}
}
