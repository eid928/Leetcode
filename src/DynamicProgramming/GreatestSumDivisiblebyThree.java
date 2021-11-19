package DynamicProgramming;

import java.util.Arrays;

public class GreatestSumDivisiblebyThree {

	public static void main(String[] args) {
		
		int[] nums = {3,6,5,1,8};
		System.out.println(maxSumDivThree(nums)); // 18, Pick numbers 3, 6, 1 and 8
		int[] nums2 = {4};
		System.out.println(maxSumDivThree(nums2)); // 0, do not pick any number
		int[] nums3 = {1,2,3,4,4};
		System.out.println(maxSumDivThree(nums3)); // 12, Pick numbers 1, 3, 4 and 4
		int[] nums4 = {4,1,5,3,1};
		System.out.println(maxSumDivThree(nums4)); // 12, 4+5+3
		int[] nums5 = {7,3,7,4,2,2,7,5};
		System.out.println(maxSumDivThree(nums5)); // 33
	}

    public static int maxSumDivThree(int[] nums) {
        /**
         * dp tabulation
         * dp[i][0]表示到i為止的最大和，除3餘0
         * dp[i][1]表示到i為止的最大和，除3餘1
         * dp[i][2]表示到i為止的最大和，除3餘2
         * 
         * table: 
         *   3  6  5  1  8
         * 0 3  9  9  15 18
         * 1 x  x  x  10 x
         * 2 x  x  14 x  23
         * 
         * 轉移方程式很複雜，直接看程式
         */
    	int[][] dp = new int[nums.length][3];
    	for (int i = 0; i < dp.length; i++) {
    		Arrays.fill(dp[i], -1); // -1表示無組合
    	}
    	
    	int first = nums[0];
    	dp[0][first % 3] = first;
    	
    	for (int i = 1; i < dp.length; i++) {
    		int curNum = nums[i];
    		int lastRemain0 = dp[i-1][0];
    		int lastRemain1 = dp[i-1][1];
    		int lastRemain2 = dp[i-1][2];
    		
    		if (curNum % 3 == 0) {
    			dp[i][0] = lastRemain0 == -1 ? nums[i] : lastRemain0 + nums[i];
    			dp[i][1] = lastRemain1 == -1 ? lastRemain1 : lastRemain1 + nums[i];;
    			dp[i][2] = lastRemain2 == -1 ? lastRemain2 : lastRemain2 + nums[i];;
			}
    		if (curNum % 3 == 1) {
    			dp[i][0] = lastRemain2 == -1 ? lastRemain0 : Math.max(lastRemain0, lastRemain2 + nums[i]); 
    			/* 與上一組的餘數2搭配組成餘數0 */
    			dp[i][1] = lastRemain0 == -1 ? Math.max(lastRemain1, nums[i]) : Math.max(lastRemain1, lastRemain0 + nums[i]);
    			/* 與上一組的餘數0搭配組成新的餘數1，若上一組沒有餘數0，則看自己跟上一組餘數1 */
    			dp[i][2] = lastRemain1 == -1 ? lastRemain2 : Math.max(lastRemain2, lastRemain1 + nums[i]);
			}
    		if (curNum % 3 == 2) {
    			dp[i][0] = lastRemain1 == -1 ? lastRemain0 : Math.max(lastRemain0, lastRemain1 + nums[i]);
    			dp[i][1] = lastRemain2 == -1 ? lastRemain1 : Math.max(lastRemain1, lastRemain2 + nums[i]);
    			dp[i][2] = lastRemain0 == -1 ? Math.max(lastRemain2, nums[i]) : Math.max(lastRemain2, lastRemain0 + nums[i]);
			}
    	}
    	
    	return dp[nums.length-1][0];
    }
}
