package DynamicProgramming;

public class HouseRobberII {

	public static void main(String[] args) {
		/**
		 * 和House Robber一樣
		 * 但房子是圍成一圈，頭尾相連
		 */
		int[] nums = {2,3,2};
		int[] nums2 = {1,2,3,1};
		int[] nums3 = {1,2,3};
		
		System.out.println(rob(nums)); // 3
		System.out.println(rob(nums2)); // 4
		System.out.println(rob(nums3)); // 3
	}

    public static int rob(int[] nums) {
    	/**
    	 * 一樣的做法，但要遍歷兩次
    	 * 頭尾相連的話，只有三種狀況
    	 * 搶頭，搶尾，都不搶
    	 * 那就把尾去掉，做一遍，紀錄maxWithFirstHouse
    	 * 接著把頭去掉，做一遍，紀錄maxWithLastHouse
    	 * 取兩者較大即是答案
    	 */
    	if (nums.length < 2) {
			return nums[0];
		}
    	
    	int[][] dp = new int[nums.length][2];
    	/**
    	 * 0: 不搶，下一間房子可以是0也可以是1
    	 * 1: 搶，下一間房子只能是0
    	 */
    	dp[0][0] = 0;
    	dp[0][1] = nums[0];
    	
    	for (int i = 1; i < nums.length-1; i++) {
    		
    		dp[i][0] = Math.max(dp[i-1][0], dp[i-1][1]);
    		dp[i][1] = dp[i-1][0] + nums[i];
    	}
    	
    	int maxWithFirstHouse = Math.max(dp[dp.length-2][0], dp[dp.length-2][1]);
    	
    	dp = new int[nums.length][2];
    	
    	dp[1][0] = 0;
    	dp[1][1] = nums[1];
    	
    	for (int i = 2; i < nums.length; i++) {
    		
    		dp[i][0] = Math.max(dp[i-1][0], dp[i-1][1]);
    		dp[i][1] = dp[i-1][0] + nums[i];
    	}
    	
    	int maxWithLastHouse = Math.max(dp[dp.length-1][0], dp[dp.length-1][1]);
    	
        return Math.max(maxWithFirstHouse, maxWithLastHouse);
    }
}
