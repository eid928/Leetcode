package DynamicProgramming;

public class LongestIncreasingSubsequence {

	public static void main(String[] args) {
		/**
		 * 給定一個array
		 * 求出最長的遞增subsequence是多長
		 * subsequence可以是不連續的，例如此例題
		 * 2,5,6,101是最長的subsequence
		 */
		int [] nums = {10,9,2,5,3,7,101,18};
		System.out.println(lengthOfLIS(nums));
	}
	
	public static int lengthOfLIS(int[] nums) {
		
		/**
		 * 優化做法，做一個長度可以變化的dp
		 * 遍歷nums
		 * 1. 若是遍歷到的數字小於dp的首字，則替代
		 * 2. 若是遍歷到的數字大於dp的尾字，則add
		 * 3. 若是，dp首字<遍歷到的數字<dp尾字，則將這個遍歷到的數字替代掉dp中第一個比他大的數字(用二分查找)
		 * O(NlogN)
		 * 以例題來說，dp的變化會是：
		 * 10->9->2->2,5->2,3->2,3,7->2,3,7,101->2,3,7,18
		 */
		
		
		return 0;
	}
	
    public static int lengthOfLIS2(int[] nums) {
    	
    	/**
    	 * dp裡存的是以這個位置為subsequence的最後一個數字的最大長度
    	 * 它會是前面比他小的數字中，長度最大的那個長度+1
    	 * O(N^2)
    	 */
    	int[] dp = new int[nums.length];
    	dp[0] = 1;
    	int longestLength = 1;
    	
    	for (int i = 1; i < nums.length; i++) {
    		
    		int longestLengthBeforeI = 0;
    		
    		for (int j = 0; j < i; j++) {
    			if (nums[j] < nums[i]) {
					longestLengthBeforeI = Math.max(dp[j], longestLengthBeforeI);
				}
    		}
    		
    		dp[i] = longestLengthBeforeI + 1;
    		longestLength = Math.max(dp[i], longestLength);
    	}
    	
    	for (int i = 0; i < dp.length; i++) {
    		System.out.print(dp[i]+",");
    	}
    	
        return longestLength;
    }
}
