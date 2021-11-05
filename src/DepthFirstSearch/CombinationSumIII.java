package DepthFirstSearch;

import java.util.ArrayList;
import java.util.List;

public class CombinationSumIII {

	public static void main(String[] args) {
		/**
		 * 給定兩正整數k, n
		 * 2 <= k <= 9
		 * 1 <= n <= 60
		 * 必須使用k個數字來達成sum＝n
		 * 可以使用1~9，每種數字最多只能使用一次
		 * 求出所有可能的組合
		 */
		System.out.println(combinationSum3(3, 9));
	}

    public static List<List<Integer>> combinationSum3(int k, int n) {
    	/**
    	 * dfs
    	 * 從1開始dfs
    	 * 只遍歷起點之後的數字，例如起點為5，則後續只接6~9，避免出現相同數字但排序不同的組合
    	 */
    	
    	List<List<Integer>> results = new ArrayList<>();
    	List<Integer> result = new ArrayList<>();
    	int currSum = 0;
    	
    	dfs(k, n, 1, currSum, result, results);
    	
        return results;
    }

	private static void dfs(int k, int targetSum, int start, int currSum, List<Integer> result, List<List<Integer>> results) {
		
		if (result.size() > k || currSum > targetSum) { /* 用的數字超過或是和超過就放棄 */
			return;
		}
		
		if (result.size() == k && currSum == targetSum) {
			results.add(new ArrayList<>(result));
			return;
		}
		
		for (int i = start; i < 10; i++) {
			
			result.add(i);
			dfs(k, targetSum, i+1, currSum+i, result, results);
			result.remove(result.size()-1);
		}
	}
}
