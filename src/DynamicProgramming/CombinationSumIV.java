package DynamicProgramming;

import java.util.HashSet;
import java.util.Set;

public class CombinationSumIV {

	public static void main(String[] args) {
		/*
		給定一組array，以及一正整數目標
		求出所有sum＝目標的組合數一共有多少
		順序不同算是不同組，且array中沒有重複的數字
		*/
		int[] nums = {1,2,3};
		int target = 4;
		/*
		The possible combination ways are:
		(1, 1, 1, 1) dp[3]
		(1, 1, 2) dp[2]
		(1, 2, 1) dp[3]
		(1, 3) dp[1]
		(2, 1, 1) dp[3]
		(2, 2) dp[2]
		(3, 1) dp[3]
		Note that different sequences are counted as different combinations.
		*/
		int combinationSum4 = combinationSum4(nums, target);
		System.out.println(combinationSum4);
	}
	
    public static int combinationSum4_2(int[] nums, int target) {
    	/**
    	 * dp
    	 * dp[i]儲存target＝i的結果
    	 * step1: 先將nums存進set中以便之後查找
    	 * step2: 雙遍歷dp
    	 * 若此時在i，則tempTarget = i
    	 * j從0遍歷到i-1
    	 * dp[j]所有結果的和都是j，若set中有i-j這個數字，則dp[j]所有的結果加上這個數字就能達成i
    	 * => dp[i] += dp[j]
    	 * 時間複雜度＝O(target^2)
    	 */
    	int[] dp = new int[target+1];
    	dp[0] = 1;
    	Set<Integer> numsSet = new HashSet<>();
    	for (int i = 0; i < nums.length; i++) {
    		numsSet.add(nums[i]);
    	}
    	
    	for (int i = 1; i < target+1; i++) {
    		int count = 0;
    		for (int j = 0; j < i; j++) {
    			int temp = i - j;
				if (numsSet.contains(temp)) {
					count += dp[j];
				}
    		}
    		dp[i] = count;
    	}
    	
        return dp[target];
    }
    
    public static int combinationSum4(int[] nums, int target) {
    	/**
    	 * dp
    	 * 上面的優化，可減少遍歷的長度
    	 * 因為
    	 * 1 <= nums.length <= 200
    	 * 1 <= target <= 1000
    	 * 遍歷nums是更好的選擇
    	 * dp[i]可直接加上dp[i-num]因為dp[i-num]的目標就是i-num，而有num則一定可以組成i
    	 * 時間複雜度＝O(target * nums.length)
    	 */
		int[] dp = new int[target+1];
    	dp[0] = 1;
    	
    	for (int i = 1; i < target+1; i++) {
    		
    		for (int num : nums) {
    			if (i - num >= 0) {
					dp[i] += dp[i-num];
				}
    		}
    	}
    	
        return dp[target];
	}
}
