package DepthFirstSearch;

import java.util.Arrays;

public class PartitiontoKEqualSumSubsets {

	public static void main(String[] args) {
		/**
		 * 給定一組array以及一正整數k
		 * 請回傳此array是否能均分為k組subset
		 * 每一組subset的和相等
		 */
		int[] nums = {4,3,2,3,5,2,1};
		System.out.println(canPartitionKSubsets(nums, 4));
		
		int[] nums2 = {1,2,3,4};
		System.out.println(canPartitionKSubsets(nums2, 3));
		
		int[] nums3 = {1,1,1,1,2,2,2,2};
		System.out.println(canPartitionKSubsets(nums3, 2));
		
		int[] nums4 = {1,1,1,1,2,2,2,2};
		System.out.println(canPartitionKSubsets(nums4, 4));
	}

    public static boolean canPartitionKSubsets(int[] nums, int k) {
    	/**
    	 * 只需回答是or否
    	 * 不需紀錄subset
    	 */
    	int sum = 0;
    	for (int num : nums) sum += num;
    	
    	if (sum % k != 0) return false;
    	
    	boolean[] visited = new boolean[nums.length];
    	Arrays.sort(nums);
    	
    	System.out.println("target:"+sum/k);
    	
        return dfs(nums, 0, 0, sum/k, k, visited);
    }

	private static boolean dfs(int[] nums, int index, int currSum, int target, int k, boolean[] visited) {
		/**
    	 * 思路：
    	 * 使用dfs，每找到一組和=target的，就紀錄哪些index用過了
    	 * 之後重新執行dfs，再次從頭找，但k-1
    	 * 若k=1則回傳true
    	 */
		if (k == 1) {
			return true;
		}
		
		if (currSum > target) return false;
		
		if (currSum == target) {
			/**
			 * 找到一組後必須再重新查找
			 * 但已找到的會標註為visited，所以可以略過
			 */
			return dfs(nums, 0, 0, target, k-1, visited);
		}
		
		for (int i = index; i < nums.length; i++) {
			
			if (visited[i]) {
				continue;
			}
			visited[i] = true;
			if (dfs(nums, index+1, currSum+nums[i], target, k, visited)) return true;
			visited[i] = false;
		}
		
		return false;
	}
}
