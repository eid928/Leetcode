package TwoPointer;

import java.util.HashMap;
import java.util.Map;

public class SubarraySumEqualsK {

	public static void main(String[] args) {
		/**
		 * 給定一組數列，以及一整數k
		 * 求出一共有多少組連續subarray，其和=k?
		 */
		int[] nums = {1,1,1};
		System.out.println(subarraySum(nums, 2));
		int[] nums2 = {1,2,3};
		System.out.println(subarraySum(nums2, 3));
		int[] nums3 = {1,2,1,2,1};
		System.out.println(subarraySum(nums3, 3));
	}

    public static int subarraySum(int[] nums, int k) {
    	/**
    	 * 連續subarray和為k，直覺上使用sliding window
    	 * 但array中有可能出現負數，所以無法直接判斷窗戶該向哪滑動
    	 * 除了用暴力法
    	 * 此題可以用hashmap作特殊解
    	 * 
    	 * 首先遍歷指針只有一個i，sum紀錄了從0到i的sum
    	 * 但中途的sum全部都會紀錄在map中
    	 * 設想一個狀況，現在走到i
    	 * (........................) sum
    	 * 我想要找到和為k的subarray，就要從前段剪掉一部份，於是我要找到前段是否有和為sum-k的部分
    	 * 假設有兩段：
    	 * (.....).......)............) sum
    	 *  sum-k
    	 *      sum-k
    	 * 則原本一大段個別減去這兩段都會是答案
    	 * 
    	 * 注意：map需初始化sum=0，count=1，這樣才能找到包含第一個數的subarray
    	 */
    	int result = 0;
    	int sum = 0;
    	Map<Integer, Integer> sumMap = new HashMap<>();
    	
    	sumMap.put(0, 1);
    	/**
    	 * key: 0~某i的sum
    	 * value: count
    	 */
    	
    	for (int i = 0; i < nums.length; i++) {
    		
    		sum += nums[i];
    		result += sumMap.getOrDefault(sum-k, 0);
    		sumMap.put(sum, sumMap.getOrDefault(sum, 0)+1);
    	}
    	
    	return result;
    }
}
