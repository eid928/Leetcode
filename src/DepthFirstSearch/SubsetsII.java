package DepthFirstSearch;

import java.util.ArrayList;
import java.util.Arrays;
import java.util.List;

public class SubsetsII {

	public static void main(String[] args) {
		
		int[] nums = {1,2,2};
		System.out.println(subsetsWithDup(nums));
	}
	
    public static List<List<Integer>> subsetsWithDup(int[] nums) {
        
    	List<List<Integer>> results = new ArrayList<>();
    	List<Integer> result = new ArrayList<>();
    	Arrays.sort(nums);
    	dfs(nums, 0, result, results);
    	
    	return results;
    }

	private static void dfs(int[] nums, int start, List<Integer> result, List<List<Integer>> results) {
		
		results.add(new ArrayList<>(result));
		
		for (int i = start; i < nums.length; i++) {
			/**
			 * 為避免重複的條件
			 * 假設1,2,2'
			 * 若要加入2'，必須加在2的後面
			 * 若這個數字和上一個數字一樣，且i不是start，表示2沒有在目前的result中(因為remove)
			 */
			if (i >= 1 && i != start && nums[i] == nums[i-1]) {
				continue;
			}
			
			result.add(nums[i]);
			dfs(nums, i+1, result, results);
			result.remove(result.size()-1);
		}
	}
}
