package BinarySearch;

public class SearchinRotatedSortedArrayII {

	public static void main(String[] args) {
		/**
		 * 和Search in Rotated Sorted Array大致一樣
		 * 但array中有可能會有duplicate
		 */
		int[] nums = {2,5,6,0,0,1,2};
		System.out.println(search(nums, 0));
		System.out.println(search(nums, 3));
		int[] nums2 = {1,1,1,1,1,1,1,1,1,1,1,1,1,2,1,1,1,1,1};
		System.out.println(search(nums2, 2));
	}

    public static boolean search(int[] nums, int target) {
    	/*
    	 * 解法大致上一樣，左右兩邊至少有一邊是正常的
    	 * 但因為存在重複的數字，在判斷哪一邊是正常的較為困難
    	 * 例如說nums[mid] == nums[left] == nums[right]:
    	 * 1,2,3,4,1,1,1,1,1
    	 * 這種狀況只能先暴力搜尋某一側
    	 * 所以時間複雜度上
    	 * best case為O(logN)
    	 * worst case為O(N)
    	 */
    	int left = 0;
    	int right = nums.length-1;
    	
    	while (left <= right) {
    		
    		int mid = (left+right)/2;
    		System.out.println("left: "+left+", mid: "+mid+", right: "+right);
    		if (target == nums[mid] || target == nums[left] || target == nums[right]) {
				return true;
			}
    		
    		if (target > nums[right] && target < nums[left]) return false;
    		
    		if (nums[mid] > nums[left]) {
    			
    			if (target < nums[mid] && target > nums[left]) {
					right = mid-1;
				} else {
					left = mid+1;
				}
    		} else if (nums[mid] < nums[right]){
				
    			if (target > nums[mid] && target < nums[right]) {
					left = mid+1;
				} else {
					right = mid-1;
				}
			} else {
				/**
				 * case3: nums[mid] == nums[left] == nums[right]
				 * ex: 1,2,3,4,1,1,1,1,1
				 * 直接暴力搜尋某一側，不行就拋給另一邊
				 */
				for (int i = left; i < mid; i++) {
					if (nums[i] == target) return true;
				}
				left = mid+1;
			}
    	}
    	
        return false;
    }
}
