package DepthFirstSearch;
import java.util.ArrayList;
import java.util.List;

public class CombinationSum {

	public static void main(String[] args) {
		/**
		 * 給定候選數字組和目標
		 * 回傳所有可用候選數字組相加成目標的組合
		 * 候選數字沒有使用次數限制，可重複使用
		 */
		int[] candidates = {2,3,6,7};
		int target = 7;

		System.out.println(combinationSum(candidates, target));
	}
    public static List<List<Integer>> combinationSum(int[] candidates, int target) {
    	
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
		/**
		 * dfs
		 * currSum紀錄目前組合的和
		 * start參數是為了不出現重複的組合，例如2,2,3跟2,3,2
		 * 當以2為起點的時候，後續能用的有2,3,6,7
		 * 當以3為起點的時候，後續能用的就只有3,6,7，避免重複
		 */
		for (int i = start; i < candidates.length; i++) {
			
			theResult.add(candidates[i]);
			dfs(candidates, target, currSum + candidates[i], theResult, results, i);
			theResult.remove(theResult.size()-1);
		}
	}
}
