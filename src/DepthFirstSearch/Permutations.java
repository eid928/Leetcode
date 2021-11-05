package DepthFirstSearch;
import java.util.ArrayList;
import java.util.List;

public class Permutations {

	public static void main(String[] args) {
		/**
		 * 求nums中數字的所有排列組合，排序不同算是不同組合
		 */
		int[] nums = {1,2,3};
		System.out.println(permute(nums));
	}
	
    public static List<List<Integer>> permute(int[] nums) {
        /**
         * 這題挺困難的，採用DFS
         */
    	List<List<Integer>> permutes = new ArrayList<>();
    	List<Integer> permute = new ArrayList<>();
    	boolean[] visited = new boolean[nums.length];
    	
    	dfs(permutes, permute, nums, visited, 0);
    	
    	return permutes;
    }

    /**
     * @param permutes 最終要回傳的所有組合
     * @param permute 目前DFS路線的組合
     * @param nums 題目
     * @param visited 紀錄目前DFS路線有哪些數字訪問了
     * @param level 紀錄目前DFS路線用了幾個數字，一旦滿了就表示完成一個組合
     */
	public static void dfs(List<List<Integer>> permutes, List<Integer> permute, int[] nums, boolean[] visited, int level) {
		
		if (level == nums.length) {
			/* 滿了，把完成的組合加進去 */
			/* 注意：要加new arraylist重新做一個新的，否則return後下一行，permute.remove會影響到permutes裡面的元素 */
			permutes.add(new ArrayList<>(permute));
			return;
		}
		
		for (int i = 0; i < nums.length; i++) {
			
			if (visited[i]) {
				continue;
			}
			/* 各個組合怎麼來？1,2,3 & 1,3,2為例子 */
			visited[i] = true;
			permute.add(nums[i]);
			/* 第一次順順地走會完成1,2,3，當走到2的時候，dfs最終勢必會完成1,2,3加入答案並return */
			dfs(permutes, permute, nums, visited, level+1);
			/* 此時，以2為起點的訪問已經結束了，permute＝1,2，remove後變成1，並將2標註為訪問結束
			 * 在下一個loop，走到3，未訪問，這時便加進permute變成1,3，之後進入dfs完成1,3,2 */
			permute.remove(permute.size()-1);
			visited[i] = false;
			/**
			 * 重點在於完成dfs後，下一行即代表以這個index為起點的訪問都結束了
			 * 須將這個index重新標注為訪問結束，並退回來尋求別的路線
			 */
			
			/* 不remove最後一個的寫法：每次都重新創一個新的permute
			 * 可參考Combination及Letter Combination Of A Phone Number
			visited[i] = true;
			List<Integer> newPermute = new ArrayList<>(permute);
			newPermute.add(nums[i]);
			dfs(permutes, newPermute, nums, visited, level+1);
			visited[i] = false;
			*/
		}
	}
}
