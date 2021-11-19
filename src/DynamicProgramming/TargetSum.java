package DynamicProgramming;

import java.util.HashMap;
import java.util.Map;

public class TargetSum {

	public static void main(String[] args) {
		
		int[] nums = {1,1,1,1,1};
		System.out.println(findTargetSumWays(nums, 3));
		int[] nums2 = {1};
		System.out.println(findTargetSumWays(nums2, 1));
		int[] nums3 = {0,0,0,0,0,0,0,0,1};
		System.out.println(findTargetSumWays(nums3, 1));
	}
	public static int findTargetSumWays(int[] nums, int target) {
    	/**
    	 * dfs + memo
    	 * 要紀錄兩個參數，index和target，
    	 * Map<String, Integer> key紀錄target, index 最慢
    	 * Map<Integer, int[]> 次之 (int[]要事先填充-1進去當做未訪問)
    	 * Map<Integer, Map<Integer, Integer>> 最快
    	 */
    	Map<Integer, Map<Integer, Integer>> memo = new HashMap<Integer, Map<Integer, Integer>>();
		
		return dfs(nums, 0, target, memo);
	}

    private static int dfs(int[] nums, int index, int target, Map<Integer, Map<Integer, Integer>> memo) {
		/**
		 * nums中每個數字都要使用，所以index超過時就是結算的時刻
		 * 對於nums中的每個數字，前面不是+就是-
		 * 所以樹狀圖每個節點都有兩個分支，加下一個數字or減下一個數字
		 */
    	if (index == nums.length) {
			return target == 0 ? 1 : 0;
		}
    	if (memo.containsKey(target) && memo.get(target).containsKey(index)) {
			return memo.get(target).get(index);
		}
    	
    	int minusWay = dfs(nums, index+1, target+nums[index], memo);
    	int plusWay = dfs(nums, index+1, target-nums[index], memo);
    	
    	Map<Integer, Integer> map = memo.getOrDefault(target, new HashMap<Integer, Integer>());
    	map.put(index, minusWay + plusWay);
    	memo.put(target, map);
		return minusWay + plusWay;
	}
    
	public static int findTargetSumWays2(int[] nums, int target) {
    	/**
    	 * dp tabulation
    	 * dp[i][j]代表的是用了前j個元素，組成總和i有幾種組合
    	 * 
    	 * 起始點:
    	 * dp[1][1] = 1
    	 * dp[-1][1] = 1
    	 * 當下一個1進來後
    	 * 可以延伸至-2, 0, 2
    	 * 
    	 * table: 
    	 *      1 1 1 1 1
    	 * -5           1
    	 * -4         1
    	 * -3       1   5
    	 * -2     1   4
    	 * -1   1   3   10
    	 * 0      2   6
    	 * 1    1   3   10
    	 * 2      1   4
    	 * 3        1   5
    	 * 4          1
    	 * 5   	        1
    	 */
    	int max = 0;
    	int min = 0;
    	for (int i = 0; i < nums.length; i++) {
    		max += nums[i];
    		min -= nums[i];
    	}
    	
    	Map<Integer, int[]> dp = new HashMap<Integer, int[]>();
    	for (int i = min; i <= max; i++) {
    		dp.put(i, new int[nums.length]);
    	}
    	
    	dp.get(nums[0])[0] += 1;
    	dp.get(-nums[0])[0] += 1;
    	
    	for (int j = 0; j < nums.length-1; j++) {
    		for (int i = min; i <= max; i++) {
    			if (dp.get(i)[j] != 0) { /* reachable => 影響下一直欄 */
    				
    				int newTargetPlus = i + nums[j+1];
    				int newTargetMinuse = i - nums[j+1];
    				dp.get(newTargetPlus)[j+1] += dp.get(i)[j];
    				dp.get(newTargetMinuse)[j+1] += dp.get(i)[j];
    			}
    		}
    	}
    	
    	for (int i = min; i <= max; i++) {
    		System.out.print(i+": ");
    		for (int j = 0; j < nums.length; j++) {
    			System.out.print(dp.get(i)[j]+",");
    		}
    		System.out.println();
    	}
    	
        return dp.containsKey(target) ? dp.get(target)[nums.length-1] : 0;
    }
}
