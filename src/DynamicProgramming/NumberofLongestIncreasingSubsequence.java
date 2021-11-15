package DynamicProgramming;

import java.util.HashMap;
import java.util.Map;

public class NumberofLongestIncreasingSubsequence {

	public static void main(String[] args) {
		
		int[] nums = {1,3,5,4,7};
		System.out.println(findNumberOfLIS(nums));
		int[] nums2 = {2,2,2,2,2};
		System.out.println(findNumberOfLIS(nums2));
	}

	public static int findNumberOfLIS(int[] nums) {
    	/**
    	 * dp[i][0] 為以第i個做為結尾的Longest Increasing Subsequence的長度
    	 * dp[i][1] 則是組成這個最長的subsequence共有幾組
    	 * dp[i][0] = max(dp[j][0]) + 1 if nums[i] > nums[j], j<i
    	 * dp[i][1] = max(dp[j][1])     if nums[i] > nums[j], j<i
    	 */
    	Map<Integer, Integer> countMap = new HashMap<>();
    	int[][] dp = new int[nums.length][2];
    	int longestLength = 1;
    	dp[0][0] = 1;
    	dp[0][1] = 1;
    	countMap.put(1, 1);
    	
    	for (int i = 1; i < dp.length; i++) {
    		
    		int longestBeforeI = 0;
    		int longestBeforeICount = 1;
    		
    		for (int j = 0; j < i; j++) {
    			
    			if (nums[j] < nums[i]) {
					
    				if (dp[j][0] > longestBeforeI) {
						longestBeforeI = dp[j][0];
						longestBeforeICount = dp[j][1];
					} else if (dp[j][0] == longestBeforeI) {
						
						longestBeforeICount += dp[j][1];
					}
				}
    		}
    		
    		dp[i][0] = longestBeforeI+1;
    		dp[i][1] = longestBeforeICount;
    		countMap.put(dp[i][0], countMap.getOrDefault(dp[i][0], 0)+dp[i][1]);
    		longestLength = Math.max(longestLength, dp[i][0]);
    	}
        
        return countMap.get(longestLength);
    }
}
