package DepthFirstSearch;
import java.util.HashSet;
import java.util.Set;

public class JumpGameIII {

	public static void main(String[] args) {
		/**
		 * 給定一array以及一個整數start
		 * 從start出發，每次可以往左or往右跳arr[start]格
		 * 回傳是否能跳到值=0的任何一格
		 */
		int[] arr = {4,2,3,0,3,1,2};
		System.out.println(canReach(arr, 5)); // true
		System.out.println(canReach(arr, 0)); // true
		int[] arr2 = {3,0,2,1,2};
		System.out.println(canReach(arr2, 2)); // false
	}

    public static boolean canReach(int[] arr, int start) {
    	/**
    	 * 每次往左or往右
    	 * 典型的樹狀圖，兩個子節點分別就是往左往右
    	 * => dfs
    	 * 再用一個set來紀錄到過的節點
    	 */
    	Set<Integer> visited = new HashSet<Integer>();
    	
        return dfs(arr, start, visited);
    }

	private static boolean dfs(int[] arr, int start, Set<Integer> visited) {
		
		if (start < 0 || start >= arr.length) {
			return false;
		}
		
		if (visited.contains(start)) {
			return false;
		}
		
		if (arr[start] == 0) {
			return true;
		}
		
		visited.add(start);
		
		return dfs(arr, start+arr[start], visited) || dfs(arr, start-arr[start], visited);
	}
}
