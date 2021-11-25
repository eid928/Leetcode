package DynamicProgramming;

public class LongestSubarrayof1AfterDeletingOneElement {

	public static void main(String[] args) {
		/**
		 * 給定一組array
		 * 必須先從array中刪除一個數後
		 * 回傳刪除後最長的subarray，其值全部為1
		 */
		int[] nums = {0,1,1,1,0,1,1,0,1};
		int[] nums2 = {1,1,1};
		System.out.println(longestSubarray(nums));
		System.out.println(longestSubarray(nums2));
	}

    public static int longestSubarray(int[] nums) {
    	/**
    	 * dp
    	 * 用兩組dp array來紀錄
    	 * 一組dp[i]紀錄在i的左邊，從上一個0到i(不含i)的最長全1數列的長度
    	 * 另一組紀錄右邊
    	 * nums:   0,1,1,1,0,1,1,0,1
    	 * left:   0 0 1 2 3 0 1 2 0
    	 * right:  3 2 1 0 2 1 0 1 0
    	 * 將兩組分別加起來，就是刪除該值後可以得到的全i數列的長度
    	 * 取最大的那個
    	 * time: O(n)
    	 */
        
    	int[] longest1sOnLeft = new int[nums.length];
    	int[] longest1sOnRight = new int[nums.length];
    	
    	int lengthOf1s = 0;
    	for (int i = 0; i < nums.length; i++) {
    		
    		longest1sOnLeft[i] = lengthOf1s;
    		if (nums[i] == 1) {
				lengthOf1s ++;
			} else {
				lengthOf1s = 0;
			}
    	}
    	
    	lengthOf1s = 0;
    	int result = 0;
    	for (int i = nums.length-1; i >= 0; i--) {
    		
    		longest1sOnRight[i] = lengthOf1s;
    		result = Math.max(result, longest1sOnLeft[i]+longest1sOnRight[i]);
    		if (nums[i] == 1) {
				lengthOf1s ++;
			} else {
				lengthOf1s = 0;
			}
    	}
    	
    	return result;
    }
}
