package DynamicProgramming;

import java.util.ArrayList;
import java.util.Arrays;
import java.util.HashMap;
import java.util.LinkedList;
import java.util.List;
import java.util.Map;

public class LargestDivisibleSubset {

	public static void main(String[] args) {
		/**
		 * 給定一組array
		 * 求出最長的可整除subset
		 * 可整除subset表示，其成員可以倆倆互相整除
		 */
		int[] nums = {1,2,3};
		System.out.println(largestDivisibleSubset(nums));
		int[] nums2 = {1,2,4,8};
		System.out.println(largestDivisibleSubset(nums2));
		int[] nums3 = {2,3,4,9,8};
		System.out.println(largestDivisibleSubset(nums3));
		int[] nums4 = {3,4,16,8};
		System.out.println(largestDivisibleSubset(nums4));
	}
	
	public static List<Integer> largestDivisibleSubset(int[] nums) {
		/**
		 * tabulation dp
		 * 不直接複製list
		 * 而是紀錄parent，也就是在subset中的上一個元素的index
		 * dp[i][j]
		 * dp[i][0] = 以nums[i]結尾的最大可整除subset大小
		 * dp[i][1] = 以nums[i]結尾的最大可整除subset，nums[i]的上一個元素的index
		 * 例如[1,2,3]
		 * 以2為結尾的最大可整除subset為1,2
		 * 則dp[2][0] = 2, dp[2][1] = 0(1的index)
		 * 轉移方程:
		 * dp[i][0] = Max(dp[j][0])+1, where nums[i] % nums[j] == 0
		 * dp[i][1] = j
		 * time: O(N^2)
		 */
		int[][] dp = new int[nums.length][2];
		Arrays.sort(nums);
		dp[0][0] = 1;
		dp[0][1] = -1;
		
		int largestSubsetTail = 0;
		int largestSubsetSize = 1;
		
		for (int i = 1; i < nums.length; i++) {
			
			for (int j = 0; j < i; j++) {
				
				if (nums[i] % nums[j] == 0 && dp[j][0] >= dp[i][0]) {
					dp[i][0] = dp[j][0]+1;
					dp[i][1] = j;
				}
			}
			if (dp[i][0] == 0) {
				dp[i][0] = 1;
				dp[i][1] = -1;
			}
			System.out.print(nums[i]+":["+dp[i][0]+","+dp[i][1]+"], ");
			if (dp[i][0] > largestSubsetSize) {
				largestSubsetSize = dp[i][0];
				largestSubsetTail = i;
			}
		}
		
		LinkedList<Integer> result = new LinkedList<Integer>();
		
		for (int i = 0; i < largestSubsetSize; i++) {
			result.addFirst(nums[largestSubsetTail]);
			largestSubsetTail = dp[largestSubsetTail][1];
		}
		
		return result;
	}
	
	private static List<Integer> largestResult;

    public static List<Integer> largestDivisibleSubset2(int[] nums) {
        /**
         * 先排序後用dfs + memo的做法
         * time: O(N^3)
         * 雖然有memo，只需要找到0~N的答案
         * 且每個答案for i都要回頭找0~i => O(N^2)
         * 再加上複製array這一步驟又需要O(N)
         */
    	Arrays.sort(nums);
    	largestResult = new ArrayList<Integer>();
    	Map<Integer, List<Integer>> memo = new HashMap<Integer, List<Integer>>();
    	largestDivisibleSubset(nums, nums.length-1, memo);
    	System.out.println(memo);
    	
    	return largestResult;
    }

	private static List<Integer> largestDivisibleSubset(int[] nums, int lastIndex, Map<Integer, List<Integer>> memo) {
		
		if (memo.containsKey(lastIndex)) {
			return memo.get(lastIndex);
		}
		
		List<Integer> result = new ArrayList<Integer>();
		
		for (int i = 0; i < lastIndex; i++) {
			
			List<Integer> resultForI = largestDivisibleSubset(nums, i, memo);
			if (nums[lastIndex] % nums[i] == 0 && resultForI.size() > result.size()) {
				result = new ArrayList<Integer>(resultForI);
			}
		}
		
		result.add(nums[lastIndex]);
		memo.put(lastIndex, result);
		if (result.size() > largestResult.size()) {
			largestResult = result;
		}
		
		return result;
	}
}
