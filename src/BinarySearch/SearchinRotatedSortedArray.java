package BinarySearch;

public class SearchinRotatedSortedArray {

	public static void main(String[] args) {
		/**
		 * 有一個被rotate過且sorted的array，array中沒有duplicate
		 * 請找出target的index，否則回傳-1
		 * 時間複雜度需為O(logN)
		 */
		int[] nums = {4,5,6,7,0,1,2};
		System.out.println(search(nums, 0));
		System.out.println(search(nums, 3));
		System.out.println(search(new int[] {1}, 0));
		System.out.println(search(new int[] {1}, 1));
		int[] nums2 = {4,5,6,7,8,1,2,3};
		System.out.println(search(nums2, 8));
	}

    public static int search(int[] nums, int target) {
    	/**
    	 * 時間複雜度需為O(logN) => binary search
    	 * 當取到mid的時候，左右兩邊至少會有一邊是正常sorted的
    	 * 先看target有沒有在正常部分的範圍內，沒有就拋給另外一邊search
    	 */
    	int left = 0;
    	int right = nums.length-1;
    	
    	while (left <= right) {
    		
    		int mid = (left+right)/2;
    		System.out.println("left: "+nums[left]+", mid: "+nums[mid]+", right: "+nums[right]);
    		if (target > nums[right] && target < nums[left]) {
    			return -1;
    		}
    		if (target == nums[mid]) {
				return mid;
			}
    		if (target == nums[left]) {
				return left;
			}
    		if (target == nums[right]) {
				return right;
			}
    		if (nums[mid] > nums[left]) { /* 如果左邊是正常的 */
				if (target < nums[mid] && target > nums[left]) { /* 且target也在左邊的範圍內，就拋給左邊 */
					right = mid-1;
				} else { /* 否則就拋給右邊 */
					left = mid+1;
				}
			} else { /* 如果右邊是正常的 */
				if (target > nums[mid] && target < nums[right]) { /* 且target也在右邊的範圍內，就拋給右邊 */
					left = mid+1;
				} else { /* 否則就拋給左邊 */
					right = mid-1;
				}
			}
    	}
    	
        return -1;
    }
}
