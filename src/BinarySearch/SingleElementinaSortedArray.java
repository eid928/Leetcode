package BinarySearch;

public class SingleElementinaSortedArray {

	public static void main(String[] args) {
		/*
		 * 給定一組sorted array
		 * 幾乎所有的元素都會連續出現兩次，唯獨只有一個元素會單獨出現
		 * 請找出並回傳該單獨元素
		 * time需求O(logN)
		 */
		int[] nums = {1,1,2,3,3,4,4,8,8};
		System.out.println(singleNonDuplicate(nums));
		int[] nums2 = {3,3,7,7,10,11,11};
		System.out.println(singleNonDuplicate(nums2));
	}

    public static int singleNonDuplicate(int[] nums) {
        /*
         * binary search
         * 單獨數出現在左邊還是右邊
         * 可以從mid的位置和mid的重複數在左邊和右邊來判斷
         *                 mid在奇數      mid在偶數
         * mid的夥伴在右  左邊正常,往右   右邊正常,往左 
         * mid的夥伴在左  右邊正常,往左   左邊正常,往右
         */
    	int left = 0;
    	int right = nums.length-1;
    	
    	while (left <= right) {
    		
    		int mid = (left+right)/2;
    		
    		if (isSingle(nums, left)) {
				return nums[left];
			}
    		
    		if (isSingle(nums, right)) {
				return nums[right];
			}
    		
    		if (isSingle(nums, mid)) {
				return nums[mid];
			}
    		
    		if (mid % 2 == 0) { // 1,1,2,3,3,4,4,8,8
				
    			if (nums[mid] == nums[mid-1]) {
					right = mid - 1;
				} else {
					left = mid + 1;
				}
			} else { // 3,3,7,7,10,11,11
				
				if (nums[mid] == nums[mid-1]) {
					left = mid + 1;
				} else {
					right = mid - 1;
				}
			}
    	}
    	
    	return -1;
    }

	private static boolean isSingle(int[] nums, int index) {
		
		int value = nums[index];
		
		if (index == 0) {
			
			return index+1 >= nums.length ? true : value != nums[index+1];
			
		} else if (index == nums.length-1) {
			
			return value != nums[index-1];
			
		} else {
			
			return value != nums[index+1] && value != nums[index-1];
		}
	}
}
