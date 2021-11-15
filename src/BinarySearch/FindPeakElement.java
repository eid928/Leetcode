package BinarySearch;

public class FindPeakElement {

	public static void main(String[] args) {
		/**
		 * peak為比左右兩元素都要大
		 * 請找出array中的peak
		 * 若有多個peak，回傳其中一個即可
		 * 可以把nums[-1]和nums[length]當作負無限
		 * 時間複雜度需為O(logN)
		 */
		int[] nums = {1,2,3,1};
		System.out.println(findPeakElement(nums));
		int[] nums2 = {1,2,1,3,5,6,4}; // 2or6都是
		System.out.println(findPeakElement(nums2));
	}

    public static int findPeakElement(int[] nums) {
    	/**
    	 * O(logN) => 二分查找
    	 * 判斷mid是不是peak，若不是的話，往高的地方爬
    	 */
    	int left = 0;
    	int right = nums.length-1;
    	
    	while (left <= right) {
    		int mid = (left+right)/2;
    		if (isPeak(nums, mid)) {
				return mid;
			}
    		
    		if (nums[mid+1] > nums[mid]) {
				left = mid+1;
			} else {
				right = mid-1;
			}
    	}
        
    	return 0;
    }
    
    private static boolean isPeak(int[] nums, int index) {
    	
    	if (index < 0 || index > nums.length-1) {
			return false;
		}
    	
    	int left = 0;
    	int right = 0;
    	boolean hasLeft = true;
    	boolean hasRight = true;
    	
    	try {
    		left = nums[index-1];
    	} catch (Exception e) {
			hasLeft = false;
		}
    	
    	try {
    		right = nums[index+1];
    	} catch (Exception e) {
			hasRight = false;
		}
    	
    	if (hasRight && hasLeft) {
    		return nums[index] > left && nums[index] > right;
		} else if (hasRight) {
			return nums[index] > right;
		} else if (hasLeft) {
			return nums[index] > left;
		} else {
			return true;
		}
    }
}
