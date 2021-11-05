package DynamicProgramming;

public class PartitionEqualSubsetSum {

	public static void main(String[] args) {
		/**
		 * 給定一組array，array內全是正整數
		 * 求出這組array是否能被分成兩組sum相等的集合
		 * ex: 1,5,11,5 => 1,5,5 & 11 => true
		 */
		int[] nums = {1,5,11,5};
		int[] nums2 = {1,2,3,5};
		int[] nums3 = {3,3,3,4,5};
		
		System.out.println(canPartition(nums));
		System.out.println(canPartition(nums2));
		System.out.println(canPartition(nums3));
	}

    public static boolean canPartition(int[] nums) {
        /**
         * 先求出所有數的和，若和為奇數則一定為false
         * target則為sum/2
         * 題目則變為是否能以數個array中的數字相加為target
         * 可以用dfs但效率太差
         */
    	int sum = 0;
    	for (int num : nums) sum += num;
    	if (sum % 2 != 0) return false;
    	int target = sum / 2;
    	
    	/**
    	 * dp，dp[i]儲存的是是否能以數個array中的數字相加為i
    	 * 最終回傳dp[target]
    	 * 先思考狀態轉移公式，也就是dp[]可以如何從其他dp的資訊得出
    	 * 若我們先遍歷nums，一個數字一個數字加進來看，以{1,5,11,5}為例子，target＝11
    	 * 先看1，更新dp array，1結束時，dp應該index=0,1為true，其他皆為false
    	 * 接著看5，更新dp array
    	 * 當我們有了5之後，5~11(target)的dp皆有可能變成true，
    	 * 所以我們在5的時候，dp需要從index=5遍歷到index=11
    	 * 例如dp[6] = dp[1]，因為5+1=6
    	 * 所以dp[j] = dp[j] || dp[j-nums[i]];
    	 */
    	boolean[] dp = new boolean[target+1];
    	dp[0] = true;
    	
    	for (int i = 0; i < nums.length; i++) {
    		
    		for (int j = dp.length-1; j >= nums[i]; j--) {
    			
    			dp[j] = dp[j] || dp[j-nums[i]];
    		}
    	}
    	
    	return dp[target];
    }
}
