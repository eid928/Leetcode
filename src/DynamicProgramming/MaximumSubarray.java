package DynamicProgramming;


public class MaximumSubarray {
	
	public static void main(String[] args) {
		int[] nums = {-2,1,-3,4,-1,2,1,-5,4};
		int ans = MaximumSubarray.maxSubArray(nums);
		System.out.println(ans);
	}
	
	public static int maxSubArray(int[] nums) {
		
		if (nums.length == 1) {
			return nums[0];
		}
		
		int currentSubArray = nums[0];
		int maxSubArray = nums[0];
		
		for (int i = 1; i < nums.length; i ++) {
			if (nums[i] > currentSubArray + nums[i]) {
				currentSubArray = nums[i];
			} else {
				currentSubArray += nums[i];
			}
			
			maxSubArray = Math.max(currentSubArray, maxSubArray);
		}
		
		return maxSubArray;
    }
}
