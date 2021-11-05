package SlidingWindow;

public class MinimumSizeSubarraySum {

	public static void main(String[] args) {
		/**
		 * 給定一組array以及一target
		 * array中的數字全部都大於等於1
		 * 求出array中最小的連續subarray的長度，其和>=target
		 */
		int[] nums = {2,3,1,2,4,3};
		System.out.println(minSubArrayLen(7, nums));
		int[] nums2 = {1,4,4};
		System.out.println(minSubArrayLen(4, nums2));
		int[] nums3 = {1,1,1,1,1,1,1,1};
		System.out.println(minSubArrayLen(11, nums3));
		int[] nums4 = {1,2,3,4,5};
		System.out.println(minSubArrayLen(11, nums4));
	}

    public static int minSubArrayLen(int target, int[] nums) {
    	/**
    	 * 全部數字皆為正數，且subarray為連續的=>sliding window
    	 */
        if (nums.length == 0) {
			return 0;
		}
        
    	int left = 0;
    	int right = 0;
    	int curSum = nums[0];
    	int minLen = Integer.MAX_VALUE;
    	
    	while (right < nums.length) {
    		
    		if (curSum < target) {
				right ++;
				if (right < nums.length) curSum += nums[right];
			} else {
				minLen = Math.min(minLen, right-left+1);
				curSum -= nums[left];
				left ++;
			}
    	}
    	
    	return minLen == Integer.MAX_VALUE ? 0 : minLen;
    }
}
