package DynamicProgramming;

import java.util.ArrayList;
import java.util.Arrays;
import java.util.HashMap;
import java.util.List;
import java.util.Map;

public class LargestDivisibleSubset {

	public static void main(String[] args) {
		
		int[] nums = {1,2,3};
		System.out.println(largestDivisibleSubset(nums));
		int[] nums2 = {1,2,4,8};
		System.out.println(largestDivisibleSubset(nums2));
		int[] nums3 = {2,3,4,9,8};
		System.out.println(largestDivisibleSubset(nums3));
	}
	
	private static List<Integer> largestResult;

    public static List<Integer> largestDivisibleSubset(int[] nums) {
        
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
