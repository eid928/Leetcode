package DepthFirstSearch;

import java.util.ArrayList;
import java.util.Arrays;
import java.util.List;

public class PermutationsII {

	public static void main(String[] args) {
		/**
		 * 和permutation一樣求所有的排列
		 * 但不同的是array中有可能有一樣的數字(N!/S!, N=數字數量, S=重複的數字數量)
		 */
		int[] nums1 = {1,1,2};
		int[] nums2 = {1,2,3};
		
		System.out.println(permuteUnique(nums1));
		System.out.println(permuteUnique(nums2));
	}

    public static List<List<Integer>> permuteUnique(int[] nums) {
        /**
         * dfs
         * 由於array中有可能有duplicate，所以在dfs之前先將nums做排序
         */
    	List<List<Integer>> results = new ArrayList<>();
    	List<Integer> result = new ArrayList<>();
    	int[] visited = new int[nums.length];
    	Arrays.sort(nums);
    	
    	dfs(nums, result, results, visited);
    	
    	return results;
    }

	private static void dfs(int[] nums, List<Integer> result, List<List<Integer>> results, int[] visited) {
		
		if (result.size() == nums.length) {
			results.add(new ArrayList<>(result));
			return;
		}
		
		for (int i = 0; i < nums.length; i++) {
			if (visited[i] == 1) {
				continue;
			}
			/**
			 * 為避免duplicate造成相同排列所設的條件
			 * 以1,1,2為例，為區分不同的1先寫成1,1',2
			 * 若將不同的1看作不同，則總共有六種排列為：
			 * [1, 1', 2], [1, 2, 1'], [1', 1, 2], [1', 2, 1], [2, 1, 1'], [2, 1', 1]
			 * 為了排除重複，其實我們只要規定1'一定要出現在1後面就好
			 * 也就是說當我們發現nums[i] == nums[i-1]，1'==1為重複的數字
			 * 且這個時候1還沒有被visited，此時插入1'就會造成1'會在1前面違反我們的規定
			 * 所以continue跳過
			 */
			if (i-1>=0 && nums[i] == nums[i-1] && visited[i-1] == 0) {
				continue;
			}
			result.add(nums[i]);
			visited[i] = 1;
			dfs(nums, result, results, visited);
			result.remove(result.size()-1);
			visited[i] = 0;
		}
	}
}
