package DepthFirstSearch;
import java.util.ArrayList;
import java.util.List;

public class Combination {

	public static void main(String[] args) {
		/**
		 * 參數n,k
		 * 用1~n的數字，總共用k個
		 * 回傳所有可能的組合，不論排序
		 */
		System.out.println(combine(4, 2));
	}

    public static List<List<Integer>> combine(int n, int k) {
    	
    	List<List<Integer>> result = new ArrayList<>();
    	List<Integer> combine = new ArrayList<>();
    	
    	dfs(n, k, result, combine, 1);
    	
        return result;
    }

	private static void dfs(int n, int k, List<List<Integer>> result, List<Integer> combine, int level) {
		/**
		 * dfs: 以n=4, k=2作為例子
		 * 由於不論排序，以1為起點的時候，後續可以接2,3,4
		 * 而輪到2為起點的時候，就可以不用考慮1了，後續只能接3,4
		 * 越後面的數字當作起點，他的選擇就越少
		 * 畫成圖就是：每條線就是一個組合
		 * 1 - 2 - 3 - 4
		 *   \ 3 \ 4
		 *   \ 4
		 */
		if (combine.size() == k) {
			System.out.println(combine);
			result.add(new ArrayList<>(combine));
			return;
		}
		/**
		 * i = level，從1開始
		 */
		for (int i = level; i <= n; i++) {
			
			combine.add(i);
			/* 當i＝1作為起點的時候，後續只能選2,3,4，也就是level要給2 */
			dfs(n, k, result, combine, i+1);
			combine.remove(combine.size()-1);
			
			/* 不remove最後一個的寫法：每次都重新創一個新的combine
			 * 另外一題Letter Combination Of A Phone Number是拼接字串的
			 * 就是用這種寫法
			 * 但List要重新複製比較不適合
			List<Integer> newCombine = new ArrayList<>(combine);
			newCombine.add(i);
			dfs(n, k, result, newCombine, i+1);
			*/
		}
	}
}
