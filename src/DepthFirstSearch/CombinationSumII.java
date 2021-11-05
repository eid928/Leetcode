package DepthFirstSearch;
import java.util.ArrayList;
import java.util.Arrays;
import java.util.List;

public class CombinationSumII {

	public static void main(String[] args) {
		/**
		 * Combination Sum的相似題目
		 * 給定候選數字組和目標
		 * 回傳所有可用候選數字組相加成目標的組合
		 * 候選數字只能使用一次
		 */
		int[] candidates = {10,1,2,7,6,1,5};
		int target = 8;
		System.out.println(combinationSum2(candidates, target));
	}
	
    public static List<List<Integer>> combinationSum2(int[] candidates, int target) {
        
    	Arrays.sort(candidates);
    	/**
    	 * 例如以第一個1為出發點可以得到的1,7
    	 * 以及以7為出發點，可以得到的7,1，這兩者是一樣的
    	 * 為了避免出現一樣的組合，先排序，則一樣的數字會出現在隔壁
    	 * {1,1,2,5,6,7,10}
    	 * 以第一個1為起點dfs結束後，應該到得到[1, 1, 6], [1, 2, 5], [1, 7]這三組答案
    	 * 而走到第二個1的時候，因為起點一樣，所以直接跳過，就可以避免重複
    	 */
    	
    	List<List<Integer>> results = new ArrayList<>();
    	List<Integer> theResult = new ArrayList<>();
    	
    	dfs(candidates, target, 0, theResult, results, 0);
    	
    	return results;
    }

	private static void dfs(int[] candidates, int target, int currSum, List<Integer> theResult, List<List<Integer>> results, int start) {
		
		if (currSum == target) {
			results.add(new ArrayList<>(theResult));
			return;
		}
		if (currSum > target) {
			return;
		}
		
		for (int i = start; i < candidates.length; i++) {
			
			if (i > start && candidates[i] == candidates[i-1]) {
				/**
				 * i>start:
				 * 當第一個1為起點的時候，後面的1要可以用(start=1, i=1)
				 * 但當第二個1為起點的時候，要直接跳過(start=0, i=1)
				 */
				continue;
			}
			
			theResult.add(candidates[i]);
			dfs(candidates, target, currSum + candidates[i], theResult, results, i+1);
			theResult.remove(theResult.size()-1);
		}
	}
}
