package DynamicProgramming;

public class MaximumProductSubarray {

	public static void main(String[] args) {
		int[] nums1 = {2,3,-2,4};
		int[] nums2 = {-2,0,-1};

		System.out.println(maxProduct(nums1));
		System.out.println(maxProduct(nums2));
	}

	public static int maxProduct(int[] nums) {
		
		if (nums.length == 0) {
			return 0;
		}
		/**
		 * dp[i][0]放以i為subarray最尾時的最大乘積
		 * dp[i][0]放以i為subarray最尾時的最小乘積
		 */
		int[][] dp = new int[nums.length][2];
		dp[0][0] = nums[0];
		dp[0][1] = nums[0];
		int max = dp[0][0];
		
		for (int i = 1; i < nums.length; i ++) {
			/**
			 * nums[i]為subarray最尾的最大乘積為自己(上一個是0)、自己跟上一個最大相乘(自己是正數)、自己跟上一個最小相乘(自己是負數)的最大
			 */
			int maxOfTheIndex = Math.max(Math.max(dp[i-1][0] * nums[i], dp[i-1][1] * nums[i]), nums[i]);
			int minOfTheIndex = Math.min(Math.min(dp[i-1][0] * nums[i], dp[i-1][1] * nums[i]), nums[i]);
			dp[i][0] = maxOfTheIndex;
			dp[i][1] = minOfTheIndex;
			System.out.print(dp[i][0] + ":" + dp[i][1] + ", ");
			max = Math.max(max, dp[i][0]);
		}
		
        return max;
    }
}
