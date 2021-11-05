package TwoPointer;

import java.util.ArrayList;
import java.util.Arrays;
import java.util.List;

public class ThreeSum {

	public static void main(String[] args) {
		/**
		 * 求出所有三數之和為0的組合
		 */
		int[] nums = {-1,0,1,2,-1,-4};
		System.out.println(threeSum(nums));
	}
	
    public static List<List<Integer>> threeSum(int[] nums) {
    	/**
    	 * sort後用雙指針
    	 */
    	if (nums.length < 3) {
			return new ArrayList<>();
		}
    	Arrays.sort(nums);
    	List<List<Integer>> results = new ArrayList<>();
    	for (int i = 0; i < nums.length-2; i ++) {
    		if (nums[i] > 0) break;
    		if (i > 0 && nums[i] == nums[i - 1]) continue;
    		int targetFor2Sum = 0 - nums[i];
    		int left = i + 1;
    		int right = nums.length-1;
    		while (left < right) {
    			
    			int twoSum = nums[left] + nums[right];
    			if (targetFor2Sum == twoSum) { /* 找到符合的組合 */
    				List<Integer> result = new ArrayList<>();
    				result.add(nums[i]);
    				result.add(nums[left]);
    				result.add(nums[right]);
    				results.add(result);
    				left++;
    				right--;
    				/* 繼續尋找下一組不一樣的組合 */
    				while (left < right && nums[left] == nums[left - 1]) left++;
    		        while (left < right && nums[right] == nums[right + 1]) right--;
    		        continue;
				}
    			if (targetFor2Sum > twoSum) {
					left ++;
				} else {
					right --;
				}
    		}
    	}
    	
        return results;
    }
}
