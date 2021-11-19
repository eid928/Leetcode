package DynamicProgramming;

public class MaximumLengthofRepeatedSubarray {

	public static void main(String[] args) {
		
		int[] nums1 = {1,2,3,2,1};
		int[] nums2 = {3,2,4,1,7};
		System.out.println(findLength(nums1, nums2)); // 2: 3,2
		int[] nums3 = {0,0,0,0,0};
		int[] nums4 = {0,0,0,0,0};
		System.out.println(findLength(nums3, nums4)); // 5: 0,0,0,0,0
	}

    public static int findLength(int[] nums1, int[] nums2) {
    	/**
    	 * dp tabulation
    	 * subarray => 一定要連續
    	 * 因為需要連續所以
    	 * dp[i][j]代表兩個array以i,j項為結尾的最長相同subarray長度
    	 * 例如
    	 * 1,2,3,2 vs 3,2 答案為2
    	 * 1,2,3,2,1 vs 3,2 答案為0
    	 * dp[0][0]代表[] vs []，故dp[0][0] = 0
    	 * 
    	 * dp[i][j] = dp[i-1][j-1] + 1 if nums1[i] == nums2[j]
    	 *          = 0 if nums1[i] != nums2[j]
    	 *          
    	 * table: 
    	 *   1,2,3,2,1
    	 * 3 0 0 1 0 0
    	 * 2 0 1 0 2 0
    	 * 4 0 0 0 0 0 => 最大是2
    	 * 1 1 0 0 0 1
    	 * 7 0 0 0 0 0 
    	 */
    	int[][] dp = new int[nums1.length+1][nums2.length+1];
    	int maximumLen = 0;
    	
    	for (int i = 1; i <= nums1.length; i++) {
    		for (int j = 1; j <= nums2.length; j++) {
    			
    			if (nums1[i-1] == nums2[j-1]) {
					dp[i][j] = dp[i-1][j-1] + 1;
				} else {
					dp[i][j] = 0;
				}
    			maximumLen = Math.max(maximumLen, dp[i][j]);
    		}
    		
    	}
        return maximumLen;
    }
}
