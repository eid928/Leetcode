package DepthFirstSearch;

public class SumofAllSubsetXORTotals {

	public static void main(String[] args) {
		/**
		 * 給定一組array
		 * 求出所有subset的xor總和
		 */
		int[] nums = {1,3};
		System.out.println(subsetXORSum(nums)); // 6
		int[] nums2 = {5,1,6};
		System.out.println(subsetXORSum(nums2)); // 28
	}
	
	private static int sum;

    public static int subsetXORSum(int[] nums) {
    	/**
    	 * 和subset一樣
    	 * xor運算符若再次使用時，則可以回到原本的數值=>backstracking
    	 */
    	sum = 0;
    	dfs(nums, 0, 0);
    	
        return sum;
    }

	private static void dfs(int[] nums, int start, int curVal) {
		
		sum += curVal;
		
		for (int i = start; i < nums.length; i++) {
			
			curVal ^= nums[i];
			dfs(nums, i+1, curVal);
			curVal ^= nums[i];
		}
	}
}
