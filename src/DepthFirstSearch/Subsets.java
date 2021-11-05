package DepthFirstSearch;
import java.util.ArrayList;
import java.util.List;

public class Subsets {

	public static void main(String[] args) {
		/**
		 * 給定一個array，回傳所有Subset，包括全空
		 * ex: 1,2,3 -> [[],[1],[2],[1,2],[3],[1,3],[2,3],[1,2,3]]
		 */
		int[] nums = {1,2,3};
		System.out.println("final answer: " + subsets(nums));
		System.out.println("final answer2: " + subsets2(nums));
	}
	
    public static List<List<Integer>> subsets(int[] nums) {
        /**
         * 一樣是求出所有組合的題目->DFS
         * 雖然每種組合長度不一，但其實就是每種數字只有兩種狀態：有or沒有
         * 只要確認好每個數字的狀態，就會完成一組組合
         * 可以想成二元樹，只有true or false選項
         */
    	List<List<Integer>> results = new ArrayList<>();
    	List<Boolean> statuses = new ArrayList<>();
    	
    	dfs(nums, true, statuses, results); /* true開頭的二元樹 */
    	dfs(nums, false, statuses, results); /* false開頭的二元樹 */
    	
    	return results;
    }

	private static void dfs(int[] nums, boolean isExist, List<Boolean> statuses, List<List<Integer>> results) {
		
		if (statuses.size() == nums.length-1) {
			List<Boolean> newStatuses = new ArrayList<>(statuses);
			newStatuses.add(isExist);
			List<Integer> theResult = new ArrayList<>();
			
			for (int i = 0; i < newStatuses.size(); i++) {
				if (newStatuses.get(i)) theResult.add(nums[i]);
			}
			results.add(theResult);
			return;
		}
		List<Boolean> newStatuses = new ArrayList<>(statuses);
		newStatuses.add(isExist);
		dfs(nums, true, newStatuses, results);
		dfs(nums, false, newStatuses, results);
	}
	
	public static List<List<Integer>> subsets2(int[] nums) {
        /**
         * 第二種寫法：更簡單的dfs
         */
    	List<List<Integer>> results = new ArrayList<>();
    	List<Integer> theResult = new ArrayList<>();
    	
    	dfs2(nums, 0, theResult, results);
    	
    	return results;
    }

	private static void dfs2(int[] nums, int start, List<Integer> theResult, List<List<Integer>> results) {
		/**
		 * 不管目前狀態是什麼直接加，因為都是符合的subset
		 */
		results.add(new ArrayList<>(theResult));
		
		for (int i = start; i < nums.length; i++) {
			
			theResult.add(nums[i]);
			dfs2(nums, i+1, theResult, results);
			theResult.remove(theResult.size()-1);
		}
	}
}
